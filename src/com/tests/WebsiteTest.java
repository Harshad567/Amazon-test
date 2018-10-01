package com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.AddToCartConfirmPage;
import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailsPage;
import com.pages.SearchResultsPage;
import com.util.ExcelReader;

public class WebsiteTest {

    private WebDriver driver;
    private HomePage homePage;
    
	@DataProvider
	public String[][] getExcelData() {
		ExcelReader read = new ExcelReader();
		return read.getCellData("D:/Book1.xls", "Sheet1");
	}

    @BeforeClass
    public void setUp(){
    	System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void openHomePage(){
    	driver.get("http://www.amazon.com");
        homePage = new HomePage(driver).open();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test(testName = "addToCart", dataProvider = "getExcelData")
    public void testAddToCart(String category, String searchString) throws InterruptedException{
        SearchResultsPage searchResultsPage = homePage.navigationMenu().searchFor(category, searchString);
        String itemTitle = searchResultsPage.getFirstResultTitle();
        ProductDetailsPage productDetailsPage = searchResultsPage.clickFirstResultTitle();

        assert (productDetailsPage.getProductTitle().equals(itemTitle));
        AddToCartConfirmPage addToCartConfirmPage = productDetailsPage.addToCart();
        assert (addToCartConfirmPage.getConfirmationText().equals("Added to Cart"));

        CartPage cartPage = homePage.navigationMenu().navigateToCartPage();
        assert (cartPage.getFirstItemText().contains(itemTitle));

    }
}
