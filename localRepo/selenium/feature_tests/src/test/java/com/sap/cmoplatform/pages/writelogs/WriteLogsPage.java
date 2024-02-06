package com.sap.cmoplatform.pages.writelogs;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.objects.WriteLog;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WriteLogsPage extends Page {
    private static final Properties writeLogPageIds = PropertyReader.loadProperties("writelog/WriteLogPageIds.properties");
    private static final String prefix = writeLogPageIds.getProperty("writelog.home.pagePrefix");
    private static final String messageIdInput = prefix + writeLogPageIds.get("writelog.home.messageId");
    private static final String dispositionIdInput = prefix + writeLogPageIds.get("writelog.home.dispositionId");
    private static final String gtinIdInput = prefix + writeLogPageIds.get("writelog.home.gtinId");
    private static final String lotIdInput = prefix + writeLogPageIds.get("writelog.home.lotId");
    private final String eventTimeId = prefix + writeLogPageIds.get("writelog.home.eventTimeId");
    private final String messageTimeId = prefix + writeLogPageIds.get("writelog.home.messageTimeId");
    private final String requestTableId = prefix + writeLogPageIds.get("writelog.home.requestTableId");
    private final String btnShowHide = prefix + writeLogPageIds.get("writelog.home.btnShowHide");
    private final String tableHeader = prefix + writeLogPageIds.get("writelog.home.tableHeader");
    private final String btnFilters = prefix + writeLogPageIds.get("writelog.home.btnFilters");
    private final String btnSave = prefix + writeLogPageIds.get("writelog.home.btnSave");
    private final String btnGoFilter = prefix + writeLogPageIds.get("writelog.home.btnGoFilter");
    public String saveViewPopUpHeader = prefix + writeLogPageIds.get("writelog.home.saveViewPopUpHeader");
    private final String saveViewCancelBtn = prefix + writeLogPageIds.get("writelog.home.saveViewCancelBtn");
    private final String saveViewSaveBtn = prefix + writeLogPageIds.get("writelog.home.saveViewSaveBtn");
    private final String saveViewName = prefix + writeLogPageIds.get("writelog.home.saveViewName");
    private final String defaultBtn = prefix + writeLogPageIds.get("writelog.home.defaultBtn");
    private final String noDataText = prefix + writeLogPageIds.get("writelog.home.noDataText");
    private final By btnMessageIdRadio = By.xpath("//div[text()='Message ID']/preceding::input[1]/parent::div");
    private final By btnMessageTimeRadio = By.xpath("//div[text()='Message Time']/preceding::input[1]/parent::div");
    private final By btnEventTimeRadio = By.xpath("//div[text()='Event Time']/preceding::input[1]/parent::div");
    private final By btnDispositionRadio = By.xpath("//div[text()='Disposition']/preceding::input[1]/parent::div");
    private final By btnGtinRadio = By.xpath("//div[text()='GTIN']/preceding::input[1]/parent::div");
    private final By btnLotRadio = By.xpath("//div[text()='Lot/Batch Number']/preceding::input[1]/parent::div");
    private final By lotColumn = By.xpath("//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--lotColumn']/span");
    private final By gtinColumn = By.xpath("//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--gtinColumn']/span");
    private final By dispositionColumn = By.xpath("//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--dispositionColumn']/span");
    private final By messageTimeColumn = By.xpath("//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--messageTimeColumn']/span");
    private final By eventTimeColumn = By.xpath("//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--eventTimeColumn']/span");
    private final By messageIDColumn = By.xpath("//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--messageIDColumn']/span");
    private final By statusColumn = By.xpath("//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--statusColumn']/span");
    private final By writtenEpcColumn = By.xpath("//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--sumepcsColumn']/span");
    private final By queuedEpcColumn = By.xpath("//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--capturedsumepcsColumn']/span");
    private final By btnDescendingOrder = By.xpath("//div[text()='Descending']/preceding::input[1]/parent::div");
    //private By blockChainStreamLink = By.xpath("//span[@aria-label='Blockchain Stream']/following::div[1]/a");
    private final By repositoryExplorerink = By.xpath("//button[@title='Open Repository Explorer']/span/span/bdi");
    private final String selectViewDropDown = prefix + writeLogPageIds.get("writelog.home.selectViewDropDown");
    private final String manageViewButton = prefix + writeLogPageIds.get("writelog.home.manageViewButton");
    private final String manageSaveButton = prefix + writeLogPageIds.get("writelog.home.manageSaveButton");
    private final String manageCancelButton = prefix + writeLogPageIds.get("writelog.home.manageCancelButton");
    private final By duplicateSaveViewFileError = By.xpath("//div[@class='sapMInputBaseContentWrapper sapMInputBaseContentWrapperState sapMInputBaseContentWrapperError']");
    private final By writeLogSubHeader = By.xpath("//*[contains(@aria-label,'Write Log')]/descendant::span[contains(@id,'subTitle')]");
    private final By recordCount = By.xpath("//*[contains(@aria-label,'Write Log')]/descendant::div[@class='sapMTileCntContent']/..//span[2]");
    public By clearFilter = By.xpath("//span[contains(@id,'listView-innerListViewTable-clearSelection')]");
    public By viewSearchInput = By.xpath("//input[@type='search']");
    public By defaultViewChckBox = By.xpath("//div[contains(@id,'application-analyticsWrite-show-component---worklist--filterbar-variantUi2-manage-def')]");

    Actions actions = new Actions(SeleniumUI5TestUtil.getConfig().getDriver());

    public enum filterField {
        GTIN("GTIN", gtinIdInput),
        lot("lot", lotIdInput),
        disposition("disposition", dispositionIdInput),
        messageID("messageIdInput", messageIdInput);
        public String filterInputID;
        public String filter;

        filterField(String filter, String filterInputID) {
            this.filter = filter;
            this.filterInputID = filterInputID;
        }

    }

    public void selectFilterField(filterField filterField, String value) throws Exception {
        waitForPageToLoad();
        getElement(filterField.filterInputID).clear();
        inputText(filterField.filterInputID, value);
    }

    public void enterMessageId(String message) throws Exception {
        waitForPageToLoad();
        getElement(messageIdInput).clear();
        inputText(messageIdInput, message);
    }

    public void enterLot(String lot) throws Exception {
        waitForPageToLoad();
        getElement(lotIdInput).clear();
        inputText(lotIdInput, lot);
    }

    public void clearGtinValue() throws Exception {
        waitForPageToLoad();
        getElement(gtinIdInput).clear();
    }

    public void clearLotValue() throws Exception {
        waitForPageToLoad();
        getElement(lotIdInput).clear();
    }

    public void clearMessageIdValue() throws Exception {
        waitForPageToLoad();
        getElement(messageIdInput).clear();
    }

    public void clearDispositionValue() throws Exception {
        waitForPageToLoad();
        getElement(dispositionIdInput).clear();
    }

    public void clearMessageTimeValue() throws Exception {
        waitForPageToLoad();
        getElement(messageTimeId).clear();
    }

    public void clearEventTimeValue() throws Exception {
        waitForPageToLoad();
        getElement(eventTimeId).clear();
    }

    public void enterDisposition(String disposition) throws Exception {
        waitForPageToLoad();
        getElement(dispositionIdInput).clear();
        inputText(dispositionIdInput, disposition);
    }

    public void enterMessageTime(String messageTime) throws Exception {
        waitForPageToLoad();
        getElement(messageTimeId).clear();
        inputText(messageTimeId, messageTime);
        getElement(messageIdInput).click();
        String text = getElement(messageTimeId).getAttribute("value");
        inputText(messageTimeId, "-" + text);

    }

    public void enterEventTime(String eventTime) throws Exception {
        waitForPageToLoad();
        getElement(eventTimeId).clear();
        inputText(eventTimeId, eventTime);
        getElement(dispositionIdInput).click();
        String text = getElement(eventTimeId).getAttribute("value");
        inputText(eventTimeId, "-" + text);
    }

    public void clickShowHideFilterBarButton() throws Exception {
        waitForPageToLoad();
        clickElement(btnShowHide);
    }

    public List<WriteLog> getWriteLogs() throws Exception {
        waitForTableToLoad();
        List<WriteLog> logs = new ArrayList<>();
        List<WebElement> tableData = getTableRows(By.id(requestTableId));
        int count = tableData.size();
        for (int i = 0; i < count; i++) {
            WebElement row = getTableRows(By.id(requestTableId)).get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            WriteLog log = new WriteLog()
                    .withMessageID(cells.get(1).getText())
                    .withMessageTime(cells.get(2).getText())
                    .withEventTime(cells.get(3).getText())
                    .withDisposition(cells.get(4).getText())
                    .withGtin(cells.get(5).getText())
                    .withLot(cells.get(6).getText())
                    .withExpirationDate(cells.get(7).getText())
                    .withWrittenEpc(cells.get(8).getText())
                    .withQueuedEpc(cells.get(9).getText())
                    .withStatus(cells.get(10).getText());
            logs.add(log);
        }
        return logs;
    }

    public int getNumberOfRecords() throws Exception {
        waitForTableToLoad();
        List<WriteLog> logs = new ArrayList<>();
        List<WebElement> tableData = getTableRows(By.id(requestTableId));
        return tableData.size();
    }

    public String getTableHeader() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        return getElement(tableHeader).getText();
    }


    public String getResponseDateFromWriteLogTable(String date) {
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

    public void clickBtnLotRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnLotRadio);
    }

    public void clickBtnGtinRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnGtinRadio);
    }

    public void clickBtnMessageIdRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnMessageIdRadio);
    }

    public void clickBtnMessageTimeRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnMessageTimeRadio);
    }

    public void clickBtnEventTimeRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnEventTimeRadio);
    }

    public void clickBtnDispositionRadio() throws Exception {
        waitForPageToLoad();
        clickElement(btnDispositionRadio);
    }

    public String[] getLotColumnValues() throws Exception {
        waitForPageToLoad();
        int count = getElements(lotColumn).size();
        String[] lotValue = new String[count];
        for (int i = 0; i < count; i++) {
            lotValue[i] = getElement(By.xpath("(//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--lotColumn']/span)[" + (i + 1) + "]")).getText();

        }
        return lotValue;
    }

    public String[] getGtinColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(gtinColumn).size();
        String[] gtinValue = new String[count];
        for (int i = 0; i < count; i++) {
            gtinValue[i] =   getElement(By.xpath("(//*[contains(@data-sap-ui-column,'gtinColumn')]/span)[" + (i + 1) + "]")).getText();

        }
        return gtinValue;
    }

    public String[] getDispositionColumnValues() throws Exception {
        waitForPageToLoad();
        int count = getElements(dispositionColumn).size();
        String[] dispositionValue = new String[count];
        for (int i = 0; i < count; i++) {
            dispositionValue[i] = getElement(By.xpath("(//*[contains(@data-sap-ui-column,'dispositionColumn')]/span)[" + (i + 1) + "]")).getText();
        }
        return dispositionValue;
    }

    public String[] getMessageIDColumnValues() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = getElements(messageIDColumn).size();
        String[] messageIDValue = new String[count];
        for (int i = 0; i < count; i++) {
            messageIDValue[i]=getElement(By.xpath("(//*[contains(@data-sap-ui-column,'messageIDColumn')]/span)[" + (i + 1) + "]")).getText();
        }
        return messageIDValue;
    }


    public String[] getMessageTimeColumnValues() throws Exception {
        waitForPageToLoad();
        int count = getElements(messageTimeColumn).size();
        String[] messageTime = new String[count];
        String[] parsedMessageTime = new String[count];
        for (int i = 0; i < count; i++) {
            messageTime[i] = getElements(messageTimeColumn).get(i).getText();
            //  messageTime[i] = getElement(By.xpath("(//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--messageTimeColumn']/span)[" + (i + 1) + "]")).getText();
        }
//        for (int i = 0; i < count; i++) {
//            String[] reqDate = messageTime[i].split(", ");
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
//            parsedMessageTime[i] = year + outputFormat.format(cal.getTime()) + dd;
//        }
        for (int i = 0; i < count; i++) {
            String[] reqDate = messageTime[i].split("T");
            parsedMessageTime[i] = reqDate[0].replace("-", "");
        }
        return parsedMessageTime;
    }

