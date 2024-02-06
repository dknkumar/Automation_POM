package com.sap.cmoplatform;

/**
 * All possible SAPUI5 versions that {@link SeleniumUI5TestUtil} will work with. <br>
 * Temporarily, the different environments represent different UI5 versions running on those environments.
 */
public enum UI5Version {
   QA,
   QA_SNR,
   QA_SEM,
   QA_TI,
   QA_REL,
   QA_OB,
   DEV,
   DEV_SNR,
   DEV_SEM,
   DEV_TI,
   DEV_REL,
   DEV_OB,
   LOCAL,
   STAGING;
}
