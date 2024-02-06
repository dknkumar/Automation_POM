package com.sap.cmoplatform.teststeps.configureVerificationResponses;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.CommonComponentPage;
import com.sap.cmoplatform.pages.GenericPage;
import com.sap.cmoplatform.pages.LauncherPage;
import com.sap.cmoplatform.pages.configureVerificationResponses.ConfigureVerificationResponsesPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static org.junit.Assert.*;

public class ConfigureVerificationResponsesTestSteps {

    private ConfigureVerificationResponsesPage configVerificationResponsesPage = new ConfigureVerificationResponsesPage();
    private CommonComponentPage commonComponentPage = new CommonComponentPage();
    private GenericPage genericPage = new GenericPage();
    private LauncherPage launcherPage = new LauncherPage();
    private String[] dispositionRuleNames, Gln, ActiveSwitch;
    private String reason, activeSwitch;

    private List<String> reasonList = new ArrayList<>();
    private List<String> activeSwitchList = new ArrayList<>();
    private String dispositionName = "auto_test_" + new Random().nextInt(900);
    private String dispositionName2 = "auto_test_" + new Random().nextInt(900);

    @Then("^the panel header is '(.+)'$")
    public void thePanelHeaderIs(String gs1Header) throws Exception {
        System.out.println(configVerificationResponsesPage.getPanelHeader());
        assertTrue(configVerificationResponsesPage.getPanelHeader().contains(gs1Header));
    }

    @And("click on view gsl scenarios button")
    public void clickOnViewGslScenariosButton() throws Exception {
        configVerificationResponsesPage.clickGs1ScenarioViewBtn();
    }

    @Then("verify the disposition content for gsl scenarios")
    public void theDispositionContentForGslScenarios() throws Exception {
        List<String> expectedDispositionContent = new ArrayList<>();
        List<String> actualDispositionContent = new ArrayList<>();
        expectedDispositionContent.add("B1");
        expectedDispositionContent.add("Product Identifier matches, and manufacturer has provided additional information indicating product is recalled and therefore unfit for distribution.");
        expectedDispositionContent.add("true");
        expectedDispositionContent.add("Recalled");
        expectedDispositionContent.add("-");
        expectedDispositionContent.add("B2");
        expectedDispositionContent.add("Product Identifier matches, and manufacturer has provided additional information indicating product is recalled and therefore unfit for distribution.");
        expectedDispositionContent.add("false");
        expectedDispositionContent.add("Recalled");
        expectedDispositionContent.add("Manufacturer_policy");
        expectedDispositionContent.add("C1.1");
        expectedDispositionContent.add("Product Identifier matches, and manufacturer has provided additional information, indicating that product is suspect and therefore unfit for distribution.");
        expectedDispositionContent.add("false");
        expectedDispositionContent.add("Suspect");
        expectedDispositionContent.add("Not_for_re-distribution");
        expectedDispositionContent.add("C1.2");
        expectedDispositionContent.add("Product Identifier matches, and manufacturer has provided additional information, indicating that product is illegitimate and therefore unfit for distribution.");
        expectedDispositionContent.add("false");
        expectedDispositionContent.add("Illegitimate");
        expectedDispositionContent.add("Not_for_re-distribution");
        expectedDispositionContent.add("C2");
        expectedDispositionContent.add("Product Identifier matches, and manufacturer has additional information to suggest that product is unfit for distribution. However the additional information is not provided.");
        expectedDispositionContent.add("false");
        expectedDispositionContent.add("-");
        expectedDispositionContent.add("Manufacturer_policy");

        actualDispositionContent.addAll((configVerificationResponsesPage.getDispositionContent()));
        assertEquals(actualDispositionContent, expectedDispositionContent);
    }

    @Given("the {} Disposition name {} is entered")
    public void the_first_disposition_name_is_entered(String row, String name) throws Throwable {
        configVerificationResponsesPage.enterDispositionName(row, name);
    }

    @And("the Disposition name with more than 100 char is entered")
    public void the_100CharDisposition_name_is_entered() throws Exception {
        String generatedString = RandomStringUtils.random(102, true, true);
        configVerificationResponsesPage.enterDispositionName("first", generatedString);
    }

