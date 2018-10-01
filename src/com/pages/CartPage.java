package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private static final By LIST_ITEM = By.className("sc-list-item-content");
    private WebDriver driver;

    public CartPage(WebDriver driver)    {
        this.driver = driver;
    }
    public String getFirstItemText(){
        return driver.findElement(LIST_ITEM).getText();
    }
}