//    public String[] getEventTimeColumnValues() throws Exception {
//        waitForPageToLoad();
//        int count = getElements(eventTimeColumn).size();
//        String[] eventTimeValues = new String[count];
//        for (int i = 0; i < count; i++) {
//            eventTimeValues[i] = getElement(By.xpath("(//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--eventTimeColumn']/span)[" + (i + 1) + "]")).getText();
//        }
//        return eventTimeValues;
//    }

    public String[] getEventTimeColumnValues() throws Exception {
        waitForPageToLoad();
        int count = getElements(eventTimeColumn).size();
        String[] eventTimeValues = new String[count];
        String[] parsedEventTime = new String[count];

        for (int i = 0; i < count; i++) {
            //   eventTimeValues[i] = getElement(By.xpath("(//td[@data-sap-ui-column='application-analyticsWrite-show-component---worklist--eventTimeColumn']/span)[" + (i + 1) + "]")).getText();
            eventTimeValues[i] = getElements(eventTimeColumn).get(i).getText();

        }
//        for (int i = 0; i < count; i++) {
//            String[] reqDate = eventTimeValues[i].split(", ");
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
//            parsedEventTime[i] = year + outputFormat.format(cal.getTime()) + dd;
//        }
        for (int i = 0; i < count; i++) {
            String[] reqDate = eventTimeValues[i].split("T");
            parsedEventTime[i] = reqDate[0].replace("-", "");
        }
        return parsedEventTime;
    }

    public void clickBtnDescendingOrder() throws Exception {
        waitForPageToLoad();
        clickElement(btnDescendingOrder);
    }

    public void clickBtnFilters() throws Exception {
        waitForPageToLoad();
        clickElement(btnFilters);
    }

    public void clickSaveButton() throws Exception {
        waitForPageToLoad();
        clickElement(btnSave);
    }

    public void clickFilterPopUpGoButton() throws Exception {
        waitForPageToLoad();
        clickElement(btnGoFilter);
    }

    public WebElement getMessageId() throws Exception {
        waitForPageToLoad();
        return getElement(messageIdInput);
    }

    public WebElement getMessageTime() throws Exception {
        waitForPageToLoad();
        return getElement(messageTimeId);
    }

    public WebElement getEventTime() throws Exception {
        waitForPageToLoad();
        return getElement(eventTimeId);
    }

    public WebElement getDisposition() throws Exception {
        waitForPageToLoad();
        return getElement(dispositionIdInput);
    }

    public WebElement getGtin() throws Exception {
        waitForPageToLoad();
        return getElement(gtinIdInput);
    }

    public WebElement getLot() throws Exception {
        waitForPageToLoad();
        return getElement(lotIdInput);
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

    public void clickFirstRecord() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(messageIDColumn).click();
    }

    public void clickRepositoryExplorerLink() throws Exception {
        waitForPageToLoad();
        getElement(repositoryExplorerink).click();
    }

    public WebElement getRepositoryExplorerLinkId() throws Exception {
        waitForPageToLoad();
        return getElement(repositoryExplorerink);
    }

    public void clickOnRecord(String value) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElement(By.xpath("//td/span[text()='" + value + "']")).click();
    }

    public WebElement getNoDataTextElement() throws Exception {
        waitForPageToLoad();
        return getElement(noDataText);
    }

    public void clickSelectViewDropDown() throws Exception {
        getElement(selectViewDropDown).click();
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

    public void selectAvailableView(String fileName) throws Exception {
        waitForPageToLoad();
        actions.moveToElement(getElement(By.xpath("//li[text()='" + fileName + "']"))).click().perform();
    }

    public WebElement getErrorMessageElement() throws Exception {
        waitForPageToLoad();
        return getElement(duplicateSaveViewFileError);
    }

    public void clickSaveViewCancelButton() throws Exception {
        getElement(saveViewCancelBtn).click();
    }
    public String getWriteLogSubHeader() throws Exception {
        waitForPageToLoad();
        return getElement(writeLogSubHeader).getText();
    }

    public String getRecordCount() throws Exception {
        waitForPageToLoad();
        return getElement(recordCount).getText();
    }
}