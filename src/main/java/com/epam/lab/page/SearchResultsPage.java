package com.epam.lab.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//div[@data-filter-name='producer']//input[@name='searchline']")
    private WebElement searchBrandField;
    //a[@data-id='HP']
    @FindBy(xpath = "//ul[@class='checkbox-filter'][2]//li//a")
    private List<WebElement> listCheckBox;

    @FindBy(xpath = "//select[@class='select-css ng-untouched ng-pristine ng-valid ng-star-inserted']")
    private WebElement filterDropDown;

    @FindBy(xpath = "//select[@class='select-css ng-untouched ng-pristine ng-valid ng-star-inserted']//option")
    private List<WebElement> fromExpensiveToCheap;

    @FindBy(xpath = "//button[@class='buy-button goods-tile__buy-button ng-star-inserted']")
    private List<WebElement> listOfCartIcons;

    @FindBy(xpath = "//button[@class='header__button ng-star-inserted header__button--active']")
    private WebElement cartButton;

    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']//span")
    private WebElement price;

    public boolean isSearchBrandFieldVisible() { return searchBrandField.isDisplayed(); }
    public void enterTextToSearchBrandField(final String searchText) {
        searchBrandField.clear();
        searchBrandField.sendKeys(searchText);
        searchBrandField.sendKeys(Keys.ENTER);
    }
    public WebElement getSearchBrandField() { return searchBrandField;}

    public WebElement getListCheckBox() { return listCheckBox.get(0);}
    public boolean isListCheckBoxVisible() { return listCheckBox.get(0).isDisplayed(); }
    public boolean isListCheckBoxEnabled() { return listCheckBox.get(0).isEnabled(); }
    public void clickListCheckBox() { listCheckBox.get(0).click(); }

    public WebElement getFilterDropDown() {return filterDropDown;}
    public void clickFilterDropDown() { filterDropDown.click();}

    public void clickFromExpensiveToCheap() { fromExpensiveToCheap.get(2).click(); }
    public WebElement isCartIconVisible() { return listOfCartIcons.get(0);}
    public void clickListOfCartIcons() { listOfCartIcons.get(0).click(); }

    public void clickOnCartButton() {cartButton.click();}
    public WebElement getCartButton() { return cartButton; }

    public boolean isPriceVisible() { return price.isDisplayed(); }
    public WebElement getPrice() { return price; }
    public int getTextFromPrice() {return Integer.parseInt(price.getText());}

    public SearchResultsPage(WebDriver driver) { super(driver); }
}
