package com.sap.cmoplatform.teststeps.manageCustomization;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.Page;
import com.sap.cmoplatform.pages.manageCustomization.ManageCustomizingPage;
import com.sap.cmoplatform.pages.manageMessage.ManageMessagingPage;
import com.sap.cmoplatform.utils.PortalDriver;
import com.sap.cmoplatform.utils.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.*;

public class ManageCustomizationTestSteps {
    private ManageCustomizingPage manageCustomizingPage = new ManageCustomizingPage();
    private SeleniumUI5TestUtil portalDriver = PortalDriver.getInstance();
    private Properties manageCustomizingProperties = PropertyReader.loadProperties("managecustomization/managecustomization.properties");
    @Then("the ManageCustomizations page should be visible")
    public void theManageCustomizationsPageShouldBeVisible() throws Exception {
        assertEquals(manageCustomizingProperties.getProperty("MC"), manageCustomizingPage.getAppTitleName());
    }
    @And("^Click on (.+) button")
    public void clickOnButtoncase(String btnName) throws Exception {
        switch (btnName) {
            default:
//                manageCustomizingPage.waitForPageToLoad();
                manageCustomizingPage.waitForElementToLoad();
                manageCustomizingPage.clickButton(btnName);
                break;
        }
    }

    @Then("Add button is clicked")
    public void addButtonIsClicked() throws Exception {
        assertTrue(manageCustomizingPage.isCustomizationAddOne());
    }

    @And("Select {}: value {}")
    public void selectTypeValue(String type,String value) throws Exception {
        if(!"".equals(value) ){
            manageCustomizingPage.selectDropDown(type,value);
        }

    }
    @And("Modify {}: value {}")
    public void ModifyValue(String type,String value) throws Exception {
        switch (type){
            case "Parameter Value":
                manageCustomizingPage.modifyDropDown(type,value);
                break;
            case "Description":
                manageCustomizingPage.modifyDescription(value);
                break;
        }

    }
    @And("Select record {}:{}:{}")
    public void selectRecord(String scenario,String key,String option) throws Exception {
        manageCustomizingPage.selectRecord(scenario,key,option);
    }
    @And("Delete record {}:{}:{}")
    public void deleteRecord(String scenario,String key,String option) throws Exception {
        manageCustomizingPage.selectRecord(scenario,key,option);
        manageCustomizingPage.clickButton("Delete");

    }

    @Then("Newly added customization with:{},{},{},{} get from payload")
    public void newlyAddedCustomizationGetFromPayload(String scenario,String parameterKey,String parameterOption,String parameterValue) throws Exception {
        assertTrue(manageCustomizingPage.checkRecordFromPayload(scenario,parameterKey,parameterOption,parameterValue));
        assertTrue(manageCustomizingPage.isRecordExist(scenario,parameterKey,parameterOption,parameterValue));
    }

    @Then("Newly added customization record is available with:{},{},{},{}")
    public void newlyAddedCustomizationRecordIsAvailableWithValueScenarioParameterKeyParameterOptionParameterValue(String scenario,String parameterKey,String parameterOption,String parameterValue) throws Exception {
        assertTrue(manageCustomizingPage.isRecordExist(scenario,parameterKey,parameterOption,parameterValue));
    }
    @Then("Newly added customization record is not available with:{},{},{},{}")
    public void newlyAddedCustomizationRecordIsNotAvailableWithValueScenarioParameterKeyParameterOptionParameterValue(String scenario,String parameterKey,String parameterOption,String parameterValue) throws Exception {
        assertFalse(manageCustomizingPage.isRecordExist(scenario,parameterKey,parameterOption,parameterValue));
    }

    @And("Input {}: value {}")
    public void inputParameterValueValueParameterValue(String type,String value) throws Exception {
        manageCustomizingPage.inputValue(type,value);


    }
    @Then("^(.+) Pop Up must open")
    public void viewSettingPopUpMustOpen(String viewTitle) throws Exception {
        switch (viewTitle) {
            case "Warning":
//            case "Change Log":
                Assert.assertTrue(manageCustomizingPage.getElement(By.xpath("//span[text()='" + viewTitle + "']")).isDisplayed());
                break;
//            case "Adapt Filters":
//                Assert.assertTrue(manageMessagingPage.getElement(By.xpath("//button/span/span/bdi[text()='" + viewTitle + "']")).isDisplayed());
//                break;
        }
    }

