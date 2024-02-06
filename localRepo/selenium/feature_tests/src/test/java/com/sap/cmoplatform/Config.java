package com.sap.cmoplatform;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Class that contains all of the configuration used by {@link SeleniumUI5TestUtil}.
 */
public class Config {

    // Default configuration values for SeleniumTestHelper
    private final int timeoutPeriod = 20000;
    private final int veryShortInterval = 50;
    private final int shortInterval = 100;
    private final int mediumInterval = 500;
    private final int longInterval = 1000;
    private final int pageLoadTimeout = 60;
    private final int webDriverWaitTimeout = 5;

    // Variables that get instantiated in the constructor
    private UI5Version ui5Version;
    private ChromeDriver driver;
    private WebDriverWait wait;

    // Variables that get set based on the selected UI5 version
    private String homePageUrl = "";
    private String cssSelector = "";
    private String cssDialog = "";
    private String searchItemXPath = "";
    private String searchRowXPath = "";
    private String buttonXPath = "";

    // Home page Urls
    private String HOME_URL_LOCAL = "http://localhost:8000/webapp/test/GruntIndex.html";
    private String HOME_URL_DEV = "https://flpsandbox-w6cd17e00.dispatcher.int.sap.hana.ondemand.com/sites/pharmanetworkcmoportal1511889010758#Shell-home";
    private String HOME_URL_QA = "https://bieno-da11-q-904331-webapi-08.azurewebsites.net";
    //"https://pbc-qa.launchpad.cfapps.us10.hana.ondemand.com/site?siteId=82c6c20f-8301-47a5-a94e-0677aba62b79#Shell-home";
    //private String HOME_URL_QA = "https://flpnwc-a629a2553.dispatcher.hana.ondemand.com/sites/pharmanetworkportal-refactor#Shell-home";
    private final String HOME_URL_STAGING = "https://ich-pbc-stage-pbc-launchpad.cfapps.us10.hana.ondemand.com/sites#verification-show";

    // QA-DEV-STAGING options for choosing UI5 version specific xpaths
    private final String CSS_SELECTOR_LOCAL = "*[class='OneByOne sapMGT sapMGTScopeDisplay sapMGTStateLoaded sapMPointer']";
    private final String CSS_SELECTOR_DEV = "*[class='OneByOne sapMGT sapMGTScopeDisplay sapMGTStateLoaded sapMPointer']";
    //private String CSS_SELECTOR_QA = "*[class='OneByOne sapMGT sapMGTScopeDisplay sapMGTStateLoaded sapMPointer']";
    private final String CSS_SELECTOR_QA = "*[class='sapMGT sapMGTStateLoaded sapMGTScopeDisplay OneByOne sapMPointer']";
    private final String CSS_SELECTOR_STAGING = "*[class='OneByOne sapMGT sapMGTScopeDisplay sapMGTStateLoaded sapMPointer']";

    private final String CSS_DIALOG_LOCAL = "//div[contains(@class,'sapMDialog')]";
    private final String CSS_DIALOG_DEV = "//div[contains(@class,'sapMDialog')]";
    private final String CSS_DIALOG_QA = "//div[contains(@role,'alertdialog')]";
    private final String CSS_DIALOG_STAGING = "//div[contains(@class,'sapMDialog')]";

    private final String SEARCH_ITEM_XPATH_LOCAL = "//*[.='?']";
    private final String SEARCH_ITEM_XPATH_DEV = "//*[.='?']";
    private final String SEARCH_ITEM_XPATH_QA = "//div[@role='dialog']//*[text()='?']";
    private final String SEARCH_ITEM_XPATH_STAGING = "//*[.='?']";

    private final String SEARCH_ROW_XPATH_LOCAL = "//*[text()='?']";
    private final String SEARCH_ROW_XPATH_DEV = "//*[text()='?']";
    private final String SEARCH_ROW_XPATH_QA = "//*[text()='?']";
    private final String SEARCH_ROW_XPATH_STAGING = "//*[text()='?']";

