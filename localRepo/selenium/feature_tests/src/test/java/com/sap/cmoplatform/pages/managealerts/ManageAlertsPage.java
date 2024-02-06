package com.sap.cmoplatform.pages.managealerts;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.objects.VerificationLog;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ManageAlertsPage extends Page {

    private Properties manageAlertsPageIds = PropertyReader.loadProperties("managealerts/ManageAlertsPageIds.properties");
    private String prefix = manageAlertsPageIds.getProperty("managealerts.home.pagePrefix");
    private String btnFilters = prefix + manageAlertsPageIds.getProperty("managealerts.home.btnFilters");

    private String requestTableId = prefix + manageAlertsPageIds.getProperty("managealerts.home.requestTableId");
    public String saveViewPopUpHeader = prefix + manageAlertsPageIds.get("managealerts.home.saveViewPopUpHeader");
    private String saveViewName = prefix + manageAlertsPageIds.get("managealerts.home.saveViewName");
    private String defaultBtn = prefix + manageAlertsPageIds.get("managealerts.home.defaultBtn");
    private String saveViewSaveBtn = prefix + manageAlertsPageIds.get("managealerts.home.saveViewSaveBtn");
    private String btnSave = prefix + manageAlertsPageIds.get("managealerts.home.btnSave");
    private String selectViewDropDown = prefix + manageAlertsPageIds.get("managealerts.home.selectViewDropDown");
    private String btnGoFilter = prefix + manageAlertsPageIds.get("managealerts.home.btnGoFilter");
    public String requestDateId = prefix + manageAlertsPageIds.get("managealerts.home.requestDateId");
    public String gtinId = prefix + manageAlertsPageIds.get("managealerts.home.gtinId");
    public String lotId = prefix + manageAlertsPageIds.get("managealerts.home.lotId");

    public String serialNumber = prefix + manageAlertsPageIds.get("managealerts.home.serialNumber");
    public String additionalInformationId = prefix + manageAlertsPageIds.get("managealerts.home.additionalInformationId");
    public String requestorGlnId = prefix + manageAlertsPageIds.get("managealerts.home.requestorGlnId");
    public String responderGlnId = prefix + manageAlertsPageIds.get("managealerts.home.responderGlnId");
    public String uuid = prefix + manageAlertsPageIds.get("managealerts.home.uuid");
    public String linkTypeId = prefix + manageAlertsPageIds.get("managealerts.home.linkType");
    public String contextId = prefix + manageAlertsPageIds.get("managealerts.home.context");
    public String statusId = prefix + manageAlertsPageIds.get("managealerts.home.status");
    public String httpstatusId = prefix + manageAlertsPageIds.get("managealerts.home.httpstatus");
    public String httpresponseId = prefix + manageAlertsPageIds.get("managealerts.home.httpresponsecode");
    public By viewSearchInput = By.xpath("//input[@type='search'][@placeholder='Search']");
    public By defaultViewChckBox = By.xpath("//div[contains(@id,'application-AlertManagement-show-component---worklist--filterbar-variantUi2-manage-def')]");
    private By httpResponseCodeList = By.xpath("//*[contains(@id,'inputFilterHttpCode-valueStateText')]/div/ul/li");
    private By verificationfailurereason = By.xpath("(//span[contains(text(),'Manufacturer_policy')])[2]");
    public static final By groupPath = By.xpath("//tr[@class= 'sapMLIB sapMLIB-CTX sapMLIBShowSeparator sapMLIBTypeInactive sapMLIBFocusable sapMGHLI']");

    private By DetailedViewPageNavigationButton = By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div/ul/li[2]");
    private By ERVPageNavigationButton = By.xpath("//ul//li//div[text()='ERV']");
    private By searchBox = By.xpath("//*[@id='react-select-17-placeholder']");

    private By BusinessViewPageNavigationButton = By.xpath("//ul//li//button[contains(@id,'controlled-tab-example-tab-business_view')]");
    private By ProvisionsPageNavigationButton = By.xpath("//ul//li//button[contains(@id,'controlled-tab-example-tab-provisions')]");
    private By selectView = By.xpath("(//input[contains(@id,'worklist--filterbar')])[1]");
    private By tableContentSetting = By.xpath("//button[@title='Personalization']/span/span");
    private final By verificationFailReasonInput = By.xpath("//input[contains(@id,'VerificationFailReason')]");
    private String personalDialogUndoButton = prefix + manageAlertsPageIds.getProperty("managealerts.home.personalDialogUndoButton");
    private By selectAllColumnInput = By.xpath("//*[(@aria-label='Select all rows')]");
    private By selectAllColumnBtn = By.xpath("//*[(@aria-label='Select all rows')]/div");
    private static By alertRuleNameColumn = By.xpath("//*[contains(@data-sap-ui-column,'alertRuleNameColumn')]/span");
    private static final By uiidcolumn = By.xpath("//*[(text()='Alert ID (UUID)')]/following::div[1]/span");
    private static final By gtinColumn = By.xpath("//*[contains(@data-sap-ui-column,'pcodeColumn')]/span");
    private static By alertDateColumn = By.xpath("//*[contains(@data-sap-ui-column,'dateColumn')]/span");
    private static By expDateColumn = By.xpath("//*[contains(@data-sap-ui-column,'expDateColumn')]/span");
    private static By batchNameColumn = By.xpath("//*[contains(@data-sap-ui-column,'batchNameColumn')]/span");
    private static By verifiedColumn = By.xpath("//*[contains(@data-sap-ui-column,'verifiedColumn')]/span");
    private static By additionalInfoColumn = By.xpath("//*[contains(@data-sap-ui-column,'additionalInfoColumn')]/span");
    private static By statusColumn = By.xpath("//*[contains(@data-sap-ui-column,'statusColumn')]/span");
    private static By issueColumn = By.xpath("//*[contains(@aria-labelledby,'issueColumnTitle')]");
    private static By resolutionColumn = By.xpath("//*[contains(@aria-labelledby,'resolutionColumnTitle')]");
    private By descendingOrderButton = By.xpath("(//*[contains(@name,'sortorderlist_selectGroup')]/parent::div/node()/node()[2])[2]");
    private String inputFilterHttpCodeArrow = prefix + manageAlertsPageIds.getProperty("managealerts.home.inputFilterHttpCodeArrow");
    private static By serialNumberColumn = By.xpath("//*[contains(@data-sap-ui-column,'packSerialNumberColumn')]/span");
    private static By requestTimeColumn = By.xpath("//*[contains(@data-sap-ui-column,'requestTimeColumn')]/span");
    private static By requesterGlnColumn = By.xpath("//*[(text()='Requester GLN')]/following::div[1]/span");
    private static By requesterCompanyColumn = By.xpath("//*[(text()='Requester Company')]/following::div[1]/span");
    private static By responderGlnColunm = By.xpath("//*[(text()='Responder GLN')]/following::div[1]/span");
    private static By httpResponseCode = By.xpath("//*[(text()='HTTP Response Code')]/following::div[1]/span");
    private static By httpStatus = By.xpath("//*[(text()='HTTP Status')]/following::div[1]/span");
    private static By responderCompanyColumn = By.xpath("//*[(text()='Responder Company')]/following::div[1]/span");
    private static By getNoneColumn = By.xpath("//*[text()='None']/preceding::input[1]/parent::node()");
    private static By getAlert = By.xpath("(//*[@class='sapMListTblSelCol']/div/div)[1]");
    private static By getNotGroupedColumn = By.xpath("//*[text()='(Not Grouped)']/preceding::input[1]/parent::node()");
    private String inputFilterAlertRuleName = prefix + manageAlertsPageIds.getProperty("managealerts.home.inputFilterAlertRuleName");
    private String inputAlertDateFilter = prefix + manageAlertsPageIds.getProperty("managealerts.home.inputAlertDateFilter");
    private String inputFilterGTIN = prefix + manageAlertsPageIds.getProperty("managealerts.home.inputFilterGTIN");
    public String verifiedId = prefix + manageAlertsPageIds.getProperty("managealerts.home.verifiedId");
    private String inputFilterAdditionalInfo = prefix + manageAlertsPageIds.getProperty("managealerts.home.inputFilterAdditionalInfo");
    private String inputFilterBatch = prefix + manageAlertsPageIds.getProperty("managealerts.home.inputFilterBatch");
    private String selectFilterStatus = prefix + manageAlertsPageIds.getProperty("managealerts.home.selectFilterStatus");

    Actions actions = new Actions(portalDriver.getConfig().getDriver());


    public void switchToFrame(String frameId) throws Exception {
        waitForPageToLoad();
        portalDriver.getConfig().getDriver().switchTo().defaultContent();
        waitForTableToLoad();
        portalDriver.getConfig().getDriver().switchTo().frame(frameId);
        waitForTableToLoad();
        waitForPageToLoad();
    }

    public void navigateToDetailedViewPage() throws Exception {
        clickElement(DetailedViewPageNavigationButton);

    }
    public void navigateToERVPage() throws Exception {
        clickElement(ERVPageNavigationButton);
    }

    public void navigateToBusinessViewPage() throws Exception {
        clickElement(BusinessViewPageNavigationButton);
    }

    public void navigateToProvisionsPage() throws Exception {
        clickElement(ProvisionsPageNavigationButton);
    }

    public void enterTextToSearch() throws Exception {
        waitForPageToLoad();
        getElement(searchBox).sendKeys();
    }

    public boolean checkColumnDisplay1(String columnName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        boolean flag = false;
        if (getElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div/div[1]/div/div/div/div")).size() > 0) {
            flag = true;
        }
        return flag;
    }





    public enum FilterField {
        GTIN("GTIN", gtinColumn),
        UIID("UIID", uiidcolumn),
        AlertDate("Alert Date", alertDateColumn),
        AlertRuleName("Alert Rule Name", alertRuleNameColumn),
        BatchNumber("Lot/Batch Number", batchNameColumn),
        Verified("Verified", verifiedColumn),
        AdditionalInformation("Additional Information", additionalInfoColumn),
        Status("Status", statusColumn),
        Issue("Issue", issueColumn),
        SerialNumber("Serial Number", serialNumberColumn),
        RequesterGln("Requester GLN", requesterGlnColumn),
        ResponderGln("Responder GLN", responderGlnColunm),
        RequesterCompany("Requester Company", requesterCompanyColumn),
        HTTPStatus("HTTP Status", httpStatus),
        HTTPResponseCode("HTTP Response Code", httpResponseCode),
        ResponderCompany("Responder Company", responderCompanyColumn),
        Resolution("Resolution", resolutionColumn),
        ExpirationDate("Expiration Date", expDateColumn),
        RequestTime("Request Time", requestTimeColumn);
        public final By columnID;
        public final String filter;

        FilterField(String filter, By filterInputID) {
            this.filter = filter;
            this.columnID = filterInputID;
        }

    }

    public void clickBtnFilters() throws Exception {
        waitForPageToLoad();
        clickElement(btnFilters);
    }
    public void enterFailureReason(String reason) throws Exception {
        getElement(verificationFailReasonInput).sendKeys(reason);
    }

    public List<String> getHttpResponseCodeValues() {

        List<String> actualCodes = new ArrayList<>();

        for (WebElement code : getElements(httpResponseCodeList)) {
            actualCodes.add(code.getText());
        }
        return actualCodes;
    }

    public void selectAllColumnsToDisplay() throws Exception {
        waitForPageToLoad();
        clickElement(tableContentSetting);
        waitForPageToLoad();
        getElement(personalDialogUndoButton).click();
        if (getElement(selectAllColumnInput).getAttribute("aria-checked").equals("false")) {
            getElement(selectAllColumnBtn).click();
        }
    }
    public Boolean checkColumnValues(String columnName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        System.out.println(getElements(FilterField.valueOf(columnName).columnID).get(0).getText());
        Boolean flag = getElements(FilterField.valueOf(columnName).columnID).get(0).isDisplayed();
        return flag;
    }
    public Boolean checkGroupRadioButton(String groupButton) throws Exception {
        return getElement(By.xpath("//*[text()='" + groupButton + "']")).isDisplayed();
    }
    public String checkFieldValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        return getElement(verificationfailurereason).getText();
    }

    public String[] getColumnValues(String columnName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(FilterField.valueOf(columnName).columnID).size();
        String[] columnValues = new String[count];
        for (int i = 0; i < count; i++) {
            columnValues[i] = getElements(FilterField.valueOf(columnName).columnID).get(i).getText();
        }
        return columnValues;
    }
    public String[] getGroupColumnValues(String columnName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(groupPath).size();
        String[] columnValues = new String[count];
        for (int i = 0; i < count; i++) {
            columnValues[i] = getElements(groupPath).get(i).getText();
        }
        return columnValues;

    }

    public String[] getExpirationDateColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(expDateColumn).size();
        String[] expirationDate = new String[count];
        //   String[] parsedDate = new String[count];
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            expirationDate[i] = getElements(expDateColumn).get(i).getText();
        }
        for (int i = 0; i < count; i++) {
            String[] splitDate = expirationDate[i].split("-");
            if (splitDate.length == 3) {
                if ((splitDate[0] + splitDate[1] + splitDate[2]).matches("[0-9]+")) {
                    list.add(splitDate[0] + splitDate[1] + splitDate[2]);
                }
            }
        }
        String[] parsedDate = new String[list.size()];
        return list.toArray(parsedDate);
    }

    public String[] getExpirationDateGroupValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(groupPath).size();
        String[] expirationDate = new String[count];
        //   String[] parsedDate = new String[count];
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            expirationDate[i] = getElements(groupPath).get(i).getText();
        }
        for (int i = 0; i < count; i++) {
            String[] splitDate = expirationDate[i].split("-");
            if (splitDate.length == 3) {
                if ((splitDate[0] + splitDate[1] + splitDate[2]).matches("[0-9]+")) {
                    list.add(splitDate[0] + splitDate[1] + splitDate[2]);
                }
            }
        }
        String[] parsedDate = new String[list.size()];
        return list.toArray(parsedDate);
    }

    public void clickInputFilterHttpCodeArrow() throws Exception {
        waitForPageToLoad();
        clickElement(inputFilterHttpCodeArrow);
    }


    public String[] getAlertDateColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(alertDateColumn).size();
        String[] expirationDate = new String[count];
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            expirationDate[i] = getElements(alertDateColumn).get(i).getText();
        }
        for (int i = 0; i < count; i++) {
            String[] splitDate = expirationDate[i].split("-");
            if (splitDate.length == 3) {
                if ((splitDate[0] + splitDate[1] + splitDate[2]).matches("[0-9]+")) {
                    list.add(splitDate[0] + splitDate[1] + splitDate[2]);
                }
            }
        }
        String[] parsedDate = new String[list.size()];
        return list.toArray(parsedDate);
    }
    public String[] getAlertDateGroupValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(groupPath).size();
        String[] expirationDate = new String[count];
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            expirationDate[i] = getElements(groupPath).get(i).getText();
        }
        for (int i = 0; i < count; i++) {
            String[] splitDate = expirationDate[i].split("-");
            if (splitDate.length == 3) {
                if ((splitDate[0] + splitDate[1] + splitDate[2]).matches("[0-9]+")) {
                    list.add(splitDate[0] + splitDate[1] + splitDate[2]);
                }
            }
        }
        String[] parsedDate = new String[list.size()];
        return list.toArray(parsedDate);
    }

    public String[] getResolutionColumn() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(resolutionColumn).size();
        String[] resolution = new String[count];
        for (int i = 0; i < count; i++) {
            resolution[i] = getElements(resolutionColumn).get(i).getAttribute("value");
        }
        return resolution;
    }
    public String[] getResolutionGroup() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(groupPath).size();
        String[] resolution = new String[count];
        for (int i = 0; i < count; i++) {
                resolution[i] = getElements(groupPath).get(i).getAttribute("value");
        }


        return resolution;
    }

    public void clickDescendingOrderButton() throws Exception {
        waitForPageToLoad();
        getElement(descendingOrderButton).click();
    }

    public String[] getRequestTimeColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(requestTimeColumn).size();
        String[] requestDate = new String[count];
        String[] parsedRequestDate = new String[count];

        for (int i = 0; i < count; i++) {
            requestDate[i] = getElements(requestTimeColumn).get(i).getText();

        }
        for (int i = 0; i < count; i++) {
            String[] reqDate = requestDate[i].split("T");
            parsedRequestDate[i] = reqDate[0].replace("-", "");
        }
        return parsedRequestDate;
    }

    public String[] getRequestTimeGroupValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(groupPath).size();
        String[] requestDate = new String[count];
        String[] parsedRequestDate = new String[count];

        for (int i = 0; i < count; i++) {
            requestDate[i] = getElements(groupPath).get(i).getText();

        }
        for (int i = 0; i < count; i++) {
            String[] reqDate = requestDate[i].split("T");
            parsedRequestDate[i] = reqDate[0].replace("-", "");
        }
        return parsedRequestDate;
    }

    public WebElement getInputFilterAlertRuleName() throws Exception {
        waitForPageToLoad();
        return getElement(inputFilterAlertRuleName);
    }

    public WebElement getInputAlertDateFilter() throws Exception {
        waitForPageToLoad();
        return getElement(inputAlertDateFilter);
    }

    public WebElement getInputFilterGTIN() throws Exception {
        waitForPageToLoad();
        return getElement(inputFilterGTIN);
    }

    public WebElement getVerifiedId() throws Exception {
        waitForPageToLoad();
        return getElement(verifiedId);
    }

    public WebElement getInputFilterAdditionalInfo() throws Exception {
        waitForPageToLoad();
        return getElement(inputFilterAdditionalInfo);
    }

    public WebElement getInputFilterBatch() throws Exception {
        waitForPageToLoad();
        return getElement(inputFilterBatch);
    }

    public WebElement getSelectFilterStatus() throws Exception {
        waitForPageToLoad();
        return getElement(selectFilterStatus);
    }

    public void clickSortingRadiobtn(String buttonName) throws Exception {
        waitForPageToLoad();
        if(buttonName.equals("None")){
            getElement(getNoneColumn).click();
        }
        else
        {
            getElement(By.xpath("//*[text()='" + FilterField.valueOf(buttonName).filter + "']/preceding::input[1]/parent::node()")).click();
        }

    }
    public void clickGroupRadiobtn(String buttonName) throws Exception {
        waitForPageToLoad();
        getElement(By.xpath("//*[text()='" + FilterField.valueOf(buttonName).filter + "']/preceding::input[1]/parent::node()")).click();

    }
    public void clickAlert() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(getAlert).click();
    }
    public void clickIssueAndresolutionColumn(String columnName) throws Exception {
        waitForTableToLoad();
        getElements(FilterField.valueOf(columnName).columnID).get(0).click();
    }
    public void selectFromIssueAndResolutionColumn(String columnName) throws Exception {
        getElement(By.xpath("//*[text()='"+columnName+"']/ancestor::li")).click();
    }
    public List<VerificationLog> getLogs() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        Thread.sleep(10000);
        List<VerificationLog> logs = new ArrayList<>();
        List<WebElement> tableData = getTableRows(By.id(requestTableId));
        int count = tableData.size();
        System.out.println(count);
        for (int i = 0; i < count; i = i + 2) {
            WebElement row = getTableRows(By.id(requestTableId)).get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            VerificationLog log = new VerificationLog()
                    .withalertRuleName(cells.get(2).getText())
                    .withalertDate(cells.get(3).getText())
                    .withGtin(cells.get(4).getText())
                    .withLot(cells.get(5).getText())
                    .withVerified(cells.get(6).getText())
                    .withAdditionalInfo(cells.get(7).getText())
                    .withStatus(cells.get(8).getText())
                    .withissue(cells.get(9).getText())
                    .withresolution(cells.get(10).getText())
                    .withExpirationDate(cells.get(11).getText())
                    .withSerialNum(cells.get(12).getText())
                    .withRequestTime(cells.get(13).getText())
                    .withchangeHistory(cells.get(14).getText());
            logs.add(log);

        }
        return logs;
    }
    public List<VerificationLog> getVrLogs() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        Thread.sleep(10000);
        List<VerificationLog> logs = new ArrayList<>();
        List<WebElement> tableData = getTableRows(By.id(requestTableId));
        int count = tableData.size();
        System.out.println(count);
        for (int i = 2; i < count; i = i + 2) {
            WebElement row = getTableRows(By.id(requestTableId)).get(i);
            List<WebElement> cells = row.findElements(By.xpath("//td[2]/..//div[@class='sapMListTblSubCntVal sapMListTblSubCntValBlock']/span"));
            VerificationLog log = new VerificationLog()
                    .withverificationFailurereason(cells.get(0).getText())
                    .withUUID(cells.get(1).getText())
                    .withRequestorGln(cells.get(2).getText())
                    .withRequesterCompany(cells.get(3).getText())
                    .withResponderGln(cells.get(4).getText())
                    .withResponderCompany(cells.get(5).getText())
                    .withLinkType(cells.get(6).getText())
                    .withContext(cells.get(7).getText())
                    .withhttpstatus(cells.get(8).getText())
                    .withResponseCode(cells.get(9).getText())
                    .withResponseCodeDescription(cells.get(10).getText())
                    .withcontrolAttestation(cells.get(12).getText())
                    .withrequestorEmail(cells.get(13).getText())
                    .withrequestorTelephone(cells.get(14).getText())
                    .withresponderEmail(cells.get(15).getText())
                    .withresponderTelephone(cells.get(16).getText());
            logs.add(log);
        }
        return logs;
    }
    public WebElement getSaveViewPopUpHeader() throws Exception {
        waitForPageToLoad();
        return getElement(saveViewPopUpHeader);
    }
    public void enterSaveViewName(String name) throws Exception {
        waitForPageToLoad();
        getElement(saveViewName).clear();
        inputText(saveViewName, name);
    }
    public void clickDefaultButton() throws Exception {
        waitForPageToLoad();
        getElement(defaultBtn).click();
    }
    public void clickSaveViewSaveBtn() throws Exception {
        waitForPageToLoad();
        clickElement(saveViewSaveBtn);
    }
    public void clickSaveButton() throws Exception {
        waitForPageToLoad();
        clickElement(btnSave);
    }
    public void clickSelectViewDropDown() throws Exception {
        getElement(selectViewDropDown).click();
    }
    public void clickFilterPopUpGoButton() throws Exception {
        waitForPageToLoad();
        clickElement(btnGoFilter);
    }
    public WebElement getRequestDateId() throws Exception {
        waitForPageToLoad();
        return getElement(requestDateId);
    }
    public WebElement getGtinId() throws Exception {
        waitForPageToLoad();
        return getElement(gtinId);
    }
    public WebElement getLotId() throws Exception {
        waitForPageToLoad();
        return getElement(lotId);
    }
    public WebElement getSerialNumberId() throws Exception {
        waitForPageToLoad();
        return getElement(serialNumber);
    }
    public WebElement getAdditionalInformationId() throws Exception {
        waitForPageToLoad();
        return getElement(additionalInformationId);
    }
    public WebElement getRequestorId() throws Exception {
        waitForPageToLoad();
        return getElement(requestorGlnId);
    }
    public WebElement getResponderId() throws Exception {
        waitForPageToLoad();
        return getElement(responderGlnId);
    }
    public WebElement getUUID() throws Exception {
        waitForPageToLoad();
        return getElement(uuid);
    }
    public WebElement getLinkTypeId() throws Exception {
        waitForPageToLoad();
        return getElement(linkTypeId);
    }
    public WebElement getContextId() throws Exception {
        waitForPageToLoad();
        return getElement(contextId);
    }
    public WebElement getStatusId() throws Exception {
        waitForPageToLoad();
        return getElement(statusId);
    }
    public WebElement getHttpStatusId() throws Exception {
        waitForPageToLoad();
        return getElement(httpstatusId);
    }
    public WebElement getResponseCodeStatusId() throws Exception {
        waitForPageToLoad();
        return getElement(httpresponseId);
    }
    public void enterGtin(String gtin) throws Exception {
        waitForPageToLoad();
        getElement(gtinId).clear();
        inputText(gtinId, gtin);
    }
    public void enterLot(String lot) throws Exception {
        waitForPageToLoad();
        getElement(lotId).clear();
        inputText(lotId, lot);
    }
    public void enterRequestDate(String date) throws Exception {
        waitForPageToLoad();
        getElement(requestDateId).clear();
        inputText(requestDateId, date);
        getElement(gtinId).click();
        String text = getElement(requestDateId).getAttribute("value");
        inputText(requestDateId, "-" + text);

    }
    public void enterSerialNumber(String serialNum) throws Exception {
        waitForPageToLoad();
        getElement(serialNumber).clear();
        inputText(serialNumber, serialNum);
    }
    public void enterAdditionalInfo(String additionalInformation) throws Exception {
        waitForPageToLoad();
        if (additionalInformation == "") {
            additionalInformation = "Suspect";
        }
        inputText(additionalInformationId, additionalInformation);
        getElement(additionalInformationId).sendKeys(Keys.ENTER);
    }
    public void enterResponderGln(String responderGln) throws Exception {
        waitForPageToLoad();
        getElement(responderGlnId).clear();
        inputText(responderGlnId, responderGln);
    }
    public void enterUUID(String correlationUuid) throws Exception {
        waitForPageToLoad();
        getElement(uuid).clear();
        inputText(uuid, correlationUuid);
    }
    public void enterLinkType(String linktype) throws Exception {
        waitForPageToLoad();
        getElement(linkTypeId).clear();
        inputText(linkTypeId, linktype);
    }
    public void enterContext(String context) throws Exception {
        waitForPageToLoad();
        //getElement(contextId).clear();
        inputText(contextId, context);
        getElement(contextId).sendKeys(Keys.ENTER);
    }
    public void enterRequestorGln(String requestorGln) throws Exception {
        waitForPageToLoad();
        getElement(requestorGlnId).clear();
        inputText(requestorGlnId, requestorGln);
    }
    public void enterStatus(String status) throws Exception {
        waitForPageToLoad();
        clickElement(By.xpath("//span[contains(@class,'sapMTokenIcon')]"));
        inputText(statusId, status);
        getElement(statusId).sendKeys(Keys.ENTER);
    }
}
