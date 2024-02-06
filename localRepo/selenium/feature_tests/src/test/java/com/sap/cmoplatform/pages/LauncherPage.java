package com.sap.cmoplatform.pages;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.SeleniumUtilException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LauncherPage extends Page {

    private static final String NO_APP_FOUND = "No App Found";
    private static final String TILE_NUMBER = "*[class='sapMNCValueScr']";
    private final By viewSortSetting = By.xpath("//button[@title='View Sort Settings']/span/span");
    private final By viewGroupSetting = By.xpath("//button[@title='View Group Settings']/span/span");
    private final By btnDownalodPdf = By.xpath("//button[@title='Download PDF']/span/span");
    private final By btnDownalodIcon = By.xpath("//button[@title='Download']/span/span");
    private final By homebutton = By.xpath("//span[text()='Home']");
    private final By backButton = By.xpath("//a[@id='backBtn']/span");
    private final By alertdateinput = By.xpath("//input[contains(@id,'inputAlertDateFilter-inner')]");
    private final By navigationMenuTitle = By.xpath("//*[@id='shellAppTitle-button']");
    private final By exportDocumentBusyIndicator = By.xpath("//h2/span[text()='Export Document']");
    private final String verificationLogFrameId="application-analytics-show";
    private final String configRuleFrameId="application-config-show";
    private final String configAlertRuleFrameId= "application-USSCAlertservices-show";
    private final String configAlertResolutionFrameId= "application-configAlertManagement-show";
    private final String verificationFrameId=  "application-verification-show";
    private final String writeLogFrameId= "application-analyticsWrite-show";
    private final String ldProductFrameId=  "application-ldproduct-show";
    private final String ManageAlertFrameId=  "application-AlertManagement-show";
    private final String semAppFrameId=  "application-uisem-Display";
    private final String verifyProductLogFrameId="application-BIPoCverifylog-show";
    private final String transactionInformationFrameId="application-dispenserportal-display";
    private final String manageOrganizationRelationshipsFrameId = "application-orgrelationship-manage";
    private final String manageOrganizationsFrameId = "application-organization-manage";
    private final String manageCustomizationFrameId = "application-customization-manage";
    private final String manageMessageFrameId="application-Message-manage";
    private final String manageOrganizationId = "application-organization-manage";
    private final String manageCerFrameId = "application-certificate_automation-Display";
    private final String manageUserId = "application-users-manage";

    public void clickFioriApp(String appName) throws Exception {
        waitForPageToLoad();
        portalDriver.clickFioriApp(appName);
    }

    public boolean isAppAvailable(String appName) throws Exception {
        waitForPageToLoad();
        return !portalDriver.getAppId(appName).equals(NO_APP_FOUND);
    }

    public int getNumberFromTile(String appName) throws Exception {
        int interval = portalDriver.getConfig().getMediumInterval();
        int maxWait = portalDriver.getConfig().getTimeoutPeriod();
        int valFromTile;
        Thread.sleep(portalDriver.getConfig().getLongInterval());
        for (int i = 0; i < maxWait; i += interval) {
            try {
                String appId = portalDriver.getAppId(appName);
                WebElement app = portalDriver.findElement(By.id(appId));
                String text = app.findElement(By.cssSelector(TILE_NUMBER)).getText();
                valFromTile = parseValueWithK(text);
                return valFromTile;
            } catch (Exception e) {
                if (i + interval < maxWait) {
                    Thread.sleep(interval);
                } else {
                    throw new SeleniumUtilException(e.toString());
                }
            }
        }
        return -1;
    }

    private int parseValueWithK(String input) {
        int output = 0;
        int thousands = 0;
        int units = 0;
        if (input.contains(".")) {
            thousands = 1000 * Integer.parseInt(input.substring(0, input.indexOf(".")));
            units = 10 * Integer.parseInt(input.substring(input.indexOf(".") + 1, input.length()));
            output = thousands + units;
        } else {
            output = Integer.parseInt(input);
        }
        return output;
    }

    public void clickUsscApp(String appName) throws Exception {
        waitForPageToLoad();
        portalDriver.clickUsscApp(appName);
    }

    public void clickViewSortSetting() throws Exception {
        waitForPageToLoad();
        clickElement(viewSortSetting);
    }
    public void clickViewGroupSetting() throws Exception {
        waitForPageToLoad();
        clickElement(viewGroupSetting);
    }
    public void clickselectdate(String pdate,String cdate) throws Exception {
       getElement(alertdateinput).sendKeys(pdate+" - "+cdate);
    }


    public void selectFilterComponent(int index) {
        List<WebElement> checkboxes = getElements(By.xpath("//div[@title='Show on Filter Bar']/div"));
        checkboxes.get(index).click();
    }

    public void clickBtnDownloadPdf() throws Exception {
        waitForPageToLoad();
        clickElement(btnDownalodPdf);
        waitForPageToLoad();
    }

    public void clickBtnDownloadIcon() throws Exception {
        waitForPageToLoad();
        clickElement(btnDownalodIcon);
        waitForPageToLoad();
    }

    public void clickOnHomeButton() throws Exception {
        waitForPageToLoad();
        clickShowNavigationMenu();
        getElement(homebutton).click();
    }

    public void clickBackButton() throws Exception {
        waitForPageToLoad();
        getElement(backButton).click();
    }

    public String getNavigationMenuTitle() throws Exception {
        waitForPageToLoad();
        return getElement(navigationMenuTitle).getText();
    }

    public By getBusyIndicatorExportDocument() throws Exception {
        waitForPageToLoad();
        return exportDocumentBusyIndicator;
    }

    public WebElement getNavigationMenuElement() throws Exception {
        waitForPageToLoad();
        return getElement(navigationMenuTitle);
    }

    public void switchToFrame(String app) throws Exception {
        waitForPageToLoad();
        try {
            if (app.equals("ManageCertificates")) {
                portalDriver.switchToFrame(manageCerFrameId);
            }
            if (app.equals("VerificationLog")) {
                portalDriver.switchToFrame(verificationLogFrameId);
                //portalDriver.switchToFrame(writeLogFrameId);
            }
            if (app.equals("ConfigureVerificationResponses")) {
                portalDriver.switchToFrame(configRuleFrameId);
                //portalDriver.switchToFrame(writeLogFrameId);
            }
            if (app.equals("AlertConfiguration")) {
                portalDriver.switchToFrame(configAlertRuleFrameId);
            }
            if (app.equals("ConfigureAlertResolutions")) {
                portalDriver.switchToFrame(configAlertResolutionFrameId);
            }
            if (app.equals("SEM")) {
                portalDriver.switchToFrame(semAppFrameId);
            }
            if (app.equals("UploadProductData")) {
                portalDriver.switchToFrame(ldProductFrameId);
            }
            if (app.equals("UploadProductPackData")) {
                portalDriver.switchToFrame(ldProductFrameId);
            }
            if (app.equals("ManageAlert")) {
                portalDriver.switchToFrame(ManageAlertFrameId);
            }
            if (app.equals("WriteLog")) {
                portalDriver.switchToFrame(writeLogFrameId);
            }
            if (app.equals("BiPocVerifyProductPack")) {
                portalDriver.switchToFrame(verifyProductLogFrameId);
                //portalDriver.switchToFrame(writeLogFrameId);
            }
            if (app.equals("TransactionInformation")) {
                portalDriver.switchToFrame(transactionInformationFrameId);
            }
            if (app.equals("VerifyProductPack")) {
                portalDriver.switchToFrame(verificationFrameId);
                //portalDriver.switchToFrame(writeLogFrameId);
            }
           if (app.equals("ManageMessages")) {
               portalDriver.switchToFrame(manageMessageFrameId);
           }
           if (app.equals("ManageCustomizations")){
               portalDriver.switchToFrame(manageCustomizationFrameId);
           }
            if (app.equals("ManageOrganizationRelationships")) {
                portalDriver.switchToFrame(manageOrganizationRelationshipsFrameId);
            }
            if(app.equals("ManageOrganizations")){
                portalDriver.switchToFrame(manageOrganizationsFrameId);
            }
            if (app.equals("ManageUsers")) {
                portalDriver.switchToFrame(manageUserId);
            }
        } catch (NoSuchFrameException e) {
            System.out.println("catching the frame");
            portalDriver.switchToGenericFrame();
        }
    }

    public void switchToDefaultContent() throws Exception {
        waitForPageToLoad();
        portalDriver.switchToDefaultContent();
    }
}