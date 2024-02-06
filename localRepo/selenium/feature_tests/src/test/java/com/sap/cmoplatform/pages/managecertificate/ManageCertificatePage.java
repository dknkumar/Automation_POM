package com.sap.cmoplatform.pages.managecertificate;

import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PropertyReader;
import org.json4s.DefaultWriters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.Properties;

import com.sap.cmoplatform.pages.LauncherPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManageCertificatePage extends Page {
    private static final LauncherPage launcherPage = new LauncherPage();
    public static final By lineItemTLS =
            By.xpath(
                    "//td[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//span[contains(text(), 'QA_TLS')]");
    public static final By lineItemMLS =
            By.xpath(
                    "//td[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//span[contains(text(), 'QA_MLS')]");
    public static final By MLSCertDownloadButton =
            By.xpath(".//button[@title='Download Certificate']");
    public static final By cerStatusPath =
            By.xpath(
                    ".//td[@headers = 'application-certificate_automation-Display-component---certificatesList--typeCol']");
    public static final By CertPathInDetailPage =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificateDetails--idUploadsTable') and @class = 'sapMLIB sapMLIB-CTX sapMLIBShowSeparator sapMLIBTypeInactive sapMLIBFocusable sapMListTblRow']");
    public static final By certStatusPath =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//td[@headers = 'application-certificate_automation-Display-component---certificatesList--statusCol']");
    public static final By certDownloadButtonInDetailPage = By.xpath(".//button[@title = 'Download Certificate']");
    public static final By certificateDetailColumns =
            By.xpath(
                    "//div[contains(@id, 'application-certificate_automation-Display-component---certificateDetails')]//span[contains(@class,'sapMTextMaxWidth sapMColumnHeaderContent')]");
    public static final By searchFieldPath =
            By.xpath(
                    "//input[@id='application-certificate_automation-Display-component---certificatesList--companySearchFilter-I']");
    public static final By searchButtonPath =
            By.xpath(
                    "//div[@id='application-certificate_automation-Display-component---certificatesList--companySearchFilter-search']");
    public static final By certNamePath =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//td[@headers = 'application-certificate_automation-Display-component---certificatesList--nameCol']");
    public static final By certAliasNamePath =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//td[@headers = 'application-certificate_automation-Display-component---certificatesList--aliasNameCol']");
    public static final By certSerialNumberPath =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//td[@headers = 'application-certificate_automation-Display-component---certificatesList--serialNumCol']");
    public static final By certEnvironmentPath =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//td[@headers = 'application-certificate_automation-Display-component---certificatesList--environmentCol']");
    public static final By certTypePath =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//td[@headers = 'application-certificate_automation-Display-component---certificatesList--typeCol']");
    public static final By groupButtonPath = By.xpath("//button[@title='Group']");
    public static final By OrderListItemPath =
            By.xpath(
                    "//li[contains(@class,'sapMLIB sapMLIB-CTX sapMLIBShowSeparator sapMLIBTypeInactive sapMLIBActionable sapMLIBHoverable')]");
    public static final By ByListItemPath =
            By.xpath(
                    "//li[contains(@class,'sapMLIB sapMLIB-CTX sapMLIBShowSeparator sapMLIBTypeActive sapMLIBActionable')]");
    public static final By sortButtonPath = By.xpath("//button[@title = 'Sort']");
    public static final By certExpiryDatePath =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//td[@headers = 'application-certificate_automation-Display-component---certificatesList--expireOnCol']");
    public static final By certLastModifiedPath =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]//td[@headers = 'application-certificate_automation-Display-component---certificatesList--changedCol']");
    public static final By exportToExcelButtonPath = By.xpath("//button[@title = 'Export to Excel']");
    public static final By CertPath =
            By.xpath(
                    "//tr[contains(@id, 'application-certificate_automation-Display-component---certificatesList--columnListItem')]");
    public static final By certDownloadButton = By.xpath(".//button[@title = 'Download Certificate']");
    public static final By groupPath =
            By.xpath(
                    "//tr[@class= 'sapMLIB sapMLIB-CTX sapMLIBShowSeparator sapMLIBTypeInactive sapMLIBFocusable sapMGHLI']");
    By personalizationButtonPath = By.xpath("//button[@title = 'Personalization']");
    By selectAllButton = By.xpath("//div[@id = 'application-certificate_automation-Display-component---certificatesList--CertificatesTable-PersoDialog-colTable-sa']");
    By personalizationColumnsPath = By.xpath("//tr[contains(@id,'application-certificate_automation-Display-component---certificatesList--CertificatesTable-PersoDialog-cli-application-certificate_automation-Display-component---certificatesList--CertificatesTable-PersoDialog-colTable')]");
    By certColumnsPath = By.xpath(
            "//div[contains(@id,'application-certificate_automation-Display-component---certificatesList') and @class='sapMColumnHeader']");
    By userListPath = By.xpath("//td[@headers='application-users-manage-component---Master--userTableUserCol']");
    By appListOfUserPath = By.xpath("//tr[contains(@id,'application-users-manage-component---CreateUser--userApplicationsTable') and@class='sapMLIB sapMLIB-CTX sapMLIBShowSeparator sapMLIBTypeInactive sapMLIBFocusable sapMListTblRow']");
    By appListInHomePage = By.xpath("//div[@class='sapUiView sapUiXMLView sapUiViewDisplayBlock']");
    By certListPath = By.xpath("//tr[contains(@id,'application-certificate_automation-Display-component---certificateDetails--idUploadsTable')]");
    By chageLogListPath = By.xpath("//tr[@class='sapMLIB sapMLIB-CTX sapMLIBShowSeparator sapMLIBTypeInactive sapMLIBFocusable sapMListTblRow']");
    By appsListPath = By.xpath("//tr[@class='sapMLIB sapMLIB-CTX sapMLIBShowSeparator sapMLIBTypeInactive sapMLIBActionable sapMLIBHoverable sapMLIBFocusable sapMListTblRow']");
    public static Properties configManageCertificate = PropertyReader.loadProperties("manageCertificate/managecertificate.properties");
    public String activatedMLSCert;

    public void clickTLSCert() throws Exception {
        getElement(lineItemTLS).click();
    }


    public void uploadNewCert() throws AWTException {
        // 使用 Robot 类进行模拟按键操作
        Robot robot = new Robot();
        StringSelection filePath =
                new StringSelection(
                        System.getProperty("user.dir")
                                + "\\src\\test\\resources\\testfiles\\certficates\\TLS\\Test_TLS_03.cer"); // 文件的完整路径
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.delay(2000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void clickUpload() throws Exception {
        checkIfCertNumberEqualMaxAndDeleteOne();
        clickButton("Upload");
    }

    public void deleteItemByIndex(int index) throws Exception {
        List<WebElement> radios = getElements(By.xpath("//*[@role = 'radio']"));
        radios.get(index).click();
        clickButton("Delete");
        clickButton("OK");
        portalDriver.waitFor(3000);
    }

    public void selectTheNewCert() {
        int itemIndex = this.getItemIndex();
        List<WebElement> radios = getElements(By.xpath("//*[@role = 'radio']"));
        radios.get(itemIndex).click();
    }

    public void clickOnDownloadButtonOfTheMLSCertificate() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> rows = getElements(CertPath);
        for (WebElement row : rows) {
            if (row.findElement(cerStatusPath).getText().equals("MLS")) {
                row.findElement(certDownloadButton).click();
                break;
            }
        }
        portalDriver.waitFor(5000);
    }

    public void checkIfCertNumberEqualMaxAndDeleteOne() throws Exception {
        List<WebElement> certs = getCertName();
        List<WebElement> statusList = getStatus();
        if (certs.size() == 5) {
            for (int i = 0; i < statusList.size(); i++) {
                if (Objects.equals(statusList.get(i).getText(), "Inactive") || statusList.get(i).getText().equals("New")) {
                    deleteItemByIndex(i);
                    break;
                }
            }
        }
    }

    public void uploadNewCert(String uploadCert) throws AWTException, InterruptedException {
        // 使用 Robot 类进行模拟按键操作
        Robot robot = new Robot();
        StringSelection filePath;
        if (uploadCert.startsWith("Test_MLS")) {
            filePath = new StringSelection(System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\certficates\\MLS\\" + uploadCert); // 文件的完整路径
        } else {
            filePath = new StringSelection(System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\certficates\\TLS\\" + uploadCert); // 文件的完整路径
        }

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
    }

    public void waitForElementToLoad() throws Exception {
        portalDriver.waitFor(2000);
        portalDriver.waitForLoad();
    }

    public void clickOnDownloadButtonOfTheCertificate() throws Exception {
        List<WebElement> rows = getElements(CertPathInDetailPage);
        for (WebElement row : rows) {
            if (row.findElements(By.xpath(".//span[contains(@class,'sapMObjStatus')]"))
                    .get(1)
                    .getText()
                    .equals("Active")) {
                row.findElement(certDownloadButtonInDetailPage).click();
                break;
            }
        }
        portalDriver.waitFor(5000);
    }

    public String getErrorMessageInMainPage() throws Exception {
        portalDriver.waitFor(1000);
        return portalDriver
                .findElement(By.xpath("//section[@class='sapMDialogSection sapUiScrollDelegate']"))
                .getText();
    }

    public void uploadCertWithName(String environment, String fileName) throws AWTException, InterruptedException {
        // 使用 Robot 类进行模拟按键操作
        Robot robot = new Robot();
        StringSelection filePath =
                new StringSelection(
                        System.getProperty("user.dir")
                                + "\\src\\test\\resources\\testfiles\\certficates\\"
                                + environment
                                + "\\"
                                + fileName); // 文件的完整路径
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.delay(2000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        portalDriver.waitFor(5000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public List<String> getCertificateDetailColumns() {
        List<String> columns =
                getElements(certificateDetailColumns).stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
        columns.remove(9);
        return columns;
    }

    public void searchForInSearchField(String info) throws Exception {
        inputText(searchFieldPath, info);
        getElement(searchButtonPath).click();
        waitForTableToLoad();
    }

    public String getFieldName(String field) throws Exception {
        By fieldPath = null;
        switch (field) {
            case "Name":
                fieldPath = certNamePath;
                break;
            case "Type":
                fieldPath = certTypePath;
                break;
            case "Environment":
                fieldPath = certEnvironmentPath;
                break;
            case "Serial Number":
                fieldPath = certSerialNumberPath;
                break;
            case "Alias Name":
                fieldPath = certAliasNamePath;
                break;
            case "Status":
                fieldPath = certStatusPath;
                break;
        }
        return getElements(fieldPath).get(0).getText().split("\n")[0];
    }

    public void clickOnGroupButtonInMainPage() throws Exception {
        getElement(groupButtonPath).click();
    }

    public void selectOrderInOrderSection(String groupOrder) {
        getElements(OrderListItemPath).stream()
                .filter(e -> e.getText().equals(groupOrder))
                .findFirst()
                .get()
                .click();
    }

    public int getItemIndex() {
        List<WebElement> items =
                getElements(
                        By.xpath(
                                "//td[@headers=\"application-certificate_automation-Display-component---certificateDetails--certificateFileCol\"]//span"));
        System.out.println(items.size());
        for (int i = 0; i < items.size(); i++) {
            WebElement item = items.get(i);
            if (item.getText().equals("Test_TLS_03.cer")) {
                return i; // 返回匹配元素的索引
            }
        }
        return -1; // 如果找不到匹配的元素，返回 -1 表示未找到
    }

    public int getItemIndex(String certName) {
        List<WebElement> items = getElements(By.xpath("//td[@headers=\"application-certificate_automation-Display-component---certificateDetails--certificateFileCol\"]//span"));
        for (int i = 0; i < items.size(); i++) {
            WebElement item = items.get(i);
            if (item.getText().equals("Test_TLS_03.cer")) {
                return i; // 返回匹配元素的索引
            }
        }
        return -1; // 如果找不到匹配的元素，返回 -1 表示未找到
    }

    public void selectByInBySection(String groupBy) {
        getElements(ByListItemPath).stream()
                .filter(e -> e.getText().equals(groupBy))
                .findFirst()
                .get()
                .click();
    }

    public boolean resultShouldBeDisplayedWithGroupOrderOrderOfGroupByGroupBy(String groupOrder, String groupBy) {
        List<String> result =
                getElements(groupPath).stream().map(WebElement::getText).collect(Collectors.toList());
        if (groupOrder.equals("Ascending")) {
            for (int i = 0; i < result.size() - 1; i++) {
                if (result.get(i).compareTo(result.get(i + 1)) > 0) {
                    return false;
                }
            }
            return true;
        } else if (groupOrder.equals("Descending")) {
            for (int i = 0; i < result.size() - 1; i++) {
                if (result.get(i).compareTo(result.get(i + 1)) < 0) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public void clickOnSortButtonInMainPage() throws Exception {
        getElement(sortButtonPath).click();
    }

    public boolean resultShouldBeDisplayedWithSortOrderOrderOfSortBySortBy(String sortOrder, String sortBy) throws Exception {
        waitForTableToLoad();
        By fieldPath = null;
        switch (sortBy) {
            case "Name":
                fieldPath = certNamePath;
                break;
            case "Status":
                fieldPath = certStatusPath;
                break;
            case "Alias Name":
                fieldPath = certAliasNamePath;
                break;
            case "Expiry Date":
                fieldPath = certExpiryDatePath;
                break;
            case "Last Modified":
                fieldPath = certLastModifiedPath;
                break;
        }
        List<String> result =
                getElements(fieldPath).stream().map(WebElement::getText).collect(Collectors.toList());
        if (sortOrder.equals("Ascending")) {
            for (int i = 0; i < result.size() - 1; i++) {
                if (result.get(i).compareTo(result.get(i + 1)) > 0) {
                    return false;
                }
            }
            return true;
        } else if (sortOrder.equals("Descending")) {
            for (int i = 0; i < result.size() - 1; i++) {
                if (result.get(i).compareTo(result.get(i + 1)) < 0) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public void clickOnExportToExcelButtonInMainPage() throws Exception {
        getElement(exportToExcelButtonPath).click();
        portalDriver.waitFor(5000);
    }

    public boolean certNameShouldBeDownloadedWithCorrectValue(String fileName) throws Exception {
        String[] fields = {
                "Name",
                "Type",
                "Environment",
                "Serial Number",
                "Alias Name",
                "Status",
                "Expiry Date",
                "Last Modified"
        };
        for (String field : fields) {
            List<String> actualValue = getFieldList(field);
            List<String>[] result = getCSVDownloadFileContent(fileName);
            List<String> csvValue = result[getFieldIndex(field)];
            return checkListValue(csvValue, actualValue);
        }
        return true;
    }

    private int getFieldIndex(String field) {
        switch (field) {
            case "Name":
                return 0;
            case "Type":
                return 1;
            case "Environment":
                return 2;
            case "Serial Number":
                return 3;
            case "Alias Name":
                return 4;
            case "Status":
                return 5;
            case "Expiry Date":
                return 6;
            case "Last Modified":
                return 7;
        }
        return 0;
    }

    public boolean checkListValue(List<String> csvValue, List<String> actualValue) throws Exception {
        boolean flag = true;
        if (csvValue.size() != actualValue.size()) flag = false;
        for (int i = 0; i < csvValue.size(); i++) {
            if (!csvValue.get(i).equals(actualValue.get(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static List[] readCSV(File file) throws Exception {
        List<String> name = new ArrayList<>();
        List<String> type = new ArrayList<>();
        List<String> environment = new ArrayList<>();
        List<String> serialNumber = new ArrayList<>();
        List<String> aliasName = new ArrayList<>();
        List<String> status = new ArrayList<>();
        List<String> expiryDate = new ArrayList<>();
        List<String> lastModified = new ArrayList<>();
        List[] result = new List[0];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            if (reader.readLine() != null) {
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    name.add(data[0]);
                    type.add(data[1]);
                    environment.add(data[2]);
                    serialNumber.add(data[3]);
                    aliasName.add(data[4]);
                    status.add(data[5]);
                    expiryDate.add(data[7]);
                    lastModified.add(data[8]);
                }
            }
            result =
                    new List[]{
                            name, type, environment, serialNumber, aliasName, status, expiryDate, lastModified
                    };
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<String>[] getCSVDownloadFileContent(String downloadFileName) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //    Document document = builder.newDocument();
        File f = null;
        String filePath =
                System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        List<String>[] res = new List[0];
        // Look for the file in the files
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.equals(downloadFileName)) {
                    f = new File(filePath + fileName);
                    res = readCSV(f);
                    break;
                }
            }
        }
        f.delete();
        return res;
    }

    public void selectTheNewTLSCert() throws Exception {
        int itemIndex = this.getItemIndex("Test_TLS_03.cer");
        List<WebElement> status = getStatus();
        if (itemIndex == -1) {
            clickUpload();
            uploadNewCert("Test_TLS_03.cer");
            portalDriver.waitFor(4000);
            itemIndex = this.getItemIndex("Test_TLS_03.cer");
        }
        if (!Objects.equals(status.get(itemIndex).getText(), "New")) {
            itemIndex = this.getItemIndex("Test_TLS_04.cer");
        }
        List<WebElement> radios = getElements(By.xpath("//*[@role = 'radio']"));
        radios.get(itemIndex).click();
    }


    public List<WebElement> getStatus() {
        return getElements(By.xpath("//td[@headers=\"application-certificate_automation-Display-component---certificateDetails--statusCol\"]//span[contains(@id, 'text')]"));
    }

    public List<WebElement> getSerialNumbers() {
        List<WebElement> serialNumbers = getElements(By.xpath("//tbody//*[@data-sap-ui-column=\"application-certificate_automation-Display-component---certificateDetails--serialNoCol\"]/span"));
        return serialNumbers;
    }

    public List<WebElement> getCertName() {
        List<WebElement> certNames = getElements(By.xpath("//*[@headers=\"application-certificate_automation-Display-component---certificateDetails--certificateFileCol\"]/span"));
        return certNames;
    }

    public List<WebElement> getExpireDate() {
        List<WebElement> dates = getElements(By.xpath("//*[@headers=\"application-certificate_automation-Display-component---certificateDetails--expireOnCol\"]/span"));
        return dates;
    }

    public void clickMLSCert() throws Exception {
        getElement(lineItemMLS).click();
    }

    public void selectTheNewMLSCert() throws Exception {
        portalDriver.waitFor(5000);
        int itemIndex = this.getItemIndex("Test_MLS_01.cer");
        List<WebElement> status = getStatus();
        if (itemIndex == -1) {
            clickUpload();
            uploadNewCert("Test_MLS_01.cer");
            itemIndex = this.getItemIndex("Test_MLS_01.cer");
        }
        if (Objects.equals(status.get(itemIndex).getText(), "Inactive")) {
            deleteItemByIndex(itemIndex);
            clickUpload();
            uploadNewCert("Test_MLS_01.cer");
            itemIndex = this.getItemIndex("Test_MLS_01.cer");
        }
        if (!Objects.equals(status.get(itemIndex).getText(), "New")) { // If cer1 is active
            itemIndex = this.getItemIndex("Test_MLS_02.cer");
            if (itemIndex != -1 && Objects.equals(status.get(itemIndex).getText(), "Inactive")) { // If cert2 is inactive
                deleteItemByIndex(itemIndex);
                clickUpload();
                uploadNewCert("Test_MLS_02.cer");
            }
            if (itemIndex == -1) {
                clickUpload();
                uploadNewCert("Test_MLS_02.cer"); // if cert2 is not existing
            }
            itemIndex = this.getItemIndex("Test_MLS_02.cer");
        }
        List<WebElement> certNames = getCertName();
        activatedMLSCert = certNames.get(itemIndex).getText();
        List<WebElement> radios = getElements(By.xpath("//*[@role = 'radio']"));
        radios.get(itemIndex).click();
    }

    public List<String> getFieldList(String field) {
        By fieldPath = null;
        switch (field) {
            case "Name":
                fieldPath = certNamePath;
                break;
            case "Type":
                fieldPath = certTypePath;
                break;
            case "Environment":
                fieldPath = certEnvironmentPath;
                break;
            case "Serial Number":
                fieldPath = certSerialNumberPath;
                break;
            case "Alias Name":
                fieldPath = certAliasNamePath;
                break;
            case "Status":
                fieldPath = certStatusPath;
                break;
            case "Expiry Date":
                fieldPath = certExpiryDatePath;
                break;
            case "Last Modified":
                fieldPath = certLastModifiedPath;
                break;
        }
        return getElements(fieldPath).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickOnPersonalizationButton() throws Exception {
        waitForTableToLoad();
        getElement(personalizationButtonPath).click();
        waitForElementToLoad();
    }

    public void selectColumnsFromThePersonalisation(List<String> columns) throws Exception {
        getElement(selectAllButton).click();
        getElement(selectAllButton).click();
        waitForElementToLoad();
        List<WebElement> personalizationColumns = getElements(personalizationColumnsPath).stream().filter(e -> columns.contains(e.getText().replace("\u00AD", ""))).map(e -> e.findElement(By.xpath(".//td[@class='sapMListTblSelCol']"))).collect(Collectors.toList());
        for (WebElement personalizationColumn : personalizationColumns) {
            personalizationColumn.click();
            portalDriver.waitFor(1000);
        }
    }

    public boolean fileShouldGetDownloaded(String suffix) {
        return checkDownloadFileExist(suffix);
    }

    private boolean checkDownloadFileExist(String suffix) {
        File f = null;
        boolean flag = false;
        String filePath =
                System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        String fileExtension = null;
        // Look for the file in the files
        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                int lastDotIndex = fileName.lastIndexOf('.');
                if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
                    fileExtension = fileName.substring(lastDotIndex);
                }
                if (fileExtension.equals(suffix)) {
                    flag = true;
                    listOfFile.delete();
                }
            }
        }
        return flag;
    }

    public boolean columnsShouldBeDisplayedOnMainPage(List<String> columns) throws InterruptedException {
        portalDriver.waitFor(3000);
        List<String> certColumns = getElements(certColumnsPath).stream().map(f -> f.getText().replace("\u00AD", "")).collect(Collectors.toList());
        return certColumns.containsAll(columns);
    }

    public void clickOnTestCertUser(String userName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        getElements(userListPath).stream().filter(e -> e.getText().equals(userName)).findFirst().get().click();
        waitForPageToLoad();
        waitForTableToLoad();
    }

    public void removeApplicationForUser(String appName) throws Exception {
        waitForTableToLoad();
        scrollToElement((WebElement) appListOfUserPath);
        WebElement app = getElements(appListOfUserPath).stream().filter(e -> e.getText().split("\n")[0].equals(appName)).findFirst().get();
        app.findElement(By.xpath(".//button[@title='Remove Application']")).click();
    }

    public boolean appVisible(String appName) {
        portalDriver.switchToDefaultContent();
        List<String> appList = getElements(appListInHomePage).stream().map(WebElement::getText).collect(Collectors.toList());
        return appList.contains(appName);
    }

    public void activateCertificate(String certName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> certList = getElements(certListPath);
        certList.remove(0);//remove title
        certList.stream().filter(e -> e.findElement(By.xpath(".//td[@headers='application-certificate_automation-Display-component---certificateDetails--certificateFileCol']")).getText().equals(certName)).findFirst().get().findElement(By.xpath(".//div[@class = 'sapMRbB sapMRbHoverable']")).click();
        clickButton("Activate");
        clickButton("OK");
        waitForTableToLoad();
    }

    public void navigateToMainPage() throws Exception {
        launcherPage.switchToDefaultContent();
        launcherPage.clickBackButton();
        waitForPageToLoad();
    }

    public boolean changelogShouldDisplayWithColumnsOf(List<String> changelogResult) throws InterruptedException {
        portalDriver.waitFor(3000);
        List<String> changeLogItems = getElements(chageLogListPath).get(0).findElements(By.xpath(".//td[@class='sapMListTblCell']//span")).stream().map(WebElement::getText).collect(Collectors.toList());
        changeLogItems.remove(5);//remove "When" item
        return changeLogItems.equals(changelogResult);
    }

    public void addApplicationForUser(String appName) throws Exception {
        clickButton("Add");
        portalDriver.waitFor(1000);
        WebElement app = getElements(appsListPath).stream().filter(e -> e.getText().equals(appName)).findFirst().get();
        app.findElement(By.xpath(".//div[contains(@id,'selectMulti') and @role='checkbox']")).click();
        clickButton("Add");
        portalDriver.waitFor(1000);
    }
}
