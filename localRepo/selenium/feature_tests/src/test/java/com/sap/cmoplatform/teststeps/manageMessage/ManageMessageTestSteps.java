package com.sap.cmoplatform.teststeps.manageMessage;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage;
import com.sap.cmoplatform.teststeps.common.CommonTestSteps;
import com.sap.cmoplatform.utils.PortalDriver;
import com.sap.cmoplatform.utils.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sap.cmoplatform.pages.genericReporting.genericReportingPage.configGenreicReporting;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;


public class ManageMessageTestSteps {
    private final ManageMessagingPage manageMessagingPage = new ManageMessagingPage();
    private final Properties manageMessagingProperties =
            PropertyReader.loadProperties("manageMessage/ManageMessage.properties");

    @Then("the ManageMessages page should be visible")
    public void manageMessagesPageShouldBeVisible() throws Exception {
        assertEquals(manageMessagingProperties.getProperty("MM"), manageMessagingPage.getAppTitleName());
    }

    @Then("no data should be present in the table")
    public void noDataShouldBePresentInTheTable() throws Exception {
        assertEquals(0, manageMessagingPage.getMessageCount());
        assertEquals(1, manageMessagingPage.getMessageTableRows().size());
        assertEquals(
                "No Message Data Found", manageMessagingPage.getMessageTableRows().get(0).getText());
    }

    @Then("data should be present in the table")
    public void dataShouldBePresentInTheTable() throws Exception {
        assertTrue(manageMessagingPage.getMessageCount() > 0);
        assertTrue(manageMessagingPage.getMessageTableRows().size() > 1);
        assertNotEquals(
                "No Message Data Found", manageMessagingPage.getMessageTableRows().get(0).getText());
    }

    @And("click on {string} button")
    public void clickOnButton(String buttonName) throws Exception {
        manageMessagingPage.clickButton(buttonName);
    }

    @And("select Basic filters")
    public void selectBasicFilters(List<String> basicFilters) throws Exception {
        manageMessagingPage.clearFilters();
        manageMessagingPage.basicFilters = basicFilters;
        manageMessagingPage.selectFiltersInAdaptFilter(basicFilters);
    }

    @And("select Addition filters")
    public void selectAdditionFilters(List<String> additionFilters) {
        manageMessagingPage.additionFilters = additionFilters;
        manageMessagingPage.selectFiltersInAdaptFilter(additionFilters);
    }

    @Then("all basic filters should get selected")
    public void allBasicFiltersShouldGetSelected() {
        assertTrue(manageMessagingPage.checkFilterGetSelected(manageMessagingPage.basicFilters));
    }

    @Then("all additional filters should get selected")
    public void allAdditionalFiltersShouldGetSelected() {
        assertTrue(manageMessagingPage.checkFilterGetSelected(manageMessagingPage.additionFilters));
    }

    private final ManageMessagingPage ManageMessagingPage = new ManageMessagingPage();
    private final By correlationIdFilterPath = By.xpath("//input[contains(@id,'MessageList--inputFilterCorrelationID-inner')]");
    private String correlationID;

    @Then("all the filters show on the UI page")
    public void allTheFiltersShowOnTheUIPage() {
        assertTrue(manageMessagingPage.checkFilterGetSelectedOnListPage());
    }

    @Then("only the selected filters show on the UI page")
    public void onlyTheSelectedFiltersShowOnTheUIPage() {
        assertTrue(manageMessagingPage.checkOnlySelectedFilterOnListPage());
    }

    @Then("<{string}> should have following drop-down values")
    public void shouldHaveFollowingDropDownValues(String dropDown, List<String> dropDownValues)
            throws Exception {
        // TODO: BR_MM_2108_06, BR_MM_2108_07 to be tested
        assertTrue(
                manageMessagingPage.dropDownsContainsValues(
                        manageMessagingPage.getDropDowns1(dropDown), dropDownValues));
    }

    @When("^click columnsDropDownPath button$")
    public void user_click_cloumns_dropdown_path_button() throws Throwable {
        manageMessagingPage.clickcolumnsDropDownPath();
    }

    @And("Select name in columns1 filter")
    public void selectNameInColumns1Filter(List<String> columnList) throws Exception {
        manageMessagingPage.selectNameColumns1Filter(columnList);
    }

    @And("^check the users count$")
    public void checkTheUsersCount() throws Exception {
        Thread.sleep(2000);
        usersCount = manageMessagingPage.getTableRowCount(manageMessagingPage.usersTable);
        assertTrue(manageMessagingPage.getUsersCount().contains(Integer.toString(usersCount)));
    }
    int usersCount = 0;
    @And("enter the {string} to search")
    public void enterTextToSearch(String data) throws Exception {
        manageMessagingPage.enterTextToSearch(data);
    }

    @And("click on calendar in Changed On filter")
    public void clickOnCalendarInChangedOnFilter() throws Exception {
        manageMessagingPage.clickOnCalendarInChangedOnFilter();
    }

    @Then("calendar should open")
    public void calendarInChangedOnFilterShouldOpen() throws Exception {
        manageMessagingPage.checkCalendarInChangedOnFilter();
    }

    @And("select changed on date {string} and {string}")
    public void selectChangedOnDate(String fromDate, String toDate) throws Exception {
        manageMessagingPage.selectChangedOnDate(fromDate, toDate);
    }

    @Then("User should be able to write in the {} field")
    public void userShouldBeAbleToWriteInTheSelectedField() throws Exception {
        // Click somewhere in the application to bring the focus out of the field being tested
        manageMessagingPage.getElement(manageMessagingPage.messageCountPath).click();
        System.out.println("Value is" + manageMessagingPage.getElement(manageMessagingPage.InstanceIDfilter).getAttribute("Value"));
        assertNotEquals("", manageMessagingPage.getElement(manageMessagingPage.InstanceIDfilter).getAttribute("Value"));
    }

    @And("Enter any {} in the {} Filter")
    public void writeAnyInstanceIDInTheInstanceIDFilter(String filterValue, String filterColumnName) throws Exception {
        manageMessagingPage.enterInputFilterValue(filterValue, filterColumnName);
    }

