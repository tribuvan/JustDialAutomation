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
    public static void implicitlyWait(WebDriver driver, int time){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }
    public static void waitForElement(WebDriver driver, By by, int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        wdw.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public static WebElement getElementByVisibility(WebDriver driver,WebElement element, int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        return wdw.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement getClickableElements(WebDriver driver,WebElement element,int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        return wdw.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static boolean waitForNumber(WebDriver driver, By by, Pattern pattern, int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        return wdw.until(ExpectedConditions.textMatches(by,pattern));
    }

    public static List<WebElement> getAllVisibleElements(WebDriver driver,By by, int time){
        WebDriverWait wdw = new WebDriverWait(driver,Duration.ofSeconds(time));
        return wdw.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
}
