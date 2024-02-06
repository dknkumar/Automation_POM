package com.sap.cmoplatform.teststeps.genericReporting;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.genericReporting.genericReportingPage;
import com.sap.cmoplatform.teststeps.common.CommonTestSteps;
import com.sap.cmoplatform.utils.ModifyXml;
import com.sap.cmoplatform.utils.PortalDriver;
import com.sap.cmoplatform.utils.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class genericReportingTestSteps {
    private static final genericReportingPage bahrainIflowsPage = new genericReportingPage();
    private static Properties configGenreicReporting = PropertyReader.loadProperties("genericReporting/genericReporting.properties");
    private static String artifcatCorrelID;
    private static String ReceiverID;
    private static final String InstanceID = configGenreicReporting.getProperty("instanceid");
    private static final String generatedString = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    private static final String ObjCode1 = "01" + configGenreicReporting.getProperty("GTINSFDA") + "21" + configGenreicReporting.getProperty("SFDAObjCode1") + generatedString;
    private static final String ObjCode2 = "01" + configGenreicReporting.getProperty("GTINSFDA") + "21" + configGenreicReporting.getProperty("SFDAObjCode2") + generatedString;
    private static String manufDate;
    private static String expDate;
    private static String invalidExpDate;

    @Given("the user logs in CPI")
    public void cpiLogin() throws Throwable {
        PortalDriver.getInstance();
        SeleniumUI5TestUtil.getConfig().getDriver().get(configGenreicReporting.getProperty("cpiMonitoringAppURL"));
    }

    @Given("Monitor Message Processing app is displayed")
    public void verifyApp() throws Exception {
        assertTrue(bahrainIflowsPage.onMonitorMessageProcessing());
    }

    @And("enable trace logs for {} I-flow")
    public void enableTraceLogsForIFlowArtifact(String Iflow) throws Exception {
        bahrainIflowsPage.filterArtifact(Iflow);
        bahrainIflowsPage.manageArtifactEnableTrace(Iflow);
    }

    @And("Trigger {} SOAP req and Validate status for {} {}")
    public static void triggerSOAPRequest(String scenario, String fileName, String updatedFileName) throws Exception {
        updatePNID(configGenreicReporting.getProperty("pnID" + scenario), fileName, configGenreicReporting.getProperty("filepath" + scenario), scenario);

        String url1 = configGenreicReporting.getProperty("GR.endpoint");
        String filePath = System.getProperty("user.dir") + configGenreicReporting.getProperty("filepath" + scenario) + fileName;
        String updatedFilePath = System.getProperty("user.dir") + configGenreicReporting.getProperty("filepath" + scenario) + updatedFileName;

        Calendar cal = Calendar.getInstance();
        Date cdate = cal.getTime();
        //get next year
        cal.add(Calendar.YEAR, 1);
        Date nyear = cal.getTime();
        cal.add(Calendar.YEAR, -1);
        Date pyear = cal.getTime();
        manufDate = new SimpleDateFormat("yyyy-MM-dd").format(cdate);
        expDate = new SimpleDateFormat("yyyy-MM-dd").format(nyear);
        invalidExpDate = new SimpleDateFormat("yyyy-MM-dd").format(pyear);
        //Set ReceiverID
        if (fileName.contains("supply")) {
            ReceiverID = configGenreicReporting.getProperty("receiverIDSFDASupply");
        } else if (fileName.contains("accept")) {
            ReceiverID = configGenreicReporting.getProperty("receiverIDSFDAAccept");
        } else if (fileName.contains("import")) {
            ReceiverID = configGenreicReporting.getProperty("receiverIDSFDAImport");
        } else if (fileName.contains("deactivate")) {
            ReceiverID = configGenreicReporting.getProperty("receiverIDSFDADeactivate");
        } else if (fileName.contains("dispatch")) {
            ReceiverID = configGenreicReporting.getProperty("receiverIDSFDADispatch");
        } else if (fileName.contains("export")) {
            ReceiverID = configGenreicReporting.getProperty("receiverIDSFDAExport");
        } else if (fileName.contains("upload")) {
            ReceiverID = configGenreicReporting.getProperty("receiverIDSFDAUpload");
        }
        //Update Input XML
        if (fileName.contains("multi")) {//to Update 10 Objects
            ModifyXml.modifySFDAXml10obj(filePath, updatedFilePath, ReceiverID, InstanceID, manufDate, expDate);
        } else if (fileName.contains("50K")) {//to Update 50K Objects
            ModifyXml.modifySFDAXml50Kobj(filePath, updatedFilePath, ReceiverID, InstanceID, manufDate, expDate);
        } else if (fileName.contains("woReceiverID")) {
            ModifyXml.modifySFDAXml(filePath, updatedFilePath, "", InstanceID, ObjCode1, ObjCode2, manufDate, expDate);
        } else if (fileName.contains("deactivateFailure")) {
            ModifyXml.modifySFDAXml(filePath, updatedFilePath, ReceiverID, InstanceID, ObjCode1, ObjCode2, manufDate, invalidExpDate);
        } else {
            ModifyXml.modifySFDAXml(filePath, updatedFilePath, ReceiverID, InstanceID, ObjCode1, ObjCode2, manufDate, expDate);
        }
        File xmlPayload = new File(updatedFilePath);

        Response res = bahrainIflowsPage.httpReq(url1, xmlPayload);
        if (fileName.contains("woReceiverID")) {
            //Parse and Validate XML response
            XmlPath xmlPath = new XmlPath(res.asString());
            System.out.println("correlation ID is " + res.getHeader("sap_mplcorrelationid"));
            System.out.println("Response is " + res.andReturn().asPrettyString());

            assertEquals("E", xmlPath.getString("Envelope.Body.envelope.acknowledgment.ackClass"));
            assertEquals("javax.script.ScriptException: java.lang.Exception: groovy.lang.MissingPropertyException: No such property: LOG for class: script7__Script@ line 30 in script7.groovy", xmlPath.getString("Envelope.Body.envelope.acknowledgment.ackLog.ackLogMsg"));

            bahrainIflowsPage.navigateToMonitorMessageProcessingApp();
            artifcatCorrelID = res.getHeader("sap_mplcorrelationid");
            bahrainIflowsPage.validateCorrelIDwoReceiverID(artifcatCorrelID, scenario);
        } else {
            //Parse and Validate XML response
            XmlPath xmlPath = new XmlPath(res.asString());
            System.out.println("correlation ID is " + res.getHeader("sap_mplcorrelationid"));
            System.out.println("Response is " + res.andReturn().asPrettyString());

            assertEquals("S", xmlPath.getString("Envelope.Body.envelope.acknowledgment.ackClass"));
            assertEquals("200", xmlPath.getString("Envelope.Body.envelope.acknowledgment.ackCode"));
            assertEquals("Message successfully received in ICH", xmlPath.getString("Envelope.Body.envelope.acknowledgment.ackLog.ackLogMsg"));

            bahrainIflowsPage.navigateToMonitorMessageProcessingApp();
            artifcatCorrelID = res.getHeader("sap_mplcorrelationid");
            bahrainIflowsPage.validateCorrelID(artifcatCorrelID);
        }
    }

    public static void updatePNID(String pnID, String fileName, String filepath, String scenario) throws Exception {
        String oauthURL = configGenreicReporting.getProperty("oauth.TokenURL");
        String url1 = configGenreicReporting.getProperty("url.UpdatePNID");
        //Retrieving Access Token
        String accessToken = bahrainIflowsPage.oauthToken(oauthURL, configGenreicReporting.getProperty("url.UpdatePNID.AuthCode"));
        //Update PNID
        bahrainIflowsPage.updatePNID(pnID, accessToken, url1);
    }

    @And("Validate payload from {} and {} {} for {}")
    public void validatePayloadForIflowname(String Artifact, String step, String segment, String status) throws Exception {
        bahrainIflowsPage.navigateToTraces(Artifact, artifcatCorrelID, ReceiverID);
        bahrainIflowsPage.validateResponsePayload(step, segment, status, expDate);
    }

}