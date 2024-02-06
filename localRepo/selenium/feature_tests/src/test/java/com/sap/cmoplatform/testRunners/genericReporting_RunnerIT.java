package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/genericReporting.html",
                "json:target/test-reports/genericReporting.json",
                "junit:target/test-reports/genericReporting.xml",
                "pretty"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/genericReporting",
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.genericReporting"
        }
)
public class genericReporting_RunnerIT {
}
