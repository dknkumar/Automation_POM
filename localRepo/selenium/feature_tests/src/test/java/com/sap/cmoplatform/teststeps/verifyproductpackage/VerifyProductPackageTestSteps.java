package com.sap.cmoplatform.teststeps.verifyproductpackage;

import com.sap.cmoplatform.pages.GenericPage;
import com.sap.cmoplatform.pages.verifyproductpackage.VerifyProductPackagePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.Map;

import static org.junit.Assert.*;

public class VerifyProductPackageTestSteps {

    private VerifyProductPackagePage verifyProductPackagePage = new VerifyProductPackagePage();
    private GenericPage genericPage = new GenericPage();
    String senderGln = "";
    String gs1Element = "";

    @And("^the GTIN value '(.+)' is entered$")
    public void the_gtin_value_is_entered(String gtin) throws Throwable {
        verifyProductPackagePage.enterGTIN(gtin);
    }

    @And("^the Batch Number value '(.+)' is entered$")
    public void the_batch_number_value_is_entered(String batchnumber) throws Throwable {
        verifyProductPackagePage.enterBatchNumber(batchnumber);
    }

    @And("^the Expiration Date value '(.+)' is entered$")
    public void the_expiration_date_value_is_entered(String expirationDate) throws Throwable {
        verifyProductPackagePage.enterExpirationDate(expirationDate);
    }

    @And("^the Serial Number value '(.+)' is entered$")
    public void the_serial_number_value_is_entered(String serialnumber) throws Throwable {
        verifyProductPackagePage.enterSerialNumber(serialnumber);
    }

    @And("^click on verify button$")
    public void click_on_verify_button() throws Throwable {
        verifyProductPackagePage.clickVerifyButton();
    }


    @And("^the Batch Number with 100 characters '(.+)' is entered$")
    public void the_batch_number_with_100_characters_is_entered(String batchnumber) throws Throwable {
        verifyProductPackagePage.enterBatchNumber(batchnumber);
    }


    @And("^Verify (.+) field has 100 characters$")
    public void verify_field_has_100_characters(String batchnumber) throws Throwable {
        assertEquals(batchnumber, verifyProductPackagePage.getBatchNumber());
    }

    @And("^check correct values are populated on Verification screen$")
    public void check_correct_values_are_populated_for_are_populated_on_verification_screen(Map<String, String> expectedValue) throws Throwable {
        assertEquals(verifyProductPackagePage.getGtin(), expectedValue.get("GTIN"));
        assertEquals(expectedValue.get("BatchNumber"), verifyProductPackagePage.getBatchNumber());
        assertEquals(expectedValue.get("ExpirationDate"), verifyProductPackagePage.getExpirationDateValue());
        assertEquals(expectedValue.get("SerialNumber"), verifyProductPackagePage.getSerialNumber());
        assertEquals(expectedValue.get("GS1Element"), verifyProductPackagePage.getGS1Element());
    }

    @And("^click on Verification Tab$")
    public void click_on_verification_tab() throws Throwable {
        verifyProductPackagePage.clickVerificationTab();
    }

    @Then("GSl Element contains the decoded value")
    public void gsElementContainsTheDecodedValue(Map<String, String> expectedDetails) throws Exception {
        if (expectedDetails.containsKey("GTIN")) {

            assertEquals(expectedDetails.get("GTIN"), verifyProductPackagePage.getGtinFromGS1Element(verifyProductPackagePage.getGS1Element()));
        }
        if (expectedDetails.containsKey("BatchNumber")) {

            assertEquals(expectedDetails.get("BatchNumber"), verifyProductPackagePage.getBatchNumberFromGS1Element(verifyProductPackagePage.getGS1Element()));
        }
        if (expectedDetails.containsKey("SerialNumber")) {

            assertEquals(expectedDetails.get("SerialNumber"), verifyProductPackagePage.getSerialNumberFromGS1Element(verifyProductPackagePage.getGS1Element()));
        }
        if (expectedDetails.containsKey("ExpirationDate")) {

            assertEquals(expectedDetails.get("ExpirationDate"), verifyProductPackagePage.getExpirationDateFromGS1Element(verifyProductPackagePage.getGS1Element()));
        }
        if (expectedDetails.containsKey("DatePickerExpirationDate")) {
            assertEquals(verifyProductPackagePage.getExpirationDateValue(), verifyProductPackagePage.getExpirationDateFromGS1Element(verifyProductPackagePage.getGS1Element()));
        }


    }

    @And("^the Expiration Date value (future|past|current) '(.+)' is selected from date picker calender$")
    public void theExpirationDateValueExpirationDateIsSelectedFromDatePickerCalender(String identifier, String date) throws Exception {
        if (identifier.equals("future")) {
            verifyProductPackagePage.selectFutureDateFromCalender(date);
        }
        if (identifier.equals("past")) {
            verifyProductPackagePage.selectPreviousDateFromCalender(date);
        }
        if (identifier.equals("current")) {
            verifyProductPackagePage.selectCurrentDateFromCalender();
        }
    }