    @And("^the (first|second) Disposition name is entered$")
    public void the_Disposition_name_is_entered(String row) throws Throwable {
        if (Objects.equals(row, "second")) {
            configVerificationResponsesPage.enterDispositionName(row, dispositionName2);
        } else {
            configVerificationResponsesPage.enterDispositionName(row, dispositionName);
        }
    }

    @And("select the {} Disposition entered")
    public void selectTheDisposition(String row) throws Exception {
        if (Objects.equals(row, "second")) {
            configVerificationResponsesPage.selectDisposition(dispositionName2);
        } else {
            configVerificationResponsesPage.selectDisposition(dispositionName);
        }
    }

    @And("click on Add Rule button")
    public void clickOnAddRuleButton() throws Exception {
        configVerificationResponsesPage.clickAddRuleButton();

    }

    @Then("verify the maximum allowed length for disposition name field")
    public void verifyTheMaximumAllowedLengthForDispositionNameField() throws Exception {
        configVerificationResponsesPage.waitForPageToLoad();
        configVerificationResponsesPage.getElement(configVerificationResponsesPage.firstDispositionNameInput).sendKeys(Keys.ENTER);
        assertTrue(configVerificationResponsesPage.getElement(configVerificationResponsesPage.firstDispositionNameInput).getAttribute("Value").length() == 100);
    }


    @And("verify the navigation menu in configure verification rules page")
    public void verifyTheNavigationMenuInConfigureVerificationRulesPage() throws Exception {
        assertTrue(configVerificationResponsesPage.getDispositionRuleMenu().isDisplayed());
        assertTrue(configVerificationResponsesPage.getExpiryRuleMenu().isDisplayed());
    }

    @And("^the disposition rule (B1|B2|C1.1|C1.2|C2) is selected for (first|second) record$")
    public void theDispositionRuleBIsSelected(String rule, String row) throws Exception {
        configVerificationResponsesPage.selectDispositionRule(rule, row);
    }

    @And("^select the disposition created$")
    public void selectTheDisposition() throws Exception {
        configVerificationResponsesPage.selectDisposition(dispositionName);
    }

    @And("select the {} disposition created")
    public void selectTheDisposition1(String row) throws Exception {
        if (Objects.equals(row, "second")) {
            configVerificationResponsesPage.selectDisposition(dispositionName2);
        } else {
            configVerificationResponsesPage.selectDisposition(dispositionName);
        }
    }

    @And("^click on Delete Icon$")
    public void click_delete_icon() throws Throwable {
        configVerificationResponsesPage.clickDeleteIcon();
    }

    @And("click on sort rules button")
    public void clickOnSortRulesButton() throws Exception {
        configVerificationResponsesPage.clickSortRulesButton();
    }

    @And("click on Change Log button")
    public void clickOnChangeLogButton() throws Exception {
        configVerificationResponsesPage.clickChangeLogButton();
    }

