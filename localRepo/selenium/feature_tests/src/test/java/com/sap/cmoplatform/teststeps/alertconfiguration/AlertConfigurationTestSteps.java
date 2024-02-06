package com.sap.cmoplatform.teststeps.alertconfiguration;

import com.sap.cmoplatform.pages.CommonComponentPage;
import com.sap.cmoplatform.pages.GenericPage;
import com.sap.cmoplatform.pages.alertconfiguration.AlertConfigurationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import scala.util.Random;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AlertConfigurationTestSteps {
    private AlertConfigurationPage alertConfigurationPage = new AlertConfigurationPage();
    private GenericPage genericPage = new GenericPage();
    private String ruleName = "Auto_test_" + new Random().nextInt(900);

    @Then("^the title of the detail page is '(.+)'$")
    public void theTitleOfThePageIsTitle(String title) throws Exception {
        assertEquals(title, alertConfigurationPage.getDetailPageTitle());
    }

    @Then("^the title of the master page is '(.+)'$")
    public void theTitleOfTheMasterPageIsMasterPageTitle(String title) throws Exception {
        assertTrue(alertConfigurationPage.getElement(By.id(alertConfigurationPage.masterPageTitle)).getText().contains(title));
    }

    @Then("verify available tabs on the alert configuration page")
    public void verifyAvailableTabsOnTheAlertConfigurationPage() throws Exception {
        assertTrue(alertConfigurationPage.getNotificationTab().isDisplayed());
        assertTrue(alertConfigurationPage.getConditionsTab().isDisplayed());
    }

    @Then("^the title of create recipient pop up title is '(.+)'$")
    public void theTitleOfCreateRecipientPopUpTitleIsCreateRecipientTitle(String title) throws Exception {
        assertTrue(alertConfigurationPage.getCreateRecipientDialogTitle().contains(title));
    }

    @And("^the recipient name '(.+)' is entered$")
    public void theRecipientNameNameIsEntered(String name) throws Exception {
        alertConfigurationPage.enterRecipientName(name);
    }

    @And("^the recipient email '(.+)' is entered$")
    public void theRecipientEmailEmailIsEntered(String email) throws Exception {
        alertConfigurationPage.enterRecipientEmail(email);
    }

    @And("^the (Add|Delete) button is clicked$")
    public void theAddButtonIsClicked(String buttonName) throws Exception {
        if (buttonName.equals("Add")) {
            alertConfigurationPage.clickAddButton();
        }
        if (buttonName.equals("Delete")) {
            alertConfigurationPage.clickDeleteButton();
        }
    }

    @And("the Add Rule button is clicked")
    public void theAddRuleButtonIsClicked() throws Exception {
        alertConfigurationPage.clickAddRuleButton();
    }

    @Then("^the title of create rule pop up title is '(.+)'$")
    public void theTitleOfCreateRulePopUpTitleIsCreateRuleTitle(String title) throws Exception {
        assertEquals(title, alertConfigurationPage.getCreateAlertRuleDialogTitle());
    }

    @And("^the rule name '(.+)' is entered$")
    public void theRuleNameNameIsEntered(String name) throws Exception {
        alertConfigurationPage.enterRuleName(name);
    }

    @And("^the rule name is entered$")
    public void theRuleNameNameIsEntered1() throws Exception {
        alertConfigurationPage.enterRuleName(ruleName);
    }

    @Then("validate duplicate error Toast message")
    public void theToastMessageMessageContains() throws Exception {
        assertEquals(genericPage.getToastMessage(), "Rule with name: " + ruleName + " already exists.");
    }

    @And("^the rule description '(.+)' is entered$")
    public void theRuleDescriptionDescriptionIsEntered(String desc) throws Exception {
        alertConfigurationPage.enterRuleDescriptionId(desc);
    }

    @Then("^the first rule (contains|does not contains) '(.+)'$")
    public void theFirstRuleContainsName(String id, String name) throws Exception {
        if (id.equals("contains")) {
            assertTrue(alertConfigurationPage.getFirstRuleFromRulesGroup(name).isDisplayed());
        }
        if (id.equals("does not contains")) {
            try {
                assertFalse(alertConfigurationPage.getFirstRuleFromRulesGroup(name).isDisplayed());
            } catch (Exception e) {
                //handling Element not found exception
            }
        }
    }

    @Then("^the first rule (contains|does not contains)$")
    public void theFirstRuleContainsName1(String id) throws Exception {
        if (id.equals("contains")) {
            assertTrue(alertConfigurationPage.getFirstRuleFromRulesGroup(ruleName).isDisplayed());
        }
        if (id.equals("does not contains")) {
            try {
                assertFalse(alertConfigurationPage.getFirstRuleFromRulesGroup(ruleName).isDisplayed());
            } catch (Exception e) {
                //handling Element not found exception
            }
        }
    }

    @And("^search the alert rule '(.+)'$")
    public void searchTheAlertRuleName(String name) throws Exception {
        alertConfigurationPage.enterSearchField(name);
        alertConfigurationPage.clickSearchIcon();

    }

    @Then("^the warning pop up contains '(.+)' and '(.+)'$")
    public void theDeletePopUpContainsDeletePopUpMessage(String header, String message) throws Exception {
        assertTrue(alertConfigurationPage.getDeletePopUpHeader(header).isDisplayed());
        assertEquals(message, alertConfigurationPage.getDeletePopUpMessage());
    }

    @Then("^the (Status|Alert in App) field is disabled$")
    public void theStatusFieldIsDisabled(String id) throws Exception {
        assertEquals("true", alertConfigurationPage.getFlagSwitchFieldId(id).getAttribute("aria-disabled"));
    }

    @Then("^the default rule status is '(.+)'$")
    public void theDefaultRuleStatusIsStatus(String status) throws Exception {
        assertEquals(status, alertConfigurationPage.getDefaultAlertStatus());
    }

    @And("^click on (Status|Alert) switch '(.+)' button$")
    public void clickOnStatusSwitchButton(String name, String flag) throws Exception {
        alertConfigurationPage.getAlertFlag(name, flag).click();
    }

    @Then("^the alert rule status is '(.+)'$")
    public void theAlertRuleStatusIsStatus(String status) throws Exception {
        assertEquals(status, alertConfigurationPage.getAlertStatus());
    }

    @Then("recipient table contains")
    public void recipientTableContains(List<String> expectedValues) throws Exception {
        List<String> actualValues = alertConfigurationPage.getRecipientsFromTable();
        System.out.println("actualValues" + actualValues);
        System.out.println("expectedValues" + expectedValues);
        assertEquals(actualValues, expectedValues);
    }

    @Then("^the recipient count is '(.+)'$")
    public void theRecipientCountIsCount(String number) throws Exception {
        String text = alertConfigurationPage.getRecipientTableHeader();
        assertTrue(text.substring(text.indexOf("(") + 1, text.indexOf(")")).equals(number));
    }

    @And("select the recipient from table")
    public void selectTheRecipientFromTable() throws Exception {
        alertConfigurationPage.selectRecipientRecord();
    }

    @Then("^the rule name in review page is '(.+)'$")
    public void theRuleNameInReviewPageIsName(String name) throws Exception {
        assertEquals(name, alertConfigurationPage.getReviewPageRuleName());
    }

    @Then("^the rule name in review page$")
    public void theRuleNameInReviewPageIsName1() throws Exception {
        assertEquals(ruleName, alertConfigurationPage.getReviewPageRuleName());
    }

    @And("^click on (notification|conditions) menu$")
    public void clickOnConditionsMenu(String name) throws Exception {
        if (name.equals("conditions")) {
            alertConfigurationPage.getConditionsTab().click();
        }
        if (name.equals("notification")) {
            alertConfigurationPage.getNotificationTab().click();
        }
    }


    @Then("^title of Condition table header is '(.+)'$")
    public void titleOfConditionTableHeaderIsHeader(String header) throws Exception {
        assertEquals(header, alertConfigurationPage.getConditionTableHeader());
    }

    @Then("the table columns header contains")
    public void conditionsTableColumnsHeaderContains(List<String> header) throws Exception {
        assertEquals(header, alertConfigurationPage.getTableColumnHeader());
    }

    @Then("^title of Recipient table header is '(.+)'$")
    public void titleOfRecipientTableHeaderIsRecipientHeader(String header) throws Exception {
        assertTrue(alertConfigurationPage.getRecipientTableHeader().contains(header));
    }

    @Then("conditions table contains")
    public void conditionsTableContains(List<String> expectedValue) throws Exception {
        assertEquals(alertConfigurationPage.getConditionsFromTable(), expectedValue);
    }

    @Then("^verify (Operator|Values) dropdown list for (VERIFIED STATUS|ADDITIONAL INFORMATION|VERIFICATION FAILURE REASON|LINKTYPE|GTIN|REQUESTER GLN|RESPONDER GLN|CONTEXT|HTTP RESPONSE CODE) field$")
    public void verifyOperatorAndValueDropdownListForField(String field, String fieldName, List<String> expectedValue) throws Exception {
        alertConfigurationPage.waitForPageToLoad();
        alertConfigurationPage.getElement(alertConfigurationPage.dropdownIcon).click();
        alertConfigurationPage.selectDropDownFilterInput(String.valueOf(alertConfigurationPage.fieldDropdownID), fieldName);
        assertEquals(expectedValue, alertConfigurationPage.getOperatorAndValueList(fieldName, field));
    }

    @Then("^the conditions table contains '(.+)' conditions$")
    public void theConditionsTableContainsCountConditions(String count) throws Exception {
        assertEquals(alertConfigurationPage.getConditionsCount() + "", count);
    }

    @And("the first condition is selected from table")
    public void theFirstConditionIsSelectedFromTable() throws Exception {
        alertConfigurationPage.selectFirstConditionFromTable();
    }

    @Then("validate field drop down values")
    public void validateFieldDropDownValues() throws Exception {
        alertConfigurationPage.clickFieldDropDown();
        assertEquals(Arrays.asList(alertConfigurationPage.expectedConditionFieldValues), alertConfigurationPage.getFieldValues());
    }

    @And("^select the field (ADDITIONAL INFORMATION|HTTP RESPONSE CODE)$")
    public void selectTheFieldADDITIONALINFO(String fieldName) throws Exception {
        alertConfigurationPage.getElement(alertConfigurationPage.dropdownIcon).click();
        alertConfigurationPage.selectDropDownFilterInput(alertConfigurationPage.getElement(alertConfigurationPage.fieldDropdownID), fieldName);
    }

    @And("^the (VERIFIED STATUS|ADDITIONAL INFORMATION|HTTP RESPONSE CODE) condition is selected from table$")
    public void theConditionIsSelectedFromTable(String field) throws Exception {
        alertConfigurationPage.selectTheConditions(field);
    }

    @And("^select the value '(.+)' from Values dropdown list$")
    public void selectTheValueNewValueFromValuesDropdownList(String value) throws Exception {
        alertConfigurationPage.selectTheValue(value);
    }


    @And("the button labeled Yes is clicked in delete pop up")
    public void theButtonLabeledYesIsClickedInDeleteAlertRulePopUp() throws Exception {
        alertConfigurationPage.clickDeleteAlertRuleYesButton();
    }

    @And("^select the alert rule '(.+)'$")
    public void selectTheAlertRuleName(String name) throws Exception {
        alertConfigurationPage.getFirstRuleFromRulesGroup(name).click();
    }

    @And("^select the alert rule$")
    public void selectTheAlertRuleName1() throws Exception {
        alertConfigurationPage.getFirstRuleFromRulesGroup(ruleName).click();
    }

    @Then("validate create new alert rule pop up fields")
    public void validateCreateNewAlertRulePopUpFields() throws Exception {
        assertTrue(alertConfigurationPage.getAlertFlagElement().isDisplayed());
        assertTrue(alertConfigurationPage.getStatusFlagElement().isDisplayed());
    }

    @Then("^the flag status of alert rule is '(.+)' and '(.+)'$")
    public void theDefaultFlagStatusOnCreationOfNewRuleIsStatusAndAlertInApp(String status, String flag) throws Exception {
        assertEquals(alertConfigurationPage.getFlagStatusInDetailPage().get(0).getText(), status);
        assertEquals(alertConfigurationPage.getFlagStatusInDetailPage().get(1).getText(), flag);

    }

    @And("^create alert rule if does not exist '(.+)' '(.+)'$")
    public void createAlertRuleIfDoesNotExist(String ruleName, String desc) throws Exception {
        if (!alertConfigurationPage.getFirstRuleFromRulesGroup(ruleName).isDisplayed()) {
            alertConfigurationPage.clickAddRuleButton();
            alertConfigurationPage.enterRuleName(ruleName);
            alertConfigurationPage.enterRuleDescriptionId(desc);
            genericPage.clickButton("Save");

        }
    }

    @Then("the error message {string} appears")
    public void theErrorMessageErrorMessageAppears(String expectedMessage) throws Exception {
        assertEquals("Message does not appear or is wrong.", expectedMessage, alertConfigurationPage.getErrorMessage());

    }
}