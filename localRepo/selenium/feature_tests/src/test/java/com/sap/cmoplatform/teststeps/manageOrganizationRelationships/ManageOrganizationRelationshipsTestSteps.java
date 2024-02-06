package com.sap.cmoplatform.teststeps.manageOrganizationRelationships;

import com.sap.cmoplatform.pages.manageOrganizationRelationships.ManageOrganizationRelationshipsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManageOrganizationRelationshipsTestSteps {
  ManageOrganizationRelationshipsPage manageOrganizationRelationships =
      new ManageOrganizationRelationshipsPage();

  @And("input {} in search filter")
  public void inputValueInSearchFilter(String PNID) throws Exception {
    manageOrganizationRelationships.inputValueInSearchFilter(PNID);
  }

  @Then("customer record should be displayed with PNID of {}")
  public void customerRecordShouldBeDisplayedWithPNIDOf(String PNID) throws Exception {
    assertEquals(PNID, manageOrganizationRelationships.getListReportPNID().get(0));
  }

  @And("open first message")
  public void openFirstMessage() throws Exception {
    manageOrganizationRelationships.openFirstMessage();
  }

  @Then("customer detail page should be displayed")
  public void customerDetailPageShouldBeDisplayed() throws Exception {
    assertEquals(
        "Organizations",
        manageOrganizationRelationships
            .getElement(manageOrganizationRelationships.organizationDetailPageTitle)
            .getText());
  }
  //  @Then("Manage Organization Relationships link should be displayed under Relationship section")
  // public void
  // manageOrganizationRelationshipsLinkShouldBeDisplayedUnderRelationshipSection()throws Exception
  // {
  //
  // assertTrue(manageOrganizationRelationships.manageOrganizationRelationshipsLinkShouldBeDisplayedUnderRelationshipSection());
  //  }
  @Then("{string} field should be displayed on overview page")
  public void variousFilterFieldShouldBeDisplayedOnOverviewPage(String fieldName) throws Exception {
    assertTrue(
        manageOrganizationRelationships.variousFilterFieldShouldBeDisplayedOnOverviewPage(
            fieldName));
  }

  @And("click on scenario drop down")
  public void clickOnScenarioDropDown() throws Exception {
    manageOrganizationRelationships
        .getElement(manageOrganizationRelationships.scenarioDetailPageDropDownArrow)
        .click();
  }

  @Then("scenario drop down should be displayed with respective values")
  public void scenarioDropDownShouldBeDisplayedWithRespectiveValues() {
    assertTrue(
        manageOrganizationRelationships.scenarioDropDownShouldBeDisplayedWithRespectiveValues());
  }

  @And("close scenario drop down")
  public void closeScenarioDropDown() throws Exception {
    manageOrganizationRelationships
        .getElement(manageOrganizationRelationships.scenarioDetailPageDropDownArrow)
        .click();
  }



  @And("click on search button in Adapt Filters")
  public void clickOnSearchButtonInAdaptFilters() throws Exception {
    manageOrganizationRelationships
        .getElement(manageOrganizationRelationships.adaptFilterSearchButton)
        .click();
  }

  @And("search for {string} in Adapt Filters")
  public void searchForFilterInAdaptFilters(String filterName) throws Exception {
    manageOrganizationRelationships.searchForFilterInAdaptFilters(filterName);
  }

  @Then("search result should be displayed with {string} in Adapt Filters")
  public void searchResultShouldBeDisplayedWithFilterNameInAdaptFilters(String filterName) {
    assertEquals(
        filterName, manageOrganizationRelationships.getAdaptFilterTableFilterNames().get(0));
  }

  @And("click on {string} drop-down")
  public void clickOnFilterDropDown(String filterName) throws Exception {
    manageOrganizationRelationships.clickOnFilterDropDown(filterName);
  }

  @Then("{string} drop-down should have  following values")
  public void dropDownShouldHaveFollowingValues(String fieldName, List<String> dropdownValues) {
    assertTrue(
        manageOrganizationRelationships.dropDownShouldHaveFollowingValues(
            fieldName, dropdownValues));
  }

  @And("click on calendar in {string} field")
  public void clickOnCalendarInFilterField(String filterName) throws Exception {
    manageOrganizationRelationships.clickOnCalendarInFilterField(filterName);
  }

  @And("select last update time {string} and {string} for {string} field")
  public void selectLastUpdateTimeFromDateAndToDate(
      String fromDate, String toDate, String fieldName) throws Exception {
    manageOrganizationRelationships.selectLastUpdateTimeFromDateAndToDate(
        fromDate, toDate, fieldName);
  }

  @Then("data should be displayed with {string} between {string} and {string}")
  public void dataShouldBeDisplayedWithFieldBetweenDate(
      String fieldName, String fromDate, String toDate) throws Exception {
    assertTrue(
        manageOrganizationRelationships.dataShouldBeDisplayedWithFieldBetweenDate(
            fieldName, fromDate, toDate));
  }

  @And("input date in {string} field with {string} and {string}")
  public void inputDateInFieldWithDate(String fieldName, String fromDate, String toDate)
      throws Exception {
    manageOrganizationRelationships.inputDateInFieldWithDate(fieldName, fromDate, toDate);
  }

  @And("input {} in {string} filter")
  public void inputValueInFilter(String fieldValue, String fieldName) throws Exception {
    manageOrganizationRelationships.inputValueInFilter(fieldValue, fieldName);
  }

  @Then("data should be displayed with {string} of {}")
  public void dataShouldBeDisplayedWithfieldValue(String fieldName, String fieldValue)
      throws Exception {
    assertTrue(
        manageOrganizationRelationships.dataShouldBeDisplayedWithfieldValue(fieldName, fieldValue));
  }

  @And("select {} in {string}")
  public void selectValueInFilter(String filterValue, String fieldName) throws Exception {
    manageOrganizationRelationships.selectValueInFilter(filterValue, fieldName);
  }

  @And("select {} in {string} drop down")
  public void selectInFilterDropDown(String filterValue, String fieldName) throws Exception {
    manageOrganizationRelationships.selectInFilterDropDown(filterValue, fieldName);
  }

  @Then("the {string} list page is displayed on the page")
  public void theListPageIsDisplayedOnThePage(String pageName) throws Exception {
    assertEquals(pageName, manageOrganizationRelationships.theListPageIsDisplayedOnThePage(pageName));
  }

  @And("select filter options")
  public void selectFilterOptionsInAdaptFilter(List<String> filterNames) throws Exception {
    manageOrganizationRelationships.selectFilterOptionsInAdaptFilter(filterNames);
  }

  @Then("it will show all the filters on the overview page")
  public void itWillShowAllTheFiltersOnTheOverviewPage(List<String> filterNames) {
    assertTrue(
        manageOrganizationRelationships.itWillShowAllTheFiltersOnTheOverviewPage(filterNames));
  }

  @Then("message count in list report should match")
  public void messageCountInListReportShouldMatch() throws Exception {
    assertTrue(manageOrganizationRelationships.messageCountInListReportShouldMatch());
  }

  @And("click on export to spreadsheet icon")
  public void clickOnExportToSpreadsheetIcon() throws Exception {
    manageOrganizationRelationships.clickOnExportToSpreadsheetIcon();
  }

  @Then("user should be able to download the result in {string} format")
  public void userShouldBeAbleToDownloadTheResultInXslFormat(String suffix) {
    assertTrue(manageOrganizationRelationships.userShouldBeAbleToDownloadTheResult(suffix));
  }

  @And("click on setting icon")
  public void clickOnSettingIcon() throws Exception {
    manageOrganizationRelationships.clickOnSettingIcon();
  }

  @And("select fields in setting popover")
  public void selectFieldsInSettingPopover(List<String> fields) throws Exception {
    manageOrganizationRelationships.selectFieldsInSettingPopover(fields);
  }

  @Then("selected columns will be shown up in the list report page")
  public void selectedColumnsWillBeShownUpInTheListReportPage(List<String> fields) {
    assertTrue(manageOrganizationRelationships.getListReportColumns().containsAll(fields));
  }

  @And("select all fields in setting popover")
  public void selectAllFieldsInSettingPopover() throws Exception {
    manageOrganizationRelationships.selectAllFieldsInSettingPopover();
  }

  @Then("create page should open")
  public void createPageShouldOpen() throws Exception {
    assertTrue(manageOrganizationRelationships.createPageShouldOpen());
  }

  @And("select {} in Scenario drop down in detail page")
  public void selectInScenarioDropDownInDetailPage(String scenarioName) throws Exception {
    manageOrganizationRelationships.selectInScenarioDropDownInDetailPage(scenarioName);
  }

  @And("select {} in {string} in detail page")
  public void selectInInviterGLNInDetailPage(String fieldValue, String fieldName) throws Exception {
    manageOrganizationRelationships.selectInDetailPage(fieldValue, fieldName);
  }
@Then("downloaded {} in the spreadsheet should be same as showing in the list page")
public void downloadedDataInTheSpreadsheetShouldBeSameAsShowingInTheListPage(String downloadFileName)throws Exception {
  assertTrue(manageOrganizationRelationships.checkDownloadFile(downloadFileName));
  }@Then("{string} error message should be displayed in detail page")
public void invitationAlreadyExistsForTheBusinessPartnerErrorMessageShouldBeDisplayedInDetailPage(String message)throws Exception {
    assertEquals(message, manageOrganizationRelationships.getErrorMessageInDetailPage());
  }@And("save the relationship")
public void saveTheRelationship()throws Exception {
    manageOrganizationRelationships.saveTheRelationship();
  }@And("search for {} in search field")
public void searchForInSearchField(String pnid)throws Exception {
    manageOrganizationRelationships.searchForInSearchField(pnid);
  }@And("set the organization status to {string}")
public void setTheOrganizationStatusToInactive(String value)throws Exception {
    manageOrganizationRelationships.setTheOrganizationStatusToInactive(value);
  }@Then("organization status should be {string}")
public void organizationStatusShouldBeInactive(String value)throws Exception {
    assertEquals(value,manageOrganizationRelationships.getOrganizationStatus());
  }@Then("{} should not be displayed in Inviter Organization in detail page")
public void inviterOrganizationShouldNotBeDisplayedInInviterOrganizationInDetailPage(String pnid)throws Exception {
assertTrue(manageOrganizationRelationships.inviterOrganizationNotDiaplayedInRelationshipDetailPage(pnid));
  }@And("cancel the creation of new relationship")
public void cancelTheCreationOfNewRelationship()throws Exception {
    manageOrganizationRelationships.cancelTheCreationOfNewRelationship();
  }@Then("{string} should be displayed under details section")
public void inviterGLNShouldBeDisplayedUnderDetailsSection(String fieldName)throws Exception {
    assertTrue(manageOrganizationRelationships.inviterOrganizationShouldBeDisplayedUnderInviterDetailsSection(fieldName));
  }@And("reset the Adapt Filters")
public void resetTheAdaptFilters()throws Exception {
    manageOrganizationRelationships.resetTheAdaptFilters();
  }@And("get the message count")
public void getTheMessageCount()throws Exception {
    manageOrganizationRelationships.getTheMessageCount();
  }@Then("all the filters should get removed and it should display the whole data")
public void allTheFiltersShouldGetRemovedAndItShouldDisplayTheWholeData()throws Exception {
    assertEquals(manageOrganizationRelationships.messageCount,manageOrganizationRelationships.getMessageCount());
    assertTrue(manageOrganizationRelationships.allTheFiltersShouldGetRemovedAndItShouldDisplayTheWholeData());
  }@And("click on Adapt Filters")
public void clickOnAdaptFilters()throws Exception {
    manageOrganizationRelationships.clickOnAdaptFilters();
  }}
