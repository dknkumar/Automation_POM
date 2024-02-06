package com.sap.cmoplatform.objects;

public class Snr {

    private String customer;
    private String product;
    private String identifierType;
    private String identifier;
    private String request;
    private String amount;
    private String status;
    private String senderGLN;
    private String receiverGLN;
    private String rangeList;
    private String companyPrefix;
    private String extensionDigit;
    private String encodedValue;

    public Snr() {
    }

    public Snr withCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public Snr withProduct(String product) {
        this.product = product;
        return this;
    }

    public Snr withIdentifierType(String identifierType) {
        this.identifierType = identifierType;
        return this;
    }

    public Snr withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public Snr withRequest(String request) {
        this.request = request;
        return this;
    }

    public Snr withAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public Snr withStatus(String status) {
        this.status = status;
        return this;
    }

    public Snr withSenderGLN(String senderGLN) {
        this.senderGLN = senderGLN;
        return this;
    }

    public Snr withReceiverGLN(String receiverGLN) {
        this.receiverGLN = receiverGLN;
        return this;
    }

    public Snr withRangeList(String rangeList) {
        this.rangeList = rangeList;
        return this;
    }

    public Snr withExtensionDigit(String extensionDigit) {
        this.extensionDigit = extensionDigit;
        return this;
    }

    public Snr withCompanyPrefix(String companyPrefix) {
        this.companyPrefix = companyPrefix;
        return this;
    }
    public Snr withEncodedValue(String encodedValue) {
        this.encodedValue = encodedValue;
        return this;
    }

    public String getCustomer() {
        return customer;
    }
    public String getProduct() {
        return product;
    }
    public String getIdentifierType() {
        return identifierType;
    }
    public String getIdentifier() {
        return identifier;
    }
    public String getRequest() {
        return request;
    }
    public String getAmount() {
        return amount;
    }
    public String getStatus() {
        return status;
    }
    public String getSenderGLN() {
        return senderGLN;
    }
    public String getReceiverGLN() {
        return receiverGLN;
    }
    public String getRangeList() {
        return rangeList;
    }
    public String getCompanyPrefix() {
        return companyPrefix;
    }
    public String getExtensionDigit() {
        return extensionDigit;
    }
    public String getEncodedValue() { return encodedValue; }
}
