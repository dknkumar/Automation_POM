package com.sap.cmoplatform.teststeps.managecertificate;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.pages.managecertificate.ManageCertificatePage;
import com.sap.cmoplatform.teststeps.common.CommonTestSteps;
import com.sap.cmoplatform.utils.PortalDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.sap.cmoplatform.pages.managecertificate.ManageCertificatePage.configManageCertificate;
import static org.junit.Assert.*;

public class ManageCertificateTestSteps extends Page {
    private final ManageCertificatePage manageCertificatePage = new ManageCertificatePage();
    private String uploadTestCert = "Test_TLS_03.cer";

    String[] TLScert1 = {"64119be7", "Mar 14, 2024, 6:20:23 PM"};
    String[] TLScert2 = {"008b63e7bd35cded08f5a3649cf6d19c75", "Oct 7, 2023, 2:14:41 PM"};
    private String inActiveCert;
    String[] MLScert1 = {"00cadbf24d87f8e3b54cf086e045a786dd", "Jul 4, 2024, 8:20:24 PM"};
    String[] MLSCertCPI1 = {"0xcadbf24d87f8e3b54cf086e045a786dd", "Jul 04, 2023, 19:20:24", "Jul 04, 2024, 20:20:24"};
    String[] MLSCertCPI2 = {"0x97dbd5b8d9b6ce7bd93cd28f16822ce", "Jul 04, 2023, 19:20:31", "Jul 04, 2024, 20:20:31"};
    String[] MLScert2 = {"097dbd5b8d9b6ce7bd93cd28f16822ce", "Jul 4, 2024, 8:20:31 PM", "Jul 04, 2023, 19:20:31"};
    private String deleteCerName;
    public String deletedCert;


    @When("Navigate to TLS Certificate type")
    public void navigateToTLSCertificateTypeEnvironmentDevQAStaging() throws Exception {
        manageCertificatePage.clickTLSCert();
    }

    @And("Click upload button if the cert not exist")
    public void clickUploadButton() throws Exception {
        portalDriver.waitFor(5000);
        portalDriver.waitForLoad();
        int itemIndex = manageCertificatePage.getItemIndex(uploadTestCert);
        List<WebElement> status = manageCertificatePage.getStatus();
        if (itemIndex != -1) {
            if (!Objects.equals(status.get(itemIndex).getText(), "Active")) {
                manageCertificatePage.deleteItemByIndex(itemIndex);
            } else {
                uploadTestCert = "Test_TLS_04.cer";
                int newIndex = manageCertificatePage.getItemIndex(uploadTestCert);
                if (newIndex != -1) {
                    manageCertificatePage.deleteItemByIndex(newIndex);
                }
            }
        }
        manageCertificatePage.clickUpload();
    }

    @And("Upload new cert")
    public void uploadNewCert() throws Exception {
        manageCertificatePage.uploadNewCert(uploadTestCert);
    }

    @Then("Certificate should be successfully uploaded in app with same Serial number, Status\\(New), Expiry date")
    public void certificateShouldBeSuccessfullyUploadedInAppWithSameSerialNumberStatusNewExpiryDate() throws Exception {
        portalDriver.waitFor(4000);
        int itemIndex = manageCertificatePage.getItemIndex(uploadTestCert);
        assertTrue(itemIndex != -1);
        List<WebElement> serialNumbers = manageCertificatePage.getSerialNumbers();
        List<WebElement> status = manageCertificatePage.getStatus();

        String[] certInfo;
        if (uploadTestCert.equals("Test_TLS_03.cer")) {
            certInfo = TLScert1;
        } else {
            certInfo = TLScert2;
        }
        assertEquals("New", status.get(itemIndex).getText());
        assertEquals(certInfo[0], serialNumbers.get(itemIndex).getText());
        // manageCertificatePage.deleteItemByIndex(itemIndex);
    }


    @And("Inside that Select the New TLS certificate")
    public void insideThatSelectTheNewTLSCertificate() throws Exception {
        portalDriver.waitFor(5000);
        portalDriver.waitForLoad();
        manageCertificatePage.selectTheNewTLSCert();
    }

