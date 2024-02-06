package com.sap.cmoplatform.pages.verificationlog;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.objects.VerificationLog;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class VerificationLogPage extends Page {
    public By clearFilter = By.xpath("//span[contains(@id,'listView-innerListViewTable-clearSelection')]");
    public By viewSearchInput = By.xpath("//input[@type='search'][@placeholder='Search']");
    public By defaultViewChckBox = By.xpath("//div[contains(@id,'application-analytics-show-component---worklist--filterbar-variantUi2-manage-def')]");
    private Properties verificationLogPageIds = PropertyReader.loadProperties("verificationlog/VerificationLogPageIds.properties");
    private String prefix = verificationLogPageIds.getProperty("verificationlog.home.pagePrefix");
    public String requestDateId = prefix + verificationLogPageIds.get("verificationlog.home.requestDateId");
    public String gtinId = prefix + verificationLogPageIds.get("verificationlog.home.gtinId");
    public String lotId = prefix + verificationLogPageIds.get("verificationlog.home.lotId");
    public String verifiedId = prefix + verificationLogPageIds.get("verificationlog.home.verifiedId");
    public String serialNumber = prefix + verificationLogPageIds.get("verificationlog.home.serialNumber");
    //private By serialNumber = By.xpath("//*[@title='Serial Number']");
    public String additionalInformationId = prefix + verificationLogPageIds.get("verificationlog.home.additionalInformationId");
    public String requestorGlnId = prefix + verificationLogPageIds.get("verificationlog.home.requestorGlnId");
    public String responderGlnId = prefix + verificationLogPageIds.get("verificationlog.home.responderGlnId");
    public String uuid = prefix + verificationLogPageIds.get("verificationlog.home.uuid");
    public String linkTypeId = prefix + verificationLogPageIds.get("verificationlog.home.linkType");
    public String contextId = prefix + verificationLogPageIds.get("verificationlog.home.context");
    public String executionDurationId = prefix + verificationLogPageIds.get("verificationlog.home.executionDurationId");
    private String btnShowHide = prefix + verificationLogPageIds.get("verificationlog.home.btnShowHide");
    private String btnFilters = prefix + verificationLogPageIds.get("verificationlog.home.btnFilters");
    private String btnGo = prefix + verificationLogPageIds.get("verificationlog.home.btnGo");
    private String tableHeader = prefix + verificationLogPageIds.get("verificationlog.home.tableHeader");
    private String requestTableId = prefix + verificationLogPageIds.get("verificationlog.home.requestTableId");
    private String btnSave = prefix + verificationLogPageIds.get("verificationlog.home.btnSave");
    private String btnGoFilter = prefix + verificationLogPageIds.get("verificationlog.home.btnGoFilter");
    private String saveViewSaveBtn = prefix + verificationLogPageIds.get("verificationlog.home.saveViewSaveBtn");
    private String saveViewCancelBtn = prefix + verificationLogPageIds.get("verificationlog.home.saveViewCancelBtn");
    private String selectViewDropDown = prefix + verificationLogPageIds.get("verificationlog.home.selectViewDropDown");
    public String saveViewPopUpHeader = prefix + verificationLogPageIds.get("verificationlog.home.saveViewPopUpHeader");
    private String saveViewName = prefix + verificationLogPageIds.get("verificationlog.home.saveViewName");
    private String defaultBtn = prefix + verificationLogPageIds.get("verificationlog.home.defaultBtn");
    private String defaultVerifiedId = prefix + verificationLogPageIds.get("verificationlog.home.defaultVerifiedId");
    private String manageViewButton = prefix + verificationLogPageIds.get("verificationlog.home.manageViewButton");
    private String manageSaveButton = prefix + verificationLogPageIds.get("verificationlog.home.manageSaveButton");
    private String manageCancelButton = prefix + verificationLogPageIds.get("verificationlog.home.manageCancelButton");
    private String personalDialogUndoButton = prefix + verificationLogPageIds.get("verificationlog.home.personalDialogUndoButton");
    private String statusId = prefix + verificationLogPageIds.get("verificationlog.home.statusId");
    private By tableContentSetting = By.xpath("//button[@title='Personalization']/span/span");
    private By selectAllColumnInput = By.xpath("//*[contains(@id,'PersoDialog-colTable')]/div/input");
    private By selectAllColumnBtn = By.xpath("(//*[contains(@id,'PersoDialog-colTable')]/div)[2]");
    private By btnLotRadio = By.xpath("//div[text()='Lot/Batch Number']/preceding::input[1]/parent::div");
    private By btnGtinRadio = By.xpath("//div[text()='GTIN']/preceding::input[1]/parent::div");
    private By btnRequestTimeRadio = By.xpath("//div[text()='Request Time']/preceding::input[1]/parent::div");
    private By btnExpirationDateRadio = By.xpath("//div[text()='Expiration Date']/preceding::input[1]/parent::div");
    private By btnSerialNumberRadio = By.xpath("//div[text()='Serial Number']/preceding::input[1]/parent::div");
    private By btnVerifiedRadio = By.xpath("//div[text()='Verified']/preceding::input[1]/parent::div");
    private By btnRequestorGlnRadio = By.xpath("//div[text()='Requester GLN']/preceding::input[1]/parent::div");
    private By btnResponderGlnRadio = By.xpath("//div[text()='Responder GLN']/preceding::input[1]/parent::div");
    private By btnAdditionalInfoRadio = By.xpath("//div[text()='Additional Information']/preceding::input[1]/parent::div");
    private By btnDescendingOrder = By.xpath("//div[text()='Descending']/preceding::input[1]/parent::div");
    private By btnExecutionDurationRadio = By.xpath("//div[text()='Execution Duration (ms)']/preceding::input[1]/parent::div");
    private By btnRequesterCompanyRadio = By.xpath("//div[text()='Requester Company']/preceding::input[1]/parent::div");
    private By btnResponderCompanyRadio = By.xpath("//div[text()='Responder Company']/preceding::input[1]/parent::div");
    private By gtinText = By.xpath("//bdi[text()='GTIN']/following::div[1]/span");
    private By lotText = By.xpath("//bdi[text()='Lot/Batch Number']/following::div[1]/span");
    private By expirationDateText = By.xpath("//span[@aria-label='Expiration Date']/following::div[1]/span");
    private By serialNumberText = By.xpath("//bdi[text()='Serial Number']/following::div[1]/span");
    private By requestorGlnText = By.xpath("//bdi[text()='Requester GLN']/following::div[1]/span");
    private By responderGlnText = By.xpath("//bdi[text()='Responder GLN']/following::div[1]/span");
    private By verifiedText = By.xpath("//bdi[text()='Verified']/following::div[1]/span");
    private By requestTimeText = By.xpath("//bdi[text()='Request Time']/following::div[1]/span");
    private By additionalInformationText = By.xpath("//bdi[text()='Additional Information']/following::div[1]/span");
    private By detailsScreenFirstItem = By.xpath("(//td[@class='sapMListTblNavCol'])[1]");
    private By uuidText = By.xpath("//bdi[text()='UUID']/following::div[1]/span");
    private By contextText = By.xpath("//bdi[text()='Context']/following::div[1]/span");
    private By linkTypeText = By.xpath("//bdi[text()='Linktype']/following::div[1]/span");
    private By executionDurationText = By.xpath("//bdi[text()='Execution Duration (ms)']/following::div[1]/span");
    private By requestTimeColumn = By.xpath("//bdi[contains(@id,'table-PersoDialog')][text()='Request Time']/preceding::div[1]");
    private By gtinColumn = By.xpath("//bdi[contains(@id,'table-PersoDialog')][text()='GTIN']/preceding::div[1]");
    private By lotColumn = By.xpath("//bdi[contains(@id,'table-PersoDialog')][contains(text(),'Lot')]/preceding::div[1]");
    private By expirationDateColumn = By.xpath("//*[contains(@id,'table-PersoDialog')][text()='Expiration Date']/preceding::div[1]");
    private By serialNumberColumn = By.xpath("//bdi[contains(@id,'table-PersoDialog')][contains(text(),'Ser')]/preceding::div[1]");
    private By verifiedColumn = By.xpath("//bdi[contains(@id,'table-PersoDialog')][contains(text(),'Ver')]/preceding::div[1]");
    private By requestorGlnColumn = By.xpath("//*[contains(@id,'table-PersoDialog')][contains(text(),'Requester GLN')]/preceding::div[1]");
    private By responderGlnColumn = By.xpath("//*[contains(@id,'table-PersoDialog')][contains(text(),'Respon')]/preceding::div[1]");
    private By additionalInfoColumn = By.xpath("//bdi[contains(@id,'table-PersoDialog')][contains(text(),'Add')]/preceding::div[1]");
    private By uuidColumn = By.xpath("//*[contains(@id,'table-PersoDialog')][text()='UUID']/preceding::div[1]");
    private By linkTypeColumn = By.xpath("//*[contains(@id,'table-PersoDialog')][contains(text(),'Link')]/preceding::div[1]");
    private By contextColumn = By.xpath("//*[contains(@id,'table-PersoDialog')][contains(text(),'Con')]/preceding::div[1]");
    private By verificationFailureReasonColumn = By.xpath("//span[contains(@id,'table-PersoDialog-li-application-analytics-show-component')][text()='Verification Failure Reason']/preceding::div[1]");
    private String requestTimeCol = prefix + verificationLogPageIds.get("verificationlog.home.requestTimeCol");
    private String gtinCol = prefix + verificationLogPageIds.get("verificationlog.home.gtinCol");
    private String lotCol = prefix + verificationLogPageIds.get("verificationlog.home.lotCol");
    private String expirationDateCol = prefix + verificationLogPageIds.get("verificationlog.home.expirationDateCol");
    private String serialNumberCol = prefix + verificationLogPageIds.get("verificationlog.home.serialNumberCol");
    private String verifiedCol = prefix + verificationLogPageIds.get("verificationlog.home.verifiedCol");
    private String requestorGlnCol = prefix + verificationLogPageIds.get("verificationlog.home.requestorGlnCol");
    private String responderGlnCol = prefix + verificationLogPageIds.get("verificationlog.home.responderGlnCol");
    private String additionalInformationCol = prefix + verificationLogPageIds.get("verificationlog.home.additionalInformationCol");
    private By uuidCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='UUID']");
    private By linkTypeCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Link Type']");
    private By contextCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Context']");
    private By verificationFailureReasonCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Verification Failure Reason']");
    private By executionDurationCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Execution Duration (ms)']");
    private By requesterCompanyCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Requester Company']");
    private By responderCompanyCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Responder Company']");
    private By statusCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Status']");
    public By responseCodeCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Response Code']");
    private By responseCodeDescriptionCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Response Code Description']");
    private By alertEmailStatusCol = By.xpath("//*[@class='sapMListTblSubCntHdr']/span[text()='Alert Email Status']");
    private String personalDialogDownButton = prefix + verificationLogPageIds.get("verificationlog.home.personalDialogDownButton");
    private By secondColHeader = By.xpath("//th[3]/div/span");
    private String executionDurationProgress = prefix + verificationLogPageIds.get("verificationlog.home.executionDurationProgress");
    private By executionDurationColumn = By.xpath("//*[(text()='Execution Duration (ms)')]/following::div/span");
    private String executionDurationStartHandle = prefix + verificationLogPageIds.get("verificationlog.home.executionDurationStartHandle");
    private String executionDurationEndHandle = prefix + verificationLogPageIds.get("verificationlog.home.executionDurationEndHandle");
    private By blockChainEntryLink = By.xpath("//label[@aria-label='Blockchain Entry']/following::a");
    private By blockChainStreamText = By.xpath("//label[@aria-label='Blockchain Stream']/following::div[1]");
    private By blockChainNetworkText = By.xpath("//label[@aria-label='Blockchain Network']/following::div[1]");
    private String nodataTextId = prefix + verificationLogPageIds.get("verificationlog.home.nodataText");
    private By requesterCompanyColumn = By.xpath("//*[(text()='Requester Company')]/following::span[1]");
    private By responderCompanyColumn = By.xpath("//*[(text()='Responder Company')]/following::span[1]");
    private String requesterCompanyId = prefix + verificationLogPageIds.get("verificationlog.home.requesterCompanyId");
    private String responderCompanyId = prefix + verificationLogPageIds.get("verificationlog.home.responderCompanyId");
    private String defaultRequesterCompanyId = prefix + verificationLogPageIds.get("verificationlog.home.defaultRequesterCompanyId");
    private String defaultResponderCompanyId = prefix + verificationLogPageIds.get("verificationlog.home.defaultResponderCompanyId");
    private By errorMessage = By.xpath("//div[@role='alertdialog']/..//section/div/div/span");
    private By statusCodeList = By.xpath("//*[contains(@id,'statuscode-valueStateText')]/..//ul/li");
    private String statusCodeId = prefix + verificationLogPageIds.get("verificationlog.home.statusCodeId");
    private String repositoryLinkId = "" + verificationLogPageIds.get("verificationlog.home.repositoryLinkId");

    Actions actions = new Actions(portalDriver.getConfig().getDriver());
    WebDriverWait wait = new WebDriverWait(portalDriver.getConfig().getDriver(), 120);


    public String getTableHeader() throws Exception {
        waitForPageToLoad();
        return getElement(tableHeader).getText();
    }

    public void clickBtnFilters() throws Exception {
        waitForPageToLoad();
        clickElement(btnFilters);
    }

    public WebElement getSaveViewPopUpHeader() throws Exception {
        waitForPageToLoad();
        return getElement(saveViewPopUpHeader);
    }

    public void enterRequestDate(String date) throws Exception {
        waitForPageToLoad();
        getElement(requestDateId).clear();
        inputText(requestDateId, date);
        getElement(gtinId).click();
        String text = getElement(requestDateId).getAttribute("value");
        inputText(requestDateId, "-" + text);

    }

    public void enterGtin(String gtin) throws Exception {
        waitForPageToLoad();
        getElement(gtinId).clear();
        inputText(gtinId, gtin);
    }

    public void enterUUID(String correlationUuid) throws Exception {
        waitForPageToLoad();
        getElement(uuid).clear();
        inputText(uuid, correlationUuid);
    }

    public void enterContext(String context) throws Exception {
        waitForPageToLoad();
        //getElement(contextId).clear();
        inputText(contextId, context);
        getElement(contextId).sendKeys(Keys.ENTER);
    }

    public void enterLinkType(String linktype) throws Exception {
        waitForPageToLoad();
        getElement(linkTypeId).clear();
        inputText(linkTypeId, linktype);
    }

    public void enterRequestorGln(String requestorGln) throws Exception {
        waitForPageToLoad();
        getElement(requestorGlnId).clear();
        inputText(requestorGlnId, requestorGln);
    }

    public void enterResponderGln(String responderGln) throws Exception {
        waitForPageToLoad();
        getElement(responderGlnId).clear();
        inputText(responderGlnId, responderGln);
    }

    public void enterLot(String lot) throws Exception {
        waitForPageToLoad();
        getElement(lotId).clear();
        inputText(lotId, lot);
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

    public void selectAllColumnsToDisplay() throws Exception {
        waitForTableToLoad();
        clickElement(tableContentSetting);
        waitForPageToLoad();
        getElement(personalDialogUndoButton).click();
        if (!getElement(selectAllColumnInput).isSelected()) {
            clickElement(selectAllColumnBtn);
        }
    }

    public void selectAllColumnButton() throws Exception {
        waitForPageToLoad();
        clickElement(tableContentSetting);
        waitForPageToLoad();
        clickElement(selectAllColumnBtn);
    }

    public void unSelectAllSelection() throws Exception {
        waitForPageToLoad();
        int i = 1;
        List<WebElement> checkboxes = getElements(By.xpath("//div[@title='Show on Filter Bar']/div/input"));
        for (WebElement checkbox : checkboxes) {
            try {
                if (checkbox.getAttribute("checked").equals("true")) {
                    getElement(By.xpath("(//div[@title='Show on Filter Bar']/div)[" + i + "]")).click();
                }
            } catch (NullPointerException ne) {
                System.out.println("Filter Element not selected");
            }
            i++;
        }

    }


    public void clickSaveButton() throws Exception {
        waitForPageToLoad();
        clickElement(btnSave);
    }

    public void clickSaveViewSaveBtn() throws Exception {
        waitForPageToLoad();
        clickElement(saveViewSaveBtn);
    }

    public void clickShowHideFilterBarButton() throws Exception {
        waitForPageToLoad();
        clickElement(btnShowHide);
    }

    public void clickGoButton() throws Exception {
        waitForPageToLoad();
        clickElement(btnGo);
    }

    public void clickFilterPopUpGoButton() throws Exception {
        waitForPageToLoad();
        clickElement(btnGoFilter);
    }

    public WebElement getRequestDateId() throws Exception {
        waitForPageToLoad();
        return getElement(requestDateId);
    }

    public void selectDropDownValue(String fieldName, String value) throws Exception {
        if (fieldName.equals("verified")) {
            selectDropDownFilterInput(verifiedId, value);
        }
        if (fieldName.equals("RequesterCompany")) {
            waitForPageToLoad();
            getElement(requesterCompanyId).click();
            actions.moveToElement(getElement(By.xpath("//li[text()='" + value + "']"))).click().perform();
            //  getElement(By.xpath("//li[text()='"+value+"']")).click();
        }
        if (fieldName.equals("ResponderCompany")) {
            waitForPageToLoad();
            getElement(responderCompanyId).click();
            actions.moveToElement(getElement(By.xpath("//li[text()='" + value + "']"))).click().perform();
        }
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

    public WebElement getVerifiedId() throws Exception {
        waitForPageToLoad();
        return getElement(verifiedId);
    }

    public WebElement getUUID() throws Exception {
        waitForPageToLoad();
        return getElement(uuid);
    }

    public WebElement getDefaultVerifiedId() throws Exception {
        waitForPageToLoad();
        return getElement(defaultVerifiedId);
    }

    public WebElement getRequestorId() throws Exception {
        waitForPageToLoad();
        return getElement(requestorGlnId);
    }

    public WebElement getResponderId() throws Exception {
        waitForPageToLoad();
        return getElement(responderGlnId);
    }

    public WebElement getAdditionalInformationId() throws Exception {
        waitForPageToLoad();
        return getElement(additionalInformationId);
    }

    public WebElement getLinkTypeId() throws Exception {
        waitForPageToLoad();
        return getElement(linkTypeId);
    }

    public WebElement getContextId() throws Exception {
        waitForPageToLoad();
        return getElement(contextId);
    }

    public WebElement getContextValues() throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//span[@class='sapMTokenText']"));
    }

    public WebElement getExecutionDurationId() throws Exception {
        waitForPageToLoad();
        return getElement(executionDurationId);
    }

    public String getRequestDateFromVerificationLogTable(String date) {
//        String[] reqDate = date.split(",");
//        String parseDate = reqDate[0] + "," + reqDate[1];
//        return parseDate;
        String[] reqDate = date.split("T");
        return reqDate[0];
    }

    public String getRequestDateFromFilterSection(String date) {
        String[] reqDate = date.split(" - ");
        String parseDate = reqDate[0];
        return parseDate;
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
                    .withRequestTime(cells.get(1).getText())
                    .withGtin(cells.get(2).getText())
                    .withLot(cells.get(3).getText())
                    .withExpirationDate(cells.get(4).getText())
                    .withSerialNum(cells.get(5).getText())
                    .withVerified(cells.get(6).getText())
                    .withRequestorGln(cells.get(7).getText())
                    .withResponderGln(cells.get(8).getText())
                    .withAdditionalInfo(cells.get(9).getText());
//                    .withUUID(cells.get(10).getText())
//                    .withLinkType(cells.get(11).getText())
//                    .withContext(cells.get(12).getText())
//                    .withExecutionDuration(cells.get(14).getText())
//                    .withRequesterCompany(cells.get(15).getText())
//                    .withResponderCompany(cells.get(16).getText());
            logs.add(log);

//            VerificationLog log = new VerificationLog()
//                    .withGtin(getElements(By.xpath("//td[@data-sap-ui-column='application-analytics-show-component---worklist--gtinCol']/span")).get(i).getText())
//                    .withRequestTime(getElements(By.xpath("//td[@data-sap-ui-column='application-analytics-show-component---worklist--requestTimeCol']/span")).get(i).getText())
//                    .withLot(getElements(By.xpath("//td[@data-sap-ui-column='application-analytics-show-component---worklist--lotCol']/span")).get(i).getText());
//            logs.add(log);
        }
//        for (int i = 2; i < count; i = i + 2) {
//            WebElement row = getTableRows(By.id(requestTableId)).get(i);
//            List<WebElement> cells = row.findElements(By.xpath("//td[2]/..//div[@class='sapMListTblSubCntVal sapMListTblSubCntValBlock']/span"));
//            VerificationLog log = new VerificationLog()
//                    .withUUID(cells.get(0).getText())
//                    .withLinkType(cells.get(1).getText())
//                    .withContext(cells.get(2).getText())
//                    .withExecutionDuration(cells.get(4).getText())
//                    .withRequesterCompany(cells.get(5).getText())
//                    .withResponderCompany(cells.get(6).getText());
//            System.out.println(cells.get(5).getText());
//            System.out.println(cells.get(6).getText());
//
//            logs.add(log);
//
//        }
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
                    .withUUID(cells.get(0).getText())
                    .withLinkType(cells.get(1).getText())
                    .withContext(cells.get(2).getText())
                    .withExecutionDuration(cells.get(4).getText())
                    .withRequesterCompany(cells.get(5).getText())
                    .withResponderCompany(cells.get(6).getText())
                    .withStatus(cells.get(7).getText())
                    .withResponseCode(cells.get(8).getText())
                    .withResponseCodeDescription(cells.get(9).getText())
                    .withAlertEmailStatus(cells.get(10).getText());
            logs.add(log);
        }
        return logs;
    }

    public String[] getLotColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(By.xpath("//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[4]/span")).size();
        String[] lotNum = new String[count];
        for (int i = 0; i < count; i++) {
            lotNum[i] = getElement(By.xpath("(//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[4]/span)[" + (i + 1) + "]")).getText();
            System.out.println(lotNum[i]);
        }
        return lotNum;
    }

    public String[] getGtinColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(By.xpath("//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[3]/span")).size();
        String[] gtin = new String[count];
        for (int i = 0; i < count; i++) {
            gtin[i] = getElement(By.xpath("(//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[3]/span)[" + (i + 1) + "]")).getText();

        }
        return gtin;
    }


    public void clickBtnLotRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnLotRadio);
    }

    public void clickBtnGtinRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnGtinRadio);
    }

    public void clickBtnRequestTimeRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnRequestTimeRadio);
    }

    public void clickBtnRequestorGlnRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnRequestorGlnRadio);
    }

    public void clickBtnResponderGlnRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnResponderGlnRadio);
    }

    public void clickBtnAdditionalInfoRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnAdditionalInfoRadio);
    }

    public void clickBtnVerifiedRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnVerifiedRadio);
    }

    public void clickBtnSerialNumberRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnSerialNumberRadio);
    }

    public void clickBtnDescendingOrder() throws Exception {
        waitForPageToLoad();
        clickElement(btnDescendingOrder);
    }

    public void clickBtnExecutionDurationRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnExecutionDurationRadio);
    }

    public void clickBtnRequesterCompanyRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnRequesterCompanyRadio);
    }

    public void clickBtnResponderCompanyRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnResponderCompanyRadio);
    }

    public String[] getVerifiedColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(By.xpath("//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[7]/span")).size();
        String[] verified = new String[count];
        for (int i = 0; i < count; i++) {
            verified[i] = getElement(By.xpath("(//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[7]/span)[" + (i + 1) + "]")).getText();

        }
        return verified;
    }

    public String[] getExpirationDateColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(By.xpath("//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[5]/span")).size();
        String[] expirationDate = new String[count];
        //   String[] parsedDate = new String[count];
        List<String> list = new ArrayList<String>();

        int j = 0;
        for (int i = 0; i < count; i++) {
            expirationDate[i] = getElement(By.xpath("(//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[5]/span)[" + (i + 1) + "]")).getText();
        }
        for (int i = 0; i < count; i++) {
            String[] splitDate = expirationDate[i].split("-");
            if (splitDate.length == 3) {
                if ((splitDate[0] + splitDate[1] + splitDate[2]).matches("[0-9]+")) {
                    list.add(splitDate[0] + splitDate[1] + splitDate[2]);
                    // parsedDate[i] = splitDate[0] + splitDate[1] + splitDate[2];
                    //  System.out.println(parsedDate[i]+" "+ i);
                }
            }
        }
        String[] parsedDate = new String[list.size()];
        return list.toArray(parsedDate);
        // return parsedDate;
    }

    public String[] getSerialNumberColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(By.xpath("//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[6]/span")).size();
        String[] serialNumber = new String[count];
        for (int i = 0; i < count; i++) {
            serialNumber[i] = getElement(By.xpath("(//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[6]/span)[" + (i + 1) + "]")).getText();

        }
        return serialNumber;
    }

    public String[] getRequestTimeColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(By.xpath("//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[2]/span")).size();
        String[] requestDate = new String[count];
        String[] parsedRequestDate = new String[count];

        for (int i = 0; i < count; i++) {
            requestDate[i] = getElement(By.xpath("(//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[2]/span)[" + (i + 1) + "]")).getText();

        }
