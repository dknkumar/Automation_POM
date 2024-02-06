package com.sap.cmoplatform.pages.bipoc;

import com.sap.cmoplatform.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BiPocVerifyProductPackPage extends Page {
    private By gtin = By.xpath("//*[text()='GTIN']//following::input[1]");
    private By batchNumber = By.xpath("//*[text()='Batch Number']//following::input[1]");
    private By serialNumber = By.xpath("//*[text()='Serial Number']//following::input[1]");
    private By expirationDate = By.xpath("//*[text()='Expiration Date']//following::input[1]");
    private By gs1Element = By.xpath("//*[text()='GS1 Element']//following::input[1]");
    private By glnNumberElement = By.xpath("//*[text()='GLN Number']//following::input[1]");
    private By requestType = By.xpath("//*[@class='sapMDialogTitle']/..//*[text()='Request Type']");



    public WebElement getAppElement(String appName) throws Exception {
        waitForPageToLoad();
       return getElement(By.xpath("//a[contains(@aria-label,'" + appName + "')]"));
    }
    public WebElement getGtinElement() throws Exception {
        waitForPageToLoad();
        return getElement(gtin);
    }
    public WebElement getBatchNumberElement() throws Exception {
        waitForPageToLoad();
        return getElement(batchNumber);
    }
    public WebElement getSerialNumberElement() throws Exception {
        waitForPageToLoad();
        return getElement(serialNumber);
    }
    public WebElement getExpirationDateElement() throws Exception {
        waitForPageToLoad();
        return getElement(expirationDate);
    }
    public WebElement getGs1ElementElement() throws Exception {
        waitForPageToLoad();
        return getElement(gs1Element);
    }
    public WebElement getGlnNumberElementElement() throws Exception {
        waitForPageToLoad();
        return getElement(glnNumberElement);
    }
    public WebElement getRequestTypeElement() throws Exception {
        waitForPageToLoad();
        return getElement(requestType);
    }
}
