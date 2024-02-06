package com.sap.cmoplatform.actions;

import com.sap.cmoplatform.SeleniumUtilException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindElementAction implements GetAction {
   private WebDriver driver;
   private By selector;

   /**
    * Constructor needs the web driver and the selector for the element.
    *
    * @param driver   The web driver to interact with the browser
    * @param selector The By selector to find the element
    */
   public FindElementAction(WebDriver driver, By selector) {
      this.driver = driver;
      this.selector = selector;
   }

   /**
    * Finds the element using the selector and returns it.
    */
   public WebElement invoke() throws Exception{
      try {
         return driver.findElement(selector);
      } catch (Exception e) {
         throw new SeleniumUtilException("Unable to find element " + e.getMessage());
      }
   }
}