    @And("Inside that Select the New certificate")
    public void insideThatSelectTheNewCertificate() throws Exception {
        portalDriver.waitFor(5000);
        portalDriver.waitForLoad();
        manageCertificatePage.selectTheNewCert();
    }


    @And("Click Activate button")
    public void clickActivateButton() throws Exception {
        clickButton("Activate");
        clickButton("OK");
    }

    @Then("New certificate should be successfully Activated in app")
    public void newCertificateShouldBeSuccessfullyActivatedInApp() throws Exception {
        portalDriver.waitFor(5000);
        int itemIndex = manageCertificatePage.getItemIndex("Test_TLS_03.cer");
        List<WebElement> status = manageCertificatePage.getStatus();
        if (itemIndex == -1 || !Objects.equals(status.get(itemIndex).getText(), "Active")) {
            itemIndex = manageCertificatePage.getItemIndex("Test_TLS_04.cer");
        }
        assertEquals("Active", status.get(itemIndex).getText());
    }

    @Then("same entry of Certificate should be updated in the main page with given serial number, Status \\(Active), In Use checkbox \\(Checked), Activation Date \\(Current Date), Expiry Date etc.")
    public void sameEntryOfCertificateShouldBeUpdatedInTheMainPageWithGivenSerialNumberStatusActiveInUseCheckboxCheckedActivationDateCurrentDateExpiryDateEtc() throws InterruptedException {
        portalDriver.waitFor(5000);
        String uploadTestCert = "Test_TLS_03.cer";
        int itemIndex = manageCertificatePage.getItemIndex("Test_TLS_03.cer");
        List<WebElement> status = manageCertificatePage.getStatus();
        if (itemIndex == -1 || !Objects.equals(status.get(itemIndex).getText(), "Active")) {
            itemIndex = manageCertificatePage.getItemIndex("Test_TLS_04.cer");
            uploadTestCert = "Test_TLS_04.cer";
        }
        List<WebElement> statusIcon = getElements(By.xpath("//*[@aria-label=\"Status Icon\"]"));
        List<WebElement> serialNumbers = manageCertificatePage.getSerialNumbers();
        List<WebElement> dates = manageCertificatePage.getExpireDate();

        if (uploadTestCert.equals("Test_TLS_03.cer")) {
            assertEquals(TLScert1[0], serialNumbers.get(itemIndex).getText());
            assertEquals(TLScert1[1], dates.get(itemIndex).getText());
        } else {
            assertEquals(TLScert2[0], serialNumbers.get(itemIndex).getText());
            assertEquals(TLScert2[1], dates.get(itemIndex).getText());
        }
        assertEquals("sapUiIcon", statusIcon.get(itemIndex).getAttribute("class"));
        assertEquals("Active", status.get(itemIndex).getText());
    }

    @And("Inside that Select the Inactive certificate")
    public void insideThatSelectTheInactiveCertificate() throws Exception {
        portalDriver.waitFor(5000);
        List<WebElement> status = manageCertificatePage.getStatus();
        List<WebElement> radios = getElements(By.xpath("//*[@role = 'radio']"));
        int indexFirstInActive = 0;
        List<WebElement> certNames = manageCertificatePage.getCertName();
        int activeCert = 0;
        int indexNewCert = 0;
        for (int i = 0; i < status.size(); i++) {
            Boolean isTestCert = certNames.get(i).getText().equals("Test_TLS_03.cer") || certNames.get(i).getText().equals("Test_TLS_04.cer");
            if (status.get(i).getText().equals("Active") && isTestCert) {
                activeCert = i;
            }
            if (status.get(i).getText().equals("New") && isTestCert) {
                indexNewCert = i;
            }
            if (status.get(i).getText().equals("Inactive") && isTestCert) {
                indexFirstInActive = i;
                break;
            }
        }
        if (indexFirstInActive == 0) {
            if (indexNewCert != 0) {
                radios.get(indexNewCert).click();
                clickButton("Activate");
                clickButton("OK");
                portalDriver.waitFor(4000);
                indexFirstInActive = activeCert;
            }
        }
        inActiveCert = certNames.get(indexFirstInActive).getText();

        radios.get(indexFirstInActive).click();
    }


