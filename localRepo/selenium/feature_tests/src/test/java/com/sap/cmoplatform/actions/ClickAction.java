package com.sap.cmoplatform.actions;

import com.sap.cmoplatform.SeleniumUtilException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickAction implements DoAction {
   private WebDriver driver;
   private By selector;

   /**
    * Constructor needs the web driver and the selector for the element...
    *
    * @param driver   The web driver to interact with the browser
    * @param selector The By selector to find the element
    */
   public ClickAction(WebDriver driver, By selector) {
      this.driver = driver;
      this.selector = selector;
   }

   /**
    * Finds the element and performs a click() action on it...
    */
   public void invoke() throws Exception {
      WebElement element = driver.findElement(selector);
      try {
         element.click();
      } catch (Exception e) {
         throw new SeleniumUtilException("Unable to click element " + element + e.getMessage());
      }
   }
}
