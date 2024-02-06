package com.sap.cmoplatform.pages.alertconfiguration;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.CommonComponentPage;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AlertConfigurationPage extends Page {
    private final Properties alertConfigurationPageIds = PropertyReader.loadProperties("alertconfiguration/AlertConfiguration.properties");
    private final String alertConfigurationprefix = alertConfigurationPageIds.getProperty("alertconfiguration.home.pagePrefix");
    public final String masterPageTitle = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.masterPageTitle");
    private final String detailPageTitle = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.detailPageTitle");
    private final String createRecipientNameId = alertConfigurationprefix+alertConfigurationPageIds.getProperty("alertconfiguration.home.createRecipientNameId");
    private final String createRecipientEmailId = alertConfigurationprefix+alertConfigurationPageIds.getProperty("alertconfiguration.home.createRecipientEmailId");
    private final String createRecipientDialogTitle = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.createRecipientDialogTitle");
    private final By notificationTab = By.xpath("//*[text()='Notifications']");
    private final By conditionsTab = By.xpath("//*[text()='Conditions']");
    private final By addButton = By.xpath("(//button[@title='Add']/span/span)[2]");
    private final By deleteButton = By.xpath("//button[@title='Delete']/span/span");
    private final String addRuleButtonId = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.addRuleButtonId");
    private final String createAlertRuleDialogTitle = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.createAlertRuleDialogTitle");
    private final String ruleNameId = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.ruleNameId");
    private final String ruleDescriptionId = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.ruleDescriptionId");
    private final String searchFieldID = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.searchFieldId");
    private final String searchFieldIconID = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.searchFieldIconId");
    private final By deletePopUpMessage = By.xpath("//*[@class='sapMDialogScrollCont']/span");
    private final By inactiveAlertStatus = By.xpath("//li/..//div[@class='sapMObjStatus sapMObjStatusError']/span[1]");
    private final By statusSwitchButton = By.xpath("//*[@class='sapMSwtText sapMSwtTextOff']");
    private final By activeAlertStatus = By.xpath("//li/..//div[@class='sapMObjStatus sapMObjStatusSuccess']/span[1]");
    private final String recipientTable = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.recipientTable");
    private final By recipientTableHeader = By.xpath("//*[contains(@id,'oRecipientTable')]/div/div/span");
    private final By recipientRadioButton = By.xpath("//td/..//input/parent::div");
    private final String ruleTitle = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.ruleTitle");
    private final By conditionTableHeader = By.xpath("//*[contains(@id,'oTableid')]/..//h2/span");
    private final By tableColumnHeader = By.xpath("//th/div/span");
    private final String conditionsTable = alertConfigurationprefix + alertConfigurationPageIds.getProperty("alertconfiguration.home.conditionTable");
    private final By fieldDropDownBtn = By.xpath("(//*[@type='text']/../div/span)[1]");
    public final By fieldDropdownID = By.xpath("(//input[contains(@id,'USSCAlertservices-show-component---detail--oTableid-0-inner')])[1]");
    public final By operatorDropDownID = By.xpath("(//input[contains(@id,'USSCAlertservices-show-component---detail--oTableid-0-inner')])[2]");
    private final By operatorDropDownBtn = By.xpath("(//*[@type='text']/../div/span)[2]");
    //private By valueDropDownBtn = By.xpath("(//*[@type='text']/../div/span)[3]");
    private final By valueDropDownBtn = By.xpath("(//*[@class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon'])[3]");
    private final By fieldValueList = By.xpath("(//*[contains(@id,'popup-list-listUl')])[1]/li/div/div/div");
    private final By operatorValueList = By.xpath("(//*[contains(@id,'popup-list-listUl')])[2]/li/div/div/div");
    private final By valueList = By.xpath("(//*[contains(@id,'popup-list-listUl')])[3]/li/div/div/div");
    private final By deleteAlertRuleYesButton = By.xpath("//bdi[text()='Yes']");
    private final By conditionValueCheckboxInput = By.xpath("//input[@type='CheckBox']/parent::div");
    private final By alertFlagText = By.xpath("//*[text()='Alert in App:']");
    private final By statusFlagText = By.xpath("//*[text()='Status:']");
    private final By flagStatusInDetailPage = By.xpath("//*[contains(@id,'objectHeader')]/..//span[@class='sapMObjStatusText']");
    private final By errorMessage = By.xpath("//*[contains(@class,'sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText')]");
    public By deletePopup = By.xpath("//*[text()='Delete Alert Rule']/ancestor::div[@role='dialog']");
    public final String[] expectedConditionFieldValues = {"VERIFIED STATUS", "ADDITIONAL INFORMATION", "VERIFICATION FAILURE REASON", "LINKTYPE", "GTIN", "REQUESTER GLN", "RESPONDER GLN", "CONTEXT","HTTP RESPONSE CODE", "CONTROL ATTESTATION"};
    public final String errorMsg = "Message does not appear or is wrong.";
    public final By dropdownIcon = By.xpath("(//span[contains(@id,'USSCAlertservices-show-component---detail--oTableid-0-arrow')])[1]");

    public String getMasterPageTitle() throws Exception {
        waitForPageToLoad();
        return getElement(masterPageTitle).getText();
    }

    public String getDetailPageTitle() throws Exception {
        waitForPageToLoad();
        return getElement(detailPageTitle).getText();
    }

    public WebElement getNotificationTab() throws Exception {
        waitForPageToLoad();
        return getElement(notificationTab);
    }

    public WebElement getConditionsTab() throws Exception {
        waitForPageToLoad();
        return getElement(conditionsTab);
    }

    public void enterRecipientName(String name) throws Exception {
        waitForPageToLoad();
        inputText(createRecipientNameId, name);
    }

    public void enterRecipientEmail(String email) throws Exception {
        waitForPageToLoad();
        inputText(createRecipientEmailId, email);
    }

    public String getCreateRecipientDialogTitle() throws Exception {
        waitForPageToLoad();
        return getElement(createRecipientDialogTitle).getText();
    }

    public void clickAddButton() throws Exception {
        waitForPageToLoad();
        getElement(addButton).click();
    }

    public void clickDeleteButton() throws Exception {
        waitForPageToLoad();
        getElement(deleteButton).click();
    }

    public void clickAddRuleButton() throws Exception {
        waitForPageToLoad();
        getElement(addRuleButtonId).click();
    }

    public String getCreateAlertRuleDialogTitle() throws Exception {
        waitForPageToLoad();
        System.out.println(createAlertRuleDialogTitle);
        return getElement(createAlertRuleDialogTitle).getText();
    }

    public void enterRuleName(String name) throws Exception {
        waitForPageToLoad();
        inputText(ruleNameId, name);
    }

    public void enterRuleDescriptionId(String desc) throws Exception {
        waitForPageToLoad();
        inputText(ruleDescriptionId, desc);
    }

    public WebElement getFirstRuleFromRulesGroup(String name) throws Exception {
        CommonComponentPage.waitForPresenceofElement(By.xpath("//ul/li/..//span[text()='" + name + "']"));
        scrollToElement((WebElement) By.xpath("//ul/li/..//span[text()='" + name + "']"));
        return getElement(By.xpath("//ul/li/..//span[text()='" + name + "']"));
    }

    public void enterSearchField(String name) throws Exception {
        waitForPageToLoad();
        inputText(searchFieldID, name);
    }

    public void clickSearchIcon() throws Exception {
        waitForPageToLoad();
        getElement(searchFieldIconID).click();
    }

    public WebElement getDeletePopUpHeader(String header) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//h2/span[text()='" + header + "']"));
    }

    public String getDeletePopUpMessage() throws Exception {
        waitForPageToLoad();
        return getElement(deletePopUpMessage).getText();
    }

    public WebElement getFlagSwitchFieldId(String field) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//*[contains(text(),'" + field + "')]/following::*[contains(@class,'sapMSwtCont')][1]"));
    }

    public String getDefaultAlertStatus() throws Exception {
        waitForPageToLoad();
        return getElement(inactiveAlertStatus).getText();
    }

    public void clickStatusSwitchButton() throws Exception {
        waitForPageToLoad();
        getElement(statusSwitchButton).click();
    }

    public String getAlertStatus() throws Exception {
        waitForPageToLoad();
        return getElement(activeAlertStatus).getText();
    }

    public List<String> getRecipientsFromTable() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> tableData = portalDriver.getTableRows(By.id(recipientTable));
        List<String> list = new ArrayList<>();
        System.out.println(tableData.size());
        for (WebElement row : tableData) {

            List<WebElement> cells = row.findElements(By.xpath("//td/span"));

            System.out.println(cells.size());
            for (WebElement text : cells) {
                list.add(text.getText());

            }
        }
        return list;
    }

    public String getRecipientTableHeader() throws Exception {
        waitForPageToLoad();
        return getElement(recipientTableHeader).getText();
    }

    public void selectRecipientRecord() throws Exception {
        waitForPageToLoad();
        getElement(recipientRadioButton).click();
    }

    public String getReviewPageRuleName() throws Exception {
        waitForPageToLoad();
        return getElement(ruleTitle).getText();
    }

    public String getConditionTableHeader() throws Exception {
        waitForPageToLoad();
        return getElement(conditionTableHeader).getText();
    }

    public List<String> getTableColumnHeader() throws Exception {
        waitForPageToLoad();
        List<WebElement> colHeaders = getElements(tableColumnHeader);
        List<String> list = new ArrayList<>();
        for (WebElement header : colHeaders) {
            list.add(header.getText());
        }
        return list;
    }

    public List<String> getConditionsFromTable() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> tableData = portalDriver.getTableRows(By.id(conditionsTable));
        List<String> list = new ArrayList<>();
        System.out.println(tableData.size());
        for (WebElement row : tableData) {

            List<WebElement> cells = row.findElements(By.xpath("//td/..//input[@type='text']"));

            System.out.println(cells.size());
            for (WebElement text : cells) {
                list.add(text.getAttribute("value"));

            }
        }
        return list;
    }

    public void clickFieldDropDown() throws Exception {
        waitForPageToLoad();
        getElement(fieldDropDownBtn).click();
    }

    public void clickOperatorDropDownBtn() throws Exception {
        waitForPageToLoad();
        getElement(operatorDropDownBtn).click();
    }

    public void clickValueDropDownBtn() throws Exception {
        waitForPageToLoad();
        getElement(valueDropDownBtn).click();
    }

    public void selectFieldValue(String fieldName) throws Exception {
        waitForPageToLoad();
        List<WebElement> values = getElements(fieldValueList);
        for (WebElement value : values) {
            Actions actions = new Actions(SeleniumUI5TestUtil.getConfig().getDriver());
            System.out.println(value.getText());
            if (value.getText().equals(fieldName)) {
                actions.moveToElement(value).click().build().perform();
                //  value.click();
            }

        }
    }

    public List<String> getOperatorAndValueList(String fieldName, String listName) throws Exception {
        waitForPageToLoad();
        List<String> list = new ArrayList<>();
        if (listName.equals("Operator")) {
            clickOperatorDropDownBtn();
            for (WebElement value : getElements(operatorValueList)) {
                list.add(value.getText());
            }
        }
        if (listName.equals("Values")) {
            clickValueDropDownBtn();
            for (WebElement value : getElements(valueList)) {
                list.add(value.getText());
            }
        }
        return list;
    }

    public int getConditionsCount() throws Exception {
        waitForTableToLoad();
        List<WebElement> tableData = portalDriver.getTableRows(By.id(conditionsTable));
        System.out.println(tableData.size());
        return tableData.size();
    }


    public void selectFirstConditionFromTable() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> tableData = portalDriver.getTableRows(By.id(conditionsTable));
        System.out.println(tableData.size());
        for (WebElement row : tableData) {
            row.findElement(By.xpath("//td[2]/div/div")).click();

            break;
        }
    }

    public List<String> getFieldValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> values = getElements(fieldValueList);
        List<String> list = new ArrayList<>();
        System.out.println(values.size());
        for (WebElement value : values) {

            list.add(value.getText());

        }

        return list;
    }

    public void selectTheConditions(String field) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(By.xpath("//input[@value='" + field + "']/preceding::td[1]/div/div")).click();
    }

    public void selectTheValue(String value) throws Exception {
        waitForPageToLoad();
        clickValueDropDownBtn();
        getElement(By.xpath("//li/div/div/div[text()='" + value + "']")).click();
    }

    public void clickDeleteAlertRuleYesButton() throws Exception {
        waitForPageToLoad();
        getElement(deleteAlertRuleYesButton).click();
    }

    public void selectConditionValue() throws Exception {
        waitForPageToLoad();
        getElement(conditionValueCheckboxInput).click();
    }

    public WebElement getAlertFlagElement() throws Exception {
        waitForPageToLoad();
        return getElement(alertFlagText);
    }

    public WebElement getAlertFlag(String name, String flag) throws Exception {
        waitForPageToLoad();

        if (name.equals("Status")) {
            return getElement(By.xpath("//*[text()='Status:']/following::*[contains(@class,'sapMSwtLabel')][text()='" + flag + "']"));

        } else {
            return getElement(By.xpath("//*[text()='Alert in App:']/following::*[contains(@class,'sapMSwtLabel')][text()='" + flag + "']"));

        }

    }

    public WebElement getStatusFlagElement() throws Exception {
        waitForPageToLoad();

        return getElement(statusFlagText);
    }

    public List<WebElement> getFlagStatusInDetailPage() throws Exception {
        waitForPageToLoad();
        return getElements(flagStatusInDetailPage);
    }

    public String getErrorMessage() throws Exception{
        return getElement(errorMessage).getText();
    }


}