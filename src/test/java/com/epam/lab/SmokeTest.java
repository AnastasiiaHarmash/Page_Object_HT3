package com.epam.lab;

import com.epam.lab.page.HomePageRozetka;
import com.epam.lab.page.SearchResultsPage;
import com.epam.lab.util.PropertiesReader;
import com.epam.lab.util.XMLToObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.logging.Logger;


public class SmokeTest {

    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(SmokeTest.class.getName());
    private static final long DEFAULT_TIMEOUT = 60;
    private static XMLToObject xmlToObject;

    static {
        try {
            xmlToObject = new XMLToObject();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

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

    @DataProvider
    public Object [][] simpleData() {
        return new Object[][] {{"someProduct", "someBrand", "someSum"}};
    }

    @DataProvider
    public Object [][] testData() {
        logger.info("Running dataProvider testData");
        return xmlToObject.testDataMassive();
    }

    @Test(dataProvider = "testData"/*, invocationCount = 5, threadPoolSize = 5*/)
    public void smokeTest(String product, String brand, String sum) throws InterruptedException {
        logger.info("smokeTest is running");
        HomePageRozetka homePageRozetka = new HomePageRozetka(driver);
        homePageRozetka.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        logger.info("Enter text to text field");
        homePageRozetka.enterTextToSearchField(product);
        homePageRozetka.clickSearchButton();
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getSearchBrandField());
        Assert.assertTrue(searchResultsPage.isSearchBrandFieldVisible());
        logger.info("Enter brand to search field");
        searchResultsPage.enterTextToSearchBrandField(brand);
        //redneck code
        Thread.sleep(4000);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getListCheckBox());
        Assert.assertTrue(searchResultsPage.isListCheckBoxVisible());
        Assert.assertTrue(searchResultsPage.isListCheckBoxEnabled());
        logger.info("Click check box");
        searchResultsPage.clickListCheckBox();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getFilterDropDown());
        logger.info("Click filter dropdown");
        searchResultsPage.clickFilterDropDown();
        searchResultsPage.clickFromExpensiveToCheap();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        Thread.sleep(3000);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.isCartIconVisible());
        logger.info("Click add to cart");
        searchResultsPage.clickListOfCartIcons();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getCartButton());
        logger.info("Click on cart button");
        searchResultsPage.clickOnCartButton();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getPrice());
        Assert.assertTrue(searchResultsPage.isPriceVisible());
        Assert.assertTrue(Integer.parseInt(sum) < searchResultsPage.getTextFromPrice());
    }
}
