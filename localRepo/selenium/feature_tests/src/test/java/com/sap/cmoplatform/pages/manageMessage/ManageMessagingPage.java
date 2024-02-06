package com.sap.cmoplatform.pages.manageMessage;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.LauncherPage;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.utils.PortalDriver;
import com.sap.cmoplatform.utils.PropertyReader;
import groovy.util.logging.Slf4j;
import com.sap.cmoplatform.pages.GenericPage;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.w3c.dom.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

@Slf4j
public class ManageMessagingPage extends Page {
    //By
    public static By appTitlePath = By.xpath("//span[contains(@class,'AppTitleText')]");
    public static By messageCountPath = By.xpath("//div[contains(@class,'paginationInfo')]");
    public static By messageTablePath = By.xpath("//table[contains(@id,'MessageList--messageTable')]");
    public By changeLogTablePath = By.xpath("//table[contains(@id,'changeLogDialogTable')]");
    public static By searchFieldSearchPath = By.xpath("//div[contains(@id,'searchField-search')]");
    public static By searchFieldResetPath = By.xpath("//div[contains(@id,'searchField-reset')]");
    public static By searchFieldCheckboxPath = By.xpath("//div[@role = 'checkbox']");
    public static By searchFilterInputPath = By.xpath("//input[@placeholder = 'Search for Filters']");
    public static By searchViewInputPath = By.xpath("//input[@placeholder = 'Search']");
    public static By filterClearSelectionPath = By.xpath("//span[contains(@id,'clearSelection')]");
    public static By filterItemOnListPagePath = By.xpath("//bdi[contains(@id,'filterBar-filterItem')]");
    public static By selectedFilterItemPath = By.xpath("//tr[@aria-selected = 'true']");
    public static By InstanceIDfilter = By.xpath("//input[@id='application-Message-manage-component---MessageList--inputFilterInstanceID-inner']");
    private Properties mmProperties = PropertyReader.loadProperties("manageMessage/ManageMessage.properties");
    public static By typeDropDownPath = By.xpath("//span[contains(@id,'MessageList--inputFilterScenario-arrow') and @role = 'button']");
    public static By messageStatusDropDownPath = By.xpath("//span[contains(@id,'MessageList--inputFilterMessageStatus-arrow') and @role = 'button']");

    private By usersCount = By.xpath("//div[contains(@class,'paginationInfo')]");
    public By usersTable = By.xpath("//div[contains(@class,'position-relative')]");
    public static By businessgroupDropDownPath = By.xpath("//*[@id='businessGroup']/div[1]/button");
    public static By columnsDropDownPath = By.xpath("//div[contains(@id,'custom_column_filter')]");
    public static By plantDropDownPath = By.xpath("//div[contains(@id,'plant')]");
    public static By categoryDropDownPath = By.xpath("//div[contains(@id,'category')]");
    public static By expirydateDropDownPath = By.xpath("//div[contains(@id,'expiryCategory')]");
    public static By provisionquantityDropDownPath = By.xpath("//div[contains(@id,'quantityFilter')]");
    public static By losstreeDropDownPath = By.xpath("//div[contains(@id,'lossTree')]");


    public static By reviewStatusDropDownPath = By.xpath("//span[contains(@id,'MessageList--inputFilterReviewStatus-arrow') and @role = 'button']");
    public static By searchBox = By.xpath("//div[@class='upose-select__value-container upose-select__value-container--is-multi upose-select__value-container--has-value css-hlgwow' and @class]");

    public static By dropDownPath = By.xpath("//div[@class = 'custom_checkbox']");
    public static By changedOnCalendarPath = By.xpath("//span[contains(@id,'MessageList--inputFilterLastUpdate-icon') and @role = 'presentation']");
    public static By changedOnCalendarPopoverPath = By.xpath("//div[contains(@id,'MessageList--inputFilterLastUpdate-RP-popover') and @role = 'dialog']");
    public static By calendarPopoverYearPath = By.xpath("//button[contains(@id,'MessageList--inputFilterLastUpdate-cal--Head-B2')]");
    public static By calendarPopoverActiveYearPath = By.xpath("//div[contains(@id,'MessageList--inputFilterLastUpdate-cal--YP') and @role = 'gridcell' and not(@aria-disabled = 'true')]");
    public static By calendarPopoverMonthPath = By.xpath("//button[contains(@id,'MessageList--inputFilterLastUpdate-cal--Head-B1')]");
    public static By calendarPopoverActiveMonthPath = By.xpath("//div[contains(@id,'MessageList--inputFilterLastUpdate-cal--MP') and @role = 'gridcell' and not(@aria-disabled = 'true')]");
    public static By calendarPopoverActiveDatePath = By.xpath("//div[contains(@id,'MessageList--inputFilterLastUpdate-cal') and @role = 'gridcell' and not(@aria-disabled = 'true')]");
    public static By listReportChangedOnPath = By.xpath(".//span[text()='Changed On']");
    public static By listReportCreatedOnPath = By.xpath(".//td[contains(@data-sap-ui-column,'MessageList--colCreatedOn')]");
    public static By parentElementPath = By.xpath("parent::*");
    public static By nextSiblingElementPath = By.xpath("following-sibling::*[1]");
    public static By CreatedOnFilterInputPath = By.xpath("//input[contains(@id,'MessageList--inputFilterCreate-inner')]");//
    public static By ChangedOnFilterInputPath = By.xpath("//input[contains(@id,'MessageList--inputFilterLastUpdate-inner')]");
    private static String timestampInputType;
    public static List<String> basicFilters = new ArrayList<>();
    public static List<String> additionFilters = new ArrayList<>();
    static final SeleniumUI5TestUtil portalDriver = PortalDriver.getInstance();

    private static String searchFilterId = "application-Message-manage-component---MessageList--inputSearchFilter-I";

    private static String changeLogSearchFilterId = "application-Message-manage-component---MessageList--changeLogDialogSearchField-I";

    public final By BDI_TEXT_GTIN = By.xpath("//td//bdi[text()='GTIN']");
    public final By SEARCH_FOR_FILTERS = By.xpath("//div[text()='Search']");
    public static final By sortFieldDropdown = By.xpath("//div[contains(@class, 'sapMInputBase sapMInputBaseHeightMargin sapMComboBoxBase sapMComboBox sapMInputBaseHasEndIcons')]//span");
    public static final By sortOrderDropDown = By.xpath("//div[@class='sapMSlt sapMSltDefault sapMSltMinWidth sapMSltHoverable sapMSltWithArrow']");
    public final By selectAllCheckBox = By.xpath("//*[@aria-label=\"Select all rows\"]");
    public final By settingsButton = By.xpath("//*[@title='Settings']");
    public final By VIEWICON = By.xpath("//span[@id='application-Message-manage-component---MessageList--variantManagement-trigger-inner']");
    public final By MODIFIEDICON = By.xpath("//span[@id='application-Message-manage-component---MessageList--variantManagement-modified']");
    public final By sortByPopUpLabel = By.xpath("//*[text()='Sort']");
    public final By sortList = By.xpath("//div[@class='sapMSortPanelContent sapMSortPanelBG']");
    public static final String PREPATH = "//span[text()='";
    public static final String ENDPATH = "']/../../div[contains(@class,'sapMListTblSubCntValInline')]//span";
    public static final String MOREPATH = "[contains(@id,'string')]";
    private static final Properties MANAGEMESSAGEIDS = PropertyReader.loadProperties("managemessage/managemessage.properties");
    private static final String PREFIX = MANAGEMESSAGEIDS.getProperty("mm.homePage.pagePrefix");
    public static final By listReportTypePath = By.xpath(".//td[contains(@data-sap-ui-column,'MessageList--colType')]");
    public static final By listReportMessageStatusPath = By.xpath(".//td[contains(@data-sap-ui-column,'MessageList--colMessageStatus')]");
    public static final By listReportReviewStatusPath = By.xpath(".//td[contains(@data-sap-ui-column,'MessageList--colReviewStatus')]");
    public static final By listReportLotNoPath = By.xpath(".//span[text()='Lot Numbers']");
    public static final By listReportBusinessDocIDPath = By.xpath(".//span[text()='Business Document ID']");
    public static final By listReportGTINPath = By.xpath(".//span[text()='GTINs']");
    public static final By listReportChangedByPath = By.xpath(".//span[text()='Changed By']");
    public static final By listReportCorrelationIDPath = By.xpath(".//span[text()='Correlation ID']");
    public static String correlationID = "";
    public static final By listReportSenderCodePath = By.xpath(".//span[text()='Sender Code']");
    public static final By listReportReceiverCodePath = By.xpath(".//span[text()='Receiver Code']");
    public static final By validationArtifactPath = By.xpath("//span[contains(@title,'Validation')]");
    public static final By partnerPNIDPath = By.id("MESSAGES_INFO_SENDER");
    public static final By customerPNIDPath = By.id("MESSAGES_INFO_RECEIVER");
    public static final By textViewPath = By.xpath("//*[@title = 'View message processing log as text']");
    public static final By textPath = By.xpath("//div[contains(@class,'HTMLControlInScrollableContainer ')]");
    public static final By GTINShowMoreButtonPath = By.xpath("//a[text() = 'More']");
    public static final By logSectionPath = By.id("sectionLogs");

