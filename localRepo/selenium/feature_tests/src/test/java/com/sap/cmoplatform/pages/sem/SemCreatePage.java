package com.sap.cmoplatform.pages.sem;

import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.objects.Sem;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Properties;

public class SemCreatePage extends Page {

    private Properties semCreatePageIds = PropertyReader.loadProperties("sem/SemCreatePageIds.properties");
    private final Properties ui5PageIds = PropertyReader.loadProperties("ui5PageIds.properties");

    private String prefix = semCreatePageIds.getProperty("sem.createPage.pagePrefix");
    private String mahPrefix = semCreatePageIds.getProperty("semmah.createPage.pagePrefix");
    private By customerInputId = By.xpath("//*[contains(@id,'createsem--custID-inner')]");
    private By partnerInputId = By.xpath("//*[contains(@id,'createsemmah--custID-inner')]");

    private By senderGlnInputId = By.xpath("//*[contains(@id,'createsem--senderGln-inner')]");
    private By mahSenderGlnInputId = By.xpath("//*[contains(@id,'createsemmah--senderGln-inner')]");
    private String receiverGlnInputId = prefix + semCreatePageIds.getProperty("sem.createPage.receiverGlnInputId");
    private String mahReceiverGlnInputId = mahPrefix + semCreatePageIds.getProperty("sem.createPage.receiverGlnInputId");
    private By productInputId =  By.xpath("//*[contains(@id,'createsem--productInput-inner')]");
    private By mahProductInputId = By.xpath("//*[contains(@id,'createsemmah--productInput-inner')]");
    private String confirmationDialogId = semCreatePageIds.getProperty("sem.createPage.confirmationDialogId");
    private By messageTypeInputId =By.xpath("//*[contains(@id,'createsem--oSelectMessageType-label')]");
    private By mahMessageTypeInputId = By.xpath("//*[contains(@id,'createsemmah--oSelectMessageType-label')]");
    private By batchInputId = By.xpath("//*[contains(@id,'createsem--inputBatchId-inner')]");
    private By mahBatchInputId =By.xpath("//*[contains(@id,'createsemmah--inputBatchId-inner')]");
    private By fileInputId = By.xpath("//*[contains(@id,'createsemmah--fileUploader-fu')][@type='file']");
    private By mahFileInputId = By.xpath("//*[contains(@id,'createsemmah--fileUploader-fu')]");
    private By sendButtonId = By.xpath("//*[contains(@id,'semCreateSendButton-BDI-content')]");
    private String saveDraftButtonId = prefix + semCreatePageIds.getProperty("sem.createPage.saveDraftButtonId");
    private String mahSaveDraftButtonId = mahPrefix + semCreatePageIds.getProperty("sem.createPage.saveDraftButtonId");
    private String filepath = System.getProperty("user.dir") + "/src/test/resources/testfiles/uploadProduct/";
    private String tableClassName = ui5PageIds.getProperty("ui5.tableClassName");
    private String cancelButtonId = prefix + semCreatePageIds.getProperty("sem.createPage.cancelButtonId");
    private String mahCancelButtonId = mahPrefix + semCreatePageIds.getProperty("sem.createPage.cancelButtonId");
    private String backButtonId = prefix + semCreatePageIds.getProperty("sem.createPage.backButtonId");
    private String mahBackButtonId = mahPrefix + semCreatePageIds.getProperty("sem.createPage.backButtonId");
    private By refreshBtnId = By.xpath("//button[@title='Refresh SEM']/span/span");

    public void selectMessageTypeFromDropdown(String messageType) throws Exception {
        try{
            selectDropDownFilterInput(getElement(mahMessageTypeInputId), messageType);
        }catch(Exception e) {
            selectDropDownFilterInput(getElement(messageTypeInputId), messageType);
        }
    }

    public void attachFile(String filename) throws Exception {
//        try{
//            WebElement fileInput = getElement(mahFileInputId);
//            fileInput.sendKeys(filepath + filename);
//        }catch(Exception e) {
     //   Actions actions =new Actions(portalDriver.getConfig().getDriver());
            WebElement fileInput = getElement(fileInputId);
         //   actions.moveToElement(fileInput).build().perform();
            fileInput.sendKeys(filepath + filename);
       // }
    }

    public void enterBatchId(String batchId) throws Exception {
        try{
            getElement(mahBatchInputId).clear();
            inputText(mahBatchInputId, batchId);
        }catch(Exception e) {
            getElement(batchInputId).clear();
            inputText(batchInputId, batchId);
        }
    }

    public void selectCustomer(String customer) throws Exception {
        waitForPageToLoad();
        try {
            selectFragmentListFilterInput(getElement(partnerInputId), customer);

        }catch (Exception e){
            selectFragmentListFilterInput(getElement(customerInputId), customer);
        }
    }

    public void selectSenderGln(String senderGln) throws Exception {
        waitForPageToLoad();
        try {
            selectFragmentListFilterInput(getElement(mahSenderGlnInputId), senderGln);
        }catch(Exception e){
            selectFragmentListFilterInput(getElement(senderGlnInputId), senderGln);
        }
    }
    public void selectReceiverGln(String receiverGln) throws Exception {
        waitForPageToLoad();
        try {
            selectFragmentListFilterInput(mahReceiverGlnInputId, receiverGln);
        }catch(Exception e){
            selectFragmentListFilterInput(receiverGlnInputId, receiverGln);
        }
    }