    @And("there is a warning message with yellow border around the ExpirationDate field is displayed")
    public void thereIsAWarningMessageWithYellowBorderAroundTheExpirationDateFieldIsDisplayed() throws Exception {
        assertTrue(verifyProductPackagePage.isWarningBorderDisplayed());
    }

    @And("^the error message with red border around the (gtin|lot|serial|expireDate|gs1) field is displayed$")
    public void theErrorMessageWithRedBorderAroundTheGTINFieldIsDisplayed(String field) throws Exception {
        assertTrue(verifyProductPackagePage.isErrorBorderDisplayed(field));

    }


    @Then("verify the maximum allowed length for the fields in verify product pack page")
    public void verifyTheMaximumAllowedLengthForTheFieldsInVerifyProductPackPage(Map<String, String> inputValue) throws Exception {
        if (inputValue.containsKey("BatchNumber")) {
            assertEquals(verifyProductPackagePage.getBatchNumber().length(), inputValue.get("BatchNumber").length() - 1);
        }
        if (inputValue.containsKey("SerialNumber")) {
            assertEquals(verifyProductPackagePage.getSerialNumber().length(), inputValue.get("SerialNumber").length() - 1);
        }


    }

    @And("^Verify the Ghost Text '(.+)' is present by default in (BatchNumber|SerialNumber) field$")
    public void verifyTheGhostTextBatchNumberGhostTextIsPresentByDefaultInBatchNumberField(String ghostText, String id) throws Exception {
        if (id.equals("SerialNumber")) {
            assertEquals(ghostText, verifyProductPackagePage.getGhostTextSerialNumber());
        }
        if (id.equals("BatchNumber")) {
            assertEquals(ghostText, verifyProductPackagePage.getGhostTextBatchNumber());
        }
    }


    @And("^the gsl element '(.+)' is entered$")
    public void theGslElementGslElementIsEntered(String gs1Element) throws Exception {
        verifyProductPackagePage.enterGS1Element(gs1Element);
    }

    @And("click on GslElementString tab")
    public void clickOnGslElementStringTab() throws Exception {
        verifyProductPackagePage.clickOnGs1ElementString();
    }

    @Then("verify values displayed against each field")
    public void verifyValuesDisplayedAgainstEachField(Map<String, String> expectedValue) throws Exception {
        Thread.sleep(1000);
        assertEquals(verifyProductPackagePage.getGtin(), expectedValue.get("GTIN"));
        assertEquals(verifyProductPackagePage.getBatchNumber(), expectedValue.get("BatchNumber"));
        assertEquals(verifyProductPackagePage.getSerialNumber(), expectedValue.get("SerialNumber"));
        assertEquals(verifyProductPackagePage.getExpirationDateValue(), expectedValue.get("ExpirationDate"));
    }

    @Then("^Error message '(.+)' is displayed$")
    public void errorMessageErrorMessageIsDisplayed(String errorMessage) throws Exception {
        assertEquals(errorMessage, verifyProductPackagePage.getMandatoryErrorMessage());
    }

    @And("^the error message with red border around the (GTIN|Batch Number|Serial Number|Expiration Date) field is displayed in GS1Element String tab$")
    public void theErrorMessageWithRedBorderAroundTheBatchNumberFieldIsDisplayedInGSElmentStringTab(String field) throws Exception {
        assertTrue(verifyProductPackagePage.isErrorBorderDisplayedInGS1ElementStringTab(field));

    }

    @Then("verify details on Verification response screen")
    public void verifyDetailsOnVerificationResponseScreen(Map<String, String> expectedDetails) throws Exception {
        if (expectedDetails.containsKey("Verification Failure Reason")) {
            if (expectedDetails.get("Verified").equals("false")) {
                assertTrue(expectedDetails.get("Verification Failure Reason").equals(verifyProductPackagePage.getResponseValue("Verification Failure Reason")));
            }
        }
        if (expectedDetails.containsKey("Verified")) {
            assertTrue(expectedDetails.get("Verified").equals(verifyProductPackagePage.getResponseValue("Verified")));
        }
        if (expectedDetails.containsKey("Additional Information")) {
            if (expectedDetails.get("Additional Information").isEmpty()) {
                assertTrue(verifyProductPackagePage.getResponseValue("Additional Information").isEmpty());
            } else {
                assertTrue(verifyProductPackagePage.getResponseValue("Additional Information").equals(expectedDetails.get("Additional Information")));
            }
        }
        if (expectedDetails.containsKey("Verification Time Stamp")) {
            assertTrue(verifyProductPackagePage.getResponseValue("Verification Time Stamp").contains(verifyProductPackagePage.getCurrentDate()));
        }
        if (expectedDetails.containsKey("Correlation UUID")) {
            assertFalse(verifyProductPackagePage.getResponseValue("Correlation UUID").isEmpty());

            assertTrue(expectedDetails.get("Responder GLN").equals(verifyProductPackagePage.getResponseValue("Responder GLN")));
        }
        if (expectedDetails.containsKey("Responder GLN")) {
            assertTrue(expectedDetails.get("Responder GLN").equals(verifyProductPackagePage.getResponseValue("Responder GLN")));
        }
        if (expectedDetails.containsKey("HTTP Return Code")) {
            assertTrue(expectedDetails.get("HTTP Return Code").equals(verifyProductPackagePage.getResponseValue("HTTP Return Code")));
        }
        if (expectedDetails.containsKey("Responder Company")) {
            assertTrue(expectedDetails.get("Responder Company").equals(verifyProductPackagePage.getResponseValue("Responder Company")));

        }
    }

