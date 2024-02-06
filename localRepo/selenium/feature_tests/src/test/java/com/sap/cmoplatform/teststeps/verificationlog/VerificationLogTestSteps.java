package com.sap.cmoplatform.teststeps.verificationlog;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.objects.WriteLog;
import com.sap.cmoplatform.pages.CommonComponentPage;
import com.sap.cmoplatform.pages.LauncherPage;
import com.sap.cmoplatform.pages.verificationlog.VerificationLogPage;
import com.sap.cmoplatform.objects.VerificationLog;
import com.sap.cmoplatform.pages.writelogs.WriteLogsPage;
import com.sap.cmoplatform.utils.PortalDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class VerificationLogTestSteps {
    private VerificationLogPage verificationLogPage = new VerificationLogPage();
    private LauncherPage launcherPage = new LauncherPage();
    private CommonComponentPage commonComponentPage = new CommonComponentPage();
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
    private static final String RESPONSECODE = "ResponseCode";
    private static final String RESPONSECODEDESCRIPTION = "ResponseCodeDescription";

    String executionDuration, executionDurationRange, executionDurationRangeMin, executionDurationRangeMax;
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

    @Then("^the title of the Verification log table is '(.+)'$")
    public void theTitleOfTheVerificationLogTableIsTitle(String title) throws Exception {
        assertTrue(verificationLogPage.getTableHeader().contains(title));
    }

    @And("the filters button is clicked in the verification log page")
    public void theFiltersButtonIsClickedInTheVerificationLogPage() throws Exception {
        verificationLogPage.waitForPageToLoad();
        verificationLogPage.clickBtnFilters();
    }

    @And("^Save view '(.+)' for filter and Save button is clicked in filter modal pop up$")
    public void saveButtonIsClickedInFilterModalPopUp(String viewName) throws Exception {
        commonComponentPage.clickSelectViewDropDown();
        if (verificationLogPage.getElements(By.xpath("//*[text()='Save']")).size() > 0) {
            verificationLogPage.clickButton("Save");
        } else {
            verificationLogPage.clickButton("Save As");
        }
        verificationLogPage.waitForPageToLoad();
        PortalDriver.getInstance();
        WebDriverWait wait = new WebDriverWait(SeleniumUI5TestUtil.getConfig().getDriver(), 10);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'" + verificationLogPage.saveViewPopUpHeader + "')]")));
            if (verificationLogPage.getSaveViewPopUpHeader().isDisplayed()) {
                verificationLogPage.enterSaveViewName(viewName);
                verificationLogPage.clickDefaultButton();
                verificationLogPage.clickSaveViewSaveBtn();
                verificationLogPage.clickSaveButton();
                //  }
            }
        } catch (Exception e) {
        }
    }

    @And("Go button is clicked in filter modal pop up")
    public void goButtonIsClickedInFilterModalPopUp() throws Exception {
        verificationLogPage.clickFilterPopUpGoButton();
        Thread.sleep(10000);
    }

    @Then("verify selected filter components are available")
    public void verifySelectedFilterComponentsAreAvailable(List<String> filterComponent) throws Exception {
        for (String component : filterComponent) {
            if (component.equals("RequestTime")) {
                assertTrue(verificationLogPage.getRequestDateId().isDisplayed());
            }
            if (component.equals("GTIN")) {
                assertTrue(verificationLogPage.getGtinId().isDisplayed());
            }
            if (component.equals("LOT")) {
                assertTrue(verificationLogPage.getLotId().isDisplayed());
            }
            if (component.equals("Verified")) {
                assertTrue(verificationLogPage.getVerifiedId().isDisplayed());
            }
            if (component.equals("Serial Number")) {
                assertTrue(verificationLogPage.getSerialNumberId().isDisplayed());
            }
            if (component.equals("Additional Information")) {
                assertTrue(verificationLogPage.getAdditionalInformationId().isDisplayed());
            }
            if (component.equals("RequestorGln")) {
                assertTrue(verificationLogPage.getRequestorId().isDisplayed());
            }
            if (component.equals("ResponderGln")) {
                assertTrue(verificationLogPage.getResponderId().isDisplayed());
            }
            if (component.equals("UUID")) {
                assertTrue(verificationLogPage.getUUID().isDisplayed());
            }
            if (component.equals("Linktype")) {
                assertTrue(verificationLogPage.getLinkTypeId().isDisplayed());
            }
            if (component.equals("Context")) {
                assertTrue(verificationLogPage.getContextId().isDisplayed());
            }

        }
    }

    @And("Go button is clicked in verification home page")
    public void goButtonIsClickedInVerificationHomePage() throws Exception {
        verificationLogPage.clickGoButton();
    }

    @Then("the results contains the Verified filtered values in the response")
    public void theResultsContainsVerfiedValues(Map<String, String> expectedLogDetails) throws Exception {
        List<VerificationLog> actualLog = verificationLogPage.getLogs();
        for (VerificationLog log : actualLog) {
            System.out.println(log.getVerified());
            assertTrue(log.getVerified().contains(expectedLogDetails.get("Verified")));
        }

    }

    @Then("the results contains the filtered values in the response")
    public void theResultsContainsTheFilteredValuesInTheResponse(Map<String, String> expectedLogDetails) throws Exception {
        List<VerificationLog> actualLog = verificationLogPage.getLogs();
        for (VerificationLog log : actualLog) {
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
        List<VerificationLog> actualVrLog = verificationLogPage.getVrLogs();
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
                System.out.println(log.getResponderCompany());
                assertThat(log.getRequesterCompany(), is(expectedLogDetails.get(REQUESTERCOMPANY)));
            }
            if (expectedLogDetails.containsKey(RESPONDERCOMPANY)) {
                assertThat(log.getResponderCompany(), is(expectedLogDetails.get(RESPONDERCOMPANY)));
            }
            if (expectedLogDetails.containsKey(STATUS)) {
                assertThat(log.getStatus(), is(expectedLogDetails.get(STATUS)));
            }
            if (expectedLogDetails.containsKey(RESPONSECODE)) {
                System.out.println(log.getResponseCode());
                System.out.println(expectedLogDetails.get(RESPONSECODE));
                assertThat(log.getResponseCode(), is(expectedLogDetails.get(RESPONSECODE)));
            }
            if (expectedLogDetails.containsKey(RESPONSECODEDESCRIPTION)) {
                System.out.println(log.getResponseCodeDescription());
                System.out.println(expectedLogDetails.get(RESPONSECODEDESCRIPTION));
                assertThat(log.getResponseCodeDescription(), is(expectedLogDetails.get(RESPONSECODEDESCRIPTION)));
            }
        }

    }

    @And("Go button is click in filter modal pop up")
    public void goButtonIsClickInFilterModalPopUp() throws Exception {
        verificationLogPage.clickFilterPopUpGoButton();
    }

    @And("^set filter value gtin '(.+)'$")
    public void setFilterValueGtinGTIN(String gtin) throws Exception {
        verificationLogPage.enterGtin(gtin);
    }

    @And("^set filter value lot '(.+)'$")
    public void setFilterValueLotLOT(String lot) throws Exception {
        verificationLogPage.enterLot(lot);
    }

    @And("Show Filter Bar button is clicked")
    public void showFilterBarButtonIsClicked() throws Exception {
        verificationLogPage.clickShowHideFilterBarButton();
    }

    @And("^set filter value serialNumber '(.+)'$")
    public void setFilterValueSerialNumberSerialNumber(String serialNum) throws Exception {
        verificationLogPage.enterSerialNumber(serialNum);
    }


    @And("^the (verified|RequesterCompany|ResponderCompany), '(.+)', is selected on the verification log page$")
    public void theVerifiedIsSelected(String fieldName, String fieldValue) throws Exception {
        verificationLogPage.selectDropDownValue(fieldName, fieldValue);

    }

    @And("^set filter value Additional Info '(.+)'$")
    public void setFilterValueAdditionalInfoAdditionalInfo(String additionalInfo) throws Exception {
        verificationLogPage.enterAdditionalInfo(additionalInfo);
    }

    @And("^set filter value requestor Gln '(.+)'$")
    public void setFilterValueRequestorGlnRequestorGln(String reqGln) throws Exception {
        verificationLogPage.enterRequestorGln(reqGln);
    }

    @And("^set filter value responder Gln '(.+)'$")
    public void setFilterValueResponderGlnResponderGln(String resGln) throws Exception {
        verificationLogPage.enterResponderGln(resGln);
    }


    @And("^the sort option (GTIN|LOT|Verified|RequestTime|SerialNumber|RequestorGln|ResponderGln|AdditionalInfo|ExpirationDate|ExecutionDuration|RequesterCompany|ResponderCompany) is selected$")
    public void theSortOptionIsSelected(String option) throws Exception {
        if (option.equals("LOT")) {
            verificationLogPage.clickBtnLotRadio();
        }
        if (option.equals("GTIN")) {
            verificationLogPage.clickBtnGtinRadio();
        }
        if (option.equals("Verified")) {
            verificationLogPage.clickBtnVerifiedRadio();
        }
        if (option.equals("RequestTime")) {
            verificationLogPage.clickBtnRequestTimeRadio();
        }
        if (option.equals("RequestorGln")) {
            verificationLogPage.clickBtnRequestorGlnRadio();
        }
        if (option.equals("ResponderGln")) {
            verificationLogPage.clickBtnResponderGlnRadio();
        }
        if (option.equals("AdditionalInfo")) {
            verificationLogPage.clickBtnAdditionalInfoRadio();
        }
        if (option.equals("SerialNumber")) {
            verificationLogPage.clickBtnSerialNumberRadio();
        }
        if (option.equals("ExpirationDate")) {
            verificationLogPage.clickBtnExpirationDateRadio();
        }
        if (option.equals("ExecutionDuration")) {
            verificationLogPage.clickBtnExecutionDurationRadio();
        }
        if (option.equals("RequesterCompany")) {
            verificationLogPage.clickBtnRequesterCompanyRadio();
        }
        if (option.equals("ResponderCompany")) {
            verificationLogPage.clickBtnResponderCompanyRadio();
        }

    }

    @Then("^the (GTIN|LOT|Verified|RequestTime|SerialNumber|RequestorGln|ResponderGln|AdditionalInfo|ExpirationDate|ExecutionDuration|ResponderCompany|RequesterCompany) column is sorted in ascending order$")
    public void columnIsSortedInAscendingOrder(String columnName) throws Exception {
        if (columnName.equals("LOT")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getLotColumnValues()));
        }
        if (columnName.equals("GTIN")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getGtinColumnValues()));
        }
        if (columnName.equals("Verified")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getVerifiedColumnValues()));
        }
        if (columnName.equals("RequestTime")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getRequestTimeColumnValues()));
        }
        if (columnName.equals("SerialNumber")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getSerialNumberColumnValues()));
        }
        if (columnName.equals("RequestorGln")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getRequestorGlnColumnValues()));
        }
        if (columnName.equals("ResponderGln")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getResponderGlnColumnValues()));
        }
        if (columnName.equals("AdditionalInfo")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getAdditionalInfoColumnValues()));
        }
        if (columnName.equals("ExpirationDate")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getExpirationDateColumnValues()));
        }
        if (columnName.equals("ExecutionDuration")) {
            try {
                if (!verificationLogPage.getNodataTextId().isDisplayed()) {
                    assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getExecutionDurationColumnValues()));
                }
            } catch (Exception ne) {
                System.out.println("No data result not displayed");
            }

        }
        if (columnName.equals("RequesterCompany")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getRequesterCompanyColumnValues()));
        }
        if (columnName.equals("ResponderCompany")) {
            assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(verificationLogPage.getResponderCompanyColumnValues()));
        }
    }

    @Then("^the (GTIN|LOT|Verified|RequestTime|SerialNumber|RequestorGln|ResponderGln|AdditionalInfo|ExpirationDate|ExecutionDuration) column is sorted in descending order$")
    public void columnIsSortedInDescendingOrder(String columnName) throws Exception {
        if (columnName.equals("LOT")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getLotColumnValues()));
        }
        if (columnName.equals("GTIN")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getGtinColumnValues()));
        }
        if (columnName.equals("Verified")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getVerifiedColumnValues()));
        }
        if (columnName.equals("RequestTime")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getRequestTimeColumnValues()));
        }
        if (columnName.equals("SerialNumber")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getSerialNumberColumnValues()));
        }
        if (columnName.equals("RequestorGln")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getRequestorGlnColumnValues()));
        }
        if (columnName.equals("ResponderGln")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getResponderGlnColumnValues()));
        }
        if (columnName.equals("AdditionalInfo")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getAdditionalInfoColumnValues()));
        }
        if (columnName.equals("ExpirationDate")) {
            assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getExpirationDateColumnValues()));
        }

        if (columnName.equals("ExecutionDuration")) {
            try {
                if (!verificationLogPage.getNodataTextId().isDisplayed()) {
                    assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(verificationLogPage.getExecutionDurationColumnValues()));
                }
            } catch (Exception ne) {
                System.out.println("No data result not displayed");
            }

        }
    }

    @And("the radio button descending order is clicked")
    public void theRadioButtonDescendingOrderIsClicked() throws Exception {
        verificationLogPage.clickBtnDescendingOrder();
    }

    @And("select all columns to display under personalisation")
    public void selectAllColumnHeaderIsSet() throws Exception {
        verificationLogPage.waitForPageToLoad();
        verificationLogPage.selectAllColumnsToDisplay();
    }

    @And("^clear filter (gtin|lot|serialNumber|requestorGln|responderGln|additionalInfo|requestDate|Response Code) value in verification log$")
    public void clearFilterValue(String filter) throws Exception {
        if (filter.equals("gtin")) {
            verificationLogPage.clearGtinValue();
        }
        if (filter.equals("lot")) {
            verificationLogPage.clearLotValue();
        }
        if (filter.equals("serialNumber")) {
            verificationLogPage.clearSerialNumberValue();
        }
        if (filter.equals("requestorGln")) {
            verificationLogPage.clearRequestorGlnValue();
        }
        if (filter.equals("responderGln")) {
            verificationLogPage.clearResponderGlnValue();
        }
        if (filter.equals("additionalInfo")) {
            verificationLogPage.clearAdditionalInfoValue();
        }
        if (filter.equals("requestDate")) {
            verificationLogPage.clearRequestDateValue();
        }
        if (filter.equals("Response Code")) {
            verificationLogPage.getElement(verificationLogPage.responseCodeCol).clear();
        }
    }

    @And("verify default value for Verified filter component")
    public void verifyDefaultValueForVerified(Map<String, String> filterComponent) throws Exception {
        assertEquals(verificationLogPage.getDefaultVerifiedId().getText(), filterComponent.get("Verified"));
    }


    @And("verify default value for filter component")
    public void verifyDefaultValueForFilterComponent(Map<String, String> filterComponent) throws Exception {
        verificationLogPage.waitForPageToLoad();
        if (filterComponent.containsKey("Verified")) {
            assertEquals(verificationLogPage.getDefaultVerifiedId().getText(), verified);
        }
        if (filterComponent.containsKey("RequestTime")) {
            Date expecteddate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(reqTime);
            Date actualdate = new SimpleDateFormat("MMM dd, yyyy").parse(verificationLogPage.getRequestDateId().getAttribute("value"));
            SimpleDateFormat formatdate = new SimpleDateFormat("MMM dd, yyyy");
            assertEquals(formatdate.format(expecteddate), formatdate.format(actualdate));
        }
        if (filterComponent.containsKey("GTIN")) {
            assertEquals(verificationLogPage.getGtinId().getAttribute("value"), gtin);
        }
        if (filterComponent.containsKey("LOT")) {
            assertEquals(verificationLogPage.getLotId().getAttribute("value"), lot);
        }
        if (filterComponent.containsKey("SerialNumber")) {
            assertEquals(verificationLogPage.getSerialNumberId().getAttribute("value"), serialNumber);
        }
        if (filterComponent.containsKey("AdditionalInfo")) {
            assertEquals(verificationLogPage.getAdditionalInformationId().getAttribute("value"), additionalInfo);
        }
        if (filterComponent.containsKey("RequestorGln")) {
            assertEquals(verificationLogPage.getRequestorId().getAttribute("value"), requestorGLN);
        }
        if (filterComponent.containsKey("ResponderGln")) {
            assertEquals(verificationLogPage.getResponderId().getAttribute("value"), responderGLN);
        }
        if (filterComponent.containsKey("UUID")) {
            assertEquals(verificationLogPage.getUUID().getAttribute("value"), uuid);
        }
        if (filterComponent.containsKey("Linktype")) {
            assertEquals(verificationLogPage.getLinkTypeId().getAttribute("value"), linkType);
        }
        if (filterComponent.containsKey("Context")) {
            if (verificationLogPage.getContextId().getAttribute("value") == "")
                assertEquals(verificationLogPage.getContextValues().getText(), context);
            else
                assertEquals(verificationLogPage.getContextId().getAttribute("value"), context);
        }
        if (filterComponent.containsKey("ExecutionDuration")) {
            assertEquals(verificationLogPage.getExecutionDurationStartHandle().getAttribute("aria-valuenow"), executionDurationRangeMin);
            assertEquals(verificationLogPage.getExecutionDurationEndHandle().getAttribute("aria-valuenow"), executionDurationRangeMax);

        }
        if (filterComponent.containsKey("RequesterCompany")) {
            System.out.println(verificationLogPage.getDefaultRequesterCompanyId().getText() + " requestor Company" + filterComponent.get("RequesterCompany"));
            assertEquals(verificationLogPage.getDefaultRequesterCompanyId().getText(), filterComponent.get("RequesterCompany"));
        }
        if (filterComponent.containsKey("ResponderCompany")) {
            System.out.println(verificationLogPage.getDefaultResponderCompanyId().getText() + " requestor Company" + filterComponent.get("ResponderCompany"));
            assertEquals(verificationLogPage.getDefaultResponderCompanyId().getText(), filterComponent.get("ResponderCompany"));
        }
    }


    @And("clear all filter component fields")
    public void clearAllFilterComponentFields() throws Exception {
        verificationLogPage.getElement(verificationLogPage.clearFilter).click();
    }


