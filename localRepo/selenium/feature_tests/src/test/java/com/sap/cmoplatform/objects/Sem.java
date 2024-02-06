package com.sap.cmoplatform.objects;

public class Sem {
   private String customer;
   private String product;
   private String identifierType;
   private String identifier;
   private String lastUpdated;
   private String status;
   private String senderGln;
   private String receiverGln;
   private String batchId;
   private String messageType;
   private String fileName;

   public Sem() {
   }

   public Sem withCustomer(String customer) {
      this.customer = customer;
      return this;
   }

   public Sem withSenderGln(String senderGln) {
      this.senderGln = senderGln;
      return this;
   }

   public Sem withReceiverGln(String receiverGln) {
      this.receiverGln = receiverGln;
      return this;
   }

   public Sem withProduct(String product) {
      this.product = product;
      return this;
   }

   public Sem withBatchId(String batchId) {
      this.batchId = batchId;
      return this;
   }

   public Sem withIdentifierType(String identifierType) {
      this.identifierType = identifierType;
      return this;
   }

   public Sem withIdentifier(String identifier) {
      this.identifier = identifier;
      return this;
   }

   public Sem withLastUpdated(String lastUpdated) {
      this.lastUpdated = lastUpdated;
      return this;
   }

   public Sem withStatus(String status) {
      this.status = status;
      return this;
   }

   public Sem withMessageType(String messageType) {
      this.messageType = messageType;
      return this;
   }

   public Sem withFileName(String filename) {
      this.fileName = filename;
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

   public String getLastUpdated() {
      return lastUpdated;
   }

   public String getStatus() {
      return status;
   }

   public String toString() {
      return "SEM To String:" +
              "\nCustomer:\t" + getCustomer() +
              "\nProduct:\t" + getProduct() +
              "\nStatus:\t\t" + getStatus() +
              "\nLast Updated:\t" + getLastUpdated() +
              "\nIdentifier:\t" + getIdentifierType() +
              "\nIdentifier Number:\t" + getIdentifier();
   }

   public String getSenderGln() {
      return senderGln;
   }

   public String getReceiverGln() {
      return receiverGln;
   }

   public String getBatchId() {
      return batchId;
   }

   public String getMessageType() {
      return messageType;
   }

   public String getFileName() {
      return fileName;
   }
}
