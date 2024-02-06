package com.sap.cmoplatform.pages.manageCustomization;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.objects.TransactionInformation;
import com.sap.cmoplatform.pages.LauncherPage;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import scala.Int;
import scala.util.parsing.combinator.testing.Str;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManageCustomizingPage extends Page {
    private static Properties configManageCustomization = PropertyReader.loadProperties("managecustomization/managecustomization.properties");
    public By appTitlePath = By.xpath("//span[contains(@class,'AppTitleText')]");
    private final String titleInner = "application-customization-manage-component---ManageCustomization--manageCustomizationPageTitle-inner";
    private Integer numberOfCustomization = 0;
    private final By customizationTablePath = By.xpath("//table[contains(@id,'manageCustomizationTable')]");
    private final By addButton = By.xpath("//span[contains(@id,'AddBtn')]");
    private final By editButton = By.xpath("//span[contains(@id,'EditBtn')]");
    private final By deleteButton = By.xpath("//span[contains(@id,'DeleteBtn')]");
    private final By refreshButton = By.xpath("//span[contains(@id,'refresh')]");
    private LauncherPage launcherPage = new LauncherPage();

    public String getAppTitleName() throws Exception {
        portalDriver.switchToDefaultContent();
        String appName = portalDriver.findElement(appTitlePath).getText();
        launcherPage.switchToFrame("ManageCustomizations");
        return appName;
    }


    public static void waitForPageToLoad() throws Exception {
        Page.waitForPageToLoad();
    }

    public void waitForElementToLoad() throws Exception {
        portalDriver.waitFor(2000);
        portalDriver.waitForLoad();
    }

    public boolean isCustomizationAddOne() throws Exception {
        String title = getElement(titleInner).getText();
        String titleNumber = title.substring(title.indexOf(("(")) + 1, title.indexOf((")")));
        if (numberOfCustomization == 0) {
            return true;
        } else {
            return Integer.parseInt(titleNumber) == numberOfCustomization + 1;
        }
    }

    public void getTitleNumber() throws Exception {
        waitForPageToLoad();
        waitForElementToLoad();
        String title = getElement(titleInner).getText();
        String titleNumber = title.substring(title.indexOf(("(")) + 1, title.indexOf((")")));
        numberOfCustomization = Integer.parseInt(titleNumber);
    }

    public boolean checkPageWell() throws Exception {
        return getElement(customizationTablePath).isDisplayed() && getElement(addButton).isDisplayed() && getElement(editButton).isDisplayed() && getElement(deleteButton).isDisplayed() && getElement(refreshButton).isDisplayed();
    }

    public void selectDropDown(String type, String value) throws Exception {
        waitForPageToLoad();
        clickElement(By.xpath("//td[contains(@headers,'" + type.replace(" ", "") + "')]//span[@aria-label = 'Select Options']"));
        waitForPageToLoad();
        selectDropDownFilterInput(getElement(By.xpath("//td[contains(@headers,'" + type.replace(" ", "") + "')]//input[contains(@id,'manageCustomizationTable')]")), value.trim());
//        inputText(By.xpath("//td[contains(@headers,'"+type.replace(" ","")+"')]//input[contains(@id,'manageCustomizationTable')]"), value.trim());
    }

    public void modifyDropDown(String type, String value) throws Exception {
//        waitForPageToLoad();
        clickElement(By.xpath("//td[contains(@headers,'" + type.replace(" ", "") + "')]//span[@aria-label = 'Select Options']"));
//        selectDropDownFilterInput(getElement(By.xpath("//td[contains(@headers,'"+type.replace(" ","")+"')]//input[contains(@id,'manageCustomizationTable')]")), value.trim());
        waitForElementToLoad();
        inputText(By.xpath("//td[contains(@headers,'" + type.replace(" ", "") + "')]//input[contains(@id,'manageCustomizationTable')]"), " " + value.trim());
    }


    public boolean isRecordExist(String scenario, String parameterKey, String parameterOption, String parameterValue) throws Exception {
        waitForTableToLoad();
        List<WebElement> tableRows = getTableRows(customizationTablePath);
        for (int i = 0; i < tableRows.size(); i++) {
            String columnText = tableRows.get(i).getText();
            if (tableRows.get(i).getText().contains(scenario) &&
                    columnText.contains(parameterKey) &&
                    columnText.contains(parameterOption) &&
                    columnText.contains(parameterValue)) {
//                getElements(By.xpath("//div[@role = 'checkbox']")).get(i).click();
//                clickButton("Delete");
//                clickButton("OK");
                return true;
            }

        }

        return false;
    }

    public void inputValue(String type, String value) throws Exception {
        waitForPageToLoad();
        inputText(By.xpath("//td[contains(@headers,'" + type.replace(" ", "") + "')]//input[contains(@id,'manageCustomizationTable')]"), value.trim());

    }

    public boolean checkRecordFromPayload(String scenario, String parameterKey, String parameterOption, String parameterValue) {
        String accessToken = getAccessToken(configManageCustomization.getProperty("oauth.clientId"), configManageCustomization.getProperty("oauth.clientSecret"), configManageCustomization.getProperty("oauth.TokenUrl"));
        String url = configManageCustomization.getProperty("MCURL");
        Response resp = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .header("Accept", "*/*")
                .header("Connection", "keep-alive")
                .get(url + "?$filter=Pid eq 'PNTESTREGCOLLABORG2305'").then().extract().response();
        assertEquals(200, resp.getStatusCode());
        XmlPath xmlPath = new XmlPath(resp.body().asPrettyString());
        List<String> entryValues = xmlPath.getList("feed.entry.content.properties.Value");
        List<String> entryId = xmlPath.getList("feed.entry.content.properties.Id");
        if (null == entryValues) {
            return false;
        }
        if (null == entryId) {
            return false;
        }
        String preId = "";
        switch (parameterKey) {
            case "CNPJ Determination":
                preId = "CNPJ_";
                break;
        }
        String midID = "";
        switch (parameterOption) {
            case "Declarant for Activation":
                midID = "DECLARANT_ACTIVATION";
                break;
            case "MbrAgt for Activation":
                midID = "MBRAGT_ACTIVATION";
                break;
            case "Receiver for Activation":
                midID = "RECEIVER_ACTIVATION";
                break;
            case "Sender for Activation":
                midID = "SENDER_ACTIVATION";
                break;
        }

        for (int i = 0; i < entryValues.size(); i++) {
            if (parameterValue.equals(entryValues.get(i))
                    && entryId.get(i).contains(preId + midID)
            ) {
                return true;
            }
        }

        return false;
    }

    public String getAccessToken(String clientId, String clientSecret, String tokenUrl) {

        final Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");
        final String auth = clientId + ":" + clientSecret;
        final String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
        String accessToken = "";
        String content = "grant_type=client_credentials";
        BufferedReader reader = null;
        HttpsURLConnection connection = null;
        try {
            URL url = new URL(tokenUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + authentication);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            PrintStream os = new PrintStream(connection.getOutputStream());
            os.print(content);
            os.close();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            StringWriter out = new StringWriter(
                    connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            String response = out.toString();
            JSONObject obj = new JSONObject(response);
            accessToken = obj.getString("access_token");
            Matcher matcher = pat.matcher(response);
            if (matcher.matches() && matcher.groupCount() > 0) {
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            connection.disconnect();
        }
        return accessToken;
    }


    public void backToHomePage() throws Exception {
        portalDriver.switchToDefaultContent();
        launcherPage.clickBackButton();
    }

    public boolean isUnsavedWindowPopUp() {
        if (portalDriver.getAlert().getText().equals("This page contains unsaved data")) {
            portalDriver.getAlert().accept();
            return true;
        } else {
            return false;
        }

    }

    public void selectRecord(String scenario, String key, String option) throws Exception {
        waitForPageToLoad();
        List<WebElement> views = getTableRows(By.id("application-customization-manage-component---ManageCustomization--manageCustomizationTable-listUl"));
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).getText().contains(scenario) && views.get(i).getText().contains(key) && views.get(i).getText().contains(option)) {
                clickElement(views.get(i).findElements(By.xpath("//div[@role ='checkbox']")).get(i));
                waitForPageToLoad();
                break;
            }
        }
    }

    public void selectSecondDropDown(String type, String value) throws Exception {
        clickElement(By.xpath("//td[contains(@headers,'" + type.replace(" ", "") + "')]//span[@role = 'button']"));
        clickElement(By.xpath("//td[contains(@headers,'" + type.replace(" ", "") + "')]//span[contains(@data-sap-ui,'1-arrow')]"));
        waitForElementToLoad();
        inputText(By.xpath("//td[contains(@headers,'" + type.replace(" ", "") + "')]//input[contains(@id,'manageCustomizationTable-1-inner')]"), " " + value.trim());

    }

    public boolean isOrderSortBy(String sortOrder, String sortBy) throws Exception {
        waitForPageToLoad();
        List<WebElement> views = getTableRows(By.id("application-customization-manage-component---ManageCustomization--manageCustomizationTable-listUl"));
        int listNum = views.size();
//        String expectSort = sortOrder;
        String colume = "";
        switch (sortBy) {
            case "Scenario":
                colume = "text3";
                break;
        }
        String tempScenario = "";
        String tempScenarioNext = "";
        for (int i = 0; i < listNum; i++) {
            tempScenarioNext = getElement(By.xpath("//span[contains(@id,'" + colume + "-application-customization-manage-component---ManageCustomization--manageCustomizationTable-" + i + "')]")).getText();

            if (sortOrder.equals("Ascending")) {
                if (!tempScenario.equals("") && tempScenario.compareTo(tempScenarioNext) > 0) {
                    return false;
                }
            } else {
                if (!tempScenario.equals("") && tempScenario.compareTo(tempScenarioNext) < 0) {
                    return false;
                }
            }
            tempScenario = tempScenarioNext;
        }
        return true;

    }

    private boolean compareRecentTime(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy, hh:mm:ss a");
        try {
            Date date = dateFormat.parse(dateString);
            long timestamp = date.getTime();
            long currentTime = System.currentTimeMillis();
            long ThreeMinutesInMillis = 90 * 1000;
            long timeDiff = Math.abs(currentTime - timestamp);
            if (timeDiff <= ThreeMinutesInMillis) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return true;
        }
    }

    public boolean changeLogCheck(String value) throws Exception {
        boolean onlyDisplay = true;
        List<WebElement> views = getTableRows(By.id("application-customization-manage-component---ManageCustomization--changeLogDialogTable-listUl"));
        for (int i = 0; i < views.size(); i++) {
            if (!views.get(i).getText().contains(value)) {
                onlyDisplay = false;
            }
        }
        return onlyDisplay;
    }


    public boolean isOrderFilterBy(String by, String value) throws Exception {
        waitForTableToLoad();
        List<WebElement> views = getTableRows(By.id("application-customization-manage-component---ManageCustomization--manageCustomizationTable-listUl"));
        int colume = 0;
        switch (by) {
            case "Scenario":
                colume = 2;
                break;
            case "Parameter Key":
                colume = 3;
                break;
            case "Parameter Option":
                colume = 4;
                break;
        }
        for (WebElement view : views) {
            if (!view.findElements(By.tagName("td")).get(colume).getText().equals(value)
            ) {
                return false;
            }
        }
        return true;
    }

    public boolean isOrderFilterBy(String scenario, String key, String option) throws Exception {
        waitForTableToLoad();
        List<WebElement> views = getTableRows(By.id("application-customization-manage-component---ManageCustomization--manageCustomizationTable-listUl"));
        for (WebElement view : views) {
            if (!view.findElements(By.tagName("td")).get(2).getText().equals(scenario) ||
                    !view.findElements(By.tagName("td")).get(3).getText().equals(key) ||
                    !view.findElements(By.tagName("td")).get(4).getText().equals(option)
            ) {
                return false;
            }
        }
        return true;
    }

    public void clearTheData() throws Exception {
        waitForTableToLoad();
        waitForElementToLoad();
        waitForTableToLoad();
        List<WebElement> views = getElements(By.xpath("//*[@id='application-customization-manage-component---ManageCustomization--manageCustomizationTable-listUl']//tbody//tr//td[@class='sapMListTblSelCol']/div/div"));
        for (WebElement view : views) {
            if (views.isEmpty()) {
              return;
           }
            if (views.size() == 1) {
                if (view.isSelected())
                    view.click();
                else
                    return;
            }
            if (views.size() > 1) {
                view.click();
            }
        }
        waitForPageToLoad();
        clickButton("Delete");
        clickButton("OK");
        waitForTableToLoad();
    }

    public boolean checkValueChanged(String action,String scenario, String key, String option, String value) throws Exception {
        waitForTableToLoad();
        List<WebElement> views = getTableRows(By.id("application-customization-manage-component---ManageCustomization--changeLogDialogTable-listUl"));
        for (WebElement view : views) {
            if (view.findElements(By.tagName("td")).get(1).getText().equals(action) &&
//                    view.findElements(By.tagName("td")).get(2).getText().equals(scenario) &&
                    view.findElements(By.tagName("td")).get(3).getText().contains(scenario) &&
                    view.findElements(By.tagName("td")).get(3).getText().contains(option) &&
                    view.findElements(By.tagName("td")).get(3).getText().contains(key) &&
//                    view.findElements(By.tagName("td")).get(4).getText().equals(option) &&
                    view.findElements(By.tagName("td")).get(5).getText().equals(value) &&
                    compareRecentTime(view.findElements(By.tagName("td")).get(7).getText())
            ) {


                return true;
            }

        }
        return false;
    }

    public boolean isRecordExistWithTime(String scenario, String parameterKey, String parameterOption, String parameterValue) throws Exception {
        waitForTableToLoad();
        List<WebElement> tableRows = getTableRows(customizationTablePath);
        for (int i = 0; i < tableRows.size(); i++) {
            String columnText = tableRows.get(i).getText();
            if (tableRows.get(i).getText().contains(scenario) &&
                    columnText.contains(parameterKey) &&
                    columnText.contains(parameterOption) &&
                    columnText.contains(parameterValue)  &&
            compareRecentTime(columnText.split("\n")[4]))
            {
//                getElements(By.xpath("//div[@role = 'checkbox']")).get(i).click();
//                clickButton("Delete");
//                clickButton("OK");
                return true;
            }

        }

        return false;
    }

    public void modifyDescription(String value) throws Exception {
        waitForElementToLoad();
        getElement(By.xpath("//td[contains(@headers,'Description')]//input[contains(@id,'manageCustomizationTable')]")).clear();
        waitForElementToLoad();
        inputText(By.xpath("//td[contains(@headers,'Description')]//input[contains(@id,'manageCustomizationTable')]"), " " + value.trim());


    }

}
