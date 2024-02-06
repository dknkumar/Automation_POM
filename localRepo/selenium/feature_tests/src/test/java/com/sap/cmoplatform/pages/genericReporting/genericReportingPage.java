package com.sap.cmoplatform.pages.genericReporting;

import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.teststeps.common.CommonTestSteps;
import com.sap.cmoplatform.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Objects;
import java.util.Properties;

import static org.junit.Assert.*;

public class genericReportingPage extends Page {
    private final By filterArtifactID = By.xpath("//*[contains(@placeholder,'Name, Id')]");
    private final By timeFilterLabel = By.id("MsgTimeSelect-label");
    private final By filterArtifactPrompt = By.xpath("//*/span/span[text()='ICH_DataExchange_NHRA_JMS_2305_0_0'']");
    private final By attributeDetailsSection = By.xpath("//*/bdi[contains(@id,'mplArtifactDetailsSection')]");
    private final By manageIntegrationContent = By.xpath("//*[contains(text(),'Manage Integration Content')]");
    private final By logLevel = By.id("selLogLevel");
    private final By change = By.xpath("//*[text()='Change']");
    private final By alertDialog = By.xpath("//*[@role='alertdialog']");
    private final By overview = By.id("monitoring-a");
    private final By integrationFlows = By.xpath("//*[contains(@aria-label,'All Integration Flows')][1]");
    private final By correlIDfilter = By.id("MsgIDSelect-I");
    private final By monitorMessageApp = By.xpath("//*[contains(text(),'Monitor Message Processing')]");
    private final String correlIDSearch = "MsgIDSelect-search";
    private final String msgCount = "PanelMessagesTitle-bdi";
    private final By messageDetails = By.id("MESSAGES_OBJECTPAGELAYOUT-anchBar-mplpropertiessection-anchor-BDI-content");
    private final By messageLogs = By.id("MESSAGES_OBJECTPAGELAYOUT-anchBar-sectionLogs-anchor-BDI-content");
    private final By msgProcessingRun = By.xpath("//*[contains(text(),'Message Processing Run')]");
    private final By receiverID = By.xpath("//span[@id='MESSAGES_INFO_RECEIVER']");
    private final By CORRELATIONID = By.xpath("//span[@id='MESSAGES_INFO_CORRELATION_ID']");
    private final By trace = By.xpath("//a[text()='Trace']");
    private final By msgContent = By.xpath("//*[text()='Message Content']");
    private final By payloadSection = By.xpath("//bdi[contains(@id,'MPLSTEPTRACE_PAYLOAD_SECTION')]");
    private final By download = By.xpath("//*[text()='Download Payload']");
    private final By msgContentPayload = By.id("MSG_CONTENT_PAYLOAD");
    public static Properties configGenreicReporting = PropertyReader.loadProperties("genericReporting/genericReporting.properties");

    public void filterArtifact(String artifact) throws Exception {
        //Filter to manage the Artifact
        waitForPageToLoad();
        WebElement time = portalDriver.findElement(timeFilterLabel);
        selectDropDownFilterInput(time, "All");
        waitForPageToLoad();
        portalDriver.findElement(filterArtifactID).clear();
        inputText(filterArtifactID, artifact);
        portalDriver.findElement(filterArtifactID).sendKeys(Keys.ENTER);
    }

    public void manageArtifactEnableTrace(String artifact) throws Exception {
        //Manage Artifact and Enable Trace logs
        waitForTableToLoad();
        clickElement(By.xpath("//*[text()='" + artifact + "'][contains(@id,'MESSAGES_TABLE-0')]"));
        clickElement(attributeDetailsSection);
        clickElement(manageIntegrationContent);
        waitForPageToLoad();
        scrollToElement(logLevel);

        String currLogLevel = portalDriver.findElement(logLevel).getText();
        if (Objects.equals(currLogLevel, "Trace\nTrace")) {
            System.out.println("Log Level for" + artifact + "is set to :" + portalDriver.findElement(logLevel).getText());
        } else {
            System.out.println("Log Level for" + artifact + "is set to :" + portalDriver.findElement(logLevel).getText());
            selectDropDownFilterInput(portalDriver.findElement(logLevel), "Trace");
            clickElement(change);
        }
    }

    public boolean onMonitorMessageProcessing() throws Exception {
        waitForPageToLoad();
        return doesElementExist(monitorMessageApp);
    }

