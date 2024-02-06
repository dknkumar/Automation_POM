package com.sap.cmoplatform.teststeps.transactioninformation;

import com.sap.cmoplatform.objects.TransactionInformation;
import com.sap.cmoplatform.pages.transactioninformation.TransactionInformationPage;
import com.sap.cmoplatform.teststeps.common.CommonTestSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TransactionInformationTestSteps {

    private TransactionInformationPage transactionInformationPage = new TransactionInformationPage();
    private static final String DELIVERYNUMBER = "DeliveryNumber";
    private static final String GTIN = "GTIN";
    private static final String LOT = "LOT";
    String deliveryNumber;

    @Then("^the title of the transaction information table is '(.+)'$")
    public void theTitleOfTheTransactionInformationTableIsTableHeader(String ExpHeader) throws Exception {
        assertEquals(ExpHeader, transactionInformationPage.getTableHeader().split(" \\(")[0]);

    }

    @And("^set filter value delivery number '(.+)'$")
    public void setFilterValueDeliveryNumberDeliveryNumber(String deliveryNumber) throws Exception {
        transactionInformationPage.enterDeliveryNumber(deliveryNumber);

    }

    @Then("the TI table contains the filtered values in the response")
    public void theTITableContainsTheFilteredValuesInTheResponse(Map<String, String> expectedLogDetails) throws Exception {
        List<TransactionInformation> actualRes = transactionInformationPage.getTableResponse();
        for (TransactionInformation res : actualRes) {
            if (expectedLogDetails.containsKey(DELIVERYNUMBER)) {
                System.out.println(res.getDeliveryNumber());
                assertThat(res.getDeliveryNumber(), is(expectedLogDetails.get(DELIVERYNUMBER)));

            }
        }
    }

    @And("^clear filter (deliveryNumber|deliveryDate|senderGln|receiverGln|messageId|messageDate|purchaseOrder|gtin|lotNumber|serialNumber|ndc|downloaded) value in transaction information$")
    public void clearFilterValue(String filter) throws Exception {
        if (filter.equals("deliveryNumber")) {
            transactionInformationPage.clearDeliveryNumberValue();
        }
        if (filter.equals("deliveryDate")) {
            transactionInformationPage.clearDeliveryDateValue();
        }
        if (filter.equals("senderGln")) {
            transactionInformationPage.clearSenderGlnValue();
        }
        if (filter.equals("receiverGln")) {
            transactionInformationPage.clearReceiverGlnValue();
        }
        if (filter.equals("messageId")) {
            transactionInformationPage.clearMessageIdValue();
        }
        if (filter.equals("messageDate")) {
            transactionInformationPage.clearMessageDateValue();
        }
        if (filter.equals("purchaseOrder")) {
            transactionInformationPage.clearPurchaseOrderValue();
        }
        if (filter.equals("gtin")) {
            transactionInformationPage.clearGtinValue();
        }
        if (filter.equals("lotNumber")) {
            transactionInformationPage.clearLotNumberValue();
        }
        if (filter.equals("serialNumber")) {
            transactionInformationPage.clearSerialNumberValue();
        }
        if (filter.equals("ndc")) {
            transactionInformationPage.clearNdcValue();
        }
        if (filter.equals("downloaded")) {
            transactionInformationPage.clearDownloadedValue();
        }

    }

    @And("^set filter value senderGln '(.+)'$")
    public void setFilterValueSenderGlnSenderGln(String senderGln) throws Exception {
        transactionInformationPage.enterSenderGln(senderGln);
    }

    @And("^set filter value receiverGln '(.+)'")
    public void setFilterValueReceiverGlnReceiverGln(String receiverGln) throws Exception {
        transactionInformationPage.enterReceiverGln(receiverGln);

    }

    @And("^set filter value messageId '(.+)'$")
    public void PurchaseOrdersetFilterValueMessageIdMessageId(String messageId) throws Exception {
        transactionInformationPage.enterMessageId(messageId);
    }

    @And("^set filter value PurchaseOrder '(.+)'$")
    public void setFilterValuePurchaseOrderPurchaseOrder(String purchaseOrder) throws Exception {
        transactionInformationPage.entertPurchaseOrder(purchaseOrder);
    }

    @And("^set filter value lotNumber '(.+)'$")
    public void setFilterValueLotNumberLotNumber(String lotNumber) throws Exception {
        transactionInformationPage.enterLotNumber(lotNumber);
    }

    @And("^set filter value ndc '(.+)'$")
    public void setFilterValueNdcNDC(String ndc) throws Exception {
        transactionInformationPage.enterNdc(ndc);
    }

    @Then("the adapt filter pop is displayed")
    public void theAdaptFilterPopIsDisplayed() throws Exception {
        assertTrue(transactionInformationPage.getAdaptFilterHeaderElement().isDisplayed());
    }

    @Then("verify unselected filter components are not available")
    public void verifyUnselectedFilterComponentsAreNotAvailable(List<String> filterComponent) throws Exception {
        for (String component : filterComponent) {
            try {
                if (component.equals("DeliveryNumber")) {
                    assertFalse(transactionInformationPage.getDeliveryNumber().isDisplayed());
                }
                if (component.equals("DeliveryDate")) {
                    assertFalse(transactionInformationPage.getDeliveryDate().isDisplayed());
                }
                if (component.equals("SenderGln")) {
                    assertFalse(transactionInformationPage.getSenderGlnElement().isDisplayed());
                }
                if (component.equals("ReceiverGln")) {
                    assertFalse(transactionInformationPage.getReceiverGlnElement().isDisplayed());
                }
                if (component.equals("MessageId")) {
                    assertFalse(transactionInformationPage.getMessageIdElement().isDisplayed());
                }
                if (component.equals("PurchaseOrder")) {
                    assertFalse(transactionInformationPage.getPurchaseOrderElement().isDisplayed());
                }
                if (component.equals("LotNumber")) {
                    assertFalse(transactionInformationPage.getLotNumberElement().isDisplayed());
                }
                if (component.equals("NDC")) {
                    assertFalse(transactionInformationPage.getNdcElement().isDisplayed());
                }
                if (component.equals("Downloaded")) {
                    assertFalse(transactionInformationPage.getDownloadedElement().isDisplayed());
                }
                if (component.equals("MessageDate")) {
                    assertFalse(transactionInformationPage.getMessageDateElement().isDisplayed());
                }
                if (component.equals("SerialNumber")) {
                    assertFalse(transactionInformationPage.getSerialNumberElement().isDisplayed());
                }
                if (component.equals("NDC")) {
                    assertFalse(transactionInformationPage.getNdcElement().isDisplayed());
                }
            } catch (Exception e) {
                System.out.println("Filter element not present");
            }

        }
    }

    @Then("verify selected filter components are available in TI app")
    public void verifySelectedFilterComponentsAreAvailableInTIApp(List<String> filterComponent) throws Exception {
        for (String component : filterComponent) {
            if (component.equals("NDC")) {
                assertTrue(transactionInformationPage.getNdcElement().isDisplayed());
            }
            if (component.equals("Downloaded")) {
                assertTrue(transactionInformationPage.getDownloadedElement().isDisplayed());
            }
        }
    }

    @And("^the download TI TS (pdf|xml|excel) report button is clicked$")
    public void theDownloadTITSPdfReportButtonIsClicked(String file) throws Exception {
        if(file.equals("pdf")) {
            transactionInformationPage.clickDownloadPdfButton();
        }
        if(file.equals("xml")){
            transactionInformationPage.clickDownloadXmlReportButton();
        }

        if(file.equals("excel")){
            transactionInformationPage.clickDownloadSpreadsheetButton();
        }    }

    @And("get the first TI Message Delivery Number")
    public void getTheFirstTIMessageDeliveryNumber() throws Exception {
        List<TransactionInformation> actualRes = transactionInformationPage.getTableResponse();
        System.out.println(actualRes.get(0).getDeliveryNumber());
        deliveryNumber=actualRes.get(0).getDeliveryNumber();
//        for (TransactionInformation res : actualRes) {
//            res.getDeliveryNumber()
//
//        }

    }

    @Then("^verify TI (pdf|xml) report '(.+)' is downloaded$")
    public void verifyTIReportPdfFileNameIsDownloaded(String file,String fileName) throws Exception {
        if(file.equals("pdf")) {
            CommonTestSteps.verifyFileIsDownloaded(fileName + "(" + deliveryNumber + ")" + ".pdf");
        }
        if(file.equals("xml")){
            CommonTestSteps.verifyFileIsDownloaded(fileName+"("+deliveryNumber+")"+".xml");
        }
    }

    @Then("^the download TI TS (pdf|xml|excel) report button is displayed")
    public void theDownloadTITSPdfReportButtonIsDisplayed(String file) throws Exception {
        if(file.equals("pdf")) {
           assertTrue(transactionInformationPage.getDownloadPdfReportButton().isDisplayed());
        }
        if(file.equals("xml")){
            assertTrue(transactionInformationPage.getDownloadXmlReportButton().isDisplayed());
        }

        if(file.equals("excel")){
           assertTrue(transactionInformationPage.getDownloadSpreadsheetElement().isDisplayed());
        }
    }

    @Then("the TI table contains below columns")
    public void theTITableContainsBelowColumns(List<String> expectedColumns) throws Exception {
        for(int i=0;i<expectedColumns.size();i++){
            assertTrue(transactionInformationPage.getColumnHeader(expectedColumns.get(i)).isDisplayed());
        }
    }

    @Then("the TI overview page contains below filters")
    public void theTIOverviewPageContainsBelowFilters(List<String> expectedFilter) throws Exception {
        for(int i=0;i<expectedFilter.size();i++){
            assertTrue(transactionInformationPage.getFilterHeader(expectedFilter.get(i)).isDisplayed());
        }
    }
}