    @Then("same entry of Certificate should be updated in the main page with given serial number, Status \\(Active), In Use checkbox \\(Checked), Activation Date \\(Current Date), Expiry Date")
    public void sameEntryOfCertificateShouldBeUpdatedInTheMainPageWithGivenSerialNumberStatusActiveInUseCheckboxCheckedActivationDateCurrentDateExpiryDate() throws InterruptedException {
        portalDriver.waitFor(5000);
        int itemIndex = manageCertificatePage.getItemIndex(inActiveCert);
        List<WebElement> statusIcon = getElements(By.xpath("//*[@aria-label=\"Status Icon\"]"));
        List<WebElement> serialNumbers = manageCertificatePage.getSerialNumbers();
        List<WebElement> status = manageCertificatePage.getStatus();
        List<WebElement> dates = manageCertificatePage.getExpireDate();

        if (inActiveCert.equals("Test_TLS_03.cer")) {
            assertEquals(TLScert1[0], serialNumbers.get(itemIndex).getText());
            assertEquals(TLScert1[1], dates.get(itemIndex).getText());
        } else {
            assertEquals(TLScert2[0], serialNumbers.get(itemIndex).getText());
            assertEquals(TLScert2[1], dates.get(itemIndex).getText());
        }
        assertEquals("sapUiIcon", statusIcon.get(itemIndex).getAttribute("class"));
        assertEquals("Active", status.get(itemIndex).getText());
    }

    @Then("Inactive Certificate should be successfully Activated in app")
    public void inActiveCertificateShouldBeSuccessfullyActivatedInApp() throws InterruptedException {
        portalDriver.waitFor(5000);
        int itemIndex = manageCertificatePage.getItemIndex(inActiveCert);
        List<WebElement> status = manageCertificatePage.getStatus();
        assertEquals("Active", status.get(itemIndex).getText());
    }

    @And("Inside that Select the New or Inactive certificate")
    public void insideThatSelectTheNewInactiveCertificate() throws Exception {
        portalDriver.waitFor(5000);
        List<WebElement> status = manageCertificatePage.getStatus();
        List<WebElement> radios = getElements(By.xpath("//*[@role = 'radio']"));

        List<WebElement> certNames = manageCertificatePage.getCertName();
        int certToDelete = -1;
        for (int i = 0; i < status.size(); i++) {
            Boolean isTestCert = certNames.get(i).getText().equals("Test_TLS_03.cer") || certNames.get(i).getText().equals("Test_TLS_04.cer");
            if (status.get(i).getText().equals("New") && isTestCert) {
                certToDelete = i;
                break;
            }
            if (status.get(i).getText().equals("Inactive") && isTestCert) {
                certToDelete = i;
                break;
            }
        }

        deleteCerName = certNames.get(certToDelete).getText();

        radios.get(certToDelete).click();
    }

    @Then("Certificate should be successfully Deleted from app")
    public void certificateShouldBeSuccessfullyDeletedFromApp() throws InterruptedException {
        portalDriver.waitFor(3000);
        assertEquals(-1, manageCertificatePage.getItemIndex(deleteCerName));
    }

    @When("Navigate to MLS Certificate type")
    public void navigateToMLSCertificateType() throws Exception {
        manageCertificatePage.clickMLSCert();
    }

    @And("Click upload if the cert not exist")
    public void clickUploadIfTheCertNotExist() throws Exception {
        portalDriver.waitFor(5000);
        portalDriver.waitForLoad();
        uploadTestCert = "Test_MLS_01.cer";
        int itemIndex = manageCertificatePage.getItemIndex(uploadTestCert);
        List<WebElement> status = manageCertificatePage.getStatus();
        if (itemIndex != -1) {
            if (!Objects.equals(status.get(itemIndex).getText(), "Active")) {
                manageCertificatePage.deleteItemByIndex(itemIndex);
            } else {
                uploadTestCert = "Test_MLS_02.cer";
                int newIndex = manageCertificatePage.getItemIndex(uploadTestCert);
                if (newIndex != -1) {
                    manageCertificatePage.deleteItemByIndex(newIndex);
                }
            }
        }
        manageCertificatePage.clickUpload();
    }

