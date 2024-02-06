package com.sap.cmoplatform.teststeps.common;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import com.sap.cmoplatform.pages.CommonComponentPage;
import com.sap.cmoplatform.utils.PortalDriver;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CommonComponentTestSteps {

    private CommonComponentPage commonComponentPage = new CommonComponentPage();


    @And("^switch to my view if '(.+)' exists$")
    public void switchToMyViewIfViewNameExists(String viewName) throws Exception {
        commonComponentPage.waitForPageToLoad();
        commonComponentPage.clickSelectViewDropDown();
        try {
            if (commonComponentPage.availableView(viewName).isDisplayed()) {
                commonComponentPage.availableView(viewName).click();
            }
        } catch (Exception e) {
        }
    }


    @And("^the (GLN|Active/Inactive|None|Description) option is selected to sort in (Ascending|Descending) Order$")
    public void theOptionIsSelectedToSortInAscendingOrder(String field, String sortOrder) throws Exception {
        if (sortOrder.equals("Ascending")) {
            commonComponentPage.clickAscendingOrderButton();
        } else if (sortOrder.equals("Descending")) {
            commonComponentPage.clickDescendingOrderButton();
        }

        commonComponentPage.selectSortOption(field);
    }

    @And("filter components are selected")
    public void filterComponentsAreSelected(List<String> value) throws Exception {
        PortalDriver.getInstance();
        try {
            if (commonComponentPage.getElements(commonComponentPage.clearFilter).size() > 0) {
                commonComponentPage.clickElement(commonComponentPage.clearFilter);
                for (String listValue : value) {
                    commonComponentPage.selectFilterComponents(listValue);
                }
            }
        } catch (Exception e) {
            for (String listValue : value) {
                commonComponentPage.selectFilterComponents(listValue);
            }
        }
    }

    @And("all the fields are selected as filter component")
    public void allTheFieldsAreSelectedAsFilterComponent() throws Exception {
        commonComponentPage.selectAllSelection();
    }

}