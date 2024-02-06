package com.sap.cmoplatform.teststeps.sem;

import com.sap.cmoplatform.pages.sem.SemDetailsPage;
import com.sap.cmoplatform.pages.sem.SemHomePage;
import com.sap.cmoplatform.objects.Sem;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SemEditTestSteps {

    private SemHomePage semHomePage = new SemHomePage();
    private SemDetailsPage semDetailsPage = new SemDetailsPage();
    private Sem currentMessage = new Sem();

    private static final Logger LOGGER = LogManager.getLogger(new Object() {
    }.getClass().getEnclosingClass().getName());

    @And("^the edit button is clicked in the SEM details page$")
    public void clickEditButton() throws Exception {
        semDetailsPage.clickEditButton();
    }

    @And("^the customer '(.+)' is selected in the SEM details page$")
    public void selectCustomer(String customer) throws Exception {
        semDetailsPage.selectCustomer(customer);
    }

    @And("^the product '(.+)' is selected in the SEM details page$")
    public void selectProduct(String product) throws Exception {
        semDetailsPage.selectProduct(product);
    }

    @And("the Batch ID, {string}, is entered on the SEM details Page")
    public void theBatchIDBatchIdIsEnteredOnTheSEMDetailsPage(String batchNumber) throws Exception {
        semDetailsPage.enterBatchNumber(batchNumber);
    }

    @And("message type, {string}, is selected in the SEM details page")
    public void selectMessageFormat(String messageType) throws Exception {
        semDetailsPage.selectMessageTypeFromDropdown(messageType);
    }

    @And("the file, {string}, is attached in the SEM detail page")
    public void theFileFilenameIsAttachedInTheSEMDetailPage(String filename) throws Exception {
        semDetailsPage.attachFile(filename);
    }

    @And("^the send button is clicked in the SEM details page$")
    public void clickSendButton() throws Exception {
        semDetailsPage.clickSend();
    }

    @Then("^the message info is read in the SEM details page$")
    public void readMessageInfo() throws Exception {
        currentMessage = semDetailsPage.getCurrentMessageInfo();
    }

    @Then("^the message has been updated from the SEM Details page$")
    public void checkIfChangesAreSaved() throws Exception {
        semHomePage.selectCustomer(currentMessage.getCustomer());
        semHomePage.selectProduct(currentMessage.getProduct());
        semHomePage.selectStatus(currentMessage.getStatus());
        semHomePage.clickGoButton();
        List<Sem> messages = semHomePage.getSems();
        boolean doesMessageExist = false;
        for (Sem message : messages) {
            LOGGER.info(String.format("%s - %s", message.getCustomer(), currentMessage.getCustomer()));
            LOGGER.info(String.format("%s - %s", message.getProduct(), currentMessage.getProduct()));
            LOGGER.info(String.format("%s - %s", message.getIdentifierType(), currentMessage.getIdentifierType()));
            LOGGER.info(String.format("%s - %s", message.getIdentifier(), currentMessage.getIdentifier()));
            if (
                    message.getCustomer().equals(currentMessage.getCustomer()) &&
                            message.getIdentifierType().equals(currentMessage.getIdentifierType()) &&
                            message.getIdentifier().equals(currentMessage.getIdentifier()) &&
                            message.getProduct().equals(currentMessage.getProduct())
            ) {
                doesMessageExist = true;
                break;
            }
        }
        assertThat("Message does not exist", doesMessageExist, is(true));
    }

    @And("^the download attachment button is clicked$")
    public void checkDownloadButton() throws Throwable {
        semDetailsPage.clickDownload();
    }

    @And("^the SEM message status is Sent or Received$")
    public void checkMessageStatus() throws Throwable {
        String status = semDetailsPage.getStatus();
        boolean sentOrReceived = false;

        if (status.equals("Sent") || status.equals("Received")) {
            sentOrReceived = true;
        }

        assertThat("Status is not sent or received. Actual status is " + status + ".", sentOrReceived, is(true));
    }

    @And("the save draft button is clicked in the SEM details page")
    public void theSaveDraftButtonIsClickedInTheSEMDetailsPage() throws Exception {
        semDetailsPage.clickSaveDraftButton();
    }

    @And("check all fields are reset in the SEM details page")
    public void checkAllFieldsAreResetInTheSEMDetailsPage() throws Exception {
        assertEquals("", semDetailsPage.getProductDescription());
        assertEquals("", semDetailsPage.getBatchNumber());
        assertEquals(semDetailsPage.getDefaultMessageType(), semDetailsPage.getMessageType());
        assertEquals("", semDetailsPage.getFilename());
    }

    @And("the receiver GLN {string} is selected from the receiver GLN list in the SEM details page")
    public void theReceiverGLNReceiverGlnIsSelectedFromTheReceiverGLNListInTheSEMDetailsPage(String gln) throws Exception {
        semDetailsPage.selectReceiverGln(gln);
    }

    @And("the sender GLN {string} is selected from the sender GLN list in the SEM details page")
    public void theSenderGLNSenderGlnIsSelectedFromTheSenderGLNListInTheSEMDetailsPage(String gln) throws Exception {
        semDetailsPage.selectSenderGln(gln);
    }

    @Then("the fields are not editable in the SEM details page")
    public void theFieldsAreNotEditableInTheSEMDetailsPage() throws Exception {
        assertThat("Customer is editable", semDetailsPage.isCustomerUneditable(), is(true));
        assertThat("Product is editable", semDetailsPage.isProductUneditable(), is(true));
        assertThat("Batch number is editable", semDetailsPage.isBatchNumberUneditable(), is(true));
        assertThat("Message type is editable", semDetailsPage.isMessageTypeUneditable(), is(true));
        assertThat("File is editable", semDetailsPage.isFileUneditable(), is(true));
    }

    @And("the batch id is {string}")
    public void checkBatchidIsChars(String batchid) throws Exception {
        assertEquals("Batch id is incorrect", batchid, semDetailsPage.getBatchNumber());
    }

    @And("^there is a red border around the (product|receiver GLN|batch id|message type|file) field on SEM details page$")
    public void thereIsARedBorderAroundTheProductFieldOnSEMDetailsPage(String fieldName) throws Exception {
        assertThat("Red error border does not appear", semDetailsPage.checkErrorBorder(fieldName), is(true));
    }

    @Then("^the batch number only contains valid chars in the SEM details page$")
    public void checkBatchNumber() throws Exception {
        String batchNumber = semDetailsPage.getBatchNumber();
        assertThat(batchNumber.matches("[0-9a-zA-Z]{1,20}"), is(true));
    }

    @And("the attached file is {string}")
    public void theAttachedFileIsFile(String file) throws Exception {
        assertEquals("File is incorrect", file, semDetailsPage.getFilename());
    }

    @And("the back button is clicked in the SEM details page")
    public void theBackButtonIsClickedInTheSEMDetailsPage() throws Exception {
        semDetailsPage.clickBackButton();
    }

    @And("the cancel button is clicked in the SEM details page")
    public void theCancelButtonIsClickedInTheSEMDetailsPage() throws Exception {
        semDetailsPage.clickCancelButton();
    }
}