    @And("back to home page")
    public void backToHomePage() throws Exception {
        manageCustomizingPage.backToHomePage();
    }

    @Then("unsaved window pop up")
    public void unsavedWindowPopUp() throws Exception {
       assertTrue(manageCustomizingPage.isUnsavedWindowPopUp());
    }

    @Then("warning message {} display")
    public void warningMessageParameterValueDisplay(String value) throws Exception {
        assertEquals("Please Select "+value,manageCustomizingPage.getElement(By.xpath("//div[@class = 'sapMSLITitleOnly']")).getText());
    }

    @And("ModifySecond {}: value {}")
    public void selectSecondTypeValue(String type,String value) throws Exception {
        manageCustomizingPage.selectSecondDropDown(type,value);

    }

    @Then("Changes will not save and throw warning {}")
    public void changesWillNotSaveAndThrowWarning(String message) throws  Exception {
        manageCustomizingPage.waitForPageToLoad();
       int num = manageCustomizingPage.getElements(By.xpath("//div[@class = 'sapMSLITitleOnly']")).size() - 1;
        assertEquals(message,manageCustomizingPage.getElements(By.xpath("//div[@class = 'sapMSLITitleOnly']")).get(num).getText());
        manageCustomizingPage.clickButton("Cancel");
        manageCustomizingPage.clickButton("OK");
    }

    @When("Sort order {} by {}")
    public void sortOrderBy(String sortOrder ,String sortBy) throws Exception{
        manageCustomizingPage.waitForPageToLoad();
        manageCustomizingPage.clickElement(By.id("application-customization-manage-component---ManageCustomization--sortManageCustomization"));
        manageCustomizingPage.clickElement(By.xpath("//div[text() = '" + sortOrder + "']"));
        manageCustomizingPage.clickElement(By.xpath("//div[text() = '"+sortBy+"']"));
        manageCustomizingPage.clickButton("OK");
    }

    @Then("Order is {} sorted by {}")
    public void orderIsAscendingSortedByScenario(String sortOrder ,String sortBy) throws Exception {
        manageCustomizingPage.waitForPageToLoad();
        assertTrue(manageCustomizingPage.isOrderSortBy(sortOrder,sortBy));


    }

    @Then("ChangeLog display only:")
    public void updateParameterValue(List<String> valueList) throws Exception {
    manageCustomizingPage.waitForPageToLoad();
        for (String value :valueList) {
            manageCustomizingPage.inputText(By.xpath("//input[@placeholder = 'Search']"),value);
            manageCustomizingPage.clickElement(By.xpath("//div[@title = 'Search']"));
            assertTrue(manageCustomizingPage.changeLogCheck(value));
            manageCustomizingPage.clickElement(By.xpath("//div[@title = 'Reset']"));
        }
        manageCustomizingPage.clickButton("Close");
    }


    @When("Filter By {} :{}")
    public void filterBy(String by,String value)throws Exception {
//        manageCustomizingPage.waitForPageToLoad();
//        manageCustomizingPage.clickElement("application-customization-manage-component---ManageCustomization--sortManageCustomization-img");
        manageCustomizingPage.clickElement(By.id("application-customization-manage-component---ManageCustomization--filterManageCustomization"));
        manageCustomizingPage.clickElement(By.xpath("//div[text() = '" + by + "']"));
        manageCustomizingPage.waitForElementToLoad();
       int num =  manageCustomizingPage.getElements(By.xpath("//div[text() = '"+value +"']")).size() - 1;
        manageCustomizingPage.getElements(By.xpath("//div[text() = '"+value +"']")).get(num).click();
//        manageCustomizingPage.clickElement(By.xpath("//div[text() = '"+value +"']"));
        manageCustomizingPage.clickButton("OK");

    }

    @Then("Only {} :{} customizations display")
    public void onlyScenarioScenarioCustomizationsDisplay(String by,String value)throws Exception {
        assertTrue(manageCustomizingPage.isOrderFilterBy(by, value));
        manageCustomizingPage.clickElement(By.id("application-customization-manage-component---ManageCustomization--filterManageCustomization"));
        try {
            Page.clickButton("Reset");
        }catch (Exception e){
            //do nothing
        }
        manageCustomizingPage.clickButton("OK");
    }