    @Then("MLS Certificate should be successfully uploaded in app with same Serial number, Status\\(New), Expiry date")
    public void mlsCertificateShouldBeSuccessfullyUploadedInAppWithSameSerialNumberStatusNewExpiryDate() throws InterruptedException {
        portalDriver.waitFor(4000);
        int itemIndex = manageCertificatePage.getItemIndex(uploadTestCert);
        assertTrue(itemIndex != -1);
        List<WebElement> serialNumbers = manageCertificatePage.getSerialNumbers();
        List<WebElement> status = manageCertificatePage.getStatus();

        String[] certInfo;
        if (uploadTestCert.equals("Test_MLS_01.cer")) {
            certInfo = MLScert1;
        } else {
            certInfo = MLScert2;
        }
        assertEquals("New", status.get(itemIndex).getText());
        assertEquals(certInfo[0], serialNumbers.get(itemIndex).getText());
        // manageCertificatePage.deleteItemByIndex(itemIndex);
    }

    @And("Inside that Select the New MLS certificate")
    public void insideThatSelectTheNewMLSCertificate() throws Exception {
        portalDriver.waitFor(5000);
        portalDriver.waitForLoad();
        manageCertificatePage.selectTheNewMLSCert();
    }

    @When("the user logs in CPI for MC")
    public void cpiLoginMcertificate() throws Throwable {
        PortalDriver.getInstance();
        SeleniumUI5TestUtil.getConfig().getDriver().get(configManageCertificate.getProperty("cpiMonitoringAppURL"));
        CommonTestSteps.enableTimestampforScreenshots();
        clickButton("Close");
    }

    @And("Search for the alias as updated in the manage certificate app")
    public void searchForTheAliasAsUpdatedInTheManageCertificateApp() throws Exception {
        inputText("__component0---com.sap.it.op.web.ui.pages.KeystoreManagement--SEARCHFIELD_KEYSTORE-I", "cf_qa_mlstest");
        clickElement(By.xpath("//a[text()='cf_qa_mlstest']"));
    }

    @Then("Activated Certificate entry updated in CPI Keystore with same Alias")
    public void activatedCertificateEntryUpdatedInCPIKeystoreWithSameAlias() throws Exception {
        waitForPageToLoad();
        portalDriver.waitFor(5000);
        if (Objects.equals(manageCertificatePage.activatedMLSCert, "Test_MLS_01.cer")) {
            assertEquals(MLSCertCPI1[0], getElement(By.xpath("//*[@data-sap-ui=\"KEYSTORE_ENTRY_SERIALNR\"]")).getText());
            assertEquals(MLSCertCPI1[1], getElement(By.xpath("//*[@data-sap-ui=\"KEYSTORE_ENTRY_VALID_FROM\"]")).getText());
            assertEquals(MLSCertCPI1[2], getElement(By.xpath("//*[@data-sap-ui=\"KEYSTORE_ENTRY_VALID_UNTIL_2\"]")).getText());
        } else {
            assertEquals(MLSCertCPI2[0], getElement(By.xpath("//*[@data-sap-ui=\"KEYSTORE_ENTRY_SERIALNR\"]")).getText());
            assertEquals(MLSCertCPI2[1], getElement(By.xpath("//*[@data-sap-ui=\"KEYSTORE_ENTRY_VALID_FROM\"]")).getText());
            assertEquals(MLSCertCPI2[2], getElement(By.xpath("//*[@data-sap-ui=\"KEYSTORE_ENTRY_VALID_UNTIL_2\"]")).getText());
        }
    }