//        for (int i = 0; i < count; i++) {
//            String[] reqDate = requestDate[i].split(", ");
//            String month = (reqDate[0].split(" "))[0];
//            String dd = (reqDate[0].split(" "))[1];
//            String year = reqDate[1];
//            if (Integer.parseInt(dd) < 10) {
//                dd = "0" + dd;
//            }
//            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM");
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(inputFormat.parse(month));
//            SimpleDateFormat outputFormat = new SimpleDateFormat("MM");
//            parsedRequestDate[i] = outputFormat.format(cal.getTime()) + dd + year;
//        }
        for (int i = 0; i < count; i++) {
            String[] reqDate = requestDate[i].split("T");
            parsedRequestDate[i] = reqDate[0].replace("-", "");
        }
        return parsedRequestDate;
    }

    public String[] getRequestorGlnColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(By.xpath("//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[8]/span")).size();
        String[] requestorGln = new String[count];
        for (int i = 0; i < count; i++) {
            requestorGln[i] = getElement(By.xpath("(//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[8]/span)[" + (i + 1) + "]")).getText();

        }
        return requestorGln;
    }

    public String[] getResponderGlnColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(By.xpath("//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[9]/span")).size();
        String[] responderGln = new String[count];
        for (int i = 0; i < count; i++) {
            responderGln[i] = getElement(By.xpath("(//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[9]/span)[" + (i + 1) + "]")).getText();

        }
        return responderGln;
    }

    public String[] getAdditionalInfoColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(By.xpath("//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[10]/span")).size();
        String[] additionalInfo = new String[count];
        for (int i = 0; i < count; i++) {
            additionalInfo[i] = getElement(By.xpath("(//tbody[@id='application-analytics-show-component---worklist--table-tblBody']/tr/td[10]/span)[" + (i + 1) + "]")).getText();

        }
        return additionalInfo;
    }

    public void clickBtnExpirationDateRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnExpirationDateRadio);
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

    public void clearGtinValue() throws Exception {
        waitForPageToLoad();
        getElement(gtinId).clear();
    }

    public void clearLotValue() throws Exception {
        waitForPageToLoad();
        getElement(lotId).clear();
    }

    public void clearSerialNumberValue() throws Exception {
        waitForPageToLoad();
        getElement(serialNumber).clear();
    }

    public void clearRequestDateValue() throws Exception {
        waitForPageToLoad();
        getElement(requestDateId).clear();
    }

    public void clearRequestorGlnValue() throws Exception {
        waitForPageToLoad();
        getElement(requestorGlnId).clear();
    }

    public void clearResponderGlnValue() throws Exception {
        waitForPageToLoad();
        getElement(responderGlnId).clear();
    }

    public void clearAdditionalInfoValue() throws Exception {
        waitForPageToLoad();
        getElement(By.xpath("//*[@class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMTokenIcon']")).click();
    }

    public void clearUUIDValue() throws Exception {
        waitForPageToLoad();
        getElement(uuid).clear();
    }

    public void clearLinkTypeValue() throws Exception {
        waitForPageToLoad();
        getElement(linkTypeId).clear();
    }

    public void clearContextValue() throws Exception {
        waitForPageToLoad();
        getElement(contextId).clear();
    }


    public void clickSelectViewDropDown() throws Exception {
        getElement(selectViewDropDown).click();
    }

    public void selectAvailableView(String fileName) throws Exception {
        waitForPageToLoad();
        actions.moveToElement(getElement(By.xpath("//li[text()='" + fileName + "']"))).click().perform();

    }

    public WebElement availableView(String viewName) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//li[text()='" + viewName + "']"));
    }

    public void setDefaultView(String viewName) throws Exception {
        waitForPageToLoad();
        getElement(manageViewButton).click();
        if (!getElement(By.xpath("//input[@value='" + viewName + "']/../following::input[1]")).isSelected()) {
            getElement(By.xpath("//input[@value='" + viewName + "']/../following::input[1]/parent::div")).click();
            getElement(manageSaveButton).click();
        } else {
            getElement(manageCancelButton).click();
        }
    }

    public void clickFirstRecord() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(detailsScreenFirstItem).click();
    }

    public Map<String, String> getRequestDetails() throws Exception {
        waitForPageToLoad();
        HashMap<String, String> reqDetails = new HashMap<String, String>();
        reqDetails.put("GTIN", getElement(gtinText).getText());
        reqDetails.put("LOT", getElement(lotText).getText());
        reqDetails.put("Request Time", getElement(requestTimeText).getText());
        reqDetails.put("Serial Number", getElement(serialNumberText).getText());
        reqDetails.put("Verified", getElement(verifiedText).getText());
        reqDetails.put("Additional Information", getElement(additionalInformationText).getText());
        reqDetails.put("Requestor GLN", getElement(requestorGlnText).getText());
        reqDetails.put("Responder GLN", getElement(responderGlnText).getText());
        reqDetails.put("UUID", getElement(uuidText).getText());
        reqDetails.put("Linktype", getElement(linkTypeText).getText());
        reqDetails.put("Context", getElement(contextText).getText());
        reqDetails.put("Execution Duration", getElement(executionDurationText).getText());

        return reqDetails;

    }

    public void selectRequestTimeColumn() throws Exception {
        waitForPageToLoad();
        getElement(requestTimeColumn).click();
    }

    public void selectGtinColumnn() throws Exception {
        waitForPageToLoad();
        getElement(gtinColumn).click();
    }

    public void selectAdditionalInfoColumn() throws Exception {
        waitForPageToLoad();
        getElement(additionalInfoColumn).click();
    }

    public void selectLotColumn() throws Exception {
        waitForPageToLoad();
        getElement(lotColumn).click();
    }

    public void selectRequestorGlnColumn() throws Exception {
        waitForPageToLoad();
        getElement(requestorGlnColumn).click();
    }

    public void selectResponderGlnColumn() throws Exception {
        waitForPageToLoad();
        getElement(responderGlnColumn).click();
    }

    public void selectExpirationDateColumn() throws Exception {
        waitForPageToLoad();
        getElement(expirationDateColumn).click();
    }

    public void selectLinkTypeColumn() throws Exception {
        waitForPageToLoad();
        getElement(linkTypeColumn).click();
    }

    public void selectVerifiedColumn() throws Exception {
        waitForPageToLoad();
        getElement(verifiedColumn).click();

    }

    public void selectUuidColumn() throws Exception {
        waitForPageToLoad();
        getElement(uuidColumn).click();
    }

    public void selectSerialNumberColumn() throws Exception {
        waitForPageToLoad();
        getElement(serialNumberColumn).click();
    }

    public void selectContextColumn() throws Exception {
        waitForPageToLoad();
        getElement(contextColumn).click();
    }
    public void selectVerificationFailureReasonColumn() throws Exception {
        waitForPageToLoad();
        actions.moveToElement(getElement(verificationFailureReasonColumn)).click().perform();
     //   getElement(verificationFailureReasonColumn).click();
    }

    public WebElement getRequestTimeCol() throws Exception {
        waitForPageToLoad();
        return getElement(requestTimeCol);
    }

    public WebElement getGtinCol() throws Exception {
        waitForPageToLoad();
        return getElement(gtinCol);
    }

    public WebElement getVerifiedCol() throws Exception {
        waitForPageToLoad();
        return getElement(verifiedCol);
    }

    public WebElement getExpirationDateCol() throws Exception {
        waitForPageToLoad();
        return getElement(expirationDateCol);
    }

    public WebElement getLotCol() throws Exception {
        waitForPageToLoad();
        return getElement(lotCol);
    }

    public WebElement getSerialNumberCol() throws Exception {
        waitForPageToLoad();
        return getElement(serialNumberCol);
    }

    public WebElement getRequestorGlnCol() throws Exception {
        waitForPageToLoad();
        return getElement(requestorGlnCol);
    }

    public WebElement getResponderGlnCol() throws Exception {
        waitForPageToLoad();
        return getElement(responderGlnCol);
    }

    public WebElement getAdditionalInformationCol() throws Exception {
        waitForPageToLoad();
        return getElement(additionalInformationCol);
    }

    public WebElement getContextCol() throws Exception {
        waitForPageToLoad();
        return getElement(contextCol);
    }

    public WebElement getExecutionDurationCol() throws Exception {
        waitForPageToLoad();
        return getElement(executionDurationCol);
    }


    public WebElement getVerificationFailureReasonCol() throws Exception {
        waitForPageToLoad();
        return getElement(verificationFailureReasonCol);
    }

    public WebElement getRequesterCompanyCol() throws Exception {
        waitForPageToLoad();
        return getElement(requesterCompanyCol);
    }

    public WebElement getResponderCompanyCol() throws Exception {
        waitForPageToLoad();
        return getElement(responderCompanyCol);
    }

    public WebElement getStatusCol() throws Exception {
        waitForPageToLoad();
        return getElement(statusCol);
    }

    public WebElement getResponseCodeCol() throws Exception {
        waitForPageToLoad();
        return getElement(responseCodeCol);
    }

    public WebElement getResponseCodeDescriptionCol() throws Exception {
        waitForPageToLoad();
        return getElement(responseCodeDescriptionCol);
    }

    public WebElement getAlertEmailStatusCol() throws Exception {
        waitForPageToLoad();
        return getElement(alertEmailStatusCol);
    }

    public WebElement getLinkTypeCol() throws Exception {
        waitForPageToLoad();
        return getElement(linkTypeCol);
    }

    public WebElement getUuidCol() throws Exception {
        waitForPageToLoad();
        return getElement(uuidCol);
    }

    public void clickPersonalDialogDownButton() throws Exception {
        waitForPageToLoad();
        clickElement(tableContentSetting);
        waitForPageToLoad();
        clickElement(personalDialogDownButton);
    }

    public String getColumnHeader() throws Exception {
        waitForTableToLoad();
        return getElement(secondColHeader).getText();
    }


