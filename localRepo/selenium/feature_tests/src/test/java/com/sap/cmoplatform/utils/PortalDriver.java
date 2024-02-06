package com.sap.cmoplatform.utils;

import com.sap.cmoplatform.Config;
import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.UI5Version;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public final class PortalDriver {

   private static volatile SeleniumUI5TestUtil instance = null;
  // private static final Logger LOGGER = LogManager.getLogger(new Object(){}.getClass().getEnclosingClass().getName());


   public static SeleniumUI5TestUtil getInstance() {
      if (instance == null) {
         synchronized (PortalDriver.class) {
            if (instance == null) {
             //  LOGGER.info("Instantiating PortalDriver");
               HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
               chromePrefs.put("profile.default_content_settings.popups", 0);
               chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\downloadFiles\\");
               chromePrefs.put("safebrowsing.enabled","true");
               ChromeOptions options = new ChromeOptions();
               //AGRESSIVE: options.setPageLoadStrategy(PageLoadStrategy.NONE); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
               options.addArguments("--disable-gpu");
               options.setExperimentalOption("prefs", chromePrefs);
               String chromedriver = "";


               if(SystemUtils.IS_OS_LINUX) {
                  chromedriver = "/usr/bin/chromedriver";
                  String binaryLocation = "/opt/google/chrome/google-chrome";
                  options.setBinary(binaryLocation);
               } else {
                  //chromedriver = "./src/test/resources/chromedriver.exe";
               }

               //System.setProperty("webdriver.chrome.driver", chromedriver);

               String version = "QA";

               if (version.equals("DEV"))
                  instance = new SeleniumUI5TestUtil(new Config(UI5Version.DEV, options));
               else if (version.equals("QA"))
                  instance = new SeleniumUI5TestUtil(new Config(UI5Version.QA, options));
               else if (version.equals("LOCAL"))
                  instance = new SeleniumUI5TestUtil(new Config(UI5Version.LOCAL, options));
            }
         }
      }
      return instance;
   }

   public static void quitDriver() {
  //     LOGGER.info("Destroying PortalDriver");
       instance.quitDriver();
       instance = null;
   }
}
