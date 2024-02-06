package com.sap.cmoplatform.objects;

public class WriteLog {

    private String messageID;
    private String gtin;
    private String lot;
    private String disposition;
    private String messageTime;
    private String eventTime;
    private String expirationDate;
    private String writtenEpc;
    private String queuedEpc;
    private String status;
    public WriteLog() {
    }

    public String getMessageId() {
        return messageID;
    }

    public String getGtin() {
        return gtin;
    }

    public String getLot() {
        return lot;
    }

    public String getDisposition() {
        return disposition;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
    public String getWrittenEpc() {
        return writtenEpc;
    }
    public String getQueuedEpc() {
        return queuedEpc;
    }
    public String getStatus() {
        return status;
    }
    public WriteLog withMessageID(String messageID) {
        this.messageID = messageID;
        return this;
    }
    public WriteLog withGtin(String gtin) {
        this.gtin = gtin;
        return this;
    }
    public WriteLog withLot(String lot) {
        this.lot = lot;
        return this;
    }
    public WriteLog withDisposition(String disposition) {
        this.disposition = disposition;
        return this;
    }
    public WriteLog withMessageTime(String messageTime) {
        this.messageTime = messageTime;
        return this;
    }
    public WriteLog withEventTime(String eventTime) {
        this.eventTime = eventTime;
        return this;
    }
    public WriteLog withExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
    public WriteLog withWrittenEpc(String writtenEpc) {
        this.writtenEpc = writtenEpc;
        return this;
    }
    public WriteLog withQueuedEpc(String queuedEpc) {
        this.queuedEpc = queuedEpc;
        return this;
    }
    public WriteLog withStatus(String status) {
        this.status = status;
        return this;
    }

}