//    public void selectFilterComponents(String filterValue) throws Exception {
//
//        if (filterValue.equals("RequestTime")) {
//            if (!getElement(requestTimeFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(requestTimeFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("GTIN")) {
//            if (!getElement(gtinFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(gtinFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("LOT")) {
//            if (!getElement(lotFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(lotFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("SerialNumber")) {
//            if (!getElement(serialnumberFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(serialnumberFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("Verified")) {
//            if (!getElement(verifiedFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(verifiedFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("AdditionalInfo")) {
//            if (!getElement(additionalInfoFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(additionalInfoFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("RequestorGln")) {
//            if (!getElement(requestorGlnFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(requestorGlnFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("ResponderGln")) {
//            if (!getElement(responderGlnFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(responderGlnFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("UUID")) {
//            if (!getElement(uuidFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(uuidFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("Linktype")) {
//            if (!getElement(linkTypeFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(linkTypeFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("Context")) {
//            if (!getElement(contextFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(contextFilterCheckbox).click();
//            }
//        }
//
//
//        if (filterValue.equals("RequesterCompany")) {
//            if (!getElement(requestorCompanyFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(requestorCompanyFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("ResponderCompany")) {
//            if (!getElement(responderCompanyFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(responderCompanyFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("Status")) {
//            if (!getElement(statusFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(statusFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("StatusCode")) {
//            if (!getElement(statuscodeFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(statuscodeFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("EmailStatus")) {
//            if (!getElement(emailStatusFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(emailStatusFilterCheckbox).click();
//            }
//        }
//        if (filterValue.equals("ExecutionDuration")) {
//            waitForPageToLoad();
//            for (int i = 0; i < 2; i++) {
//                if (!getElement(executionDurationFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                    getElement(executionDurationFilterCheckbox).click();
//                }
//            }
//        }
//        if (filterValue.equals("VerificationFailureReason")) {
//            if (!getElement(verificationFailureReasonFilterCheckbox).getAttribute("class").contains("sapMCbMarkChecked")) {
//                getElement(verificationFailureReasonFilterCheckbox).click();
//            }
//        }
//
//
//    }

    public void selectStatusFilter(String status) throws Exception {
        selectDropDownFilterInput(statusId, status);
    }

    public String getExecutionDurationRange() throws Exception {
        waitForPageToLoad();
        return getElement(executionDurationProgress).getAttribute("aria-valuenow");
    }

    public WebElement getExecutionDurationRangeID() throws Exception {
        waitForPageToLoad();
        return getElement(executionDurationProgress);
    }

    public void selectDefaultColumnsToDisplay() throws Exception {
        waitForPageToLoad();
        clickElement(tableContentSetting);
        waitForPageToLoad();
        getElement(personalDialogUndoButton).click();
    }

    public String[] getExecutionDurationColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(executionDurationColumn).size();
        String[] executionDuration = new String[count];
        for (int i = 0; i < count; i++) {
            executionDuration[i] = getElements(executionDurationColumn).get(i).getText();
        }
        return executionDuration;
    }

    public String getExecutionDurationOfFirstRecord() throws Exception {
        waitForPageToLoad();
        return getElement(executionDurationColumn).getText();
    }

    public void selectDefaultExecutionDurationMinRange() throws Exception {
        waitForPageToLoad();
        actions.moveToElement(getElement(executionDurationEndHandle))
                .dragAndDropBy
                        (getElement(executionDurationEndHandle), 100, 0)
                .perform();
    }

    public void selectDefaultExecutionDurationMaxRange() throws Exception {
        waitForPageToLoad();
        actions.moveToElement(getElement(executionDurationStartHandle))
                .dragAndDropBy
                        (getElement(executionDurationStartHandle), -50, 0)
                .perform();
    }

    public void selectExecutionDurationRange() throws Exception {
        waitForPageToLoad();
        actions.moveToElement(getElement(executionDurationEndHandle))
                .dragAndDropBy
                        (getElement(executionDurationEndHandle), -100, 0)
                .perform();
        actions.moveToElement(getElement(executionDurationStartHandle))
                .dragAndDropBy
                        (getElement(executionDurationStartHandle), 50, 0)
                .perform();
    }

    public WebElement getExecutionDurationEndHandle() throws Exception {
        waitForPageToLoad();
        return getElement(executionDurationEndHandle);
    }

    public WebElement getExecutionDurationStartHandle() throws Exception {
        waitForPageToLoad();
        return getElement(executionDurationStartHandle);
    }

    public void clickRepositoryExplorerLink() throws Exception {
        waitForPageToLoad();
        getElement(repositoryLinkId).click();
//        try {
//            wait.until(ExpectedConditions.visibilityOf(getElement(blockChainEntryLink)));
//            getElement(blockChainEntryLink).click();
//        } catch (Exception e) {
//            portalDriver.getConfig().getDriver().navigate().refresh();
//            wait.until(ExpectedConditions.visibilityOf(getElement(blockChainEntryLink)));
//        }

    }

    public WebElement getNodataTextId() throws Exception {
        waitForPageToLoad();
        return getElement(nodataTextId);
    }

    public String[] getRequesterCompanyColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(requesterCompanyColumn).size();
        String[] requesterCompany = new String[count];
        for (int i = 0; i < count; i++) {
            requesterCompany[i] = getElements(requesterCompanyColumn).get(i).getText();
        }
        return requesterCompany;
    }

    public String[] getResponderCompanyColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(responderCompanyColumn).size();
        String[] responderCompany = new String[count];
        for (int i = 0; i < count; i++) {
            responderCompany[i] = getElements(responderCompanyColumn).get(i).getText();
        }
        return responderCompany;
    }

    public WebElement getResponderCompanyId() throws Exception {
        waitForPageToLoad();
        return getElement(responderCompanyId);
    }

    public WebElement getRequesterCompanyId() throws Exception {
        waitForPageToLoad();
        return getElement(requesterCompanyId);
    }

    public WebElement getDefaultResponderCompanyId() throws Exception {
        waitForPageToLoad();
        return getElement(defaultResponderCompanyId);
    }

    public WebElement getDefaultRequesterCompanyId() throws Exception {
        waitForPageToLoad();
        return getElement(defaultRequesterCompanyId);
    }
    public WebElement getBlockChainEntryLink() throws Exception {
        waitForPageToLoad();
        return getElement(blockChainEntryLink);
    }

    public WebElement getBlockChainNetworkElement() throws Exception {
        waitForPageToLoad();
        return getElement(blockChainNetworkText);
    }
    public WebElement getBlockChainStreamElement() throws Exception {
        waitForPageToLoad();
        return getElement(blockChainStreamText);
    }
    public List<WebElement> getStatusCodeList() throws Exception {
        waitForPageToLoad();
        return getElements(statusCodeList);
    }
    public void clickStatusCodeButton() throws Exception {
        waitForPageToLoad();
        clickElement(statusCodeId);
    }
    public WebElement getRepositoryExplorerLink() throws Exception {
        waitForPageToLoad();
        return getElement(repositoryLinkId);
    }