    @Then("^(first|second) record in Change Log contains$")
    public void historyContainsChangesDetails(String index, Map<String, String> expectedLog) throws Exception {
        if (index.equals("first")) {
            assertEquals(expectedLog.get("action"), configVerificationResponsesPage.getHistoryLogs(1).get(0));
            //   assertEquals(configVerificationResponsesPage.getUserName(), configVerificationResponsesPage.getHistoryLogs(1).get(1));
            assertEquals(dispositionName, configVerificationResponsesPage.getHistoryLogs(1).get(2));
            if (expectedLog.get("action").equals("CREATE")) {
                assertTrue(configVerificationResponsesPage.getHistoryLogs(1).get(3).isEmpty());
                assertEquals(expectedLog.get("StatusVal") + "/" + expectedLog.get("AdditionalInfo") + "/" + expectedLog.get("VerificationFailureReason"), configVerificationResponsesPage.getHistoryLogs(1).get(4));

            } else if (expectedLog.get("action").equals("DELETE")) {
                assertEquals(expectedLog.get("NewStatusVal") + "/" + expectedLog.get("NewAdditionalInfo") + "/" + expectedLog.get("NewVerificationFailureReason"), configVerificationResponsesPage.getHistoryLogs(1).get(3));
                assertTrue(configVerificationResponsesPage.getHistoryLogs(1).get(4).equals("-/-/-"));
            } else {
                assertEquals(expectedLog.get("StatusVal") + "/" + expectedLog.get("AdditionalInfo") + "/" + expectedLog.get("VerificationFailureReason"), configVerificationResponsesPage.getHistoryLogs(1).get(3));
                assertEquals(expectedLog.get("NewStatusVal") + "/" + expectedLog.get("NewAdditionalInfo") + "/" + expectedLog.get("NewVerificationFailureReason"), configVerificationResponsesPage.getHistoryLogs(1).get(4));

            }

        } else if (index.equals("second")) {
            assertEquals(expectedLog.get("action"), configVerificationResponsesPage.getHistoryLogs(2).get(0));
            //     assertEquals(configVerificationResponsesPage.getUserName(), configVerificationResponsesPage.getHistoryLogs(2).get(1));
            assertEquals(expectedLog.get("dispositionName"), configVerificationResponsesPage.getHistoryLogs(2).get(2));
            if (expectedLog.get("action").equals("CREATE")) {
                assertTrue(configVerificationResponsesPage.getHistoryLogs(2).get(3).isEmpty());
                assertEquals(expectedLog.get("StatusVal") + "/" + expectedLog.get("AdditionalInfo") + "/" + expectedLog.get("VerificationFailureReason"), configVerificationResponsesPage.getHistoryLogs(2).get(4));

            } else if (expectedLog.get("action").equals("DELETE")) {
                assertEquals(expectedLog.get("StatusVal") + "/" + expectedLog.get("AdditionalInfo") + "/" + expectedLog.get("VerificationFailureReason"), configVerificationResponsesPage.getHistoryLogs(2).get(3));
                assertTrue(configVerificationResponsesPage.getHistoryLogs(2).get(4).equals("-/-/-"));
            } else {
                assertEquals(expectedLog.get("StatusVal") + "/" + expectedLog.get("AdditionalInfo") + "/" + expectedLog.get("VerificationFailureReason"), configVerificationResponsesPage.getHistoryLogs(2).get(3));
                assertEquals(expectedLog.get("NewStatusVal") + "/" + expectedLog.get("NewAdditionalInfo") + "/" + expectedLog.get("NewVerificationFailureReason"), configVerificationResponsesPage.getHistoryLogs(2).get(4));

            }

        }
    }

    @And("^the disposition rule is updated to (B1|B2|C1.1|C1.2|C2)$")
    public void theDispositionRuleIsUpdated(String rule) throws Exception {
        configVerificationResponsesPage.updateDispositionRule(rule, dispositionName);
    }

    @And("the {} disposition rule is updated to {}")
    public void theDispositionRuleIsUpdated1(String row, String rule) throws Exception {
        if (Objects.equals(row, "second")) {
            configVerificationResponsesPage.updateDispositionRule(rule, dispositionName2);
        } else {
            configVerificationResponsesPage.updateDispositionRule(rule, dispositionName);
        }
    }

    @And("^add disposition rule if rule does not exist$")
    public void addDispositionRuleIfDispositionRuleDoesNotExist(Map<String, String> dispDetails) throws Throwable {
        if (!configVerificationResponsesPage.getDispositionRuleName(dispDetails.get("Disposition"))) {
            configVerificationResponsesPage.clickAddRuleButton();
            the_Disposition_name_is_entered("first");
            configVerificationResponsesPage.selectDispositionRule(dispDetails.get("Rule"), "first");
            genericPage.clickButton("Save");
            genericPage.clickButton("OK");

        }
    }

    @And("^verify Disposition name '(.+)' already exists in the list$")
    public void verifyDispositionRuleAlreadyExist(String dispositionName) throws Throwable {
        if (configVerificationResponsesPage.getDispositionRuleName(dispositionName)) {
            configVerificationResponsesPage.selectDisposition(dispositionName);
            configVerificationResponsesPage.clickDeleteIcon();
            genericPage.clickButton("OK");

        }
    }

    @Then("the disposition rule records are disabled")
    public void theDispositionRuleRecordsAreDisabled() throws Exception {
        assertFalse(configVerificationResponsesPage.isDispositionNameElementEnabled());
    }


    @And("get disposition rule name records")
    public void getDispositionRuleNameRecords() throws Exception {
        dispositionRuleNames = configVerificationResponsesPage.getDispositionNames();
    }


