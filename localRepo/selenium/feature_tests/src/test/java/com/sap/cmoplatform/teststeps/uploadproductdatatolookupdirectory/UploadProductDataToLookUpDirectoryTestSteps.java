package com.sap.cmoplatform.teststeps.uploadproductdatatolookupdirectory;

import com.sap.cmoplatform.pages.uploadproductdatatolookupdirectory.UploadProductDataToLookUpDirectoryPage;
import com.sap.cmoplatform.objects.UploadProductToLookUpDirectory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;


public class UploadProductDataToLookUpDirectoryTestSteps {

    private final UploadProductDataToLookUpDirectoryPage uploadProductDataToLookUpDirectoryPage = new UploadProductDataToLookUpDirectoryPage();
    int num;
    String notesText = "* Maximum upload file size is 1 MB with a maximum of 2000 GTINs in the file \n" +
            "* File extension must be .csv \n" +
            "* Upload file must contain only two columns \n" +
            "* Column header text must be lower case:\n" +
            "     gtin - GS1 GTIN (14 characters)\n" +
            "     description - GTIN description (text string, 100 characters max) \n" +
            "* First column must be gtin, second column must be description \n" +
            "* First row of the upload file is the header row, and is ignored";

    @Then("^the title of the upload product data table is '(.+)'$")
    public void theTitleOfTheUploadProductDataTableIsTableHeader(String title) throws Exception {
        assertEquals(title, uploadProductDataToLookUpDirectoryPage.getPageTitle());
    }

    @And("the file, {string}, is attached in the upload product data page")
    public void theFileFilenameIsAttachedInTheSEMCreatePage(String filename) throws Exception {
        uploadProductDataToLookUpDirectoryPage.attachFile(filename);
    }


    @And("^the start date '(.+)' is entered$")
    public void theStartDateStartDateIsEntered(String date) throws Exception {
        uploadProductDataToLookUpDirectoryPage.inputText(By.id(uploadProductDataToLookUpDirectoryPage.datepickerStartDateInput), date);
    }

