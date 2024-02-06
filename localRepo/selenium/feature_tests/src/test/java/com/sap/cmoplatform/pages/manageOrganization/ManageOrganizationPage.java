package com.sap.cmoplatform.pages.manageOrganization;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.objects.Organization;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PortalDriver;
import groovy.util.logging.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;


@Slf4j
public class ManageOrganizationPage extends Page {

    private final By OrgDisplayID = By.xpath("//div[contains(@class,'paginationInfo')]");

    private final By createPNIDInput = By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ObjectPage.view.Details::Organizations--headerEditable::com.sap.vocabularies.UI.v1.HeaderInfo::Description::Field-input-inner']");

    private final By createNameInput = By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ObjectPage.view.Details::Organizations--headerEditable::com.sap.vocabularies.UI.v1.HeaderInfo::Title::Field-input-inner']");
    private By organizationPNIDInput = By.xpath("//input[@placeholder='Value']");

    private By fileNameInput = By.xpath("//input[@id='exportSettingsDialog-fileName-inner']");

    private final String orgTableId ="//div[contains(@class,'position-relative')]";

    protected SeleniumUI5TestUtil portalDriver = PortalDriver.getInstance();

    public void clickDropDown(String type) throws Exception {
        waitForPageToLoad();
        switch (type) {
            case "Type":
                clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReportFilter-filterItemControl_BASIC-type_ID-arrow' and @role='button']"));
                break;
            case "Status":
                clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReportFilter-filterItemControl_BASIC-status_ID-arrow' and @role='button']"));
                break;
            case "Role":
                clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--CustomOrgRoleFilter-combobox-arrow' and @role='button']"));
                break;
            case "OnboardingType":
                clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReportFilter-filterItemControl_BASIC-onboardingType_ID-arrow' and @role='button']"));
                break;
            case "OrganizationPNID":
                clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReportFilter-filterItemControl_BASIC-ID-vhi']"));
                break;
            case "Interface Type":
                clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReportFilter-filterItemControl_BASIC-interfaceType_ID-vhi']"));
                break;
        }
    }
    public void selectDropDownOption(String filterValue) throws Exception {
        clickElement(By.xpath("//*[text()='" + filterValue + "' and @class='sapMSLITitleOnly']"));
    }

