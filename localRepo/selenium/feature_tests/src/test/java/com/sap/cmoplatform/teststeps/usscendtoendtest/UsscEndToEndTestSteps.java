package com.sap.cmoplatform.teststeps.usscendtoendtest;

import com.sap.cmoplatform.pages.blockchainexplorer.BlockChainExplorerPage;
import com.sap.cmoplatform.pages.verificationlog.VerificationLogPage;
import com.sap.cmoplatform.pages.verifyproductpackage.VerifyProductPackagePage;
import com.sap.cmoplatform.pages.writelogs.WriteLogsPage;
import com.sap.cmoplatform.objects.BlockChainExplorer;
import com.sap.cmoplatform.objects.VerificationLog;
import com.sap.cmoplatform.objects.WriteLog;
import com.sap.cmoplatform.utils.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UsscEndToEndTestSteps {
    private WriteLogsPage writeLogsPage = new WriteLogsPage();
    private VerifyProductPackagePage verifyProductPackagePage = new VerifyProductPackagePage();
    private VerificationLogPage verificationLogPage = new VerificationLogPage();
    private BlockChainExplorerPage blockChainExplorerPage = new BlockChainExplorerPage();

    private final Properties uploadproductpackdata = PropertyReader.loadProperties("uploadproductpackdata/UploadProductPackData.properties");

    private String identifier = uploadproductpackdata.getProperty("Identifier");
    private String lot = uploadproductpackdata.getProperty("LOT");
    private String uuid = "", blockChainStreamLink = "";

    @And("set filter value messageId in write logs page")
    public void setFilterValueMessageIdInWriteLogsPage() throws Exception {
        writeLogsPage.enterMessageId(identifier);
    }

    @And("set filter value lot in write logs page")
    public void setFilterValueLotInWriteLogsPage() throws Exception {
        writeLogsPage.enterLot(lot);
    }

    @Then("the results contains the filtered values in the write log response")
    public void theResultsContainsFilteredValuesInTheWriteLogResponse(Map<String, String> expectedLogDetails) throws Exception {
        List<WriteLog> actualLog = writeLogsPage.getWriteLogs();
        for (WriteLog log : actualLog) {

            if (expectedLogDetails.containsKey("LOT")) {
                assertEquals(log.getLot(), lot);
            }
            if (expectedLogDetails.containsKey("MessageId")) {
                assertEquals(log.getMessageId(), identifier);
            }

        }
    }

    @And("the Batch Number is entered")
    public void theBatchNumberIsEntered() throws Exception {
        verifyProductPackagePage.enterBatchNumber(lot);
    }

    @And("get UUID value from verification response screen")
    public void getUUIDValueFromVerificationResponseScreen() throws Exception {
        uuid = verifyProductPackagePage.getResponseValue("Correlation UUID");
    }

    @And("set UUID value")
    public void setUUIDValue() throws Exception {
        verificationLogPage.enterUUID(uuid);
    }


    @Then("the response contains the filtered values")
    public void theResponseContainsFilteredValues(Map<String, String> expectedLogDetails) throws Exception {
        List<VerificationLog> actualLog = verificationLogPage.getLogs();
        for (VerificationLog log : actualLog) {
            if (expectedLogDetails.containsKey("GTIN")) {
                assertThat(log.getGtin(), is(expectedLogDetails.get("GTIN")));
            }
            if (expectedLogDetails.containsKey("LOT")) {
                assertEquals(log.getLot(), lot);
            }
            if (expectedLogDetails.containsKey("SERIALNUM")) {
                assertThat(log.getSerialNum(), is(expectedLogDetails.get("SERIALNUM")));
            }
            if (expectedLogDetails.containsKey("RESPONDERGLN")) {
                assertThat(log.getResponderGln(), is(expectedLogDetails.get("RESPONDERGLN")));
            }
            if (expectedLogDetails.containsKey("ADDITIONALINFO")) {
                assertThat(log.getAdditionalInfo(), is(expectedLogDetails.get("ADDITIONALINFO")));
            }
            if (expectedLogDetails.containsKey("VERIFIED")) {
                assertThat(log.getVerified(), is(expectedLogDetails.get("VERIFIED")));
            }
            if (expectedLogDetails.containsKey("UUID")) {
                assertEquals(log.getUUID(), uuid);
            }
        }

    }
//
//    @Then("get blockchain explorer stream link")
//    public void getBlockchainExplorerStreamLink() throws Exception {
//        blockChainStreamLink = writeLogsPage.getBlockChainStreamId();
//    }

    @Then("^the number of epc count is '(.+)'$")
    public void theNumberOfEpcCountIsNumberOfEpcs(String count) throws Exception {
        assertEquals(count, blockChainExplorerPage.getKeyCount("Lot Hash: " + blockChainStreamLink));

    }

    @And("click on recorded LOT")
    public void clickOnRecordedLOT() throws Exception {
        blockChainExplorerPage.clickOnRecordedLot("Lot Hash: " + blockChainStreamLink);
    }

    @Then("the recorded event list contains event details")
    public void theRecordedEventListContainsEventDetails(Map<String, String> expectedDetails) throws Exception {
        if (blockChainExplorerPage.getEventDetails()[3].equals(expectedDetails.get("ActionEvent1"))) {
            assertEquals(blockChainExplorerPage.getEventDetails()[0], expectedDetails.get("SenderGln"));
            assertEquals(blockChainExplorerPage.getEventDetails()[1], expectedDetails.get("Disposition1"));
            assertEquals(blockChainExplorerPage.getEventDetails()[2], expectedDetails.get("EventTime"));
            assertEquals(blockChainExplorerPage.getEventDetails()[3], expectedDetails.get("ActionEvent1"));
        } else {
            assertEquals(blockChainExplorerPage.getEventDetails()[0], expectedDetails.get("SenderGln"));
            assertEquals(blockChainExplorerPage.getEventDetails()[1], expectedDetails.get("Disposition"));
            assertEquals(blockChainExplorerPage.getEventDetails()[2], expectedDetails.get("EventTime"));
            assertEquals(blockChainExplorerPage.getEventDetails()[3], expectedDetails.get("ActionEvent"));
        }
    }


    @Then("the second recorded events contains event details")
    public void theSecondRecordedEventsContainsEventDetails(Map<String, String> expectedDetails) throws Exception {
        if (blockChainExplorerPage.getSecondEventDetails()[3].equals(expectedDetails.get("ActionEvent1"))) {
            assertEquals(blockChainExplorerPage.getSecondEventDetails()[0], expectedDetails.get("SenderGln"));
            assertEquals(blockChainExplorerPage.getSecondEventDetails()[1], expectedDetails.get("Disposition1"));
            assertEquals(blockChainExplorerPage.getSecondEventDetails()[2], expectedDetails.get("EventTime"));
            assertEquals(blockChainExplorerPage.getSecondEventDetails()[3], expectedDetails.get("ActionEvent1"));
        } else {
            assertEquals(blockChainExplorerPage.getSecondEventDetails()[0], expectedDetails.get("SenderGln"));
            assertEquals(blockChainExplorerPage.getSecondEventDetails()[1], expectedDetails.get("Disposition"));
            assertEquals(blockChainExplorerPage.getSecondEventDetails()[2], expectedDetails.get("EventTime"));
            assertEquals(blockChainExplorerPage.getSecondEventDetails()[3], expectedDetails.get("ActionEvent"));
        }

    }

    @And("Validate the PI details on Blockchain Explorer screen")
    public void validateThePIDetailsOnBlockchainExplorerScreen(Map<String, String> expectedDetails) throws Exception {
        //   String expirationDate=expectedDetails.get("Expiration Date").substring(0,2)+"-"+expectedDetails.get("Expiration Date").substring(2,4)+"-"+expectedDetails.get("Expiration Date").substring(4);
        assertEquals(blockChainExplorerPage.getGTINValue(), "GTIN: " + expectedDetails.get("GTIN"));
        assertEquals(blockChainExplorerPage.getLotValue(), "Lot/Batch Number: " + lot);
        assertEquals(blockChainExplorerPage.getExpirationDateValue(), "Expiration Date: " + expectedDetails.get("Expiration Date"));
        assertEquals(blockChainExplorerPage.getPICountValue(), "PI Count: " + expectedDetails.get("PI Count"));
    }


    @Then("Validate the recorded events")
    public void validateTheRecordedEvents(Map<String, List<String>> expected) throws Exception {
        List<BlockChainExplorer> actuals = blockChainExplorerPage.getRecordedEvents();
        int i = 0;
        List<String> expectedPI = new ArrayList<>();
        expectedPI.addAll(expected.get("Action"));
        expectedPI.addAll(expected.get("Disposition"));
        Collections.sort(expectedPI);
        List<String> actualPI = new ArrayList<>();

        for (BlockChainExplorer actual : actuals) {
            if (expected.containsKey("Timestamp")) {
                assertEquals(expected.get("Timestamp").get(i), actual.getTimeStamp());
            }
            assertEquals(expected.get("GLN").get(i), actual.getGln());
            if (expected.containsKey("Written to Blockchain")) {
                assertEquals(expected.get("Written to Blockchain").get(i), actual.getwrittenToBlockchain());
            }
            actualPI.add(actual.getDisposition());
            actualPI.add(actual.getAction());
            if (expected.get("Action").size() > 1)
                i++;
        }
        Collections.sort(actualPI);
        assertEquals(expectedPI, actualPI);
    }


}
