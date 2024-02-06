package com.sap.cmoplatform.objects;

public class GenericMessaging {
    private String companyName;
    private String receivedDate;

   public GenericMessaging(){

    }

    public GenericMessaging withCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public GenericMessaging withReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }
    public String getReceivedDate() {
        return receivedDate;
    }


}
