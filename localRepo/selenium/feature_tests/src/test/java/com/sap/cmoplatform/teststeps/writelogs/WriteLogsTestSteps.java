package com.sap.cmoplatform.teststeps.writelogs;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.CommonComponentPage;
import com.sap.cmoplatform.pages.LauncherPage;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.pages.writelogs.WriteLogsPage;
import com.sap.cmoplatform.objects.WriteLog;
import com.sap.cmoplatform.utils.PortalDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WriteLogsTestSteps {

    private WriteLogsPage writeLogsPage = new WriteLogsPage();
    private LauncherPage launcherPage = new LauncherPage();
    private CommonComponentPage commonComponentPage = new CommonComponentPage();

    private static final String GTIN = "gtin";
    private static final String LOT = "lot";
    private static final String MESSAGEID = "MessageId";
    private static final String DISPOSITION = "Disposition";
    private static final String MESSAGETIME = "MessageTime";
    private static final String EVENTTIME = "EventTime";
    private static String gtin;
    private static String lot;
    private static String messageId;
    private static String disposition;
    private static String messageTime;
    private static String eventTime;
    private static final String STATUS = "Status";
    private static final String WRITTENEPC = "WrittenEpc";
    private static final String QUEUEDEPC = "QueuedEpc";
    String recordCount, tableRecordCount;


    @Then("^the title of the Write log table is '(.+)'$")
    public void theTitleOfTheWriteLogTableIsTitle(String title) throws Exception {
        assertTrue(writeLogsPage.getTableHeader().contains(title));
    }


    @And("^set '(.+)' filter value '(.+)' in write logs page$")
    public void setFilterValueGtinInWriteLogsPage(String filterField, String value) throws Exception {
        writeLogsPage.waitForPageToLoad();
        writeLogsPage.selectFilterField(WriteLogsPage.filterField.valueOf(filterField), value);
    }

    @And("^set '(.+)' filter value in write logs page$")
    public void setFilterValueGtinInWriteLogsPage(String field) throws Exception {
        writeLogsPage.waitForPageToLoad();
        if (field.equals("GTIN")) {
            writeLogsPage.selectFilterField(WriteLogsPage.filterField.valueOf(field), gtin);
        }
        if (field.equals("lot")) {
            writeLogsPage.selectFilterField(WriteLogsPage.filterField.valueOf(field), lot);
        }
        if (field.equals("disposition")) {
            writeLogsPage.selectFilterField(WriteLogsPage.filterField.valueOf(field), disposition);
        }
        if (field.equals("messageID")) {
            writeLogsPage.selectFilterField(WriteLogsPage.filterField.valueOf(field), messageId);
        }
        if (field.equals("MessageTime")) {
            writeLogsPage.enterMessageTime(StringUtils.substringBefore(messageTime, "T"));
            ;
        }
        if (field.equals("EventTime")) {
            writeLogsPage.enterEventTime(StringUtils.substringBefore(eventTime, "T"));
        }
    }

    @And("^get data for filters$")
    public void getData() throws Exception {
        writeLogsPage.waitForPageToLoad();
        writeLogsPage.waitForTableToLoad();
        List<WriteLog> actualLog = writeLogsPage.getWriteLogs();
        gtin = actualLog.get(0).getGtin();
        lot = actualLog.get(0).getLot();
        disposition = actualLog.get(0).getDisposition();
        messageId = actualLog.get(0).getMessageId();
        messageTime = actualLog.get(0).getMessageTime();
        eventTime = actualLog.get(0).getEventTime();
    }

    @Then("the write log results contains the filtered values in the response")
    public void theWriteLogResultsContainsTheFilteredValuesInTheResponse(Map<String, String> expectedLogDetails) throws Exception {
        writeLogsPage.waitForPageToLoad();
        List<WriteLog> actualLog = writeLogsPage.getWriteLogs();
        for (WriteLog log : actualLog) {
            if (expectedLogDetails.containsKey(GTIN)) {
                assertThat(log.getGtin(), is(expectedLogDetails.get(GTIN)));
            }
            if (expectedLogDetails.containsKey(LOT)) {
                assertThat(log.getLot(), is(expectedLogDetails.get(GTIN)));
            }
            if (expectedLogDetails.containsKey(MESSAGEID)) {
                assertThat(log.getMessageId(), is(expectedLogDetails.get(MESSAGEID)));
            }
            if (expectedLogDetails.containsKey(DISPOSITION)) {
                if (disposition == null)
                    assertTrue(log.getDisposition().contains(expectedLogDetails.get(DISPOSITION)));
                else
                    assertTrue(log.getDisposition().contains(disposition));
            }
            if (expectedLogDetails.containsKey(MESSAGETIME)) {
                // assertThat(writeLogsPage.getResponseDateFromWriteLogTable(log.getMessageTime()), is(writeLogsPage.getRequestDateFromFilterSection(expectedLogDetails.get(MESSAGETIME))));
                assertThat(writeLogsPage.getResponseDateFromWriteLogTable(log.getMessageTime()), is(expectedLogDetails.get(MESSAGETIME)));

            }
            if (expectedLogDetails.containsKey(EVENTTIME)) {
                //  assertThat(writeLogsPage.getResponseDateFromWriteLogTable(log.getEventTime()), is(writeLogsPage.getRequestDateFromFilterSection(expectedLogDetails.get(EVENTTIME))));
                if (eventTime == null)
                    assertTrue(writeLogsPage.getResponseDateFromWriteLogTable(log.getEventTime()).contains(expectedLogDetails.get(EVENTTIME)));
                else
                    assertTrue(writeLogsPage.getResponseDateFromWriteLogTable(log.getEventTime()).contains(eventTime));
            }
            if (expectedLogDetails.containsKey(STATUS)) {
                assertThat(log.getStatus(), is(expectedLogDetails.get(STATUS)));
            }
            if (expectedLogDetails.containsKey(WRITTENEPC) && expectedLogDetails.containsKey(QUEUEDEPC)) {
                assertTrue(Integer.parseInt(log.getQueuedEpc().replaceAll(",", "")) >= Integer.parseInt(log.getWrittenEpc().replaceAll(",", "")));
                assertTrue(log.getQueuedEpc().matches("[0-9, /,]+"));
                assertTrue(log.getWrittenEpc().matches("[0-9, /,]+"));

            }

        }
    }

    @Then("the write log results contains the filtered values in the results")
    public void theWriteLogResultsContainsTheFilteredValuesInTheresults(Map<String, String> expectedLogDetails) throws Exception {
        writeLogsPage.waitForPageToLoad();
        List<WriteLog> actualLog = writeLogsPage.getWriteLogs();
        for (WriteLog log : actualLog) {
            if (expectedLogDetails.containsKey(GTIN)) {
                assertTrue(log.getGtin().contains(gtin));
            }
            if (expectedLogDetails.containsKey(LOT)) {
                assertTrue(log.getLot().contains(lot));
            }
            if (expectedLogDetails.containsKey(MESSAGEID)) {
                assertTrue(log.getMessageId().contains(messageId));
            }
            if (expectedLogDetails.containsKey(DISPOSITION)) {
                assertTrue(log.getDisposition().contains(disposition));
            }
            if (expectedLogDetails.containsKey(MESSAGETIME)) {
                assertTrue(log.getMessageTime().contains(messageTime));
            }
            if (expectedLogDetails.containsKey(EVENTTIME)) {
                assertTrue(log.getEventTime().contains(eventTime));
            }
        }
    }

    @And("^set filter value lot '(.+)' in write logs page$")
    public void setFilterValueLotLOTInWriteLogsPage(String lot) throws Exception {
        writeLogsPage.enterLot(lot);
    }

    @And("^clear filter (gtin|lot|messageId|disposition|MessageTime|EventTime) value$")
    public void clearFilterValue(String filter) throws Exception {
        if (filter.equals("gtin")) {
            writeLogsPage.clearGtinValue();
        }
        if (filter.equals("lot")) {
            writeLogsPage.clearLotValue();
        }
        if (filter.equals("messageId")) {
            writeLogsPage.clearMessageIdValue();
        }
        if (filter.equals("disposition")) {
            writeLogsPage.clearDispositionValue();
        }
        if (filter.equals("MessageTime")) {
            writeLogsPage.clearMessageTimeValue();
        }
        if (filter.equals("EventTime")) {
            writeLogsPage.clearEventTimeValue();
        }
    }

    @And("^set filter value messageId '(.+)' in write logs page$")
    public void setFilterValueMessageIdMessageIdInWriteLogsPage(String messageId) throws Exception {
        writeLogsPage.enterMessageId(messageId);
    }

    @And("^set filter value disposition '(.+)' in write logs page$")
    public void setFilterValueDispositionDispositionInWriteLogsPage(String disposition) throws Exception {
        writeLogsPage.enterDisposition(disposition);
    }

    @And("^set filter value MessageTime '(.+)' in write logs page$")
    public void setFilterValueMessageTimeMessageTimeInWriteLogsPage(String messageTime) throws Exception {
        writeLogsPage.enterMessageTime(messageTime);
    }

    @And("^set filter value EventTime '(.+)' in write logs page$")
    public void setFilterValueEventTimeEventTimeInWriteLogsPage(String eventTime) throws Exception {
        writeLogsPage.enterEventTime(eventTime);
    }

    @And("^the sort option (LOT|GTIN|MessageId|MessageTime|EventTime|Disposition) is selected in write log page$")
    public void theSortOptionIsSelectedInWriteLogPage(String option) throws Exception {
        if (option.equals("LOT")) {
            writeLogsPage.clickBtnLotRadio();
        }
        if (option.equals("GTIN")) {
            writeLogsPage.clickBtnGtinRadio();
        }
        if (option.equals("MessageId")) {
            writeLogsPage.clickBtnMessageIdRadio();
        }
        if (option.equals("MessageTime")) {
            writeLogsPage.clickBtnMessageTimeRadio();
        }
        if (option.equals("EventTime")) {
            writeLogsPage.clickBtnEventTimeRadio();
        }
        if (option.equals("Disposition")) {
            writeLogsPage.clickBtnDispositionRadio();
        }
    }

    @Then("^the (GTIN|LOT|MessageId|MessageTime|EventTime|Disposition) column is sorted in ascending order in write log page$")
    public void theColumnIsSortedInAscendingOrderInWriteLogPage(String columnName) throws Exception {
        writeLogsPage.waitForTableToLoad();
        if (columnName.equals("LOT")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(writeLogsPage.getLotColumnValues()));
        }
        if (columnName.equals("GTIN")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(writeLogsPage.getGtinColumnValues()));
        }
        if (columnName.equals("Disposition")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(writeLogsPage.getDispositionColumnValues()));
        }
        if (columnName.equals("MessageId")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(writeLogsPage.getMessageIDColumnValues()));
        }
        if (columnName.equals("MessageTime")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(writeLogsPage.getMessageTimeColumnValues()));
        }
        if (columnName.equals("EventTime")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(writeLogsPage.getEventTimeColumnValues()));
        }
    }

    @And("the radio button descending order is clicked in write log page")
    public void theRadioButtonDescendingOrderIsClickedInWriteLogPage() throws Exception {
        writeLogsPage.clickBtnDescendingOrder();
    }

    @Then("^the (GTIN|LOT|MessageId|MessageTime|EventTime|Disposition) column is sorted in descending order in write log page")
    public void theColumnIsSortedInDescendingOrderInWriteLogPage(String columnName) throws Exception {
        commonComponentPage.waitForPageToLoad();
        if (columnName.equals("LOT")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(writeLogsPage.getLotColumnValues()));
        }
        if (columnName.equals("GTIN")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(writeLogsPage.getGtinColumnValues()));
        }
        if (columnName.equals("MessageId")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(writeLogsPage.getMessageIDColumnValues()));
        }
        if (columnName.equals("MessageTime")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(writeLogsPage.getMessageTimeColumnValues()));
        }
        if (columnName.equals("EventTime")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(writeLogsPage.getEventTimeColumnValues()));
        }
        if (columnName.equals("Disposition")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(writeLogsPage.getDispositionColumnValues()));
        }
    }


    @And("the filters button is clicked in the write log page")
    public void theFiltersButtonIsClickedInTheWriteLogPage() throws Exception {
        writeLogsPage.waitForPageToLoad();
        writeLogsPage.clickBtnFilters();
    }

