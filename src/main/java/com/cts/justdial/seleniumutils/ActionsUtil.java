package com.cts.justdial.seleniumutils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil {
    //method to move the cursor to a particular element
    public static void moveToElement(WebDriver driver, WebElement target){
        Actions a = new Actions(driver);
        a.moveToElement(target).perform();
    }
    //method to click a webelement using actions class
    public static void click(WebDriver driver, WebElement target){
        Actions a = new Actions(driver);
        a.click(target).perform();
    }
    //method to scroll the page by an x,y offsets(pixels)
    public static void scrollOperation(WebDriver driver,int x, int y){
        Actions a = new Actions(driver);
        a.scrollByAmount(x,y).perform();
    }
    //method to press enter
    public static void pressEnter(WebDriver driver){
        Actions a = new Actions(driver);
        a.keyDown(Keys.ENTER).perform();
    }
    //method to scroll the page to till the webelement is visible.
    public static void scrollToElement(WebDriver driver,WebElement element){
        Actions a = new Actions(driver);
        a.scrollToElement(element).perform();
    }
}
