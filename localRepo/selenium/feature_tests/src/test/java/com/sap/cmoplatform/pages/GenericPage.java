package com.sap.cmoplatform.pages;

import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;

import java.util.Properties;

public class GenericPage extends Page {
    private final Properties ui5PageIds = PropertyReader.loadProperties("Ui5PageIds.properties");
    private String logOut = ui5PageIds.getProperty("ui5.logoutButtonId");
    private String userButtonId = ui5PageIds.getProperty("ui5.userButtonId");
    private By signOutButton=By.xpath("//div[text()='Sign Out']");

    public void clickOnUserButtonId() throws Exception{
        waitForPageToLoad();
        clickElement(userButtonId);
    }

    public void logOut() throws Exception{
        clickElement(logOut);
    }
    public void signOutButton() throws Exception{
        clickElement(signOutButton);
    }

}