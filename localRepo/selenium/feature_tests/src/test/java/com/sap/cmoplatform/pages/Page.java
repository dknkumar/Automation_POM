package com.sap.cmoplatform.pages;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.utils.PortalDriver;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public abstract class Page {

    private static final Properties ui5PageIds = PropertyReader.loadProperties("Ui5PageIds.properties");
    private final String toastMessageXpath = ui5PageIds.getProperty("ui5.toastMessageXpath");
    private final String toastXpath = ui5PageIds.getProperty("ui5.toastXpath");
    private static final String busyIndicatorId = ui5PageIds.getProperty("ui5.busyIndicatorId");
    private final String appTitleId = ui5PageIds.getProperty("ui5.appTitleId");

    protected static SeleniumUI5TestUtil portalDriver = PortalDriver.getInstance();

    private static final DateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
     static Date date = new Date();



    private static final String orgName = (sdf.format(date));


    public static final String getOrgValue() {
        return orgName;
    }

    public static void clickButton(String buttonName) throws Exception {
        portalDriver.waitForLoad();

        portalDriver.clickButtonByName(buttonName);
    }

    public String getToastMessage() throws Exception {
        portalDriver.waitForLoad();
//        WebElement toast;
//        try {
//            toast = portalDriver.findElement(By.xpath(toastXpath));
//
//        }catch(Exception e){
//            toast = portalDriver.findElement(By.xpath(toastMessageXpath));
//        }
        return portalDriver.findElement(By.xpath(toastXpath)).getText();
    }

    public String getSuccessToastMessage() throws Exception {
        portalDriver.waitForLoad();
        WebElement toast;
        try {
            toast = portalDriver.findElement(By.xpath(toastMessageXpath));
        }catch(Exception e){
            toast = portalDriver.findElement(By.xpath(toastXpath));
        }
        return toast.getText();
    }

    public String getErrorToastMessage() throws Exception {
        portalDriver.waitForLoad();
        WebElement toast;
        try {
            toast = portalDriver.findElement(By.xpath(toastXpath));

        }catch(Exception e){
            toast = portalDriver.findElement(By.xpath(toastMessageXpath));

        }
        return toast.getText();
    }
    protected String replaceIndexInId(int index, String firstEntryId) {
        if (index > 0) {
            String target = "0";
            String newChar = Integer.toString(index - 1);
            return firstEntryId.replace(target, newChar);
        } else {
            return firstEntryId;
        }
    }


    public static void waitForPageToLoad() throws Exception {
        portalDriver.waitForLoad();
        portalDriver.waitForLoad(By.id(busyIndicatorId));
    }

    public void waitForTableToLoad() throws Exception {
        portalDriver.waitFor(5000);
        portalDriver.waitForLoad();
        portalDriver.waitForLoad(By.id(busyIndicatorId));
    }

    protected void selectFragmentListFilterInput(String fragmentFilterInputId, String itemName) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.selectListItem(fragmentFilterInputId, itemName);
    }

    protected void selectFragmentTableFilterInput(String fragmentFilterInputId, String itemName) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.selectTableItem(fragmentFilterInputId, itemName);
    }
    protected void selectFragmentTableFilterInputForOrg(String fragmentFilterInputId, String itemName) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.selectTableItemFromOrg(fragmentFilterInputId, itemName);
    }

    protected void selectFragmentListFilterInput(WebElement fragmentFilterInputId, String itemName) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.selectListItem(fragmentFilterInputId, itemName);
    }

    public void selectDropDownFilterInput(String statusFilterInputId, String itemName) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.selectDropdownItem(statusFilterInputId, itemName);
    }

    public void selectDropDownFilterInput(WebElement statusFilterInputId, String itemName) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.selectDropdownItem(statusFilterInputId, itemName);
    }


    protected static void clickElement(String elementId) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.clickElement(elementId);
        portalDriver.waitForLoad();
    }

    public static void clickElement(By selector) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.clickElement(selector);
        portalDriver.waitForLoad();
    }

    protected void scrollToandclickElement(By selector) throws Exception {
        scrollToElement((WebElement) selector);
        portalDriver.clickElement(selector);
    }

    protected static void clickElement(WebElement webElement) throws Exception {
        waitForPageToLoad();
        portalDriver.waitForLoad();
        portalDriver.clickElement(webElement);
        waitForPageToLoad();
    }

    public void scrollToandclickElement(WebElement webElement) throws Exception {
        scrollToElement(webElement);
        portalDriver.clickElement(webElement);
    }


    protected void scrollToElement(WebElement webElement) throws Exception {
        ((JavascriptExecutor) portalDriver.getConfig().getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    protected void scrollToElement(By element) throws Exception {
        WebElement webElement = getElement(element);
        ((JavascriptExecutor) portalDriver.getConfig().getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    protected static void inputText(String inputId, String text) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.inputText(inputId, text);
    }

    public void inputText(By element, String text) throws Exception {
        portalDriver.waitForLoad();
        portalDriver.inputText(element, text);
    }

    protected WebElement getElement(String elementId) throws Exception {
        portalDriver.waitForLoad();
        return portalDriver.findElement(By.id(elementId));
    }

    public static WebElement getElement(By selector) throws Exception {
        portalDriver.waitForLoad();
        return portalDriver.findElement(selector);
    }

    public List<WebElement> getElements(By selector) {
        return portalDriver.findElements(selector);
    }

    protected List<WebElement> getTableRows(By by) {
        return portalDriver.getTableRows(by);
    }

    protected List<WebElement> getListRows(By selector) throws Exception {
        return portalDriver.getListRows(selector);
    }

    protected boolean doesElementExist(By selector) {
        WebElement element;
        try {
            element = portalDriver.findElement(selector);
        } catch (Exception e) {
            return false;
        }
        return (element != null);
    }

    protected int getCount(String elementId) throws Exception {
        waitForTableToLoad();
        String text = getElement(elementId).getText();
        int beginIndex = text.indexOf("(") + 1;
        int endIndex = text.indexOf(")");
        String number = text.substring(beginIndex, endIndex);
        return Integer.parseInt(number);
    }

    public String getErrorMessage() throws Exception{
        return portalDriver.findElement(By.xpath("//section[@id='oErrorFragment-cont']/div/div/span")).getText();
    }

    protected boolean hasErrorBorder(String field, String expectedClass) throws Exception {
        return getElement(field).getAttribute("class").equals(expectedClass);
    }

    protected void clickShowNavigationMenu() throws Exception {
        clickElement(appTitleId);
    }

    public String getPageTitle() throws Exception {
        waitForPageToLoad();
        return portalDriver.getPageTitle();
    }
}










































































































































































































































































































































































































































































































































