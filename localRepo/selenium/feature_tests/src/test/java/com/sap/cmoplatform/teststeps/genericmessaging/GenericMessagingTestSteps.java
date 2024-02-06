package com.sap.cmoplatform.teststeps.genericmessaging;

import com.sap.cmoplatform.objects.GenericMessaging;
import com.sap.cmoplatform.pages.genericmessaging.GenericMessagingHomePage;
import io.cucumber.core.exception.CucumberException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GenericMessagingTestSteps {

    private GenericMessagingHomePage genericMessagingHomePage = new GenericMessagingHomePage();

    @When("^a message of type (TXT|PDF|XML|ZIP|ZIPPEDPDF) is sent from postman")

    public void aMessageOfTypeIsSent(String type) throws IOException {
        sendMessage(type);
    }

    private void sendMessage(String type) throws IOException {
        HttpPost post = createPostWithHeaders();
        String filepath = getFilePath(type);
        post.setEntity(getHttpEntity(filepath));
        int statusCode = execute(post);
        System.out.println("Status = " + statusCode);
    }

    private String getFilePath(String type) {
        switch (type) {
            case "PDF":
                return "src/test/resources/testfiles/httpRequests/pdf_ich4ls.xml";
            case "XML":
                return "src/test/resources/testfiles/httpRequests/xml_ich4ls.xml";
            case "TXT":
                return "src/test/resources/testfiles/httpRequests/txt_ich4ls.xml";
            case "ZIP":
                return "src/test/resources/testfiles/httpRequests/zip_ich4ls.xml";
            case "ZIPPEDPDF":
                return "src/test/resources/testfiles/httpRequests/zippedPDF_ich4ls.xml";
            default:
                throw new CucumberException("File type not supported");
        }
    }

    private int execute(HttpPost post) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(post);
        return response.getStatusLine().getStatusCode();
    }

    private HttpEntity getHttpEntity(String path) throws IOException {
        String xml = readFile(path, StandardCharsets.UTF_8);
        return new ByteArrayEntity(xml.getBytes(StandardCharsets.UTF_8));
    }

    private HttpPost createPostWithHeaders() {
        HttpPost post = new HttpPost(genericMessagingHomePage.getURI());
        post.setHeader("Authorization", "Bearer e847555c4012ae41d5d9714c53895a61");
        post.setHeader("Content-Type", "application/xml");
        post.setHeader("SENDER_PARTNER_ID", "PNASTRAZENECA20170111");
        post.setHeader("RECEIVER_PARTNER_ID", "PNCMO150ATTP");
        post.setHeader("SENDER_PNID", "PNCMO150ATTP");


//        HttpPost post = new HttpPost("https://r0134-ifl.fmsp.eu1.hana.ondemand.com/http/ich-vrs/b2b-direct/gtin/00363391180998/lot/AutoDbTest/ser/1234567890?exp=230715&linkType=verificationService&context=dscsaSaleableReturn&reqGLN=0363391100002&corrUUID={{$guid}}");
//        post.setHeader("Authorization", "Basic UDIwMDI1NDM3MzQ6UzNFMjdQSnl1NjZEQU10");
//        post.setHeader("Content-Type", "application/json");

        return post;
    }

    private String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @And("the first message is downloaded")
    public void theFirstMessageIsDownloaded() throws Exception {
        genericMessagingHomePage.clickFirstDownloadButton();
    }

    @Then("only received messages appear on GM page")
    public void onlyReceivedMessagesAppearOnGMPage() throws Exception {
        assertEquals("Receieved messages does not exist", genericMessagingHomePage.getMessageReceived(), "Received");
    }

    @And("^the user selects received from company '(.+)' detail$")
    public void theUserSelectsReceivedFromCompanyDetail(String name) throws Exception {
        genericMessagingHomePage.selectReceivedFromCompany(name);
    }

    @And("the apply filter button is clicked in the GM home page")
    public void theApplyFilterButtonIsClickedInTheGMHomePage() throws Exception {
        genericMessagingHomePage.clickGoButton();
    }

    @Then("^the results (do not contain|contains|contains only) the company details with which filter is applied$")
    public void theResultsContainsTheCompanyDetailsWithWhichFilterIsApplied(String condition, Map<String, String> expectedCompany) throws Exception {
        if (condition.equals("contains only")) {
            GenericMessaging actualCompany = genericMessagingHomePage.getGenericMessaging(expectedCompany.get("receivedFromCompany"));
            assertThat(actualCompany.getCompanyName(), is(expectedCompany.get("receivedFromCompany")));
        } else if (condition.equals("do not contain")) {
            assertThat(genericMessagingHomePage.doesCompamnyExist(expectedCompany.get("receivedFromCompany")), is(false));

        }
    }

    @And("^the date range today is input in the date picker on the GM home page filter$")
    public void theDateRangeTodayIsInputInTheDatePickerOnTheGMHomePageFilter() throws Exception {
        Calendar dt1 = Calendar.getInstance();
        dt1.add(Calendar.MONTH, 1);
        String startDate = dt1.get(Calendar.DAY_OF_MONTH) + "/" + dt1.get(Calendar.MONTH) + "/" + dt1.get(Calendar.YEAR);
        Calendar dt2 = Calendar.getInstance();
        dt2.add(Calendar.MONTH, 1);
        String toDate = dt2.get((Calendar.DAY_OF_MONTH)) + "/" + dt2.get(Calendar.MONTH) + "/" + dt2.get(Calendar.YEAR);
        genericMessagingHomePage.selectReceivedDateRange(startDate + " to " + toDate);


    }

    @Then("the results contains only the dates with which filter is applied")
    public void theResultsContainsOnlyTheDatesWithWhichFilterIsApplied() throws Exception {
        Calendar dt = Calendar.getInstance();
        dt.add(Calendar.MONTH, 1);
        String expectedDate = dt.get(Calendar.MONTH) + "/" + dt.get(Calendar.DAY_OF_MONTH) + "/" + dt.get(Calendar.YEAR);
        GenericMessaging actualDate = genericMessagingHomePage.getGenericMessagingDate(expectedDate);
        assertThat(actualDate.getReceivedDate(), is(expectedDate));
    }
}

