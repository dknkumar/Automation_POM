package com.sap.cmoplatform.pages.transactioninformation;

import com.sap.cmoplatform.objects.TransactionInformation;
import com.sap.cmoplatform.objects.VerificationLog;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TransactionInformationPage extends Page {
    private Properties transactionInformationPageIds = PropertyReader.loadProperties("transactionInformation/TransactionInformationPageIds.properties");
    private String transactionInformationPagePrefix = transactionInformationPageIds.getProperty("transactioninformation.home.pagePrefix");
    private String transactionInformationTableHeader = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.tableHeader");
    private String idInputDeliveryNumber = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idInputDeliveryNumber");
    private String idDRSDelivery = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idDRSDelivery");
    private String idCBReceiverGln = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idCBReceiverGln");
    private String idCBSenderGln = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idCBSenderGln");
    private String idInputMessageId = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idInputMessageId");
    private String idInputPurchaseOrder = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idInputPurchaseOrder");
    private String idDRSMessage = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idDRSMessage");
    private String idInputGtin = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idInputGtin");
    private String idInputLotNumber = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idInputLotNumber");
    private String idInputSerialNumber = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idInputSerialNumber");
    private String idInputNdc = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idInputNdc");
    private String idSelectDownloaded = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idSelectDownloaded");
    private String idDispenserPortalTable = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.idDispenserPortalTable");
private By adaptFilterHeader=By.xpath("//*[text()='Adapt Filters']");
    private By downloadPdfReportButton=By.xpath("//button[@title='Message Summary PDF']/span/span");
    private By downloadXmlReportButton=By.xpath("//button[@title='Original message XML']/span/span");
    private String downloadSpreadsheet = transactionInformationPagePrefix + transactionInformationPageIds.getProperty("transactioninformation.home.downloadSpreadsheet");
    private String showLogTitle = "" + transactionInformationPageIds.getProperty("transactioninformation.home.showLogTitle");


    public String getTableHeader() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        return getElement(transactionInformationTableHeader).getText();
    }

    public void enterDeliveryNumber(String deliveryNumber) throws Exception {
        waitForPageToLoad();
        inputText(idInputDeliveryNumber, deliveryNumber);
    }

    public void enterSenderGln(String senderGln) throws Exception {
        waitForPageToLoad();
        inputText(idCBSenderGln, senderGln);
    }

    public void enterReceiverGln(String receiverGln) throws Exception {
        waitForPageToLoad();
        inputText(idCBReceiverGln, receiverGln);
    }

    public void enterMessageId(String messageId) throws Exception {
        waitForPageToLoad();
        inputText(idInputMessageId, messageId);
    }

    public void entertPurchaseOrder(String purchaseOrder) throws Exception {
        waitForPageToLoad();
        inputText(idInputPurchaseOrder, purchaseOrder);
    }

    public void enterGtin(String gtin) throws Exception {
        waitForPageToLoad();
        inputText(idInputGtin, gtin);
    }

    public void enterLotNumber(String lotNumber) throws Exception {
        waitForPageToLoad();
        inputText(idInputLotNumber, lotNumber);
    }

    public void enterSerialNumber(String serialNumber) throws Exception {
        waitForPageToLoad();
        inputText(idInputSerialNumber, serialNumber);
    }

    public void enterNdc(String ndc) throws Exception {
        waitForPageToLoad();
        inputText(idInputNdc, ndc);
    }

    public List<TransactionInformation> getTableResponse() throws Exception {

        waitForPageToLoad();
        waitForTableToLoad();
        Thread.sleep(10000);
        List<TransactionInformation> logs = new ArrayList<>();
        List<WebElement> tableData = getTableRows(By.id(idDispenserPortalTable));
        int count = tableData.size();
        System.out.println(count);
        for (int i = 0; i < count; i = i + 2) {
            WebElement row = getTableRows(By.id(idDispenserPortalTable)).get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));


            TransactionInformation log = new TransactionInformation()
                    .withDeliveryDNumber(cells.get(1).getText())
                    .withDeliveryDate(cells.get(2).getText())
                    .withSenderGln(cells.get(3).getText())
                    .withReceiverGln(cells.get(4).getText())
                    .withMessageId(cells.get(5).getText())
                    .withMessageDate(cells.get(6).getText())
                    .withPurchaseOrder(cells.get(7).getText())
                    .withGtin(cells.get(8).getText())
                    .withLotNumber(cells.get(9).getText());

            logs.add(log);



        }
        return logs;
    }

    public void clearDeliveryNumberValue() throws Exception {
        waitForPageToLoad();
        getElement(idInputDeliveryNumber).clear();
    }
    public void clearDeliveryDateValue() throws Exception {
        waitForPageToLoad();
        getElement(idDRSDelivery).clear();
    }
    public void clearSenderGlnValue() throws Exception {
        waitForPageToLoad();
        getElement(idCBSenderGln).clear();
    }
    public void clearReceiverGlnValue() throws Exception {
        waitForPageToLoad();
        getElement(idCBReceiverGln).clear();
    }
    public void clearMessageIdValue() throws Exception {
        waitForPageToLoad();
        getElement(idInputMessageId).clear();
    }
    public void clearMessageDateValue() throws Exception {
        waitForPageToLoad();
        getElement(idDRSMessage).clear();
    }
    public void clearPurchaseOrderValue() throws Exception {
        waitForPageToLoad();
        getElement(idInputPurchaseOrder).clear();
    }
    public void clearGtinValue() throws Exception {
        waitForPageToLoad();
        getElement(idInputGtin).clear();
    }
    public void clearLotNumberValue() throws Exception {
        waitForPageToLoad();
        getElement(idInputLotNumber).clear();
    }
    public void clearSerialNumberValue() throws Exception {
        waitForPageToLoad();
        getElement(idInputSerialNumber).clear();
    }
    public void clearNdcValue() throws Exception {
        waitForPageToLoad();
        getElement(idInputNdc).clear();
    }
    public void clearDownloadedValue() throws Exception {
        waitForPageToLoad();
        getElement(idSelectDownloaded).clear();
    }
    public WebElement getAdaptFilterHeaderElement() throws Exception {
        waitForPageToLoad();
        return getElement(adaptFilterHeader);
    }
    public WebElement getDeliveryNumber() throws Exception {
        waitForPageToLoad();
        return getElement(idInputDeliveryNumber);
    }
    public WebElement getDeliveryDate() throws Exception {
        waitForPageToLoad();
        return getElement(idDRSDelivery);
    }
    public WebElement getSerialNumberElement() throws Exception {
        waitForPageToLoad();
        return getElement(idInputSerialNumber);
    }
    public WebElement getLotNumberElement() throws Exception {
        waitForPageToLoad();
        return getElement(idInputLotNumber);
    }
    public WebElement getPurchaseOrderElement() throws Exception {
        waitForPageToLoad();
        return getElement(idInputPurchaseOrder);
    }
    public WebElement getMessageIdElement() throws Exception {
        waitForPageToLoad();
        return getElement(idInputMessageId);
    }
    public WebElement getReceiverGlnElement() throws Exception {
        waitForPageToLoad();
        return getElement(idCBReceiverGln);
    }
    public WebElement getSenderGlnElement() throws Exception {
        waitForPageToLoad();
        return getElement(idCBSenderGln);
    }
    public WebElement getMessageDateElement() throws Exception {
        waitForPageToLoad();
        return getElement(idDRSMessage);
    }
    public WebElement getNdcElement() throws Exception {
        waitForPageToLoad();
        return getElement(idInputNdc);
    }
    public WebElement getDownloadedElement() throws Exception {
        waitForPageToLoad();
        return getElement(idSelectDownloaded);
    }
    public void clickDownloadPdfButton() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
         getElement(downloadPdfReportButton).click();
    }
    public void clickDownloadXmlReportButton() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
         getElement(downloadXmlReportButton).click();
    }
    public void clickDownloadSpreadsheetButton() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(downloadSpreadsheet).click();
    }
    public WebElement getDownloadSpreadsheetElement() throws Exception {
        waitForPageToLoad();
        return getElement(downloadSpreadsheet);
    }
    public WebElement getDownloadXmlReportButton() throws Exception {
        waitForPageToLoad();
        return getElement(downloadXmlReportButton);
    }
    public WebElement getDownloadPdfReportButton() throws Exception {
        waitForPageToLoad();
        return getElement(downloadPdfReportButton);
    }
    public WebElement getColumnHeader(String columnName) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//*[contains(@class,'sapMColumnHeaderContent')][text()='"+columnName+"']"));
    }
    public WebElement getFilterHeader(String filterName) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//*[contains(@id,'filterbar')][text()='"+filterName+"']"));
    }

}
