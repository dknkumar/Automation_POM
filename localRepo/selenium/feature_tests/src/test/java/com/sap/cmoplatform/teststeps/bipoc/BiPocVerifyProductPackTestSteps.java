package com.sap.cmoplatform.teststeps.bipoc;

import com.sap.cmoplatform.pages.bipoc.BiPocVerifyProductPackPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

public class BiPocVerifyProductPackTestSteps {
    BiPocVerifyProductPackPage biPocVerifyProductPackPage = new BiPocVerifyProductPackPage();

    @And("^the (BiPocVerifyProductPack) app is not visible in the USSC launcher page$")
    public void theBiPocVerifyProductPackAppIsNotVisibleInTheUSSCLauncherPage(String appName) throws Exception {
        try {
            assertFalse(biPocVerifyProductPackPage.getAppElement(appName).isDisplayed());
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Then("verify available fields visibility in Decoded data section")
    public void verifyAvailableFieldsVisibilityInDecodedDataSection() throws Exception {
        //assertTrue(biPocVerifyProductPackPage.getRequestTypeElement().isDisplayed());
        assertTrue(biPocVerifyProductPackPage.getGtinElement().isDisplayed());
        assertTrue(biPocVerifyProductPackPage.getSerialNumberElement().isDisplayed());
        assertTrue(biPocVerifyProductPackPage.getExpirationDateElement().isDisplayed());
        assertTrue(biPocVerifyProductPackPage.getBatchNumberElement().isDisplayed());
        assertTrue(biPocVerifyProductPackPage.getGs1ElementElement().isDisplayed());
        assertTrue(biPocVerifyProductPackPage.getGlnNumberElementElement().isDisplayed());
    }

    @Then("verify decoded data input fields")
    public void verifyDecodedDataInputFields() throws Exception {
        assertTrue(biPocVerifyProductPackPage.getGtinElement().isEnabled());
        assertTrue(biPocVerifyProductPackPage.getSerialNumberElement().isEnabled());
        assertTrue(biPocVerifyProductPackPage.getExpirationDateElement().isEnabled());
        assertTrue(biPocVerifyProductPackPage.getBatchNumberElement().isEnabled());
        assertFalse(biPocVerifyProductPackPage.getGs1ElementElement().isEnabled());
        assertFalse(biPocVerifyProductPackPage.getGlnNumberElementElement().isEnabled());

    }

    @Then("verify GSl element String input fields")
    public void verifyGS1ElementDataInputFields() throws Exception {
        assertFalse(biPocVerifyProductPackPage.getGtinElement().isEnabled());
        assertFalse(biPocVerifyProductPackPage.getSerialNumberElement().isEnabled());
        assertFalse(biPocVerifyProductPackPage.getExpirationDateElement().isEnabled());
        assertFalse(biPocVerifyProductPackPage.getBatchNumberElement().isEnabled());
        assertTrue(biPocVerifyProductPackPage.getGs1ElementElement().isEnabled());
        assertFalse(biPocVerifyProductPackPage.getGlnNumberElementElement().isEnabled());

    }

    @Then("^GLN Number is auto populated with '(.+)'$")
    public void glnNumberIsAutoPopulatedWithRequesterGLN(String gln) throws Exception {
      assertEquals(biPocVerifyProductPackPage.getGlnNumberElementElement().getAttribute("value"),gln);
    }
}
