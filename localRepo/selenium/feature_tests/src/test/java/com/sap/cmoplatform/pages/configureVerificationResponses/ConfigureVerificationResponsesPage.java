package com.sap.cmoplatform.pages.configureVerificationResponses;

import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigureVerificationResponsesPage extends Page {

    private Properties configVerificationResponsesPageIds = PropertyReader.loadProperties("configureVerificationResponses/ConfigResponsePageIds.properties");
    private Properties users = PropertyReader.loadProperties("Users.properties");
    private String prefix = configVerificationResponsesPageIds.getProperty("configureverificationresponse.home.pagePrefix");
    private String addRuleButton = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.home.addRuleButton");
    private By dispositionContentText = By.xpath("//table[contains(@id,'dispositionContent-listUl')]/tbody/tr/td/span");
    private String deleteRuleButton = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.home.deleteRuleButton");
    private By gs1ScenarioViewBtn = By.xpath("//*[@title='Expand/Collapse']");
    private By panelHeader = By.className("sapMPanelHdr");
    private By dispositionRule = By.xpath("//td[contains(@data-sap-ui-column,'appView--rule')]/..//input[@type='radio']/parent::div");
    private By dispositionNameInput = By.xpath("//input[contains(@id,'dispositionRulesFragment--dispositionCell')]");
    public By firstDispositionNameInput = By.xpath("(//input[contains(@id,'dispositionRulesFragment--dispositionCell')])[1]");
    private By dispositionInputCheckBox = By.xpath("//input[contains(@id,'dispositionRulesFragment--dispositionCell')]/preceding::td[1]");

    private By dispositionRuleTableHeader = By.xpath("//div[@role='heading']/span");
    private String dispositionRuleMenu = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.home.dispRulestTitleText");
    private String expiryRuleMenu = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.home.expRulestTitleText");
    private By ruleDeletedPopup = By.xpath("//div[text()='Rule deleted']");
    private String sortRulesButton = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.home.sortRulesButton");
    private By changeLogButton = By.xpath("//*[text()='Change Log']");
    //private By showHistoryLink = By.xpath("//a[text()='Show history of changes']");
    private By expiredDispositionContentText = By.xpath("//table[contains(@id,'expiryContent-listUl')]/tbody/tr/td/span");
    private By expiredPIText = By.xpath("//td[contains(@data-sap-ui-column,'expiryRulesFragment--expiredPi')]/div/span");
    private By expiryRuleHistoryLogs = By.xpath("//tbody[contains(@id,'showExpLog-table-tblBody')]/tr[1]/td/span");
    private By saveDispositionMessage = By.xpath("//header[@class='sapMDialogTitle']/..//section/div/div/span");
    private String blockListMenu = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.blocklistGln.blockListTitleText");
    private String addGLNBlockListButton = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.blocklistGln.addGLNBlocklistButton");
    private By glnBlockListInput = By.xpath("//input[contains(@id,'GLNBlocklistCell')]");
    private By glnBlockListCompanyNameInput = By.xpath("//input[contains(@id,'GLNBlocklistCompanyNameCell')]");
    private By glnBlockListReasonInput = By.xpath("//input[contains(@id,'GLnBlocklistreasoncell')]");
    private By glnActiveSwitch = By.xpath("//*[contains(@id,'GLNActiveCell')][@class='sapMSwtHandle']");
    private By glnActiveSwitchField = By.xpath("//*[contains(@id,'GLNActiveCell')]/parent::div");
    private By descendingRadioButton = By.xpath("//*[text()='Descending']/preceding::input[1]/parent::div");
    private By ascendingRadioButton = By.xpath("//*[text()='Ascending']/preceding::input[1]/parent::div");
    private String sortGLNBlocklistButton = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.blocklistGln.sortGLNBlocklistButton");
    private By sortByPopUpLabel = By.xpath("//*[text()='Sort']");
    private String showGLNBlocklistLogTitle = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.blocklistGln.showGLNBlocklistLogTitle");
    private String showGLNBlocklistLogSearchFieldId = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.blocklistGln.showGLNBlocklistLogSearchFieldId");
    private String showGLNBlocklistLogSearchIcon = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.blocklistGln.showGLNBlocklistLogSearchIcon");
    private By changeHistoryGlnColumn = By.xpath("//*[contains(@id,'showGLNBlocklistLog-table-tblBody')]/..//td[3]/span");
    private By blockedGlnList = By.xpath("//tbody[contains(@id,'glnBlockList-tblBody')]/tr/td/div/div");
    private String provideReasonTitle = prefix + configVerificationResponsesPageIds.get("configureverificationresponse.blocklistGln.provideReasonTitle");
    private By fieldError = By.xpath("//*[contains(@class,'sapMInputBaseContentWrapper sapMInputBaseContentWrapperState sapMInputBaseContentWrapperError')]");
    private By provideReasonInput = By.xpath("//*[contains(@id,'provideReason-cont')]/..//input");

    private By blockedGlnHistoryLogRows = By.xpath("//tbody[contains(@id,'showGLNBlocklistLog-table-tblBody')]");
    private By blockedGlnHistoryGLNColumn = By.xpath("//tbody[contains(@id,'showGLNBlocklistLog-table-tblBody')]/tr/td[3]/span");


    private String user = users.getProperty("USSC_Manufacturer.username");

    public String getPanelHeader() throws Exception {
        waitForPageToLoad();
        return getElement(panelHeader).getText();
    }

    public void clickGs1ScenarioViewBtn() throws Exception {
        getElement(gs1ScenarioViewBtn).click();
    }

    public List<String> getDispositionContent() throws Exception {
        waitForTableToLoad();
        List<String> dispositionContent = new ArrayList<String>();
        List<WebElement> elements = getElements(dispositionContentText);
        for (WebElement cells : elements) {
            dispositionContent.add(cells.getText());
        }
        return dispositionContent;
    }

    public void clickDispositionRule() throws Exception {
        waitForPageToLoad();
        getElement(dispositionRule).click();
    }

    public void enterDispositionName(String row, String dispName) throws Exception {
        waitForPageToLoad();
        if (row.equals("first")) {
            getElements(dispositionNameInput).get(0).sendKeys(dispName);
        } else if (row.equals("second")) {
            getElements(dispositionNameInput).get(1).sendKeys(dispName);

        }

    }

    public void clickAddRuleButton() throws Exception {
        getElement(addRuleButton).click();
    }

    public WebElement getDispositionName() throws Exception {
        waitForTableToLoad();
        return getElement(dispositionNameInput);
    }

    public WebElement getDispositionRuleMenu() throws Exception {
        return getElement(dispositionRuleMenu);
    }

    public WebElement getExpiryRuleMenu() throws Exception {
        return getElement(expiryRuleMenu);
    }

    public void selectDispositionRule(String rule, String row) throws Exception {
        waitForPageToLoad();
        if (row.equals("first")) {
            getElement(By.xpath("//bdi[text()='" + rule + "']/preceding::input[1]/parent::div")).click();
        } else if (row.equals("second")) {
            getElement(By.xpath("(//bdi[text()='" + rule + "']/preceding::input[1]/parent::div)[2]")).click();
        }
    }

    public boolean isRuleDeletedPopUpDisplayed() throws Exception {
        return getElement(ruleDeletedPopup).isDisplayed();
    }

    public void clickDeleteIcon() throws Exception {
        clickElement(deleteRuleButton);
    }

    public void selectDisposition(String dispositionName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(dispositionNameInput).size();
        for (int i = 0; i < count; i++) {
            String name = getElements(dispositionNameInput).get(i).getAttribute("value");
            if (name.equals(dispositionName)) {
                getElements(dispositionInputCheckBox).get(i).click();
                break;
            }
        }
    }

    public void clickSortRulesButton() throws Exception {
        waitForPageToLoad();
        getElement(sortRulesButton).click();
    }

    public void clickChangeLogButton() throws Exception {
        getElement(changeLogButton).click();
    }


    public List<String> getHistoryLogs(int index) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<String> logs = new ArrayList<>();
        for (int i = 1; i <= index; i++) {
            logs.add(getElement(By.xpath("(//tbody[contains(@id,'showLog-table-tblBody')]/..//td[2]/span)[" + index + "]")).getText());
            logs.add(getElement(By.xpath("(//tbody[contains(@id,'showLog-table-tblBody')]/..//td[3]/span)[" + index + "]")).getText());
            logs.add(getElement(By.xpath("(//tbody[contains(@id,'showLog-table-tblBody')]/..//td[5]/span)[" + index + "]")).getText());
            logs.add(getElement(By.xpath("(//tbody[contains(@id,'showLog-table-tblBody')]/..//td[6]/span)[" + index + "]")).getText());
            logs.add(getElement(By.xpath("(//tbody[contains(@id,'showLog-table-tblBody')]/..//td[7]/span)[" + index + "]")).getText());
        }
        return logs;
    }

    public String getUserName() {
        return user;
    }

    public void updateDispositionRule(String rule, String dispositionName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(dispositionNameInput).size();
        for (int i = 0; i < count; i++) {
            String name = getElements(dispositionNameInput).get(i).getAttribute("value");
            if (name.equals(dispositionName)) {
                getElements(By.xpath("(//bdi[text()='" + rule + "']/preceding::input[1]/parent::div)")).get(i).click();
                break;
            }
        }
    }

    public boolean getDispositionRuleName(String dispositionName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(dispositionNameInput).size();
        System.out.println(count);
        boolean flag = false;
        for (int i = 0; i < count; i++) {
            String name = getElements(dispositionNameInput).get(i).getAttribute("value");
            System.out.println(i+" name: "+ name);
            if (name.equals(dispositionName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean isDispositionNameElementEnabled() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        return getElement(dispositionNameInput).isEnabled();
    }

    public String[] getDispositionNames() throws Exception {
        waitForTableToLoad();
        int count = getElements(dispositionNameInput).size();
        String[] dispositionName = new String[count];
        for (int i = 0; i < count; i++) {
            dispositionName[i] = getElements(dispositionNameInput).get(i).getAttribute("value");
        }
        return dispositionName;
    }


    public List<String> getExpiredDispositionContent() throws Exception {
        waitForTableToLoad();
        List<String> dispositionContent = new ArrayList<String>();
        List<WebElement> elements = getElements(expiredDispositionContentText);
        for (WebElement cells : elements) {
            dispositionContent.add(cells.getText());
        }
        return dispositionContent;
    }

    public WebElement getExpiredPITextElement() throws Exception {
        waitForPageToLoad();
        return getElement(expiredPIText);
    }

    public WebElement getButtonText(String rule) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//bdi[text()='" + rule + "']"));
    }


    public void selectExpiryRule(String rule) throws Exception {
        waitForPageToLoad();
        if (!getElement(By.xpath("//bdi[text()='" + rule + "']/preceding::input[1]")).isSelected()) {
            getElement(By.xpath("//bdi[text()='" + rule + "']/preceding::input[1]/parent::div")).click();
        }
    }

    public boolean isExpiryRuleSet(String rule) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//bdi[text()='" + rule + "']/preceding::input[1]")).isSelected();

    }

    public List<String> getExpiryRulesHistoryLogs() throws Exception {
        List<String> logs = new ArrayList<>();
        for (int i = 2; i <= 7; i++) {
            logs.add(getElement(By.xpath("//tbody[contains(@id,'showExpLog-table-tblBody')]/tr[1]/td[" + i + "]/span")).getText());
        }
        return logs;
    }

    public String getSaveDispositionMessage() throws Exception {
        waitForPageToLoad();
        return getElement(saveDispositionMessage).getText();
    }

    public WebElement getBlockListMenu() throws Exception {
        return getElement(blockListMenu);
    }

    public void clickAddGLNBlockListButton() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(addGLNBlockListButton).click();
    }

    public WebElement getGlnBlockListInput() throws Exception {
        return getElement(glnBlockListInput);
    }

    public WebElement getGlnBlockListCompanyNameInput() throws Exception {
        waitForTableToLoad();
        return getElement(glnBlockListCompanyNameInput);
    }

    public WebElement getGlnBlockListReasonInput() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        return getElement(glnBlockListReasonInput);
    }

    public WebElement getGlnActiveSwitch() throws Exception {
        waitForTableToLoad();
        return getElement(glnActiveSwitch);
    }

    public WebElement getGlnActiveSwitchField() throws Exception {
        waitForTableToLoad();
        return getElement(glnActiveSwitchField);
    }


    public void clickSortGLNBlocklistButton() throws Exception {
        waitForTableToLoad();
        getElement(sortGLNBlocklistButton).click();
    }

    public void selectSortOption(String field) throws Exception {
        waitForPageToLoad();
        getElement(By.xpath("//*[text()='" + field + "']/preceding::input[1]/parent::div")).click();
    }

    public void clickAscendingOrderButton() throws Exception {
        waitForPageToLoad();
        getElement(ascendingRadioButton).click();
    }

    public void clickDescendingOrderButton() throws Exception {
        waitForPageToLoad();
        getElement(descendingRadioButton).click();
    }

    public String[] getGlnColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(glnBlockListInput).size();
        String[] Gln = new String[count];
        int i = 0;
        for (WebElement element : getElements(glnBlockListInput)) {
            Gln[i] = element.getAttribute("value");
            i++;
        }
        return Gln;
    }


    public String[] getActiveSwitchValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(glnActiveSwitch).size();
        String[] activeSwitch = new String[count];
        int i = 0;
        for (WebElement element : getElements(glnActiveSwitch)) {
            activeSwitch[i] = element.getAttribute("data-sap-ui-swt");
            i++;
        }
        return activeSwitch;
    }


    public WebElement getSortByLabel() throws Exception {
        waitForPageToLoad();
        return getElement(sortByPopUpLabel);
    }


    public String getShowGLNBlocklistLogTitle() throws Exception {
        waitForTableToLoad();
        return getElement(showGLNBlocklistLogTitle).getText();
    }

    public WebElement getGlnSearchInputId() throws Exception {
        waitForTableToLoad();
        return getElement(showGLNBlocklistLogSearchFieldId);
    }

    public void clickShowGLNBlocklistLogSearchIcon() throws Exception {
        waitForTableToLoad();
        getElement(showGLNBlocklistLogSearchIcon).click();
    }

    public List<String> getHistoryGlnValues() throws Exception {
        waitForTableToLoad();
        List<String> gln = new ArrayList<>();
        for (WebElement element : getElements(changeHistoryGlnColumn)) {
            gln.add(element.getText());
        }
        return gln;
    }


    public List<WebElement> getGlnBlockListCompanyNameColumn() throws Exception {
        waitForTableToLoad();
        return getElements(glnBlockListCompanyNameInput);
    }

    public void selectTheBlockedGLN() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(blockedGlnList).click();
    }

    public WebElement getProvideReasonPopUpTitle() throws Exception {
        waitForPageToLoad();
        return getElement(provideReasonTitle);
    }

    public WebElement getFieldError() throws Exception {
        waitForPageToLoad();
        return getElement(fieldError);
    }

    public void enterReason(String reason) throws Exception {
        waitForPageToLoad();
        getElement(provideReasonInput).sendKeys(reason);
    }



    public void selectTheBlockedGlnRecord(String gln) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(By.xpath("//input[@value='" + gln + "']/preceding::input[1]/parent::div")).click();

    }

    public List<String> getBlockedGlnHistoryLogs(int index) throws Exception {
        List<String> logs = new ArrayList<>();
        for (int i = 2; i <= 7; i++) {
            logs.add(getElement(blockedGlnHistoryLogRows).findElement(By.xpath("tr[" + index + "]/td[" + i + "]/span")).getText());
        }
        return logs;
    }

    public void clickOnActiveSwitchForTheSelectedRecord(String gln) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(By.xpath("//input[@value='" + gln + "']/following::*[contains(@id,'GLNActiveCell')][@class='sapMSwtHandle'][1]")).click();

    }

    public WebElement getGlnBlockListReasonInputOfGlnRecord(String gln) throws Exception {
        waitForTableToLoad();
        return getElement(By.xpath("//input[@value='" + gln + "']/following::input[contains(@id,'GLnBlocklistreasoncell')][1]"));
    }

    public String getGlnActiveSwitchValueOfGlnRecord(String gln) throws Exception {
        waitForTableToLoad();
        return getElement(By.xpath("//input[@value='" + gln + "']/following::*[contains(@id,'GLNActiveCell')][@class='sapMSwtHandle'][1]")).getAttribute("data-sap-ui-swt");
    }
    public boolean isWarningBorderDisplayed() throws Exception {
        return getElement(fieldError).isDisplayed();
    }

}
