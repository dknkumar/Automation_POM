package com.sap.cmoplatform.pages;

import com.sap.cmoplatform.SeleniumUI5TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CommonComponentPage extends Page {

    private By selectViewDropDown = By.xpath("//*[contains(@id,'filterbar-variantUi2-trigger-img')]");
    private By manageViewButton = By.xpath("//*[contains(@id,'filterbar-variantUi2-manage-BDI-content')]");
    private By manageSaveButton = By.xpath("//*[contains(@id,'filterbar-variantUi2-managementsave-BDI-content')]");
    private By manageCancelButton = By.xpath("//*[contains(@id,'filterbar-variantUi2-managementcancel-BDI-content')]");
    private By errorMessage = By.xpath("//div[@role='alertdialog']/..//section/div/div/span");
    private By descendingRadioButton = By.xpath("//*[text()='Descending']/preceding::input[1]/parent::div");
    private By ascendingRadioButton = By.xpath("//*[text()='Ascending']/preceding::input[1]/parent::div");
    private By moreFiltersLink = By.xpath("//a[contains(text(),'More Filters')]");
    private By setFilter = By.xpath("//section[contains(@id,'filterbar-set-filters-dialog-cont')]/..//li/div/div");
    public By clearFilter = By.xpath("//span[contains(@id,'clearSelection')]");
    public By filterSearch = By.xpath("//input[contains(@id,'searchField-I')]");

    Actions actions = new Actions(SeleniumUI5TestUtil.getConfig().getDriver());


    public void clickSelectViewDropDown() throws Exception {
        waitForPageToLoad();
        getElement(selectViewDropDown).click();

    }

    public WebElement availableView(String viewName) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//li[text()='" + viewName + "']"));
    }

    public void setDefaultView(String viewName) throws Exception {
        waitForPageToLoad();
        if (viewName.equals("Standard")) {
            getElement(manageViewButton).click();
            if (!getElement(By.xpath("//*[text()='" + viewName + "']/following::td/..//input")).isSelected()) {
                getElement(By.xpath("//*[text()='" + viewName + "']/following::td/..//input/parent::div")).click();
                getElement(manageSaveButton).click();
            } else {
                getElement(manageCancelButton).click();
            }

        } else {
            getElement(manageViewButton).click();
            if (!getElement(By.xpath("//input[@value='" + viewName + "']/../following::input[1]")).isSelected()) {
                getElement(By.xpath("//input[@value='" + viewName + "']/../following::input[1]/parent::div")).click();
                getElement(manageSaveButton).click();
            } else {
                getElement(manageCancelButton).click();
            }
        }
    }

    public void selectAvailableView(String fileName) throws Exception {
        waitForPageToLoad();
        actions.moveToElement(getElement(By.xpath("//li[text()='" + fileName + "']"))).click().perform();

    }

    public String getDownloadErrorMessage() throws Exception {
        waitForPageToLoad();
        return getElement(errorMessage).getText();
    }

    public void clickDescendingOrderButton() throws Exception {
        waitForPageToLoad();
        getElement(descendingRadioButton).click();
    }

    public void clickAscendingOrderButton() throws Exception {
        waitForPageToLoad();
        getElement(ascendingRadioButton).click();
    }

    public void selectSortOption(String field) throws Exception {
        waitForPageToLoad();
        getElement(By.xpath("//*[text()='" + field + "']/preceding::input[1]/parent::div")).click();
    }

    public Boolean isColumnSortedInAscendingOrder(String[] columnValue) {
        Boolean flag = false;
        int size = columnValue.length;
        String[] actual = new String[size];
        String[] sorted = new String[size];

        for (int i = 0; i < size; i++) {
            actual[i] = sorted[i] = columnValue[i];
        }

        Arrays.sort(sorted, Comparator.nullsLast(Comparator.naturalOrder()));

        //Sorting the array
        try {
            for (int i = 0; i < size; i++) {
                System.out.println(i + ": actual ->" + actual[i] + " sorted ->" + sorted[i]);
                if (actual[i].equals(sorted[i])) {
                    flag = true;
                } else {
                    return flag;
                }
            }
        }
        catch (Exception e) {
            System.out.println("The column is empty");
            return true;
        }


        return flag;
    }

    public Boolean isColumnSortedInDescendingOrder(String[] columnValue) {
        Boolean flag = false;
        int size = columnValue.length;
        String[] actual = new String[size];
        String[] sorted = new String[size];
        for (int i = 0; i < size; i++) {
            columnValue[i] = columnValue[i].toLowerCase();
            actual[i] = sorted[i] = columnValue[i];
        }
        //Sorting the array
        Arrays.sort(sorted, Comparator.nullsLast(Comparator.reverseOrder()));
        //Sorting the array
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": actual ->" + actual[i] + " sorted ->" + sorted[i]);
            if (actual[i].equals(sorted[i])) {
                flag = true;
            } else {
                return flag;
            }
        }
        return flag;
    }


    public Boolean isColumnSortedInServerSequenceOrder(String[] columnValue, String[] defaultColumnValue) throws Exception {
        Boolean flag = false;
        int size = columnValue.length;

        for (int i = 0; i < size; i++) {
            if (columnValue[i].equals(defaultColumnValue[i])) {
                flag = true;
            } else {
                return flag;
            }
        }

        return flag;
    }


    public void selectFilterComponents(String filterValue) throws Exception {
        getElement(filterSearch).clear();
        inputText(filterSearch, filterValue);
        if (!getElement(By.xpath("//*[text()='" + filterValue + "']/preceding::input[1]")).isSelected()) {
            getElement(By.xpath("//*[text()='" + filterValue + "']/preceding::input[1]/parent::div")).click();
        }
    }


    public void setFilterSection() throws Exception {
        try {
            if (getElement(moreFiltersLink).isDisplayed()) {
                getElement(moreFiltersLink).click();
                waitForPageToLoad();
                List<WebElement> elements = getElements(setFilter);
                for (WebElement element : elements) {
                    if (!element.getAttribute("class").contains("sapMCbMarkChecked")) {
                        element.click();

                        //   actions.moveToElement(element).click().perform();
                    }

                }
                clickButton("OK");

            }
        } catch (Exception e) {
            //handle exception if all filters are applied
        }


    }

    public void unSelectAllSelection() throws Exception {
        waitForPageToLoad();
        int i = 1;
        //  List<WebElement> checkboxes = getElements(By.xpath("//*[contains(@id,'listView-innerSelectionPanelTable-listUl')]/..//input"));
        List<WebElement> checkboxes = getElements(By.xpath("//*[contains(@id,'listView-innerListViewTable-listUl')]/..//input"));
        System.out.println(checkboxes.size());
        for (WebElement checkbox : checkboxes) {
            System.out.println(checkbox.getAttribute("checked"));

            try {
                if (checkbox.getAttribute("checked").equals("true")) {
                    System.out.println("true");
                    //   actions.moveToElement(getElement(By.xpath("(//div[@title='Show on Filter Bar']/div)[" + i + "]"))).click().perform();
                    getElement(By.xpath("(//*[contains(@id,'listView-innerListViewTable-listUl')]/..//input/parent::div)[" + i + "]")).click();
                    System.out.println("click");
                }
            } catch (NullPointerException ne) {
                System.out.println("Filter Element not selected");
            }
            i++;
        }

    }

    public void selectAllSelection() throws Exception {
        waitForPageToLoad();
        int i = 1;
        List<WebElement> checkboxes = getElements(By.xpath("//*[contains(@id,'innerListViewTable-listUl')]/..//input"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                actions.moveToElement(getElement(By.xpath("(//*[contains(@id,'innerListViewTable-listUl')]/..//input/parent::div)[" + i + "]"))).click();
                i++;
            }

        }
    }

    public WebElement getAppNameElement(String name) throws Exception {
        waitForPageToLoad();
        return getElement(By.xpath("//*[text()='" + name + "'][contains(@id,'title-inner')]"));
    }

    public static void waitForPresenceofElement(By webElement) throws Exception {
        WebDriverWait w = new WebDriverWait(SeleniumUI5TestUtil.getConfig().getDriver(), 30);
        w.until(ExpectedConditions.presenceOfElementLocated(webElement));
    }
}