package com.sap.cmoplatform.pages;

import com.sap.cmoplatform.utils.PropertyReader;

import java.util.Properties;

public class GoodbyePage extends Page {
   private final Properties ui5PageIds = PropertyReader.loadProperties("Ui5PageIds.properties");
   private final String logBackOnButtonId = ui5PageIds.getProperty("ui5.logBackOnButtonId");

   public void logBackOn() throws Exception {
      portalDriver.clickElement(logBackOnButtonId);
   }
}