package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/managemessage.html",
                "json:target/test-reports/managemessage.json",
                "junit:target/test-reports/managemessage.xml",
                "pretty"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/managemessage"
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.manageMessage"
        }
)
public class ManageMessage_RunnerIT {
}
