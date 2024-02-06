package com.sap.cmoplatform.pages.blockchainexplorer;

import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.objects.BlockChainExplorer;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class BlockChainExplorerPage extends Page {
    
    private final Properties blockchainExplorerPageIds = PropertyReader.loadProperties("blockchainexplorer/BlockChainExplorerPageIds.properties");
    private final String prefix = blockchainExplorerPageIds.getProperty("blockchainexplorer.home.pagePrefix");
    private final String newprefix = blockchainExplorerPageIds.getProperty("blockchainexplorer.home.newpagePrefix");
    private final String availableNetworkHeader = prefix + blockchainExplorerPageIds.get("blockchainexplorer.home.availableNetworkHeader");
    private final String recordedLotsHeader = prefix + blockchainExplorerPageIds.get("blockchainexplorer.home.recordedLotsHeader");
    private final By keysCount = By.xpath("//div[contains(text(),'Lot Hash: 65b228f82d7f42b522912dfe73986e12')]/following::div[1]/div");
    private final By recordedEvent = By.xpath("//div[@id='application-cockpit-show-component---key--keyPage-page-contentFitContainer']/div/ul/li/div/div/div/div");
    private final By secondRecordedEvent = By.xpath("(//div[@id='application-cockpit-show-component---key--keyPage-page-contentFitContainer']/div/ul/li/div/div/div/div)[3]");
    private final By recordedEpcs = By.xpath("//span[contains(text(),'EPC Hash: ')]");
    private final By recordedEpcsHeader = By.xpath("//div[@id='application-cockpit-show-component---stream--streamPage-pageTitle-left-inner']/..//span");
    private final By serialNoTextBox = By.xpath("//*[text()='Serial Number:']//following::input");
    private final By searchButton = By.xpath("//*[@title='Search']");
    private final By gtin = By.xpath("//div[contains(@id,'master--Gtin')]/span");
    private final By lot = By.xpath("//div[contains(@id,'master--Batch')]/span");
    private final By expirationDate = By.xpath("//div[contains(@id,'master--ExpDate')]/span");
    private final By piCount = By.xpath("//div[contains(@id,'master--PICount')]/span");
    private final String recordedEventsTableId = prefix + blockchainExplorerPageIds.get("blockchainexplorer.home.recordedEvenTable");
    private final String nodataText = prefix + blockchainExplorerPageIds.get("blockchainexplorer.home.nodataText");
    private final By noDataError=By.xpath("//*[contains(text(),'No data found for given Key')]");

    public String getAvailableNetworkHeader() throws Exception {
        waitForPageToLoad();
        String header = getElement(availableNetworkHeader).getText().split("[\\(\\)]")[0];
        System.out.println(header);
        return header;
    }

    public String getRecordedLotsHeader() throws Exception {
        waitForPageToLoad();
        return getElement(recordedLotsHeader).getText().split("[\\(\\)]")[0];

    }

    public WebElement recordedEpcsHeader(String header) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//span[text()='" + header + "']"));

    }

    public WebElement recordedEventsHeader(String header) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//span[text()='" + header + "']"));

    }

    public void clickOnNetwork(String network) throws Exception {
        waitForPageToLoad();
        getElement(By.xpath("//span[text()='" + network + "']")).click();
    }

    public void clickOnRecordedLot(String lot) throws Exception {
        waitForPageToLoad();
        getElement(By.xpath("//div[text()='" + lot + "']")).click();
    }

    public boolean isRecordedLotDisplayed(String lot) throws Exception {
        waitForPageToLoad();
        Boolean flag = false;
        if (getElement(By.xpath("//div[text()='" + lot + "']")).isDisplayed()) {
            flag = true;
        }
        return flag;
    }

    public String getAvailableNetworkCount() throws Exception {
        waitForPageToLoad();
        String count = getElement(availableNetworkHeader).getText();
        // Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(count);
        String availableNetwork = count.split("[\\(\\)]")[1];
        System.out.println(availableNetwork);
        return availableNetwork;
    }

    public String getKeyCount(String lot) throws Exception {
        waitForPageToLoad();
        portalDriver.scrollToElement(getElement(By.xpath("//div[contains(text(),'" + lot + "')]/following::div[1]/div")));
        String[] count = getElement(By.xpath("//div[contains(text(),'" + lot + "')]/following::div[1]/div")).getText().split(", ");
        String epcCount = count[0].split("Keys: ")[1];
        return epcCount;
    }

    public String[] getEventDetails() throws Exception {
        String[] details = new String[4];
        String[] eventDetails = getElement(recordedEvent).getText().split("\",\"s\":\"");
        details[0] = eventDetails[1].split("\"")[0];
        details[1] = eventDetails[0].split("\",\"d\":\"")[1];
        details[2] = eventDetails[0].split("\",\"d\":\"")[0].split(",\"t\":\"")[1];
        details[3] = eventDetails[0].split("\",\"d\":\"")[0].split(",\"t\":\"")[0].split("\"a\":")[1];
        return details;
    }


    public String[] getSecondEventDetails() throws Exception {
        String[] details = new String[4];
        String[] eventDetails = getElement(secondRecordedEvent).getText().split("\",\"s\":\"");
        details[0] = eventDetails[1].split("\"")[0];
        details[1] = eventDetails[0].split("\",\"d\":\"")[1];
        details[2] = eventDetails[0].split("\",\"d\":\"")[0].split(",\"t\":\"")[1];
        details[3] = eventDetails[0].split("\",\"d\":\"")[0].split(",\"t\":\"")[0].split("\"a\":")[1];
        return details;
    }

    public void clickOnRecordedEpcs() throws Exception {
        waitForPageToLoad();
        getElement(recordedEpcs).click();
    }

    public String getGTINValue() throws Exception {
        waitForPageToLoad();
        portalDriver.waitFor(3000);
        return getElement(gtin).getText();
    }

    public String getLotValue() throws Exception {
        waitForPageToLoad();
        return getElement(lot).getText();
    }

    public String getExpirationDateValue() throws Exception {
        waitForPageToLoad();
        return getElement(expirationDate).getText();

    }

    public String getPICountValue() throws Exception {
        waitForPageToLoad();
        return getElement(piCount).getText();
    }

    public void searchSerialNo(String serialNo) throws Exception {
        waitForPageToLoad();
        inputText(serialNoTextBox, serialNo);
        clickElement(searchButton);

    }

    public List<BlockChainExplorer> getRecordedEvents() throws Exception {
        waitForTableToLoad();
        getElement(recordedEventsTableId);
        waitForTableToLoad();
        List<BlockChainExplorer> recordedEvents = new ArrayList<>();
        List<WebElement> tableData = getTableRows(By.id(recordedEventsTableId));
        int count = tableData.size();
        for (int i = 0; i < count; i++) {
            WebElement row = getTableRows(By.id(recordedEventsTableId)).get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String actionValue=cells.get(2).getText();
            String actionTrim=actionValue.contains("\n")?actionValue.substring(0, actionValue.indexOf('\n')):actionValue;
            BlockChainExplorer recordedEvent = new BlockChainExplorer()
                    .withTimestamp(cells.get(1).getText())
                    .withAction(actionTrim)
                    .withDisposition(cells.get(3).getText())
                    .withGLN(cells.get(4).getText())
                    .withWrittenToBlockchain(cells.get(5).getText());
            recordedEvents.add(recordedEvent);
        }
        return recordedEvents;
    }


    public WebElement getNodataTextId() throws Exception {
        waitForPageToLoad();
        return getElement(nodataText);
    }

    public WebElement getSerialNoId() throws Exception {
        waitForPageToLoad();
        return getElement(serialNoTextBox);
    }
    public WebElement getNoDataError() throws Exception {
        waitForPageToLoad();
        return getElement(noDataError);
    }
}