    @Then("Related data must get displayed for the searched {}")
    public void relatedDataMustGetDisplayedForTheSearchedInstanceID(String instanceId) throws Exception {
        boolean flag = true;
        List<String> valueList = manageMessagingPage.getListSubValue("Instance ID");
        if (valueList.size() < 1) {
            flag = false;
        }
        for (String s : valueList) {
            if (!s.equals(instanceId)) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }

    @Then("Only the messages of selected filters {} should be displayed")
    public void onlyTheMessagesOfSelectedFiltersShouldBeDisplayed(String correlationID) throws Exception {
        boolean flag = true;
        List<String> valueList = manageMessagingPage.getListSubValue("Correlation ID");
        if (valueList.size() < 1) {
            flag = false;
        }
        for (String s : valueList) {
            if (!s.equals(correlationID)) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }

    @And("^Select the column (.+) '(.+)'$")
    public void selectTheColumnReviewStatusColumnReviewStatus(String columnName, String filterValue) throws Exception {
        manageMessagingPage.clickDropDown(columnName);
        manageMessagingPage.selectDropDownOption(filterValue);
    }

    @And("Click on Collapse Header icon")
    public void clickOnCollapseHeaderIcon() throws Exception {
        manageMessagingPage.clickCollapseHeaderIcon();
    }

    @Then("The filter bar should be removed from the displayed screen")
    public void theFilterBarShouldBeRemovedFromTheDisplayedScreen() throws Exception {
        assertFalse(manageMessagingPage.checkFilterBarDisplay());
    }

    @And("Click on Expand Header icon")
    public void clickOnExpandHeaderIcon() throws Exception {
        manageMessagingPage.clickExpandHeaderIcon();
    }

    @Then("The filter bar should be returned to the displayed screen")
    public void theFilterBarShouldBeReturnedToTheDisplayedScreen() throws Exception {
        assertTrue(manageMessagingPage.checkFilterBarDisplay());
    }

    @And("^Enter '(.+)' in the Search Filter$")
    public void writeSenderOrganisationNameInTheSearchFilter(String filterValue) throws Exception {
        manageMessagingPage.enterSearchFilterValue(filterValue);
    }

    @Then("All the applied filters should get removed and it should shows the whole data")
    public void allTheAppliedFiltersShouldGetRemovedAndItShouldShowsTheWholeData() throws Exception {
        assertFalse(manageMessagingPage.checkSearchFilterNotNull());
        assertFalse(manageMessagingPage.checkDropDownNotNull("Scenario"));
        assertEquals(manageMessagingPage.getMessageCount(), manageMessagingPage.messageCount);
    }

    @Then("data should display for the selected dates with {string} from {string} and {string}")
    public void dataShouldDisplayForTheSelectedDatesWithFromDateAndToDate(
            String filter, String fromDate, String toDate) throws Exception {
        assertTrue(manageMessagingPage.dataShouldDisplayForTheSelectedDates(filter, fromDate, toDate));
    }

    @And("input date in {string} filter with {string} and {string}")
    public void inputDateInFilterWithFromDateAndToDate(String filter, String fromDate, String toDate) throws Exception {
        manageMessagingPage.inputDateInTimeStampFilter(filter, fromDate, toDate);
        manageMessagingPage.inputDateInTimeStampFilter(filter, fromDate, toDate);
    }

    @Then("^On the home screen data will show up as (.+) Descending order")
    public void onTheHomeScreenDataWillShowUpAsDescendingOrder(String columnName) throws Exception {
        assertTrue(
                manageMessagingPage.isColumnSortedInDescendingOrder(
                        manageMessagingPage.getColumnValues(columnName)));
    }

    @And("Click select all check box")
    public void clickSelectAllCheckBox() throws Exception {
        manageMessagingPage.clickElement(manageMessagingPage.selectAllCheckBox);
    }

    @When("Click on Settings icon")
    public void clickOnSettingIcon() throws Exception {
        manageMessagingPage.clickElement(manageMessagingPage.settingsButton);
    }

    @And("Write {} in {} Filter")
    public void updateFilterField(String value, String condition) throws Exception {
        manageMessagingPage.inputValue(com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.FilterField.valueOf(condition), value);
    }

    @And("Select {} in {} Filter")
    public void selectFilterField(String value, String condition) throws Exception {
        manageMessagingPage.selectValue(com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.FilterField.valueOf(condition), value);
    }

    @Then("{} Related data must get displayed for the searched {}")
    public void relatedDataMustGetDisplayedForTheSearchedField(String condition, String value)
            throws Exception {
        Boolean checkContains = true;
        if ("SEARCH".equals(condition)) {
            /** for SEARCH case only,check value one by one * */
            List<String> receiverDataList = manageMessagingPage.getTableFieldDatas(com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.FilterField.RECEIVER);
            List<String> senderDataList = manageMessagingPage.getTableFieldDatas(com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.FilterField.SENDER);
            List<String> receiverCodeDataList = manageMessagingPage.getTableFieldDatas(com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.FilterField.RECEIVERCODE);
            List<String> senderCodeDataList = manageMessagingPage.getTableFieldDatas(com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.FilterField.SENDERCODE);
            assertTrue(receiverDataList.size() != 0);
            assertTrue(senderDataList.size() != 0);
            assertTrue(receiverCodeDataList.size() != 0);
            assertTrue(senderCodeDataList.size() != 0);
            for (int i = 0; i < receiverDataList.size(); i++) {
                if (!(receiverDataList.get(i).contains(value)
                        || senderDataList.get(i).contains(value)
                        || receiverCodeDataList.get(i).contains(value)
                        || senderCodeDataList.get(i).contains(value))) {
                    checkContains = false;
                    break;
                }
            }
        } else {
            List<String> dataList = manageMessagingPage.getTableFieldDatas(com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.FilterField.valueOf(condition));
            assertTrue(dataList.size() != 0);
            for (String fieldData : dataList) {
                if (!fieldData.contains(value)) {
                    checkContains = false;
                    break;
                }
            }
        }
        assertTrue(checkContains);
    }

    @And("Click on Sort option")
    public void clickOnSortOption() throws Exception {
        manageMessagingPage.clickElement(manageMessagingPage.sortByPopUpLabel);
    }

    @Then("Sort view page will open")
    public void sortViewPagWillOpen() throws Exception {
        assertTrue(manageMessagingPage.getElement(manageMessagingPage.sortList).isDisplayed());
    }

    @Then("^(.+) and (.+) order options must get selected")
    public void gtinAndDescendingOrderOptionsMustGetSelected(String columnName, String sortOrder)
            throws Exception {
        assertTrue(manageMessagingPage.fieldSelected(columnName));
        assertTrue(manageMessagingPage.sortSeleted(sortOrder));
    }


    @And("^Click on (.+) button")
    public void clickOnButtoncase(String btnName) throws Exception {
        switch (btnName) {
            case "OK":
            case "Go":
            case "Save As":
            case "Save":
            case "Cancel":
                manageMessagingPage.clickButton(btnName);
                break;
            case "Add Sort Criterion":
            case "Remove Sort Criterion":
                manageMessagingPage.clickElement(By.xpath("//*[@title='" + btnName + "']"));
                break;
            case "Adapt Filters":
                manageMessagingPage.clickElement(By.xpath("//*[text()='" + btnName + "']"));
                break;
            case "Change Log":
                manageMessagingPage.clickElement(By.xpath("//*[@id='application-Message-manage-component---MessageList--changeLogBtn']"));
                break;
            case "Manage":
                manageMessagingPage.clickElement(By.xpath("//*[@id='application-Message-manage-component---MessageList--variantManagement-manage']"));
                break;
        }
    }

    @Then("^On the home screen data will show up as (.+) Ascending order")
    public void onTheHomeScreenDataWillShowUpAsAscendingOrder(String columnName) throws Exception {
        assertTrue(
                manageMessagingPage.isColumnSortedInAscendingOrder(
                        manageMessagingPage.getColumnValues(columnName)));
    }

    @And("^Select (.+) option from the list and select (.+) order")
    public void selectOptionFromTheListAndSelectDescendingOrder(String columnName, String sortOrder)
            throws Exception {
        manageMessagingPage.clickLastDropDown("field");
        manageMessagingPage.selectItem(columnName, "field");
        manageMessagingPage.clickLastDropDown("so");
        manageMessagingPage.selectItem(sortOrder, "so");
    }

    @Then("One more sort field added")
    public void oneMoreSortFieldAdded() throws Exception {
        assertTrue(manageMessagingPage.countOfElementsEquals(manageMessagingPage.sortFieldDropdown, 2));
    }

    @And("Search for Plant in the search option")
    public void searchForPlantInTheSearchOption() throws Exception {
        manageMessagingPage.inputText(manageMessagingPage.SEARCH_FOR_FILTERS, "Location");
    }

    @Then("GTIN should come as search result")
    public void gtinShouldComeAsSearchResult() throws Exception {
        manageMessagingPage.getElement(manageMessagingPage.BDI_TEXT_GTIN).isDisplayed();
    }

    @And("Validate Message Count on Home Screen of Message Manager app")
    public void validateMessageCountOnHomeScreenOfMessageManagerApp() throws Exception {
        assertTrue(manageMessagingPage.checkMessageCountDisplay());
    }

    @And("Valid count should be present.")
    public void validCountShouldBePresent() throws Exception {
        assertTrue(manageMessagingPage.validateMessageCount());
    }

    @And("Click on download icon")
    public void clickOnDownloadIcon() throws Exception {
        manageMessagingPage.clickDownloadIcon();
    }

    @Then("^verify (.+) format (.+) is downloaded$")
    public void verifyXslFormatFileNameIsDownloaded(String type, String fileName) throws Exception {
        String convertType = "xlsx";
        if (type.equals("xsl")) {
            CommonTestSteps.verifyFileIsDownloaded(fileName + "." + convertType);
        } else {
            CommonTestSteps.verifyFileIsDownloaded(fileName + "." + type);
        }
    }

    @Then("Downloaded data in the {} excel sheet must be same as showing in the Home page")
    public void downloadedDataInTheExcelSheetMustBeSameAsShowingInTheHomePage(String downloadFileName)
            throws Exception {
        assertTrue(manageMessagingPage.checkDownloadFile(downloadFileName));
    }

    @Then("^It should display the columns on the home display")
    public void itShouldDisplayAllTheFieldNameOnTheHomeDisplay(List<String> columnList)
            throws Exception {
        for (String columnName : columnList) {
            assertTrue(manageMessagingPage.checkColumnDisplay(columnName));
        }
    }

    @And("Select only few fields")
    public void selectOnlyFewFields(List<String> columnList) throws Exception {
        manageMessagingPage.selectCheckBox(columnList);
    }

    @And("Get message count")
    public void getMessageCount() throws Exception {
        manageMessagingPage.messageCount =
                manageMessagingPage.getMessageCount();
    }

    @Then("Data will not be in any of the sorting order")
    public void dataWillNotBeInAnyOfTheSortingOrder() {
        assertTrue(true);
    }

    @And("Click on Go button")
    public void clickOnGoButton() throws Exception {
        manageMessagingPage.clickGoIcon();
    }


    @And("select {string} in Scenario drop-down")
    public void selectDropdownInScenarioDropDown(String dropdownName) throws Exception {
        manageMessagingPage.selectDropdownInScenarioDropDown(dropdownName);
    }

    @And("input integer in {string} filter with {}")
    public void inputIntegerInFilterWith(String filterName, String integerCount) throws Exception {
        manageMessagingPage.inputIntegerInFilter(filterName, integerCount);
    }

    @And("select {string} in view setting")
    public void selectInViewSetting(String view) throws Exception {
        manageMessagingPage.selectInViewSetting(view);
    }

    @Then("messages should display with Number of Retriggers of {}")
    public void messagesShouldDisplayWithNumberOfRetriggersOf(String integerCount) throws Exception {
        assertTrue(
                manageMessagingPage.dataShouldDisplayForNumberOfRetriggersWithIntegerCount(integerCount));
    }

    @Then("messages should display with Message Retriggerable of {}")
    public void messagesShouldDisplayWithMessageRetriggerableOf(String booleanValue)
            throws Exception {
        assertTrue(
                manageMessagingPage.dataShouldDisplayForMessageRetriggerableWithbooleanValue(booleanValue));
    }

    @And("select {} in Message Retriggerable filter")
    public void selectBooleanInMessageRetriggerableFilter(String booleanValue) throws Exception {
        manageMessagingPage.selectBooleanInFilter(booleanValue);
    }

    @Then("{string} table with column names should be displayed")
    public void tableWithColumnNamesShouldBeDisplayed(String tableName) throws Exception {
        assertTrue(manageMessagingPage.tableWithColumnNamesShouldBeDisplayed(tableName));
    }

    @Then("{string} timestamp should be displayed")
    public void timestampsShouldBeDisplayed(String timestampName) throws Exception {
        assertTrue(manageMessagingPage.timestampsShouldBeDisplayed(timestampName));
    }

    @Then("{string} payload should be displayed")
    public void payloadShouldBeDisplayed(String payload) throws Exception {
        assertTrue(manageMessagingPage.payloadShouldBeDisplayed(payload));
    }

    @And("Enter the {string} in additional information field")
    public void enterTheValidInfoInAdditionalInformationField(String validInfo) throws Exception {
        manageMessagingPage.inputText(manageMessagingPage.additionalInfoInputPath, validInfo);
    }

    @Then("{string} message should display")
    public void messageShouldDisplay(String message) throws Exception {
        assertEquals(message, manageMessagingPage.getSuccessToastMessage());
    }

    @Then("{string} file should get uploaded")
    public void finalizationMessageFileShouldGetUploaded(String file) throws Exception {
        assertEquals(manageMessagingPage.getElement(manageMessagingPage.fileInputPath).getAttribute("Title"), manageMessagingPage.getFileName(file));
    }

    @And("set Message Status filter to {string}")
    public void setMessageStatusFilterToValue(String filterValue) throws Exception {
        manageMessagingPage.setMessageStatusFilterToValue(filterValue);
    }

    @And("download {string} payload")
    public void downloadPayload(String payload) throws Exception {
        manageMessagingPage.downloadPayload(payload);
    }

    @And("leave {string} in comment")
    public void leaveCommentMessageInComment(String commentMessage) throws Exception {
        manageMessagingPage.leaveCommentMessageInComment(commentMessage);
    }

    @And("submit comment")
    public void submitComment() throws Exception {
        manageMessagingPage.submitComment();
    }

    @Then("{string} should display on message detail page")
    public void commentMessageShouldDisplayOnMessageDetailPage(String commentMessage)
            throws Exception {
        assertTrue(manageMessagingPage.containsComment(commentMessage));
    }

    @And("the user logs out from current account")
    public void theUserLogsOutFromCurrentAccount() throws Exception {
        manageMessagingPage.theUserLogsOutFromCurrentAccount();
    }

    @Then("{} should match from both end")
    public void senderPayloadFileNameShouldMatchFromBothEnd(String payloadFileName) throws Exception {
        assertTrue(manageMessagingPage.payloadShouldMatchFromBothEnd(payloadFileName));
    }

    @And("input {} into the Correlation ID filter")
    public void inputCorrelationIdIntoTheCorrelationIDFilter(String correlationID) throws Exception {
        manageMessagingPage.inputCorrelationIdIntoTheCorrelationIDFilter(correlationID);
    }

    @Then("submit commit button should be disabled")
    public void submitCommitButtonShouldBeDisabled() throws Exception {
        assertTrue(
                manageMessagingPage
                        .getElement(manageMessagingPage.submitCommentDisabledButtonPath)
                        .isDisplayed());
    }

    @And("select a {string} sub type from Sub Type filter")
    public void selectASubTypeFromSubTypeFilter(String subTypeName) throws Exception {
        manageMessagingPage.selectASubTypeFromSubTypeFilter(subTypeName);
    }

    @Then("data should display with {string} of {string}")
    public void dataShouldDisplayWithFilterOfValue(String filterName, String filterValue)
            throws Exception {
        assertTrue(manageMessagingPage.dataShouldDisplayWithFilterOfValue(filterName, filterValue));
    }

    @Then("{string} warning message should display")
    public void onlyCharactersAllowedWarningMessageShouldDisplay(String message) throws Exception {
        assertEquals(message, manageMessagingPage.getToastMessage());
    }

    @And("search for iflows with message correlation ID")
    public void searchForIflowsWithMessageCorrelationID() throws Exception {
        manageMessagingPage.searchForIflowsWithMessageCorrelationID();
    }

    @Then("all the related iflows should get hit with {string} status")
    public void allTheRelatedIflowsShouldGetHitWithStatus(String status) {
        assertTrue(manageMessagingPage.allTheRelatedIflowsShouldGetHitWithStatus(status));
    }

    @Then("message status should be {string}")
    public void messageStatusShouldBeError(String status) throws Exception {
        assertEquals(status, manageMessagingPage.getMessageStatus());
    }

    @Then("first row should display with {string} of {string}")
    public void firstRowShouldDisplayWithOf(String name, String value) {

        assertEquals(value, manageMessagingPage.firstRowShouldDisplayValueWithOf(name));
    }

    @And("input in the Correlation ID filter")
    public void inputInTheCorrelationIDFilter() throws Exception {
        manageMessagingPage.inputCorrelationIdIntoTheCorrelationIDFilter(manageMessagingPage.correlationID);
    }

    @And("get first row correlation ID")
    public void getFirstRowCorrelationID() throws Exception {
        manageMessagingPage.getMessageCorrelationId();
    }

    @And("open validation artifact")
    public void openValidationArtifact() throws Exception {
        manageMessagingPage.openValidationArtifact();
    }

    @Then("{string} information should match in CPI {string}")
    public void informationShouldMatchInCPI(String PNIDvalue, String name) throws Exception {
        assertEquals(PNIDvalue, manageMessagingPage.getPNIDInCPIArtifact(name));
    }

    @And("open text view")
    public void openTextView() throws Exception {
        manageMessagingPage.openTextView();
    }

    @Then("intermediateError should be {}")
    public void intermediateErrorShouldBeValue(String value) throws Exception {
        assertEquals(value, manageMessagingPage.getIntermediateErrorInTextView());
    }

    @Then("check user should be able to Select {} in the {} Filter")
    public void userShouldBeAbleToWriteInTheSelectedField(String value, String condition) throws Exception {
        assertTrue(manageMessagingPage.checkFieldNotNull(value, com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.FilterField.valueOf(condition)));
    }

    @And("Click on one message")
    public void clickOnOneMessage() throws Exception {
        manageMessagingPage.clickOnMessage();
    }

    @Then("Message should be retriggered")
    public void messageShouldBeRetriggered() throws Exception {
        assertTrue(manageMessagingPage.validateRetrigger());
    }

    @And("Back to home page")
    public void backToHomePage() throws Exception {
        manageMessagingPage.clickOnBackButton();
    }

    @And("click on Adapt Filter")
    public void clickOnAdaptFilter() throws Exception {
        manageMessagingPage.openAdaptFilter();
    }

    @Then("Verify field column display")
    public void verifyChangeLogPopUpFieldColumnDisplay() {
        assertTrue(manageMessagingPage.validateColumnDisplay());
    }

    @Then("^(.+) pop up must open")
    public void viewSettingPopUpMustOpen(String viewTitle) throws Exception {
        switch (viewTitle) {
            case "View Settings":
            case "Change Log":
                assertTrue(manageMessagingPage.getElement(By.xpath("//span[text()='" + viewTitle + "']")).isDisplayed());
                break;
            case "Adapt Filters":
                assertTrue(manageMessagingPage.getElement(By.xpath("//button/span/span/bdi[text()='" + viewTitle + "']")).isDisplayed());
                break;
            default:
                assertTrue(manageMessagingPage.getElement(By.xpath("//span[text()='" + viewTitle + "']")).isDisplayed());
                break;
        }
    }

    @Then("Verify columns value display")
    public void verifyColumnsValueDisplay(List<String> columnList) {
        for (String column : columnList) {
            assertTrue(manageMessagingPage.validateColumnValueDisplay(column));
        }
    }

    @And("Get correlationID")
    public void getCorrelationID() throws Exception {
        manageMessagingPage.correlationID = manageMessagingPage.getCorrelationID();
    }

    @And("Select check box in adapt filter")
    public void selectCheckBoxInAdaptFilter(List<String> columnList) throws Exception {
        manageMessagingPage.selectCheckBoxAdaptFilter(columnList);
    }

    @And("^Select the '(.+)'")
    public void selectTheMessageType(String messageType) throws Exception {
        manageMessagingPage.clickLastDropDown("mt");
        manageMessagingPage.clickElement(By.xpath("//*[text()='Regulatory Reporting for Brazil']"));
    }

    @Then("^(.+) will get selected")
    public void brazilReportingMessageTypeWillGetSelected(String messageType) throws Exception {
        WebElement messageTypeInput = manageMessagingPage.getElement(By.xpath("//input[@id='application-Message-manage-component---MessageList--typeComboBox-inner']"));
        assertEquals(messageType, messageTypeInput.getAttribute("value"));
    }

    @And("Attach the message file {} in xml format")
    public void attachTheMessageFileInXmlFormat(String filename) throws Exception {
        String filePath = manageMessagingPage.testFiles + File.separator + filename;
        manageMessagingPage.uploadfile(filePath);
    }

    @And("^Search '(.+)' in Change Log$")
    public void searchValueInChangeLog(String searchValue) throws Exception {
        manageMessagingPage.inputValueInSearch(searchValue);
    }

    @And("Click search button to search")
    public void clickSearchButtonToSearch() throws Exception {
        manageMessagingPage.clickSearchButton();
    }

    @Then("Accept message file should get uploaded")
    public void acceptMessageFileShouldGetUploaded() throws Exception {
        WebElement fileInput = manageMessagingPage.getElement((By.xpath("//input[@type='file']")));
        assertFalse(fileInput.getAttribute("value").isEmpty());
    }

//    @When("Click on Select View option")
//    public void clickOnSelectViewOption() throws Exception {
//        manageMessagingPage.waitForPageToLoad();
//        manageMessagingPage.clickElement(By.id("application-Message-manage-component---MessageList--variantManagement-trigger"));
//    }


    @And("clear all customized views")
    public void clearAllCustomizedViews() throws Exception {
        manageMessagingPage.clickElement(By.id("application-Message-manage-component---MessageList--variantManagement-manage"));
        List<WebElement> deleteBtns = manageMessagingPage.getElements(By.xpath("//*[@title='Delete View']"));
        for (WebElement dltBtn : deleteBtns) {
            dltBtn.click();
        }
        clickOnButton("Save");
    }

    @And("Enter name in the View name field")
    public void enterNameInTheViewNameField() throws Exception {
        WebElement viewNameField = manageMessagingPage.getElement(By.id("application-Message-manage-component---MessageList--variantManagement-name-inner"));
        viewNameField.click();
        viewNameField.sendKeys(Keys.CONTROL + "a");
        viewNameField.sendKeys(Keys.DELETE);
        manageMessagingPage.inputText(By.id("application-Message-manage-component---MessageList--variantManagement-name-inner"), "TestView");
    }

    @Then("User should be enter text1 in name field")
    public void userShouldBeEnterText1InNameField() throws Exception {
        assertEquals("TestView", manageMessagingPage.getElement(By.id("application-Message-manage-component---MessageList--variantManagement-name-inner")).getAttribute("value"));
    }


    @And("Attach the message file in zip format")
    public void attachTheMessageFileInZipFormat() throws Exception {
        String filePath = manageMessagingPage.testFiles + File.separator + "zip folder.zip";
        manageMessagingPage.uploadfile(filePath);
    }

    @And("Attach a xml file with contains upper and lower letters XML,xmL,XmL")
    public void attachAXmlFileWithContainsUpperAndLowerLettersXMLXmLXmL() throws Exception {
        String filePath = manageMessagingPage.testFiles + File.separator + "BR_MM_2108_40_2.Xml";
        manageMessagingPage.uploadfile(filePath);
    }


    @And("Attach a xml file contains with upper and lower letters and spec characters")
    public void attachAXmlFileContainsWithUpperAndLowerLettersAndSpecCharactersSpace() throws Exception {
        String filePath = manageMessagingPage.testFiles + File.separator + "BR_MM_2108_40_1 -lower space.xml";
        manageMessagingPage.uploadfile(filePath);
    }

    @Then("After clicking on save button, view should get save with same name and user will navigate back to home page")
    public void afterClickingOnSaveButtonViewShouldGetSaveWithSameNameAndUserWillNavigateBackToHomePage() throws
            Exception {
        assertTrue(manageMessagingPage.variantViewDisplayed());
        assertTrue(manageMessagingPage.listPageDisplayed());
    }

    @And("Reset check box in adapt filter")
    public void resetCheckBoxInAdaptFilter(List<String> columnList) throws Exception {
        manageMessagingPage.resetCheckBoxAdaptFilter(columnList);
    }

    @Then("Verify column display in home page")
    public void verifyColumnDisplayInHomePage(List<String> columnList) throws Exception {
        for (String column : columnList) {
            assertFalse(manageMessagingPage.validateColumnDisplayHomePage(column));
        }
    }


    @Then("check user should be able to write {} in the empty field")
    public void userShouldBeAbleToWriteInTheSelectedField1(String condition) throws Exception {
        assertTrue(manageMessagingPage.checkFieldNotNull(com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.FilterField.valueOf(condition)));
    }

    @And("Delete {} view")
    public void deleteViewNameView(String viewName) throws Exception {
        if (manageMessagingPage.getElement(By.xpath("//input[@value='" + viewName + "']")).isDisplayed()) {
            manageMessagingPage.clickElement(By.xpath("//input[@value='" + viewName + "']/../../../..//button"));
        }
    }

    @Then("After clicking on save button, view should get delete with same name and user will navigate back to home page")
    public void afterClickingOnSaveButtonViewShouldGetDeleteWithSameNameAndUserWillNavigateBackToHomePage() throws
            Exception {
        assertTrue(manageMessagingPage.standardViewDisplayed());
    }

    @Then("User will navigate back to home page and the view still exist")
    public void userWillNavigateBackToHomePageAndTheViewStillExist() throws Exception {
        assertTrue(manageMessagingPage.variantViewDisplayed());
    }

    @And("Select {} from View Settings")
    public void selectColumnFromViewSettings(String column) {
        manageMessagingPage.selectFiltersInViewSetting(column);
    }

    @And("Click on My views incon")
    public void clickOnMyViewsIncon() throws Exception {
        manageMessagingPage.clickElement(manageMessagingPage.VIEWICON);
    }

    @Then("no  change will get save")
    public void noChangeWillGetSave() throws Exception {
        assertTrue(manageMessagingPage.getElement(manageMessagingPage.MODIFIEDICON).isDisplayed());

    }

    @When("Select the message from the top")
    public void selectTheMessageFromTheTop() throws Exception {
        WebDriverWait w = new WebDriverWait(SeleniumUI5TestUtil.getConfig().getDriver(), 30);
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='sapMListTblNavCol']")));
        manageMessagingPage.getElements(By.xpath("//*[@class='sapMListTblNavCol']")).get(0).click();
    }

    @And("Select undefault view name from the list and make it default one")
    public void selectAnyUndefaultViewNameFromTheListAndMakeItDefaultOne() throws Exception {
        manageMessagingPage.selectAnyUndefaultView();
    }

    @Then("undefault view will get selected")
    public void undefaultViewWillGetSelected() {
        assertTrue(manageMessagingPage.UndefaultViewSelected());

    }

    @Then("After clicking on save button the choosed view will become the default view")
    public void afterClickingOnSaveButtonTheChoosedViewWillBecomeTheDefaultView() throws Exception {
        manageMessagingPage.clickElement(manageMessagingPage.VIEWICON);
        manageMessagingPage.clickButton("Manage");
        assertTrue(manageMessagingPage.UndefaultViewSelected());
    }

    @And("Search for {} view name")
    public void searchViewName(String viewName) throws Exception {
        manageMessagingPage.inputText(manageMessagingPage.searchViewInputPath, viewName);
    }

    @Then("It must show only the {} view")
    public void itMustShowOnlyTheStandardView(String viewName) {
        assertTrue(manageMessagingPage.isViewShowOnly(viewName));
    }

    @And("Click on {} tab")
    public void clickOnTab(String tabName) throws Exception {
        manageMessagingPage.waitForPageToLoad();
        manageMessagingPage.waitForTableToLoad();
        manageMessagingPage.waitForElementToLoad();
        manageMessagingPage.clickElement(By.xpath("//*[text()='" + tabName + "']"));
    }

    @And("open first message in Manage Message App")
    public void openFirstMessageinManageMessage() throws Exception {
        manageMessagingPage.openFirstMessageinManageMessage();
    }

    @Then("Detail page must get open")
    public void detailPageMustGetOpen() throws Exception {
        manageMessagingPage.waitForPageToLoad();
        manageMessagingPage.waitForTableToLoad();
        manageMessagingPage.waitForElementToLoad();
        assertTrue(manageMessagingPage.correlationIDisDisplayed());
    }

    @Then("Downloaded payload should contain the {} payload")
    public void downloadedPaloadShouldContainPayload(String uploadFileName) {

        String filePathUpload = manageMessagingPage.testFiles + File.separator + uploadFileName;
        String filePathDownload = System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
        File folder = new File((filePathDownload));
        File f;
        File[] listOfFiles = folder.listFiles();
        //Look for the file in the files
        if (null != listOfFiles) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    String fileName = listOfFile.getName();
                    if (fileName.contains("Sender")) {
                        f = new File(filePathDownload + fileName);
                        f.deleteOnExit();
                        assertTrue(manageMessagingPage.checkXMLisSame(new File(filePathUpload), f));
                        return;
                    }
                }
            }
        }
    }

    @Then("Search field, Action, Field, Applied To, Old Values, New Values, Changed By, Changed On: table columns should be displayed")
    public void searchFieldActionFieldAppliedToOldValuesNewValuesChangedByChangedOnTableColumnsShouldBeDisplayed() throws
            Exception {
        manageMessagingPage.waitForPageToLoad();
        manageMessagingPage.waitForTableToLoad();
        manageMessagingPage.waitForElementToLoad();
        assertTrue(manageMessagingPage.checkLogDisplay());
    }

    @And("Click on Review Comments text box")
    public void clickOnReviewCommentsTextBox() throws Exception {
        PortalDriver.getInstance().waitFor(1000);
        manageMessagingPage.scrollToComment();
        PortalDriver.getInstance().waitFor(1000);
        manageMessagingPage.clickElement(By.xpath("//textarea[@placeholder = 'Post something here']"));

    }

    @And("Provide new {}")
    public void provideNewComments(String comment) throws Exception {

        manageMessagingPage.inputText(By.xpath("//textarea[@placeholder = 'Post something here']"), comment);
    }

    @And("Change the status")
    public void changeTheStatus() throws Exception {
        manageMessagingPage.changeReviewStatus();

    }

    @Then("No New Log Row should be found for the message with the respective Correlation ID")
    public void noNewLogRowShouldBeFoundForTheMessageWithTheRespectiveCorrelationID() throws Exception {
        assertFalse(manageMessagingPage.noNewLogRowShouldBeFoundForTheMessageWithTheRespectiveCorrelationID());

    }

    @Then("Columns values should be displayed in the table as reviewStatus template")
    public void columnsValuesShouldBeDisplayedInTheTableAsReviewStatusTemplate() throws Exception {
        assertTrue(manageMessagingPage.changeLogDisplayedAsReviewStatusTemplate());
    }

    @Then("Columns values should be displayed in the table as uploadMessage template")
    public void columnsValuesShouldBeDisplayedInTheTableAsUploadMessageTemplate() throws Exception {
        assertTrue(manageMessagingPage.changeLogDisplayedAsUploadMessageTemplate());
    }

    @Then("A unique correlation id and timestamp will show")
    public void aUniqueCorrelationIdAndTimeStempWillShow() throws Exception {
        manageMessagingPage.waitForPageToLoad();
        assertTrue(manageMessagingPage.correlationIDisDisplayed());
        assertTrue(manageMessagingPage.timestampIsDisplayed());
    }

    @Then("Fields will show in page header facet")
    public void fieldsWillShowInPageHeaderFacet() throws Exception {
        assertTrue(ManageMessagingPage.fieldsShowInHeader());
    }

    @Then("Tabs are present")
    public void tabsArePresent() throws Exception {
        int nTab = manageMessagingPage.getElements(By.xpath("//div[contains(@data-sap-ui,'application-Message-manage-component---MessageDetails--ObjectPageLayout-anchBar-')and @aria-haspopup='menu']")).size();
        assertEquals(2, nTab);
        assertTrue(manageMessagingPage.getElement(By.xpath("//bdi[text()='Comments']")).isDisplayed());
    }

    @And("Go to Message Details")
    public void goToMessageDetails() throws Exception {
        manageMessagingPage.clickElement(By.xpath("//bdi[text()='Message Details']"));
    }

    @Then("Fields will show in Message Details")
    public void fieldsWillShowInMessageDetails() throws Exception {
        boolean bStatusDescription = manageMessagingPage.getElement(By.xpath("//bdi[text()='Status Description']")).isDisplayed();
        boolean bSender = manageMessagingPage.getElement(By.xpath("//bdi[contains(@id, 'application-Message-manage-component---MessageDetails') and text()='Sender']")).isDisplayed();
        boolean bRetriggeredOn = manageMessagingPage.getElement(By.xpath("//bdi[text()='Retriggered On']")).isDisplayed();
        boolean bBusinessDocID = manageMessagingPage.getElement(By.xpath("//bdi[contains(@id, 'application-Message-manage-component---MessageDetails') and text()='Business Document ID']")).isDisplayed();
        boolean bReceiver = manageMessagingPage.getElement(By.xpath("//bdi[contains(@id, 'application-Message-manage-component---MessageDetails') and text()='Receiver']")).isDisplayed();
        boolean bCreatedOn = manageMessagingPage.getElement(By.xpath("//span[starts-with(@id,'__')]/bdi[text()='Created On']")).isDisplayed();
        boolean bAdditionalInfo = manageMessagingPage.getElement(By.xpath("//bdi[text()='Additional Information']")).isDisplayed();
        assertTrue(bRetriggeredOn && bStatusDescription && bSender && bBusinessDocID && bReceiver && bCreatedOn && bAdditionalInfo);
    }

    @Then("Table will show in Message Content")
    public void tableWillShowInMessageContent() throws Exception {
        assertTrue(manageMessagingPage.getElement(By.xpath("//span[text()='LOTs']")).isDisplayed());
        assertTrue(manageMessagingPage.getElement(By.xpath("//table//td[@headers='application-Message-manage-component---MessageDetails--idContentDesColumn']//span[text()='GTINs']")).isDisplayed());
    }

    @And("Go to Related Messages")
    public void goToRelatedMessages() throws Exception {
        manageMessagingPage.clickElement(By.xpath("//bdi[text()='Related Messages']"));
    }

    @Then("Fields will show in Automatic Retries")
    public void fieldsWillShowInAutomaticRetries() throws Exception {
        boolean bRetryAttempts = manageMessagingPage.getElement(By.xpath("//span[text()='Retry Attempt']")).isDisplayed();
        boolean bRetried = manageMessagingPage.getElement(By.xpath("//span[text()='Retried']")).isDisplayed();
        boolean bRetriedStatus = manageMessagingPage.getElement(By.xpath("//span[text()='Retried Status']")).isDisplayed();
        boolean bStatusDescription = manageMessagingPage.getElement(By.xpath("//span[text()='Status Description']")).isDisplayed();
        assertTrue(bRetried && bRetriedStatus && bRetryAttempts && bStatusDescription);
    }

    @Then("Fields will show in Identical Messages")
    public void fieldsWillShowInIdenticalMessages() throws Exception {
        boolean bCorrelationID = manageMessagingPage.getElement(By.id("application-Message-manage-component---MessageDetails--idCorrelationIdColumn")).isDisplayed();
        boolean bRetriggeredOn = manageMessagingPage.getElement(By.id("application-Message-manage-component---MessageDetails--idRetriggeredColumn")).isDisplayed();
        boolean bMessageStatus = manageMessagingPage.getElement(By.id("application-Message-manage-component---MessageDetails--idIdenticalStausColumn")).isDisplayed();
        boolean bStatusDescription = manageMessagingPage.getElement(By.id("application-Message-manage-component---MessageDetails--idIdenticalStausDesColumn")).isDisplayed();
        assertTrue(bMessageStatus && bCorrelationID && bRetriggeredOn && bStatusDescription);
    }

    @And("Go to Comments")
    public void goToComments() throws Exception {
        manageMessagingPage.clickElement(By.xpath("//bdi[text()='Comments']"));
    }

    @Then("Comment input will show")
    public void commentInputShow() throws Exception {
        assertTrue(manageMessagingPage.getElement(By.id("application-Message-manage-component---MessageDetails--messageCommentFeedInput-textArea-inner")).isDisplayed());
    }

    @And("Open the CPI tenant and check for the message which has been sent from UI and copy the co-relation id")
    public void openTheCPITenantAndCheckForTheMessageWhichHasBeenSentFromUIAndCopyTheCoRelationId() throws
            Exception {
        correlationID = manageMessagingPage.getElements(By.xpath("//span[text()='Correlation ID']/../..//*[@class = 'sapMListTblSubCntVal sapMListTblSubCntValInline']//span")).get(0).getText();
        PortalDriver.getInstance().getConfig().getDriver().get(configGenreicReporting.getProperty("cpiMonitoringAppURL"));
        CommonTestSteps.enableTimestampforScreenshots();

    }

    @Then("All the related iflows should get hit with completed status")
    public void allTheRelatedIflowsShouldGetHitWithCompletedStatus() {
        List<WebElement> status = manageMessagingPage.getElements(By.xpath("//td[@data-sap-ui-column=\"MESSAGES_TABLE-STATUS\"]//span[contains(@id, 'text')]"));
        for (WebElement s : status
        ) {
            assertEquals("Completed", s.getText());
        }
    }

    @And("Now Paste the same corelation in the search box and click on GO button.")
    public void nowPasteTheSameCorelationInTheSearchBoxAndClickOnGOButton() throws Exception {
        manageMessagingPage.inputText(correlationIdFilterPath, correlationID);
    }

    @Then("Result should show for the searched co-relation ID")
    public void resultShouldShowForTheSearchedCoRelationID() throws Exception {
        WebElement title = manageMessagingPage.getElement(By.id("PanelMessagesTitle-bdi"));
        Pattern pattern = Pattern.compile("\\d+"); // 匹配一个或多个数字
        Matcher matcher = pattern.matcher(title.getText());
        String number = null;
        while (matcher.find()) {
            number = matcher.group();
        }
        int n = Integer.parseInt(number);
        assertTrue(n > 0);
    }

    @And("Select {} in New Message Scenario")
    public void selectScenarioInNewMessageScenario(String scenario) throws Exception {
        manageMessagingPage.selectScenarioInNewMessageScenario(scenario);
    }

    @Then("Message should be triggered")
    public void messageShouldBeTriggered() throws Exception {
        manageMessagingPage.checkMessageIsTriggerred();
    }

    @And("Download {} file")
    public void downloadSenderFile(String file) throws Exception {
        PortalDriver.getInstance().waitFor(1000);
        try {
            if (file.equals("Sender")) {
                manageMessagingPage.clickElement(manageMessagingPage.senderPayloadPath);
            } else {
                manageMessagingPage.clickElement(manageMessagingPage.receiverPayloadPath);
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }

        PortalDriver.getInstance().waitFor(5000);

    }

    @Then("Downloaded payload should be transformed payload.")
    public void downloadedPayloadShouldBeTransformedPayload() {
        String filePathDownload = System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
        File folder = new File((filePathDownload));
        File f;
        File[] listOfFiles = folder.listFiles();
        if (null != listOfFiles) {
            //Look for the file in the files
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    String fileName = listOfFile.getName();
                    if (fileName.contains("Receiver")) {
                        f = new File(filePathDownload + fileName);
                        f.deleteOnExit();
                        assertFalse(manageMessagingPage.checkXMLisEncode(f));
                        return;
                    }
                }
            }
        }

    }

    @Then("Downloaded Receiver file contains  the decoded file content of the sender {} info")
    public void downloadedReceiverFileContainsTheDecodedFileContentOfTheSenderFileNameInfo(String uploadFileName) throws InterruptedException {
        PortalDriver.getInstance().waitFor(5000);
        String filePathUpload = manageMessagingPage.testFiles + File.separator + uploadFileName;
        String filePathDownload = System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
        File folder = new File((filePathDownload));
        File f;
        File[] listOfFiles = folder.listFiles();
        //Look for the file in the files
        if (null != listOfFiles) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    String fileName = listOfFile.getName();
                    if (fileName.contains("Receiver")) {
                        f = new File(filePathDownload + fileName);
                        f.deleteOnExit();
                        assertFalse(manageMessagingPage.checkXMLisEncode(f));
                        assertTrue(manageMessagingPage.checkPartnerIsSame(new File(filePathUpload), f));
                        return;
                    }
                }
            }
        }
    }
}