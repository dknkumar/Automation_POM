package com.sap.cmoplatform.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GetTableDataAction implements GetAction {
   private WebDriver driver;
   private By selector;
   private String tag;

   /**
    * Constructor needs the web driver and the selector for the element.
    *
    * @param driver   The web driver to interact with the browser
    * @param selector The By selector to find the element
    * @param tag The tag for the rows tr or li
    */
   public GetTableDataAction(WebDriver driver, By selector, String tag) {
      this.driver = driver;
      this.selector = selector;
      this.tag = tag;
   }

   /**
    * Finds the data of each row using the selector and returns it.
    */
   public List<List<WebElement>> invoke() {
      List<List<WebElement>> tableData = new ArrayList<List<WebElement>>();
      WebElement table = driver.findElement(selector);
      List<WebElement> tableRows = table.findElements(By.tagName(tag));
      for (WebElement row : tableRows) {
         List<WebElement> rowData = row.findElements(By.tagName("td"));
         tableData.add(rowData);
      }
      return tableData;
   }
}
