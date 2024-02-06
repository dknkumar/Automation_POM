package com.sap.cmoplatform.pages.verifyproductpackage;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PortalDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class VerifyProductPackagePage extends Page {

    private By gtin = By.xpath("//input[@id='application-productVerify-Display-component---Verify--gtinInput-inner']");
    private By batchNumber = By.xpath("//input[@id='application-productVerify-Display-component---Verify--lotInput-inner']");
    private By serialNumber = By.xpath("//input[@id='application-productVerify-Display-component---Verify--serialInput-inner']");
    private By expirationDate = By.xpath("//input[@id='application-productVerify-Display-component---Verify--expireDate-inner']");
    private By gs1Element = By.xpath("//textarea[@id='application-productVerify-Display-component---Verify--gs1Input-inner']");
    private By verify = By.xpath("//*[text()='Verify']");
    private By expirationDateCalenderPicker = By.xpath("//span[@title='Open Picker']");
    //private By expirationDateCalenderPicker = By.xpath("//div[@class='sapMInputBaseIconContainer']/span");
    private By previousMonthArrow = By.xpath("//button[@title='Previous']");
    private By nextMonthArrow = By.xpath("//button[@title='Next']/span");
    private By errorField = By.xpath("//span[text()='GTIN']/..//div[@class='sapMInputBaseContentWrapper sapMInputBaseContentWrapperState sapMInputBaseContentWrapperError']");
    private By warningField = By.xpath("//div[@id='application-productVerify-Display-component---Verify--expireDate-content'][@class='sapMInputBaseContentWrapper sapMInputBaseContentWrapperState sapMInputBaseContentWrapperWarning']");
    private By verificationRequestTab = By.xpath("//bdi[text()='Verification Request']");
    private By gs1ElementString = By.xpath("//*[text()='GS1 Element String']");
    private By mandatoryError = By.xpath("//span[text()='Error']/following::section/div/div/span");
    private By adjustRequestHeader = By.xpath("//h2/span[text()='Adjust Request']");
    private By verificationResponseHeader = By.xpath("//span[@id='application-verification-show-component---view1--ObjectPageLayout-anchBar-__section1-anchor-content']/bdi[text()='Response']");
    private By title = By.xpath("//h2/span");
    private By decodedDataText = By.xpath("//*[text()='Decoded Data' ]");
    private By gs1ElementText = By.xpath("//*[text()='GS1 Element String']");
    private By settingsButton = By.xpath("//*[@title='Settings']");
    private By selectGlnHeader = By.xpath("//bdi[text()='Please select the GLN which serves as Requestor GLN in the Verification Request.']");
    private By barCodeIcon = By.xpath("//span[@aria-label='bar-code']");
    private By settingsIcon = By.xpath("//*[@title='Settings']");
    private By errorMessage = By.xpath("//div[@role='alertdialog']/..//section/div/div/span");
    public By requestTypeArrow = By.id("application-productVerify-Display-component---Verify--requestTypeSelect-arrow");
    private By busyIdicator = By.className("sapUiLocalBusyIndicatorAnimation sapUiLocalBusyIndicatorAnimStandard");

    WebDriverWait wait = new WebDriverWait(PortalDriver.getInstance().getConfig().getDriver(), 60);

    public void enterGTIN(String gtinValue) throws Exception {
        getElement(gtin).clear();
        inputText(gtin, gtinValue);
    }

    public void enterSerialNumber(String serialNumberValue) throws Exception {
        getElement(serialNumber).clear();
        inputText(serialNumber, serialNumberValue);
    }

    public void enterBatchNumber(String batchNumberValue) throws Exception {
        getElement(batchNumber).clear();
        inputText(batchNumber, batchNumberValue);
    }

    public void enterExpirationDate(String expirationDateValue) throws Exception {
        getElement(expirationDate).clear();
        inputText(expirationDate, expirationDateValue);
        getElement(serialNumber).click();
    }

    public WebElement getExpirationDate() throws Exception {
        return getElement(expirationDate);
    }

    public void enterGS1Element(String gs1Value) throws Exception {
        inputText(gs1Element, gs1Value);
        clickElement(gtin);
    }

    public void clickVerifyButton() throws Exception {
        clickElement(verify);

    }

    public String getExpirationDateFromGS1Element(String gs1Element) throws Exception {
        getElement(serialNumber).click();
        return gs1Element.split("\\(17\\)")[1];
    }

    public String getGtinFromGS1Element(String gs1Element) throws Exception {
        return gs1Element.split("\\(21\\)")[0].split("\\(01\\)")[1];
    }

    public String getSerialNumberFromGS1Element(String gs1Element) throws Exception {
        return gs1Element.split("\\(21\\)")[1].split("\\(10\\)")[0];
    }

    public String getBatchNumberFromGS1Element(String gs1Element) throws Exception {
        return gs1Element.split("\\(10\\)")[1].split("\\(17\\)")[0];
    }

    public boolean isWarningBorderDisplayed() throws Exception {
        return getElement(warningField).isDisplayed();
    }

    public boolean isErrorBorderDisplayed(String field) throws Exception {
        if (field.equals("expireDate")) {
            return getElement(By.xpath("//div[@id='application-productVerify-Display-component---Verify--expireDate-content'][@class='sapMInputBaseContentWrapper sapMInputBaseContentWrapperState sapMInputBaseContentWrapperError']")).isDisplayed();
        } else {
            return getElement(By.xpath("//div[@id='application-productVerify-Display-component---Verify--" + field + "Input-content'][@class='sapMInputBaseContentWrapper sapMInputBaseContentWrapperState sapMInputBaseContentWrapperError']")).isDisplayed();
        }

    }

    public boolean isErrorBorderDisplayedInGS1ElementStringTab(String field) throws Exception {
        return getElement(By.xpath("//span[text()='" + field + "']/..//div[@class='sapMInputBaseContentWrapper sapMInputBaseDisabledWrapper sapMInputBaseContentWrapperState sapMInputBaseContentWrapperError']")).isDisplayed();

    }

    public String getGhostTextSerialNumber() throws Exception {
        return getElement(serialNumber).getAttribute("placeholder");

    }

    public String getGhostTextBatchNumber() throws Exception {
        return getElement(batchNumber).getAttribute("placeholder");

    }

    public String getGtin() throws Exception {
        return getElement(gtin).getAttribute("value");

    }

    public String getBatchNumber() throws Exception {
        return getElement(batchNumber).getAttribute("value");

    }

    public String getSerialNumber() throws Exception {
        return getElement(serialNumber).getAttribute("value");


    }

    public String getExpirationDateValue() throws Exception {

        return getElement(expirationDate).getAttribute("value");

    }

    public String getGS1Element() throws Exception {
        return getElement(gs1Element).getAttribute("value");

    }

    public void clickVerificationTab() throws Exception {
        clickElement(verificationRequestTab);

    }


    public void selectFutureDateFromCalender(String date) throws Exception {
        waitForPageToLoad();
        Actions actions = new Actions(SeleniumUI5TestUtil.getConfig().getDriver());
        JavascriptExecutor js = SeleniumUI5TestUtil.getConfig().getDriver();
        js.executeScript("arguments[0].click();", getElement(expirationDateCalenderPicker));
        LocalDate currentDate = LocalDate.now();
        Month month = currentDate.getMonth();
        int nextMonthValue;
        if (month.getValue() == 12) {
            nextMonthValue = 1;
        } else {
            nextMonthValue = month.getValue() + 1;
        }
        String monthString = new DateFormatSymbols().getMonths()[nextMonthValue - 1];
        getElement(nextMonthArrow).click();
        actions.moveToElement(getElement(By.xpath("//div[contains(@aria-label,'" + monthString + "')]/span[@class='sapUiCalItemText'][text()='" + date + "']"))).click().build().perform();
    }

    public void selectPreviousDateFromCalender(String date) throws Exception {
        waitForPageToLoad();
        LocalDate currentDate = LocalDate.now();
        Month month = currentDate.getMonth();
        int prevMonthValue;
        System.out.println(month + " Month");
        if (month.getValue() == 1) {
            prevMonthValue = 12;
        } else {
            prevMonthValue = month.getValue() - 1;
        }
        System.out.println(prevMonthValue);
        String monthString = new DateFormatSymbols().getMonths()[prevMonthValue - 1];
        System.out.println(monthString);
        Actions actions = new Actions(portalDriver.getConfig().getDriver());
        JavascriptExecutor js = portalDriver.getConfig().getDriver();
        js.executeScript("arguments[0].setAttribute('style','border:2px solid red;background:yellow')", getElement(expirationDateCalenderPicker));
        js.executeScript("arguments[0].click();", getElement(expirationDateCalenderPicker));
        Thread.sleep(10000);
        js.executeScript("arguments[0].setAttribute('style','border:2px solid red;background:yellow')", getElement(previousMonthArrow));
        js.executeScript("arguments[0].click();", getElement(previousMonthArrow));
        //getElement(previousMonthArrow).click();
        Thread.sleep(20000);
        actions.moveToElement(getElement(By.xpath("//div[contains(@aria-label,'" + monthString + "')]/span[@class='sapUiCalItemText'][text()='" + date + "']"))).click().build().perform();
    }

    public void selectCurrentDateFromCalender() throws Exception {
        waitForPageToLoad();
        Actions actions = new Actions(SeleniumUI5TestUtil.getConfig().getDriver());
        JavascriptExecutor js = SeleniumUI5TestUtil.getConfig().getDriver();
        js.executeScript("arguments[0].click();", getElement(expirationDateCalenderPicker));
        actions.moveToElement(getElement(By.xpath("//div[contains(@aria-label,'today')]/span"))).click().build().perform();

    }

    public void clickOnGs1ElementString() throws Exception {
        clickElement(requestTypeArrow);
        getElement(gs1ElementString).click();
    }

    public String getMandatoryErrorMessage() throws Exception {
        return getElement(mandatoryError).getText();
    }

    public String getResponseValue(String field) throws Exception {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(busyIdicator));
        waitForPageToLoad();
        String responseValue = "";
        try {
            responseValue = getElement(By.xpath("//*[text()='" + field + "']/following::div[1]/span")).getText();
        } catch (Exception e) {
            responseValue = getElement(By.xpath("//*[text()='" + field + "']/following::div[1]/span")).getText();

            System.out.println(e.getMessage());
        }
        return responseValue;

    }

    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void isAdjustRequestHeaderDisplayed() throws Exception {
        waitForPageToLoad();
        getElement(adjustRequestHeader).isDisplayed();
    }

    public void clickAdjuctRequestButton() throws Exception {
        waitForPageToLoad();
        //   getElement(adjustRequestHeader).click();
    }

    public void isVerificationResponseHeaderDisplayed() throws Exception {
        waitForPageToLoad();
        getElement(verificationResponseHeader).isDisplayed();
    }

    public String getTitle() throws Exception {
        waitForPageToLoad();
        return getElement(title).getText();
    }

    public WebElement getDecodedDataText() throws Exception {
        waitForPageToLoad();
        return getElement(decodedDataText);
    }

    public WebElement getGs1ElementText() throws Exception {
        waitForPageToLoad();
        return getElement(gs1ElementText);
    }

    public void clickSettingsButton() throws Exception {
        waitForPageToLoad();
        getElement(settingsButton).click();
    }

    public void clickSettingsBtnInVerifyScreen() throws Exception {
        waitForPageToLoad();
        getElement(settingsIcon).click();
    }

    public WebElement getSelectGLNScreenHeader() throws Exception {
        waitForPageToLoad();
        return getElement(selectGlnHeader);
    }

    public String selectSenderGLN(String gln1, String gln2) throws Exception {
        if (getElement(By.xpath("//div[@class='sapMSLITitleOnly'][text()='" + gln1 + "']/preceding::input[1]")).isSelected()) {
            getElement(By.xpath("//div[@class='sapMSLITitleOnly'][text()='" + gln2 + "']/preceding::input[1]/parent::div")).click();
            return gln2;
        } else {
            getElement(By.xpath("//div[@class='sapMSLITitleOnly'][text()='" + gln1 + "']/preceding::input[1]/parent::div")).click();
            return gln1;
        }
    }

    public void selectGLN(String gln) throws Exception {
        getElement(By.xpath("//div[@class='sapMSLITitleOnly'][text()='" + gln + "']/preceding::input[1]/parent::div")).click();


    }

    public void clickBarCodeIcon() throws Exception {
        waitForPageToLoad();
        getElement(barCodeIcon).click();
    }

    public String getErrorMessage() throws Exception {
        waitForPageToLoad();
        return getElement(errorMessage).getText();
    }
}