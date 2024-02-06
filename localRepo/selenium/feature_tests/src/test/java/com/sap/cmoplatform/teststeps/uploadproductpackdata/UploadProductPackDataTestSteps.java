package com.sap.cmoplatform.teststeps.uploadproductpackdata;

import com.sap.cmoplatform.pages.uploadproductpackdata.UploadProductPackDataPage;
import io.cucumber.java.en.And;

public class UploadProductPackDataTestSteps {
    private UploadProductPackDataPage uploadProductPackDataPage = new UploadProductPackDataPage();


    @And("^user switches to the (parent|child) window$")
    public void userSwitchesToTheNewWindow(String window) throws Exception {
        if(window.equals("child")){
            uploadProductPackDataPage.switchToChildWindow();
        }
        if(window.equals("parent")){
            uploadProductPackDataPage.switchToParentWindow();
        }

    }
}
