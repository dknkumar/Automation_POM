package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/verifyproductpackage.html",
                "json:target/test-reports/verifyproductpackage.json",
                "junit:target/test-reports/verifyproductpackage.xml",
                "pretty",
                "rerun:target/rerun.txt"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/verifyproductpackage"
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.verifyproductpackage",
                "com.sap.cmoplatform.teststeps.usscendtoendtest",
                "com.sap.cmoplatform.teststeps.verificationlog",
                "com.sap.cmoplatform.teststeps.configureVerificationResponses",
                "com.sap.cmoplatform.teststeps.alertconfiguration"



        }
)
public class VerifyProductPackage_RunnerIT {
}