    public static final String GOBUTTON = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.goButton");
    private static final String CLEARBUTTON = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.clearButton");
    private static final String LOTNUMBERINPUT = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.LotNumberInputId");
    private static String GTININPUT = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.GTINInputId");
    private static String SENDERGLN = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.SenderGLNInputId");
    private static String RECEIVERGLN = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.ReceiverGLNInputId");
    private static String SENDERORG = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.SenderOrgInputId");
    private static String RECEIVERORG = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.ReceiverOrgInputId");
    private static String REVIEWSTATUS = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.ReviewStatusInputId");
    private static String NEWMESSAGESCENARIOID = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.NewMessageScenario");
    private static String MESSAGESTATUSID = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.MessageStatus");
    private static String BUSINESSID = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.BusinessInputId");
    private static String CORRELATIONID = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.CorrelationInputId");
    private static String VIEWNAMEID = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.ViewNameInputId");
    private static String SEARCHID = PREFIX + MANAGEMESSAGEIDS.getProperty("mm.homePage.inputSearchId");
    private static String defaultViewTitle = "";
    public final By senderPayloadPath = By.xpath("//li[contains(@id, 'MessageDetails--downloadMessageMenu') and contains(@title, 'Sender')]");
    public final By receiverPayloadPath = By.xpath("//li[contains(@id, 'MessageDetails--downloadMessageMenu') and contains(@title, 'Receiver')]");
    private static final By ACTIONPATH = By.xpath("//span[text()='Action']");
    private static final By FIELDPATH = By.xpath("//span[text()='Field']");
    private static final By ITEMPATH = By.xpath("//span[text()='Item']");
    private static final By PREVIOUSPATH = By.xpath("//span[text()='Previous']");
    private static final By NEWPATH = By.xpath("//span[text()='New']");
    private static final By WHOPATH = By.xpath("//span[text()='Who']");
    private static final By WHENPATH = By.xpath("//span[text()='When']");

    private LauncherPage launcherPage = new LauncherPage();
    private static String firstMessageCorrelationID = "";
    private static String firstMessageOldReviewStatus = "";
    private static String firstMessageNewReviewStatus = "In Review";
    private static final By messageTypeDropDown = By.id("application-Message-manage-component---MessageList--typeComboBox-arrow");
    public String testFiles = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\testfiles\\manageMessage";

    public String getAppTitleName() throws Exception {
        portalDriver.switchToDefaultContent();
        return portalDriver.findElement(appTitlePath).getText();
    }

    public enum FilterField {
        LOT("Lot Numbers", LOTNUMBERINPUT),
        GTIN("GTINs", GTININPUT),
        SENDER("Sender", SENDERORG),
        RECEIVER("Receiver", RECEIVERORG),
        SENDERCODE("Sender Code", SENDERGLN),
        RECEIVERCODE("Receiver Code", RECEIVERGLN),
        REVIEW("Review Status", REVIEWSTATUS),
        BUSINESS("Business Document ID", BUSINESSID),
        SEARCH("SEARCH", SEARCHID),
        VIEW("View", VIEWNAMEID),
        MESSAGESTATUS("Message Status", MESSAGESTATUSID),
        NEWMESSAGESCENARIO("New Message Scenario", NEWMESSAGESCENARIOID),
        CORRELATION("Correlation ID", CORRELATIONID);
        public String filterInputID;
        public String filter;

        FilterField(String filter, String filterInputID) {
            this.filter = filter;
            this.filterInputID = filterInputID;
        }
    }

    private final String messageCountId = "application-Message-manage-component---MessageList--messageMonitorCountText-inner";
    public static int messageCount;
    public By additionalInfoInputPath = By.id("application-Message-manage-component---MessageList--additionalInfoTextArea-inner");
    public By settingFieldCheckboxPath = By.xpath("//div[contains(@id,'selectMulti') and @role = 'checkbox']");
    public final By submitCommentDisabledButtonPath = By.xpath("//button[contains(@class, 'sapMBtnDisabled') and @title = 'Submit']");
    private final Properties ui5Properties = PropertyReader.loadProperties("Ui5PageIds.properties");
    public By listReportNumberOfRetriggersPath = By.xpath(".//span[text()='Number of Retriggers']");
    public By listReportSubTypePath = By.xpath(".//td[contains(@data-sap-ui-column,'MessageList--colSubType')]");
    public By scenarioDropDownPath = By.xpath("//span[contains(@id,'MessageList--typeComboBox-arrow') and @role = 'button']");
    private final GenericPage genericPage = new GenericPage();
    public String senderPayloadFileName;
    public String receiverPayloadFileName;
    public final By settingSearchInputPath =
            By.xpath("//input[contains(@id,'searchField') and @placeholder = 'Search']");
    public final By settingTriggerSearchPath = By.xpath("//div[contains(@id,'searchField-search')]");
    public final By retriggerMessageButtonPath = By.xpath("//bdi[text() = 'Retrigger Message']");
    public final By fileInputPath = By.xpath("//input[@type='file']");
    public final By NumberOfRetriggersFilterInputPath = By.xpath("//input[contains(@id,'MessageList--inputFilterNumberOfRetriggers-inner')]");
    public final By messageRetriggerableFilterPath = By.xpath("//span[contains(@id,'MessageList--inputFilterMessageRetriggerable-arrow')]");
    public final By messageRetriggerableTruePath = By.xpath("//li[contains(@id, 'MessageList--inputFilterMessageRetriggerable-1')]");
    public final By messageRetriggerableFalsePath = By.xpath("//li[contains(@id, 'MessageList--inputFilterMessageRetriggerable-0')]");
    public final By automaticRetriesTitlePath = By.xpath("//div[@role = 'heading' and contains(text(),'Automatic Retries')]");
    public final By identicalMessagesTitlePath = By.xpath("//div[@role = 'heading' and contains(text(),'Identical Messages')]");
    public final By automaticRetriesTablePath = By.id("application-Message-manage-component---MessageDetails--idRetryObjectPageSection-innerGrid");
    public final By identicalMessagesTablePath = By.id("application-Message-manage-component---MessageDetails--idIdenticalObjectPageSection-innerGrid");
    public final By innerPageRetriggeredOnTimestampTitlePath = By.xpath("//bdi[text() = 'Retriggered On']");
    public final By innerPageRetriggeredOnTimestampPath = By.xpath("//span[contains(@id,'MessageDetails--retriggeredText')]");
    public final By innerPageCreatedOnTimestampTitlePath = By.xpath("//bdi[not(contains(@id,'MessageList--filterBar')) and text() = 'Created On']");
    public final By innerPageCreatedOnTimestampPath = By.xpath("//span[contains(@id,'MessageDetails--createdText')]");
    public final By commentPath = By.xpath("//textarea[contains(@id, 'MessageDetails--messageCommentFeedInput')]");
    public final By submitButton = By.xpath("//button[contains(@id, 'messageCommentFeedInput-button')]");
    public final By commentTextPath = By.xpath(
            "//span[contains(@id, 'MessageDetails--messageCommentFeedList') and  contains(@id, 'realtext')]");
    public final By correlationIdFilterPath = By.xpath("//input[contains(@id,'MessageList--inputFilterCorrelationID-inner')]");
    public final By subTypeFilterPath = By.xpath("//span[contains(@id, 'inputFilterMessageType-arrow') and @role = 'button']");
    public final By subTypeFilterDropdownPath = By.xpath("//div[@class = 'sapMSLITitleOnly']");
    public final By CPISearchFieldPath = By.id("MsgIDSelect-I");

    public final By CPITriggerSearchPath = By.id("MsgIDSelect-search");
    public final By CPIiflowTablePath = By.id("MESSAGES_TABLE-listUl");
    public final By CPIiflowTableStatusPath = By.xpath("//span[contains(@id, 'status') and @class = 'sapMObjStatusText']");
    public final By iflowCorrelatonIdPath = By.xpath("//span[@id = 'MESSAGES_INFO_CORRELATION_ID']");
    public final By messageStatusPath = By.xpath("//span[text() = 'Message Status']");
    private final int CHANGELOG_COLUMNS = 7;