    public void selectProduct(String product) throws Exception {
        waitForPageToLoad();
        try {
            selectFragmentListFilterInput(getElement(mahProductInputId), product);
        }catch(Exception e){
            selectFragmentListFilterInput(getElement(productInputId), product);
        }
    }

    public String getBatchNumber() throws Exception {
        return getElement(batchInputId).getAttribute("value");
    }

    public Sem getCurrentMessageInfo() throws Exception {
        return new Sem()
                .withCustomer(getCustomer())
                .withProduct(getProductDescription().replaceAll("\\s+", " "))
                .withIdentifierType(getPrimaryIdentifierType())
                .withIdentifier(getPrimaryIdentifierNumber())
                .withStatus("Sent");
    }

    public String getCustomer() throws Exception {
        return getElement(customerInputId).getAttribute("value");
    }

    public String getProductDescription() throws Exception {
        return getElement(productInputId).getAttribute("value");
    }

    public void clickSend() throws Exception {
      waitForPageToLoad();
            clickElement(sendButtonId);

    }

    public void clickCancelButton() throws Exception {
        try{
            clickElement(mahCancelButtonId);

        }catch(Exception e){
            clickElement(cancelButtonId);

        }
    }

    public void clickSaveDraftButton() throws Exception {
        try{
            clickElement(mahSaveDraftButtonId);

        }catch(Exception e){
            clickElement(saveDraftButtonId);

        }
    }

    public void clickBackButton() throws Exception {
        try{
            clickElement(mahBackButtonId);

        }catch(Exception e){
            clickElement(backButtonId);

        }
    }

    private String getPrimaryIdentifierType() throws Exception {
        List<WebElement> identifierTableData = getTableRows(By.cssSelector(tableClassName));
        for (WebElement identifierRow : identifierTableData) {
            List<WebElement> elements = identifierRow.findElements(By.tagName("td"));
            if (elements.size() > 6) {
                if (elements.get(5).getText().equals("Default")) {
                    return elements.get(1).getText();
                }
            }
        }
        return getElement(productInputId).getText();
    }

    private String getPrimaryIdentifierNumber() throws Exception {
        List<WebElement> identifierTableData = getTableRows(By.cssSelector(tableClassName));
        for (WebElement identifierRow : identifierTableData) {
            List<WebElement> elements = identifierRow.findElements(By.tagName("td"));
            if (elements.size() > 6) {
                if (elements.get(5).getText().equals("Default")) {
                    return elements.get(2).getText();
                }
            }
        }
        return getElement(productInputId).getText();
    }

    public Sem getSemDetailsFromFragment() throws Exception {
        WebElement div = getElement(confirmationDialogId);
        List<WebElement> items = div.findElements(By.xpath("./div/div[last()]/div/div"));
        return new Sem()
                .withCustomer(items.get(0).getText())
                .withSenderGln(items.get(1).getText())
                .withReceiverGln(items.get(2).getText())
                .withProduct(items.get(3).getText())
                .withBatchId(items.get(4).getText())
                .withMessageType(items.get(5).getText())
                .withFileName(items.get(6).getText());
    }

//    public boolean checkErrorBorder(String field) throws Exception {
//        switch (field) {
//            case "customer":
//                return hasErrorBorder(customerInputId, "sapMInputBaseInner sapMInputBaseStateInner sapMInputBaseErrorInner");
//            case "sender GLN":
//                return hasErrorBorder(senderGlnInputId, "sapMInputBaseInner sapMInputBaseStateInner sapMInputBaseErrorInner");
//            case "receiver GLN":
//                return hasErrorBorder(receiverGlnInputId, "sapMInputBaseInner sapMInputBaseStateInner sapMInputBaseErrorInner");
//            case "product":
//                return hasErrorBorder(productInputId, "sapMInputBaseInner sapMInputBaseStateInner sapMInputBaseErrorInner");
//            case "batch id":
//                return hasErrorBorder(batchInputId, "sapMInputBaseInner sapMInputBaseStateInner sapMInputBaseErrorInner");
//            case "message type":
//                return hasErrorBorder(messageTypeInputId, "sapMSltLabel sapMSltLabelError sapMSltLabelState");
//            case "file":
//                return hasErrorBorder(fileInputId, "");
//            default:
//                return false;
//        }
//    }

    public String getMessageType() throws Exception {
        return getElement(messageTypeInputId).getText();
    }

    public String getFile() throws Exception {
        return getElement(fileInputId).getAttribute("value");
    }

    public String getSenderGln() throws Exception {
        return getElement(senderGlnInputId).getText();
    }

    public String getReceiverGln() throws Exception {
        return getElement(receiverGlnInputId).getText();
    }

    public void clickRefreshBtn() throws Exception {
        waitForPageToLoad();
        Thread.sleep(5000);
        getElement(refreshBtnId).click();
    }
}
