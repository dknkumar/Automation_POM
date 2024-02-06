package com.sap.cmoplatform.teststeps.managealerts;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.objects.VerificationLog;
import com.sap.cmoplatform.pages.CommonComponentPage;
import com.sap.cmoplatform.pages.LauncherPage;
import com.sap.cmoplatform.pages.managealerts.ManageAlertsPage;
import com.sap.cmoplatform.utils.PortalDriver;
import com.sap.cmoplatform.utils.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ManageAlertsTestSteps {
    String executionDuration, executionDurationRange, executionDurationRangeMin, executionDurationRangeMax;

    private static final String REQUESTDATE = "RequestTime";
    private static final String GTIN = "GTIN";
    private static final String LOT = "LOT";
    private static final String SERIALNUM = "SerialNumber";
    private static final String ADDITIONALINFO = "AdditionalInfo";
    private static final String REQUESTRORGLN = "RequestorGln";
    private static final String RESPONDERGLN = "ResponderGln";
    private static final String VERIFIED = "Verified";
    private static final String UUID = "UUID";
    private static final String CONTEXT = "Context";
    private static final String LINKTYPE = "Linktype";
    private static final String EXECUTIONDURATION = "ExecutionDuration";
    private static final String REQUESTERCOMPANY = "RequesterCompany";
    private static final String RESPONDERCOMPANY = "ResponderCompany";
    private static final String STATUS = "Status";

    private static final String HTTPSTATUS = "HTTPStatus";
    private static final String RESPONSECODE = "HTTPResponseCode";
    private static final String RESPONSECODEDESCRIPTION = "ResponseCodeDescription";
    private String gtin;
    private String lot;
    private String reqTime;
    private String expDate;
    private String serialNumber;
    private String verified;
    private String requestorGLN;
    private String responderGLN;
    private String additionalInfo;
    private String uuid;
    private String linkType;
    private String context;
    private String alertRuleName;
    private String alertDate;
    private String issue;
    private String resolution;
    private String changeHistory;
    private String verificationFailureReason;
    private String responderEmail;
    private String responderTelephone;
    private String controlAttestation;
    private String requestorEmail;
    private String requestorTelephone;
    private String httpstatus;
    private String responseCode;
    private String responseCodeDescription;
    private String status;


    private ManageAlertsPage manageAlertsPage = new ManageAlertsPage();
    private LauncherPage launcherPage = new LauncherPage();
    private CommonComponentPage commonComponentPage = new CommonComponentPage();

    @And("the filters button is clicked in the manage alerts log page")
    public void theFiltersButtonIsClickedInTheManageAlertsLogPage() throws Exception {
        manageAlertsPage.clickBtnFilters();
    }

    private final Properties apps = PropertyReader.loadProperties("Apps.properties");

    @And("^the (Summary|DetailedView|ERV) app is clicked in the launcher page$")
    public void clickApp(String app) throws Exception {
        String appName = apps.getProperty(app + ".appName");
        System.out.println(" APP Name : " + appName);
        launcherPage.clickFioriApp(appName);
        launcherPage.switchToFrame(app);
    }

    @And("^the (Summary|DetailedView|ERV) tab is opened$")
    public void theTabIsOpened(String tabName) throws Exception {
        //manageAlertsPage.switchToFrame(manageAlertsPage.iFrame);
        //Assert.assertEquals("Manage Users",manageAlertsPage.getPageTitle());
        if(tabName.equals("Detailed View")) {
            manageAlertsPage.navigateToERVPage();
        } else {
            manageAlertsPage.navigateToDetailedViewPage();
        }
        Thread.sleep(5000);
    }


    @And("^the (BusinessView|Provisions) tab is opened on DetailedView page$")
    public void theTabIsOpenedonDetailedViewpage(String tabName) throws Exception {
        //manageAlertsPage.switchToFrame(manageAlertsPage.iFrame);
        //Assert.assertEquals("Manage Users",manageAlertsPage.getPageTitle());
        if(tabName.equals("Business View")) {
            manageAlertsPage.navigateToBusinessViewPage();
        } else {
            manageAlertsPage.navigateToProvisionsPage();
        }
        Thread.sleep(4000);
    }

    @Then("Verify all the filters are present")
    public void Verifyallthefiltersarepresent(List<String> columnList)
            throws Exception {
        for (String columnName : columnList) {
            assertTrue(manageAlertsPage.checkColumnDisplay1(columnName));
        }
    }

    @And("the HomeTabs button is clicked in the Indonesia page")
    public void theHomeTabsbuttonisclickedintheIndonesiaPage() throws Exception {
        manageAlertsPage.clickBtnFilters();
    }
    @And("the verification failure reason is entered")
    public void theVerificationFailureReasonEntered(String reason) throws Exception {
        manageAlertsPage.enterFailureReason(reason);

    }
    @And("select all columns to display under personalisation in manage alert app")
    public void selectAllColumnsToDisplayUnderPersonalisationInManageAlertApp() throws Exception {
        manageAlertsPage.selectAllColumnsToDisplay();
    }
    @And("get verification log data")
    public void getVerificationLogData() throws Exception {
        manageAlertsPage.waitForPageToLoad();
        manageAlertsPage.waitForTableToLoad();
        List<VerificationLog> actualLog = manageAlertsPage.getLogs();
        List<VerificationLog> actualLog1 = manageAlertsPage.getVrLogs();
        alertRuleName = actualLog.get(0).getAlertRuleName();
        alertDate = actualLog.get(0).getAlertDate();
        gtin = actualLog.get(0).getGtin();
        lot = actualLog.get(0).getLot();
        verified = actualLog.get(0).getVerified();
        additionalInfo = actualLog.get(0).getAdditionalInfo();
        status= actualLog.get(0).getStatus();
        issue = actualLog.get(0).getIssue();
        resolution = actualLog.get(0).getResolution();
        expDate = actualLog.get(0).getExpirationDate();
        serialNumber = actualLog.get(0).getSerialNum();
        reqTime = actualLog.get(0).getRequestTime();
        httpstatus = actualLog1.get(0).getHttpstatus();
        responseCode = actualLog1.get(0).getResponseCode();
        responseCodeDescription = actualLog1.get(0).getResponseCodeDescription();
        System.out.println(responseCodeDescription);

    }
    @And("^Save view '(.+)' for filter and Save button clicked in filter modal pop up$")
    public void saveButtonIsClickedInFilterModalPopUp(String viewName) throws Exception {
        commonComponentPage.clickSelectViewDropDown();
        if (manageAlertsPage.getElements(By.xpath("//*[text()='Save']")).size() > 0) {
            manageAlertsPage.clickButton("Save");
        } else {
            manageAlertsPage.clickButton("Save As");
        }
        manageAlertsPage.waitForPageToLoad();
        PortalDriver.getInstance();
        WebDriverWait wait = new WebDriverWait(SeleniumUI5TestUtil.getConfig().getDriver(), 10);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'" + manageAlertsPage.saveViewPopUpHeader + "')]")));
            if (manageAlertsPage.getSaveViewPopUpHeader().isDisplayed()) {
                manageAlertsPage.enterSaveViewName(viewName);
                manageAlertsPage.clickDefaultButton();
                manageAlertsPage.clickSaveViewSaveBtn();
                manageAlertsPage.clickSaveButton();
                //  }
            }
        } catch (Exception e) {
        }
    }
    @And("{string} is set as a default view on the verification log page")
    public void isSetAsDeafultView(String viewName) throws Exception {
        manageAlertsPage.clickSelectViewDropDown();
        manageAlertsPage.clickButton("Manage");
        manageAlertsPage.inputText(manageAlertsPage.viewSearchInput, viewName);
        manageAlertsPage.clickElement(manageAlertsPage.defaultViewChckBox);
        manageAlertsPage.clickButton("Save");
    }
    @And("Go button clicked in filter modal pop up")
    public void goButtonIsClickedInFilterModalPopUp() throws Exception {
        manageAlertsPage.clickFilterPopUpGoButton();
        Thread.sleep(10000);
    }
    @And("^set '(.+)' filter value in managealert page$")
    public void setFilterValueVerificationLogsPage(String field) throws Exception {
        manageAlertsPage.waitForPageToLoad();
        if (field.equals("Status")) {
            manageAlertsPage.enterStatus(status);
        }
        if (field.equals("HTTP Status")) {
            manageAlertsPage.selectDropDownFilterInput(manageAlertsPage.httpstatusId, httpstatus);
        }
        if (field.equals("HTTP Response Code")) {
            manageAlertsPage.selectDropDownFilterInput(manageAlertsPage.httpresponseId, responseCode);
        }
        if (field.equals("GTIN")) {
            manageAlertsPage.enterGtin(gtin);
        }
        if (field.equals("lot")) {
            manageAlertsPage.enterLot(lot);
        }
        if (field.equals("Request Date")) {
            manageAlertsPage.enterRequestDate(StringUtils.substringBefore(reqTime, "T"));
        }
        if (field.equals("Verified")) {
            System.out.println(verified);
            manageAlertsPage.selectDropDownFilterInput(manageAlertsPage.verifiedId, verified);
        }
        if (field.equals("Serial Number")) {
            manageAlertsPage.enterSerialNumber(serialNumber);
        }
        if (field.equals("Additional Information")) {
            manageAlertsPage.enterAdditionalInfo(additionalInfo);
        }
        if (field.equals("ResponderGln")) {
            manageAlertsPage.enterResponderGln(responderGLN);
        }
        if (field.equals("UUID")) {
            manageAlertsPage.enterUUID(uuid);
        }
        if (field.equals("Linktype")) {
            manageAlertsPage.enterLinkType(linkType);
        }
        if (field.equals("Context")) {
            manageAlertsPage.enterContext(context);
        }
        if (field.equals("Requester GLN")) {
            manageAlertsPage.enterRequestorGln(requestorGLN);
        }
    }


