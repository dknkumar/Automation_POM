package com.sap.cmoplatform.teststeps.sem;

import com.sap.cmoplatform.pages.sem.SemHomePage;
import com.sap.cmoplatform.objects.Sem;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SemOverviewTestSteps {

    private SemHomePage semHomePage = new SemHomePage();
    private int semCount;

    @And("^an sem is selected in the SEM home page$")
    public void selectMessage(Map<String, String> message) throws Exception {
        Sem sem = new Sem()
                .withCustomer(message.get("customer"))
                .withProduct(message.get("product"))
                .withBatchId(message.get("batchId"))
                .withIdentifierType(message.get("identifier"))
                .withIdentifier(message.get("identifierNumber"))
                .withStatus(message.get("status"));

        semHomePage.selectCustomer(message.get("customer"));
        semHomePage.selectProduct(message.get("product"));
        semHomePage.selectBatchId(message.get("batchId"));
        semHomePage.selectStatus(message.get("status"));
        semHomePage.clickGoButton();
        semHomePage.selectMessage(sem);
    }

    @And("the customer, {string}, is selected on the SEM Page")
    public void theCustomerCustomerIsSelectedOnTheSEMPage(String customer) throws Exception {
        semHomePage.selectCustomer(customer);
    }

    @And("the product, {string}, is selected on the SEM Page")
    public void theProductProductIsSelectedOnTheSEMPage(String product) throws Exception {
        semHomePage.selectProduct(product);
    }

    @And("the date range for the last month is input in the date picker on the SEM home page filter")
    public void theDateRangeForTheLastMonthIsInputInTheDatePickerOnTheSEMHomePageFilter() throws Exception {
        semHomePage.selectDateRangeForLastMonth();
    }

    @And("the status, {string}, is selected on the SEM page")
    public void theStatusStatusIsSelectedOnTheSEMPage(String status) throws Exception {
        semHomePage.selectStatus(status);
    }

    @And("the apply filter button is clicked in the SEM home page")
    public void theApplyFilterButtonIsClickedInTheSEMHomePage() throws Exception {
        semHomePage.clickGoButton();
    }

    @And("the (show|hide) filters button is clicked on the SEM home page")
    public void theHideFiltersButtonIsClickedOnTheSEMHomePage() throws Exception {
        semHomePage.clickShowHideButton();
    }

    @Then("the results contains the SEM in the SEM home page")
    public void theResultsContainsTheSEMInTheSEMHomePage(Map<String, String> expected) throws Exception {
        List<Sem> sems = semHomePage.getSems();
        for (Sem sem : sems) {
            assertEquals(expected.get("customer"), sem.getCustomer());
            assertEquals(expected.get("product"), sem.getProduct());
            assertEquals(expected.get("identifierType"), sem.getIdentifierType());
            assertEquals(expected.get("identifier"), sem.getIdentifier());
        }
    }

    @Then("the status of the SEMs is {string}")
    public void theStatusOfTheSEMsIsStatus(String expected) throws Exception {
        List<Sem> sems = semHomePage.getSems();
        for (Sem sem : sems) {
            String actual = sem.getStatus();
            assertThat(actual.equals(expected) || actual.contains(expected), is(true));
        }
    }

    @Then("^the filters (are|are not) present on the SEM home page$")
    public void checkFiltersVisibility(String condition) throws Exception {
        if (condition.equals("are")) {
            assertTrue(semHomePage.canSeeStatus());
        } else if (condition.equals("are not")) {
            assertFalse(semHomePage.canSeeStatus());
        }
    }

    @Then("the first SEM message status is {string}")
    public void theFirstSEMMessageStatusIsDraft(String expected) throws Exception {
        String actual = semHomePage.getSems().get(0).getStatus();
        assertEquals("Status does not match", expected, actual);
    }

    @And("the first message status is {string} in SEM home page")
    public void theFirstMessageStatusIsSentInSEMHomePage(String expected) throws Exception {
        assertEquals(expected, semHomePage.getSems().get(0).getStatus());
    }

    @Then("filters are set to default on SEM home page")
    public void filtersAreSetToDefaultOnSEMHomePage() throws Exception {
        String customer = semHomePage.getCustomer();
        assertNull("Customer is not default : " + customer, customer);
        String dateRange = semHomePage.getDateRange();
        assertEquals("Date Range is not default : " + dateRange,"", dateRange);
        assertEquals("Status is not default", "-- All --", semHomePage.getStatus());
    }

    @And("store the number of SEMS on the SEM home page")
    public void storeTheNumberOfSEMSOnTheSEMHomePage() throws Exception {
        semCount = semHomePage.countSems();
    }

    @And("the number of SEMS has not changed on the SEM home page")
    public void theNumberOfSEMSHasNotChangedOnTheSEMHomePage() throws Exception {
        assertEquals("", semCount, semHomePage.countSems());
    }

    @Then("only SEMs relating to {string} are visible on the trade items home page")
    public void onlySEMsRelatingToCustomerAreVisibleOnTheTradeItemsHomePage(String customer) throws Exception {
        List<Sem> sems = semHomePage.getSems();
        for (Sem sem : sems) {
            assertEquals("Wrong customer", sem.getCustomer(), customer);
        }
    }

    @And("the clear filters button is pressed on SEM home page")
    public void theClearFiltersButtonIsPressedOnSEMHomePage() throws Exception {
        semHomePage.clickClearFilterButton();
    }

    @And("the focus returns to the overview page")
    public void theFocusReturnsToTheOverviewPage() throws Exception {
        assertEquals("Serialization Events Management",semHomePage.getPageTitle());
    }

    @And("select the first SEM entry in the table")
    public void selectTheFirstSEMEntryInTheTable() throws Exception {
        semHomePage.selectMessage(semHomePage.getSems().get(0));
    }


}