    public void clickOnOrganization() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> values = getElements(By.xpath("//*[@class='sapMListTblNavCol']"));
        // click the first one
        values.get(0).click();
    }

    public boolean checkRelationshipTabVisible() throws Exception {
        return getElement(By.xpath("//div[text()='Relationship']")).isDisplayed();
    }


    public void selectCheckBoxAdaptFilter(List<String> columnList) throws Exception {
        waitForPageToLoad();
        for (String column : columnList) {
            WebElement checkbox = getElement(By.xpath("//bdi[text()='" + column + "']/../../../../../..//div[@role='checkbox']"));
            if (checkbox.getAttribute("aria-checked").equals("false")) {
                checkbox.click();
            }
        }

    }

    public boolean checkFilterSelected(List<String> columnList) throws Exception {
        boolean flag = true;
        waitForPageToLoad();
        for (String column : columnList) {
            WebElement checkbox = getElement(By.xpath("//bdi[text()='" + column + "']/../../../../../..//div[@role='checkbox']"));
            if (checkbox.getAttribute("aria-checked").equals("false")) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    public boolean checkFilterOnOverviewPage(String columnName){
        List<WebElement> elements = getElements(By.xpath("//bdi[text()='" + columnName + "']"));
        return elements.size() > 0;
    }

    public int getFilterCount() {
        return getElements(By.xpath("//*[@class='sapUiAFLayoutItem']")).size();
    }

    public void unselectCheckBox(List<String> columnList) throws Exception {
        waitForPageToLoad();
        for (String column: columnList) {
            WebElement checkbox = getElement(By.xpath("//bdi[text()='" + column + "']/../../../../../..//div[@role='checkbox']"));
            if (checkbox.getAttribute("aria-checked").equals("true")) {
                checkbox.click();
            }
        }
    }

    public void clickCollapseArrow() throws Exception {
        waitForPageToLoad();
        clickElement(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--template:::ListReportPage:::DynamicPageHeader-collapseBtn']"));
    }

    public boolean checkFilterBarDisplay() throws Exception {
        WebElement element = getElement(By.xpath("//*[@class='sapContrastPlus sapFDynamicPageHeader sapFDynamicPageHeaderWithContent sapFDynamicPageHeaderPinnable']"));
        return element.getAttribute("style").equals("visibility: hidden;");
    }

    public boolean checkFilterValueDisplay(String value) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> elements = getElements(By.xpath("//span[text()='" + value + "']"));
        return elements.size() > 1;
    }

    public void inputCondition(String value) throws Exception {
        Robot robot = new Robot();
        getElement(organizationPNIDInput).clear();
        inputText(organizationPNIDInput, value);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public boolean checkInputConditionSelected(String value) throws Exception {
        WebElement element = getElement(By.xpath("//*[@class='sapMTokenText']"));
        String text = element.getText();
        String condition = text.substring(1,text.length()-1);

        return condition.equals(value);
    }

    public boolean checkInputConditionDisplay(String value) throws Exception {
        waitForPageToLoad();
        waitForElementToLoad();
        List<WebElement> elements = getElements(By.xpath("//*[contains(text(),'" + value + "')]"));
        return elements.size() > 0;
    }

    public void selectInterfaceType() throws Exception {
        clickElement(By.xpath("//div[@data-sap-ui-rowindex='0']"));

    }

    public boolean checkFilterValueReset() {
        List<WebElement> elements = getElements(By.xpath("//*[text()='No tokens']"));
        // there are 6 filter bar total
        return elements.size() == 6;
    }

    public int getOrgCount() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
//        portalDriver.scrollToBottom(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--responsiveTable-listUl']//tr"));
        return getTableRows(By.id(orgTableId)).size();
    }

    public List<Organization> getOrganizations() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<Organization> organizations = new ArrayList<>();
        portalDriver.scrollToBottom(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--responsiveTable-listUl']//tr"));
        List<WebElement> tableRows = getTableRows(By.id(orgTableId));
        int count = tableRows.size();
        for (int i = 0; i < count; i++) {
            WebElement row = tableRows.get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));

            Organization org = new Organization();
            String orgPNID = cells.get(1).getText() + "(" + cells.get(2).getText() + ")";
           /* org.setOrganizationPNID(orgPNID);
            org.setType(cells.get(2).getText());
            org.setRole(cells.get(3).getText());
            org.setOnboardingType(cells.get(4).getText());
            org.setInterfaceType(cells.get(5).getText());
            org.setChangedOn(cells.get(6).getText());
            org.setCreatedOn(cells.get(7).getText());
            org.setStatus(cells.get(8).getText());*/

            organizations.add(org);
        }
        return organizations;
    }

    private void waitForElementToLoad() throws Exception {
        portalDriver.waitFor(2000);
        portalDriver.waitForLoad();
    }

    public int getOrgDisplayCount() throws Exception {
        waitForTableToLoad();

        WebElement tableTitle = getElement(OrgDisplayID);
        String regex = "\\((\\d+)\\)";
        int orgCount;
        Pattern pattern = Pattern.compile(regex);
        waitForElementToLoad();
        String tableTitleString = tableTitle.getText().replaceAll(",", "");
        Matcher matcher = pattern.matcher(tableTitleString);
        if (matcher.find()) {
            orgCount = Integer.parseInt(matcher.group(1));
        } else {
            throw new Exception("Org count not loaded");
        }
        return orgCount;
    }

    public void clickOnDownloadButton() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        clickElement("com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReport-btnExcelExport-internalSplitBtn-textButton-img");
    }

    public void clickOnDownloadArrow() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        clickElement("com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReport-btnExcelExport-internalSplitBtn-arrowButton-img");
    }

    public void selectCheckBoxExportAs(List<String> columnList) throws Exception {
        waitForPageToLoad();
        for (String column : columnList) {
            WebElement checkbox = getElement(By.xpath("//bdi[text()='" + column + "']/../../..//input[@type='CheckBox']/.."));
            /*if (checkbox.getAttribute("aria-checked").equals("false")) {
                checkbox.click();
            }*/
            checkbox.click();
        }
    }

    public void checkDownloadFile() throws Exception {
        List<Organization> result = getDownloadFileContent("Organizations.xlsx");
        checkOrganizations(result);
    }

    public void checkOrganizations(List<Organization> orgList) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        portalDriver.scrollToBottom(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--responsiveTable-listUl']//tr"));
        List<WebElement> tableRows = getTableRows(By.id(orgTableId));
        int count = tableRows.size();
        assertEquals(count,orgList.size());
        for (int i = 0; i < count; i++) {
            WebElement row = tableRows.get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));

            Organization org = orgList.get(i);
            //assertTrue(cells.get(1).getText().contains(org.getSubPNID()));
           /* assertTrue(cells.get(2).getText().contains(org.getType()));
            assertTrue(cells.get(3).getText().contains(org.getRole()));
            assertTrue(cells.get(4).getText().contains(org.getOnboardingType()));
            assertTrue(cells.get(5).getText().contains(org.getInterfaceType()));
            assertTrue(cells.get(6).getText().contains(org.getChangedOn()));
            assertTrue(cells.get(7).getText().contains(org.getCreatedOn()));
            assertTrue(cells.get(8).getText().contains(org.getStatus()));*/
        }
    }

    private List<Organization> getDownloadFileContent(String downloadFileName) throws Exception {
        List<Organization> list = new ArrayList<>();
        File f = null;
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        //Look for the file in the files
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.equals(downloadFileName)) {
                    f = new File(filePath + fileName);
                    list = readExcel(f);
                }
            }
        }
        f.delete();
        return list;
    }

    public static List<Organization> readExcel(File file) throws Exception {
        List<Organization> result = new ArrayList<>();
        List<String> rowResult = new ArrayList<>();
        FileInputStream stream = null;
        XSSFWorkbook workbook = null;
        try {
            stream = new FileInputStream(file);

            workbook = new XSSFWorkbook(stream);

            Sheet sheet = workbook.getSheetAt(0);

            int rowNum = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(0);
            int cellNum = row.getLastCellNum();

            for (int i = 1; i < rowNum; i++) {
                row = sheet.getRow(i);
                Organization organization = new Organization();
                for (int j = 0; j < cellNum; j++) {
                    String value = row.getCell(j).toString();
                    rowResult.add(value);
                }
                /*organization.setOrganizationPNID(rowResult.get(0));
                organization.setType(rowResult.get(1));
                organization.setRole(rowResult.get(2));
                organization.setOnboardingType(rowResult.get(3));
                organization.setInterfaceType(rowResult.get(4));
                organization.setChangedOn(rowResult.get(5));
                organization.setCreatedOn(rowResult.get(6));
                organization.setStatus(rowResult.get(7));*/
                result.add(organization);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void inputFileNameExportAs(String filename) throws Exception {
        getElement(fileNameInput).clear();
        inputText(organizationPNIDInput, filename);
    }

    public void enterEmptyFileName() throws Exception {
        getElement(fileNameInput).clear();
    }

    public void searchInAdaptFilters(String searchName) throws Exception {
        By searchInputId = By.xpath("//input[@aria-label='Search for Filters']");
        getElement(searchInputId).clear();
        inputText(searchInputId, searchName);
    }

    public void searchInSearchField(String content) throws Exception {
        String searchFieldId = "com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--listReportFilter-btnBasicSearch-I";
        getElement(searchFieldId).clear();
        inputText(searchFieldId,content);
    }

    public boolean listPageDisplayed() throws Exception {
        return getElement("com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--page-content")
                .isDisplayed();
    }

    public boolean checkListViewPresent() {
        WebElement element;
        try {
            element = getElement(By.xpath("//li[text()='TestView']"));
        } catch (Exception e) {
            return false;
        }
        return element.isDisplayed();
    }

    public boolean checkListViewPresent(String viewName) {
        WebElement element;
        try {
            element = getElement(By.xpath("//li[text()='" + viewName + "']"));
        } catch (Exception e) {
            return false;
        }
        return element.isDisplayed();
    }

    public boolean variantViewDisplayed() throws Exception {
        return getElement(By.xpath("//span[text()='TestView']")).isDisplayed();
    }

    public boolean checkViewDisplayed(String name) throws Exception {
        return getElement(By.xpath("//span[text()='" + name + "']")).isDisplayed();
    }

    public void makeViewDefault(String viewName) throws Exception {
        WebElement checkbox = getElement(By.xpath("//input[@value='" + viewName + "']/../../../..//*[@role='radio']"));
        if (checkbox.getAttribute("aria-checked").equals("false")) {
            checkbox.click();
        }

    }

    public Boolean isColumnSortedInAscendingOrder(String[] columnValue) throws Exception {
        boolean flag = false;
        int size = columnValue.length;
        if (size == 0) {
            return true;
        }
        String[] actual = new String[size];
        String[] sorted = new String[size];

        for (int i = 0; i < size; i++) {
            actual[i] = sorted[i] = columnValue[i];
        }
        Arrays.sort(sorted, Comparator.nullsLast(new AscendingComparator()));

        for (int i = 0; i < size; i++) {
            if (actual[i].equals(sorted[i])) {
                flag = true;
            } else {
                return false;
            }
        }
        return flag;
    }

    public Boolean isColumnSortedInDescendingOrder(String[] columnValue) {
        boolean flag = false;
        int size = columnValue.length;
        if (size == 0) {
            return true;
        }
        String[] actual = new String[size];
        String[] sorted = new String[size];
        for (int i = 0; i < size; i++) {
            actual[i] = sorted[i] = columnValue[i].toLowerCase();
        }
        //Sorting the array
        Arrays.sort(sorted, new CustomComparator());

        //  Arrays.sort(sorted);
        for (int i = 0; i < size; i++) {
            if (actual[i].equals(sorted[i])) {
                flag = true;
            } else {
                return false;
            }
        }
        return flag;
    }

    public String[] getColumnValues(String columnName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        portalDriver.scrollToBottom(By.xpath("//*[@id='com.ich.portal.common.ui.manage.org::sap.suite.ui.generic.template.ListReport.view.ListReport::Organizations--responsiveTable-listUl']//tr"));
        List<WebElement> tableRows = getTableRows(By.id(orgTableId));
        int count = tableRows.size();
        int index = 0;
        switch (columnName) {
            case "Type":
                index = 2;
                break;
            case "Organization":
                index = 1;
                break;
            case "Onboarding Type":
                index = 4;
                break;
            case "Status":
                index = 8;
        }
        String[] columnValues = new String[count];
        for (int i = 0; i < count; i++) {
            WebElement row = tableRows.get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String value = cells.get(index).getText();
            if (index==1) {
                String[] lines = value.split("\\n");
                value = lines[0];
            }
            columnValues[i] = value;
        }
        return columnValues;
    }

    public void inputValueWithText(String fieldName, String value) throws Exception {
        switch (fieldName) {
            case "Organization PNID":
                getElement(createPNIDInput).clear();
                inputText(createPNIDInput, value);
                break;
            case "Name":
                getElement(createNameInput).clear();
                inputText(createNameInput, value);
                break;
        }
    }

    public void clickCreateDropDown(String field) throws Exception {
        waitForPageToLoad();
        clickElement(By.xpath("//bdi[text()='" + field + "']/../../../..//div[@class='sapUiFormCLCellsS12 sapUiFormCLCellsL12']//span[@role='button']"));
    }

    public void selectCreateInterfaceType(String value) throws Exception {
        // Default index = 0 is SOAP
        String index = "0";
        switch (value) {
            case "HTTP":
                index = "1";
                break;
            case "AS2":
                index = "2";
                break;
            case "SFTP":
                index = "3";
                break;
        }
        clickElement(By.xpath("//div[@data-sap-ui-rowindex='" + index + "']"));
    }

    public boolean verifyOrganizationExist(String name) throws Exception {
        searchInSearchField(name);
        clickButton("Go");
        List<WebElement> elements = getElements(By.xpath("//*[text()='" + name + "']"));

        return elements.size() > 0;

    }

    public void inputRandomPNID(int len) throws Exception {
        getElement(createPNIDInput).clear();
        inputText(createPNIDInput, generateRandomString(len));
    }

    public static String generateRandomString(int length) {
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.length());
            char randomChar = validChars.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static String generateRandomOrgName(int length) {
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.length());
            char randomChar = validChars.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public void inputRandomOrgName(int length) throws Exception {
        getElement(createNameInput).clear();
        inputText(createNameInput, generateRandomOrgName(length));
    }

    class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            int minLength = Math.min(str1.length(), str2.length());

            for (int i = 0; i < minLength; i++) {
                char ch1 = str1.charAt(i);
                char ch2 = str2.charAt(i);
                if (isUnderscore(ch1) && Character.isDigit(ch2)) {
                    return  ch1-ch2;
                }
                else if (ch1 != ch2) {
                    return ch2 - ch1;
                }
            }
            return str1.length() - str2.length();
        }


        private boolean isUnderscore(char ch) {
            boolean flag = false;

            if (Character.valueOf(ch).equals('_')) {
                flag = true;
            }
            return flag;
        }
    }

    class AscendingComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            String st1 = str1.toLowerCase();
            String st2 = str2.toLowerCase();
            int minLength = Math.min(str1.length(), str2.length());

            for (int i = 0; i < minLength; i++) {
                char c1 = st1.charAt(i);
                char c2 = st2.charAt(i);

                if (c1 != c2) {
                    if (c1 == '_' && Character.isDigit(c2)) {
                        return -1;
                    } else if (Character.isDigit(c1) && c2 == '_') {
                        return 1;
                    } else {
                        return Character.compare(c1, c2);
                    }
                }
            }
            return Integer.compare(str1.length(), str2.length());
        }
    }
}

