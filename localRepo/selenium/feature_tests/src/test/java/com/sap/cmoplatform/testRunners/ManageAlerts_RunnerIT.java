package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/managealerts.html",
                "json:target/test-reports/managealerts.json",
                "junit:target/test-reports/managealerts.xml",
                "pretty",
                "rerun:target/rerun.txt"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/managealerts"
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.managealerts"
        }
)
public class ManageAlerts_RunnerIT {
}
