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
import java.sql.DataTruncation;
import java.util.logging.Logger;

import static com.google.common.primitives.UnsignedBytes.toInt;


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

    /*@DataProvider
    public Object [][] testData() {
        return new Object[][] {{"laptop", "HP", "50000"}};
    }*/

    @DataProvider
    public Object [][] testData() {
        return xmlToObject.testDataMassive();
    }

    @Test(dataProvider = "testData", invocationCount = 1)
    public void smokeTest(String product, String brand, String sum) throws InterruptedException {
        HomePageRozetka homePageRozetka = new HomePageRozetka(driver);
        homePageRozetka.waitForPageLoadComplete(DEFAULT_TIMEOUT);

        homePageRozetka.enterTextToSearchField(product);

        homePageRozetka.clickSearchButton();

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getSearchBrandField());
        Assert.assertTrue(searchResultsPage.isSearchBrandFieldVisible());

        searchResultsPage.enterTextToSearchBrandField(brand);

        //заглушка
        Thread.sleep(4000);

        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getHpCheckBox());
        Assert.assertTrue(searchResultsPage.isHpCheckBoxVisible());
        Assert.assertTrue(searchResultsPage.isHpCheckBoxEnabled());

        searchResultsPage.hpCheckBoxClick();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getFilterDropDown());
        searchResultsPage.clickFilterDropDown();
        searchResultsPage.clickFromExpensiveToCheap();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.isCartIconVisible());
        searchResultsPage.clickListOfCartIcons();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getCartButton());
        searchResultsPage.clickOnCartButton();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getPrice());
        Assert.assertTrue(searchResultsPage.isPriceVisible());

        Assert.assertTrue(Integer.parseInt(sum) < searchResultsPage.getTextFromPrice());
    }
}
