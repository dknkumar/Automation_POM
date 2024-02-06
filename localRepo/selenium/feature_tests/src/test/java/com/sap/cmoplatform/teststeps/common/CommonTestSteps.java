package com.sap.cmoplatform.teststeps.common;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.*;
import com.sap.cmoplatform.UI5Version;
import com.sap.cmoplatform.pages.manageCustomization.ManageCustomizingPage;
import com.sap.cmoplatform.utils.ModifyXml;
import com.sap.cmoplatform.utils.PortalDriver;
import com.sap.cmoplatform.utils.PropertyReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import java.text.*;
import java.util.*;
public class CommonTestSteps {

    private final LoginPage loginPage = new LoginPage();
    private static final LauncherPage launcherPage = new LauncherPage();
    private final GoodbyePage goodbyePage = new GoodbyePage();
    private final GenericPage genericPage = new GenericPage();
    private final ManageCustomizingPage manageCustomizingPage = new ManageCustomizingPage();
    private final Properties apps = PropertyReader.loadProperties("Apps.properties");
    private static final String script = "(()=> { if (!window.automationTestTimeStamp) { const div = document.createElement('div'); div.setAttribute(\"style\", \"position: fixed;top:0px; left: 300px; color: red\"); setInterval(() => { let date = new Date;let strDate = date.toString(); strDate = \"Date=\".concat(strDate); let url = window.location.host; let strURL = url.toString(); strURL = \",HOST=\".concat(strURL); div.innerText = strDate.concat(strURL); }, 50); document.body.appendChild(div); window.automationTestTimeStamp = true; }})();";

    private Properties manageCustomizingProperties = PropertyReader.loadProperties("managecustomization/managecustomization.properties");

    static WebDriverWait wait = new WebDriverWait(PortalDriver.getInstance().getConfig().getDriver(), 60);
    private static Scenario scenario;

    @Before
    public void setup() {
        SeleniumUI5TestUtil.getConfig().getDriver();
        PortalDriver.getInstance().openUrl();
    }



    @Before
    public void before(Scenario scenario) {
        CommonTestSteps.scenario = scenario;
    }

    @After
    public void cleanup(Scenario s) throws Exception {
        enableTimestampforScreenshots();
        Thread.sleep(1000);
        TakesScreenshot ts = PortalDriver.getInstance().getConfig().getDriver();
        if (ts != null) {
            File source = ts.getScreenshotAs(OutputType.FILE);
            long timestamp = System.currentTimeMillis();
            String scname = scenario.getName().replaceAll("[^a-zA-Z0-9_]", "");
            System.out.println("Scenarios name is: " + scname.trim());
            FileUtils.copyFile(source, new File("./target/screenshots/" + scname.trim() + "/img_" + timestamp + ".png"));
            s.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", scname.trim() + "/img_" + timestamp + ".png");
        }
        PortalDriver.quitDriver();
    }

    @AfterStep
    public void takeScreenshot(Scenario s) throws Exception {
        TakesScreenshot ts1 = PortalDriver.getInstance().getConfig().getDriver();
        if (ts1 != null) {
            if (s.isFailed()) {
                enableTimestampforScreenshots();
                TakesScreenshot ts = PortalDriver.getInstance().getConfig().getDriver();
                File source = ts.getScreenshotAs(OutputType.FILE);
                long timestamp = System.currentTimeMillis();
                String scname = scenario.getName().replaceAll("[^a-zA-Z0-9_]", "");
                System.out.println("Sceanrios name is: " + scname);
                FileUtils.copyFile(source, new File("./target/screenshots/" + scname.trim() + "/img_" + timestamp + "FAILED.png"));
                s.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", scname.trim() + "/img_" + timestamp + ".png");
            }

        }
    }

    public static void addScreenshot() throws Exception {
        TakesScreenshot scrShot = PortalDriver.getInstance().getConfig().getDriver();
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Copy file at destination
        long timestamp = System.currentTimeMillis();
        String scname = scenario.getName().replaceAll("[^a-zA-Z0-9 -_]", "");
        FileUtils.copyFile(SrcFile, new File("./target/screenshots/" + scname.trim() + "/img_" + timestamp + ".png"));
    }


