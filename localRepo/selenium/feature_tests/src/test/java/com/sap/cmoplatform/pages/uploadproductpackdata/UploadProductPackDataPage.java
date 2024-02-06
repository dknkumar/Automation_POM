package com.sap.cmoplatform.pages.uploadproductpackdata;

import com.sap.cmoplatform.pages.Page;

import java.util.Set;

public class UploadProductPackDataPage extends Page {
    private String parentHandle;
    private String childHandle;

    public void switchToChildWindow() throws Exception {
        waitForPageToLoad();
        parentHandle = portalDriver.getWindowHandle();
        Set<String> handles = portalDriver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentHandle))
                portalDriver.switchToWindow(handle);
        }
    }
    public void switchToParentWindow() throws Exception {
        waitForPageToLoad();
        portalDriver.switchToWindow(parentHandle);
    }
}
