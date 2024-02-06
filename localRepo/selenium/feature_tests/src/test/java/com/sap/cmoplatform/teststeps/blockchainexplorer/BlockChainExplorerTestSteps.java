package com.sap.cmoplatform.teststeps.blockchainexplorer;

import com.sap.cmoplatform.pages.blockchainexplorer.BlockChainExplorerPage;
import com.sap.cmoplatform.objects.BlockChainExplorer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;


import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BlockChainExplorerTestSteps {
    private BlockChainExplorerPage blockChainExplorerPage = new BlockChainExplorerPage();

    @Then("^the available network header '(.+)' is displayed$")
    public void theHeaderHeaderIsDisplayed(String header) throws Exception {
        assertEquals(blockChainExplorerPage.getAvailableNetworkHeader(), header);
    }

    @And("^click on available network '(.+)'$")
    public void clickOnAvailableNetworkUshcVerification(String network) throws Exception {
        blockChainExplorerPage.clickOnNetwork(network);
    }

    @And("^click on recorded LOT '(.+)'$")
    public void clickOnRecordedLOTRecordedLOT(String lot) throws Exception {
        blockChainExplorerPage.clickOnRecordedLot(lot);
    }

    @Then("^the lot '(.+)' is displayed$")
    public void theLotRecordedLotIsDisplayed(String lot) throws Exception {
        assertTrue(blockChainExplorerPage.isRecordedLotDisplayed(lot));
    }

//    @Then("^the number of epc count is '(.+)'$")
//    public void theNumberOfEpcCountIsNumberOfEpcs(String count) throws  Exception{
//        assertEquals(count,blockChainExplorerPage.getKeyCount());
//    }

    @And("click on recorded Epcs")
    public void clickOnRecordedEpcs() throws Exception {
        blockChainExplorerPage.clickOnRecordedEpcs();
    }

    @Then("the recorded events contains event details")
    public void theRecordedEventsContainsEventDetails(Map<String, String> expectedDetails) throws Exception {
        assertEquals(blockChainExplorerPage.getEventDetails()[0], expectedDetails.get("SenderGln"));
        assertEquals(blockChainExplorerPage.getEventDetails()[1], expectedDetails.get("Disposition"));
        assertEquals(blockChainExplorerPage.getEventDetails()[2], expectedDetails.get("EventTime"));
        assertEquals(blockChainExplorerPage.getEventDetails()[3], expectedDetails.get("ActionEvent"));
    }


    @Then("^the number of epc count '(.+)' of is '(.+)'$")
    public void theNumberOfEpcCountRecordedLotOfIsNumberOfEpcs(String lot, String count) throws Exception {
        assertEquals(count, blockChainExplorerPage.getKeyCount(lot));
    }

    @Then("^the number of available network is '(.+)'$")
    public void theNumberOfAvailableNetworkIsAvailableNetworkCount(String count) throws Exception {
        assertEquals(blockChainExplorerPage.getAvailableNetworkCount(), count);
    }

    @Then("^the recorded lots header '(.+)' is displayed$")
    public void theRecordedLotsHeaderRecordedLotsHeaderIsDisplayed(String header) throws Exception {
        assertEquals(blockChainExplorerPage.getRecordedLotsHeader(), header);
    }

    @Then("^the recorded epc header '(.+)' is displayed$")
    public void theRecordedEpcHeaderRecordedEpcsHeaderIsDisplayed(String header) throws Exception {
        assertTrue(blockChainExplorerPage.recordedEpcsHeader(header).isDisplayed());
    }

    @Then("^the recorded events header '(.+)' is displayed$")
    public void theRecordedEventsHeaderRecordedEpcsHeaderIsDisplayed(String header) throws Exception {
        assertTrue(blockChainExplorerPage.recordedEventsHeader(header).isDisplayed());
    }

    @And("Validate the GS1 elements on Blockchain Explorer screen")
    public void validateTheGSElementsOnBlockchainExplorer(Map<String, String> expectedDetails) throws Exception {
        assertEquals(blockChainExplorerPage.getGTINValue(), expectedDetails.get("GTIN"));
        assertEquals(blockChainExplorerPage.getLotValue(), expectedDetails.get("Lot"));
        assertEquals(blockChainExplorerPage.getExpirationDateValue(), expectedDetails.get("Expiration Date"));
        assertEquals(blockChainExplorerPage.getPICountValue(), expectedDetails.get("PI Count"));

    }

    @And("^Search the '(.+)'$")
    public void search_the_(String serialnumber) throws Throwable {
        blockChainExplorerPage.searchSerialNo(serialnumber);
    }

    @Then("Validate the recorded event")
    public void validateTheRecordedEvent(Map<String, List<String>> expected) throws Exception {
        List<BlockChainExplorer> actuals = blockChainExplorerPage.getRecordedEvents();
        int i = 0;
        for (BlockChainExplorer actual : actuals) {
            if(expected.containsKey("Timestamp")) {
                assertEquals(expected.get("Timestamp").get(i), actual.getTimeStamp());
            }
            assertEquals(expected.get("Action").get(i), actual.getAction());
            assertEquals(expected.get("Disposition").get(i), actual.getDisposition());
            assertEquals(expected.get("GLN").get(i), actual.getGln());
            if(expected.containsKey("Written to Blockchain")) {
                assertEquals(expected.get("Written to Blockchain").get(i), actual.getwrittenToBlockchain());
            }
            if (expected.get("Action").size() > 1)
                i++;
        }
    }



    @Then("verify default recorded event table from write log page navigation")
    public void verifyDefaultRecordedEventTableFromWriteLogPageNavigation() throws Exception {
        assertTrue(blockChainExplorerPage.getNodataTextId().isDisplayed());
        assertEquals(blockChainExplorerPage.getNodataTextId().getText(), "No Data");
    }

    @Then("^verify default serial number field '(.+)' on navigation from write log page$")
    public void verifyDefaultSerialNumberFieldOnNavigationFromWriteLogPage(String defaultText) throws Exception {
        assertTrue(blockChainExplorerPage.getSerialNoId().getAttribute("value").isEmpty());
        assertEquals(blockChainExplorerPage.getSerialNoId().getAttribute("placeholder"), defaultText);
    }

    @Then("^PI count contains '(.+)' on Blockchain Explorer screen$")
    public void piCountContainsPICountOnBlockchainExplorerScreen(String PiCount) throws Exception {
        assertEquals(blockChainExplorerPage.getPICountValue(), PiCount);
    }

    @Then("^verify default serial number field '(.+)' on navigation from verification log page$")
    public void verifyDefaultSerialNumberFieldSerialNumberOnNavigationFromVerificationLogPage(String serialNumber) throws Exception {
        assertFalse(blockChainExplorerPage.getSerialNoId().getAttribute("value").isEmpty());
        assertEquals(blockChainExplorerPage.getSerialNoId().getAttribute("value"),serialNumber);
    }


    @Then("verify default recorded event table from verification log page navigation")
    public void verifyDefaultRecordedEventTableFromVerificationLogPageNavigation() throws Exception {
        assertFalse(blockChainExplorerPage.getNodataTextId().isDisplayed());
    }

    @Then("No Data Found Error message is displayed")
    public void noDataFoundErrorMessageIsDisplayed() throws Exception {
       assertTrue(blockChainExplorerPage.getNoDataError().isDisplayed());
    }
}