//
//    @And("click on the Standard dropdown")
//    public void clickOnStandardDropdown() throws Exception{
//        manageAlertsPage.clickStandardDropdown();
//    }

//    @And("^the (new) is selected$")
//    public void viewSelected(String view) throws Exception {
//        manageAlertsPage.selectView(view);
//    }
//    @And("^the (Set as Default) is checked$")
//    public void setAsDefault(String Default) throws Exception {
//        manageAlertsPage.setDefault(Default);
//    }

    @Then("the HTTPResponseCode dropdown contains")
    public void theHTTPResponseCodeDropdownContains(List<String> expectedCodes) {
        assertEquals(expectedCodes, manageAlertsPage.getHttpResponseCodeValues());
    }


    @Then("^the (BatchNumber) column is not sorted in manage alert app$")
    public void columnNotSortedInAscendingOrder(String columnName) throws Exception {
        manageAlertsPage.waitForTableToLoad();
        if(columnName.equals("BatchNUmber")){
            assertFalse(commonComponentPage.isColumnSortedInAscendingOrder(manageAlertsPage.getColumnValues(columnName)));
            assertFalse(commonComponentPage.isColumnSortedInDescendingOrder(manageAlertsPage.getColumnValues(columnName)));
        }
    }


    @Then("^the (GTIN|AlertRuleName|BatchNumber|ExpirationDate|AlertDate|Verified|AdditionalInformation|Status|Issue|Resolution|RequestTime|SerialNumber|ResponderCompany|RequesterCompany|RequesterGln|ResponderGln|HTTPResponseCode|HTTPStatus) column is sorted in ascending order in manage alert app$")
    public void columnIsSortedInAscendingOrder(String columnName) throws Exception {
        manageAlertsPage.waitForTableToLoad();
        if (columnName.equals("ResponderCompany") || columnName.equals("AlertRuleName") || columnName.equals("GTIN") || columnName.equals("BatchNumber") || columnName.equals("Verified") || columnName.equals("AdditionalInformation") || columnName.equals("Status") || columnName.equals("Issue") || columnName.equals("SerialNumber") || columnName.equals("RequesterGln") || columnName.equals("ResponderGln") || columnName.equals("HTTPResponseCode") || columnName.equals("HTTPStatus") || columnName.equals("RequesterCompany")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(manageAlertsPage.getColumnValues(columnName)));
        }
        if (columnName.equals("ExpirationDate")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(manageAlertsPage.getExpirationDateColumnValues()));
        }
        if (columnName.equals("AlertDate")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(manageAlertsPage.getAlertDateColumnValues()));
        }
        if (columnName.equals("Resolution")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(manageAlertsPage.getResolutionColumn()));
        }
        if (columnName.equals("RequestTime")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(manageAlertsPage.getRequestTimeColumnValues()));
        }

    }
    @Then("^the user should be able see (UIID|AlertRuleName|GTIN) values without any truncation issue in Alert Management screen$")
    public void columnIsPresent(String columnName) throws Exception {
        manageAlertsPage.waitForTableToLoad();
        assertTrue(manageAlertsPage.checkColumnValues(columnName));
    }
    @Then("^the user should be able to see (Manufacturer_policy) in the Verification Fail reason$")
    public void fieldIsPresent(String Field) throws Exception {
        manageAlertsPage.waitForTableToLoad();
        assertEquals(Field,manageAlertsPage.checkFieldValues());
    }
    @Then("^the (GTIN|AlertRuleName|BatchNumber|ExpirationDate|AlertDate|Verified|AdditionalInformation|Status|Issue|Resolution|RequestTime|SerialNumber|ResponderCompany|RequesterCompany|RequesterGln|ResponderGln|HTTPResponseCode|HTTPStatus) column is sorted in descending order in manage alert app$")
    public void columnIsSortedInDescendingOrder(String columnName) throws Exception {
        manageAlertsPage.waitForTableToLoad();
        if (columnName.equals("ResponderCompany") || columnName.equals("AlertRuleName") || columnName.equals("GTIN") || columnName.equals("BatchNumber") || columnName.equals("Verified") || columnName.equals("AdditionalInformation") || columnName.equals("Status") || columnName.equals("Issue") || columnName.equals("SerialNumber") || columnName.equals("RequesterGln") || columnName.equals("ResponderGln") || columnName.equals("HTTPResponseCode") || columnName.equals("HTTPStatus") || columnName.equals("RequesterCompany")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(manageAlertsPage.getColumnValues(columnName)));
        }
        if (columnName.equals("ExpirationDate")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(manageAlertsPage.getExpirationDateColumnValues()));
        }
        if (columnName.equals("AlertDate")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(manageAlertsPage.getAlertDateColumnValues()));
        }
        if (columnName.equals("Resolution")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(manageAlertsPage.getResolutionColumn()));
        }
        if (columnName.equals("RequestTime")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(manageAlertsPage.getRequestTimeColumnValues()));
        }
    }

    @And("click on http response code drop down")
    public void clickOnHttpResponseCodeDropDown() throws Exception {
        manageAlertsPage.clickInputFilterHttpCodeArrow();
    }

    @And("the radio button descending order is clicked in manage alert page")
    public void theRadioButtonDescendingOrderIsClickedInManageAlertPage() throws Exception {
        manageAlertsPage.clickDescendingOrderButton();
    }

    @Then("^the app is '(.+)'$")
    public void theAppIsAppName(String appName) throws Exception {
        launcherPage.switchToDefaultContent();
        assertTrue(commonComponentPage.getAppNameElement(appName).isDisplayed());
    }

    @Then("the manage alert page hides filter components")
    public void theManageAlertPageDoesNotContainTheFilters() {
        try {
            assertFalse(manageAlertsPage.getInputFilterAlertRuleName().isDisplayed());
            assertFalse(manageAlertsPage.getInputAlertDateFilter().isDisplayed());
            assertFalse(manageAlertsPage.getInputFilterAdditionalInfo().isDisplayed());
            assertFalse(manageAlertsPage.getInputFilterBatch().isDisplayed());
            assertFalse(manageAlertsPage.getInputFilterGTIN().isDisplayed());
            assertFalse(manageAlertsPage.getSelectFilterStatus().isDisplayed());
            assertFalse(manageAlertsPage.getVerifiedId().isDisplayed());

        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @And("^the sort option (GTIN|AlertRuleName|BatchNumber|ExpirationDate|AlertDate|Verified|AdditionalInformation|Status|Issue|Resolution|SerialNumber|RequestTime|RequesterGln|ResponderGln|RequesterCompany|ResponderCompany|HTTPStatus|HTTPResponseCode|None) is selected from sort by pop up$")
    public void theSortOptionIsSelected(String option) throws Exception {
        manageAlertsPage.clickSortingRadiobtn(option);
    }
    @And("^the group option (AlertRuleName|GTIN|BatchNumber|Status|Issue|Resolution|RequesterGln|RequesterCompany|Verified|ResponderGln|ResponderCompany) is selected from group by pop up$")
    public void theGroupOptionIsSelected(String option) throws Exception {
        manageAlertsPage.clickGroupRadiobtn(option);
    }
    @And("select an alert from alert records")
    public void selectAlertFromAlertrecords() throws Exception{
        manageAlertsPage.clickAlert();
    }
    @And("^click on the (Issue|Resolution) column$")
    public void selectIssueAndResolutionColumn(String columnName) throws Exception {
        manageAlertsPage.clickIssueAndresolutionColumn(columnName);
    }
    @And("^select (ExpiredProduct|RecheckProduct) from the column$")
    public void selectFromIssueAndResolutionColumn(String columnName) throws Exception {
        manageAlertsPage.selectFromIssueAndResolutionColumn(columnName);
    }
    @Then("^the (AlertRuleName|GTIN|BatchNumber|Status|Issue|Resolution|RequesterGln|RequesterCompany|Verified|ResponderGln|ResponderCompany) column is grouped in ascending order in manage alert app$")
    public void theColumnGroupedInAscendingOrder(String columnName) throws Exception {
        manageAlertsPage.waitForTableToLoad();
        if (columnName.equals("ResponderCompany") || columnName.equals("AlertRuleName") || columnName.equals("GTIN") || columnName.equals("BatchNumber") || columnName.equals("Verified") || columnName.equals("Status") || columnName.equals("Issue") || columnName.equals("RequesterGln") || columnName.equals("ResponderGln") || columnName.equals("RequesterCompany")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(manageAlertsPage.getGroupColumnValues(columnName)));
        }
        if (columnName.equals("Resolution")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(manageAlertsPage.getResolutionGroup()));

        }

    }

    @Then("verify the selected filter components are available")
    public void verifySelectedFilterComponentsAreAvailable(List<String> filterComponent) throws Exception {
        for (String component : filterComponent) {
            if (component.equals("RequestTime")) {
                assertTrue(manageAlertsPage.getRequestDateId().isDisplayed());
            }
            if (component.equals("GTIN")) {
                assertTrue(manageAlertsPage.getGtinId().isDisplayed());
            }
            if (component.equals("LOT")) {
                assertTrue(manageAlertsPage.getLotId().isDisplayed());
            }
            if (component.equals("Verified")) {
                assertTrue(manageAlertsPage.getVerifiedId().isDisplayed());
            }
            if (component.equals("Serial Number")) {
                assertTrue(manageAlertsPage.getSerialNumberId().isDisplayed());
            }
            if (component.equals("Additional Information")) {
                assertTrue(manageAlertsPage.getAdditionalInformationId().isDisplayed());
            }
            if (component.equals("RequestorGln")) {
                assertTrue(manageAlertsPage.getRequestorId().isDisplayed());
            }
            if (component.equals("ResponderGln")) {
                assertTrue(manageAlertsPage.getResponderId().isDisplayed());
            }
            if (component.equals("UUID")) {
                assertTrue(manageAlertsPage.getUUID().isDisplayed());
            }
            if (component.equals("Linktype")) {
                assertTrue(manageAlertsPage.getLinkTypeId().isDisplayed());
            }
            if (component.equals("Context")) {
                assertTrue(manageAlertsPage.getContextId().isDisplayed());
            }
            if (component.equals("Status")) {
                assertTrue(manageAlertsPage.getStatusId().isDisplayed());
            }
            if (component.equals("HTTP Status")) {
                assertTrue(manageAlertsPage.getHttpStatusId().isDisplayed());
            }
            if (component.equals("HTTP Response Code")) {
                assertTrue(manageAlertsPage.getResponseCodeStatusId().isDisplayed());
            }


        }
    }
    @Then("the Group by pop up should contain the following Radio Buttons")
    public void verifyGroupradioButtonsAreAvailable(List<String> groupComponent) throws Exception {
        manageAlertsPage.waitForPageToLoad();
        for (String component: groupComponent) {
            assertTrue(manageAlertsPage.checkGroupRadioButton(component));


        }
    }

    @Then("the results contains filtered values in the response")
    public void theResultsContainsTheFilteredValuesInTheResponse(Map<String, String> expectedLogDetails) throws Exception {
        List<VerificationLog> actualLog = manageAlertsPage.getLogs();
        for (VerificationLog log : actualLog) {
            if (expectedLogDetails.containsKey(STATUS)) {
                assertTrue(log.getStatus().contains(status));
            }
            if (expectedLogDetails.containsKey(REQUESTDATE)) {
                assertTrue(log.getRequestTime().contains(StringUtils.substringBefore(reqTime, "T")));
            }
            if (expectedLogDetails.containsKey(GTIN)) {
                assertTrue(log.getGtin().contains(gtin));
            }
            if (expectedLogDetails.containsKey(LOT)) {
                assertTrue(log.getLot().contains(lot));
            }
            if (expectedLogDetails.containsKey(SERIALNUM)) {
                assertTrue(log.getSerialNum().contains(serialNumber));
            }
            if (expectedLogDetails.containsKey(REQUESTRORGLN)) {
                assertTrue(log.getRequestorGln().contains(requestorGLN));
            }
            if (expectedLogDetails.containsKey(RESPONDERGLN)) {
                assertTrue(log.getResponderGln().contains(responderGLN));
            }
            if (expectedLogDetails.containsKey(ADDITIONALINFO)) {
                System.out.println(log.getAdditionalInfo());
                assertTrue(log.getAdditionalInfo().contains(additionalInfo));
            }
            if (expectedLogDetails.containsKey(VERIFIED)) {
                System.out.println(log.getVerified());
                assertTrue(log.getVerified().contains(verified));
            }
        }
        List<VerificationLog> actualVrLog = manageAlertsPage.getVrLogs();
        for (VerificationLog log : actualVrLog) {

            if (expectedLogDetails.containsKey(UUID)) {
                assertTrue(log.getUUID().contains(uuid));
            }
            if (expectedLogDetails.containsKey(LINKTYPE)) {
                assertTrue(log.getLinkType().contains(linkType));
            }
            if (expectedLogDetails.containsKey(CONTEXT)) {
                assertTrue(log.getContext().contains(context));
            }
            if (expectedLogDetails.containsKey(EXECUTIONDURATION)) {
                assertTrue((Integer.parseInt(executionDurationRangeMax) >= Integer.parseInt(log.getExecutionDuration())) && (Integer.parseInt(log.getExecutionDuration()) >= Integer.parseInt(executionDurationRangeMin)));
            }
            if (expectedLogDetails.containsKey(REQUESTERCOMPANY)) {
                System.out.println(log.getRequesterCompany());
                assertThat(log.getRequesterCompany(), is(expectedLogDetails.get(REQUESTERCOMPANY)));
            }
            if (expectedLogDetails.containsKey(RESPONDERCOMPANY)) {
                assertThat(log.getResponderCompany(), is(expectedLogDetails.get(RESPONDERCOMPANY)));
            }
            if (expectedLogDetails.containsKey(HTTPSTATUS)) {
                assertTrue(log.getHttpstatus().contains(httpstatus));
            }
            if (expectedLogDetails.containsKey(RESPONSECODE)) {
                assertTrue(log.getResponseCode().contains(responseCode));
            }
            if (expectedLogDetails.containsKey(RESPONSECODEDESCRIPTION)) {
               assertTrue(log.getResponseCodeDescription().contains(responseCodeDescription));
            }
        }

    }

}