    @When("Filter the combination of filters with {}:{}:{}")
    public void filterTheCombination(String scenario, String key, String option) throws Exception{
        manageCustomizingPage.clickElement(By.id("application-customization-manage-component---ManageCustomization--filterManageCustomization"));
        manageCustomizingPage.waitForPageToLoad();
        if(scenario!=""){
            manageCustomizingPage.clickElement(By.xpath("//div[text() = 'Scenario']"));
//            manageCustomizingPage.clickElement(By.xpath("//div[text() = '"+scenario +"']"));
            manageCustomizingPage.waitForElementToLoad();
            int num =  manageCustomizingPage.getElements(By.xpath("//div[text() = '"+scenario +"']")).size() - 1;
            manageCustomizingPage.getElements(By.xpath("//div[text() = '"+scenario +"']")).get(num).click();

            manageCustomizingPage.clickElement(By.id("application-customization-manage-component---ManageCustomization--manageCustomizationFilterDialog-backbutton"));
        }
        if(key!=""){
            manageCustomizingPage.clickElement(By.xpath("//div[text() = 'Parameter Key']"));
//            manageCustomizingPage.clickElement(By.xpath("//div[text() = '"+key +"']"));
            manageCustomizingPage.waitForElementToLoad();
            int num =  manageCustomizingPage.getElements(By.xpath("//div[text() = '"+key +"']")).size() - 1;
            manageCustomizingPage.getElements(By.xpath("//div[text() = '" + key + "']")).get(num).click();
            manageCustomizingPage.clickElement(By.id("application-customization-manage-component---ManageCustomization--manageCustomizationFilterDialog-backbutton"));
        }
        if(option!=""){
            manageCustomizingPage.clickElement(By.xpath("//div[text() = 'Parameter Option']"));
//            manageCustomizingPage.clickElement(By.xpath("//div[text() = '"+option +"']"));
            manageCustomizingPage.waitForElementToLoad();
            int num =  manageCustomizingPage.getElements(By.xpath("//div[text() = '"+ option +"']")).size() - 1;
            manageCustomizingPage.getElements(By.xpath("//div[text() = '" + option + "']")).get(num).click();
            manageCustomizingPage.clickElement(By.id("application-customization-manage-component---ManageCustomization--manageCustomizationFilterDialog-backbutton"));
        }

        manageCustomizingPage.clickButton("OK");
    
    }

    @Then("Only cunstomizations match to the combinations:{}:{}:{} display")
    public void onlyCunstomizationsMatchToTheCombinationsDisplay(String scenario, String key, String option) throws Exception{
       assertTrue( manageCustomizingPage.isOrderFilterBy(scenario,key,option));

    }


    @Then("Check {} record {}:{}:{} :value {} and timeStamp")
    public void checkRecordScenarioTimeStamp(String action,String scenario, String key, String option,String value) throws Exception{
        assertTrue(manageCustomizingPage.checkValueChanged(action,scenario,key,option,value));
    }

    @Then("CheckNo {} record {}:{}:{} :value {} and timeStamp")
    public void checkNoRecordScenarioTimeStamp(String action,String scenario, String key, String option,String value) throws Exception{
        assertFalse(manageCustomizingPage.checkValueChanged(action,scenario,key,option,value));
    }

    @Then("ChangeLog colume display well")
    public void changelogDisplayWell(List<String> columnList) throws Exception {
     for(String colume : columnList){
         manageCustomizingPage.getElement(By.xpath("//span[text() = '"+ colume +"']")).isDisplayed();
     }
    }

    @And("Filter Reset")
    public void filterReset() throws Exception {
        manageCustomizingPage.waitForPageToLoad();
        manageCustomizingPage.clickElement(By.id("application-customization-manage-component---ManageCustomization--filterManageCustomization"));
        manageCustomizingPage.waitForPageToLoad();
        manageCustomizingPage.clickButton("Reset");

    }

    @Then("App should not display the results based on the filter values selected:{}{}{}")
    public void appShouldNotDisplayTheResultsBasedOnTheFilterValuesSelected(String scenario, String key, String option) throws Exception {
        assertFalse(manageCustomizingPage.isOrderFilterBy(scenario, key, option));

    }

    @Then("Newly added customization record is available with timeStamp:{},{},{},{}")
    public void newlyAddedCustomizationRecordIsAvailableWithTimeStampScenarioParameterKeyParameterOptionChangedValue(String scenario, String parameterKey, String parameterOption, String parameterValue) throws Exception {
        assertTrue(manageCustomizingPage.isRecordExistWithTime(scenario, parameterKey, parameterOption, parameterValue));
    }

}
