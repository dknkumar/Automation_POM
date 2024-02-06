package com.sap.cmoplatform.pages.manageOrganizationRelationships;

import com.sap.cmoplatform.pages.LauncherPage;
import com.sap.cmoplatform.pages.Page;
import groovy.util.logging.Slf4j;
import org.apache.poi.ss.usermodel.Row;import org.apache.poi.ss.usermodel.Sheet;import org.apache.poi.xssf.usermodel.XSSFWorkbook;import org.openqa.selenium.By;
import org.openqa.selenium.Keys;import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.FileInputStream;import java.io.IOException;import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage.getMonthNameInEnglish;


@Slf4j
public class ManageOrganizationRelationshipsPage extends Page {
  public int messageCount;
  public static final By inviterGLNFieldInDetailPage=By.xpath("//div[contains(@id, 'Invitations--orgDetailsView--inviterCodeField-input-content')]"); public static final By inviteeGLNFieldInDetailPage=By.xpath("//div[contains(@id, 'Invitations--orgDetailsView--inviteeCodeField-input-content')]"); public static final By inviterRoleFieldInDetailPage=By.xpath("//div[contains(@id, 'Invitations--orgDetailsView--inviterRoleField-input-content')]"); public static final By inviteeRoleFieldInDetailPage=By.xpath("//div[contains(@id, 'Invitations--orgDetailsView--inviteeRoleField-input-content')]");public final By adaptFilterListNamePath =
      By.xpath(".//span[@class = 'sapMLabel sapUiSelectable sapMLabelWrapped sapMLabelMaxWidth']");
  private LauncherPage launcherPage = new LauncherPage();
  public final By organizationListReportSearchFieldPath =
      By.xpath("//input[contains(@id, 'Organizations--listReportFilter-btnBasicSearch-I')]");
  public final By organizationListReportTablePath =
      By.xpath("//table[contains(@id ,'Organizations--responsiveTable-listUl')]");
  public final By organizationPNIDPath = By.className("sapMObjectIdentifierTopRow");
  public final By organizationDetailPageTitle =
      By.xpath("//span[@class = 'sapUshellAppTitleText']");
  public final By detailPageRelationshipSectionPath =
      By.xpath("//div[contains(@id, 'Section-title') and text()= 'Relationship']");
  public final By filterElementPath =
      By.xpath("//div[contains(@id, 'Invitations--listReportFilter')]");
  public final By relationshipListReportTablePath =
      By.xpath("//table[contains(@id, 'Invitations--responsiveTable-listUl')]");
  public final By createButtonPath = By.xpath("//button[contains(@id, 'Invitations--addEntry')]");
  public final By downloadButtonPath =
      By.xpath(
          "//button[contains(@id, 'Invitations--listReport-btnExcelExport-internalSplitBtn-textButton')]");
  public final By settingButtonPath =
      By.xpath("//button[contains(@id, 'Invitations--listReport-btnPersonalisation')]");
  public final By adaptFilterButtonPath =
      By.xpath("//button[contains(@id, 'Invitations--listReportFilter-btnFilters')]");
  public final By scenarioDetailPageDropDownArrow =
      By.xpath(
          "//span[contains(@id, 'Invitations--orgDetailsView--scenarioField-comboBoxEdit-arrow') and @role = 'button']");
  public final By scenarioDetailPageDropDownValueTitlesPath =
      By.xpath("//li[contains(@class, 'sapMComboBoxBaseNonInteractiveItem') and @role = 'option']");
  public final By adaptFilterSearchButton = By.xpath("//div[contains(@id,'searchField-search')]");
  public final By adaptFilterInputPath = By.xpath("//input[contains(@id,'searchField-I')]");
  public final By adaptFilterTableFiltersNamePath =
      By.xpath("//span[@class='sapMLabel sapUiSelectable sapMLabelWrapped sapMLabelMaxWidth']");
  public final By messageTypeDropDownArrowPath =
      By.xpath(
          "//span[contains(@id, 'Invitations--CustomOrgRelMessageTypeFilter-combobox-arrow') and @role = 'button']");
  public final By sendReceiveMessageDropDownArrowPath =
      By.xpath(
          "//span[contains(@id, 'Invitations--CustomOrgRelMessageServiceFilter-combobox-arrow') and @role = 'button']");
  public final By messageTypeDropDownValueTitlesPath =
      By.xpath("//div[@class = 'sapMSLITitleOnly']");
  public final By changedOnFilterCalendarButtonPath =
      By.xpath(
          "//span[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-modifiedAt-input') and @role = 'button']");
  public final By createdOnFilterCalendarButtonPath =
      By.xpath(
          "//span[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-createdAt-input') and @role = 'button']");
  public final By changedOnCalendarFromToButton =
      By.xpath(
          "//li[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-modifiedAt-option-DATERANGE') and @role = 'listitem']");
  public final By createdOnCalendarFromToButton =
      By.xpath(
          "//li[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-createdAt-option-DATERANGE') and @role = 'listitem']");
  public final By yearButtonOnCalendar = By.xpath("//button[contains(@id, 'Head-B2')]");
  public final By monthButtonOnCalendar = By.xpath("//button[contains(@id, 'Head-B1')]");
  public final By yearListOnCalendar = By.xpath("//div[contains(@id, 'YP-y')]");
  public final By monthListOnCalendar = By.xpath("//div[contains(@id, 'MP-m')]");
  public final By dayListOnCalendar =
      By.xpath("//div[contains(@class, 'sapUiCalItem sapUiCalWDay')]");
  public final By changedOnListPath =
      By.xpath("//td[contains(@data-sap-ui-column, 'Invitations--listReport-modifiedAt')]");
  private String timestampInputType;
  public final By changedOnInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-modifiedAt')]");
  public final By createdOnInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-createdAt')]");
  public final By inviterGLNFilterInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviterCode')]");
  public final By inviteeGLNFilterInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviteeCode')]");
  public final By inviterOrganizationFilterInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviterPnid')]");
  public final By inviteeOrganizationFilterInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviteePnid')]");
  public final By GLNValueListPath =
      By.xpath(
          "//span[contains(@class, 'sapMText sapUiSelectable sapMTextMaxWidth') and contains(text(),'GLN')]");
  public final By PNIDValueListPath = By.xpath("//div[@class = 'sapMObjectIdentifierTitle']");
  public final By inviterOrganizationFilterButtonPath =
      By.xpath(
          "//span[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviterPnid') and @role = 'button']");
  public final By inviteeOrganizationFilterButtonPath =
      By.xpath(
          "//span[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviteePnid') and @role = 'button']");
  public final By inviterGLNFilterButtonPath =
      By.xpath("//span[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviterCode') and @role = 'button']");
  public final By inviteeGLNFilterButtonPath =
      By.xpath(
          "//span[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviteeCode') and @role = 'button']");
  public final By inviterOrganizationFilterSearchInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviterPnid-valueHelpDialog-smartFilterBar-btnBasicSearch')]");
  public final By inviteeOrganizationFilterSearchInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviteePnid-valueHelpDialog-smartFilterBar-btnBasicSearch')]");
  public final By inviteeGLNFilterSearchInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviteeCode-valueHelpDialog-smartFilterBar-btnBasicSearch')]");
  public final By inviterGLNFilterSearchInputPath =
      By.xpath(
          "//input[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviterCode-valueHelpDialog-smartFilterBar-btnBasicSearch')]");
  public final By inviterOrganizationSearchValueResultList =
      By.xpath(
          "//tr[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviterPnid-valueHelpDialog-table-rows')]");
  public final By inviteeOrganizationSearchValueResultList =
      By.xpath(
          "//tr[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviteePnid-valueHelpDialog-table-rows')]");
  public final By inviterGLNSearchValueResultList =
      By.xpath(
          "//td[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviterCode-valueHelpDialog-table-rows')]");
  public final By inviteeGLNSearchValueResultList =
      By.xpath(
          "//td[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-inviteeCode-valueHelpDialog-table-rows')]");
  public final By sendReceiveMessageDropDownValueInfosPath =
      By.xpath("//div[@class = 'sapMSLIDiv sapMSLIInfoMiddle']");
  public final By sendReceiveMessageDropDownValueTitlesPath =
      By.xpath("//div[@class = 'sapMSLITitleOnly']");
  public final By listPageTitlePath = By.xpath("//span[@class = 'sapUshellAppTitleText']");
  public final By messageTypeDropDownValueInfosPath =
      By.xpath("//div[@class = 'sapMSLIDiv sapMSLIInfoMiddle']");
  public final By sendReceiveValueListPath = By.xpath("//span[@class = 'sapMExTextString']");
  public final By messageTypeValueListPath = By.xpath("//span[@class = 'sapMExTextString']");
  public final By scenarioValueListPath =
      By.xpath("//td[contains(@data-sap-ui-column, 'Invitations--listReport-scenario_name')]");
  public final By createdOnListPath =
      By.xpath("//td[contains(@data-sap-ui-column, 'Invitations--listReport-createdAt')]");
  public final By adaptFilterRows =
      By.xpath(
          "//tr[contains(@class,'sapMLIB sapMLIB-CTX sapMLIBShowSeparator') and @data-sap-ui-draggable]");
  public final By adaptFilterRemoveAllButton = By.xpath("//span[@title = 'Deselect All']");
  public final By listReportFilterTitlePath =
      By.xpath("//bdi[contains(@id,'Invitations--listReportFilter-filterItem')]");
  public final By scenarioListReportPageDropDownArrow =
      By.xpath(
          "//span[contains(@id, 'Invitations--listReportFilter-filterItemControl_BASIC-scenario_ID-arrow') and @role = 'button']");
  public final By scenarioListReportDropDownValueTitlesPath =
      By.xpath("//div[@class = 'sapMSLITitleOnly']");
  public final By relationshipsListReportTitlePath =
      By.xpath("//span[contains(@id, 'Invitations--listReport-header-inner')]");
  public final By downloadIconPath =
      By.xpath(
          "//span[contains(@id, 'Invitations--listReport-btnExcelExport-internalSplitBtn-textButton-img')]");
  public final By settingIconPath =
      By.xpath("//button[contains(@id, 'Invitations--listReport-btnPersonalisation')] ");
  public final By settingSelectAllCheckbox =
      By.xpath("//div[contains(@id, 'innerSelectionPanelTable-sa-CbBg')] ");
  public final By listReportColumnPath =
      By.xpath("//div[contains(@class,'sapMColumnHeader sapMColumnHeaderActive')]");
  public final By inviterOrganizationSelectorInDetailPage =
      By.xpath(
          "//span[contains(@id, 'Invitations--orgDetailsView--inviterPnidField-input') and @role = 'button']");
  public final By inviterOrganizationSearchFieldInDetailPage =
      By.xpath(
          "//input[contains(@id, 'Invitations--orgDetailsView--inviterPnidField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-I')]");
  public final By inviterOrganizationSearchButtonInDetailPage =
      By.xpath(
          "//div[contains(@id, 'Invitations--orgDetailsView--inviterPnidField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-search')]");
  public final By inviterOrganizationSearchResultListInDetailPage =
      By.xpath(
          "//tr[contains(@id, 'Invitations--orgDetailsView--inviterPnidField-input-valueHelpDialog-table-rows')]");
  public final By inviteeOrganizationSelectorInDetailPage =
      By.xpath(
          "//span[contains(@id, 'Invitations--orgDetailsView--inviteePnidField-input') and @role = 'button']");
  public final By inviteeOrganizationSearchFieldInDetailPage =
      By.xpath(
          "//input[contains(@id, 'Invitations--orgDetailsView--inviteePnidField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-I')]");
  public final By inviteeOrganizationSearchButtonInDetailPage =
      By.xpath(
          "//div[contains(@id, 'Invitations--orgDetailsView--inviteePnidField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-search')]");
  public final By inviteeOrganizationSearchResultListInDetailPage =
      By.xpath(
          "//tr[contains(@id, 'Invitations--orgDetailsView--inviteePnidField-input-valueHelpDialog-table-rows')]");
  public final By inviterGLNSelectorInDetailPage =
      By.xpath(
          "//span[contains(@id, 'Invitations--orgDetailsView--inviterCodeField-input') and @role = 'button']");
  public final By inviterGLNSearchFieldInDetailPage =
      By.xpath(
          "//input[contains(@id, 'Invitations--orgDetailsView--inviterCodeField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-I')]");
  public final By inviterGLNSearchButtonInDetailPage =
      By.xpath(
          "//div[contains(@id, 'Invitations--orgDetailsView--inviterCodeField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-search')]");
  public final By inviterGLNSearchResultListInDetailPage =
      By.xpath(
          "//td[contains(@id, 'Invitations--orgDetailsView--inviterCodeField-input-valueHelpDialog-table-rows')]");
  public final By inviteeGLNSelectorInDetailPage =
      By.xpath(
          "//span[contains(@id, 'Invitations--orgDetailsView--inviteeCodeField-input') and @role = 'button']");
  public final By inviteeGLNSearchFieldInDetailPage =
      By.xpath(
          "//input[contains(@id, 'Invitations--orgDetailsView--inviteeCodeField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-I')]");
  public final By inviteeGLNSearchButtonInDetailPage =
      By.xpath(
          "//div[contains(@id, 'Invitations--orgDetailsView--inviteeCodeField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-search')]");
  public final By inviteeGLNSearchResultListInDetailPage =
      By.xpath(
          "//td[contains(@id, 'Invitations--orgDetailsView--inviteeCodeField-input-valueHelpDialog-table-rows')]");
  public final By inviterRoleSelectorInDetailPage =
      By.xpath(
          "//span[contains(@id, 'Invitations--orgDetailsView--inviterRoleField-input') and @role = 'button']");
  public final By inviterRoleSearchFieldInDetailPage =
      By.xpath(
          "//input[contains(@id, 'Invitations--orgDetailsView--inviterRoleField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-I')]");
  public final By inviterRoleSearchButtonInDetailPage =
      By.xpath(
          "//div[contains(@id, 'Invitations--orgDetailsView--inviterRoleField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-search')]");
  public final By inviterRoleSearchResultListInDetailPage =
      By.xpath(
          "//tr[contains(@id, 'Invitations--orgDetailsView--inviterRoleField-input-valueHelpDialog-table-rows')]");
  public final By inviteeRoleSelectorInDetailPage =
      By.xpath(
          "//span[contains(@id, 'Invitations--orgDetailsView--inviteeRoleField-input') and @role = 'button']");
  public final By inviteeRoleSearchFieldInDetailPage =
      By.xpath(
          "//input[contains(@id, 'Invitations--orgDetailsView--inviteeRoleField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-I')]");
  public final By inviteeRoleSearchButtonInDetailPage =
      By.xpath(
          "//div[contains(@id, 'Invitations--orgDetailsView--inviteeRoleField-input-valueHelpDialog-smartFilterBar-btnBasicSearch-search')]");
  public final By inviteeRoleSearchResultListInDetailPage =
      By.xpath(
          "//tr[contains(@id, 'Invitations--orgDetailsView--inviteeRoleField-input-valueHelpDialog-table-rows')]");
  public final By inviterPNIDWithGLNPath = By.xpath("//td[contains(@id,'Invitations--template:::ListReportTable:::ColumnListItem') and @data-sap-ui-column = '__column0']");
  public final By inviteePNIDWithGLNPath = By.xpath("//td[contains(@id,'Invitations--template:::ListReportTable:::ColumnListItem') and @data-sap-ui-column = '__column1']");
  public final By errorMessagePath = By.xpath("//span[@class = 'sapMText sapUiSelectable sapMTextMaxWidth sapMMsgViewTitleText']");
  public final By saveButton = By.xpath("//button[contains(@id,'Invitations--save')]");
  public final By messageOrganizationSearchField = By.xpath("//input[contains(@id,'Organizations--listReportFilter-btnBasicSearch-I')]");
  public final By messageOrganizationStatusArrowInDetailPage = By.xpath("//span[contains(@id ,'status_ID::Field-comboBoxEdit-arrow') and @role = 'button']");
  public final By messageOrganizationStatusValueListInDetailPage = By.xpath("//li[@role = 'option']");
  public final By messageOrganizationStatusPathInListReport = By.xpath("//div[contains(@class,'sapMObjStatus')]");
  public final By organizationStatusInDetailPage = By.xpath("//span[contains(@id, 'status_ID::Value-objStatus-text')]");
  public final By  inviterOrganizationFieldInDetailPage =
          By.xpath(
          "//div[contains(@id, 'Invitations--orgDetailsView--inviterPnidField-input-content')]");
  public final By  inviteeOrganizationFieldInDetailPage = By.xpath("//div[contains(@id, 'Invitations--orgDetailsView--inviteePnidField-input-content')]");
  public final By messageTypeFieldInDetailPage = By.xpath("//div[contains(@id, 'Invitations--orgRelationship--messageTypeStringComboBox-content')]");
  public final By organizationRelationshipsListReportTablePath = By.xpath("//table[contains(@id, 'Invitations--responsiveTable-listUl')]");
  public final By relationshipsFiltersPath = By.xpath("//div[contains(@id, 'Invitations--listReportFilter') and contains(@data-sap-ui, 'Invitations--listReportFilter')]");
  public final By alertDialogPath = By.xpath("//div[@role= 'alertdialog']");


