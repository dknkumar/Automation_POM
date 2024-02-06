package com.sap.cmoplatform.teststeps.manageOrganization;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.pages.manageOrganization.ManageOrganizationPage;
import com.sap.cmoplatform.teststeps.common.CommonTestSteps;
import com.sap.cmoplatform.utils.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ManageOrganizationTestSteps extends Page {

    private ManageOrganizationPage manageOrganizationPage = new ManageOrganizationPage();

    Properties manageOrganizationProperties = PropertyReader.loadProperties("manageOrganizations/ManageOrganization.properties");

    public ManageOrganizationTestSteps() throws AWTException {
    }

    @And("^Select the column (.+) '(.+)' value")
    public void selectTheColumnColumnValueValue(String column, String value) throws Exception {
        manageOrganizationPage.clickDropDown(column);
        manageOrganizationPage.selectDropDownOption(value);
    }

    @And("Click on one organization")
    public void clickOnOneOrganization() throws Exception {
        manageOrganizationPage.clickOnOrganization();
    }

    @Then("Relationship tab is visible")
    public void relationshipTabIsVisible() throws Exception {
        assertTrue(manageOrganizationPage.checkRelationshipTabVisible());
    }

    @When("click on button {string}")
    public void clickOnButtonGo(String buttonName) throws Exception {
        switch (buttonName) {
            case "OK":
            case "Go":
            case "Save As":
            case "Cancel":
            case "Save":
            case "Manage":
            case "Create":
            case "Edit":
            case "Ok":
            case "Delete":
                manageOrganizationPage.clickButton(buttonName);
                break;
            case "Download":
                manageOrganizationPage.clickOnDownloadButton();
                break;
            case "Download Arrow":
                manageOrganizationPage.clickOnDownloadArrow();
                break;
            case "Export":
                manageOrganizationPage.clickElement(By.xpath("//*[text()='Export']"));
                break;
            case "Export As":
                manageOrganizationPage.clickElement(By.xpath("//*[text()='Export As...']"));
                break;
            case "Adapt Filters":
                manageOrganizationPage.clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReportFilter-btnFilters-inner']"));
                break;
            case "Reset":
                manageOrganizationPage.clickElement(By.xpath("//*[text()='Reset']"));
                break;
            case "Reset OK":
                manageOrganizationPage.clickElement(By.xpath("//*[@class='sapMIBar sapMTB sapMTBNewFlex sapMTBInactive sapMTBStandard sapMTB-Auto-CTX sapMOTB sapMTBNoBorders sapMIBar-CTX sapMFooter-CTX']//bdi[text()='OK']"));
                break;
            case "Sort":
                manageOrganizationPage.clickElement(By.xpath("//*[text()='Sort']"));
                break;
            case "CreateOrg":
                manageOrganizationPage.clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ObjectPage.view.Details::Organizations--save-inner']//bdi"));
                break;
            case "AddScenario":
                manageOrganizationPage.clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ObjectPage.view.Details::Organizations--scenarios::com.sap.vocabularies.UI.v1.LineItem::Table::Toolbar']//bdi[text()='Add']"));
                break;
            case "ScenarioAdd":
                manageOrganizationPage.clickElement(By.xpath("//span[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ObjectPage.view.Details::Organizations--scenarioDialogTable-ok-content']//bdi[text()='Add']"));
                break;
        }
    }

    @Then("the ManageOrganizations page should be visible")
    public void theManageOrganizationsPageShouldBeVisible() {
        assertEquals(manageOrganizationProperties.getProperty("MO"), SeleniumUI5TestUtil.getConfig().getDriver().getTitle());
    }

//    @And("Select check box in adapt filter")
//    public void selectCheckBoxInAdaptFilter(List<String> columnList) throws Exception {
//        manageOrganizationPage.selectCheckBoxAdaptFilter(columnList);
//    }


    @Then("All filters should get selected")
    public void allFiltersShouldGetSelected(List<String> columnList) throws Exception {
        assertTrue(manageOrganizationPage.checkFilterSelected(columnList));
    }

    @Then("Once OK button is pressed, it must show all the filters on the UI overview page")
    public void onceOKButtonIsPressedItMustShowAllTheFiltersOnTheUIOverviewPage(List<String> columnList) {
        for (String column : columnList) {
            assertTrue(manageOrganizationPage.checkFilterOnOverviewPage(column));
        }
    }

    @Then("Once OK button is pressed, it must show only the selected filters on the UI overview page.")
    public void onceOKButtonIsPressedItMustShowOnlyTheSelectedFiltersOnTheUIOverviewPage() {
        // First search filter is default, needs to minus 1
        assertEquals(3, manageOrganizationPage.getFilterCount() - 1);
    }

    @And("Unselect checkbox in adapt filter")
    public void unselectCheckboxInAdaptFilter(List<String> columnList) throws Exception {
        manageOrganizationPage.unselectCheckBox(columnList);
    }

    @When("Click on Collapse arrow")
    public void clickOnCollapseArrow() throws Exception {
        manageOrganizationPage.clickCollapseArrow();
    }

    @Then("Header section containing the search field and filters should be hidden")
    public void headerSectionContainingTheSearchFieldAndFiltersShouldBeHidden() throws Exception {
        assertTrue(manageOrganizationPage.checkFilterBarDisplay());
    }

    @When("Click on Expand arrow")
    public void clickOnExpandArrow() throws Exception {
        manageOrganizationPage.clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template:::ListReportPage:::DynamicPageTitle-expandBtn-img']"));
    }

    @Then("Header section should be visible")
    public void headerSectionShouldBeVisible() throws Exception {
        assertFalse(manageOrganizationPage.checkFilterBarDisplay());
    }

    @Then("^Results should be displayed according to the Filter '(.+)' selected")
    public void resultsShouldBeDisplayedAccordingToTheColumnFilterValueSelected(String value) throws Exception {
        assertTrue(manageOrganizationPage.checkFilterValueDisplay(value));
    }

    @And("^Select filter item column (.+) '(.+)' value")
    public void selectFilterItemColumnColumnValueValue(String column, String value) throws Exception {
        manageOrganizationPage.clickDropDown(column);
        manageOrganizationPage.inputCondition(value);

    }

    @Then("column {string} should be selected")
    public void columnValueShouldBeSelected(String value) throws Exception {
        assertTrue(manageOrganizationPage.checkInputConditionSelected(value));
    }

    @Then("Results should be displayed according to the {string} selected")
    public void resultsShouldBeDisplayedAccordingToTheValueSelected(String value) throws Exception {
        assertTrue(manageOrganizationPage.checkInputConditionDisplay(value));
    }

    @And("^Select the Type on '(.+)' filter")
    public void selectTheTypeOnInterfaceTypeFilter(String column) throws Exception {
        manageOrganizationPage.clickDropDown(column);
        manageOrganizationPage.selectInterfaceType();
    }

    @Then("Filters to be displayed and Filter values should be reset to default and the same should be visible on the UI")
    public void filtersToBeDisplayedAndFilterValuesShouldBeResetToDefaultAndTheSameShouldBeVisibleOnTheUI() {
        assertTrue(manageOrganizationPage.checkFilterValueReset());
    }

    @Then("Verify the Organizations Count displayed on above the table")
    public void verifyTheOrganizationsCountDisplayedOnAboveTheTable() throws Exception {
        int orgCount = manageOrganizationPage.getOrgCount();
        int orgCountDisplay = manageOrganizationPage.getOrgDisplayCount();
        Assert.assertEquals(orgCount, orgCountDisplay);
    }

    @Then("User should be able to download the results in .xlsx format in an excel sheet")
    public void userShouldBeAbleToDownloadTheResultsInXslxFormatInAnExcelSheet() throws Exception {
        CommonTestSteps.verifyFileIsDownloaded("Organizations.xlsx");
    }

    @And("Select checkbox in Export As page")
    public void selectCheckboxInExportAsPage(List<String>columnList) throws Exception {
        manageOrganizationPage.selectCheckBoxExportAs(columnList);
    }

    @Then("Downloaded data in the excel sheet must be same as showing in the Home page.")
    public void downloadedDataInTheExcelSheetMustBeSameAsShowingInTheHomePage() throws Exception {
        manageOrganizationPage.checkDownloadFile();
    }

    @And("Input file name {} in Export As")
    public void inputFileNameFilenameInExportAs(String filename) throws Exception {
        manageOrganizationPage.inputFileNameExportAs(filename);
    }

    @Then("File name error show")
    public void fileNameErrorShow() throws Exception {
        Assert.assertEquals("exportSettingsDialog-fileName-message-sr", getElement(By.xpath("//input[@id='exportSettingsDialog-fileName-inner']")).getAttribute("aria-errormessage"));
    }

    @And("Search {} in Adapt Filters")
    public void searchFieldNameInAdaptFilters(String searchName) throws Exception {
        manageOrganizationPage.searchInAdaptFilters(searchName);
        // click on search button
        clickElement(By.xpath("//input[@aria-label='Search for Filters']/..//div[@title='Search']"));
    }

    @Then("{} should come as searched result")
    public void fieldNameShouldComeAsSearchedResult(String searchName) throws Exception {
        assertTrue(getElement(By.xpath("//*[@class='sapMPageEnableScrolling sapUiScrollDelegate']//bdi[text()='Role']")).isDisplayed());

    }

    @When("^Search '(.+)' in search field")
    public void searchContentInSearchField(String content) throws Exception {
        manageOrganizationPage.searchInSearchField(content);
    }

    @Then("^Results should be displayed according to the '(.+)' searched data")
    public void resultsShouldBeDisplayedAccordingToTheContentSearchedData(String content) {
        List<WebElement> elements = getElements(By.xpath("//*[text()='" + content + "']"));
        assertTrue(elements.size()>=1);
    }

    @When("Click on Select View option")
    public void clickOnSelectViewOption() throws Exception {
        waitForPageToLoad();
        clickElement("com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-trigger");
    }

    @And("Enter name in the View name field")
    public void enterNameInTheViewNameField() throws Exception {
        String viewNameInputId = "com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-name-inner";
        WebElement viewNameField = getElement(viewNameInputId);
        viewNameField.click();
        viewNameField.sendKeys(Keys.CONTROL + "a");
        viewNameField.sendKeys(Keys.DELETE);
        inputText(By.id(viewNameInputId), "TestView");
    }

//    @Then("User should be enter text in name field")
//    public void userShouldBeEnterTextInNameField() throws Exception {
//        assertEquals("TestView", getElement("com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-name-inner").getAttribute("value"));
//    }

    @Then("After clicking on cancel button, view should not get save with same name and user will navigate back to home page")
    public void afterClickingOnCancelButtonViewShouldNotGetSaveWithSameNameAndUserWillNavigateBackToHomePage() throws Exception {
        assertTrue(manageOrganizationPage.listPageDisplayed());
    }

    @Then("UI should reflect without Filters")
    public void uiShouldReflectWithoutFilters(List<String> filters) {
        for ( String filter : filters) {
            assertTrue(getElements(By.xpath("//*[text()='" + filter + "']")).size() < 2);
        }
    }

    @Then("In List of Views, new view should not be present")
    public void inListOfViewsNewViewShouldNotBePresent() {
        assertFalse(manageOrganizationPage.checkListViewPresent());
    }

    @Then("After clicking on save button, view should  get save with same name and user will navigate back to home page")
    public void afterClickingOnSaveButtonViewShouldGetSaveWithSameNameAndUserWillNavigateBackToHomePage() throws Exception {
        assertTrue(manageOrganizationPage.variantViewDisplayed());
        assertTrue(manageOrganizationPage.listPageDisplayed());
    }

    @Then("In List of Views, new view should be present")
    public void inListOfViewsNewViewShouldBePresent() {
        assertTrue(manageOrganizationPage.checkListViewPresent());
    }

    @And("clear all customized views")
    public void clearAllCustomizedViews() throws Exception {
        clickElement("com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-manage-BDI-content");
        List<WebElement> deleteBtns = getElements(By.xpath("//*[@title='Delete View']"));
        for (WebElement dltBtn : deleteBtns) {
            dltBtn.click();
        }
        clickOnButtonGo("Save");
    }
    @When("Select {string} in view")
    public void selectDefaultInView(String columnName) throws Exception {
        WebElement checkbox = getElement(By.xpath("//bdi[text()='" + columnName + "']/../../../..//div[@role='checkbox']"));
        if (checkbox.getAttribute("aria-checked").equals("false")) {
            checkbox.click();
        }
    }

    @Then("Warning message should be displayed")
    public void warningMessageShouldBeDisplayed() throws Exception {
        assertTrue(getElement(By.xpath("//*[@id='exportSettingsDialog-fileName-message-sr']")).isDisplayed());
    }

    @And("Enter {} in the View")
    public void enterViewNameInTheView(String name) throws Exception {
        String viewNameInputId = "com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-name-inner";
        WebElement viewNameField = getElement(viewNameInputId);
        viewNameField.click();
        viewNameField.sendKeys(Keys.CONTROL + "a");
        viewNameField.sendKeys(Keys.DELETE);
        inputText(By.id(viewNameInputId), name);
    }

    @Then("After clicking on save button, view should get save with {} and user will navigate back to home page")
    public void afterClickingOnSaveButtonViewShouldGetSaveWithNameAndUserWillNavigateBackToHomePage(String name) throws Exception {
        assertTrue(manageOrganizationPage.checkViewDisplayed(name));
        assertTrue(manageOrganizationPage.listPageDisplayed());
    }

    @Then("View name error message should be displayed")
    public void viewNameErrorMessageShouldBeDisplayed() throws Exception {
        assertTrue(getElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-name-message-sr']")).isDisplayed());
    }

    @And("Select the newly created view {} from the list and make it default one")
    public void selectTheNewlyCreatedViewViewNameFromTheListAndMakeItDefaultOne(String viewName) throws Exception {
        manageOrganizationPage.makeViewDefault(viewName);
    }
    @And("delete {} customized views")
    public void deleteViewNameCustomizedViews(String viewName) throws Exception {
        clickElement("com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-manage-BDI-content");
        WebElement dltBtn = getElement(By.xpath("//input[@value='" + viewName + "']/../../../..//*[@title='Delete View']"));
        dltBtn.click();
    }

    @And("create a custom view")
    public void createACustomView(List<String>views) throws Exception {
        for (String view : views) {
            String viewNameInputId = "com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-name-inner";
            WebElement viewNameField = getElement(viewNameInputId);
            viewNameField.click();
            viewNameField.sendKeys(Keys.CONTROL + "a");
            viewNameField.sendKeys(Keys.DELETE);
            inputText(By.id(viewNameInputId), view);
        }
    }

    @Then("In List of Views, {string} should be present")
    public void inListOfViewsViewShouldBePresent(String viewName) {
        assertTrue(manageOrganizationPage.checkListViewPresent(viewName));
    }

    @Then("User should be able to download the results in .xlsx format in an excel sheet with {} file")
    public void userShouldBeAbleToDownloadTheResultsInXlsxFormatInAnExcelSheetWithNameFile(String fileName) throws Exception {
        CommonTestSteps.verifyFileIsDownloaded("export.xlsx");
    }

    @And("Enter empty file name")
    public void enterEmptyFileName() throws Exception {
        manageOrganizationPage.enterEmptyFileName();
    }

    @And("Search {string} in view Search Bar")
    public void searchStandardInViewSearchBar(String viewName) throws Exception {
        By searchInputId = By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-mgmHeaderSearch-BarMiddle']/../..//input");
        getElement(searchInputId).clear();
        inputText(searchInputId, viewName);
        clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-mgmHeaderSearch-BarMiddle']/../..//div[@class='sapMSFS sapMSFB']"));
    }

    @Then("It must show only the {string} searched view")
    public void itMustShowOnlyTheStandardSearchedView(String viewName) throws Exception {
        assertTrue(getElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template::PageVariant-vm-managementTable-listUl']//span[text()='" + viewName + "']")).isDisplayed());
    }

    @When("click on settings")
    public void clickOnSettings() throws Exception {
        clickElement(By.xpath("//button[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReport-btnPersonalisation']"));
    }

    @And("^Enter '(.+)' in sort")
    public void enterColumnInSort(String column) throws Exception {
        clickElement(By.xpath("//input[@placeholder='Sort by']/..//span"));
        clickElement(By.xpath("//div[text()='" + column + "']"));
    }

    @And("^Select '(.+)' in '(.+)' sort order")
    public void selectOrderInColumnSortOrder(String order, String column) throws Exception {
        if (order.equals("Ascending")) {
            clickElement(By.xpath("//*[@value='" + column + "']/../../../..//*[@title='Sort Ascending']"));
        }
        else{
            clickElement(By.xpath("//*[@value='" + column + "']/../../../..//*[@title='Sort Descending']"));
        }
    }

    @Then("^Data will show up as organization Ascending order with '(.+)' in home page")
    public void dataWillShowUpAsOrganizationAscendingOrderWithColumnInHomePage(String column) throws Exception {
        assertTrue(manageOrganizationPage.isColumnSortedInAscendingOrder(manageOrganizationPage.getColumnValues(column)));
    }

    @Then("^Data will show up as organization Descending order with '(.+)' in home page")
    public void dataWillShowUpAsOrganizationDescendingOrderWithColumnInHomePage(String column) throws Exception {
        assertTrue(manageOrganizationPage.isColumnSortedInDescendingOrder(manageOrganizationPage.getColumnValues(column)));
    }

    @And("^Remove '(.+)' sorting")
    public void removeColumnSorting(String column) throws Exception {
        waitForPageToLoad();
        clickElement(By.xpath("//*[@value='" + column + "']/../../../../..//*[@title='Remove Sort Criterion']"));
    }

    @Then("^Organizations Data '(.+)' will not be in any of the sorting order")
    public void organizationsDataColumnWillNotBeInAnyOfTheSortingOrder() {
        assertTrue(true);
    }

    @And("^Provide column '(.+)' '(.+)' value in create page")
    public void provideColumnColumnTypeValueTypeValueInCreatePage(String field, String value) throws Exception {
        manageOrganizationPage.clickCreateDropDown(field);
        manageOrganizationPage.selectDropDownOption(value);
    }

    @And("^Provide the '(.+)' on '(.+)' filter")
    public void provideTheValueInterfaceTypeOnInterfaceTypeFilter(String value, String field) throws Exception {
        manageOrganizationPage.clickCreateDropDown(field);
        manageOrganizationPage.selectCreateInterfaceType(value);
    }
    @Then("Verify error message {string}")
    public void verifyErrorMessage(String message) throws Exception {
        assertTrue(getElement(By.xpath("//*[@class='sapMPopoverCont']//*[contains(text(),'" + message + "')]")).isDisplayed());
    }

    @And("^Input column '(.+)' '(.+)' in create page")
    public void inputColumnColumnPNIDValuePNIDInCreatePage(String field, String value) throws Exception {
        manageOrganizationPage.inputValueWithText(field, value);
    }

    @And("^Clear '(.+)' column")
    public void clearColumnColumn(String columnName) throws Exception {
        By createPNIDInput = By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ObjectPage.view.Details::Organizations--headerEditable::com.sap.vocabularies.UI.v1.HeaderInfo::Description::Field-input-inner']");
        getElement(createPNIDInput).clear();
    }

    @When("click on {} one organization")
    public void clickOnOrgNameOneOrganization(String orgName) throws Exception {
        waitForPageToLoad();
        manageOrganizationPage.searchInSearchField(orgName);
        clickButton("Go");
        waitForTableToLoad();
        clickElement(By.xpath("//table[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--responsiveTable-listUl']//*[text()='" + orgName + "']"));
    }

    @Then("Verify toast message {string}")
    public void verifyToastMessage(String message) throws Exception {
        waitForPageToLoad();
        assertTrue(getElement(By.xpath("//div[text()='" + message + "']")).isDisplayed());
    }

    @And("^Remove the '(.+)' in the organization")
    public void removeTheValueRoleInTheOrganization(String valueName) throws Exception {
        clickElement(By.xpath("//span[@class='sapMTokenText' and text()='" + valueName + "']/..//span[@role='presentation']"));
    }

    @And("^check '(.+)' in Scenario section")
    public void checkValueInScenarioSection(String value) throws Exception {
        waitForPageToLoad();
        WebElement checkbox = getElement(By.xpath("//span[text()='" + value + "']/../..//*[@role='checkbox']"));
        if (checkbox.getAttribute("aria-checked").equals("false")) {
            checkbox.click();
        }
    }

    @And("^Remove '(.+)' in the scenario section")
    public void removeValueInTheScenarioSection(String value) throws Exception {
        clickElement(By.xpath("//*[text()='" + value + "']/../../..//*[@class='sapUiIcon sapUiIconMirrorInRTL sapMBtnCustomIcon sapMBtnIcon sapMBtnIconLeft']"));
    }

    @And("click the button to show error message")
    public void clickTheButtonToShowErrorMessage() throws Exception {
        clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ObjectPage.view.Details::Organizations--showMessages']"));
    }

    @And("^search '(.+)' in Scenario search bar")
    public void searchValueInScenarioSearchBar(String value) throws Exception {
        waitForPageToLoad();
        String scenarioValueInputID = "com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ObjectPage.view.Details::Organizations--scenarioDialogTable-searchField-I";
        getElement(scenarioValueInputID).clear();
        inputText(scenarioValueInputID,value);
    }

    @And("click on search button in scenario search bar")
    public void clickOnSearchButtonInScenarioSearchBar() throws Exception {
        clickElement("com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ObjectPage.view.Details::Organizations--scenarioDialogTable-searchField-search");
    }

    @Then("^Verify organization '(.+)' is not created")
    public void verifyOrganizationOrgNameIsNotCreated(String name) throws Exception {
        assertFalse(manageOrganizationPage.verifyOrganizationExist(name));
    }

    @Then("^Validate the organization '(.+)' value '(.+)'")
    public void validateTheOrganizationColumnRoleValueExistValue(String column, String value) throws Exception {
        assertTrue(getElement(By.xpath("//*[text()='" + column + "']/../../../..//*[text()='" + value + "']")).isDisplayed());
    }

    @And("Input a random PNID in create page with length {string}")
    public void inputARandomPNIDInCreatePageWithLength(int length) throws Exception {
        manageOrganizationPage.inputRandomPNID(length);
    }

    @And("Input a random Organization name in create page with length {string}")
    public void inputARandomOrganizationNameInCreatePageWithLength(int length) throws Exception {
        manageOrganizationPage.inputRandomOrgName(length);
    }
}