package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/uploadproductpackdata.html",
                "json:target/test-reports/uploadproductpackdata.json",
                "junit:target/test-reports/uploadproductpackdata.xml",
                "pretty",
                "rerun:target/rerun.txt"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/uploadproductpackdata",
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.uploadproductpackdata",
                "com.sap.cmoplatform.teststeps.sem"


        }
)
public class UploadProductPackData_RunnerIT {
}
