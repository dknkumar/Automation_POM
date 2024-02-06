package com.sap.cmoplatform.teststeps.configurealertresolution;

import com.sap.cmoplatform.pages.CommonComponentPage;
import com.sap.cmoplatform.pages.configureVerificationResponses.ConfigureVerificationResponsesPage;
import com.sap.cmoplatform.pages.configurealertresolution.ConfigureAlertResolutionPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import scala.util.Random;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConfigureAlertResolutionTestSteps {

    private ConfigureAlertResolutionPage configureAlertResolutionPage = new ConfigureAlertResolutionPage();
    private CommonComponentPage commonComponentPage = new CommonComponentPage();
    private List<String> issueDescription, resolutionDescription;
    String issueItemCount;
    String issueNAme = "AutoIssue" + RandomStringUtils.randomAlphabetic(3);

    @Then("^the (issues|resolutions) page title is '(.+)'$")
    public void thePageTitleIsPageTitle(String page, String title) throws Exception {
        if (page.equals("issues")) {
            assertEquals(configureAlertResolutionPage.getIssuesPageTitle(), title);
        }
        if (page.equals("resolutions")) {
            assertEquals(configureAlertResolutionPage.getResolutionsPageTitle(), title);

        }
    }

    @Then("^the issues page header is '(.+)'$")
    public void theIssuesPageHeaderIsIssuePageTitle(String issueTitle) throws Exception {
        assertEquals(configureAlertResolutionPage.getIssuePageHeader(), issueTitle);
    }

    @Then("^the resolution page title is '(.+)'$")
    public void theResolutionPageTitleIsResolutionPageTitle(String resolutionTitle) throws Exception {
        assertEquals(configureAlertResolutionPage.getResolutionPageHeader(), resolutionTitle);

    }

    @And("^click on add (issues|resolutions) button$")
    public void clickOnAddIssueButton(String id) throws Exception {
        if (id.equals("issues")) {
            configureAlertResolutionPage.clickAddIssueButton();
        } else if (id.equals("resolutions")) {
            configureAlertResolutionPage.clickAddResolutionButton();
        }
    }

    @Then("^the add (issue|resolution) pop up title is '(.+)'$")
    public void theAddIssuePopUpTitleIsAddIssueTitle(String id, String title) throws Exception {
        if (id.equals("issue")) {
            assertEquals(configureAlertResolutionPage.getAddIssueText(), title);
        } else if (id.equals("resolution")) {
            assertEquals(configureAlertResolutionPage.getAddResolutionText(), title);

        }
    }

    @Then("^the add input ghost text is '(.+)'$")
    public void theAddInputGhostTextIsAddInputGhostText(String ghostText) throws Exception {
        assertEquals(configureAlertResolutionPage.getAddInput().getAttribute("placeholder"), ghostText);
    }

    @And("^enter input value '(.+)'$")
    public void enterInputValue(String issue) throws Exception {
        configureAlertResolutionPage.getAddInput().sendKeys(issue);
    }

    @And("^enter issue value$")
    public void enterIssueValue() throws Exception {
        configureAlertResolutionPage.getAddInput().sendKeys(issueNAme);
    }

    @Then("^the (issue|resolution) list contains '(.+)'$")
    public void theIssueListContainsIssue(String id, String value) throws Exception {
        List<String> list = new ArrayList<>();
        if (id.equals("issue")) {
            list = configureAlertResolutionPage.getIssuesFromList();
        } else if (id.equals("resolution")) {
            list = configureAlertResolutionPage.getResolutionsFromList();

        }
        boolean flag = false;
        for (String issue : list) {
            if (issue.equalsIgnoreCase(value)) {
                flag = true;
                break;
            }
        }
        assertTrue(flag);
    }

    @And("^get the (issues|resolutions) item list count from the List$")
    public void getTheItemListCountFromTheList(String itemName) throws Exception {
        String header = "";
        // Thread.sleep(20000);
        if (itemName.equals("issues")) {
            header = configureAlertResolutionPage.getIssueListHeader();
        } else if (itemName.equals("resolutions")) {
            header = configureAlertResolutionPage.getResolutionListHeader();
        }
        System.out.println("header " + header);
        issueItemCount = header.substring(header.indexOf("(") + 1, header.indexOf(")"));
        System.out.println("issueItemCount " + issueItemCount);

    }

    @Then("^the (issues|resolutions) item List (is|is not) (incremented|decremented)$")
    public void theItemListIsIncremented(String itemName, String id, String value) throws Exception {
        String header = "";
        {

            if (itemName.equals("issues")) {
                header = configureAlertResolutionPage.getIssueListHeader();
            } else if (itemName.equals("resolutions")) {
                header = configureAlertResolutionPage.getResolutionListHeader();
            }
            String newIssueItemCount = header.substring(header.indexOf("(") + 1, header.indexOf(")"));
            if (value.equals("incremented")) {
                if (id.equals("is")) {
                    assertTrue(Integer.parseInt(newIssueItemCount) > Integer.parseInt(issueItemCount));
                } else if (id.equals("is not")) {
                    assertTrue(Integer.parseInt(newIssueItemCount) == Integer.parseInt(issueItemCount));
                }
            } else if (value.equals("decremented")) {
                System.out.println(newIssueItemCount + " " + issueItemCount);
                assertTrue(Integer.parseInt(newIssueItemCount) < Integer.parseInt(issueItemCount));

            }
        }
    }

    @And("^select an item '(.+)' from the list$")
    public void selectTheIssueIssueFromTheList(String issue) throws Exception {
        configureAlertResolutionPage.selectTheIssue(issue);
    }

    @And("^select issue from the list$")
    public void selectIssueFromTheList() throws Exception {
        configureAlertResolutionPage.selectTheIssue(issueNAme);
    }

    @And("^verify an item '(.+)' already exists in the list$")
    public void verifyAnItemIssueAlreadyExistsInList(String issue) throws Exception {
        configureAlertResolutionPage.verifyTheIssue(issue);
    }

    @And("^the delete (issue|resolution) button is clicked$")
    public void theDeleteIssueButtonIsClicked(String id) throws Exception {
        if (id.equals("issue")) {
            configureAlertResolutionPage.clickDeleteIssueButton();
        } else if (id.equals("resolution")) {
            configureAlertResolutionPage.clickDeleteResolutionButton();

        }
    }

    @Then("verify the maximum allowed length for the issue field")
    public void verifyTheMaximumAllowedLengthForTheIssueIssueField() throws Exception {
        assertTrue(configureAlertResolutionPage.getAddInput().getAttribute("value").length() == 30);
    }

    @And("^click on sort (issue|resolution) button$")
    public void clickOnSortIssueButton(String id) throws Exception {
        if (id.equals("issue")) {
            configureAlertResolutionPage.clickSortIssueButton();
        } else if (id.equals("resolution")) {
            configureAlertResolutionPage.clickSortResolutionImg();

        }
    }

    @Then("the sort pop up is displayed")
    public void theSortPopUpIsDisplayed() throws Exception {
        assertTrue(configureAlertResolutionPage.getSortPopUpHeader().isDisplayed());
    }


    @Then("^the (issue|resolution) (Description|None) column is sorted in (Ascending|Descending) order$")
    public void theColumnIsSortedInAscendingOrder(String id, String columnName, String sortOrder) throws Exception {
        if (id.equals("issue")) {
            String[] a = new String[configureAlertResolutionPage.getIssuesFromList().size()];
            if (sortOrder.equals("Ascending")) {
                if (columnName.equals("Description")) {
                    assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(configureAlertResolutionPage.getIssuesFromList().toArray(a)));
                }

            } else if (sortOrder.equals("Descending")) {
                if (columnName.equals("Description")) {
                    assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(configureAlertResolutionPage.getIssuesFromList().toArray(a)));
                }
            }
        } else if (id.equals("resolution")) {
            String[] a = new String[configureAlertResolutionPage.getResolutionsFromList().size()];
            if (sortOrder.equals("Ascending")) {
                if (columnName.equals("Description")) {
                    assertTrue(commonComponentPage.isColumnSortedInAscendingOrder(configureAlertResolutionPage.getResolutionsFromList().toArray(a)));
                }

            } else if (sortOrder.equals("Descending")) {
                if (columnName.equals("Description")) {
                    assertTrue(commonComponentPage.isColumnSortedInDescendingOrder(configureAlertResolutionPage.getResolutionsFromList().toArray(a)));
                }
            }
        }
    }


    @And("get issue description from the list")
    public void getIssueDescriptionFromTheList() throws Exception {
        issueDescription = configureAlertResolutionPage.getIssuesFromList();
    }

    @Then("^(issue|resolution) description column is sorted by server sequence order$")
    public void issueDescriptionColumnIsSortedByServerSequenceOrder(String id) throws Exception {
        if (id.equals("issue")) {
            String[] newList = new String[configureAlertResolutionPage.getIssuesFromList().size()];
            String[] oldList = new String[issueDescription.size()];

            assertTrue(commonComponentPage.isColumnSortedInServerSequenceOrder(configureAlertResolutionPage.getIssuesFromList().toArray(newList), issueDescription.toArray(oldList)));
        } else if (id.equals("resolution")) {
            String[] newList = new String[configureAlertResolutionPage.getResolutionsFromList().size()];
            String[] oldList = new String[resolutionDescription.size()];

            assertTrue(commonComponentPage.isColumnSortedInServerSequenceOrder(configureAlertResolutionPage.getResolutionsFromList().toArray(newList), resolutionDescription.toArray(oldList)));

        }
    }

    @Then("^the error message '(.+)' is displayed for add issue input$")
    public void theErrorMessageIsDisplayedForAddIssueInput(String expectedErrorMessage) throws Exception {
        assertEquals(configureAlertResolutionPage.getAddIssueInputErrorMessage(), expectedErrorMessage);
    }

    @And("^click on (resolution|issue) menu list$")
    public void clickOnResolutionMenuList(String id) throws Exception {
        if (id.equals("resolution")) {
            configureAlertResolutionPage.clickResolutionsImg();
        } else if (id.equals("issue")) {
            configureAlertResolutionPage.clickIssuesImg();
        }
    }

    @Then("^the delete pop up message is '(.+)'$")
    public void theDeleteResolutionPopUpTitleIsDeleteResolutionTitle(String expectedMessage) throws Exception {
        assertEquals(expectedMessage, configureAlertResolutionPage.getDeleteConfirmationText());

    }

    @And("get resolution description from the list")
    public void getResolutionDescriptionFromTheList() throws Exception {
        resolutionDescription = configureAlertResolutionPage.getResolutionsFromList();
    }

    @Then("^the app header is '(.+)'$")
    public void theAppHeaderIsHeader(String expectedHeader) throws Exception {
        assertEquals(expectedHeader, configureAlertResolutionPage.getAppSubHeader());
    }
}