    private final String BUTTON_XPATH_LOCAL = "//button//span//span[text()='?']";
    private final String BUTTON_XPATH_DEV = "//button//span//span//bdi[text()='?']";
    private final String BUTTON_XPATH_QA = "//button/span/span/bdi[text()='?']";
    private final String BUTTON_XPATH_STAGING = "//button//span//span//bdi[text()='?']";

    // IDs of elements related to logging into the environment
    final String USERNAME_FIELD_ID = "j_username";
    final String PASSWORD_FIELD_ID = "j_password";
    final String LOGIN_BUTTON_ID = "logOnFormSubmit";

    private final int startX = 0;
    private final int startY = 0;
    private final int width = 1920;
    private final int height = 1080;

    /**
     * Constructor using UI5 version (mandatory).
     *
     * @param version the SAPUI5 version currently used by the app being tested.
     * @param options the SAPUI5 options for chromedriver.
     */
    public Config(UI5Version version, ChromeOptions options) {
        this.setUi5Version(version);
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(this.getPageLoadTimeout(), TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(startX, startY));
        driver.manage().window().setSize(new Dimension(width, height));
        driver.manage().window().maximize();
        this.setDriver(driver);

        this.setWait(new WebDriverWait(driver, webDriverWaitTimeout));
    }


    // Public Getters and setters
    public int getTimeoutPeriod() {
        return timeoutPeriod;
    }

    private int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public int getWebDriverWaitTimeout() {
        return webDriverWaitTimeout;
    }

    public UI5Version getUi5Version() {
        return ui5Version;
    }

