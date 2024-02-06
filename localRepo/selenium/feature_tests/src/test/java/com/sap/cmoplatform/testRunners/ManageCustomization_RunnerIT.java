package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/managecustomization.html",
                "json:target/test-reports/managecustomization.json",
                "junit:target/test-reports/managecustomization.xml",
                "pretty"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/manageCustomization"
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.manageCustomization"
        }
)
public class ManageCustomization_RunnerIT {
}