//
//    public void analyzeLog() {
////        DesiredCapabilities capabilities = new DesiredCapabilities();
////        BrowserMobProxy proxy = getProxyServer(); //getting browsermob proxy
////        Proxy seleniumProxy = getSeleniumProxy(proxy);
////        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
////        proxy.newHar();
////        List<HarEntry> entries = proxy.getHar().getLog().getEntries();
////        for (HarEntry entry : entries) {
////            System.out.println(entry.getRequest().getUrl());
////
////        }
//        LogEntries logEntries = portalDriver.getConfig().getDriver().manage().logs().get(LogType.BROWSER);
//        for (LogEntry entry : logEntries) {
//            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage() + "  " + entry.toString());
//            //do something useful with the data
//        }
//
//    }
//
//    public BrowserMobProxy getProxyServer() {
//        BrowserMobProxy proxy = new BrowserMobProxyServer();
//        proxy.setTrustAllServers(true);
//// above line is needed for application with invalid certificates
//        proxy.start();
//        return proxy;
//    }

//    public Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
//        try {
//            String hostIp = Inet4Address.getLocalHost().getHostAddress();
//            seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
//            seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            Assert.fail("invalid Host Address");
//        }
//        return seleniumProxy;
//    }
//
//    public static void main(String[] args) {
//
//        String path="C:\\ICH_USSC_Auto\\test-automation\\selenium\\feature_tests\\src\\test\\resources\\chromedriver.exe";
//        System. setProperty("webdriver.chrome.driver", path);
//       WebDriver driver= new ChromeDriver();
    //      driver.get("https://ich-pbc-qa-pbc-launchpad.cfapps.sap.hana.ondemand.com/sites#analytics-show");

    //   }

}


