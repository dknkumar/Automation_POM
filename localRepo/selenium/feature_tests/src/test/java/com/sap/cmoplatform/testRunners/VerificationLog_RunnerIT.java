package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/verificationlog.html",
                "json:target/test-reports/verificationlog.json",
                "junit:target/test-reports/verificationlog.xml",
                "pretty",
                "rerun:target/rerun.txt"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/verificationlog"
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.verificationlog",
                "com.sap.cmoplatform.teststeps.blockchainexplorer"
        }
)
public class VerificationLog_RunnerIT {
}
