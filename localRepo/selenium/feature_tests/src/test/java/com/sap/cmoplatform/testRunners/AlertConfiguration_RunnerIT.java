package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/alertconfiguration.html",
                "json:target/test-reports/alertconfiguration.json",
                "junit:target/test-reports/alertconfiguration.xml",
                "pretty"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/alertconfiguration",
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.alertconfiguration"
        }
)
public class AlertConfiguration_RunnerIT {
}
