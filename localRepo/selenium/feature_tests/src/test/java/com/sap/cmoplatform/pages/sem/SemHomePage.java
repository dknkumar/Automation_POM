package com.sap.cmoplatform.pages.sem;

import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.objects.Sem;
import com.sap.cmoplatform.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.*;

public class SemHomePage extends Page {

    private Properties semHomePageIds = PropertyReader.loadProperties("sem/SemHomePageIds.properties");
    private String prefix = semHomePageIds.getProperty("sem.homePage.pagePrefix");
    private String customerFilterInputId = prefix + semHomePageIds.getProperty("sem.homePage.customerFilterInputId");
    private String productFilterInputId = prefix + semHomePageIds.getProperty("sem.homePage.productFilterInputId");
    private String dateFilterInputId = prefix + semHomePageIds.getProperty("sem.homePage.dateFilterInputId");
    private String statusFilterInputId = prefix + semHomePageIds.getProperty("sem.homePage.statusFilterInputId");
    private String showHideId = prefix + semHomePageIds.getProperty("sem.homePage.showHideFilterBarButtonId");
    private String goButtonId = prefix + semHomePageIds.getProperty("sem.homePage.goButtonId");
    public By newMessageButtonId = By.xpath("//*[text()='New Message']");
    private By messageTableId = By.xpath("//*[contains(@id,'oSemTable-listUl')]");
    private String semCount = prefix + semHomePageIds.getProperty("sem.homePage.semCount");
    private String clearFilterButtonId = prefix + semHomePageIds.getProperty("sem.homePage.clearFilterButtonId");
    private String batchIdInputId = prefix + semHomePageIds.getProperty("sem.homePage.batchIdInputId");
    public By refreshButton = By.xpath("//button[@title='Refresh SEM']/span/span");

    private static final Logger LOGGER = LogManager.getLogger(new Object() {
    }.getClass().getEnclosingClass().getName());


    public void selectCustomer(String itemName) throws Exception {
        selectFragmentListFilterInput(customerFilterInputId, itemName);
    }

    public void selectProduct(String itemName) throws Exception {
        selectFragmentListFilterInput(productFilterInputId, itemName);
    }

    public void selectBatchId(String batchId) throws Exception {
        inputText(batchIdInputId, batchId);
    }

    private void selectDateRange(String dateRange) throws Exception {
        inputText(dateFilterInputId, dateRange);
    }

    public void selectStatus(String itemName) throws Exception {
        selectDropDownFilterInput(statusFilterInputId, itemName);
    }

    public void clickNewMessageButton() throws Exception {
        getElement(newMessageButtonId).click();
    }

    public void clickGoButton() throws Exception {
        clickElement(goButtonId);
    }

    public void clickShowHideButton() throws Exception {
        clickElement(showHideId);
    }

    public void clickClearFilterButton() throws Exception {
        clickElement(clearFilterButtonId);
    }

    public boolean canSeeStatus() {
        WebElement element;
        try {
            element = getElement(statusFilterInputId);
        } catch (Exception e) {
            return false;
        }
        return element.isDisplayed();
    }

    public void selectMessage(Sem sem) throws Exception {
        waitForTableToLoad();
        List<WebElement> tableRows = portalDriver.getTableRows(messageTableId);
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!(cells.size() < 7)
                    && cells.get(1).getText().equals(sem.getCustomer())
                    && cells.get(2).getText().equals(sem.getProduct())
                    && cells.get(3).getText().equals(sem.getIdentifierType())
                    && cells.get(4).getText().equals(sem.getIdentifier())
                    && cells.get(5).getText().equals(sem.getBatchId())
                    && cells.get(7).getText().equals(sem.getStatus())
            ) {
                portalDriver.clickElement(cells.get(1).getAttribute("id"));
                return;
            }
        }
        String format = "No sem found with details: \nCustomer - %s \nProduct - %s \nIdentifier type - %s \nIdentifier - %s \nStatus - %s";
        String reason = String.format(format, sem.getCustomer(), sem.getProduct(), sem.getIdentifierType(), sem.getIdentifier(), sem.getStatus());
        throw new NoSuchElementException(reason);
    }

    public List<Sem> getSems() throws Exception {
        waitForTableToLoad();
        List<Sem> sems = new ArrayList<>();
        List<WebElement> tableData = portalDriver.getTableRows(messageTableId);
        for (WebElement row : tableData) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            Sem sem = new Sem()
                    .withCustomer(cells.get(1).getText())
                    .withProduct(cells.get(2).getText())
                    .withIdentifierType(cells.get(3).getText())
                    .withIdentifier(cells.get(4).getText())
                    .withBatchId(cells.get(5).getText())
                    .withLastUpdated(cells.get(6).getText())
                    .withStatus(cells.get(7).getText());
            sems.add(sem);
        }
        return sems;
    }

    public void selectDateRangeForLastMonth() throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String endDate = sdf.format(calendar.getTime());
        calendar.add(Calendar.MONTH, -1);
        String startDate = sdf.format(calendar.getTime());
        selectDateRange(startDate + " to " + endDate);
    }

    public String getCustomer() throws Exception {
        return getElement(By.id(customerFilterInputId)).getAttribute("value");
    }

    public String getDateRange() throws Exception {
        return getElement(By.id(dateFilterInputId)).getAttribute("value");
    }

    public String getStatus() throws Exception {
        return getElement(By.id(statusFilterInputId)).getText();
    }

    public int countSems() throws Exception {
        return getCount(semCount);
    }

    public void clickRefreshButton() throws Exception {
        waitForPageToLoad();
        getElement(refreshButton).click();
    }
}
