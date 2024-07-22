package com.fountane.amazon.test;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import com.fountane.amazon.pages.HomePage;
import com.fountane.amazon.pages.ProductPage;
import com.fountane.amazon.pages.SearchResultPage;
import com.fountane.amazon.utils.BrowserManager;
import com.fountane.amazon.utils.Excelutils;
import com.fountane.amazon.utils.LoggerUtil;


public class SearchProductTest 
{
	private WebDriver driver;
	private HomePage homePage;
	private SearchResultPage searchResultsPage;
	private ProductPage productPage;
	private Logger logger =  LoggerUtil.getLogger();

	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) {
		driver = BrowserManager.getDriver(browser); 
		driver.get("https://www.amazon.com/");
		homePage = new HomePage(driver);
		searchResultsPage = new SearchResultPage(driver);
		productPage = new ProductPage(driver);
	}

	@DataProvider(name = "productData")
	public Object[][] getProductData() {
		String filePath = "C:\\Users\\Chetan\\eclipse-workspace-StarAgile\\Amazon\\testresources\\test_data.xlsx.xlsx"; // Update this path
		String sheetName = "Sheet1";
		List<Object[]> testData = Excelutils.getTestData(filePath, sheetName);
		return testData.toArray(new Object[0][]);
	}

	@Test(dataProvider = "productData")
	public void testSearchProduct(String productName) {
		homePage.searchProduct(productName);
		((org.apache.logging.log4j.Logger) logger).info("Searched for "+ productName);

		List<WebElement> sponsoredProducts = searchResultsPage.getSponsoredProducts();
		Assert.assertTrue(sponsoredProducts.size() > 0);
		logger.info("Verified search results contain sponsored products");
	}

	@Test
	public void testAddSponsoredProductsToCart() {
		homePage.searchProduct("Laptop");
		List<WebElement> sponsoredProducts = searchResultsPage.getSponsoredProducts();

		for (int i = 0; i < 2 && i < sponsoredProducts.size(); i++) {
			sponsoredProducts.get(i).click();
			productPage.addToCart();
			logger.info("Added product to cart");
			driver.navigate().back();
		}
	}

}
