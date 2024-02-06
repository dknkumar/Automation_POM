package com.sap.cmoplatform;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.sap.cmoplatform.actions.ClickAction;
import com.sap.cmoplatform.actions.FindElementAction;
import com.sap.cmoplatform.actions.SendKeysAction;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;

/**
 * Utility class that facilitates quick writing of Selenium tests in SoapUI.
 */
public class SeleniumUI5TestUtil {

    private static Config config;

    /**
     * Constructor using a custom configuration object.
     *
     * @param config configuration object containing numerous values used by the {@link SeleniumUI5TestUtil} class
     */
    public SeleniumUI5TestUtil(Config config) {
        super();
        this.setConfig(config);
    }

    /**
     * Log into the environment using the provided credentials.
     *
     * @param username the username of the test user to be used for the test
     * @param password the password of the test user to be used for the test
     * @throws InterruptedException thrown by Thread.sleep()
     */
    public void login(String username, String password) throws Exception {
        sendText(findElement(By.id(this.getConfig().USERNAME_FIELD_ID)), username);
        sendText(findElement(By.id(this.getConfig().PASSWORD_FIELD_ID)), password);
        clickElement(By.id(this.getConfig().LOGIN_BUTTON_ID));
    }

    /**
     * Open the list with an id matching searchId and select the item with the text matching itemName.
     *
     * @param searchId the id of the element to be clicked to open the list
     * @param itemName the name of the list item to be selected
     * @throws Exception catch-all exception for a number of errors that the test may throw catch-all exception for a number of errors that the test may throw
     */
    public void selectListItem(String searchId, String itemName) throws Exception {
        this.clickElement(By.id(searchId));
        String searchBoxId = this.findElement(By.xpath("//input[@type='search']")).getAttribute("id");
        this.inputText(searchBoxId, itemName);
        Thread.sleep(2000);
        this.clickElement(By.xpath(this.getConfig().getSearchItemXPath(itemName)));
    }

