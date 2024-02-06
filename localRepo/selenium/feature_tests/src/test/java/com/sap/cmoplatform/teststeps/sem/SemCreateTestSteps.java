package com.sap.cmoplatform.teststeps.sem;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.sem.SemCreatePage;
import com.sap.cmoplatform.pages.sem.SemHomePage;
import com.sap.cmoplatform.objects.Sem;
import com.sap.cmoplatform.utils.PortalDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SemCreateTestSteps {

    private SemHomePage semMainPage = new SemHomePage();
    private SemCreatePage semCreatePage = new SemCreatePage();
    private Sem currentMessage = new Sem();

    @When("^the new message button is clicked in the SEM create page$")
    public void when_the_new_message_button_is_clicked() throws Exception {
        Thread.sleep(30000);
        semMainPage.clickButton("New Message");
    }

    @Then("^the batch number only contains valid chars in the SEM create page$")
    public void checkBatchNumber() throws Exception {
        String batchNumber = semCreatePage.getBatchNumber();
        assertThat(batchNumber.matches("[0-9a-zA-Z]{1,20}"), is(true));
    }

    @When("^the send button is clicked in the SEM create page$")
    public void the_send_button_is_clicked() throws Throwable {
        semCreatePage.clickSend();
    }

    @And("^the customer '(.+)' is selected in the SEM create page$")
    public void selectCustomer(String customer) throws Exception {
        semCreatePage.selectCustomer(customer);
    }

    @And("^the product '(.+)' is selected in the SEM create page$")
    public void selectProduct(String product) throws Exception {
        semCreatePage.selectProduct(product);
    }

    @And("^the message type, '(.+)', is selected on the SEM Create Page$")
    public void setMessageTypeValue(String messageType) throws Exception {
        semCreatePage.selectMessageTypeFromDropdown(messageType);
    }

    @And("^the Batch ID, '(.+)', is entered on the SEM Create Page$")
    public void setBatchIDValue(String batchID) throws Exception {
        semCreatePage.enterBatchId(batchID);
    }

    @Then("^the message info is read in the SEM create page$")
    public void readMessageInfo() throws Exception {
        currentMessage = semCreatePage.getCurrentMessageInfo();
    }

    @And("^the save draft button is clicked in the SEM create page$")
    public void theSaveDraftButtonIsClickedInTheSEMCreatePage() throws Throwable {
        semCreatePage.clickSaveDraftButton();
    }

    @And("the file, {string}, is attached in the SEM create page")
    public void theFileFilenameIsAttachedInTheSEMCreatePage(String filename) throws Exception {
        semCreatePage.attachFile(filename);
    }

    @And("the sender GLN {string} is selected from the sender GLN list in the SEM create page")
    public void theSenderGLNSenderGlnIsSelectedFromTheSenderGLNListInTheSEMDetailsPage(String gln) throws Exception {
        semCreatePage.selectSenderGln(gln);
    }

    @And("the receiver GLN {string} is selected from the receiver GLN list in the SEM create page")
    public void theReceiverGLNReceiverGlnIsSelectedFromTheReceiverGLNListInTheSEMDetailsPage(String gln) throws Exception {
        semCreatePage.selectReceiverGln(gln);
    }

    @And("the confirm window contains the SEM details")
    public void theConfirmWindowContainsTheSEMDetails(Map<String, String> semDetails) throws Exception {
        Sem actual = semCreatePage.getSemDetailsFromFragment();
        Sem expected = new Sem()
                .withCustomer(semDetails.get("customer"))
                .withSenderGln(semDetails.get("senderGln"))
                .withReceiverGln(semDetails.get("receiverGln"))
                .withProduct(semDetails.get("product"))
                .withBatchId(semDetails.get("batchId"))
                .withMessageType(semDetails.get("messageType"))
                .withFileName(semDetails.get("filename"));
        assertEquals("Customer does not match", expected.getCustomer(), actual.getCustomer());
        assertEquals("Sender GLN does not match", expected.getSenderGln(), actual.getSenderGln());
        assertEquals("Receiver GLN does not match", expected.getReceiverGln(), actual.getReceiverGln());
        assertEquals("Product does not match", expected.getProduct(), actual.getProduct());
        assertEquals("Batch ID does not match", expected.getBatchId(), actual.getBatchId());
        assertEquals("Message Type does not match", expected.getMessageType(), actual.getMessageType());
        assertEquals("File does not match", expected.getFileName(), actual.getFileName());

    }

    @And("the cancel button is clicked in the SEM create page")
    public void theCancelButtonIsClickedInTheSEMCreatePage() throws Exception {
        semCreatePage.clickCancelButton();
    }

    @And("the back button is clicked in the SEM create page")
    public void theBackButtonIsClickedInTheSEMCreatePage() throws Exception {
        semCreatePage.clickBackButton();
    }
//
//    @And("^there is a red border around the (customer|product|sender GLN|receiver GLN|batch id|message type|file) field on SEM create page$")
//    public void thereIsARedBorderAroundTheProductFieldOnSEMDetailsPage(String fieldName) throws Exception {
//        assertThat("Red error border does not appear", semCreatePage.checkErrorBorder(fieldName), is(true));
//    }

    @And("all fields are empty")
    public void allFieldsAreEmpty() throws Exception {
        assertEquals("", semCreatePage.getCustomer());
        assertEquals("", semCreatePage.getBatchNumber());
        assertEquals("", semCreatePage.getMessageType());
        assertEquals("", semCreatePage.getFile());
    }

    @And("the {string} field is empty")
    public void senderGLNFieldIsEmpty(String field) throws Exception {
        switch (field) {
            case "sender GLN":
                assertEquals("", semCreatePage.getSenderGln());
                break;
            case "receiver GLN":
                assertEquals("", semCreatePage.getReceiverGln());
                break;
            case "product":
                assertEquals("", semCreatePage.getSenderGln());
                break;
            case "batch id":
                assertEquals("", semCreatePage.getSenderGln());
                break;
            case "message type":
                assertEquals("-- Select Message Type --", semCreatePage.getMessageType());
                break;
            case "file":
                assertEquals("", semCreatePage.getFile());
                break;
        }
    }

    @And("^the refresh button is clicked on the SEM master page$")
    public void the_refresh_button_is_clicked_on_the_sem_master_page() throws Exception {
        semCreatePage.clickRefreshBtn();

    }

}