    @And("^the start date Today - 20 Years is entered$")
    public void theStartDateIsEntered() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20);
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        uploadProductDataToLookUpDirectoryPage.startDateValue = dateFormat.format(date);
        uploadProductDataToLookUpDirectoryPage.inputText(By.id(uploadProductDataToLookUpDirectoryPage.datepickerStartDateInput), uploadProductDataToLookUpDirectoryPage.startDateValue);
    }

    @And("^the comments '(.+)' is entered$")
    public void theCommentsCommentsIsEntered(String comment) throws Exception {
        Random random = new Random();
        num = random.nextInt(900) + 100;
        uploadProductDataToLookUpDirectoryPage.enterComment(comment + num);
    }

    @Then("first record in history contains LD Upload details")
    public void firstRecordInHistoryContainsLDUploadDetails(Map<String, String> expectedDetails) throws Exception {
        assertEquals(expectedDetails.get("fileName"), uploadProductDataToLookUpDirectoryPage.getHistoryData().get(0));
        assertEquals(expectedDetails.get("comment") + num, uploadProductDataToLookUpDirectoryPage.getHistoryData().get(2));
        assertEquals(expectedDetails.get("gtinCount"), uploadProductDataToLookUpDirectoryPage.getHistoryData().get(3));
        assertEquals(expectedDetails.get("status"), uploadProductDataToLookUpDirectoryPage.getHistoryData().get(4));
    }

    @Then("^the (review|detail) file details contains$")
    public void verifyReviewFileDetailsContains(String pageName, Map<String, String> expectedDetails) throws Exception {
        uploadProductDataToLookUpDirectoryPage.waitForPageToLoad();
        if (pageName.equals("review")) {
            assertEquals("File Name: " + expectedDetails.get("fileNameHeader"), uploadProductDataToLookUpDirectoryPage.getFileNameHeader());
            if (uploadProductDataToLookUpDirectoryPage.startDateValue == null) {
                assertEquals(expectedDetails.get("startDate"), uploadProductDataToLookUpDirectoryPage.getReviewFileContent().get(0));
            } else {
                assertEquals(uploadProductDataToLookUpDirectoryPage.startDateValue, uploadProductDataToLookUpDirectoryPage.getReviewFileContent().get(0));
            }
            if (expectedDetails.containsKey("endDate")) {
                assertEquals(expectedDetails.get("endDate"), uploadProductDataToLookUpDirectoryPage.getReviewFileContent().get(1));
            }
            if (expectedDetails.containsKey("comments")) {
                assertEquals(expectedDetails.get("comments") + num, uploadProductDataToLookUpDirectoryPage.getReviewFileContent().get(2));
            }


        } else if (pageName.equals("detail")) {

            assertEquals(expectedDetails.get("startDate"), uploadProductDataToLookUpDirectoryPage.getDetailFileContent().get(0));
            if (expectedDetails.containsKey("endDate")) {
                assertEquals(expectedDetails.get("endDate"), uploadProductDataToLookUpDirectoryPage.getDetailFileContent().get(1));
            }
            if (expectedDetails.containsKey("comments")) {
                //  assertEquals(expectedDetails.get("comments") + num, uploadProductDataToLookUpDirectoryPage.getDetailFileContent().get(2));
            }

        }
        assertTrue(uploadProductDataToLookUpDirectoryPage.isEndpointDetailDisplayed(pageName));
        if (expectedDetails.containsKey("status")) {
            assertTrue(uploadProductDataToLookUpDirectoryPage.isStatusTextDisplayed(pageName));
        }

    }

    @Then("^the (first|second|third|fourth|fifth|sixth) record of review file items contains$")
    public void verifyReviewFileItemsContains(String id, Map<String, String> expectedDetails) throws Exception {
        List<UploadProductToLookUpDirectory> details = null;
        if (id.equals("first")) {
            details = uploadProductDataToLookUpDirectoryPage.getDetails(0);
        }
        if (id.equals("second")) {
            details = uploadProductDataToLookUpDirectoryPage.getDetails(1);
        }
        if (id.equals("third")) {
            details = uploadProductDataToLookUpDirectoryPage.getDetails(2);
        }
        if (id.equals("fourth")) {
            details = uploadProductDataToLookUpDirectoryPage.getDetails(3);
        }
        if (id.equals("fifth")) {
            details = uploadProductDataToLookUpDirectoryPage.getDetails(4);
        }
        if (id.equals("sixth")) {
            details = uploadProductDataToLookUpDirectoryPage.getDetails(5);
        }

        // for (UploadProductToLookUpDirectory actualDetails : details) {
        assertEquals(expectedDetails.get("GTIN"), details.get(0).getGtin());
        if (expectedDetails.containsKey("Description")) {
            assertEquals(expectedDetails.get("Description"), details.get(0).getDescription());
        }
        if (expectedDetails.containsKey("responseCode")) {
            assertEquals(expectedDetails.get("responseCode"), details.get(0).getResponseCode());
        }
        if (expectedDetails.containsKey("responseDetail")) {
            assertEquals(expectedDetails.get("responseDetail"), details.get(0).getResponseDetail());

        }
        if (expectedDetails.containsKey("uploadStatus")) {
            assertEquals(expectedDetails.get("uploadStatus"), details.get(0).getUploadStatus());
        }

        //  }
        //   assertEquals(expectedDetails.get("GTIN"),uploadProductDataToLookUpDirectoryPage.getReviewFileItemsTableData().get(0));
//        assertEquals(expectedDetails.get("Description"),uploadProductDataToLookUpDirectoryPage.getReviewFileItemsTableData().get(1));
//        if(expectedDetails.containsKey("responseCode")){
//            assertEquals(expectedDetails.get("responseCode"),uploadProductDataToLookUpDirectoryPage.getReviewFileItemsTableData().get(2));
//        }
//        if(expectedDetails.containsKey("responseDetail")){
//            assertEquals(expectedDetails.get("responseDetail"),uploadProductDataToLookUpDirectoryPage.getReviewFileItemsTableData().get(3));
//
//        }
//        assertEquals(expectedDetails.get("uploadStatus"),uploadProductDataToLookUpDirectoryPage.getReviewFileItemsTableData().get(4));

    }


    @Then("the title of the History table is {string}")
    public void theTitleOfTheHistoryTableIsHistoryHeader(String title) throws Exception {
        uploadProductDataToLookUpDirectoryPage.getElement(By.xpath("//*[text()='History']")).isDisplayed();
        String actTitle = uploadProductDataToLookUpDirectoryPage.getElement(By.xpath("//*[@id='application-ldproduct-show-component---worklist--tableHeader-inner']")).getText();
        assertEquals(title, uploadProductDataToLookUpDirectoryPage.getTableHeader());
    }

    @Then("the note section contains text")
    public void theNoteSectionContainsText() throws Exception {
        assertEquals(notesText, uploadProductDataToLookUpDirectoryPage.getNotesText());
    }

    @Then("the error message contains {string}")
    public void theErrorMessageContainsErrorMessage(String errorMessage) throws Exception {
        assertEquals(errorMessage, uploadProductDataToLookUpDirectoryPage.getMandatoryErrorMessage());
    }

    @And("the start date is selected from calender")
    public void theStartDateIsSelectedFromCalender() throws Exception {
        uploadProductDataToLookUpDirectoryPage.selectStartDate();
    }

    @And("the end date is selected from the calender")
    public void theEndDateIsSelectedFromTheCalender() throws Exception {
        uploadProductDataToLookUpDirectoryPage.selectEndDate();
    }


    @Then("detail page title {string} is displayed")
    public void detailPageTitleDetailPageTitleIsDisplayed(String title) throws Exception {
        assertEquals(uploadProductDataToLookUpDirectoryPage.getDetailPageTitle(), title);
    }

    @And("click on first record in history table")
    public void clickOnFirstRecordInHistoryTable() throws Exception {
        uploadProductDataToLookUpDirectoryPage.clickOnFirstRecord();
    }


    @And("^the end date '(.+)' is entered$")
    public void theEndDateEndDateIsEntered(String endDate) throws Exception {
        uploadProductDataToLookUpDirectoryPage.enterEndDate(endDate);
    }

    @Then("^the error message '(.+)' is Displayed$")
    public void theErrorMessageDuplicateGTINErrorIsDisplayed(String errorMessage) throws Exception {
        assertTrue(uploadProductDataToLookUpDirectoryPage.isDuplicateGtinPopUpHeader());
        assertEquals(errorMessage, uploadProductDataToLookUpDirectoryPage.getDuplicateGtinErrorMessage());
    }

    @And("the cancel button is clicked")
    public void theCancelButtonIsClicked() throws Exception {
        uploadProductDataToLookUpDirectoryPage.clickCancelButton();
    }

    @And("Upload to Lookup Directory is clicked")
    public void uploadToLookupDirectoryIsClicked() throws Exception {
        uploadProductDataToLookUpDirectoryPage.clickButton("Upload to Lookup Directory");
    }
}