  public void inputValueInSearchFilter(String pnid) throws Exception {
    inputText(organizationListReportSearchFieldPath, pnid);
  }

  public List<String> getListReportPNID() throws Exception {
    waitForTableToLoad();
    List<String> PNIDs =
        getTableRows(organizationListReportTablePath).stream()
            .map(list -> list.findElement(organizationPNIDPath).getText())
            .collect(Collectors.toList());
    return PNIDs;
  }

  public void openFirstMessage() throws Exception {
    List<WebElement> listReportTableRows = getTableRows(organizationListReportTablePath);
    listReportTableRows.get(0).click();
    waitForPageToLoad();
    waitForTableToLoad();
  }
  //    public boolean
  // manageOrganizationRelationshipsLinkShouldBeDisplayedUnderRelationshipSection()throws Exception
  // {
  //       scrollToElement(detailPageRelationshipSectionPath);
  //       return getElement().isDisplayed();
  //    }
  public boolean variousFilterFieldShouldBeDisplayedOnOverviewPage(String fieldName)
      throws Exception {
    switch (fieldName) {
      case "various filter":
        return getElements(filterElementPath).size() > 0;
      case "table columns":
        return getElement(relationshipListReportTablePath).isDisplayed();
      case "create button":
        return getElement(createButtonPath).isDisplayed();
      case "setting button":
        return getElement(settingButtonPath).isDisplayed();
      case "download button":
        return getElement(downloadButtonPath).isDisplayed();
      case "adapt filter button":
        return getElement(adaptFilterButtonPath).isDisplayed();
    }
    return true;
  }

