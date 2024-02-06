package com.sap.cmoplatform.pages.sem;

import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.objects.Sem;
import com.sap.cmoplatform.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;


public class SemDetailsPage extends Page {

    private static final Logger LOGGER = LogManager.getLogger(new Object(){}.getClass().getEnclosingClass().getName());
    private String filepath = System.getProperty("user.dir") + "/src/test/resources/testfiles/";

    private Properties semDetailsPageIds = PropertyReader.loadProperties("sem/SemDetailsPageIds.properties");
    private final Properties ui5PageIds = PropertyReader.loadProperties("Ui5PageIds.properties");
    private String tableClassName = ui5PageIds.getProperty("ui5.tableClassName");

    private String prefix = semDetailsPageIds.getProperty("sem.detailsPage.pagePrefix");
    private String customerInputId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.customerInputId");
    private String senderGlnInputId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.senderGlnInputId");
    private String receiverGlnInputId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.receiverGlnInputId");
    private String productInputId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.productInputId");
    private String confirmationDialogId = semDetailsPageIds.getProperty("sem.detailsPage.confirmationDialogId");
    private String messageTypeInputId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.messageTypeInputId");
    private String batchInputId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.batchInputId");
    private String fileInputId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.fileInputId");
    private String fileInputInnerId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.fileInputInnerId");
    private String sendButtonId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.sendButtonId");
    private String saveDraftButtonId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.saveDraftButtonId");
    private String downloadButtonId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.downloadButtonId");
    private String editButtonId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.editButtonId");
    private String statusId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.statusId");
    private String defaultMessageType = semDetailsPageIds.getProperty("sem.detailsPage.defaultMessageType");
    private String messageTypeDivId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.messageTypeDivId");
    private String backButtonId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.backButtonId");
    private String cancelButtonId = prefix + semDetailsPageIds.getProperty("sem.detailsPage.cancelButtonId");

    public void clickEditButton() throws Exception {
        waitForPageToLoad();
        clickElement(editButtonId);
    }

    public void selectCustomer(String customer) throws Exception {
        selectFragmentListFilterInput(customerInputId, customer);
        waitForPageToLoad();
        clickButton("Yes");
    }

    public void selectProduct(String product) throws Exception {
        selectFragmentListFilterInput(productInputId, product);
    }

    public void enterBatchNumber(String batchNum) throws Exception {
        getElement(batchInputId).clear();
        waitForPageToLoad();
        inputText(batchInputId, batchNum);
    }

    public void selectMessageTypeFromDropdown(String messageType) throws Exception {
        selectDropDownFilterInput(messageTypeInputId, messageType);
    }

    public void attachFile(String filename) throws Exception {
        WebElement fileInput = getElement(fileInputId);
        fileInput.sendKeys(filepath + filename);
    }

    public void clickSend() throws Exception {
        clickElement(sendButtonId);
    }

    public void clickDownload() throws Exception {
        clickElement(downloadButtonId);
    }

    public void clickSaveDraftButton() throws Exception {
        clickElement(saveDraftButtonId);
    }

    public Sem getCurrentMessageInfo() throws Exception {
        waitForPageToLoad();
        return new Sem()
                .withCustomer(getCustomer())
                .withProduct(getProductDescription().replaceAll("/s+", " "))
                .withIdentifierType(getPrimaryIdentifierType())
                .withIdentifier(getPrimaryIdentifierNumber())
                .withStatus(getStatus());
    }

    private String getPrimaryIdentifierType() throws Exception {
        return getTextFromTableRow(1, "Failed to find default identifier");
    }

    private String getPrimaryIdentifierNumber() throws Exception {
        return getTextFromTableRow(2, "Failed to find default identifier number");
    }

    private String getTextFromTableRow(int tdIndex, String exception) throws Exception {
        List<WebElement> tableRows = getTableRows(By.cssSelector(tableClassName));
        for (WebElement tableRow : tableRows) {
            List<WebElement> tds = tableRow.findElements(By.tagName("td"));
            if (!(tds.size() < 6) && tds.get(5).getText().equals("Default")) {
                return tds.get(tdIndex).getText();
            }
        }
        throw new Exception(exception);
    }

    private String getCustomer() throws Exception {
        return getElement(customerInputId).getAttribute("value");
    }

    public String getProductDescription() throws Exception {
        return getElement(productInputId).getAttribute("value");
    }

    public String getStatus() throws Exception {
        return getElement(statusId).getText();
    }

    public String getBatchNumber() throws Exception {
        return getElement(batchInputId).getAttribute("value");
    }

    public String getMessageType() throws Exception {
        return getElement(messageTypeInputId).getText();
    }

    public String getFilename() throws Exception {
        return getElement(fileInputInnerId).getAttribute("value");
    }

    public String getDefaultMessageType() {
        return defaultMessageType;
    }

    public void selectReceiverGln(String receiverGln) throws Exception {
        selectFragmentListFilterInput(receiverGlnInputId, receiverGln);
    }

    public void selectSenderGln(String gln) throws Exception {
        selectFragmentListFilterInput(senderGlnInputId, gln);
    }

    public boolean isCustomerUneditable() throws Exception {
        waitForPageToLoad();
        return Boolean.parseBoolean(getElement(customerInputId).getAttribute("aria-readonly"));
    }

    public boolean isReceiverGlnUneditable() throws Exception {
        return Boolean.parseBoolean(getElement(receiverGlnInputId).getAttribute("aria-readonly"));
    }

    public boolean isProductUneditable() throws Exception {
        return Boolean.parseBoolean(getElement(productInputId).getAttribute("aria-readonly"));
    }

    public boolean isBatchNumberUneditable() throws Exception {
        return Boolean.parseBoolean(getElement(batchInputId).getAttribute("aria-readonly"));
    }

    public boolean isMessageTypeUneditable() throws Exception {
        return Boolean.parseBoolean(getElement(messageTypeDivId).getAttribute("aria-disabled"));
    }

    public boolean isFileUneditable() throws Exception {
        return Boolean.parseBoolean(getElement(fileInputInnerId).getAttribute("aria-disabled"));
    }

    public boolean checkErrorBorder(String field) throws Exception {
        switch (field) {
            case "receiver GLN":
                return hasErrorBorder(receiverGlnInputId, "sapMInputBaseInner sapMInputBaseStateInner sapMInputBaseErrorInner");
            case "product":
                return hasErrorBorder(productInputId, "sapMInputBaseInner sapMInputBaseStateInner sapMInputBaseErrorInner");
            case "batch id":
                return hasErrorBorder(batchInputId, "sapMInputBaseInner sapMInputBaseStateInner sapMInputBaseErrorInner");
            case "message type":
                return hasErrorBorder(messageTypeInputId, "sapMSltLabel sapMSltLabelError sapMSltLabelState");
            case "file":
                return hasErrorBorder(fileInputId, "");
            default:
                return false;
        }
    }

    public void clickCancelButton() throws Exception {
        clickElement(cancelButtonId);
    }

    public void clickBackButton() throws Exception {
        clickElement(backButtonId);
    }
}
