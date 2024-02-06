package com.sap.cmoplatform.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/test-reports/uploadproductdatatolookupdirectory.html",
                "json:target/test-reports/uploadproductdatatolookupdirectory.json",
                "junit:target/test-reports/uploadproductdatatolookupdirectory.xml",
                "pretty",
                "rerun:target/rerun.txt"
        },
        features = {
                "src/test/resources/com/sap/cmoplatform/teststeps/uploadproductdatatolookupdirectory"
        },
        glue = {
                "com.sap.cmoplatform.teststeps.common",
                "com.sap.cmoplatform.teststeps.uploadproductdatatolookupdirectory"
        }
)
public class UploadProductDataToLookUpDirectory_RunnerIT {
}