    @Then("verify header of verification response screen")
    public void verifyHeaderOfVerificationResponseScreen() throws Exception {
        verifyProductPackagePage.isVerificationResponseHeaderDisplayed();
    }

    @Then("verify header of adjust request screen")
    public void verifyHeaderOfAdjustRequestScreen() throws Exception {
        verifyProductPackagePage.isAdjustRequestHeaderDisplayed();
    }

    @And("^title of verify product pack screen is '(.+)'$")
    public void titleOfVerifyProductPackScreen(String title) throws Exception {
        assertEquals(title, verifyProductPackagePage.getTitle());
    }

    @And("header of request types are displayed")
    public void headerOfRequestTypesAreDisplayed() throws Exception {
        verifyProductPackagePage.clickElement(verifyProductPackagePage.requestTypeArrow);
        assertTrue(verifyProductPackagePage.getDecodedDataText().isDisplayed());
        assertTrue(verifyProductPackagePage.getGs1ElementText().isDisplayed());
    }

    @And("click on setting button")
    public void clickOnSettingButton() throws Exception {
        verifyProductPackagePage.clickSettingsButton();
    }

    @Then("verify header of select GLN screen")
    public void verifyHeaderOfSelectGLNScreen() throws Exception {
        assertTrue(verifyProductPackagePage.getSelectGLNScreenHeader().isDisplayed());
    }


    @Then("^verify sender GLN '(.+)','(.+)' change for the verification$")
    public void verifySenderGLNChangeForTheVerification(String gln1, String gln2) throws Exception {
        senderGln = verifyProductPackagePage.selectSenderGLN(gln1, gln2);
        verifyProductPackagePage.clickBarCodeIcon();
    }

    @And("^change the sender GLN to '(.+)'$")
    public void changeTheSenderGLNToSenderGln(String gln) throws Exception {
        if (!senderGln.equals(gln)) {
            verifyProductPackagePage.selectGLN(gln);
            verifyProductPackagePage.clickBarCodeIcon();
        }
        else{
            verifyProductPackagePage.clickBarCodeIcon();
        }
    }

    @And("click on setting button in verify screen")
    public void clickOnSettingButtonInVerifyScreen() throws Exception {
        verifyProductPackagePage.clickSettingsBtnInVerifyScreen();
    }

    @Then("^verify the '(.+)' field$")
    public void verifyTheSerialNumberField(String serialNumber) throws Exception {
        assertEquals(verifyProductPackagePage.getSerialNumber(), serialNumber);
    }

    @Then("^error message '(.+)' '(.+)' is displayed$")
    public void errorMessageErrorMessageGTINIsDisplayed(String errorMessage, String GTIN) throws Exception {
        assertEquals(verifyProductPackagePage.getErrorMessage(), errorMessage + " " + GTIN);
    }

    @Then("^the error message '(.+)' is displayed$")
    public void errorMessageErrorMesgIsDisplayed(String errorMsg) throws Exception {
        assertEquals(verifyProductPackagePage.getErrorMessage(), errorMsg);

    }

    @Then("verify the field for blank value validation")
    public void verifyTheFieldForBlankValueValidation(Map<String, String> field) throws Exception {
        assertEquals(field.get("BatchNumber").replaceAll("\\s+", ""), verifyProductPackagePage.getBatchNumber());
        assertEquals(field.get("SerialNumber").replaceAll("\\s+", ""), verifyProductPackagePage.getSerialNumber());
    }

    @Then("warning message is not displayed for the ExpirationDate field")
    public void warningMessageIsNotDisplayedForTheExpirationDateField() {
        try {
            assertFalse(verifyProductPackagePage.isWarningBorderDisplayed());
        } catch (Exception e) {

        }
    }

    @And("get the gsl element value from the field")
    public void getTheGsElementValueFromTheField() throws Exception {
        gs1Element= verifyProductPackagePage.getGS1Element();
    }

    @And("the retrieved gsl element is entered")
    public void theRetrievedGslElementIsEntered() throws Exception {
        verifyProductPackagePage.enterGS1Element(gs1Element);
    }
}
