package com.epam.lab;

import com.epam.lab.page.HomePageRozetka;
import com.epam.lab.page.SearchResultsPage;
import com.epam.lab.util.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.logging.Logger;

import static com.google.common.primitives.UnsignedBytes.toInt;


public class SmokeTest {

    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(SmokeTest.class.getName());
    private static final long DEFAULT_TIMEOUT = 60;

    @BeforeTest
    public void profileSetUp() {
        logger.info("BeforeTest in progress.");
        PropertiesReader propertiesReader = new PropertiesReader();
        System.setProperty(propertiesReader.getDriverName(), propertiesReader.getDriverLocation());
    }

    @BeforeMethod
    public void testsSetUp() {
        logger.info("BeforeMethod in progress.");
        PropertiesReader propertiesReader = new PropertiesReader();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(propertiesReader.getUrl());
    }

    @AfterMethod
    public void tearDown() {
        logger.info("AfterMethod, driver close.");
        driver.close();
    }

    @Test
    public void smokeTest(){
        TestData testData = new TestData();

        HomePageRozetka homePageRozetka = new HomePageRozetka(driver);
        homePageRozetka.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePageRozetka.enterTextToSearchField(testData.getProduct());
        homePageRozetka.clickSearchButton();

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        Assert.assertTrue(searchResultsPage.isSearchBrandFieldVisible());
        searchResultsPage.enterTextToSearchBrandField(testData.getBrand());
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getHpCheckBox());
        Assert.assertTrue(searchResultsPage.isHpCheckBoxVisible());
        Assert.assertTrue(searchResultsPage.isHpCheckBoxEnabled());
        searchResultsPage.hpCheckBoxClick();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getFilterDropDown());
        searchResultsPage.clickFilterDropDown();
        searchResultsPage.clickFromExpensiveToCheap();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.clickListOfCartIcons();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getCartButton());
        searchResultsPage.clickOnCartButton();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getPrice());
        Assert.assertTrue(searchResultsPage.isPriceVisible());
        Assert.assertTrue(testData.getSum() < searchResultsPage.getTextFromPrice());

    }

}