    public Response httpReq(String url, File payload) throws Exception {
        KeyStore keyStore = null;
        SSLConfig config = null;

        keyStore = KeyStore.getInstance("JKS");
        String keyStoreFilepath = System.getProperty("user.dir") + configGenreicReporting.getProperty("keystoreFilePath");
        keyStore.load(new FileInputStream(keyStoreFilepath), configGenreicReporting.getProperty("keystorePass").toCharArray());
        SSLSocketFactory auth = new SSLSocketFactory(keyStore, configGenreicReporting.getProperty("keystorePass"));
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().with().sslSocketFactory(auth).and().allowAllHostnames());

        return RestAssured.
                given().contentType(ContentType.XML).log().all().urlEncodingEnabled(false)
                .body(payload).when().post(url).then().extract().response();
    }

    public String oauthToken(String url, String authcode) throws Exception {
        Response oauthReponse = RestAssured.given()
                .header("Authorization", "Basic " + authcode)
                .queryParam("grant_type", "client_credentials")
                .log().all().when().
                post(url).then().extract().response();
        return oauthReponse.jsonPath().getString("access_token");
    }

    public void updatePNID(String PNID, String token, String url) throws Exception {
        Response resp = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .log().all().urlEncodingEnabled(false)
                .body("{\"Pid\":\"" + PNID + "\"}").when()
                .put(url).then().extract().response();
        assertEquals(204, resp.getStatusCode());
    }

    public void navigateToMonitorMessageProcessingApp() throws Exception {
        clickElement(overview);
        waitForPageToLoad();
        clickElement(integrationFlows);
        waitForPageToLoad();
        onMonitorMessageProcessing();
    }

    public void searchCorrelID(String correlID) throws Exception {
        inputText(correlIDfilter, correlID);
        clickElement(correlIDSearch);
        waitForPageToLoad();
    }

    public void validateCorrelID(String correlID) throws Exception {
        searchCorrelID(correlID);

        //Get total messages for the respective CorrelationID
        String messageCount = getElement(msgCount).getText();
        messageCount = messageCount.replaceAll("[^\\d.]", "");
        int messageCountNo = Integer.parseInt(messageCount);
        System.out.println("Message count for correlID is: " + messageCountNo);

        //Validate Status as Completed for all Messages
        for (int i = 0; i < messageCountNo; i++) {
            String status = getElement(By.xpath("//*[contains(@id,'MESSAGES_TABLE-" + i + "-text')]")).getText();
            assertEquals("Completed", status);
        }

    }

    public void validateCorrelIDwoReceiverID(String correlID, String scenario) throws Exception {
        searchCorrelID(correlID);

        //Get total messages for the respective CorrelationID
        String messageCount = getElement(msgCount).getText();
        messageCount = messageCount.replaceAll("[^\\d.]", "");
        int messageCountNo = Integer.parseInt(messageCount);
        System.out.println("Message count for correlID is: " + messageCountNo);

        if (Objects.equals(scenario, "SFDA")) {
            getElement(By.xpath("//span[text()='ICH_DataExchange_GR_Product_Release_Version']/following::span[text()='Failed'][1]")).isDisplayed();
            getElement(By.xpath("//span[text()='ICH_DataExchange_GR_Transformation_2305_0_0']/following::span[text()='Failed'][1]")).isDisplayed();

            clickElement(By.xpath("//span[text()='ICH_DataExchange_GR_Product_Release_Version']"));
        }
    }

    public void navigateToTraces(String Artifact, String correlID, String RecieverID) throws Exception {
        scrollToandclickElement(By.xpath("//*[text()='" + Artifact + "']"));
        //Validate ReceiverId and CorrelationID
        clickElement(messageDetails);
        assertEquals(getElement(CORRELATIONID).getText(), correlID);
        assertEquals(getElement(receiverID).getText(), RecieverID);

        clickElement(messageLogs);
        clickElement(trace);
        waitForPageToLoad();
        doesElementExist(msgProcessingRun);
    }

    public void validateResponsePayload(String step, String segment, String status, String expDate) throws Exception {
        waitForPageToLoad();
        WebDriverWait w = new WebDriverWait(portalDriver.getConfig().getDriver(), 30);
        By stepElement = By.xpath("//*[contains(@title,'Type: " + step + "')]/../../..//*[contains(text(),'Segment " + segment + "')]");
        w.until(ExpectedConditions.presenceOfElementLocated(stepElement));
        scrollToandclickElement(stepElement);
        waitForPageToLoad();

        clickElement(msgContent);
        clickElement(payloadSection);

        //Download Payload
        clickElement(download);
        String res = getElement(msgContentPayload).getText();
        System.out.println("Response is : " + res);
        //Validate step payload
        payloadAssert(res, status, expDate);
    }

    public void payloadAssert(String payload, String status, String expDate) throws Exception {

        //Validating fields for Success Scenario
        XmlPath xmlPath = new XmlPath(payload);
        assertEquals(configGenreicReporting.getProperty("instanceid"), xmlPath.getString("envelope.content.GRResponse.Header.CorrelationId"));

        if (Objects.equals(status, "Success")) {
            assertEquals("SUCCESS", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Status"));
            assertEquals("SUCCESS", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Description"));
            assertEquals("S", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckClass"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[0]").contains("Message was successfully uploaded to"));
            assertEquals("No warning", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[1]"));
            assertEquals("SUCCESS", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.Description"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].GTIN"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].GTIN"));
            assertEquals(configGenreicReporting.getProperty("BNSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].BN"));
            assertEquals(configGenreicReporting.getProperty("BNSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].BN"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].SN").contains(configGenreicReporting.getProperty("SFDAObjCode1")));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].SN").contains(configGenreicReporting.getProperty("SFDAObjCode2")));
            assertEquals("The operation performed on the product is successful.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].DESCRIPTION"));
            assertEquals("The operation performed on the product is successful.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].DESCRIPTION"));
            assertEquals(expDate, xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].XD"));
            assertEquals(expDate, xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].XD"));
            assertEquals("00000", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].RC"));
            assertEquals("00000", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].RC"));
            assertNotNull(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.NOTIFICATIONID"));
        }
        //Invalid Serial No
        else if (Objects.equals(status, "11032")) {
            assertEquals("FUNCT_ERR_RCV", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Status"));
            assertEquals("Functional Error", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Description"));
            assertEquals("E", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckClass"));
            assertEquals(configGenreicReporting.getProperty("instanceid"), xmlPath.getString("envelope.content.GRResponse.Header.CorrelationId"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[0]").contains("Error while sending message to"));
            assertEquals("No warning", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[1]"));
            assertEquals("Functional Error", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.Description"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].GTIN"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].GTIN"));
            assertEquals(configGenreicReporting.getProperty("BNSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].BN"));
            assertEquals(configGenreicReporting.getProperty("BNSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].BN"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].SN").contains(configGenreicReporting.getProperty("SFDAObjCode1")));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].SN").contains(configGenreicReporting.getProperty("SFDAObjCode2")));
            assertEquals("Format of the serial number is invalid.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].DESCRIPTION"));
            assertEquals("Format of the serial number is invalid.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].DESCRIPTION"));
            assertEquals(expDate, xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].XD"));
            assertEquals(expDate, xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].XD"));
            assertEquals("11032", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[0].RC"));
            assertEquals("11032", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.PRODUCTLIST.PRODUCT[1].RC"));
            assertNotNull(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.AcceptServiceResponse.NOTIFICATIONID"));
        }
        //Invalid GTIN
        if (Objects.equals(status, "11004")) {
            assertEquals("FUNCT_ERR_RCV", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Status"));
            assertEquals("Undefined Drug (GTIN does not exist).", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Description"));
            assertEquals("E", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckClass"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[0]").contains("Error while sending message to"));
            assertEquals("No warning", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[1]"));
            assertEquals("Undefined Drug (GTIN does not exist).", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.Description"));
            assertEquals("11004", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.ErrorCode"));
        }
        //Invalid GTIN
        if (Objects.equals(status, "deactivateFailure")) {
            assertEquals("PAYLOAD", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Status"));
            assertEquals("Processed successfully", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Description"));
            assertEquals("E", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckClass"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[0]").contains("Error while sending message to"));
            assertEquals("30", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAPayloadOriginal.DeactivationServiceRequest.DR"));
            assertEquals("STOLEN", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAPayloadOriginal.DeactivationServiceRequest.EXPLANATION"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAPayloadOriginal.DeactivationServiceRequest.PRODUCTLIST.PRODUCT[0].GTIN"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAPayloadOriginal.DeactivationServiceRequest.PRODUCTLIST.PRODUCT[1].GTIN"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAPayloadOriginal.DeactivationServiceRequest.PRODUCTLIST.PRODUCT[0].SN").contains(configGenreicReporting.getProperty("SFDAObjCode1")));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAPayloadOriginal.DeactivationServiceRequest.PRODUCTLIST.PRODUCT[1].SN").contains(configGenreicReporting.getProperty("SFDAObjCode2")));
            assertEquals(configGenreicReporting.getProperty("BNSFDA.deactivate"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAPayloadOriginal.DeactivationServiceRequest.PRODUCTLIST.PRODUCT[0].BN"));
            assertEquals(configGenreicReporting.getProperty("BNSFDA.deactivate"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAPayloadOriginal.DeactivationServiceRequest.PRODUCTLIST.PRODUCT[1].BN"));
        }
        //export Failure
        if (Objects.equals(status, "exportFailure_11813")) {
            assertEquals("FUNCT_ERR_RCV", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Status"));
            assertEquals("Export to current country is forbidden", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Description"));
            assertEquals("E", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckClass"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[0]").contains("Error while sending message to"));
            assertEquals("No warning", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[1]"));
            assertEquals("Export to current country is forbidden", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.Description"));
            assertEquals("11813", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.ErrorCode"));
        }
        //import Failure
        if (Objects.equals(status, "importFailure_21017")) {
            assertEquals("FUNCT_ERR_RCV", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Status"));
            assertEquals("Stakeholder is unauthorized for this operation", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Description"));
            assertEquals("E", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckClass"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[0]").contains("Error while sending message to"));
            assertEquals("No warning", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[1]"));
            assertEquals("Stakeholder is unauthorized for this operation", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.Description"));
            assertEquals("21017", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.ErrorCode"));
        }
        //supply Success
        if (Objects.equals(status, "supplySuccess")) {
            assertEquals("SUCCESS", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Status"));
            assertEquals("SUCCESS", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Description"));
            assertEquals("S", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckClass"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[0]").contains("Message was successfully uploaded to"));
            assertEquals("No warning", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[1]"));
            assertEquals("SUCCESS", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.Description"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.GTIN"));
            assertEquals(configGenreicReporting.getProperty("BNSFDA.supply"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.BN"));
            assertEquals(expDate, xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.XD"));
            assertNotNull(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.NOTIFICATIONID"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[0].SN").contains(configGenreicReporting.getProperty("SFDAObjCode1")));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[1].SN").contains(configGenreicReporting.getProperty("SFDAObjCode2")));
            assertEquals("The operation performed on the product is successful.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[0].DESCRIPTION"));
            assertEquals("The operation performed on the product is successful.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[1].DESCRIPTION"));
            assertEquals("00000", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[0].RC"));
            assertEquals("00000", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[1].RC"));
        }
        //invalid Exp date
        if (Objects.equals(status, "supplySuccess")) {
            assertEquals("SUCCESS", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Status"));
            assertEquals("SUCCESS", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Description"));
            assertEquals("S", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckClass"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[0]").contains("Message was successfully uploaded to"));
            assertEquals("No warning", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[1]"));
            assertEquals("SUCCESS", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.Description"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.GTIN"));
            assertEquals(configGenreicReporting.getProperty("BNSFDA.supply"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.BN"));
            assertEquals(expDate, xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.XD"));
            assertNotNull(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.NOTIFICATIONID"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[0].SN").contains(configGenreicReporting.getProperty("SFDAObjCode1")));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[1].SN").contains(configGenreicReporting.getProperty("SFDAObjCode2")));
            assertEquals("The operation performed on the product is successful.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[0].DESCRIPTION"));
            assertEquals("The operation performed on the product is successful.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[1].DESCRIPTION"));
            assertEquals("00000", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[0].RC"));
            assertEquals("00000", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.SupplyServiceResponse.SNRESPONSELIST.SNRESPONSE[1].RC"));
        }
        //Deactivate
        if (Objects.equals(status, "deactivateFailure")) {
            assertEquals("FUNCT_ERR_RCV", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Status"));
            assertEquals("Functional Error", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Description"));
            assertEquals("E", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckClass"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[0]").contains("Error while sending message to"));
            assertEquals("No warning", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Acknowledgment.AckLog.AckLogMsg[1]"));
            assertEquals("Functional Error", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.Description"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[0].GTIN"));
            assertEquals(configGenreicReporting.getProperty("GTINSFDA"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[1].GTIN"));
            assertEquals(configGenreicReporting.getProperty("BNSFDA.deactivate"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[0].BN"));
            assertEquals(configGenreicReporting.getProperty("BNSFDA.deactivate"), xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[1].BN"));
            assertEquals(expDate, xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[0].XD"));
            assertEquals(expDate, xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[1].XD"));
            assertNotNull(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.NOTIFICATIONID"));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[0].SN").contains(configGenreicReporting.getProperty("SFDAObjCode1")));
            assertTrue(xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[1].SN").contains(configGenreicReporting.getProperty("SFDAObjCode2")));
            assertEquals("The operation performed on the product is successful.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[0].DESCRIPTION"));
            assertEquals("The operation performed on the product is successful.", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[1].DESCRIPTION"));
            assertEquals("10201", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[0].RC"));
            assertEquals("10201", xmlPath.getString("envelope.content.GRResponse.Body.ResponseStatus.Content.SAResponseOriginal.log.DeactivationServiceResponse.PRODUCTLIST.PRODUCT[1].RC"));
        }
    }

}