    private void setUi5Version(UI5Version ui5Version) {

        // Set the UI5 version and assign the appropriate xpaths
        this.ui5Version = ui5Version;
        if (ui5Version == UI5Version.QA) {
            this.setHomePageUrl(HOME_URL_QA);
            this.setCssSelector(CSS_SELECTOR_QA);
            this.setCssDialog(CSS_DIALOG_QA);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_QA);
            this.setButtonXPath(BUTTON_XPATH_QA);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_QA);
        } else if (ui5Version == UI5Version.QA_SNR) {
            this.setCssSelector(CSS_SELECTOR_QA);
            this.setCssDialog(CSS_DIALOG_QA);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_QA);
            this.setButtonXPath(BUTTON_XPATH_QA);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_QA);
        } else if (ui5Version == UI5Version.QA_SEM) {
            this.setCssSelector(CSS_SELECTOR_QA);
            this.setCssDialog(CSS_DIALOG_QA);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_QA);
            this.setButtonXPath(BUTTON_XPATH_QA);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_QA);
        } else if (ui5Version == UI5Version.QA_TI) {
            this.setCssSelector(CSS_SELECTOR_QA);
            this.setCssDialog(CSS_DIALOG_QA);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_QA);
            this.setButtonXPath(BUTTON_XPATH_QA);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_QA);
        } else if (ui5Version == UI5Version.QA_REL) {
            this.setCssSelector(CSS_SELECTOR_QA);
            this.setCssDialog(CSS_DIALOG_QA);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_QA);
            this.setButtonXPath(BUTTON_XPATH_QA);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_QA);
        } else if (ui5Version == UI5Version.QA_OB) {
            this.setCssSelector(CSS_SELECTOR_QA);
            this.setCssDialog(CSS_DIALOG_QA);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_QA);
            this.setButtonXPath(BUTTON_XPATH_QA);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_QA);
        } else if (ui5Version == UI5Version.DEV) {
            this.setHomePageUrl(HOME_URL_DEV);
            this.setCssSelector(CSS_SELECTOR_DEV);
            this.setCssDialog(CSS_DIALOG_DEV);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_DEV);
            this.setButtonXPath(BUTTON_XPATH_DEV);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_DEV);
        } else if (ui5Version == UI5Version.DEV_SNR) {
            this.setCssSelector(CSS_SELECTOR_DEV);
            this.setCssDialog(CSS_DIALOG_DEV);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_DEV);
            this.setButtonXPath(BUTTON_XPATH_DEV);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_DEV);
        } else if (ui5Version == UI5Version.DEV_SEM) {
            this.setCssSelector(CSS_SELECTOR_DEV);
            this.setCssDialog(CSS_DIALOG_DEV);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_DEV);
            this.setButtonXPath(BUTTON_XPATH_DEV);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_DEV);
        } else if (ui5Version == UI5Version.DEV_TI) {
            this.setCssSelector(CSS_SELECTOR_DEV);
            this.setCssDialog(CSS_DIALOG_DEV);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_DEV);
            this.setButtonXPath(BUTTON_XPATH_DEV);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_DEV);
        } else if (ui5Version == UI5Version.DEV_REL) {
            this.setCssSelector(CSS_SELECTOR_DEV);
            this.setCssDialog(CSS_DIALOG_DEV);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_DEV);
            this.setButtonXPath(BUTTON_XPATH_DEV);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_DEV);
        } else if (ui5Version == UI5Version.DEV_OB) {
            this.setCssSelector(CSS_SELECTOR_DEV);
            this.setCssDialog(CSS_DIALOG_DEV);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_DEV);
            this.setButtonXPath(BUTTON_XPATH_DEV);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_DEV);
        } else if (ui5Version == UI5Version.LOCAL) {
            this.setHomePageUrl(HOME_URL_LOCAL);
            this.setCssSelector(CSS_SELECTOR_LOCAL);
            this.setCssDialog(CSS_DIALOG_LOCAL);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_LOCAL);
            this.setButtonXPath(BUTTON_XPATH_LOCAL);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_LOCAL);
        } else if (ui5Version == UI5Version.STAGING) {
            this.setHomePageUrl(HOME_URL_STAGING);
            this.setCssSelector(CSS_SELECTOR_STAGING);
            this.setCssDialog(CSS_DIALOG_STAGING);
            this.setSearchItemXPath(SEARCH_ITEM_XPATH_STAGING);
            this.setButtonXPath(BUTTON_XPATH_STAGING);
            this.setSearchRowXPath(SEARCH_ROW_XPATH_STAGING);
        }
    }

    public ChromeDriver getDriver() {
        return driver;
    }

    private void setDriver(ChromeDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    int getVeryShortInterval() {
        return veryShortInterval;
    }

    int getShortInterval() {
        return shortInterval;
    }


    public int getMediumInterval() {
        return mediumInterval;
    }

    public int getLongInterval() {
        return longInterval;
    }


    // Getters for homepage url
    String getHomePageUrl() {
        return homePageUrl;
    }

    // Getters for CSS classes and XPATHS - available within the same package
    public String getCssSelector() {
        return cssSelector;
    }

    String getCssDialog() {
        return cssDialog;
    }

    String getSearchItemXPath(String searchItemName) {
        return searchItemXPath.replace("?", searchItemName);
    }

    String getSearchRowXPath(String searchRowName) {
        return searchRowXPath.replace("?", searchRowName);
    }

    String getButtonXPath(String buttonName) {
        return buttonXPath.replace("?", buttonName);
    }

    // Setter for the home page url
    private void setHomePageUrl(String url) {
        this.homePageUrl = url;
    }

    // Setters for CSS classes and XPATHS - private
    private void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    private void setCssDialog(String cssDialog) {
        this.cssDialog = cssDialog;
    }

    private void setSearchItemXPath(String searchItemXPath) {
        this.searchItemXPath = searchItemXPath;
    }

    private void setSearchRowXPath(String searchRowXPath) {
        this.searchRowXPath = searchRowXPath;
    }

    private void setButtonXPath(String buttonXPath) {
        this.buttonXPath = buttonXPath;
    }
}