    @Then("disposition column is sorted by ascending order")
    public void dispositionColumnIsSortedByAscendingOrder() throws Exception {
        assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(configVerificationResponsesPage.getDispositionNames()));
    }

    @Then("disposition column is sorted by descending order")
    public void dispositionColumnIsSortedByDescendingOrder() throws Exception {
        assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(configVerificationResponsesPage.getDispositionNames()));

    }

    @Then("disposition column is sorted by server sequence order")
    public void dispositionColumnIsSortedByServerSequenceOrder() throws Exception {
        assertTrue(commonComponentPage.isColumnSortedInServerSequenceOrder(configVerificationResponsesPage.getDispositionNames(), dispositionRuleNames));
    }

    @Then("^ghost text '(.+)' is displayed for disposition name$")
    public void ghostTextGhostTextIsDisplayedForDispositionName(String text) throws Exception {
        assertEquals(configVerificationResponsesPage.getDispositionName().getAttribute("placeholder"), text);
    }

    @And("click on expiry rules tab")
    public void clickOnExpiryRulesTab() throws Exception {
        configVerificationResponsesPage.getExpiryRuleMenu().click();

    }

    @Then("verify gsl scenarios for expired PI response configuration")
    public void verifyGslScenariosForExpiredPIResponseConfiguration() throws Exception {
        List<String> expectedDispositionContent = new ArrayList<>();
        List<String> actualDispositionContent = new ArrayList<>();
        expectedDispositionContent.add("A2");
        expectedDispositionContent.add("Product Identifier matches, AND Manufacturer has NO information to indicate that product is UNFIT for distribution and provided additional information, indicating product has extended expiration.");
        expectedDispositionContent.add("true");
        expectedDispositionContent.add("ExpirationExtended");
        expectedDispositionContent.add("-");
        expectedDispositionContent.add("B1 Expiration");
        expectedDispositionContent.add("Product Identifier matches, and manufacturer has provided additional information indicating product is expired and therefore unfit for distribution.");
        expectedDispositionContent.add("true");
        expectedDispositionContent.add("Expired");
        expectedDispositionContent.add("-");
        expectedDispositionContent.add("B2 Expiration");
        expectedDispositionContent.add("Product Identifier matches, and manufacturer has provided additional information indicating product is expired and therefore unfit for distribution.");
        expectedDispositionContent.add("false");
        expectedDispositionContent.add("Expired");
        expectedDispositionContent.add("Manufacturer_policy");
        expectedDispositionContent.add("Default");
        expectedDispositionContent.add("Manufacturer decided not to determine verification response based on expiry.");
        expectedDispositionContent.add("true");
        expectedDispositionContent.add("-");
        expectedDispositionContent.add("-");

        actualDispositionContent.addAll((configVerificationResponsesPage.getExpiredDispositionContent()));
        assertEquals(actualDispositionContent, expectedDispositionContent);
    }

    @Then("^verify button label '(.+)' '(.+)' '(.+)' '(.+)' and history links$")
    public void verifyButtonLabelAndHistoryLinks(String expiredPIText, String defaultRule, String B1Rule, String B2Rule) throws Exception {
        assertEquals(configVerificationResponsesPage.getExpiredPITextElement().getText(), expiredPIText);
        assertTrue(configVerificationResponsesPage.getButtonText(defaultRule).isDisplayed());
        assertTrue(configVerificationResponsesPage.getButtonText(B1Rule).isDisplayed());
        assertTrue(configVerificationResponsesPage.getButtonText(B2Rule).isDisplayed());

    }


    @And("^select '(.+)' rule for expired PI$")
    public void selectBExpRuleForExpiredPI(String rule) throws Exception {
        configVerificationResponsesPage.selectExpiryRule(rule);
        configVerificationResponsesPage.clickButton("Save");
        WebDriverWait wait = new WebDriverWait(SeleniumUI5TestUtil.getConfig().getDriver(), 10);
        if (configVerificationResponsesPage.getElements(By.xpath("//*[text()='OK']")).size() > 0) {
            configVerificationResponsesPage.clickButton("OK");
        }
        Thread.sleep(5000);
    }

    @And("click on Disposition rules tab")
    public void clickOnDispositionRulesTab() throws Exception {
        configVerificationResponsesPage.getDispositionRuleMenu().click();
    }

    @Then("^verify expiry rule '(.+)' is set for the user$")
    public void verifyExpiryRuleSetForTheUser(String rule) throws Exception {
        assertTrue(configVerificationResponsesPage.isExpiryRuleSet(rule));

    }

    @Then("expiry rule logs in history contains")
    public void expiryRuleLogsInHistoryContains(Map<String, String> expectedLog) throws Exception {
        assertEquals(expectedLog.get("action"), configVerificationResponsesPage.getExpiryRulesHistoryLogs().get(0));
        //    assertEquals(configVerificationResponsesPage.getUserName(), configVerificationResponsesPage.getExpiryRulesHistoryLogs().get(1));
        if (expectedLog.get("action").equals("UPDATE")) {

            System.out.println("Actual Old  : " + configVerificationResponsesPage.getExpiryRulesHistoryLogs().get(4));
            System.out.println("Expected Old  : " + expectedLog.get("StatusVal") + "/" + expectedLog.get("AdditionalInfo") + "/" + expectedLog.get("VerificationFailureReason"));

            System.out.println("Actual New  : " + configVerificationResponsesPage.getExpiryRulesHistoryLogs().get(5));
            System.out.println("Expected New  : " + expectedLog.get("NewStatusVal") + "/" + expectedLog.get("NewAdditionalInfo") + "/" + expectedLog.get("NewVerificationFailureReason"));

            assertEquals(expectedLog.get("StatusVal") + "/" + expectedLog.get("AdditionalInfo") + "/" + expectedLog.get("VerificationFailureReason"), configVerificationResponsesPage.getExpiryRulesHistoryLogs().get(4));
            assertEquals(expectedLog.get("NewStatusVal") + "/" + expectedLog.get("NewAdditionalInfo") + "/" + expectedLog.get("NewVerificationFailureReason"), configVerificationResponsesPage.getExpiryRulesHistoryLogs().get(5));
        } else {
            assertTrue(configVerificationResponsesPage.getExpiryRulesHistoryLogs().get(3).isEmpty());
            assertEquals(expectedLog.get("StatusVal") + "/" + expectedLog.get("AdditionalInfo") + "/" + expectedLog.get("VerificationFailureReason"), configVerificationResponsesPage.getExpiryRulesHistoryLogs().get(4));

        }
    }

    @And("click on blocked requester gln tab")
    public void clickOnBlockedRequesterGlnTab() throws Exception {
        configVerificationResponsesPage.getBlockListMenu().click();
    }

    @And("click on Add GLN BlockList button")
    public void clickOnAddGLNBlockListButton() throws Exception {
        configVerificationResponsesPage.clickAddGLNBlockListButton();
    }

    @Then("validate the default fields in blocked requester gln table")
    public void validateTheDefaultFieldsInBlockedRequesterGlnTable() throws Exception {
        assertTrue(configVerificationResponsesPage.getGlnBlockListInput().isEnabled());
        System.out.println(configVerificationResponsesPage.getGlnBlockListCompanyNameInput().getAttribute("disabled"));
        assertEquals("true", configVerificationResponsesPage.getGlnBlockListCompanyNameInput().getAttribute("disabled"));
        assertTrue(configVerificationResponsesPage.getGlnBlockListReasonInput().isEnabled());
        assertEquals("ON", configVerificationResponsesPage.getGlnActiveSwitch().getAttribute("data-sap-ui-swt"));
        assertEquals("true", configVerificationResponsesPage.getGlnActiveSwitchField().getAttribute("aria-disabled"));


    }

    @And("^the GLN '(.+)' value is entered$")
    public void theGLNGLNValueIsEntered(String gln) throws Exception {
        configVerificationResponsesPage.getGlnBlockListInput().clear();
        configVerificationResponsesPage.getGlnBlockListInput().sendKeys(gln);
    }

    @Then("^the auto populated value of company Name is '(.+)'$")
    public void theAutoPopulatedValueOfCompanyNameIsCompanyName(String companyName) throws Exception {
        assertEquals(companyName, configVerificationResponsesPage.getGlnBlockListCompanyNameInput().getAttribute("value"));
    }


    @And("the sort gln block list button is clicked")
    public void theSortGlnBlockListButtonIsClicked() throws Exception {
        configVerificationResponsesPage.clickSortGLNBlocklistButton();
    }

