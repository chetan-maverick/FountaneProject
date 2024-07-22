package com.fountane.amazon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends BasePage
{
	private By sponsoredProducts = By.cssSelector("span[data-component-type='s-search-results'] .s-sponsored-list-header");

    public SearchResultPage(WebDriver driver) 
    {
        super(driver);
    }

    public List<WebElement> getSponsoredProducts()
    {
        return driver.findElements(sponsoredProducts);
    }

}