    @When("^the user logs in as '(.+)' in the login page$")
    public void logsInAsUsernameAndPassword(String user) throws Throwable {
        System.out.println("Login step");
        loginPage.login(user);
    }

    @When("^the (TI|Manage Serialization Events|SNR|GM|ONBOARDING|GenericMessaging|RM) app is clicked in the launcher page$")
    public void clickApp(String app) throws Exception {
        String appName = apps.getProperty(app + ".appName");
        System.out.println(" APP Name : " + appName);
        launcherPage.clickFioriApp(appName);
        launcherPage.switchToFrame(app);
    }

    @And("^the user clicks log in again$")
    public void theUserClicksLogInAgain() throws Throwable {
        goodbyePage.logBackOn();
    }

    @Then("^the '(.+)' app is not available in the launcher page$")
    public void theOnboardingAppIsNotAvailable(String appName) throws Exception {
        assertThat(launcherPage.isAppAvailable(appName), is(false));
    }

    @And("^the button labeled (Detailed View|ERV|Business View|Provisions|Yes|No|OK|Cancel|Send|Save Draft|Create|Save & Send|Add|Cancel|Save|Go|Adjust Request|New Upload|Upload|Upload to Lookup Directory|Close|Delete|Edit|New Message|Sign in again|Adapt Filters|Change Log|Hide Filter Bar|Change Status|Save As) is clicked$")
    public void theButtonLabeledOKIsClicked(String button) throws Exception {
        Page.clickButton(button);
    }

    @Then("the toast message {string} appears")
    public void theToastMessageMessageAppears(String expectedMessage) throws Exception {
        assertEquals("Message does not appear or is wrong.", expectedMessage, genericPage.getToastMessage());
    }

    @Then("the success toast message {string} appears")
    public void theSuccessToastMessageMessageAppears(String expectedMessage) throws Exception {
        assertEquals("Message does not appear or is wrong.", expectedMessage, genericPage.getSuccessToastMessage());
    }


    @Then("the toast message contains {string}")
    public void theToastMessageMessageContains(String expectedMessage) throws Exception {
        assertThat(genericPage.getToastMessage(), containsString(expectedMessage));
    }

    @And("the user clicks on log out button")
    public void theUserClicksOnLogOutButton() throws Exception {
        genericPage.clickOnUserButtonId();
        genericPage.logOut();
    }

    @And("the user clicks on sign out button")
    public void theUserClicksOnSignOutButton() throws Exception {
        launcherPage.switchToDefaultContent();
        genericPage.clickOnUserButtonId();
        genericPage.signOutButton();
    }

    @Then("^the (Onboarding|Generic Messaging|Trade Items|Relationship Manager|Serialization Events Management|Serial Number Requests) app is available in the launcher page$")
    public void isAvailableInTheLauncherPage(String appName) throws Exception {
        assertThat(launcherPage.isAppAvailable(appName), is(true));
    }

    @When("^the (DetailedView|ERV|Summary) app is clicked in the Indonesia launcher page$")
    public void clickUsscApp(String app) throws Exception {
        String appName = apps.getProperty(app + ".appName");
        launcherPage.clickUsscApp(appName);
        launcherPage.switchToFrame(app);
    }

