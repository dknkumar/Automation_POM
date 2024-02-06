package com.sap.cmoplatform.pages.configurealertresolution;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigureAlertResolutionPage extends Page {
    private final Properties configureAlertResolutionPageIds = PropertyReader.loadProperties("configurealertresolution/ConfigureAlertResolutionPageIds.properties");
    private final String prefix = configureAlertResolutionPageIds.getProperty("configurealertresolution.detail.pagePrefix");
    private final String homePrefix = configureAlertResolutionPageIds.getProperty("configurealertresolution.home.pagePrefix");
    private final String issuesPageTitle = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.issuesPageTitle");
    private final String resolutionsPageTitle = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.resolutionsPageTitle");
    private final String issuePageHeader = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.issuePageHeader");
    private final String resolutionPageHeader = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.resolutionPageHeader");
   // private By addIssueButton = By.xpath("//button[@title='Add Issue']/span/span");
    private final By addIssueText = By.xpath("//*[text()='Add Issue']");
    private final By addDescriptionInput = By.xpath("//*[@class='sapMInputBaseInner']/..//input");
    private final String issueListTable = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.issueListTable");
    private final String issueListHeader = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.issueListHeader");
    private final String deleteIssueImg = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.deleteIssueImg");
    private final By sortByHeader = By.xpath("//*[text()='Sort']");
    private final String sortIssueImg = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.sortIssueImg");
    private final By addIssueErrorMessage=By.xpath("//input[@class='sapMInputBaseInner']/..//following::span");
    private final String resolutionsImg = homePrefix + configureAlertResolutionPageIds.get("configurealertresolution.home.resolutionsImg");
    private final String resolutionListHeader = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.resolutionListHeader");
    private final String addIssueButton =prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.addIssueImg");
    private final String addResolutionButton =  prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.addResolutionImg");
    private final By addResolutionText = By.xpath("//*[text()='Add Resolution']");
    private final String resolutionsListTable = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.resolutionsListTable");
    private final String deleteResolutionImg = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.deleteResolutionImg");
    private final By deleteConfirmationText = By.xpath("//*[@class='sapMDialogScrollCont']/span");
    private final String sortResolutionImg = prefix + configureAlertResolutionPageIds.get("configurealertresolution.detail.sortResolutionImg");
    private final String issuesImg = homePrefix + configureAlertResolutionPageIds.get("configurealertresolution.home.issuesImg");
private final By subHeader=By.xpath("//*[text()='Configure Alert Resolutions']/following::span");

WebDriverWait wait = new WebDriverWait(SeleniumUI5TestUtil.getConfig().getDriver(), 60);
    public String getIssuesPageTitle() throws Exception {
        waitForPageToLoad();
        return getElement(issuesPageTitle).getText();
    }

    public String getResolutionsPageTitle() throws Exception {
        waitForPageToLoad();
        return getElement(resolutionsPageTitle).getText();
    }

    public String getIssuePageHeader() throws Exception {
        waitForPageToLoad();
        return getElement(issuePageHeader).getText();
    }

    public String getResolutionPageHeader() throws Exception {
        waitForPageToLoad();
        return getElement(resolutionPageHeader).getText();
    }

    public void clickAddIssueButton() throws Exception {
        waitForPageToLoad();
        getElement(addIssueButton).click();
    }
    public void clickAddResolutionButton() throws Exception {
        waitForPageToLoad();
        getElement(addResolutionButton).click();
    }
    public String getAddIssueText() throws Exception {
        waitForPageToLoad();
        return getElement(addIssueText).getText();
    }
    public String getAddResolutionText() throws Exception {
        waitForPageToLoad();
        return getElement(addResolutionText).getText();
    }
    public WebElement getAddInput() throws Exception {
        waitForPageToLoad();
        return getElement(addDescriptionInput);
    }

    public List<String> getIssuesFromList() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<String> issues = new ArrayList<>();
        List<WebElement> tableRows = portalDriver.getTableRows(By.id(issueListTable));
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.xpath("td/span"));
            for (WebElement text : cells) {
                issues.add(text.getText().toLowerCase());
            }
        }
        return issues;
    }

    public String getIssueListHeader() throws Exception {
        waitForPageToLoad();
        Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(SeleniumUI5TestUtil.getConfig().getDriver(), 50);
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        return getElement(issueListHeader).getText();
    }

    public String getResolutionListHeader() throws Exception {
        waitForPageToLoad();
        return getElement(resolutionListHeader).getText();
    }

    public void selectTheIssue(String issue) throws Exception {
        waitForPageToLoad();
        portalDriver.findElement(By.xpath("//*[text()='" + issue + "']/preceding::input[1]/parent::node()")).click();
    }

    public void verifyTheIssue(String issue) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        int count = portalDriver.findElements(By.xpath("//*[contains(@class,'sapMListTblCell')]/span")).size();
        System.out.println(count);
        boolean flag = false;
        for (int i = 1; i <= count; i++) {
            String xpathID = "(//*[contains(@class,'sapMListTblCell')]/span)[" + i + "]";
            String name = portalDriver.findElement(By.xpath(xpathID)).getText();
            System.out.println(i+" name: "+ name);
            if (name.equals(issue)) {
                portalDriver.findElement(By.xpath("//*[text()='" + issue + "']/preceding::input[1]/parent::node()")).click();
                try {
                    clickDeleteIssueButton();
                } catch (Exception e) {
                    clickDeleteResolutionButton();
                }
                Thread.sleep(3000);
                portalDriver.waitForLoad();
                portalDriver.clickButtonByName("OK");
                flag = true;
                break;
            }
        }
    }

    public void clickDeleteIssueButton() throws Exception {
        waitForPageToLoad();
         getElement(deleteIssueImg).click();
    }
    public void clickDeleteResolutionButton() throws Exception {
        waitForPageToLoad();
        getElement(deleteResolutionImg).click();
    }
    public void clickSortIssueButton() throws Exception {
        waitForPageToLoad();
        getElement(sortIssueImg).click();
    }

    public WebElement getSortPopUpHeader() throws Exception {
        waitForPageToLoad();
        return getElement(sortByHeader);
    }
    public String getAddIssueInputErrorMessage() throws Exception {
        waitForPageToLoad();
        return getElement(addIssueErrorMessage).getText();
    }

    public void clickResolutionsImg() throws Exception {
        waitForPageToLoad();
         getElement(resolutionsImg).click();
    }
    public void clickIssuesImg() throws Exception {
        waitForPageToLoad();
        getElement(issuesImg).click();
    }
    public List<String> getResolutionsFromList() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<String> resolutions = new ArrayList<>();
        List<WebElement> tableRows = portalDriver.getTableRows(By.id(resolutionsListTable));
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.xpath("td/span"));
            for (WebElement text : cells) {
                resolutions.add(text.getText());
            }
        }
        return resolutions;
    }

    public String getDeleteConfirmationText() throws Exception {
        waitForPageToLoad();
        return getElement(deleteConfirmationText).getText();
    }

    public void clickSortResolutionImg() throws Exception {
        waitForPageToLoad();
        getElement(sortResolutionImg).click();
    }

    public String getAppSubHeader() throws Exception {
        waitForPageToLoad();
        return getElement(subHeader).getText();
    }
}
