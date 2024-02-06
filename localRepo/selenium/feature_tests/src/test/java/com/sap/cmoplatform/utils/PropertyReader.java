package com.sap.cmoplatform.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public final class PropertyReader {

  // private static final Logger LOGGER = LogManager.getLogger(new Object(){}.getClass().getEnclosingClass().getName());
   private static final String FILEPATH = "src/test/resources/properties/";

   private PropertyReader() {

   }

   public static Properties loadProperties(String filename) {
      Properties properties = new Properties();
      String path = FILEPATH + filename;
     // LOGGER.info("Reading properties file : " + path);
      try {
         InputStream input = new FileInputStream(path);
         properties.load(input);
         input.close();
      } catch (IOException e) {
      //   LOGGER.error(e.getMessage());
      }
      return properties;
   }
}