    @Then("verify file {string} is downloaded")
    public static void verifyFileIsDownloaded(String downloadFileName) throws Exception {
        Thread.sleep(5000);
        PortalDriver.getInstance();
        WebDriverWait wait = new WebDriverWait(SeleniumUI5TestUtil.getConfig().getDriver(), 10);
        if (FilenameUtils.getExtension(downloadFileName).equals("xlsx")) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(launcherPage.getBusyIndicatorExportDocument()));
        }
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
        File folder = new File(filePath);
        boolean flag = false;
        File f = null;
        File[] listOfFiles = folder.listFiles();
        //Look for the file in the files
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("filenamr " + fileName);
                if (fileName.equals(downloadFileName)) {
                    f = new File(filePath + fileName);
                    flag = true;
                }
            }

        }
        System.out.println(flag);
        assertTrue(flag);
        f.deleteOnExit();
    }

    @And("the download pdf button is clicked")
    public void theDownloadPdfButtonIsClicked() throws Exception {
        launcherPage.clickBtnDownloadPdf();
    }

    @And("the download button is clicked")
    public void theDownloadButtonIsClicked() throws Exception {
        launcherPage.clickBtnDownloadIcon();
    }


    @And("the view sort setting button is clicked")
    public void theViewSortSettingButtonIsClicked() throws Exception {
        launcherPage.clickViewSortSetting();
    }
    @And("the view group setting button is clicked")
    public void theViewGroupSettingButtonIsClicked() throws Exception {
        launcherPage.clickViewGroupSetting();
    }
    @And("the dates are selected")
    public void theDatesAreSelected() throws Exception{
        Calendar cal = Calendar.getInstance();
        Date cdate = cal.getTime();
        cal.add(Calendar.DATE, -8);
        Date pdate = cal.getTime();
        String new_cdate = new SimpleDateFormat("MMM dd, yyyy").format(cdate);
        String new_pdate = new SimpleDateFormat("MMM dd, yyyy").format(pdate);
        launcherPage.clickselectdate(new_pdate,new_cdate);


    }



    @Then("^the title of the page is '(.+)'$")
    public void theTitleOfThePageIsTitle(String title) throws Exception {
        assertEquals(title, launcherPage.getPageTitle());
    }


    @And("navigate to home screen")
    public void navigateToHomeScreen() throws Exception {
        launcherPage.switchToDefaultContent();
        launcherPage.clickOnHomeButton();
    }

    @And("^the xml file, '(.+)' is updated$")
    public void xmlIsUpdated(String filename) {
        ModifyXml.modifyXml(filename);
    }

    @And("click on back navigation button")
    public void clickOnBackNavigationButton() throws Exception {
        launcherPage.switchToDefaultContent();
        launcherPage.clickBackButton();
    }

    @Then("^Navigation menu title is '(.+)'$")
    public void navigationMenuTitleIs(String title) throws Exception {
        launcherPage.switchToDefaultContent();
        assertEquals(launcherPage.getNavigationMenuTitle(), title);
    }


    @And("click on sign in link")
    public void clickOnSignInLink() throws Exception {
        loginPage.clickOnSignInLink();
    }


    @And("switches to default content")
    public void switchesToDefaultContent() throws Exception {
        launcherPage.switchToDefaultContent();
    }

    public static void enableTimestampforScreenshots() throws Exception {
        //Executing js script to append timestamp for screenshots
        JavascriptExecutor js = PortalDriver.getInstance().getConfig().getDriver();
        System.out.println("Enabling timestamp for screenshots");
        js.executeScript(script);
    }

    @And("select all fields for filter criteria")
    public void selectAllFilters() {
        List<WebElement> elements = launcherPage.getElements(By.xpath("//div[@class='sapMCbBg sapMCbHoverable sapMCbActiveStateOff sapMCbMark']"));
        elements.forEach(element -> {
            try {
                element.click();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @And("clear data in the customizations app")
    public void clearDataInTheCustomizationsApp() throws Exception {
        manageCustomizingPage.clearTheData();
    }


    @Then("App must get open and user must be able to see the customizations table and all the coulmns, add , edit, delete, sort, filter and refresh buttons")
    public void appMustGetOpenAndUserMustBeAbleToSeeTheCustomizationsTableAndAllTheCoulmnsAddEditDeleteSortFilterAndRefreshButtons() throws Exception {
        manageCustomizingPage.getTitleNumber();
        assertTrue(manageCustomizingPage.checkPageWell());
        assertEquals(manageCustomizingProperties.getProperty("MC"), manageCustomizingPage.getAppTitleName());
    }
}
