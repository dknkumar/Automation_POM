package com.sap.cmoplatform.objects;

public class UploadProductToLookUpDirectory {
    private String gtin;
    private String description;
    private String responseCode;
    private String responseDetail;
    private String uploadStatus;

    public UploadProductToLookUpDirectory() {
    }

    public String getGtin() {
        return gtin;
    }

    public String getDescription() {
        return description;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseDetail() {
        return responseDetail;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public UploadProductToLookUpDirectory withGtin(String gtin) {
        this.gtin = gtin;
        return this;
    }
    public UploadProductToLookUpDirectory withDescription(String description) {
        this.description = description;
        return this;
    }
    public UploadProductToLookUpDirectory withResponseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }
    public UploadProductToLookUpDirectory withResponseDetail(String responseDetail) {
        this.responseDetail = responseDetail;
        return this;
    }
    public UploadProductToLookUpDirectory withUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
        return this;
    }

}
