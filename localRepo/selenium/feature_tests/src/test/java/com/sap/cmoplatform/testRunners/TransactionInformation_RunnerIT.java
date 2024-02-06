package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/transactioninformation",
                "json:target/test-reports/transactioninformation.json",
                "pretty",
                "junit:target/test-reports/transactioninformation.xml",
                "rerun:target/rerun.txt"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/transactioninformation"
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.transactioninformation"
        }
)
public class TransactionInformation_RunnerIT {
}
