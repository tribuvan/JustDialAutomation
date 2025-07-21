package com.cts.justdial.pageobjects;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

abstract public class BasePage {
    WebDriver driver;
    protected BasePage(WebDriver driver){
        this.driver=driver;
    }
    public String getTitle(){
         return driver.getTitle();
    }
    public void clickFreeListing(WebDriver driver){
        driver.findElement(By.xpath("//div[text()='Free Listing']")).click();
    }
}