  public boolean scenarioDropDownShouldBeDisplayedWithRespectiveValues() {
    return getElements(scenarioDetailPageDropDownValueTitlesPath).size() > 0;
  }



  public void searchForFilterInAdaptFilters(String filterName) throws Exception {
    inputText(adaptFilterInputPath, filterName);
  }

  public List<String> getAdaptFilterTableFilterNames() {
    return getElements(adaptFilterTableFiltersNamePath).stream()
        .map(filter -> filter.getText())
        .collect(Collectors.toList());
  }

  public void clickOnFilterDropDown(String filterName) throws Exception {
    if (filterName.equals("Message Type")) {
      getElement(messageTypeDropDownArrowPath).click();
    } else if (filterName.equals("Send/Receive Message")) {
      getElement(sendReceiveMessageDropDownArrowPath).click();
    }
  }

  public boolean dropDownShouldHaveFollowingValues(String fieldName, List<String> dropdownValues) {
    List<String> titles = new ArrayList<>();
    if (fieldName.equals("Message Type")) {
      titles =
          getElements(messageTypeDropDownValueTitlesPath).stream()
              .map(v -> v.getText())
              .collect(Collectors.toList());
    } else if (fieldName.equals("Send/Receive Message")) {
      titles =
          getElements(sendReceiveMessageDropDownValueTitlesPath).stream()
              .map(v -> v.getText())
              .collect(Collectors.toList());
    }
    return titles.containsAll(dropdownValues);
  }