//    @And("filter component is selected in write log app")
//    public void filterComponentIsSelectedInWriteLogApp(List<String> filterComponent) throws Exception {
//        commonComponentPage.unSelectAllSelection();
//        for (int i = 0; i <= filterComponent.size() - 1; i++) {
//            if (filterComponent.get(i).equals("MessageId")) {
//                launcherPage.selectFilterComponent(0);
//            } else if (filterComponent.get(i).equals("MessageTime")) {
//                launcherPage.selectFilterComponent(1);
//            } else if (filterComponent.get(i).equals("EventTime")) {
//                launcherPage.selectFilterComponent(2);
//            } else if (filterComponent.get(i).equals("Disposition")) {
//                launcherPage.selectFilterComponent(3);
//            } else if (filterComponent.get(i).equals("GTIN")) {
//                launcherPage.selectFilterComponent(4);
//            } else if (filterComponent.get(i).equals("LOT")) {
//                launcherPage.selectFilterComponent(5);
//            }
//        }
//    }

    @And("^Save view '(.+)' for filter and Save button is clicked in filter modal pop up in writelog page$")
    public void saveViewViewNameForFilterAndSaveButtonIsClickedInFilterModalPopUpInWriteLogPage(String viewName) throws Exception {
        commonComponentPage.clickSelectViewDropDown();
        if (writeLogsPage.getElements(By.xpath("//*[text()='Save']")).size() > 0) {
            writeLogsPage.clickButton("Save");
        } else {
            writeLogsPage.clickButton("Save As");
        }
        writeLogsPage.waitForPageToLoad();
        PortalDriver.getInstance();
        WebDriverWait wait = new WebDriverWait(SeleniumUI5TestUtil.getConfig().getDriver(), 10);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'" + writeLogsPage.saveViewPopUpHeader + "')]")));
            writeLogsPage.enterSaveViewName(viewName);
            writeLogsPage.clickDefaultButton();
            writeLogsPage.clickSaveViewSaveBtn();
            writeLogsPage.clickSaveButton();
        } catch (Exception ignored) {
        }
    }

    @And("Go button is clicked in filter modal pop up in write log app")
    public void goButtonIsClickedInFilterModalPopUpInWriteLogApp() throws Exception {
        writeLogsPage.clickFilterPopUpGoButton();
    }

    @And("Show Filter Bar button is clicked in write log app")
    public void showFilterBarButtonIsClickedInWriteLogApp() throws Exception {
        writeLogsPage.clickShowHideFilterBarButton();
    }

    @Then("verify selected filter components are available in write log app")
    public void verifySelectedFilterComponentsAreAvailableInWriteLogApp(List<String> filterComponent) throws Exception {
        for (String component : filterComponent) {
            if (component.equals("MessageId")) {
                assertTrue(writeLogsPage.getMessageId().isDisplayed());
            }
            if (component.equals("GTIN")) {
                assertTrue(writeLogsPage.getGtin().isDisplayed());
            }
            if (component.equals("LOT")) {
                assertTrue(writeLogsPage.getLot().isDisplayed());
            }
            if (component.equals("MessageTime")) {
                assertTrue(writeLogsPage.getMessageTime().isDisplayed());
            }
            if (component.equals("EventTime")) {
                assertTrue(writeLogsPage.getEventTime().isDisplayed());
            }
            if (component.equals("Disposition")) {
                assertTrue(writeLogsPage.getDisposition().isDisplayed());
            }
        }
    }


    @Then("verify the maximum allowed length")
    public void verifyTheMaximumAllowedLength(Map<String, String> inputValue) throws Exception {
        if (inputValue.containsKey("GTIN")) {
            assertTrue(writeLogsPage.getGtin().getAttribute("value").length() == 14);
        }
        if (inputValue.containsKey("LOT")) {
            assertTrue(writeLogsPage.getLot().getAttribute("value").length() == 20);
        }
        if (inputValue.containsKey("Disposition")) {
            assertTrue(writeLogsPage.getDisposition().getAttribute("value").length() == 100);
        }

    }


    @And("click on first record of write log")
    public void clickOnFirstRecordOfWriteLog() throws Exception {
        writeLogsPage.clickFirstRecord();
    }

    @And("click on repository explorer link in Write Log")
    public void clickOnRepositoryExplorerLinkInWritelog() throws Exception {
        writeLogsPage.clickRepositoryExplorerLink();
    }

    @And("clear all filter component fields in write logs page")
    public void clearAllFilterComponentFieldsInWriteLogsPage() throws Exception {
        writeLogsPage.getElement(writeLogsPage.clearFilter).click();
    }


    @And("verify default value for filter component in write log page")
    public void verifyDefaultValueForFilterComponentInWriteLogPage(Map<String, String> filterComponent) throws Exception {
        if (filterComponent.containsKey("MessageId")) {
            assertEquals(writeLogsPage.getMessageId().getAttribute("value"), filterComponent.get("MessageId"));
        }
        if (filterComponent.containsKey("MessageTime")) {
            assertEquals(writeLogsPage.getMessageTime().getAttribute("value"), filterComponent.get("MessageTime"));
        }
        if (filterComponent.containsKey("EventTime")) {
            assertEquals(writeLogsPage.getEventTime().getAttribute("value"), eventTime);
        }
        if (filterComponent.containsKey("Disposition")) {
            assertEquals(writeLogsPage.getDisposition().getAttribute("value"), disposition);
        }
        if (filterComponent.containsKey("GTIN")) {
            assertEquals(writeLogsPage.getGtin().getAttribute("value"), filterComponent.get("GTIN"));
        }
        if (filterComponent.containsKey("LOT")) {
            assertEquals(writeLogsPage.getLot().getAttribute("value"), filterComponent.get("LOT"));
        }

    }

    @Then("^the results contains '(.+)' records$")
    public void theResultsContainsNumberOfRecordsRecords(String numberOfRecords) throws Exception {
        assertEquals(writeLogsPage.getNumberOfRecords() + "", numberOfRecords);
    }

    @And("click on record of write log")
    public void clickOnRecordOfWriteLog(Map<String, String> expectedValue) throws Exception {
        if (expectedValue.containsKey("ExpirationDate")) {
            writeLogsPage.clickOnRecord(expectedValue.get("ExpirationDate"));
        }
        if (expectedValue.containsKey("Disposition")) {
            writeLogsPage.clickOnRecord(expectedValue.get("Disposition"));
        }
    }

    @Then("No records found for the filtered values")
    public void noRecordsFoundForTheFilteredValues() throws Exception {
        writeLogsPage.getNoDataTextElement().isDisplayed();
    }


    @Then("the write log record count is displayed")
    public void theWriteLogRecordCountIsDisplayed() throws Exception {
        String str = writeLogsPage.getTableHeader();
        assertTrue(Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(")"))) > 0);
    }

    @Then("^the error message '(.+)' is displayed in write log app$")
    public void theErrorMessageErrorMessageIsDisplayedInWriteLogApp(String message) throws Exception {
        assertEquals(writeLogsPage.getErrorMessage(), message);
    }

    @Then("^the app header contains '(.+)' and '(.+)'$")
    public void theAppHeaderContainsAppNameAndHeader(String appName, String text) throws Exception {
        launcherPage.switchToDefaultContent();
        System.out.println(writeLogsPage.getWriteLogSubHeader());
        assertEquals(writeLogsPage.getWriteLogSubHeader(), text);
        assertTrue(commonComponentPage.getAppNameElement(appName).isDisplayed());

    }

    @And("get the record count from the app")
    public void getTheRecordCountFromTheApp() throws Exception {
        recordCount = writeLogsPage.getRecordCount();
    }

    @Then("validate the write log record count")
    public void validateTheWriteLogRecordCount() throws Exception {
        if (Integer.parseInt(recordCount) > 0) {
            String str = writeLogsPage.getTableHeader();
            assertEquals(str.substring(str.indexOf("(") + 1, str.indexOf(")")), recordCount);
        }
    }

    @Then("^the download error message '(.+)' is displayed$")
    public void theDownloadErrorMessageErrorMessageIsDisplayed(String errorMessage) throws Exception {
        if (Integer.parseInt(tableRecordCount) > 100000) {
            assertEquals(commonComponentPage.getDownloadErrorMessage(), errorMessage);
        }
    }

    @And("get the record count from the table")
    public void getTheRecordCountFromTheTable() throws Exception {

        String str = writeLogsPage.getTableHeader();
        tableRecordCount = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
    }

    @And("{string} is set as default view")
    public void isSetAsDeafultView(String viewName) throws Exception {
        writeLogsPage.clickSelectViewDropDown();
        writeLogsPage.clickButton("Manage");
        writeLogsPage.inputText(writeLogsPage.viewSearchInput, viewName);
        writeLogsPage.clickElement(writeLogsPage.defaultViewChckBox);
        writeLogsPage.clickButton("Save");
    }

    @And("clear all filter values in write log page")
    public void clearAllFilterValuesInWriteLogPage() throws Exception {
        writeLogsPage.waitForTableToLoad();
        writeLogsPage.clearGtinValue();
        writeLogsPage.clearLotValue();
        writeLogsPage.clearMessageIdValue();
        writeLogsPage.clearDispositionValue();
        writeLogsPage.clearMessageTimeValue();
        writeLogsPage.clearEventTimeValue();
    }
}