//    @And("^the (GLN|Active/Inactive|None) option is selected to sort in (Ascending|Descending) Order$")
//    public void theOptionIsSelectedToSortInAscendingOrder(String field, String sortOrder) throws Exception {
//        if (sortOrder.equals("Ascending")) {
//            configVerificationResponsesPage.clickAscendingOrderButton();
//        } else {
//            configVerificationResponsesPage.clickDescendingOrderButton();
//        }
//
//        configVerificationResponsesPage.selectSortOption(field);
//    }

    @Then("^the (GLN|Active/Inactive|None) column is sorted in (Ascending|Descending) order$")
    public void theGLNColumnIsSortedInAscendingOrder(String columnName, String sortOrder) throws Exception {
        if (sortOrder.equals("Ascending")) {
            if (columnName.equals("GLN")) {
                assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(configVerificationResponsesPage.getGlnColumnValues()));
            }
            if (columnName.equals("Active/Inactive")) {
                assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(configVerificationResponsesPage.getActiveSwitchValues()));
            }
        } else if (sortOrder.equals("Descending")) {
            if (columnName.equals("GLN")) {
                assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(configVerificationResponsesPage.getGlnColumnValues()));
            }
            if (columnName.equals("Active/Inactive")) {
                assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(configVerificationResponsesPage.getActiveSwitchValues()));
            }
        }
    }

    @Then("sort by pop up is displayed")
    public void sortByPopUpIsDisplayed() throws Exception {
        assertTrue(configVerificationResponsesPage.getSortByLabel().isDisplayed());
    }


    @Then("^the title of blocked list history pop up is '(.+)'$")
    public void theTitleOfBlockedListHistoryPopUpIs(String title) throws Exception {
        assertEquals(configVerificationResponsesPage.getShowGLNBlocklistLogTitle(), title);
    }


    @And("^the search by GLN '(.+)' value$")
    public void theSearchByGLNGLNValue(String gln) throws Exception {
        configVerificationResponsesPage.getGlnSearchInputId().clear();
        configVerificationResponsesPage.getGlnSearchInputId().sendKeys(gln);
        configVerificationResponsesPage.clickShowGLNBlocklistLogSearchIcon();
    }

    @Then("^the logs contains entries of GLN '(.+)'$")
    public void theLogsContainsEntriesOfGLNGLN(String gln) throws Exception {
        for (String actualGln : configVerificationResponsesPage.getHistoryGlnValues()) {
            if (gln.length() == actualGln.length()) {
                assertEquals(actualGln, gln);
            } else {
                assertTrue(actualGln.contains(gln));
            }
        }
    }


    @Then("^the ghost text of GLN search field is '(.+)'$")
    public void theGhostTextOfGLNSearchFieldIsGhostText(String text) throws Exception {
        assertTrue(configVerificationResponsesPage.getGlnSearchInputId().getAttribute("placeholder").contains(text));
    }


    @Then("GLN and Active column is sorted by server sequence order")
    public void glnAndActiveInactiveColumnIsSortedByServerSequenceOrder() throws Exception {
        commonComponentPage.isColumnSortedInServerSequenceOrder(configVerificationResponsesPage.getGlnColumnValues(), Gln);
        commonComponentPage.isColumnSortedInServerSequenceOrder(configVerificationResponsesPage.getActiveSwitchValues(), ActiveSwitch);
    }

    @And("get GLN and Active switch column values")
    public void getGLNColumnValues() throws Exception {
        Gln = configVerificationResponsesPage.getGlnColumnValues();
        ActiveSwitch = configVerificationResponsesPage.getActiveSwitchValues();

    }

    @Then("validate the GLN count detail")
    public void validateTheGLNCountDetail() throws Exception {
        String header = configVerificationResponsesPage.getPanelHeader();
        assertEquals(configVerificationResponsesPage.getGlnBlockListCompanyNameColumn().size() + "", header.substring(header.indexOf("(") + 1, header.indexOf(")")));
    }


    @And("^the Reason '(.+)' value is entered$")
    public void theReasonReasonValueIsEntered(String reason) throws Exception {
        configVerificationResponsesPage.getGlnBlockListReasonInput().clear();
        configVerificationResponsesPage.getGlnBlockListReasonInput().sendKeys(reason);
    }

    @Then("verify the maximum allowed length for the field")
    public void verifyTheMaximumAllowedLengthForTheFieldsInVerifyProductPackPage(Map<String, String> field) throws Exception {
        if (field.containsKey("GLN")) {
            assertTrue(configVerificationResponsesPage.getGlnBlockListInput().getAttribute("value").length() == 13);
        }
        if (field.containsKey("Reason")) {
            assertTrue(configVerificationResponsesPage.getGlnBlockListReasonInput().getAttribute("value").length() == 200);

        }
    }


    @And("the GLN value is Cleared")
    public void theGLNValueIsCleared() throws Exception {
        configVerificationResponsesPage.getGlnBlockListInput().clear();
    }


    @And("select the first blocked GLN from the table")
    public void selectTheFirstBlockedGLNFromTheTable() throws Exception {
        configVerificationResponsesPage.selectTheBlockedGLN();
    }

    @And("click on Active switch")
    public void clickOnActiveSwitch() throws Exception {
        configVerificationResponsesPage.getGlnActiveSwitch().click();
    }

    @Then("the provide reason pop up is displayed")
    public void theProvideReasonPopUpIsDisplayed() throws Exception {
        assertTrue(configVerificationResponsesPage.getProvideReasonPopUpTitle().isDisplayed());
    }

    @Then("the error message is displayed")
    public void theErrorMessageIsDisplayed() throws Exception {
        assertTrue(configVerificationResponsesPage.getFieldError().isDisplayed());
    }

    @Then("the Active switch is enabled")
    public void theActiveSwitchIsEnabled() throws Exception {
        assertTrue(configVerificationResponsesPage.getGlnActiveSwitch().isEnabled());
    }

    @And("^the reason '(.+)' is entered in pop up window$")
    public void theReasonReasonIsEnteredInPopUpWindow(String reason) throws Exception {
        configVerificationResponsesPage.enterReason(reason);
    }


    @And("^select the gln '(.+)' record$")
    public void selectTheGlnGLNRecord(String gln) throws Exception {
        configVerificationResponsesPage.selectTheBlockedGlnRecord(gln);

    }
