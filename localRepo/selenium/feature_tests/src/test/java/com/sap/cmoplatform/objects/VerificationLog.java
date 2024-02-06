package com.sap.cmoplatform.objects;

public class VerificationLog {

    private String alertRuleName;
    private String alertDate;
    private String issue;
    private String resolution;
    private String changeHistory;
    private String verificationFailureReason;
    private String responderEmail;
    private String responderTelephone;
    private String controlAttestation;
    private String requestorEmail;
    private String requestorTelephone;

    private String requestTime;
    private String gtin;
    private String lot;
    private String expirationDate;
    private String serialNum;
    private String verified;
    private String requestorGln;
    private String responderGln;
    private String uuid;
    private String additionalInfo;
    private String linkType;
    private String context;
    private String executionDuration;
    private String requesterCompany;
    private String responderCompany;
    private String status;
    private String httpstatus;
    private String responseCode;
    private String responseCodeDescription;
    private String alertEmailStatus;

    public VerificationLog() {
    }
    public String getHttpstatus(){return httpstatus;}
    public String getRequestorTelephone(){return requestorTelephone;}
    public String getRequestorEmail(){return requestorEmail;}
    public String getControlAttestation(){return controlAttestation;}
    public String getVerificationFailureReason(){return verificationFailureReason;}
    public String getResponderEmail(){return responderEmail;}
    public String getResponderTelephone(){return responderTelephone;}
    public String getAlertRuleName(){return alertRuleName;}
    public String getAlertDate(){return alertDate;}
    public String getIssue(){return issue;}
    public String getResolution(){return resolution;}
    public String getChangeHistory(){return changeHistory;}
    public String getRequestTime() {
        return requestTime;
    }

    public String getGtin() {
        return gtin;
    }

    public String getLot() {
        return lot;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public String getVerified() {
        return verified;
    }

    public String getUUID() {
        return uuid;
    }

    public String getLinkType() {
        return linkType;
    }

    public String getContext() {
        return context;
    }

    public String getExecutionDuration() {
        return executionDuration;
    }

    public String getRequestorGln() {
        return requestorGln;
    }

    public String getResponderGln() {
        return responderGln;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getRequesterCompany() {
        return requesterCompany;
    }

    public String getResponderCompany() {
        return responderCompany;
    }

    public String getStatus() {
        return status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseCodeDescription() {
        return responseCodeDescription;
    }

    public String getAlertEmailStatus() {
        return alertEmailStatus;
    }

    public VerificationLog withhttpstatus(String httpstatus) {
        this.httpstatus = httpstatus;
        return this;
    }
    public VerificationLog withrequestorTelephone(String requestorTelephone) {
        this.requestorTelephone = requestorTelephone;
        return this;
    }
    public VerificationLog withrequestorEmail(String requestorEmail) {
        this.requestorEmail = requestorEmail;
        return this;
    }
    public VerificationLog withcontrolAttestation(String controlAttestation) {
        this.controlAttestation = controlAttestation;
        return this;
    }
    public VerificationLog withverificationFailurereason(String verificationFailureReason){
        this.verificationFailureReason = verificationFailureReason;
        return this;
    }
    public VerificationLog withresponderEmail(String responderEmail){
        this.responderEmail = responderEmail;
        return this;
    }
    public VerificationLog withresponderTelephone(String responderTelephone){
        this.responderTelephone = responderTelephone;
        return this;
    }

    public VerificationLog withalertRuleName(String alertruleName) {
        this.alertRuleName = alertRuleName;
        return this;
    }
    public VerificationLog withalertDate(String alertDate) {
        this.alertDate = alertDate;
        return this;
    }
    public VerificationLog withissue(String issue) {
        this.issue = issue;
        return this;
    }
    public VerificationLog withresolution(String resolution) {
        this.resolution = resolution;
        return this;
    }
    public VerificationLog withchangeHistory(String changeHistory) {
        this.changeHistory = changeHistory;
        return this;
    }

    public VerificationLog withRequestTime(String requestTime) {
        this.requestTime = requestTime;
        return this;
    }

    public VerificationLog withGtin(String gtin) {
        this.gtin = gtin;
        return this;
    }

    public VerificationLog withLot(String lot) {
        this.lot = lot;
        return this;
    }

    public VerificationLog withExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public VerificationLog withSerialNum(String serialNum) {
        this.serialNum = serialNum;
        return this;
    }

    public VerificationLog withExecutionDuration(String executionDuration) {
        this.executionDuration = executionDuration;
        return this;
    }

    public VerificationLog withVerified(String verified) {
        this.verified = verified;
        return this;
    }

    public VerificationLog withRequestorGln(String requestorGln) {
        this.requestorGln = requestorGln;
        return this;
    }

    public VerificationLog withResponderGln(String responderGln) {
        this.responderGln = responderGln;
        return this;
    }

    public VerificationLog withAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public VerificationLog withUUID(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public VerificationLog withContext(String context) {
        this.context = context;
        return this;
    }

    public VerificationLog withLinkType(String linkType) {
        this.linkType = linkType;
        return this;
    }

    public VerificationLog withRequesterCompany(String requesterCompany) {
        this.requesterCompany = requesterCompany;
        return this;
    }

    public VerificationLog withResponderCompany(String responderCompany) {
        this.responderCompany = responderCompany;
        return this;
    }

    public VerificationLog withStatus(String status) {
        this.status = status;
        return this;
    }

    public VerificationLog withResponseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public VerificationLog withResponseCodeDescription(String responseCodeDescription) {
        this.responseCodeDescription = responseCodeDescription;
        return this;
    }

    public VerificationLog withAlertEmailStatus(String alertEmailStatus) {
        this.alertEmailStatus = alertEmailStatus;
        return this;
    }
}