    /**
     * Open the table with an id matching searchId and select the row with the text matching itemName.
     *
     * @param searchId the id of the table box to be clicked to open the table
     * @param itemName the name of the table row to be selected
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void selectTableItem(String searchId, String itemName) throws Exception {
        this.clickElement(By.id(searchId));
        String searchBoxId = this.findElement(By.xpath("//input[@type='search']")).getAttribute("id");
        this.inputText(searchBoxId, itemName);
        Thread.sleep(2000);
        this.clickElement(By.xpath(this.getConfig().getSearchRowXPath(itemName)));
    }

    public void selectTableItemFromOrg(String searchId, String itemName) throws Exception {
        this.clickElement(By.id(searchId));
        String searchBoxId = this.findElement(By.xpath("//input[@type='search']")).getAttribute("id");
        this.inputText(searchBoxId, itemName);
        Thread.sleep(2000);
        this.clickElement(By.xpath(this.getConfig().getSearchRowXPath(itemName+" ")));
    }

    /**
     * Open the dropdown with an id matching dropDownId and select the option with the text matching itemName.
     *
     * @param dropDownId the id of the dropdown box to be clicked to open the dropdown
     * @param itemName The name of the dropdown item to be selected
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void selectDropdownItem(String dropDownId, String itemName) throws Exception {
        this.clickElement(By.id(dropDownId));
        this.clickElement(By.xpath(this.getConfig().getSearchItemXPath(itemName)));
}
    /**
     * Open the dropdown with a Webelement matching dropDownId and select the option with the text matching itemName.
     *
     * @param dropDownId the webelement of the dropdown box to be clicked to open the dropdown
     * @param itemName The name of the dropdown item to be selected
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void selectDropdownItem(WebElement dropDownId, String itemName) throws Exception {
        this.clickElement(dropDownId);
        this.clickElement(By.xpath(this.getConfig().getSearchItemXPath(itemName)));
    }

    /**
     * Enter text in the WebElement with the corresponding id. <br>
     * Makes sure that the entered text matches the expected result.
     *
     * @param elementId the id of the element that should receive the text
     * @param text      the text that should be sent to the input element
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void inputText(String elementId, String text) throws Exception {
        WebElement inputElement = findElement(By.id(elementId));
        clickElement(elementId);
        for (char c : text.toCharArray()) {
            Thread.sleep(this.getConfig().getShortInterval());
            sendText(inputElement, String.valueOf(c));
        }
        Thread.sleep(this.getConfig().getLongInterval());
    }
    public void inputText(By element, String text) throws Exception {
        WebElement inputElement = findElement(element);
        clickElement(inputElement);
        for (char c : text.toCharArray()) {
            Thread.sleep(this.getConfig().getShortInterval());
            sendText(inputElement, String.valueOf(c));
        }
        Thread.sleep(this.getConfig().getLongInterval());
    }

    /**
     * Click button with an unknown id.
     *
     * @param buttonName the text that the button to be clicked contains
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void clickButtonByName(String buttonName) throws Exception {
        Retry.doWithRetry(this.getConfig().getLongInterval(), this.getConfig().getTimeoutPeriod(),
                new ClickAction(this.getConfig().getDriver(), By.xpath(this.getConfig().getButtonXPath(buttonName))));
    }

    /**
     * Click the Fiori tile that contains the provided text.
     *
     * @param appName the text on the tile of the selected app
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void clickFioriApp(String appName) throws Exception {
        int maxWait = getConfig().getTimeoutPeriod();
        int interval = getConfig().getMediumInterval();

        for (int i = 0; i < maxWait; i += interval) {
            try {
                String id = getAppId(appName);
                System.out.println(id+  " Id");
                getConfig().getDriver().findElement(By.id(id)).click();
                return;
            } catch (Exception e) {
                if (i + interval >= maxWait) {
                    throw new SeleniumUtilException(e.toString());
                } else
                    Thread.sleep(interval);
            }
        }
    }

    /**
     * Click the Fiori tile that contains the provided text.
     *
     * @param appName the text on the tile of the selected app
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void clickUsscApp(String appName) throws Exception {
        int maxWait = getConfig().getTimeoutPeriod();
        int interval = getConfig().getMediumInterval();

        for (int i = 0; i < maxWait; i += interval) {
            try {if(appName.equals("Verification Log")){
                getConfig().getDriver().findElement(By.xpath("//a[contains(@aria-label,'"+ appName + "')][contains(@aria-label,'Verifications to Review')]")).click();

            }else {
                getConfig().getDriver().findElement(By.xpath("//a[contains(@aria-label,'" + appName + "')]")).click();
            }
                return;
            } catch (Exception e) {
                if (i + interval >= maxWait) {
                    throw new SeleniumUtilException(e.toString());
                } else
                    Thread.sleep(interval);
            }
        }
    }

    /**
     * Click the WebElement with the provided ID.
     *
     * @param id the id of the element to click
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void clickElement(String id) throws Exception {
        Retry.doWithRetry(this.getConfig().getMediumInterval(), this.getConfig().getTimeoutPeriod(),
                new ClickAction(this.getConfig().getDriver(), By.id(id)));
    }

    /**
     * Click the WebElement with the provided selector. <br>
     * If the selector matches multiple elements, the first element will be clicked.
     *
     * @param selector the selector of the element to be clicked
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void clickElement(By selector) throws Exception {
        Retry.doWithRetry(this.getConfig().getMediumInterval(), this.getConfig().getTimeoutPeriod(),
                    new ClickAction(this.getConfig().getDriver(), selector));
    }

    /**
     * Click the provided WebElement.
     *
     * @param element element to click
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void clickElement(WebElement element) throws Exception {
        Retry.doWithRetry(this.getConfig().getMediumInterval(), this.getConfig().getTimeoutPeriod(),
                new ClickAction(this.getConfig().getDriver(), By.id(element.getAttribute("id"))));
    }

    /**
     * Send text to the provided element.
     * Does not make sure that the entered text matches the expected result. <br>
     * Where possible use {@link SeleniumUI5TestUtil#inputText} instead.
     *
     * @param element the element that should receive the text
     * @param text    the text that should be sent to the input element
     * @throws Exception catch-all exception for a number of errors that the test may throw
     * @see SeleniumUI5TestUtil#inputText
     */
    private void sendText(WebElement element, String text) throws Exception {
        Retry.doWithRetry(this.getConfig().getMediumInterval(), this.getConfig().getTimeoutPeriod(),
                new SendKeysAction(this.getConfig().getDriver(), By.id(element.getAttribute("id")), text));
    }

