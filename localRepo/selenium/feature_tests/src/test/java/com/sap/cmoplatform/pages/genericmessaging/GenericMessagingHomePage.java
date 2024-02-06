package com.sap.cmoplatform.pages.genericmessaging;


import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.sap.cmoplatform.objects.GenericMessaging;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import com.sap.cmoplatform.pages.Page;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class GenericMessagingHomePage extends Page {

    private final Properties genericMessagingIds = PropertyReader.loadProperties("gm/genericmessaging.properties");
    private final String prefix = genericMessagingIds.getProperty("gm.homePage.pagePrefix");
    private final String uri = genericMessagingIds.getProperty("uri");
    private final String goButton = prefix + genericMessagingIds.getProperty("gm.homePage.goButton");
    private final String clearFilterButton = prefix + genericMessagingIds.getProperty("gm.homePage.clearFilterButton");
    private final String showHideFilterButton = prefix + genericMessagingIds.getProperty("gm.homePage.showHideFilterButton");
    private final String dateFilterInputId = prefix + genericMessagingIds.getProperty("gm.homePage.dateFilterInputId");
    private final String messagesTableId = prefix + genericMessagingIds.getProperty("gm.homePage.messagesTableId");
    private final String receivedIcon = genericMessagingIds.getProperty("gm.homePage.receivedIcon");
    private final String companyInputId = prefix + genericMessagingIds.getProperty("gm.homePage.companyInputId");


    public String getURI() {
        return uri;
    }

    public void clickGoButton() throws Exception {
        clickElement(goButton);
    }

    public void clickClearButton() throws Exception {
        clickElement(clearFilterButton);
    }

    public void clickHideFiltersButton() throws Exception {
        clickElement(showHideFilterButton);
    }

    public void selectReceivedDateRange(String date) throws Exception {
        inputText(dateFilterInputId, date);
    }

    public void clickFirstDownloadButton() throws Exception {
        portalDriver.findElement(By.xpath("//table/tbody/tr[1]/td[4]/button")).click();
    }

    public String getMessageReceived() throws Exception {
        return getElement(receivedIcon).getText();
    }

    public void selectReceivedFromCompany(String companyName) throws Exception {
        selectFragmentListFilterInput(companyInputId, companyName);
    }

    public void clickOnGobutton() throws Exception {
        clickElement(goButton);
    }


    public GenericMessaging getGenericMessaging(String companyName) throws Exception {
        waitForPageToLoad();
        List<GenericMessaging> gms = getTableData();
        for (GenericMessaging gm : gms) {
            if (gm.getCompanyName().equals(companyName)) {
                return gm;
            }
        }
        throw new ElementNotFoundException("Generic Messaging", "Received From", companyName);
    }


    public List<GenericMessaging> getTableData() throws Exception {
        waitForTableToLoad();
        List<GenericMessaging> gms = new ArrayList<>();
        List<WebElement> tableData = getTableRows(By.id(messagesTableId));
        for (WebElement row : tableData) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            GenericMessaging gm = new GenericMessaging()
                    .withCompanyName(cells.get(1).getText())
                    .withReceivedDate(cells.get(2).getText());

            gms.add(gm);
        }
        return gms;
    }


    public boolean doesCompamnyExist(String expectedCompanyName) throws Exception {
        waitForPageToLoad();
        for (GenericMessaging gm : getTableData()) {
            if (gm.getCompanyName().equals(expectedCompanyName)) {
                return true;
            }
        }
        return false;
    }


    public GenericMessaging getGenericMessagingDate(String date) throws Exception {
        waitForPageToLoad();
        List<GenericMessaging> gms = getTableData();
        for (GenericMessaging gm : gms) {


            if (gm.getReceivedDate().equals(date)) {
                return gm;
            }

        }


        throw new ElementNotFoundException("Generic Messaging", "Received date", date);
    }


    /*public List<GenericMessaging> getTableData() throws Exception {
        waitForTableToLoad();
        List<GenericMessaging> gms = new ArrayList<>();
        List<WebElement> tableData = getTableRows(By.id(messagesTableId));
        for (WebElement row : tableData) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            GenericMessaging gm = new GenericMessaging()
                    .withReceivedDate(cells.get(2).getText());

            gms.add(gm);
        }
        return gms;
    }
*/

}