//    @And("^switch to my view if '(.+)' exists$")
//    public void switchToMyViewIfViewNameExists(String viewName) throws Exception {
//        verificationLogPage.clickSelectViewDropDown();
//        try {
//            if (verificationLogPage.availableView(viewName).isDisplayed()) {
//                verificationLogPage.setDefaultView(viewName);
//                verificationLogPage.selectAvailableView(viewName);
//            }
//        } catch (Exception e) {
//        }
//    }

    @And("click on first record of verification log")
    public void clickOnFirstRecordOfVerificationLog() throws Exception {
        verificationLogPage.waitForPageToLoad();
        verificationLogPage.clickFirstRecord();
    }

    @Then("the request details contains")
    public void theRequestDetailsContains(Map<String, String> expectedDetails) throws Exception {
        if (expectedDetails.containsKey("GTIN")) {
            System.out.println(verificationLogPage.getRequestDetails().get("GTIN"));
            System.out.println(expectedDetails.get(GTIN));
            assertThat(verificationLogPage.getRequestDetails().get("GTIN"), is(expectedDetails.get(GTIN)));
        }
        if (expectedDetails.containsKey("LOT")) {
            assertThat(verificationLogPage.getRequestDetails().get("LOT"), is(expectedDetails.get(LOT)));
        }
        if (expectedDetails.containsKey("SerialNumber")) {
            assertThat(verificationLogPage.getRequestDetails().get("Serial Number"), is(expectedDetails.get(SERIALNUM)));
        }
        if (expectedDetails.containsKey("Additional Information")) {
            assertThat(verificationLogPage.getRequestDetails().get("Additional Information"), is(expectedDetails.get(ADDITIONALINFO)));
        }
        if (expectedDetails.containsKey("Verified")) {
            assertThat(verificationLogPage.getRequestDetails().get("Verified"), is(expectedDetails.get(VERIFIED)));
        }
        if (expectedDetails.containsKey("Requester GLN")) {
            assertThat(verificationLogPage.getRequestDetails().get("Requester GLN"), is(expectedDetails.get(REQUESTRORGLN)));
        }
        if (expectedDetails.containsKey("Responder GLN")) {
            assertThat(verificationLogPage.getRequestDetails().get("Responder GLN"), is(expectedDetails.get(RESPONDERGLN)));
        }
        if (expectedDetails.containsKey("UUID")) {
            assertThat(verificationLogPage.getRequestDetails().get("UUID"), is(expectedDetails.get(UUID)));
        }
        if (expectedDetails.containsKey("Linktype")) {
            assertThat(verificationLogPage.getRequestDetails().get("Linktype"), is(expectedDetails.get(LINKTYPE)));
        }
        if (expectedDetails.containsKey("Context")) {
            assertThat(verificationLogPage.getRequestDetails().get("Context"), is(expectedDetails.get(CONTEXT)));
        }
        if (expectedDetails.containsKey("Execution Duration")) {
            assertThat(verificationLogPage.getRequestDetails().get("Execution Duration"), is(executionDuration));
        }
        if (expectedDetails.containsKey("Request Time")) {
            assertTrue(verificationLogPage.getRequestDetails().get("Request Time").contains(expectedDetails.get(REQUESTDATE)));
        }
    }


    @Then("verify the maximum allowed length in verification log")
    public void verifyTheMaximumAllowedLengthInVerificationLog(Map<String, String> inputValue) throws Exception {
        if (inputValue.containsKey("GTIN")) {
            assertTrue(verificationLogPage.getGtinId().getAttribute("value").length() == 14);
        }
        if (inputValue.containsKey("RequestorGln")) {
            assertTrue(verificationLogPage.getRequestorId().getAttribute("value").length() == 13);
        }
        if (inputValue.containsKey("ResponderGln")) {
            assertTrue(verificationLogPage.getResponderId().getAttribute("value").length() == 13);
        }
        if (inputValue.containsKey("ResponderGln")) {
            assertTrue(verificationLogPage.getResponderId().getAttribute("value").length() == 13);
        }
        if (inputValue.containsKey("AdditionalInfo")) {
            assertTrue(verificationLogPage.getAdditionalInformationId().getAttribute("value").length() == 50);
        }
        if (inputValue.containsKey("LOT")) {
            assertTrue(verificationLogPage.getLotId().getAttribute("value").length() == 20);
        }
        if (inputValue.containsKey("SerialNumber")) {
            assertTrue(verificationLogPage.getSerialNumberId().getAttribute("value").length() == 20);
        }
        if (inputValue.containsKey("UUID")) {
            assertTrue(verificationLogPage.getUUID().getAttribute("value").length() == 100);
        }
    }


    @And("^set filter value UUID '(.+)'$")
    public void setFilterValueUUIDUUID(String UUID) throws Exception {
        verificationLogPage.enterUUID(UUID);
    }

    @And("^set filter value Linktype '(.+)'$")
    public void setFilterValueLinktypeLinktype(String linkType) throws Exception {
        verificationLogPage.enterLinkType(linkType);

    }

    @And("^set filter value Context '(.+)'$")
    public void setFilterValueContextContext(String context) throws Exception {
        verificationLogPage.enterContext(context);
    }

    @And("select columns from the personalisation settings")
    public void selectColumnsFromThePersonalisationSettings(List<String> columnName) throws Exception {
        verificationLogPage.selectAllColumnButton();

        for (int i = 0; i < columnName.size(); i++) {
            if (columnName.get(i).equals("RequestTime")) {
                verificationLogPage.selectRequestTimeColumn();
            } else if (columnName.get(i).equals("Verified")) {
                verificationLogPage.selectVerifiedColumn();
            } else if (columnName.get(i).equals("SerialNumber")) {
                verificationLogPage.selectSerialNumberColumn();
            } else if (columnName.get(i).equals("AdditionalInfo")) {
                verificationLogPage.selectAdditionalInfoColumn();
            } else if (columnName.get(i).equals("GTIN")) {
                verificationLogPage.selectGtinColumnn();
            } else if (columnName.get(i).equals("LOT")) {
                verificationLogPage.selectLotColumn();
            } else if (columnName.get(i).equals("RequesterGln")) {
                verificationLogPage.selectRequestorGlnColumn();
            } else if (columnName.get(i).equals("ResponderGln")) {
                verificationLogPage.selectResponderGlnColumn();
            } else if (columnName.get(i).equals("UUID")) {
                verificationLogPage.selectUuidColumn();
            } else if (columnName.get(i).equals("Linktype")) {
                verificationLogPage.selectLinkTypeColumn();
            } else if (columnName.get(i).equals("Context")) {
                verificationLogPage.selectContextColumn();
            } else if (columnName.get(i).equals("ExpirationDate")) {
                verificationLogPage.selectExpirationDateColumn();
            } else if (columnName.get(i).equals("VerificationFailureReason")) {
                verificationLogPage.selectVerificationFailureReasonColumn();
            }
        }


    }

    @Then("the verification log table contains")
    public void theVerificationLogTableContains(Map<String, String> expectedColumn) throws Exception {
        if (expectedColumn.containsKey("RequestTime")) {
            assertTrue(verificationLogPage.getRequestTimeCol().isDisplayed());
        }
        if (expectedColumn.containsKey("Verified")) {
            assertTrue(verificationLogPage.getVerifiedCol().isDisplayed());
        }
        if (expectedColumn.containsKey("SerialNumber")) {
            assertTrue(verificationLogPage.getSerialNumberCol().isDisplayed());
        }
        if (expectedColumn.containsKey("AdditionalInfo")) {
            assertTrue(verificationLogPage.getAdditionalInformationCol().isDisplayed());
        }
        if (expectedColumn.containsKey("GTIN")) {
            assertTrue(verificationLogPage.getGtinCol().isDisplayed());
        }
        if (expectedColumn.containsKey("LOT")) {
            assertTrue(verificationLogPage.getLotCol().isDisplayed());
        }
        if (expectedColumn.containsKey("RequestorGln")) {
            assertTrue(verificationLogPage.getRequestorGlnCol().isDisplayed());
        }
        if (expectedColumn.containsKey("ResponderGln")) {
            assertTrue(verificationLogPage.getResponderGlnCol().isDisplayed());
        }
        if (expectedColumn.containsKey("UUID")) {
            assertTrue(verificationLogPage.getUuidCol().isDisplayed());
        }
        if (expectedColumn.containsKey("Context")) {
            assertTrue(verificationLogPage.getContextCol().isDisplayed());
        }
        if (expectedColumn.containsKey("Linktype")) {
            assertTrue(verificationLogPage.getLinkTypeCol().isDisplayed());
        }
        if (expectedColumn.containsKey("ExpirationDate")) {
            assertTrue(verificationLogPage.getExpirationDateCol().isDisplayed());
        }
        if (expectedColumn.containsKey("VerificationFailureReason")) {
            assertTrue(verificationLogPage.getVerificationFailureReasonCol().isDisplayed());
        }
        if (expectedColumn.containsKey("ExecutionDuration")) {
            assertTrue(verificationLogPage.getExecutionDurationCol().isDisplayed());
        }
        if (expectedColumn.containsKey("RequesterCompany")) {
            assertTrue(verificationLogPage.getRequesterCompanyCol().isDisplayed());
        }
        if (expectedColumn.containsKey("ResponderCompany")) {
            assertTrue(verificationLogPage.getResponderCompanyCol().isDisplayed());
        }
        if (expectedColumn.containsKey("Status")) {
            assertTrue(verificationLogPage.getStatusCol().isDisplayed());
        }
        if (expectedColumn.containsKey("ResponseCode")) {
            assertTrue(verificationLogPage.getResponseCodeCol().isDisplayed());
        }
        if (expectedColumn.containsKey("ResponseCodeDescription")) {
            assertTrue(verificationLogPage.getResponseCodeDescriptionCol().isDisplayed());
        }
        if (expectedColumn.containsKey("AlertEmailStatus")) {
            assertTrue(verificationLogPage.getAlertEmailStatusCol().isDisplayed());
        }

    }

    @Then("the verification log table does not contains")
    public void theVerificationLogTableDoesNotContains(Map<String, String> expectedColumn) throws Exception {

        if (expectedColumn.containsKey("RequestTime")) {
            try {
                assertFalse(verificationLogPage.getRequestTimeCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("Verified")) {
            try {
                assertFalse(verificationLogPage.getVerifiedCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("SerialNumber")) {
            try {
                assertFalse(verificationLogPage.getSerialNumberCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("AdditionalInfo")) {
            try {
                assertFalse(verificationLogPage.getAdditionalInformationCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("GTIN")) {
            try {
                assertFalse(verificationLogPage.getGtinCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("LOT")) {
            try {
                assertFalse(verificationLogPage.getLotCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("RequesterGln")) {
            try {
                assertFalse(verificationLogPage.getRequestorGlnCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("ResponderGln")) {
            try {
                assertFalse(verificationLogPage.getResponderGlnCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("UUID")) {
            try {
                assertFalse(verificationLogPage.getUuidCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("Context")) {
            try {
                assertFalse(verificationLogPage.getContextCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("Linktype")) {
            try {
                assertFalse(verificationLogPage.getLinkTypeCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("ExpirationDate")) {
            try {
                assertFalse(verificationLogPage.getExpirationDateCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("VerificationFailureReason")) {
            try {
                assertFalse(verificationLogPage.getVerificationFailureReasonCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("ExecutionDuration")) {
            try {
                assertFalse(verificationLogPage.getExecutionDurationCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }

        if (expectedColumn.containsKey("RequesterCompany")) {
            try {
                assertFalse(verificationLogPage.getRequesterCompanyCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }

        if (expectedColumn.containsKey("ResponderCompany")) {
            try {
                assertFalse(verificationLogPage.getResponderCompanyCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }

        if (expectedColumn.containsKey("Status")) {
            try {
                assertFalse(verificationLogPage.getStatusCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }

        if (expectedColumn.containsKey("ResponseCode")) {
            try {
                assertFalse(verificationLogPage.getResponseCodeCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
        if (expectedColumn.containsKey("ResponseCodeDescription")) {
            try {
                assertFalse(verificationLogPage.getResponseCodeDescriptionCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }

        if (expectedColumn.containsKey("AlertEmailStatus")) {
            try {
                assertFalse(verificationLogPage.getAlertEmailStatusCol().isDisplayed());
            } catch (Exception e) {
                //catch if element is not found
            }
        }
    }

    @And("^move (RequestTime) column by one place$")
    public void moveRequestTimeColumnByOnePlace(String columnName) throws Exception {
        verificationLogPage.clickPersonalDialogDownButton();
    }

    @Then("^the second column in verification log table contains (Request Time)$")
    public void theSecondColumnInVerificationLogTableContainsRequestTime(String columnHeader) throws Exception {
        assertEquals(columnHeader, verificationLogPage.getColumnHeader());
    }


    @And("^the status, '(.+)', is selected on the verification log page$")
    public void theStatusStatusIsSelectedOnTheVerificationLogPage(String status) throws Exception {
        verificationLogPage.selectStatusFilter(status);
    }

    @Then("^execution duration range is '(.+)'$")
    public void executionDurationRangeIs(String range) throws Exception {
        assertEquals(range, verificationLogPage.getExecutionDurationRange());
    }

    @And("select default columns to display under personalisation")
    public void selectDefaultColumnsToDisplayUnderPersonalisation() throws Exception {
        verificationLogPage.selectDefaultColumnsToDisplay();
    }

    @Then("the filter component contains fields")
    public void theFilterComponentsContainsFields(Map<String, String> expectedColumn) throws Exception {
        if (expectedColumn.containsKey("RequestTime")) {
            assertTrue(verificationLogPage.getRequestDateId().isDisplayed());
        }
        if (expectedColumn.containsKey("Verified")) {
            assertTrue(verificationLogPage.getVerifiedId().isDisplayed());
        }
        if (expectedColumn.containsKey("SerialNumber")) {
            assertTrue(verificationLogPage.getSerialNumberId().isDisplayed());
        }
        if (expectedColumn.containsKey("AdditionalInfo")) {
            assertTrue(verificationLogPage.getAdditionalInformationId().isDisplayed());
        }
        if (expectedColumn.containsKey("GTIN")) {
            assertTrue(verificationLogPage.getGtinId().isDisplayed());
        }
        if (expectedColumn.containsKey("LOT")) {
            assertTrue(verificationLogPage.getLotId().isDisplayed());
        }
        if (expectedColumn.containsKey("RequestorGln")) {
            assertTrue(verificationLogPage.getRequestorId().isDisplayed());
        }
        if (expectedColumn.containsKey("ResponderGln")) {
            assertTrue(verificationLogPage.getResponderId().isDisplayed());
        }
        if (expectedColumn.containsKey("UUID")) {
            assertTrue(verificationLogPage.getUUID().isDisplayed());
        }
        if (expectedColumn.containsKey("Context")) {
            assertTrue(verificationLogPage.getContextId().isDisplayed());
        }
        if (expectedColumn.containsKey("Linktype")) {
            assertTrue(verificationLogPage.getLinkTypeId().isDisplayed());
        }
        if (expectedColumn.containsKey("ExecutionDuration")) {
            assertTrue(verificationLogPage.getExecutionDurationId().isDisplayed());
        }
        if (expectedColumn.containsKey("RequesterCompany")) {
            assertTrue(verificationLogPage.getRequesterCompanyId().isDisplayed());
        }
        if (expectedColumn.containsKey("ResponderCompany")) {
            assertTrue(verificationLogPage.getResponderCompanyId().isDisplayed());
        }
    }

    @Then("the filter component does not contains fields")
    public void theFilterComponentDoesNotContainsFields(Map<String, String> filterId) {
        if (filterId.containsKey("RequestTime")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.requestDateId)).size() > 0);
        }
        if (filterId.containsKey("Verified")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.verifiedId)).size() > 0);
        }
        if (filterId.containsKey("SerialNumber")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.serialNumber)).size() > 0);
        }
        if (filterId.containsKey("AdditionalInfo")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.additionalInformationId)).size() > 0);
        }
        if (filterId.containsKey("GTIN")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.gtinId)).size() > 0);
        }
        if (filterId.containsKey("LOT")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.lotId)).size() > 0);
        }
        if (filterId.containsKey("RequesterGln")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.requestorGlnId)).size() > 0);
        }
        if (filterId.containsKey("ResponderGln")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.responderGlnId)).size() > 0);
        }
        if (filterId.containsKey("UUID")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.uuid)).size() > 0);
        }
        if (filterId.containsKey("Context")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.contextId)).size() > 0);
        }
        if (filterId.containsKey("Linktype")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.linkTypeId)).size() > 0);
        }
        if (filterId.containsKey("ExecutionDuration")) {
            assertFalse(verificationLogPage.getElements(By.id(verificationLogPage.executionDurationId)).size() > 0);
        }
    }

    @And("get execution duration of first record")
    public void getExecutionDurationOfFirstRecord() throws Exception {
        executionDuration = verificationLogPage.getExecutionDurationOfFirstRecord();
    }


    @And("get execution duration range from filter field")
    public void getExecutionDurationRangeFromFilterField() throws Exception {
        executionDurationRangeMin = verificationLogPage.getExecutionDurationStartHandle().getAttribute("aria-valuenow");
        executionDurationRangeMax = verificationLogPage.getExecutionDurationEndHandle().getAttribute("aria-valuenow");
    }

    @And("select execution duration range")
    public void selectExecutionDurationExecutionDurationRange() throws Exception {
        verificationLogPage.selectExecutionDurationRange();
    }

    @And("set execution duration range to default value")
    public void setExecutionDurationRangeToDefaultValue() throws Exception {
        for (int i = 0; i < 2; i++) {
            if (!verificationLogPage.getExecutionDurationStartHandle().getAttribute("aria-valuenow").equals("1")) {
                verificationLogPage.selectDefaultExecutionDurationMinRange();
            }
            if (!verificationLogPage.getExecutionDurationEndHandle().getAttribute("aria-valuenow").equals("60000")) {
                verificationLogPage.selectDefaultExecutionDurationMaxRange();
            }
        }
    }

    @And("click on repository explorer link")
    public void clickOnRepositoryExplorerLink() throws Exception {
        // verificationLogPage.analyzeLog();
        verificationLogPage.clickRepositoryExplorerLink();
    }


    @Then("validate the execution duration captured")
    public void validateTheExecutionDurationCaptured() throws Exception {
        assertTrue(Integer.parseInt(verificationLogPage.getExecutionDurationOfFirstRecord()) > 0);
        assertTrue(!verificationLogPage.getExecutionDurationOfFirstRecord().isEmpty());

    }

    @Then("verify repository explorer link is not visible")
    public void verifyRepositoryExplorerLinkIsNotVisible() {
        try {
            assertFalse(verificationLogPage.getRepositoryExplorerLink().isDisplayed());
        } catch (Exception e) {
            assertTrue("Repository Explorer link Not visible", true);
        }

    }

    @Then("the verification log record count is displayed")
    public void theVerificationLogRecordCountIsDisplayed() throws Exception {
        String str = verificationLogPage.getTableHeader();
        assertTrue(Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(")"))) > 0);
    }


    @Then("verify repository explorer link is visible")
    public void verifyRepositoryExplorerLinkIsVisible() throws Exception {
        assertTrue(verificationLogPage.getRepositoryExplorerLink().isDisplayed());
    }

    @Then("verify Response Code filter dropdown values")
    public void verifyResponseCodeFilterDropdownValues(List<String> expectedList) throws Exception {
        verificationLogPage.clickStatusCodeButton();
        List<String> actualList = new ArrayList<String>();

        for (WebElement actualCode : verificationLogPage.getStatusCodeList()) {
            actualList.add(actualCode.getText());
        }

        assertEquals(expectedList, actualList);

    }

    @And("{string} is set as default view on the verification log page")
    public void isSetAsDeafultView(String viewName) throws Exception {
        verificationLogPage.clickSelectViewDropDown();
        verificationLogPage.clickButton("Manage");
        verificationLogPage.inputText(verificationLogPage.viewSearchInput, viewName);
        verificationLogPage.clickElement(verificationLogPage.defaultViewChckBox);
        verificationLogPage.clickButton("Save");
    }

    @And("^get verification log data for filters$")
    public void getData() throws Exception {
        verificationLogPage.waitForPageToLoad();
        verificationLogPage.waitForTableToLoad();
        List<VerificationLog> actualLog = verificationLogPage.getLogs();
        List<VerificationLog> actualLog1 = verificationLogPage.getVrLogs();
        gtin = actualLog.get(0).getGtin();
        lot = actualLog.get(0).getLot();
        reqTime = actualLog.get(0).getRequestTime();
        expDate = actualLog.get(0).getExpirationDate();
        serialNumber = actualLog.get(0).getSerialNum();
        verified = actualLog.get(0).getVerified();
        requestorGLN = actualLog.get(0).getRequestorGln();
        responderGLN = actualLog.get(0).getResponderGln();
        additionalInfo = actualLog.get(0).getAdditionalInfo();
        uuid = actualLog1.get(0).getUUID();
        linkType = actualLog1.get(0).getLot();
        context = actualLog1.get(0).getContext();
    }

    @And("^get verification log data for filter for field$")
    public void getDataforField(String field) throws Exception {
        verificationLogPage.waitForPageToLoad();
        verificationLogPage.waitForTableToLoad();
        switch (field) {
            case "Request Time":
            case "GTIN":
            case "Lot/Batch Number ":
            case "Serial Number":
            case "Verified":
            case "Additional Information":
            case "Requester GLN":
            case "Responder GLN":
                List<VerificationLog> actualLog = verificationLogPage.getLogs();
                gtin = actualLog.get(0).getGtin();
                lot = actualLog.get(0).getLot();
                reqTime = actualLog.get(0).getRequestTime();
                expDate = actualLog.get(0).getExpirationDate();
                serialNumber = actualLog.get(0).getSerialNum();
                verified = actualLog.get(0).getVerified();
                requestorGLN = actualLog.get(0).getRequestorGln();
                responderGLN = actualLog.get(0).getResponderGln();
                additionalInfo = actualLog.get(0).getAdditionalInfo();
                break;
            case "Link Type":
            case "Context":
            case "UUID":
            case "Execution Duration (ms)":
                List<VerificationLog> actualLog1 = verificationLogPage.getVrLogs();
                uuid = actualLog1.get(0).getUUID();
                linkType = actualLog1.get(0).getLot();
                context = actualLog1.get(0).getContext();
                executionDuration = actualLog1.get(0).getExecutionDuration();
            default:
                System.out.println("Invalid Input!");
        }
    }


    @And("^get UUID, Link Type & Context verification log data for filters$")
    public void getUUIDLinkContextData() throws Exception {
        verificationLogPage.waitForPageToLoad();
        verificationLogPage.waitForTableToLoad();
        List<VerificationLog> actualLog = verificationLogPage.getVrLogs();
        uuid = actualLog.get(0).getUUID();
        linkType = actualLog.get(0).getLinkType();
        context = actualLog.get(0).getContext();
    }


    @And("^set '(.+)' filter value in verification logs page$")
    public void setFilterValueVerificationLogsPage(String field) throws Exception {
        verificationLogPage.waitForPageToLoad();
        if (field.equals("GTIN")) {
            verificationLogPage.enterGtin(gtin);
        }
        if (field.equals("lot")) {
            verificationLogPage.enterLot(lot);
        }
        if (field.equals("Request Date")) {
            verificationLogPage.enterRequestDate(StringUtils.substringBefore(reqTime, "T"));
        }
        if (field.equals("Verified")) {
            verificationLogPage.selectDropDownFilterInput(verificationLogPage.verifiedId, verified);
        }
        if (field.equals("Serial Number")) {
            verificationLogPage.enterSerialNumber(serialNumber);
        }
        if (field.equals("Additional Information")) {
            verificationLogPage.enterAdditionalInfo(additionalInfo);
        }
        if (field.equals("ResponderGln")) {
            verificationLogPage.enterResponderGln(responderGLN);
        }
        if (field.equals("UUID")) {
            verificationLogPage.enterUUID(uuid);
        }
        if (field.equals("Linktype")) {
            verificationLogPage.enterLinkType(linkType);
        }
        if (field.equals("Context")) {
            verificationLogPage.enterContext(context);
        }
        if (field.equals("Requester GLN")) {
            verificationLogPage.enterRequestorGln(requestorGLN);
        }
    }

    @And("select all filter fields")
    public void selectAllFilters() {
        List<WebElement> elements = verificationLogPage.getElements(By.xpath("//div[@class='sapMCbBg sapMCbHoverable sapMCbActiveStateOff sapMCbMark']"));
        elements.forEach(element -> {
            try {
                element.click();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @And("clear all filter fields in verification logs page")
    public void clearAllFilterFieldsInVerificationLogsPage() throws Exception {
        verificationLogPage.waitForTableToLoad();
        verificationLogPage.clearGtinValue();
        verificationLogPage.clearLotValue();
        verificationLogPage.clearSerialNumberValue();
        verificationLogPage.clearRequestorGlnValue();
        verificationLogPage.clearResponderGlnValue();
        verificationLogPage.clearAdditionalInfoValue();
        verificationLogPage.clearRequestDateValue();
        verificationLogPage.getElement(verificationLogPage.responseCodeCol).clear();
    }
}