    /**
     * Send a keyboard key to the provided element. <br>
     * For sending text use {@link SeleniumUI5TestUtil#inputText} <br>
     * or {@link SeleniumUI5TestUtil#sendText} instead.
     *
     * @param element the element that should receive the sent key
     * @param keys    the key that should be sent to the element
     * @throws Exception catch-all exception for a number of errors that the test may throw
     * @see SeleniumUI5TestUtil#inputText
     * @see SeleniumUI5TestUtil#sendText
     */
    private void sendKey(WebElement element, Keys keys) throws Exception {
        Retry.doWithRetry(this.getConfig().getMediumInterval(), this.getConfig().getTimeoutPeriod(),
                new SendKeysAction(this.getConfig().getDriver(), By.id(element.getAttribute("id")), keys.toString()));

    }

    /**
     * Find the elements on the page using the provided selector. <br>
     *
     * @param selector the selector by which to get the element
     * @return List WebElement matching the selector if successful, null if failed to find element
     */
    public List<WebElement> findElements(By selector) {
        return this.getConfig().getDriver().findElements(selector);
    }

    /**
     * Find an element on the page using the provided selector. <br>
     * If the selector matches multiple elements, returns the first one.
     *
     * @param selector the selector by which to get the element
     * @return web element matching the selector if successful, null if failed to find element
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public WebElement findElement(By selector) throws Exception {
        return (WebElement) Retry.getWithRetry(this.getConfig().getMediumInterval(), this.getConfig().getTimeoutPeriod(),
                new FindElementAction(this.getConfig().getDriver(), selector));
    }

    /**
     * Returns the rows of a list
     *
     * @param by the selector by which to find the list
     * @return list elements of rows from the list
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public List<WebElement> getListRows(By by) throws Exception {
        WebElement list = findElement(by);
        return list.findElements(By.tagName("li"));
    }

    /**
     * Returns the rows of a list
     *
     * @param by the selector by which to find the table
     * @return list elements of rows from the table
     */
    public List<WebElement> getTableRows(By by) {
        List<WebElement> tables = findElements(by);
        return tables.get(tables.size()-1).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
    }

    /**
     * Find the id of the currently opened fragment.
     *
     * @return id of the currently opened fragment
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public String findOpenFragmentId() throws Exception {
        return findElement(By.xpath(this.getConfig().getCssDialog())).getAttribute("id");
    }

    /**
     * Find the id of the app matching appName.
     *
     * @param appName the text on the tile of the selected app
     * @return app id if successful, "No App Found" if no app matching the text could be found
     */
    public String getAppId(String appName) {
        List<WebElement> apps = findElements(By.cssSelector(this.getConfig().getCssSelector()));
        for (WebElement app : apps) {
            System.out.println((app.getAttribute("aria-label")));
            if (app.getAttribute("aria-label").contains(appName)) {
                return app.getAttribute("id");
            }
        }
        return "No App Found";
    }

    /**
     * Scroll to bottom of element with the given selector. <br>
     * Used in UI5 lists, tables, etc. to load all elements before selecting one.
     *
     * @param selector the selector of the item which should be scrolled to the bottom
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void scrollToBottom(By selector) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(getConfig().getShortInterval());
            sendKey(this.findElement(selector), Keys.END);
        }
    }
    /**
     * Scroll to element. <br>
     * Used in UI5 lists, tables, etc. to load all elements before selecting one.
     *
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public void scrollToElement(WebElement element) throws Exception {
        JavascriptExecutor js = getConfig().getDriver();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(getConfig().getShortInterval());
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }


    /**
     * Open the browser at the URL of the homepage.
     */
    public void openUrl() {
      //  this.getConfig().getDriver().manage().window().maximize();
        System.out.println("url "+ getConfig().getHomePageUrl());
        this.getConfig().getDriver().get(getConfig().getHomePageUrl());
    }

