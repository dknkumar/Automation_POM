package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/writelogs.html",
                "json:target/test-reports/writelogs.json",
                "junit:target/test-reports/writelogs.xml",
                "pretty",
                "rerun:target/rerun.txt"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/writelogs"
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.writelogs",
                "com.sap.cmoplatform.teststeps.blockchainexplorer"



        }
)
public class WriteLogs_RunnerIT {
}
