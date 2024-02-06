package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/configureVerificationResponses.html",
                "json:target/test-reports/configureVerificationResponses.json",
                "junit:target/test-reports/configureVerificationResponses.xml",
                "pretty",
                "rerun:target/rerun.txt"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/configureVerificationResponses"
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.configureVerificationResponses"


        }
)
public class ConfigureVerificationResponses_RunnerIT {
}
