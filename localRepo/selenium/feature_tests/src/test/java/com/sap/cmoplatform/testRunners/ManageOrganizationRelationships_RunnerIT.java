package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
      "html:target/test-reports/manageorganizationrelationships.html",
      "json:target/test-reports/manageorganizationrelationships.json",
      "junit:target/test-reports/manageorganizationrelationships.xml",
      "pretty"
    },
    features = {"src/test/resources/com/sap/cmoplatform/teststeps/manageorganizationrelationships"},
    glue = {
      "com.sap.cmoplatform.teststeps.common",
      "com.sap.cmoplatform.teststeps.manageOrganizationRelationships"
    })
public class ManageOrganizationRelationships_RunnerIT {}