  public void clickOnCalendarInFilterField(String filterName) throws Exception {
    if (filterName.equals("Changed on")) {
      getElement(changedOnFilterCalendarButtonPath).click();
    } else if (filterName.equals("Created on")) {
      getElement(createdOnFilterCalendarButtonPath).click();
    }
  }

  public void selectLastUpdateTimeFromDateAndToDate(
      String fromDate, String toDate, String fieldName) throws Exception {
    timestampInputType = "Calendar";
    By filterCalendarButtonPath = null;
    By calendarFromToButton = null;
    if (fieldName.equals("Changed on")) {
      filterCalendarButtonPath = changedOnFilterCalendarButtonPath;
      calendarFromToButton = changedOnCalendarFromToButton;
    } else if (fieldName.equals("Created on")) {
      filterCalendarButtonPath = createdOnFilterCalendarButtonPath;
      calendarFromToButton = createdOnCalendarFromToButton;
    }
    getElement(filterCalendarButtonPath).click();
    waitForElementToLoad();
    List<String> fromTime = parseCalendarDate(fromDate);
    List<String> toTime = parseCalendarDate(toDate);
    scrollToElement(calendarFromToButton);
    waitForElementToLoad();
    getElement(calendarFromToButton).click();
    selectDateOnCalendar(fromTime);
    selectDateOnCalendar(toTime);
    clickButton("Apply");
  }

