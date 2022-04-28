package com.epam.lab.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageRozetka extends BasePage{

    @FindBy(xpath = "//input[@class='search-form__input ng-untouched ng-pristine ng-valid']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='button button_color_green button_size_medium search-form__submit ng-star-inserted']")
    private WebElement searchButton;

    public HomePageRozetka(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchFieldVisible() { return searchField.isDisplayed(); }
    public void enterTextToSearchField(final String searchText) {
        searchField.clear();
        searchField.sendKeys(searchText);
    }
    public void clickSearchButton() { searchButton.click(); }

}
