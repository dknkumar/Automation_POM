package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/usscendtoend.html",
                "json:target/test-reports/usscendtoend.json",
                "junit:target/test-reports/usscendtoend.xml",
                "pretty",
                "rerun:target/rerun.txt"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/usscendtoend",
        },
        glue = {
                "com.sap.cmoplatform.teststeps.usscendtoendtest",
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.uploadproductpackdata",
                "com.sap.cmoplatform.teststeps.writelogs",
                "com.sap.cmoplatform.teststeps.verificationlog",
                "com.sap.cmoplatform.teststeps.verifyproductpackage",
                "com.sap.cmoplatform.teststeps.uploadproducttolookupdirectory",
                "com.sap.cmoplatform.teststeps.blockchainexplorer",
                "com.sap.cmoplatform.teststeps.configureVerificationResponses",
                "com.sap.cmoplatform.teststeps.sem",
                "com.sap.cmoplatform.teststeps.snr",

        }
)
public class UsscEndToEndFlow_RunnerIT {
}