    @Then("same entry of MLS Certificate should be updated in the main page with given serial number, Status \\(Active), In Use checkbox \\(Checked), Activation Date \\(Current Date), Expiry Date etc.")
    public void sameEntryOfMLSCertificateShouldBeUpdatedInTheMainPageWithGivenSerialNumberStatusActiveInUseCheckboxCheckedActivationDateCurrentDateExpiryDateEtc() throws InterruptedException {
        portalDriver.waitFor(5000);
        int itemIndex = manageCertificatePage.getItemIndex(manageCertificatePage.activatedMLSCert);
        List<WebElement> statusIcon = getElements(By.xpath("//*[@aria-label=\"Status Icon\"]"));
        List<WebElement> serialNumbers = manageCertificatePage.getSerialNumbers();
        List<WebElement> status = manageCertificatePage.getStatus();
        List<WebElement> dates = manageCertificatePage.getExpireDate();

        if (manageCertificatePage.activatedMLSCert.equals("Test_MLS_01.cer")) {
            assertEquals(MLScert1[0], serialNumbers.get(itemIndex).getText());
            assertEquals(MLScert1[1], dates.get(itemIndex).getText());
        } else {
            assertEquals(MLScert2[0], serialNumbers.get(itemIndex).getText());
            assertEquals(MLScert2[1], dates.get(itemIndex).getText());
        }
        assertEquals("sapUiIcon", statusIcon.get(itemIndex).getAttribute("class"));
        assertEquals("Active", status.get(itemIndex).getText());
    }

    @And("Inside that Select the Inactive MLS certificate")
    public void insideThatSelectTheInactiveMLSCertificate() throws Exception {
        portalDriver.waitFor(5000);
        List<WebElement> status = manageCertificatePage.getStatus();
        List<WebElement> radios = getElements(By.xpath("//*[@role = 'radio']"));
        int indexFirstInActive = 0;
        List<WebElement> certNames = manageCertificatePage.getCertName();
        int activeCert = 0;
        int indexNewCert = 0;
        for (int i = 0; i < status.size(); i++) {
            Boolean isTestCert = certNames.get(i).getText().equals("Test_MLS_01.cer") || certNames.get(i).getText().equals("Test_MLS_02.cer");
            if (status.get(i).getText().equals("Active") && isTestCert) {
                activeCert = i;
            }
            if (status.get(i).getText().equals("New") && isTestCert) {
                indexNewCert = i;
            }
            if (status.get(i).getText().equals("Inactive") && isTestCert) {
                indexFirstInActive = i;
                break;
            }
        }
        if (indexFirstInActive == 0) {
            if (indexNewCert != 0) {
                radios.get(indexNewCert).click();
                clickButton("Activate");
                clickButton("OK");
                portalDriver.waitFor(4000);
                indexFirstInActive = activeCert;
            }
        }
        inActiveCert = certNames.get(indexFirstInActive).getText();
        manageCertificatePage.activatedMLSCert = inActiveCert;

        radios.get(indexFirstInActive).click();
    }

    @And("Inside that Select the New or Inactive MLS certificate")
    public void insideThatSelectTheNewOrInactiveMLSCertificate() throws Exception {
        portalDriver.waitFor(5000);
        List<WebElement> status = manageCertificatePage.getStatus();
        List<WebElement> radios = getElements(By.xpath("//*[@role = 'radio']"));

        List<WebElement> certNames = manageCertificatePage.getCertName();
        int certToDelete = -1;
        for (int i = 0; i < status.size(); i++) {
            Boolean isTestCert = certNames.get(i).getText().equals("Test_MLS_01.cer") || certNames.get(i).getText().equals("Test_MLS_02.cer");
            if (status.get(i).getText().equals("New") && isTestCert) {
                certToDelete = i;
                break;
            }
            if (status.get(i).getText().equals("Inactive") && isTestCert) {
                certToDelete = i;
                break;
            }
        }
        if (certToDelete == -1 && manageCertificatePage.getItemIndex("Test_MLS_01.cer") == -1) {
            manageCertificatePage.clickUpload();
            manageCertificatePage.uploadNewCert("Test_MLS_01.cer");
            portalDriver.waitFor(4000);
            certToDelete = manageCertificatePage.getItemIndex("Test_MLS_01.cer");
        } else {
            manageCertificatePage.clickUpload();
            manageCertificatePage.uploadNewCert("Test_MLS_02.cer");
            portalDriver.waitFor(4000);
            certToDelete = manageCertificatePage.getItemIndex("Test_MLS_02.cer");
        }
        certNames = manageCertificatePage.getCertName();
        deleteCerName = certNames.get(certToDelete).getText();

        radios.get(certToDelete).click();
    }