  private void selectDateOnCalendar(List<String> time) throws Exception {
    getElement(yearButtonOnCalendar).click();
    getElements(yearListOnCalendar).stream()
        .filter(y -> y.getText().equals(time.get(0)))
        .findFirst()
        .get()
        .click();
    getElement(monthButtonOnCalendar).click();
    getElements(monthListOnCalendar).stream()
        .filter(y -> y.getText().equals(time.get(1)))
        .findFirst()
        .get()
        .click();
    getElements(dayListOnCalendar).stream()
        .filter(y -> y.getText().equals(time.get(2)))
        .findFirst()
        .get()
        .click();
  }

  private List<String> parseCalendarDate(String dateString) {
    LocalDate date = LocalDate.parse(dateString);
    String year = String.valueOf(date.getYear());
    String month = getMonthNameInEnglish(date);
    String day = String.valueOf(date.getDayOfMonth());
    return List.of(year, month, day);
  }

  public boolean dataShouldBeDisplayedWithFieldBetweenDate(
      String fieldName, String fromDate, String toDate) throws Exception {
    waitForTableToLoad();
    List<String> actualDates = new ArrayList<>();
    if (fieldName.equals("Changed on")) {
      actualDates =
          getElements(changedOnListPath).stream()
              .map(c -> c.getText())
              .collect(Collectors.toList());
    } else if (fieldName.equals("Created on")) {
      actualDates =
          getElements(createdOnListPath).stream()
              .map(c -> c.getText())
              .collect(Collectors.toList());
    }
    LocalDate[] expectedDates = parseDate(fromDate, toDate);
    LocalDate fromDateTime = expectedDates[0];
    LocalDate toDateTime = expectedDates[1];
    int i = 0;
    while (i < actualDates.size()) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy, h:mm:ss a");
      LocalDateTime dateToCheck = LocalDateTime.parse(actualDates.get(i), formatter);
      if (dateToCheck.toLocalDate().isBefore(fromDateTime)
          || dateToCheck.toLocalDate().isAfter(toDateTime)) return false;
      i++;
    }
    return true;
  }

  public LocalDate[] parseDate(String fromDate, String toDate) {
    LocalDate fromDateTime = null;
    LocalDate toDateTime = null;
    if (timestampInputType.equals("Calendar")) {
      fromDateTime = LocalDate.parse(fromDate);
      toDateTime = LocalDate.parse(toDate);
    } else if (timestampInputType.equals("Input")) {
      fromDateTime = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("MMM d, yyyy"));
      toDateTime = LocalDate.parse(toDate, DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }
    return new LocalDate[] {fromDateTime, toDateTime};
  }

  public void inputDateInFieldWithDate(String fieldName, String fromDate, String toDate)
      throws Exception {
    waitForTableToLoad();
    timestampInputType = "Input";
    String inputDate = fromDate + " - " + toDate;
    By inputField = null;
    if (fieldName.equals("Changed on")) {
      inputField = changedOnInputPath;
    } else if (fieldName.equals("Created on")) {
      inputField = createdOnInputPath;
    }
    inputText(inputField, inputDate);
  }

  public void inputValueInFilter(String fieldValue, String fieldName) throws Exception {
    By inputField = null;
    switch (fieldName) {
      case "Inviter GLN":
        inputField = inviterGLNFilterInputPath;
        break;
      case "Invitee GLN":
        inputField = inviteeGLNFilterInputPath;
        break;
      case "Inviter Organization":
        inputField = inviterOrganizationFilterInputPath;
        break;
      case "Invitee Organization":
        inputField = inviteeOrganizationFilterInputPath;
        break;
    }
    inputText(inputField, fieldValue);
    getElement(inputField).sendKeys(Keys.ENTER);
  }

  public boolean dataShouldBeDisplayedWithfieldValue(String fieldName, String fieldValue)
      throws Exception {
    waitForTableToLoad();
    List<String> tableFieldList = new ArrayList<>();
    switch (fieldName) {
      case "Inviter GLN":
        List<String> GLNlist =
            getElements(GLNValueListPath).stream()
                .map(g -> g.getText().replace("GLN: ", ""))
                .collect(Collectors.toList());
        GLNlist.remove(0);
        for (int i = 0; i < GLNlist.size(); i++) {
          if (i % 2 == 0) tableFieldList.add(GLNlist.get(i));
        }
        break;
      case "Invitee GLN":
        List<String> GLNlist2 =
            getElements(GLNValueListPath).stream()
                .map(g -> g.getText().replace("GLN: ", ""))
                .collect(Collectors.toList());
        GLNlist2.remove(0);
        for (int i = 0; i < GLNlist2.size(); i++) {
          if (i % 2 != 0) tableFieldList.add(GLNlist2.get(i));
        }
        break;
      case "Inviter Organization":
        List<String> PNIDList =
            getElements(PNIDValueListPath).stream()
                .map(g -> g.getText())
                .collect(Collectors.toList());
        for (int i = 0; i < PNIDList.size(); i++) {
          if (i % 2 == 0) tableFieldList.add(PNIDList.get(i));
        }
        break;
      case "Invitee Organization":
        List<String> PNIDList2 =
            getElements(PNIDValueListPath).stream()
                .map(g -> g.getText())
                .collect(Collectors.toList());
        for (int i = 0; i < PNIDList2.size(); i++) {
          if (i % 2 != 0) tableFieldList.add(PNIDList2.get(i));
        }
        break;
      case "Scenario":
        tableFieldList =
            getElements(scenarioValueListPath).stream()
                .map(g -> g.getText())
                .collect(Collectors.toList());
        break;
      case "Send/Receive Message":
        List<String> sendReceiveValueList =
            getElements(sendReceiveValueListPath).stream()
                .map(g -> g.getText())
                .collect(Collectors.toList());
        for (int i = 0; i < sendReceiveValueList.size(); i++) {
          if (i % 2 == 0) tableFieldList.add(sendReceiveValueList.get(i));
        }
        fieldValue = fieldValue.split(", ")[0];
        break;
      case "Message Type":
        List<String> messageTypeValueList =
            getElements(messageTypeValueListPath).stream()
                .map(g -> g.getText())
                .collect(Collectors.toList());
        for (int i = 0; i < messageTypeValueList.size(); i++) {
          if (i % 2 != 0) tableFieldList.add(messageTypeValueList.get(i));
        }
        fieldValue = fieldValue.split(", ")[0];
        break;
    }
    for (String v : tableFieldList) {
      if (!v.equals(fieldValue)) return false;
    }
    return true;
  }

  public void selectValueInFilter(String filterValue, String fieldName) throws Exception {
    WebElement fieldSelectButton = null;
    By inputField = null;
    By filterSearchValueResultList = null;
    switch (fieldName) {
      case "Inviter Organization":
        fieldSelectButton = getElement(inviterOrganizationFilterButtonPath);
        inputField = inviterOrganizationFilterSearchInputPath;
        filterSearchValueResultList = inviterOrganizationSearchValueResultList;
        break;
      case "Invitee Organization":
        fieldSelectButton = getElement(inviteeOrganizationFilterButtonPath);
        inputField = inviteeOrganizationFilterSearchInputPath;
        filterSearchValueResultList = inviteeOrganizationSearchValueResultList;
        break;
      case "Inviter GLN":
        fieldSelectButton = getElement(inviterGLNFilterButtonPath);
        inputField = inviterGLNFilterSearchInputPath;
        filterSearchValueResultList = inviterGLNSearchValueResultList;
        break;
      case "Invitee GLN":
        fieldSelectButton = getElement(inviteeGLNFilterButtonPath);
        inputField = inviteeGLNFilterSearchInputPath;
        filterSearchValueResultList = inviteeGLNSearchValueResultList;
        break;
    }
    fieldSelectButton.click();
    inputText(inputField, filterValue);
    clickButton("Go");
    waitForElementToLoad();
    getElements(filterSearchValueResultList).get(0).click();
    clickButton("OK");
  }

  public void selectInFilterDropDown(String filterValue, String fieldName) throws Exception {
    waitForPageToLoad();
    waitForTableToLoad();
    By dropdownButtonPath = null;
    By dropdownOptions = null;
    switch (fieldName) {
      case "Scenario":
        dropdownButtonPath = scenarioListReportPageDropDownArrow;
        dropdownOptions = scenarioListReportDropDownValueTitlesPath;
        break;
      case "Send/Receive Message":
        dropdownButtonPath = sendReceiveMessageDropDownArrowPath;
        dropdownOptions = sendReceiveMessageDropDownValueInfosPath;
        break;
      case "Message Type":
        dropdownButtonPath = messageTypeDropDownArrowPath;
        dropdownOptions = messageTypeDropDownValueInfosPath;
        break;
    }
    getElement(dropdownButtonPath).click();
    waitForElementToLoad();
    getElements(dropdownOptions).stream()
        .filter(o -> o.getText().replace("\n", ", ").equals(filterValue))
        .findFirst()
        .get()
        .click();
  }

  private void waitForElementToLoad() throws Exception {
    portalDriver.waitFor(2000);
    portalDriver.waitForLoad();
  }

  public String theListPageIsDisplayedOnThePage(String pageName) throws Exception {
    waitForPageToLoad();
    waitForTableToLoad();
    launcherPage.switchToDefaultContent();
    String title = getElement(listPageTitlePath).getText();
    if(pageName.equals("Manage Organization Relationships")){
      launcherPage.switchToFrame("ManageOrganizationRelationships");
    } else if (pageName.equals("Manage Organizations")) {
      launcherPage.switchToFrame("ManageOrganizations");
    }
    return title;
  }

  public void selectFilterOptionsInAdaptFilter(List<String> filterNames) throws Exception {
    getElement(adaptFilterRemoveAllButton).click();
    List<WebElement> filters =
        getElements(adaptFilterRows).stream()
            .filter(f -> filterNames.contains(f.findElement(adaptFilterListNamePath).getText()))
            .map(f -> f.findElement(By.xpath(".//div[@role = 'checkbox']")))
            .collect(Collectors.toList());
    for (WebElement filter : filters) {
      filter.click();
    }
  }

  public boolean itWillShowAllTheFiltersOnTheOverviewPage(List<String> filterNames) {
    List<String> filterList =
        getElements(listReportFilterTitlePath).stream()
            .map(f -> f.getText().replace(":", ""))
            .collect(Collectors.toList());
    return filterList.containsAll(filterNames) && filterList.size() == filterNames.size();
  }

  public boolean messageCountInListReportShouldMatch() throws Exception {
    int realCount = getListReportMessageCount();
    int displayedCount = getMessageCount();
    return realCount == displayedCount;
  }

  private int getListReportMessageCount() {
    return getTableRows(organizationRelationshipsListReportTablePath).size();
  }

  public int getMessageCount() throws Exception {
    WebElement tableTitle = getElement(relationshipsListReportTitlePath);
    String regex = "\\((\\d+)\\)";
    int messageCount;
    Pattern pattern = Pattern.compile(regex);
    waitForElementToLoad();
    String tableTitleString = tableTitle.getText().replaceAll(",", "");
    Matcher matcher = pattern.matcher(tableTitleString);
    if (matcher.find()) {
      messageCount = Integer.parseInt(matcher.group(1));
    } else {
      throw new Exception("Message count not loaded");
    }
    return messageCount;
  }

  public void clickOnExportToSpreadsheetIcon() throws Exception {
    getElement(downloadIconPath).click();
    portalDriver.waitFor(8000);
  }

  public boolean userShouldBeAbleToDownloadTheResult(String suffix) {
    String fileName = "Relationships" + suffix;
    return checkDownloadFileExist(fileName);
  }

  public boolean checkDownloadFileExist(String downloadFileName) {
    String filePath =
        System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
    File folder = new File(filePath);
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
      if (file.isFile()) {
        String fileName = file.getName();
        if (fileName.equals(downloadFileName)) {
          file.delete();
          return true;
        }
      }
    }
    return false;
  }

  public void clickOnSettingIcon() throws Exception {
    getElement(settingIconPath).click();
  }

  public void selectFieldsInSettingPopover(List<String> fields) throws Exception {
    clearSettingSelection();
    List<WebElement> settingList = getElements(By.xpath("//td[contains(@id, 'innerSelectionPanelTable')]")).stream().filter(e -> fields.contains(e.getText())).collect(Collectors.toList());
    settingList.forEach(s -> s.findElement(By.xpath("preceding-sibling::*[1]")).click());

  }

  private void clearSettingSelection() throws Exception {
    getElement(settingSelectAllCheckbox).click();
    getElement(settingSelectAllCheckbox).click();
  }

  public List<String> getListReportColumns() {
    return getElements(listReportColumnPath).stream()
        .map(f -> f.getText().replace("\u00AD",""))
        .collect(Collectors.toList());
  }

  public void selectAllFieldsInSettingPopover() throws Exception {
    getElement(settingSelectAllCheckbox).click();
  }

  public boolean createPageShouldOpen() throws Exception {
    waitForPageToLoad();
    return getPageTitle().equals("Relationship");
  }

  public void selectInScenarioDropDownInDetailPage(String scenarioName) throws Exception {
    waitForElementToLoad();
    getElements(scenarioDetailPageDropDownValueTitlesPath).stream()
        .filter(e -> e.getText().equals(scenarioName))
        .findFirst()
        .get()
        .click();
  }

  public void selectInDetailPage(String fieldValue, String fieldName) throws Exception {
    By selectorInDetailPage = null;
    By searchFieldInDetailPage = null;
    By searchButtonInDetailPage = null;
    By searchResultListInDetailPage = null;
    switch (fieldName) {
      case "Inviter Organization":
        selectorInDetailPage = inviterOrganizationSelectorInDetailPage;
        searchFieldInDetailPage = inviterOrganizationSearchFieldInDetailPage;
        searchButtonInDetailPage = inviterOrganizationSearchButtonInDetailPage;
        searchResultListInDetailPage = inviterOrganizationSearchResultListInDetailPage;
        break;
      case "Invitee Organization":
        selectorInDetailPage = inviteeOrganizationSelectorInDetailPage;
        searchFieldInDetailPage = inviteeOrganizationSearchFieldInDetailPage;
        searchButtonInDetailPage = inviteeOrganizationSearchButtonInDetailPage;
        searchResultListInDetailPage = inviteeOrganizationSearchResultListInDetailPage;
        break;
      case "Inviter GLN":
        selectorInDetailPage = inviterGLNSelectorInDetailPage;
        searchFieldInDetailPage = inviterGLNSearchFieldInDetailPage;
        searchButtonInDetailPage = inviterGLNSearchButtonInDetailPage;
        searchResultListInDetailPage = inviterGLNSearchResultListInDetailPage;
        break;
      case "Invitee GLN":
        selectorInDetailPage = inviteeGLNSelectorInDetailPage;
        searchFieldInDetailPage = inviteeGLNSearchFieldInDetailPage;
        searchButtonInDetailPage = inviteeGLNSearchButtonInDetailPage;
        searchResultListInDetailPage = inviteeGLNSearchResultListInDetailPage;
        break;
      case "Inviter Role":
        selectorInDetailPage = inviterRoleSelectorInDetailPage;
        searchFieldInDetailPage = inviterRoleSearchFieldInDetailPage;
        searchButtonInDetailPage = inviterRoleSearchButtonInDetailPage;
        searchResultListInDetailPage = inviterRoleSearchResultListInDetailPage;
        break;
      case "Invitee Role":
        selectorInDetailPage = inviteeRoleSelectorInDetailPage;
        searchFieldInDetailPage = inviteeRoleSearchFieldInDetailPage;
        searchButtonInDetailPage = inviteeRoleSearchButtonInDetailPage;
        searchResultListInDetailPage = inviteeRoleSearchResultListInDetailPage;
        break;
    }
    getElement(selectorInDetailPage).click();
    inputText(searchFieldInDetailPage, fieldValue);
    getElement(searchButtonInDetailPage).click();
    waitForElementToLoad();
    getElements(searchResultListInDetailPage).get(0).click();
  }
  public boolean checkDownloadFile(String downloadFileName) throws Exception {
    List<String> inviterPNIDValueList = getListSubValue("Inviter PNID / GLN");
    List<String> inviteePNIDValueList = getListSubValue("Invitee PNID / GLN");
    List<String>[] result = getDownloadFileContent(downloadFileName);
    boolean flag = true;
    if (inviterPNIDValueList.size() != result[0].size()) flag = false;
    for (int i = 0; i < inviterPNIDValueList.size(); i++) {
      if (!inviterPNIDValueList.get(i).equals(result[0].get(i))) {
        flag = false;
        break;
      }
    }
    if (inviteePNIDValueList.size() != result[1].size()) flag = false;
    for (int i = 0; i < inviteePNIDValueList.size(); i++) {
      if (!inviteePNIDValueList.get(i).equals(result[1].get(i))) {
        flag = false;
        break;
      }
    }
    return flag;
  }
  public static List<String>[] readExcel(File file) throws Exception {
    List<String> inviterPNID = new ArrayList<>();
    List<String> inviteePNID = new ArrayList<>();
    FileInputStream stream = null;
    XSSFWorkbook workbook = null;
    List[] result=new List[0];
    try {
      stream = new FileInputStream(file);

      workbook = new XSSFWorkbook(stream);

      Sheet sheet = workbook.getSheetAt(0);

      int rowNum = sheet.getPhysicalNumberOfRows();
      Row row;

      for (int i = 1; i < rowNum; i++) {
        row = sheet.getRow(i);
        if (row != null) {
          String inviterPNIDValue = row.getCell(0).toString();
          inviterPNID.add(inviterPNIDValue);
          String inviteePNIDValue = row.getCell(1).toString();
          inviteePNID.add(inviteePNIDValue);
        }
      }
      result = new List[] {inviterPNID, inviteePNID};
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
  public List<String> getListSubValue(String title) throws Exception {
    waitForPageToLoad();
    waitForTableToLoad();
    List<WebElement> values=null;
    if(title.equals("Inviter PNID / GLN")){
      values = getElements(inviterPNIDWithGLNPath);
    } else if(title.equals("Invitee PNID / GLN")){
      values = getElements(inviteePNIDWithGLNPath);
    }
    List<String> valueList = new ArrayList<>();
    for (WebElement row : values) {
      String[] value = row.getText().replace("GLN: ","").split("\n");
      String res = value[0] + " / " + value[2];
      valueList.add(res);
    }
    return valueList;
  }
  public List<String>[] getDownloadFileContent(String downloadFileName) throws Exception {
    List<String>[] list;
    List<String> inviterPNIDList = new ArrayList<>();
    List<String> inviteePNIDList = new ArrayList<>();
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
          inviterPNIDList = readExcel(f)[0];
          inviteePNIDList = readExcel(f)[1];
        }
      }
    }
    f.delete();
    list =new List[] {inviterPNIDList, inviteePNIDList};
    return list;
  }