    /**
     * Close the web driver.
     */
    public void quitDriver() {
        this.getConfig().getDriver().quit();
    }
    /**
     * maximise the window
     */
    public void maximiseWindow() {
      //  this.getConfig().getDriver().manage().window().maximize();
    }
    /**
     * Get the configuration object associated with this instance of the {@link SeleniumUI5TestUtil} class
     *
     * @return configuration object containing numerous values used by the {@link SeleniumUI5TestUtil} class
     */
    public static Config getConfig() {
        return config;
    }

    /**
     * Set the configuration for this instance of the class.
     *
     * @param config configuration object containing numerous values used by the {@link SeleniumUI5TestUtil} class
     */
    private void setConfig(Config config) {
        this.config = config;
    }

    //TODO replace this method with something better
    public void waitFor(long milli) throws InterruptedException {
        Thread.sleep(milli);
    }
    /**
     * Waits for the javascript in the background on the page to finish running.
     */
    public void waitForLoad() throws Exception {
        JavascriptExecutor js = getConfig().getDriver();
        for (int i = 0; i < getConfig().getTimeoutPeriod(); i += getConfig().getLongInterval()) {
            Thread.sleep(getConfig().getLongInterval());
            String status = js.executeScript("return document.readyState").toString();
            if (status.equals("complete")) {
                break;
            }
        }
    }

    public void waitForLoad(By byPopoverId) throws Exception {
        boolean popoverElementActive = true;
        while (popoverElementActive) {
            try {
                getConfig().getDriver().findElement(byPopoverId);
                Thread.sleep(getConfig().getMediumInterval());
            } catch (Exception e) {
                popoverElementActive = false;
            }
        }
    }

    public String getPageTitle() {
        return getConfig().getDriver().getTitle();
    }

    /**
     * Returns the set of window handles
     *
     * @return set elements of window handles
     **/
    public Set<String> getWindowHandles() {
        return getConfig().getDriver().getWindowHandles();
    }
    /**
     * Returns the window handles
     *
     * @return window handle
     **/
    public String getWindowHandle() {
        return getConfig().getDriver().getWindowHandle();
    }
    /**
     * Switch to the specified window handle
     *
     * @param handle of window
     */
    public void switchToWindow(String handle) {
         getConfig().getDriver().switchTo().window(handle);
    }
    /**
     * Switch to the specified frame
     *
     * @param frameId of frames
     */
    public void switchToFrame(String frameId) {
        getConfig().getDriver().switchTo().frame(frameId);
    }
    public void switchToAlert() {
        getConfig().getDriver().switchTo().alert();
    }
    public void switchToGenericFrame(){
        getConfig().getDriver().switchTo().frame(getConfig().getDriver().findElement(By.xpath("//iframe[contains(@id,'application')]")));
    }

    public void switchToDefaultContent(){
        getConfig().getDriver().switchTo().defaultContent();
    }


    /**
     * Open the list with an id matching searchId and select the item with the text matching itemName.
     *
     * @param searchId the id of the element to be clicked to open the list
     * @param itemName the name of the list item to be selected
     * @throws Exception catch-all exception for a number of errors that the test may throw catch-all exception for a number of errors that the test may throw
     */
    public void selectListItem(WebElement searchId, String itemName) throws Exception {
        waitForLoad();
        this.clickElement(searchId);
        String searchBoxId = this.findElement(By.xpath("//input[@type='search']")).getAttribute("id");
        this.inputText(searchBoxId, itemName);
        Thread.sleep(2000);
        this.clickElement(By.xpath(this.getConfig().getSearchItemXPath(itemName)));
    }
    public Alert getAlert(){
       return getConfig().getDriver().switchTo().alert();
    }


    public WebDriver.Options manage() {
        return null;
    }
}
