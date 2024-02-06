package com.sap.cmoplatform.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GetElementAttributeAction implements GetAction {
   private WebDriver driver;
   private By selector;
   private String attribute;

   /**
    * Constructor needs the web driver, the selector for the element and the keys to send to the element
    *
    * @param driver    The web driver to interact with the browser
    * @param selector  The By selector to find the element
    * @param attribute The attribute id of the value you want
    */
   public GetElementAttributeAction(WebDriver driver, By selector, String attribute) {
      this.driver = driver;
      this.selector = selector;
      this.attribute = attribute;
   }

   /**
    * Returns the value of the attribute from the element
    */
   public String invoke() {
      return driver.findElement(selector).getAttribute(attribute);
   }
}
