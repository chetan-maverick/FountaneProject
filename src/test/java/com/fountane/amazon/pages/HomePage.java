package com.fountane.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class HomePage extends BasePage
{
	private By searchBox = By.id("twotabsearchtextbox");
	private By searchButton = By.id("nav-search-submit-button");

	public HomePage(WebDriver driver) 
	{
		super(driver);
	}

	public void searchProduct(String productName) {
		enterText(searchBox, productName);
		click(searchButton);
	}

}
