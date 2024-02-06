package com.sap.cmoplatform.objects;

public class TransactionInformation {

    private String deliveryNumber;
    private String deliveryDate;
    private String senderGln;
    private String receiverGln;
    private String messageId;
    private String messageDate;
    private String purchaseOrder;
    private String gtin;
    private String lotNumber;
    private String serialNumber;
    private String ndc;
    private String downloaded;

    public TransactionInformation() {
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getSenderGln() {
        return senderGln;
    }

    public String getReceiverGln() {
        return receiverGln;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public String getGtin() {
        return gtin;
    }

    public String getNdc() {
        return ndc;
    }

    public String getDownloaded() {
        return downloaded;
    }

    public TransactionInformation withDeliveryDNumber(String deliveryDNumber) {
        this.deliveryNumber = deliveryDNumber;
        return this;
    }

    public TransactionInformation withDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public TransactionInformation withSenderGln(String senderGln) {
        this.senderGln = senderGln;
        return this;
    }

    public TransactionInformation withReceiverGln(String receiverGln) {
        this.receiverGln = receiverGln;
        return this;
    }
    public TransactionInformation withGtin(String gtin) {
        this.gtin = gtin;
        return this;
    }
    public TransactionInformation withLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
        return this;
    }
    public TransactionInformation withSerialNum(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public TransactionInformation withMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public TransactionInformation withMessageDate(String messageDate) {
        this.messageDate = messageDate;
        return this;
    }

    public TransactionInformation withPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        return this;
    }

    public TransactionInformation withNdc(String ndc) {
        this.ndc = ndc;
        return this;
    }

}