public String getErrorMessageInDetailPage()throws Exception {
    return getElement(errorMessagePath).getText().split(":")[1];
}public void saveTheRelationship()throws Exception {
    getElement(saveButton).click();
    waitForElementToLoad();
}public void searchForInSearchField(String pnid)throws Exception {
    inputText(messageOrganizationSearchField,pnid);
}public void setTheOrganizationStatusToInactive(String value)throws Exception {
    waitForTableToLoad();
    getTableRows(organizationListReportTablePath).get(0).click();
    waitForPageToLoad();
    clickButton("Edit");
    waitForElementToLoad();
    getElement(messageOrganizationStatusArrowInDetailPage).click();
    waitForElementToLoad();
    getElements(messageOrganizationStatusValueListInDetailPage).stream().filter(v -> v.getText().equals(value)).findFirst().get().click();
}public String getOrganizationStatus()throws Exception {
    return getElement(organizationStatusInDetailPage).getText();
}public boolean inviterOrganizationNotDiaplayedInRelationshipDetailPage(String pnid)throws Exception {
    getElement(inviterOrganizationSelectorInDetailPage).click();
    inputText(inviterOrganizationSearchFieldInDetailPage,pnid);
    getElement(inviterOrganizationSearchButtonInDetailPage).click();
    waitForElementToLoad();
    return getElements(inviterOrganizationSearchResultListInDetailPage).get(0).getText().equals("");
}
public void cancelTheCreationOfNewRelationship()throws Exception {
    clickButton("Cancel");
  clickButton("Cancel");
  clickButton("Ok");
}public boolean inviterOrganizationShouldBeDisplayedUnderInviterDetailsSection(String fieldName)throws Exception {
    By fieldPath = null;
    switch (fieldName){
      case "Inviter Organization":
        fieldPath = inviterOrganizationFieldInDetailPage;
        break;
      case "Invitee Organization":
        fieldPath = inviteeOrganizationFieldInDetailPage;
        break;
      case "Inviter GLN":
        fieldPath = inviterGLNFieldInDetailPage;
        break;
      case "Invitee GLN":
        fieldPath = inviteeGLNFieldInDetailPage;
        break;
      case "Inviter Role":
        fieldPath = inviterRoleFieldInDetailPage;
        break;
      case "Invitee Role":
        fieldPath = inviteeRoleFieldInDetailPage;
        break;
      case "Message Type":
        fieldPath = messageTypeFieldInDetailPage;
        break;
    }
    return getElement(fieldPath).isDisplayed();
}public void resetTheAdaptFilters()throws Exception {
    clickButton("Reset");
    waitForElementToLoad();
    getElement(alertDialogPath).findElements(By.xpath(".//button")).stream().filter(b -> b.getText().equals("OK")).findFirst().get().click();
    clickButton("OK");
}public void getTheMessageCount()throws Exception {
    messageCount = getMessageCount();
}public boolean allTheFiltersShouldGetRemovedAndItShouldDisplayTheWholeData() {
    List<WebElement> filters = getElements(relationshipsFiltersPath);
    filters.remove(0);
    for (WebElement filter: filters) {
        if (!filter.getText().equals(""))
          return false;
    }
    return true;
}public void clickOnAdaptFilters()throws Exception {
    getElement(adaptFilterButtonPath).click();
}}