    public void selectFiltersInViewSetting(String column) {
        try {
            inputText(searchViewInputPath, column);
            getElements(searchFieldCheckboxPath).get(1).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void uploadfile(String filePath) throws Exception {
        WebElement fileInput = getElement((By.xpath("//input[@type='file']")));
        fileInput.sendKeys(filePath);
    }

    public void clickOnBackButton() throws Exception {
        launcherPage.switchToDefaultContent();
        launcherPage.clickBackButton();
        launcherPage.switchToFrame("ManageMessages");
    }

    public void selectAnyUndefaultView() throws Exception {

        List<WebElement> views = getTableRows(By.id("application-Message-manage-component---MessageList--variantManagement-managementTable-listUl"));

        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).findElements(By.xpath("//div[@role ='radio']")).get(i).getAttribute("aria-checked").equals("false")) {


                if (i == 0) {

                    defaultViewTitle = views.get(i).findElements(By.tagName("td")).get(1).getText();
                } else {
                    defaultViewTitle = views.get(i).findElements(By.tagName("input")).get(0).getAttribute("value");

                }
                clickElement(views.get(i).findElements(By.xpath("//div[@role ='radio']")).get(i));
                break;
            }
        }
    }

    public boolean UndefaultViewSelected() {
        List<WebElement> views = getTableRows(By.id("application-Message-manage-component---MessageList--variantManagement-managementTable-listUl"));
        boolean selected = false;
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).findElements(By.xpath("//div[@role ='radio']")).get(i).getAttribute("aria-checked").equals("true")) {

                if (i == 0 && defaultViewTitle.equals(views.get(i).findElements(By.tagName("td")).get(1).getText())) {
                    selected = true;
                } else if (views.get(i).findElements(By.tagName("input")).get(0).getAttribute("value").equals(defaultViewTitle)) {
                    selected = true;
                }
                break;
            }
        }
        return selected;
    }

    public boolean isViewShowOnly(String viewName) {
        List<WebElement> views = getTableRows(By.id("application-Message-manage-component---MessageList--variantManagement-managementTable-listUl"));
        boolean showOnly = true;
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).isDisplayed()) {

                if (i == 0 && viewName.equals(views.get(i).findElements(By.tagName("td")).get(1).getText())) {
                    continue;
                } else if (views.get(i).findElements(By.tagName("input")).get(0).getAttribute("value").equals(viewName)) {
                    continue;
                } else {
                    showOnly = false;
                }

            }
        }

        return showOnly;
    }

    public boolean correlationIDisDisplayed() throws Exception {
        return getElement(By.xpath("//header[(@id='application-Message-manage-component---MessageDetails--ObjectPageLayout-headerTitle')]")).isDisplayed();
    }

    public boolean checkLogDisplay() throws Exception {
        return getElement(searchViewInputPath).isDisplayed()
                && getElement(ACTIONPATH).isDisplayed()
                && getElement(FIELDPATH).isDisplayed()
                && getElement(ITEMPATH).isDisplayed()
                && getElement(PREVIOUSPATH).isDisplayed()
                && getElement(NEWPATH).isDisplayed()
                && getElement(WHOPATH).isDisplayed()
                && getElement(WHENPATH).isDisplayed();
    }

    public boolean noNewLogRowShouldBeFoundForTheMessageWithTheRespectiveCorrelationID() throws Exception {
        inputText(searchViewInputPath, firstMessageCorrelationID);
        clickElement(By.xpath("//div[contains(@id, 'SearchField-search')]"));
        waitForTableToLoad();
        List<WebElement> views = getTableRows(By.id("application-Message-manage-component---MessageList--changeLogDialogTable-listUl"));
        boolean recentLog = false;
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).findElements(By.tagName("td")).get(1).getText().equals("Update") && compareRecentTime(views.get(i).findElements(By.tagName("td")).get(7).getText())) {
                recentLog = true;
            }
        }
        return recentLog;
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

    public boolean changeLogDisplayedAsReviewStatusTemplate() throws Exception {
        Properties users = PropertyReader.loadProperties("Users.properties");
        boolean recentLog = false;
        inputText(searchViewInputPath, firstMessageCorrelationID);
        clickElement(By.xpath("//div[contains(@id, 'SearchField-search')]"));
        waitForTableToLoad();
        List<WebElement> views = getTableRows(By.id("application-Message-manage-component---MessageList--changeLogDialogTable-listUl"));
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).findElements(By.tagName("td")).get(1).getText().equals("Update")
                    && views.get(i).findElements(By.tagName("td")).get(2).getText().equals("Review Status")
                    && views.get(i).findElements(By.tagName("td")).get(3).getText().equals(firstMessageCorrelationID)
                    && views.get(i).findElements(By.tagName("td")).get(4).getText().equals(firstMessageOldReviewStatus)
                    && views.get(i).findElements(By.tagName("td")).get(5).getText().equals(firstMessageNewReviewStatus)
                    && views.get(i).findElements(By.tagName("td")).get(6).getText().equals(users.getProperty("MM_User.username").toLowerCase())
                    && compareRecentTime(views.get(i).findElements(By.tagName("td")).get(7).getText()

            )) {
                recentLog = true;
            }
        }
        return recentLog;
    }

    public void changeReviewStatus() throws Exception {

        if (firstMessageOldReviewStatus.equals(firstMessageNewReviewStatus)) {
            firstMessageNewReviewStatus = "In Progress";
        }
        clickElement(By.xpath("//div[contains(@id,'MessageDetails--reviewStatusMenu')  and text() = '" + firstMessageNewReviewStatus + "']"));
    }

    public boolean changeLogDisplayedAsUploadMessageTemplate() throws Exception {
        Properties users = PropertyReader.loadProperties("Users.properties");
        boolean recentLog = false;
        inputText(searchViewInputPath, firstMessageCorrelationID);
        clickElement(By.xpath("//div[contains(@id, 'SearchField-search')]"));
        waitForTableToLoad();
        List<WebElement> views = getTableRows(By.id("application-Message-manage-component---MessageList--changeLogDialogTable-listUl"));
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).findElements(By.tagName("td")).get(1).getText().equals("Upload")
                    && views.get(i).findElements(By.tagName("td")).get(2).getText().equals("Message")
                    && views.get(i).findElements(By.tagName("td")).get(3).getText().equals(firstMessageCorrelationID)
                    && views.get(i).findElements(By.tagName("td")).get(6).getText().equals(users.getProperty("MM_User.username").toLowerCase())
                    && compareRecentTime(views.get(i).findElements(By.tagName("td")).get(7).getText()

            )) {
                recentLog = true;
            }
        }
        return recentLog;
    }

    public void selectScenarioInNewMessageScenario(String scenario) throws Exception {
        portalDriver.waitForLoad();
        selectDropDownFilterInput(NEWMESSAGESCENARIOID, scenario);
    }

    public boolean checkMessageIsTriggerred() throws Exception {
        waitForTableToLoad();
        portalDriver.waitForLoad();
        if (getMessageTableRows().size() == 1
                && getMessageTableRows().get(0).getText().equals("No Message Data Found")) {
            System.out.println("No message found");
            return false;
        }
        List<WebElement> messageRows = getMessageTableRows();
        firstMessageCorrelationID = getTableFieldDatas(FilterField.CORRELATION).get(0);
        if (getTableFieldDatas(FilterField.MESSAGESTATUS).get(0).equals("Successful")) {
            return true;
        } else {
            return false;
        }
    }

    public void scrollToComment() throws Exception {
        scrollToElement(By.xpath("//textarea[@placeholder = 'Post something here']"));
    }

    public boolean checkXMLisSame(File file1, File file2) {

        Diff diff = DiffBuilder.compare(file1).withTest(file2).build();

        if (diff.hasDifferences()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkXMLisEncode(File file) {

        try {
            StringBuilder xmlContentBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    xmlContentBuilder.append(line);
                }
            }

            String xmlContent = xmlContentBuilder.toString();
            return xmlContent.contains("encoding");
        } catch (Exception e) {

            return true;
        }
    }

    public boolean checkPartnerIsSame(File uploadFile, File downloadFile) {
        String uploadSender = "";
        String uploadReceiver = "";
        /*getPartner value from uploadFile */
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(uploadFile);
            NodeList nodeList = document.getElementsByTagName("attribute");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getAttributes().getNamedItem("id").getNodeValue().equals("sttpBupRegRegno")) {
                    if (uploadSender.equals("")) {
                        uploadSender = node.getTextContent();
                    } else if (uploadReceiver.equals("")) {
                        uploadReceiver = node.getTextContent();
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String downloadSender = "";
        String downloadReceiver = "";
        /*getPartner from download receiverFile*/
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(downloadFile);

            // Find the <sbdh:Identifier> element
            NodeList senderList = document.getElementsByTagName("sbdh:Sender");
            if (senderList.getLength() > 0) {
                Node senderNode = senderList.item(0);
                downloadSender = senderNode.getTextContent().trim();
            }
            NodeList receiverList = document.getElementsByTagName("sbdh:Receiver");
            if (senderList.getLength() > 0) {
                Node receiverNode = receiverList.item(0);
                downloadReceiver = receiverNode.getTextContent().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadSender.equals(downloadSender) && uploadReceiver.equals(downloadReceiver);


    }


    public boolean standardViewDisplayed() throws Exception {
        return getElement(By.xpath("//span[text()='Standard']")).isDisplayed();
    }

    public void clickOnMessage() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> values = getElements(By.xpath("//*[@class='sapMListTblNavCol']"));
        // click the first one
        values.get(0).click();
    }

    public boolean validateRetrigger() throws Exception {
        return getElement(By.xpath("//span[text()='Success']")).isDisplayed();
    }

    public void openAdaptFilter() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        clickElement("application-Message-manage-component---MessageList--filterBar-btnFilters");
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

    public void resetCheckBoxAdaptFilter(List<String> columnList) throws Exception {
        waitForPageToLoad();
        for (String column : columnList) {
            WebElement checkbox = getElement(By.xpath("//bdi[text()='" + column + "']/../../../../../..//div[@role='checkbox']"));
            if (checkbox.getAttribute("aria-checked").equals("true")) {
                checkbox.click();
            }
        }

    }

    public boolean validateColumnDisplay() {
        boolean flag = true;
        List<WebElement> columns = getElements(By.xpath("//section[@id='application-Message-manage-component---MessageList--changeLogDialog-cont']//th//span"));
        if (columns.size() != CHANGELOG_COLUMNS) flag = false;

        return flag;
    }

    public boolean validateColumnValueDisplay(String column) {
        boolean flag = true;
        List<WebElement> resultList = getElements(By.xpath("//*[@headers='application-Message-manage-component---MessageList--changeLogDialogCol" + column + "']"));
        String value = resultList.get(0).getText();

        switch (column) {
            case "EventAction":
                if (!value.equals("Retrigger")) {
                    flag = false;
                }
                break;
            case "Field":
                if (!value.equals("Correlation ID")) {
                    flag = false;
                }
                break;
            case "AppliedTo":
            case "OldValues":
                if (!value.equals(correlationID)) {
                    flag = false;
                }
                break;
            case "ChangedBy":
                if (!emailFormat(value)) {
                    flag = false;
                }
                break;
            case "ChangedOn":
                if (!timeStampFormat(value)) {
                    flag = false;
                }
                break;
        }
        return flag;
    }

    public boolean validateSearchValueDisplay(String column, String searchValue) {
        boolean flag = true;
        List<WebElement> resultList = getElements(By.xpath("//*[@headers='application-Message-manage-component---MessageList--changeLogDialogCol" + column + "']"));
        String value = resultList.get(0).getText();
        if (!value.equals(searchValue)) flag = false;
        return flag;

    }

    public boolean emailFormat(String email) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+[\\.]{0,1}[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]+");
        return pattern.matcher(email).matches();
    }

    public boolean timeStampFormat(String timeStamp) {
        String regex = "^(?:January|February|March|April|May|June|July|August|September|October|November|December)\\s\\d{1,2},\\s\\d{4},\\s\\d{1,2}:\\d{2}:\\d{2}\\s\\w{2}$";
        return Pattern.matches(regex, timeStamp);

    }

    public String getCorrelationID() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> list = getElements(By.xpath("//*[@class='sapMTitle sapMTitleStyleAuto sapMTitleWrap sapUiSelectable sapMTitleMaxWidth']//span"));
        return list.get(1).getText();
    }

    public void inputValueInSearch(String searchCondition) throws Exception {
        waitForPageToLoad();
        getElement(changeLogSearchFilterId).clear();
        inputText(changeLogSearchFilterId, searchCondition);
    }

    public void clickSearchButton() throws Exception {
        clickElement(By.xpath("//*[@id='application-Message-manage-component---MessageList--changeLogDialogSearchField-search']"));
    }


    public boolean validateColumnDisplayHomePage(String columnName) {
        WebElement element;
        try {
            element = getElement(By.xpath("//bdi[text()='" + columnName + "']"));
        } catch (Exception e) {
            return false;
        }
        return element.isDisplayed();
    }

    public List<WebElement> getTableRows(By by) {
        return portalDriver.getTableRows(by);
    }

    public void selectDropdownInScenarioDropDown(String dropdownName) throws Exception {
        waitForTableToLoad();
        getElement(scenarioDropDownPath).click();
        getElements(dropDownPath).stream()
                .filter(element -> element.getText().equals(dropdownName))
                .findFirst()
                .get()
                .click();
    }

    public void inputIntegerInFilter(String filterName, String integerCount) throws Exception {
        waitForTableToLoad();
        inputText(NumberOfRetriggersFilterInputPath, integerCount);
    }

    public void selectInViewSetting(String view) throws Exception {
        inputText(settingSearchInputPath, view);
        getElement(settingTriggerSearchPath).click();
        getElements(settingFieldCheckboxPath).stream()
                .filter(
                        v ->
                                v.findElement(parentElementPath)
                                        .findElement(nextSiblingElementPath)
                                        .getText()
                                        .equals("Number of Retriggers"))
                .findFirst()
                .get()
                .click();
        clickButton("OK");
    }

    public void selectBooleanInFilter(String booleanValue) throws Exception {
        getElement(messageRetriggerableFilterPath).click();
        if (Boolean.parseBoolean(booleanValue)) {
            getElement(messageRetriggerableTruePath).click();
        } else {
            getElement(messageRetriggerableFalsePath).click();
        }
    }

    public boolean dataShouldDisplayForMessageRetriggerableWithbooleanValue(String booleanValue)
            throws Exception {
        waitForTableToLoad();
        if (getMessageTableRows().size() == 1
                && getMessageTableRows().get(0).getText().equals("No Message Data Found")) return true;
        boolean retriggerButtonShouldDisplay = Boolean.parseBoolean(booleanValue);
        List<WebElement> rows = getMessageTableRows();
        rows.get(0).click();
        waitForPageToLoad();
        waitForTableToLoad();
        boolean buttonDisplayed;
        try {
            buttonDisplayed = getElement(retriggerMessageButtonPath).isDisplayed();
        } catch (Exception e) {
            buttonDisplayed = false;
        }
        return retriggerButtonShouldDisplay == buttonDisplayed;
    }

    public void openFirstMessageinManageMessage() throws Exception {
        waitForTableToLoad();
        waitForElementToLoad();
        if (getMessageTableRows().size() == 1
                && getMessageTableRows().get(0).getText().equals("No Message Data Found")) {
            System.out.println("No message found");
            return;
        }
        List<WebElement> messageRows = getMessageTableRows();
        messageRows.get(0).click();
        waitForPageToLoad();
        waitForTableToLoad();
    }

    public boolean tableWithColumnNamesShouldBeDisplayed(String tableName) throws Exception {
        boolean titleDisplayed = false;
        boolean tableDisplayed = false;
        if (tableName.equals("Automatic Retries")) {
            scrollToElement(automaticRetriesTitlePath);
            titleDisplayed = getElement(automaticRetriesTitlePath).isDisplayed();
            tableDisplayed = getElement(automaticRetriesTablePath).isDisplayed();
        } else if (tableName.equals("Identical Messages")) {
            scrollToElement(identicalMessagesTitlePath);
            titleDisplayed = getElement(identicalMessagesTitlePath).isDisplayed();
            tableDisplayed = getElement(identicalMessagesTablePath).isDisplayed();
        }
        return titleDisplayed && tableDisplayed;
    }

    public boolean timestampsShouldBeDisplayed(String timestampName) throws Exception {
        boolean titleDisplayed = false;
        boolean textDisplayed = false;
        if (timestampName.equals("Retriggered On")) {
            scrollToElement(innerPageRetriggeredOnTimestampTitlePath);
            titleDisplayed = getElement(innerPageRetriggeredOnTimestampTitlePath).isDisplayed();
            textDisplayed = getElement(innerPageRetriggeredOnTimestampPath).isDisplayed();
        } else if (timestampName.equals("Created On")) {
            scrollToElement(innerPageCreatedOnTimestampTitlePath);
            titleDisplayed = getElement(innerPageCreatedOnTimestampTitlePath).isDisplayed();
            textDisplayed = getElement(innerPageCreatedOnTimestampPath).isDisplayed();
        }
        return titleDisplayed && textDisplayed;
    }

    public boolean payloadShouldBeDisplayed(String payload) throws Exception {
        boolean payloadDisplayed = false;
        if (payload.equals("sender")) {
            payloadDisplayed = getElement(senderPayloadPath).isDisplayed();
        } else if (payload.equals("receiver")) {
            payloadDisplayed = getElement(receiverPayloadPath).isDisplayed();
        }
        return payloadDisplayed;
    }

    public void setMessageStatusFilterToValue(String filterValue) throws Exception {
        getDropDowns("Message Status").stream()
                .filter(dropDown -> dropDown.getText().equals(filterValue))
                .findFirst()
                .get()
                .click();
    }

    public void downloadPayload(String payload) throws Exception {
        if (payload.equals("sender")) {
            senderPayloadFileName = getElement(senderPayloadPath).getText();
            getElement(senderPayloadPath).click();
        } else if (payload.equals("receiver")) {
            receiverPayloadFileName = getElement(receiverPayloadPath).getText();
            getElement(receiverPayloadPath).click();
        }
        portalDriver.waitFor(8000);
    }

    public void leaveCommentMessageInComment(String commentMessage) throws Exception {
        scrollToElement(commentPath);
        inputText(commentPath, commentMessage);
    }

    public void submitComment() throws Exception {
        clickElement(submitButton);
    }

    public boolean containsComment(String commentMessage) throws Exception {
        waitForTableToLoad();
        scrollToElement(commentTextPath);
        List<WebElement> comments = getElements(commentTextPath);
        List<String> commentsText =
                comments.stream().map(comment -> comment.getText()).collect(Collectors.toList());
        return commentsText.contains(commentMessage);
    }

    public void theUserLogsOutFromCurrentAccount() throws Exception {
        launcherPage.switchToDefaultContent();
        genericPage.clickOnUserButtonId();
        genericPage.signOutButton();
    }

    public boolean payloadShouldMatchFromBothEnd(String payloadFileName) throws Exception {
        String partnerPayloadFilePath = payloadFileName + ".xml";
        String customerPayloadFilePath = payloadFileName + " (1).xml";
        Document partnerPayloadFile = getXMLDownloadFileContent(partnerPayloadFilePath);
        Document customerPayloadFile = getXMLDownloadFileContent(customerPayloadFilePath);
        return compareDOM(partnerPayloadFile, customerPayloadFile);
    }

    public static boolean compareDOM(Document document1, Document document2) {
        Element root1 = document1.getDocumentElement();
        Element root2 = document2.getDocumentElement();

        if (!compareElements(root1, root2)) {
            return false;
        }

        return true;
    }

    private static boolean compareElements(Element element1, Element element2) {
        if (!element1.getTagName().equals(element2.getTagName())) {
            return false;
        }

        if (!compareAttributes(element1, element2)) {
            return false;
        }

        if (!element1.getTextContent().equals(element2.getTextContent())) {
            return false;
        }

        NodeList children1 = element1.getChildNodes();
        NodeList children2 = element2.getChildNodes();

        if (children1.getLength() != children2.getLength()) {
            return false;
        }

        for (int i = 0; i < children1.getLength(); i++) {
            Node child1 = children1.item(i);
            Node child2 = children2.item(i);

            if (child1.getNodeType() == Node.ELEMENT_NODE && child2.getNodeType() == Node.ELEMENT_NODE) {
                if (!compareElements((Element) child1, (Element) child2)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean compareAttributes(Element element1, Element element2) {
        NamedNodeMap attributes1 = element1.getAttributes();
        NamedNodeMap attributes2 = element2.getAttributes();

        if (attributes1.getLength() != attributes2.getLength()) {
            return false;
        }

        for (int i = 0; i < attributes1.getLength(); i++) {
            Node attr1 = attributes1.item(i);
            Node attr2 = attributes2.item(i);

            if (!attr1.getNodeName().equals(attr2.getNodeName())
                    || !attr1.getNodeValue().equals(attr2.getNodeValue())) {
                return false;
            }
        }

        return true;
    }

    public void inputCorrelationIdIntoTheCorrelationIDFilter(String correlationID) throws Exception {
        inputText(correlationIdFilterPath, correlationID);
    }

    public String getCommentToastMessage() throws Exception {
        WebElement toast;
        try {
            toast =
                    portalDriver.findElement(By.xpath(ui5Properties.getProperty("ui5.toastMessageXpath")));
        } catch (Exception e) {
            toast = portalDriver.findElement(By.xpath(ui5Properties.getProperty("ui5.toastXpath")));
        }
        return toast.getText();
    }

    public void selectASubTypeFromSubTypeFilter(String subTypeName) throws Exception {
        clickElement(subTypeFilterPath);
        waitForElementToLoad();
        getElements(subTypeFilterDropdownPath).stream()
                .filter(dropdown -> dropdown.getText().equals(subTypeName))
                .findFirst()
                .get()
                .click();
    }

    public void inputValue(FilterField filterFiled, String value) throws Exception {
        try {
            getElement(CLEARBUTTON).click();
            getElement(By.id(filterFiled.filterInputID)).clear();
        } catch (Exception e) {

        }
        try {

            getElement(By.id(filterFiled.filterInputID)).clear();
        } catch (Exception e) {

        }

        inputText(filterFiled.filterInputID, value);
    }

    public boolean dataShouldDisplayWithFilterOfValue(String filterName, String filterValue)
            throws Exception {
        waitForTableToLoad();
        if (getMessageTableRows().size() == 1
                && getMessageTableRows().get(0).getText().equals("No Message Data Found")) return true;
        List<WebElement> rows = getMessageTableRows();
        List<String> values = new ArrayList<>();
        if (filterName.equals("Sub Type")) values = getSubTypeValueList(rows);
        int i = 0;
        while (i < values.size()) {
            if (!values.get(i).equals(filterValue)) return false;
            i++;
        }
        return true;
    }

    public void clickExpandHeaderIcon() throws Exception {
        waitForPageToLoad();
        clickElement(By.xpath("//*[@class='sapMBtnBase sapMBtn sapFDynamicPageToggleHeaderIndicator']"));
    }

    private List<String> getSubTypeValueList(List<WebElement> rows) {
        return rows.stream()
                .filter(row -> row.findElements(listReportSubTypePath).size() > 0)
                .map(m -> m.findElement(listReportSubTypePath).getText()) // Get the "Sub Type" value
                .collect(Collectors.toList());
    }

    private String getSubTypeValue(WebElement row) {
        return row.findElement(listReportSubTypePath).getText();
    }

    public void searchForIflowsWithMessageCorrelationID() throws Exception {
        inputText(CPISearchFieldPath, correlationID);
        getElement(CPITriggerSearchPath).click();
        waitForTableToLoad();
    }

    public boolean allTheRelatedIflowsShouldGetHitWithStatus(String status) {
        List<WebElement> iflowStatuses = getElements(CPIiflowTableStatusPath);
        for (WebElement iflowStatus : iflowStatuses) {
            if (!iflowStatus.getText().equals(status)) return false;
        }
        return true;
    }

    public String firstRowShouldDisplayValueWithOf(String name) {
        if (getMessageTableRows().size() == 1
                && getMessageTableRows().get(0).getText().equals("No Message Data Found")) return "";
        List<WebElement> rows = getMessageTableRows();
        String result = "";
        switch (name) {
            case "Sender Code":
                result = getSenderCodeValue(rows.get(1));
                break;
            case "Receiver Code":
                result = getReceiverCodeValue(rows.get(1));
                break;
            case "Sub Type":
                result = getSubTypeValue(rows.get(0));
                break;
            case "Type":
                result = getTypeValue(rows.get(0));
                break;
            case "LotNo":
                result = getLotNoValue(rows.get(1));
                break;
            case "Message Status":
                result = getMessageStatusValue(rows.get(0)).split("\n")[0];
                break;
            case "Review Status":
                result = getReviewStatusValue(rows.get(0));
                break;
            case "Business document ID":
                result = getBusinessDocIDValue(rows.get(1));
                break;
            case "GTIN":
                getElements(GTINShowMoreButtonPath).get(0).click();
                result = getGTINValue(rows.get(1)).split(" ")[0];
                break;
            case "Changed By":
                result = getChangedByValue(rows.get(1));
                break;
            case "Changed On":
                result = getChangedOnValue(rows.get(1));
                break;
        }
        return result;
    }

    private String getReceiverCodeValue(WebElement row) {
        return row.findElement(listReportReceiverCodePath)
                .findElement(parentElementPath)
                .findElement(nextSiblingElementPath)
                .getText();
    }

    private String getSenderCodeValue(WebElement row) {
        return row.findElement(listReportSenderCodePath)
                .findElement(parentElementPath)
                .findElement(nextSiblingElementPath)
                .getText();
    }

    private String getChangedByValue(WebElement row) {
        return row.findElement(listReportChangedByPath)
                .findElement(parentElementPath)
                .findElement(nextSiblingElementPath)
                .getText();
    }

    private String getGTINValue(WebElement row) {
        return row.findElement(listReportGTINPath)
                .findElement(parentElementPath)
                .findElement(nextSiblingElementPath)
                .getText();
    }

    private String getBusinessDocIDValue(WebElement row) {
        return row.findElement(listReportBusinessDocIDPath)
                .findElement(parentElementPath)
                .findElement(nextSiblingElementPath)
                .getText();
    }

    private String getChangedOnValue(WebElement row) {
        return row.findElement(listReportChangedOnPath)
                .findElement(parentElementPath)
                .findElement(nextSiblingElementPath)
                .getText();
    }

    private String getLotNoValue(WebElement row) {
        return row.findElement(listReportLotNoPath)
                .findElement(parentElementPath)
                .findElement(nextSiblingElementPath)
                .getText();

    }

    private String getReviewStatusValue(WebElement row) {
        return row.findElement(listReportReviewStatusPath).getText();
    }

    private String getMessageStatusValue(WebElement row) {
        return row.findElement(listReportMessageStatusPath).getText();
    }

    private String getTypeValue(WebElement row) {
        return row.findElement(listReportTypePath).getText();
    }

    public String getMessageStatus() throws Exception {
        return getElement(messageStatusPath).findElement(parentElementPath).findElement(nextSiblingElementPath).getText();
    }

    public void getMessageCorrelationId() throws Exception {
        waitForTableToLoad();
        List<WebElement> rows = getMessageTableRows();
        correlationID = rows.get(1).findElement(listReportCorrelationIDPath)
                .findElement(parentElementPath)
                .findElement(nextSiblingElementPath)
                .getText();
    }

    public boolean resultShouldShowForTheSearchedCoRelationID() throws Exception {
        waitForTableToLoad();
        getTableRows(CPIiflowTablePath).get(0).click();
        waitForElementToLoad();
        return getElement(CPIiflowTablePath).isDisplayed() && getElement(iflowCorrelatonIdPath).getText().equals(correlationID);
    }

    public void openValidationArtifact() throws Exception {
        getElement(validationArtifactPath).click();
        waitForTableToLoad();
    }

    public String getPNIDInCPIArtifact(String name) throws Exception {
        if (name.equals("partner PNID")) {
            return getElement(partnerPNIDPath).getText();
        } else if (name.equals("customer PNID")) {
            return getElement(customerPNIDPath).getText();
        }
        return "";
    }

    public void openTextView() throws Exception {
        scrollToElement(logSectionPath);
        waitForElementToLoad();
        WebElement tag = getElement(textViewPath);
        tag.click();
        waitForPageToLoad();
    }

    public String getIntermediateErrorInTextView() throws Exception {
        String innerHTML = getElement(textPath).getAttribute("innerHTML");
        String[] lines = innerHTML.split("<br>");
        for (String line : lines) {
            if (line.contains("IntermediateError")) {
                String[] values = line.replaceAll("&nbsp;", "").split("=");
                return values[1];
            }
        }
        return "";
    }

    public void clickLastDropDown(String type) throws Exception {
        By xPath = null;
        switch (type) {
            case "so":
                xPath = sortOrderDropDown;
                break;
            case "field":
                xPath = sortFieldDropdown;
                break;
            case "mt":
                xPath = messageTypeDropDown;
        }
        List<WebElement> dropDrowns = getElements(xPath);
        dropDrowns.get(dropDrowns.size() - 1).click();
    }

    public int getMessageCount() throws Exception {
        waitForTableToLoad();
        WebElement tableTitle = portalDriver.findElement(messageCountPath);
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

    public void waitForElementToLoad() throws Exception {
        portalDriver.waitFor(2000);
        portalDriver.waitForLoad();
    }
    public boolean checkMessageCountDisplay() throws Exception {
        return getElement(
                "application-Message-manage-component---MessageList--messageMonitorCountText-inner")
                .isDisplayed();
    }

    public List<String> getExcelDownloadFileContent(String downloadFileName) throws Exception {
        List<String> list = new ArrayList<>();
        File f = null;
        String filePath =
                System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        // Look for the file in the files
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

    public Document getXMLDownloadFileContent(String downloadFileName) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        File f = null;
        String filePath =
                System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\";
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        // Look for the file in the files
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.equals(downloadFileName)) {
                    f = new File(filePath + fileName);
                    document = builder.parse(f);
                }
            }
        }
        f.delete();
        return document;
    }


    public void clickDownloadIcon() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        clickElement("application-Message-manage-component---MessageList--exportMessagesButton-img");
    }

    public boolean validateMessageCount() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        waitForElementToLoad();
        boolean flag = true;
        String messageCount =
                getElement(messageCountId).getText().replaceAll(",", "").replaceAll(" ", "");
        String regex = "^Messages\\((\\d+)\\)$";

        if (!Pattern.matches(regex, messageCount)) {
            flag = false;
        }
        return flag;
    }

    public List<WebElement> getMessageTableRows() {
        return this.getTableRows(messageTablePath);
    }

    public void selectFiltersInAdaptFilter(List<String> filters) {
        filters.forEach(
                filter -> {
                    try {
                        inputText(searchFilterInputPath, filter); // input filter name
                        getElement(searchFieldSearchPath).click(); // search
                        getElements(searchFieldCheckboxPath).get(0).click(); // click check
                        getElement(searchFieldResetPath).click(); // clear input
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public void clearFilters() throws Exception {
        getElement(filterClearSelectionPath).click();
    }

    public boolean checkFilterGetSelected(List<String> Filters) {
        List<WebElement> filterElements = getElements(selectedFilterItemPath);
        List<String> filterName =
                filterElements.stream()
                        .filter(filter -> Boolean.parseBoolean(filter.getAttribute("aria-selected")))
                        .map(filter -> filter.findElement(By.tagName("bdi")).getText())
                        .collect(Collectors.toList());
        // Check whether the selected filters contain the required filters
        return filterName.containsAll(Filters);
    }

    public boolean checkFilterGetSelectedOnListPage() {
        List<String> filters =
                Stream.concat(basicFilters.stream(), additionFilters.stream()).collect(Collectors.toList());
        List<String> filterItemOnListPage =
                getElements(filterItemOnListPagePath).stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
        // Check whether the filters on list page contain the required filters
        return filterItemOnListPage.containsAll(filters);
    }

    public boolean checkOnlySelectedFilterOnListPage() {
        List<String> filters =
                Stream.concat(basicFilters.stream(), additionFilters.stream()).collect(Collectors.toList());
        List<String> filterItemOnListPage =
                getElements(filterItemOnListPagePath).stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
        // Check whether the filters on list page match exactly as the required filters
        return new HashSet<>(filterItemOnListPage).equals(new HashSet<>(filters));
    }

    public List<WebElement> getDropDowns(String dropDown) throws Exception {
        waitForTableToLoad();
        WebElement dropDownElement = null;
        switch (dropDown) {
            case "Type":
                dropDownElement = getElement(typeDropDownPath);
                break;
            case "Message Status":
                dropDownElement = getElement(messageStatusDropDownPath);
                break;
            case "Review Status":
                dropDownElement = getElement(reviewStatusDropDownPath);
                break;
        }
        assert dropDownElement != null;
        dropDownElement.click();
        waitForElementToLoad();
        return getElements(dropDownPath);
    }
    public void enterTextToSearch(String data) throws Exception {
        waitForPageToLoad();
        getElement(searchBox).sendKeys(data);

    }

    public void clickcolumnsDropDownPath() throws Exception {
        clickElement(columnsDropDownPath);
    }

    public void selectNameColumns1Filter(List<String> columnList) throws Exception {
        waitForPageToLoad();
        for (String column : columnList) {
            WebElement name = getElement(By.xpath("//div[text()='" + column + "']/../../../../../..//div[@class='custom_checkbox']"));
            if (name.getAttribute("custom_checkbox").equals("true")) {
                name.click();
            }
        }

    }


    public int getTableRowCount(By selector) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> rows = getTableRows(selector);
        return rows.size();
    }

    public String getUsersCount() throws Exception {
        waitForPageToLoad();
        return getElement(usersCount).getText().trim();
    }



    public List<WebElement> getDropDowns1(String dropDown) throws Exception {
        waitForTableToLoad();
        WebElement dropDownElement = null;
        switch (dropDown) {
            case "Business Group":
                dropDownElement = getElement(businessgroupDropDownPath);
                break;
            case "Plant":
                dropDownElement = getElement(plantDropDownPath);
                break;
            case "Category filter":
                dropDownElement = getElement(categoryDropDownPath);
                break;
            case "Expiry date":
                dropDownElement = getElement(expirydateDropDownPath);
                break;
            case "Provision Quantity":
                dropDownElement = getElement(provisionquantityDropDownPath);
                break;
            case "Loss Tree":
                dropDownElement = getElement(losstreeDropDownPath);
                break;
            case "Columns":
                dropDownElement = getElement(columnsDropDownPath);
                break;
        }
        assert dropDownElement != null;
        dropDownElement.click();
        waitForElementToLoad();
        return getElements(dropDownPath);
    }

    public boolean dropDownsContainsValues(
            List<WebElement> dropDowns, List<String> requiredDropDown) {
        List<String> dropDownNames =
                dropDowns.stream().map(WebElement::getText).collect(Collectors.toList());
        return dropDownNames.containsAll(requiredDropDown);
    }

    public void clickOnCalendarInChangedOnFilter() throws Exception {
        getElement(changedOnCalendarPath).click();
    }

    public void attachTheMessageFileInXmlFormat(String file) throws Exception {
        String filePath = testFiles + File.separator + getFileName(file);
        uploadFile(filePath);
    }

    public String getFileName(String file) {
        switch (file) {
            case "Shipping Message":
                return "Anvisa_Shipping_New_tags_QA_New_BP.xml";
            case "Receive Message":
                return "Receive_New_Tags_New_BP.xml";
            case "Finalization Message":
                return "Final_Export_New_Tags_New_BP.xml";
        }
        return file;
    }

    public void checkCalendarInChangedOnFilter() throws Exception {
        getElement(changedOnCalendarPopoverPath).isDisplayed();
    }

    public void selectChangedOnDate(String fromTime, String toTime) throws Exception {
        timestampInputType = "Calendar";
        selectDate(fromTime);
        selectDate(toTime);
    }

    private void selectDate(String dateString) throws Exception {
        LocalDate date = LocalDate.parse(dateString);
        String year = String.valueOf(date.getYear());
        String month = getMonthNameInEnglish(date);
        String day = String.valueOf(date.getDayOfMonth());
        // Select year
        getElement(calendarPopoverYearPath).click();
        getElements(calendarPopoverActiveYearPath).stream()
                .filter(y -> y.getText().equals(year))
                .collect(Collectors.toList())
                .get(0)
                .click();
        // Select month
        getElement(calendarPopoverMonthPath).click();
        getElements(calendarPopoverActiveMonthPath).stream()
                .filter(m -> m.getText().equals(month))
                .collect(Collectors.toList())
                .get(0)
                .click();
        // Select date
        getElements(calendarPopoverActiveDatePath).stream()
                .filter(d -> d.getText().equals(day))
                .collect(Collectors.toList())
                .get(0)
                .click();
    }


  public static String getMonthNameInEnglish(LocalDate date) {
        return Month.of(date.getMonthValue()).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public boolean dataShouldDisplayForTheSelectedDates(String filter, String fromDate, String toDate)
            throws Exception {
        LocalDate[] expectedDates = parseDate(fromDate, toDate);
        List<String> actualDates = new ArrayList<>();
        LocalDate fromDateTime = expectedDates[0];
        LocalDate toDateTime = expectedDates[1];
        waitForTableToLoad();
        if (getMessageTableRows().size() == 1
                && getMessageTableRows().get(0).getText().equals("No Message Data Found")) return true;
        List<WebElement> rows = getMessageTableRows();
        if (filter.equals("Created On")) {
            actualDates = getCreatedOnDatesList(rows);
        } else if (filter.equals("Changed On")) {
            actualDates = getChangedOnDatesList(rows);
        }
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

    private List<String> getChangedOnDatesList(List<WebElement> rows) {
        return rows.stream()
                .filter(row -> row.findElements(listReportChangedOnPath).size() > 0)
                .map(m ->
                        m.findElement(listReportChangedOnPath) // Switch to element that contains text "Changed On"
                                .findElement(parentElementPath) // Switch to its parent element
                                .findElement(nextSiblingElementPath) // Switch to its next sibling
                                .getText()) // Get the "Changed On" date time
                .collect(Collectors.toList());
    }

    private List<String> getCreatedOnDatesList(List<WebElement> rows) {
        return rows.stream()
                .filter(row -> row.findElements(listReportCreatedOnPath).size() > 0)
                .map(m ->
                        m.findElement(listReportCreatedOnPath).getText()) // Get the "Created On" date time
                .collect(Collectors.toList());
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
        return new LocalDate[]{fromDateTime, toDateTime};
    }

    public void inputDateInTimeStampFilter(String filter, String fromDate, String toDate) throws Exception {
        waitForTableToLoad();
        timestampInputType = "Input";
        String inputDate = fromDate + " - " + toDate;
        if (filter.equals("Created On")) {
            inputText(CreatedOnFilterInputPath, inputDate);
        } else if (filter.equals("Changed On")) {
            inputText(ChangedOnFilterInputPath, inputDate);
        }
    }

    public void enterInputFilterValue(String filterValue, String filterColumnName) throws Exception {
        waitForPageToLoad();
        String filterId = getIdOfInputFilter(filterColumnName);
        getElement(filterId).clear();
        inputText(filterId, filterValue);
    }

    public void enterSearchFilterValue(String filterValue) throws Exception {
        waitForPageToLoad();
        getElement(searchFilterId).clear();
        inputText(searchFilterId, filterValue);
    }

    public String getIdOfInputFilter(String columnName) {
        return "application-Message-manage-component---MessageList--inputFilter"
                + columnName
                + "-inner";
    }

    public boolean checkInputFilterNotNull(String columnName) throws Exception {
        waitForPageToLoad();
        return !getElement(getIdOfInputFilter(columnName)).getText().equals("");
    }

    public boolean checkSearchFilterNotNull() throws Exception {
        waitForPageToLoad();
        return !getElement(searchFilterId).getText().equals("");
    }

    public boolean checkDropDownNotNull(String type) throws Exception {
        waitForPageToLoad();
        boolean flag = true;
        switch (type) {
            case "MessageStatus":
                if (getElement(
                        By.xpath(
                                "//*[@id='application-Message-manage-component---MessageList--inputFilterMessageStatus']//*[@class='sapMInputBaseIconContainer']"))
                        .getText()
                        .equals("")) {
                    flag = false;
                }
                break;
            case "Scenario":
                if (getElement(
                        By.xpath(
                                "//*[@id='application-Message-manage-component---MessageList--inputFilterScenario']//*[@class='sapMInputBaseIconContainer']"))
                        .getText()
                        .equals("")) {
                    flag = false;
                }
                break;
            case "ReviewStatus":
                if (getElement(
                        By.xpath(
                                "//*[@id='application-Message-manage-component---MessageList--inputFilterReviewStatus']//*[@class='sapMInputBaseIconContainer']"))
                        .getText()
                        .equals("")) {
                    flag = false;
                }
                break;
        }
        return flag;
    }

    public List<String> getListSubValue(String title) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<WebElement> values =
                getElements(
                        By.xpath(
                                "//span[text()='"
                                        + title
                                        + "']/../../div[contains(@class,'sapMListTblSubCntValInline')]//span"));
        List<String> valueList = new ArrayList<>();
        for (WebElement row : values) {
            valueList.add(row.getText());
        }

        return valueList;
    }

    public void clickDropDown(String type) throws Exception {
        waitForPageToLoad();
        switch (type) {
            case "MessageStatus":
                clickElement(By.xpath("//*[@id='application-Message-manage-component---MessageList--inputFilterMessageStatus']//*[@class='sapMInputBaseIconContainer']"));
                break;
            case "Scenario":
                clickElement(By.xpath("//*[@id='application-Message-manage-component---MessageList--inputFilterScenario']//*[@class='sapMInputBaseIconContainer']"));
                break;
            case "ReviewStatus":
                clickElement(By.xpath("//*[@id='application-Message-manage-component---MessageList--inputFilterReviewStatus']//*[@class='sapMInputBaseIconContainer']"));
                break;
            case "MessageRetriggerable":
                clickElement(By.xpath("//span[@id='application-Message-manage-component---MessageList--inputFilterMessageRetriggerable-arrow']"));
        }
    }

    public void selectDropDownOption(String filterValue) throws Exception {
        if (filterValue.equals("true") || filterValue.equals("false")) {
            clickElement(By.xpath("//li[text()='" + filterValue + "']"));
        } else {
            clickElement(By.xpath("//*[text()='" + filterValue + "' and @class='sapMSLITitleOnly']"));
        }
    }

    public void clickCollapseHeaderIcon() throws Exception {
        waitForPageToLoad();
        clickElement(
                By.xpath("//*[@class='sapMBtnBase sapMBtn sapFDynamicPageToggleHeaderIndicator']"));
    }

    public void clickGoIcon() throws Exception {
        //        waitForPageToLoad();
        getElement(GOBUTTON).click();
    }

    public boolean checkFilterBarDisplay() throws Exception {
        return getElement(
                By.xpath(
                        "//*[@class='sapContrastPlus sapFDynamicPageHeader sapFDynamicPageHeaderWithContent sapFDynamicPageHeaderPinnable']"))
                .isDisplayed();
    }

    public void selectItem(String itemName, String type) throws Exception {
        By xPath = null;
        switch (type) {
            case "so":
                xPath = By.xpath("//li[text()='" + itemName + "']");
                break;
            case "field":
                xPath = By.xpath("//*[text()='" + itemName + "' and @class='sapMSLITitleOnly']");
                break;
        }
        List<WebElement> items = getElements(xPath);
        items.get(items.size() - 1).click();
    }

    public boolean fieldSelected(String columnName) throws Exception {
        return getElement(By.xpath("//*[@value='" + columnName + "']")).isDisplayed();
    }

    public boolean sortSeleted(String sortOrder) throws Exception {
        return getElement(By.xpath("//span[text()='" + sortOrder + "']")).isDisplayed();
    }

    public void selectValue(FilterField filterFiled, String value) throws Exception {
        try {
            clickButton("Clear");
        } catch (Exception e) {
            /**ignore for normal case"**/
        }
        selectDropDownFilterInput(filterFiled.filterInputID, value);
        try {
            clickButton("Select");
        } catch (Exception e) {
            /**ignore for normal case"**/
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
        Arrays.sort(sorted, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
        for (int i = 0; i < size; i++) {
            if (actual[i].equals(sorted[i])) {
                flag = true;
            } else {
                return false;
            }
        }

        return flag;
    }

    public Boolean isColumnSortedInDescendingOrder(String[] columnValue) throws Exception {
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
        Arrays.sort(sorted, Comparator.comparing(String::isEmpty).reversed().thenComparing(Comparator.reverseOrder()));

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

        By moreBtn =
                By.xpath(
                        "//span[text()='"
                                + columnName
                                + "']/../../div[contains(@class,'sapMListTblSubCntValInline')]//*[text()='More']");
        List<WebElement> moreBtns = getElements(moreBtn);
        int nMoreBtn = moreBtns.size();
        if (nMoreBtn != 0) {
            for (WebElement btn : moreBtns) {
                btn.click();
            }
        }

        String columnId = columnName.replaceAll("\\s", "");

        By spanForm =
                By.xpath(
                        "//span[text()='"
                                + columnName
                                + "']/../../div[contains(@class,'sapMListTblSubCntValInline')]//span[contains(@id, 'string')]");
        By resForm =
                By.xpath(
                        "//td[contains(@data-sap-ui-column, 'application-Message-manage-component---MessageList--col"
                                + columnId
                                + "')]//span[@class = 'sapMObjStatusText']");

        int count = getElements(resForm).size();
        if (count == 0) {
            resForm = spanForm;
            count = getElements(resForm).size();
        }
        String[] columnValues = new String[count];
        for (int i = 0; i < count; i++) {
            columnValues[i] = getElements(resForm).get(i).getText();
        }
        return columnValues;
    }

    public boolean countOfElementsEquals(By xPath, int count) throws Exception {
        return getElements(xPath).size() == count;
    }

    public List<String> getTableFieldDatas(FilterField filterField) throws Exception {
        waitForTableToLoad();
        List<String> fieldDatas = new ArrayList<>();
        List<WebElement> elements;
        if (ifNeedClickMoreButton(filterField)) {
            elements = getElements(By.xpath(PREPATH + filterField.filter + ENDPATH + MOREPATH));
        } else {

            elements = getElements(By.xpath(PREPATH + filterField.filter + ENDPATH));
            if (elements.size() == 0) {
                /** page layout issue some time cannot get the value* */
                elements =
                        getElements(
                                By.xpath(
                                        "//td[contains(@data-sap-ui-column, '"
                                                + PREFIX
                                                + "col"
                                                + filterField.filter.replace(" ", "")
                                                + "')]//span"));
            }
        }
        for (WebElement element : elements) {
            fieldDatas.add(element.getText());
        }
        return fieldDatas;
    }

    private Boolean ifNeedClickMoreButton(FilterField filterField) {
        By moreBtnPath =
                By.xpath(
                        PREPATH
                                + filterField.filter
                                + "']/../../div[contains(@class,'sapMListTblSubCntValInline')]//*[text()='More']");
        List<WebElement> moreBtns = getElements(moreBtnPath);
        if (moreBtns.size() == 0) {
            return false;
        }
        for (WebElement moreBtn : moreBtns) {
            moreBtn.click();
        }
        return true;
    }

    public static List<String> readExcel(File file) throws Exception {
        List<String> result = new ArrayList<>();
        FileInputStream stream = null;
        XSSFWorkbook workbook = null;
        try {
            stream = new FileInputStream(file);

            workbook = new XSSFWorkbook(stream);

            Sheet sheet = workbook.getSheetAt(0);

            int rowNum = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(0);

            for (int i = 1; i < rowNum; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        String value = row.getCell(10).toString();
                        result.add(value);
                    }
                }
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

    public boolean checkDownloadFile(String downloadFileName) throws Exception {
        List<String> valueList = getListSubValue("Correlation ID");
        List<String> result = getExcelDownloadFileContent(downloadFileName);
        boolean flag = true;
        if (valueList.size() != result.size()) flag = false;
        for (int i = 0; i < valueList.size(); i++) {
            if (!valueList.get(i).equals(result.get(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean checkColumnDisplay(String columnName) throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        boolean flag = false;
        if (getElements(By.xpath("//span[text()='" + columnName + "']")).size() > 0) {
            flag = true;
        }
        return flag;
    }

    public void selectCheckBox(List<String> columnList) throws Exception {
        for (String column : columnList) {
            clickElement(By.xpath("//div[contains(@id,'custom_column_filter') and text()='" + column + "']/../..//div[@class='custom_checkbox']"));
        }
    }

    public boolean checkFieldNotNull(FilterField filterField) throws Exception {
        waitForPageToLoad();
        return !getElement(filterField.filterInputID).getAttribute("value").equals("");
    }

    public boolean checkFieldNotNull(String value, FilterField filterField) throws Exception {
        waitForPageToLoad();
        return getElement(By.id(PREFIX + "inputFilter" + filterField.filter.replace(" ", "")))
                .getText()
                .replace(" ", "")
                .equals(value.replace(" ", ""));
    }

    public boolean dataShouldDisplayForNumberOfRetriggersWithIntegerCount(String integerCount)
            throws Exception {
        waitForTableToLoad();
        if (getMessageTableRows().size() == 1
                && getMessageTableRows().get(0).getText().equals("No Message Data Found")) return true;
        List<WebElement> rows = getMessageTableRows();
        List<String> actualCounts = getNumberOfRetriggersCount(rows);
        int i = 0;
        while (i < actualCounts.size()) {
            if (!actualCounts.get(i).equals(integerCount)) return false;
            i++;
        }
        return true;
    }

    public boolean timestampIsDisplayed() throws Exception {
        return getElement(By.xpath("//*[@class='sapFDynamicPageTitleMainHeadingSnappedExpandContent sapFDynamicPageTitleMainExpandContentVisible']")).isDisplayed();
    }

    public boolean fieldsShowInHeader() throws Exception {
        boolean bShow = false;
        boolean bInstanceID = getElement(By.xpath("//*[contains(text(), 'Instance ID:')]")).isDisplayed();
        boolean bMessage = getElement(By.xpath("//*[text()='Message Status: ']")).isDisplayed();
        boolean bReviewStatus = getElement(By.xpath("//*[text()='Review Status: ']")).isDisplayed();
        boolean bType = getElement(By.xpath("//span[starts-with(text(), \"Type: \")]")).isDisplayed();
        boolean bSubType = getElement(By.xpath("//span[starts-with(text(), \"Sub Type: \")]")).isDisplayed();
        bShow = bInstanceID && bMessage && bReviewStatus && bType && bSubType;
        return bShow;
    }

    public boolean listPageDisplayed() throws Exception {
        return getElement("application-Message-manage-component---MessageList--page-content")
                .isDisplayed();
    }

    public boolean variantViewDisplayed() throws Exception {
        return getElement(By.xpath("//span[text()='TestView']")).isDisplayed();
    }

    public void uploadFile(String filePath) throws Exception {
        WebElement fileInput = getElement(fileInputPath);
        fileInput.sendKeys(filePath);
    }

    private List<String> getNumberOfRetriggersCount(List<WebElement> rows) {
        return rows.stream()
                .filter(row -> row.findElements(listReportNumberOfRetriggersPath).size() > 0)
                .map(
                        m ->
                                m.findElement(
                                                listReportNumberOfRetriggersPath) // Switch to element that contains text
                                        // "Number of Retriggers"
                                        .findElement(parentElementPath) // Switch to its parent element
                                        .findElement(nextSiblingElementPath) // Switch to its next sibling
                                        .getText()) // Get the "Changed On" date time
                .collect(Collectors.toList());
    }
}
