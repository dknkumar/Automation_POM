package com.sap.cmoplatform.pages;

import com.sap.cmoplatform.teststeps.common.CommonTestSteps;
import com.sap.cmoplatform.utils.PropertyReader;
import org.openqa.selenium.By;

import java.util.Properties;

public class LoginPage extends Page {

    private final Properties users = PropertyReader.loadProperties("Users.properties");
    private final Properties ui5PageIds = PropertyReader.loadProperties("Ui5PageIds.properties");
    private final String logBackOnButtonId = ui5PageIds.getProperty("ui5.logBackOnButtonId");
    private final String samlLoginLink = ui5PageIds.getProperty("ui5.samlLoginLink");
    private final String ichqaLoginLink = ui5PageIds.getProperty("ui5.ichqaLoginLink");

    public void login(String user) throws Exception {
//        clickOnSignInLink();
//        waitForPageToLoad();
//        if (!onLoginPage()) {
//            clickOnSignInLink();
//        }
//        String username = users.getProperty(user + ".username");
//        String password = users.getProperty(user + ".password");
//        portalDriver.login(username, password);

        portalDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("narendra.d.kumar@unilever.com");
        portalDriver.findElement(By.xpath("//input[@data-report-value='Submit']")).click();
        Thread.sleep(3000);

        portalDriver.findElement(By.name("passwd")).sendKeys("Jdknk@8989");
        portalDriver.findElement(By.xpath("//input[@data-report-event='Signin_Submit']")).click();
        Thread.sleep(10000);
    }

    private boolean onLoginPage() {
        return doesElementExist(By.id("Email"));
    }

    public void clickOnSignInLink() throws Exception {
        waitForPageToLoad();
        portalDriver.findElement(By.xpath(ichqaLoginLink)).click();
        //CommonTestSteps.enableTimestampforScreenshots();
    }
}
