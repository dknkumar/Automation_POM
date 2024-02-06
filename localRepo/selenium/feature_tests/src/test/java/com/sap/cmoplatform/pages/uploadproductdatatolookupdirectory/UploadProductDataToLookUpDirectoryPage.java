package com.sap.cmoplatform.pages.uploadproductdatatolookupdirectory;

import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.objects.UploadProductToLookUpDirectory;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class UploadProductDataToLookUpDirectoryPage extends Page {
    private Properties UploadProductDataToLookUpDirectoryPageIds = PropertyReader.loadProperties("uploadproductdatatolookupdirectory/UploadProductDataTolookUpDirectoryPageIds.properties");
    private String prefix = UploadProductDataToLookUpDirectoryPageIds.getProperty("uploadproductdata.home.pagePrefix");
    // private String uploadPrefix = UploadProductDataToLookUpDirectoryPageIds.getProperty("uploadproductdata.upload.pagePrefix");
    // private String reviewPrefix = UploadProductDataToLookUpDirectoryPageIds.getProperty("uploadproductdata.reviewFile.pagePrefix");
    private String pageTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.home.pageTitle");
    private String tableHeader = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.home.tableHeader");
    private String fileInputId = prefix + UploadProductDataToLookUpDirectoryPageIds.getProperty("uploadproductdata.upload.fileInputId");
    public String datepickerStartDateInput = prefix + UploadProductDataToLookUpDirectoryPageIds.getProperty("uploadproductdata.upload.datepickerStartDateInput");
    private String commentsTextArea = prefix + UploadProductDataToLookUpDirectoryPageIds.getProperty("uploadproductdata.upload.commentsTextArea");
    private String datepickerEndDateInput = prefix + UploadProductDataToLookUpDirectoryPageIds.getProperty("uploadproductdata.upload.datepickerEndDateInput");
    private By fileNameColumn = By.xpath("//*[contains(@headers,'nameColumn')]/span");
    private By uploadDateColumn = By.xpath("//*[contains(@headers,'nameColumnDate')]/span");
    private By commentColumn = By.xpath("//*[contains(@headers,'nameColumnComment')]/span");
    private By gtinCountColumn = By.xpath("//*[contains(@headers,'countColumn')]/span");
    private By statusColumn = By.xpath("//*[contains(@headers,'unitNumberColumn')]/div/span");
    private String filepath = System.getProperty("user.dir") + "/src/test/resources/testfiles/uploadGTIN/";
    private String reviewPageTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.pageTitle");
    private String startDateText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.startDateText");
    private String endDateText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.endDateText");
    private String commentText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.commentText");
    private String NoteText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.upload.NoteText");
    private By mandatoryErrorMessage = By.xpath("(//div[@class='sapMMessageToast sapUiSelectable sapContrast sapContrastPlus'])[2]");
    private By endDate = By.xpath("//*[contains(@id,'datepickerEndDate')]/span[@class='sapUiCalItemText'][text()='12']");
    public By startDate = By.xpath("//*[contains(@aria-label,'today')][contains(@id,'datepickerStartDate')]");
    private String datePickerStartDateCalender = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.upload.datepickerStartDateCalender");
    private String datePickerEndDateCalender = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.upload.datepickerEndDateCalender");
    private String nextMonth = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.upload.nextMonthButton");
    private String endPointTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.endPointTitle");
    private String endPointText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.endPointText");
    private String endPointColon = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.endPointColon");
    private String fileNameHeader = UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.fileNameHeader")+"";
    private String confirmedStatusTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.confirmedStatusTitle");
    private String failedStatusTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.failedStatusTitle");
    private String inProgressStatusTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.inProgressStatusTitle");
    private String failedStatusText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.failedStatusText");
    private String confirmedStatusText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.confirmedStatusText");
    private String inProgressStatusText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.inProgressStatusText");
    private String detailPageTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.pageTitle");
    private By detailsTableColumn = By.xpath("//tbody[@id='application-ldproduct-show-component---worklist--table-tblBody']/tr/td[2]");
    private String requestTableId = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.requestTableId");
    private By duplicateGtinError = By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']");
    private By duplicateGtinPopUpHeader = By.xpath("//h2/span[text()='Error 409']");
    private String cancelButton = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.reviewFile.cancelButton");
    private String detailStartDateText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailStartDateText");
    private String detailEndDateText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailEndDateText");
    private String detailCommentText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailCommentText");
    private String detailConfirmedStatusTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailConfirmedStatusTitle");
    private String detailFailedStatusTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailFailedStatusTitle");
    private String detailInProgressStatusTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailInProgressStatusTitle");
    private String detailFailedStatusText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailFailedStatusText");
    private String detailConfirmedStatusText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailConfirmedStatusText");
    private String detailInProgressStatusText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailInProgressStatusText");
    private String detailEndPointTitle = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailEndPointTitle");
    private String detailEndPointText = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailEndPointText");
    private String detailEndPointColon = prefix + UploadProductDataToLookUpDirectoryPageIds.get("uploadproductdata.detailFile.detailEndPointColon");
    public String startDateValue;


    public String getTableHeader() throws Exception {
        waitForPageToLoad();
        return getElement(tableHeader).getText();
    }

    public String getPageTitle() throws Exception {
        waitForPageToLoad();
        return getElement(pageTitle).getText();
    }


    public String getDetailPageTitle() throws Exception {
        waitForPageToLoad();
        return getElement(detailPageTitle).getText();
    }

    public void attachFile(String filename) throws Exception {
        waitForPageToLoad();
        WebElement fileInput = getElement(fileInputId);
        fileInput.sendKeys(filepath + filename);
    }

    public void enterStartDate(String date) throws Exception {
        waitForPageToLoad();
        inputText(datepickerStartDateInput, date);
    }

    public void enterEndDate(String date) throws Exception {
        waitForPageToLoad();
        inputText(datepickerEndDateInput, date);
    }

    public void enterComment(String comment) throws Exception {
        waitForPageToLoad();
        inputText(commentsTextArea, comment);
    }

    public List<String> getHistoryData() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<String> historyData = new ArrayList<>();
        historyData.add(getElement(fileNameColumn).getText());
        historyData.add(getElement(uploadDateColumn).getText());
        historyData.add(getElement(commentColumn).getText());
        historyData.add(getElement(gtinCountColumn).getText());
        historyData.add(getElement(statusColumn).getText());
        return historyData;
    }

    public List<String> getReviewFileContent() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<String> fileData = new ArrayList<>();
        fileData.add(getElement(startDateText).getText());
        fileData.add(getElement(endDateText).getText());
        fileData.add(getElement(commentText).getText());
        return fileData;
    }
    public List<String> getDetailFileContent() throws Exception {
        waitForPageToLoad();
        waitForTableToLoad();
        List<String> fileData = new ArrayList<>();
        fileData.add(getElement(detailStartDateText).getText());
        fileData.add(getElement(detailEndDateText).getText());
        fileData.add(getElement(detailCommentText).getText());
        return fileData;
    }


    public List<UploadProductToLookUpDirectory> getDetails(int index) throws Exception {
        waitForTableToLoad();
        Thread.sleep(10000);
        List<UploadProductToLookUpDirectory> logs = new ArrayList<>();
        List<WebElement> tableData = getTableRows(By.id(requestTableId));

        WebElement row = getTableRows(By.id(requestTableId)).get(index);
        List<WebElement> cells = row.findElements(By.tagName("td"));
        UploadProductToLookUpDirectory log = new UploadProductToLookUpDirectory()
                .withGtin(cells.get(1).getText())
                .withDescription(cells.get(2).getText())
                .withResponseCode(cells.get(3).getText())
                .withResponseDetail(cells.get(4).getText())
                .withUploadStatus(cells.get(5).getText());
        logs.add(log);

        //  }
        return logs;
    }

    public String getNotesText() throws Exception {
        waitForPageToLoad();
        return getElement(NoteText).getText();
    }

    public String getMandatoryErrorMessage() throws Exception {
        return getElement(mandatoryErrorMessage).getText();
    }

    public void selectStartDate() throws Exception {
        waitForPageToLoad();
        getElement(datePickerStartDateCalender).click();
        getElement(startDate).click();

    }

    public void selectEndDate() throws Exception {
        waitForPageToLoad();
        getElement(datePickerEndDateCalender).click();
        getElement(nextMonth).click();
        getElement(endDate).click();

    }

    public boolean isEndpointDetailDisplayed(String name) throws Exception {
        waitForPageToLoad();
        if(name.equals("review")) {
            if (getElement(endPointText).isDisplayed() && getElement(endPointTitle).isDisplayed() && getElement(endPointColon).isDisplayed()) {
                return true;
            } else {
                return false;
            }
        }
        else{
            if (getElement(detailEndDateText).isDisplayed() && getElement(detailEndPointTitle).isDisplayed() && getElement(detailEndPointColon).isDisplayed()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String getFileNameHeader() throws Exception {
        waitForPageToLoad();
        String filename= getElement(fileNameHeader).getText();
        return filename;
    }

    public boolean isStatusTextDisplayed(String name) throws Exception {
        if(name.equals("review")) {
            if (getElement(confirmedStatusTitle).isDisplayed() && getElement(failedStatusTitle).isDisplayed() && getElement(inProgressStatusTitle).isDisplayed() && getElement(confirmedStatusText).isDisplayed() && getElement(failedStatusText).isDisplayed() && getElement(inProgressStatusText).isDisplayed()) {
                return true;
            } else {
                return false;
            }
        }
        else{
            if (getElement(detailConfirmedStatusTitle).isDisplayed() && getElement(detailFailedStatusTitle).isDisplayed() && getElement(detailInProgressStatusTitle).isDisplayed() && getElement(detailConfirmedStatusText).isDisplayed() && getElement(detailFailedStatusText).isDisplayed() && getElement(detailInProgressStatusText).isDisplayed()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void clickOnFirstRecord() throws Exception {
        waitForTableToLoad();
        getElement(detailsTableColumn).click();
    }

    public String getDuplicateGtinErrorMessage() throws Exception {
        waitForPageToLoad();
        return getElement(duplicateGtinError).getText();
    }

    public boolean isDuplicateGtinPopUpHeader() throws Exception {
        waitForPageToLoad();
        return getElement(duplicateGtinPopUpHeader).isDisplayed();
    }
    public void clickCancelButton() throws Exception {
        waitForPageToLoad();
        getElement(cancelButton).click();
    }
}
