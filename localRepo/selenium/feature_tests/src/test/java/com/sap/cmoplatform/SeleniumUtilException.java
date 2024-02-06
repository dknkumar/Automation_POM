package com.sap.cmoplatform;

/**
 * Exception thrown by {@link SeleniumUI5TestUtil}.
 */
@SuppressWarnings("serial")
public class SeleniumUtilException extends Exception {

   private String message = "";

   /**
    * Constructor passing in an error text.
    *
    * @param message description of the exception that occured
    */
   public SeleniumUtilException(String message) {
      this.message = message;
   }

   /**
    * Getter for the error message.
    */
   public String getMessage() {
      return message;
   }

   /**
    * Setter for the error message.
    *
    * @param message description of the exception that occured
    */
   public void setMessage(String message) {
      this.message = message;
   }


}