    @And("Click Delete button")
    public void clickDeleteButton() throws Exception {
        clickButton("Delete");
        clickButton("OK");
    }

    @And("Inside that try to upload more than 5 certificates")
    public void insideThatTryToUploadMoreThanCertificates() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> certs = manageCertificatePage.getCertName();
        int numbersOfCertsAbleToUpload = 5 - certs.size();
        while (numbersOfCertsAbleToUpload >= 0) {
            for (int i = 1; i < 8; i++) {
                String certName = "Test_MLS_0" + i + ".cer";
                if (manageCertificatePage.getItemIndex(certName) != -1) {
                    continue;
                }
                clickButton("Upload");
                manageCertificatePage.uploadNewCert(certName);
                if (getElement(By.xpath("//*[text()='Maximum five certificates permitted']")).isDisplayed()) {
                    break;
                }
            }
            numbersOfCertsAbleToUpload--;
        }

    }

    @Then("Certificate should NOT get uploaded & error message {string} should be displayed")
    public void certificateShouldNOTGetUploadedErrorMessageShouldBeDisplayed(String errorText) throws Exception {
        assertTrue(getElement(By.xpath("//*[text()='Maximum five certificates permitted']")).isDisplayed());
        clickButton("Close");
        List<WebElement> certs = manageCertificatePage.getCertName();
        assertEquals(5, certs.size());
    }

    @When("click on download button of the TLS certificate")
    public void clickOnDownloadButtonOfTheTLSCertificate() throws Exception {
        clickElement(By.xpath("//span[text()='TLS']/../../td//button"));
        portalDriver.waitFor(10000);
    }

    @Then("{string} file should get downloaded")
    public void certFileShouldGetDownloaded(String suffix) throws Exception {
        assertTrue(manageCertificatePage.fileShouldGetDownloaded(suffix));
    }

    @And("click on download button of the certificate")
    public void clickOnDownloadButtonOfTheCertificate() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> downloadButtons = getElements(By.xpath("//tbody[@id='application-certificate_automation-Display-component---certificateDetails--idUploadsTable-tblBody']//td//button[contains(@title, 'Download C')]"));
        downloadButtons.get(0).click();
        portalDriver.waitFor(10000);
    }

    @Then("Certificate should be successfully Activated in app")
    public void certificateShouldBeSuccessfullyActivatedInApp() {
        int itemIndex = manageCertificatePage.getItemIndex();
        List<WebElement> status =
                getElements(
                        By.xpath(
                                "//td[@headers=\"application-certificate_automation-Display-component---certificateDetails--statusCol\"]//span[contains(@id, 'text')]"));
        assertEquals("Activate", status.get(itemIndex).getText());
    }

    @Then("{string} error message should be displayed")
    public void duplicateCertificateNotPermittedErrorMessageShouldBeDisplayed(String errorMsg)
            throws Exception {
        assertEquals(errorMsg, manageCertificatePage.getErrorMessageInMainPage());
    }

    @And("Upload cert in {string} with {}")
    public void uploadCertInEnvironmentWithCertName(String environment, String fileName) throws AWTException, InterruptedException {
        manageCertificatePage.uploadCertWithName(environment, fileName);
    }

    @Then("the certificate details should display columns")
    public void theCertificateDetailsShouldDisplayColumns(List<String> columns) {
        assertTrue(manageCertificatePage.getCertificateDetailColumns().containsAll(columns));
    }

    @And("search for {} in search field")
    public void searchForInSearchField(String info) throws Exception {
        manageCertificatePage.searchForInSearchField(info);
    }

    @Then("result should be displayed with {string} of {}")
    public void resultShouldBeDisplayedWith(String field, String info) throws Exception {
        assertEquals(info, manageCertificatePage.getFieldName(field));
    }

    @And("click on group button in main page")
    public void clickOnGroupButtonInMainPage() throws Exception {
        manageCertificatePage.clickOnGroupButtonInMainPage();
    }

    @And("select {} in group order section")
    public void selectGroupOrderInGroupOrderSection(String groupOrder) throws Exception {
        manageCertificatePage.selectOrderInOrderSection(groupOrder);
    }

    @And("select {} in group by section")
    public void selectGroupByInGroupBySection(String groupBy) {
        manageCertificatePage.selectByInBySection(groupBy);
    }

    @Then("result should be displayed with {} order of group by {}")
    public void resultShouldBeDisplayedWithGroupOrderOrderOfGroupByGroupBy(
            String groupOrder, String groupBy) {
        assertTrue(
                manageCertificatePage.resultShouldBeDisplayedWithGroupOrderOrderOfGroupByGroupBy(
                        groupOrder, groupBy));
    }

    @And("select {} in sort order section")
    public void selectSortOrderInSortOrderSection(String sortOrder) {
        manageCertificatePage.selectOrderInOrderSection(sortOrder);
    }

    @And("select {} in sort by section")
    public void selectSortByInSortBySection(String sortBy) {
        manageCertificatePage.selectByInBySection(sortBy);
    }

    @And("click on sort button in main page")
    public void clickOnSortButtonInMainPage() throws Exception {
        manageCertificatePage.clickOnSortButtonInMainPage();
    }

    @Then("result should be displayed with {} order of sort by {}")
    public void resultShouldBeDisplayedWithSortOrderOrderOfSortBySortBy(
            String sortOrder, String sortBy) throws Exception {
        assertTrue(
                manageCertificatePage.resultShouldBeDisplayedWithSortOrderOrderOfSortBySortBy(
                        sortOrder, sortBy));
    }

    @And("click on export to excel button in main page")
    public void clickOnExportToExcelButtonInMainPage() throws Exception {
        manageCertificatePage.clickOnExportToExcelButtonInMainPage();
    }

    @Then("{} should be downloaded with correct value")
    public void certNameShouldBeDownloadedWithCorrectValue(String fileName) throws Exception {
        assertTrue(manageCertificatePage.certNameShouldBeDownloadedWithCorrectValue(fileName));
    }

    @And("click on Personalization button")
    public void clickOnPersonalizationButton() throws Exception {
        manageCertificatePage.clickOnPersonalizationButton();
    }

    @And("select columns from the Personalisation")
    public void selectColumnsFromThePersonalisation(List<String> columns) throws Exception {
        manageCertificatePage.selectColumnsFromThePersonalisation(columns);
    }

    @Then("columns should be displayed on main page")
    public void columnsShouldBeDisplayedOnMainPage(List<String> columns) throws InterruptedException {
        assertTrue(manageCertificatePage.columnsShouldBeDisplayedOnMainPage(columns));
    }

    @And("click on {string} user")
    public void clickOnTestCertUser(String userName) throws Exception {
        manageCertificatePage.clickOnTestCertUser(userName);
    }

    @And("remove {string} application")
    public void removeManageCertificateApplication(String appName) throws Exception {
        manageCertificatePage.removeApplicationForUser(appName);
    }

    @Then("the {string} app should not be displayed")
    public void theManageCertificatesAppShouldNotBeDisplayed(String appName) {
        assertFalse(manageCertificatePage.appVisible(appName));
    }

    @And("activate certificate {}")
    public void activateCertificate(String certName) throws Exception {
        manageCertificatePage.activateCertificate(certName);
    }

    @And("Navigate to main page")
    public void navigateToMainPage() throws Exception {
        manageCertificatePage.navigateToMainPage();
        portalDriver.switchToFrame("application-certificate_automation-Display");
    }

    @Then("changelog should display with columns of")
    public void changelogShouldDisplayWithColumnsOf(List<String> changelogResult) throws InterruptedException {
        assertTrue(manageCertificatePage.changelogShouldDisplayWithColumnsOf(changelogResult));
    }

    @And("add {string} application")
    public void addManageCertificateApplication(String appName) throws Exception {
        manageCertificatePage.addApplicationForUser(appName);
    }

    @Then("the {string} app should be displayed")
    public void theManageCertificatesAppShouldBeDisplayed(String appName) {
        assertTrue(manageCertificatePage.appVisible(appName));
    }
}

