package com.sap.cmoplatform.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SendKeysAction implements DoAction {
   private WebDriver driver;
   private By selector;
   private String keys;

   /**
    * Constructor needs the web driver, the selector for the element and the keys to send to the element
    *
    * @param driver   The web driver to interact with the browser
    * @param selector The By selector to find the element
    * @param keys     The keys to send to the element
    */
   public SendKeysAction(WebDriver driver, By selector, String keys) {
      this.driver = driver;
      this.selector = selector;
      this.keys = keys;
   }

   /**
    * Finds the element using the selector and sends the keys to it
    */
   public void invoke() {
      WebElement element = driver.findElement(selector);
      element.sendKeys(keys);
   }
}