//    @And("^get reason and active switch value from the selected '(.+)' record$")
//    public void getReasonAndActiveSwitchValueFromTheRecord(String gln) throws Exception {
//        reason = configVerificationResponsesPage.getGlnBlockListReasonInputOfGlnRecord(gln);
//        activeSwitch = configVerificationResponsesPage.getGlnActiveSwitchValueOfGlnRecord(gln);
//        System.out.println(reason+ "  " +activeSwitch);
//    }

    @Then("^(first|second) blocked gln logs in history contains$")
    public void blockedGlnLogsInHistoryContains(String index, Map<String, String> expectedLog) throws Exception {
        if (index.equals("first")) {
            assertEquals(expectedLog.get("action"), configVerificationResponsesPage.getBlockedGlnHistoryLogs(1).get(0));
            assertEquals(expectedLog.get("GLN"), configVerificationResponsesPage.getBlockedGlnHistoryLogs(1).get(1));
            //   assertEquals(configVerificationResponsesPage.getUserName(), configVerificationResponsesPage.getBlockedGlnHistoryLogs(1).get(2));
            if (activeSwitchList.get(0).equals("OFF")) {
                assertEquals("inactive" + "/" + reasonList.get(0), configVerificationResponsesPage.getBlockedGlnHistoryLogs(1).get(4));
                assertEquals("active" + "/" + expectedLog.get("Reason"), configVerificationResponsesPage.getBlockedGlnHistoryLogs(1).get(5));

            }
            if (activeSwitchList.get(0).equals("ON")) {
                assertEquals("active" + "/" + reasonList.get(0), configVerificationResponsesPage.getBlockedGlnHistoryLogs(1).get(4));
                assertEquals("inactive" + "/" + expectedLog.get("Reason"), configVerificationResponsesPage.getBlockedGlnHistoryLogs(1).get(5));

            }
        }
        if (index.equals("second")) {
            assertEquals(expectedLog.get("action"), configVerificationResponsesPage.getBlockedGlnHistoryLogs(2).get(0));
            assertEquals(expectedLog.get("GLN"), configVerificationResponsesPage.getBlockedGlnHistoryLogs(2).get(1));
            //  assertEquals(configVerificationResponsesPage.getUserName(), configVerificationResponsesPage.getBlockedGlnHistoryLogs(2).get(2));
            if (activeSwitchList.get(1).equals("OFF")) {
                assertEquals("inactive" + "/" + reasonList.get(1), configVerificationResponsesPage.getBlockedGlnHistoryLogs(2).get(4));
                assertEquals("active" + "/" + expectedLog.get("Reason"), configVerificationResponsesPage.getBlockedGlnHistoryLogs(2).get(5));

            }
            if (activeSwitchList.get(1).equals("ON")) {
                assertEquals("active" + "/" + reasonList.get(1), configVerificationResponsesPage.getBlockedGlnHistoryLogs(2).get(4));
                assertEquals("inactive" + "/" + expectedLog.get("Reason"), configVerificationResponsesPage.getBlockedGlnHistoryLogs(2).get(5));

            }
        }

    }


    @And("^click on Active switch for selected the gln '(.+)'$")
    public void clickOnActiveSwitchForSelectedTheGlnGLN(String gln) throws Exception {
        configVerificationResponsesPage.clickOnActiveSwitchForTheSelectedRecord(gln);
    }

    @And("^get reason and active switch value from the selected '(.+)' record$")
    public void getReasonAndActiveSwitchValueFromTheRecord(String gln) throws Exception {
        reasonList.add(configVerificationResponsesPage.getGlnBlockListReasonInputOfGlnRecord(gln).getAttribute("value"));
        activeSwitchList.add(configVerificationResponsesPage.getGlnActiveSwitchValueOfGlnRecord(gln));
        System.out.println(reason + "  " + activeSwitch);
    }


    @Then("^the selected GLN '(.+)' contains$")
    public void theSelectedGLNGLNContainsReasonReason(String gln, Map<String, String> field) throws Exception {
        if (field.containsKey("Reason")) {
            assertEquals(field.get("Reason"), configVerificationResponsesPage.getGlnBlockListReasonInputOfGlnRecord(gln).getAttribute("value"));
        }
        if (field.containsKey("ActiveSwitch")) {
            if (configVerificationResponsesPage.getGlnActiveSwitchValueOfGlnRecord(gln).equals("OFF")) {
                assertEquals(activeSwitchList.get(0), "ON");
            } else {
                assertEquals(activeSwitchList.get(0), "OFF");

            }
        }
    }

    @And("^the Reason '(.+)' value is entered for selected gln '(.+)'$")
    public void theReasonReasonValueIsEnteredForSelectedGlnGLN(String reason, String gln) throws Exception {
        configVerificationResponsesPage.getGlnBlockListReasonInputOfGlnRecord(gln).clear();
        configVerificationResponsesPage.getGlnBlockListReasonInputOfGlnRecord(gln).sendKeys(reason);
    }


    @And("^the gln '(.+)' is added to blocklist$")
    public void theGlnGLNIsAddedToBlocklist(String gln) throws Exception {
        configVerificationResponsesPage.selectTheBlockedGlnRecord(gln);
        if (configVerificationResponsesPage.getGlnActiveSwitchValueOfGlnRecord(gln).equals("OFF")) {
            configVerificationResponsesPage.clickOnActiveSwitchForTheSelectedRecord(gln);

            configVerificationResponsesPage.getGlnBlockListReasonInputOfGlnRecord(gln).clear();
            configVerificationResponsesPage.getGlnBlockListReasonInputOfGlnRecord(gln).sendKeys("Auto_test_MAH");
            genericPage.clickButton("Add");
            genericPage.clickButton("Save");
            genericPage.clickButton("OK");
        }
    }

    @Then("^the selected GLN '(.+)' contains old values$")
    public void theSelectedGLNGLNContainsOldValues(String gln) throws Exception {
        assertEquals(configVerificationResponsesPage.getGlnBlockListReasonInputOfGlnRecord(gln).getAttribute("value"), reasonList.get(0));
        assertEquals(configVerificationResponsesPage.getGlnActiveSwitchValueOfGlnRecord(gln), activeSwitchList.get(0));

    }


    @Then("warning message is not displayed")
    public void warningMessageIsNotDisplayedForTheExpirationDateField() {
        try {
            assertFalse(configVerificationResponsesPage.isWarningBorderDisplayed());
        } catch (Exception e) {

        }
    }

}
