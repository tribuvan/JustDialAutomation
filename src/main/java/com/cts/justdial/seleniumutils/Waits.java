package com.cts.justdial.seleniumutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class Waits {
    //method to wait until elements are loaded
    public static void implicitlyWait(WebDriver driver, int time){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }
    //method to wait until an element is visible using By as a parameter
    public static void waitForElement(WebDriver driver, By by, int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        wdw.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    //method to wait until an element is visible using Webelement as a parameter and return the element
    public static WebElement getElementByVisibility(WebDriver driver,WebElement element, int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        return wdw.until(ExpectedConditions.visibilityOf(element));
    }
    //method to wait until the element is clickable and return the element
    public static WebElement getClickableElements(WebDriver driver,WebElement element,int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        return wdw.until(ExpectedConditions.elementToBeClickable(element));
    }
    //method to wait until a text appears which matches specified regex patter
    public static boolean waitForNumber(WebDriver driver, By by, Pattern pattern, int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        return wdw.until(ExpectedConditions.textMatches(by,pattern));
    }
    //method to wait until all the elements located are visible and the return the list of these elements
    public static List<WebElement> getAllVisibleElements(WebDriver driver,By by, int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        return wdw.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
}
