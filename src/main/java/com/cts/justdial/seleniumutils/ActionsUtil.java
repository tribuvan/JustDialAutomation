package com.cts.justdial.seleniumutils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil {
    public static void moveToElement(WebDriver driver, WebElement target){
        Actions a = new Actions(driver);
        a.moveToElement(target).perform();
    }
    public static void click(WebDriver driver, WebElement target){
        Actions a = new Actions(driver);
        a.click(target).perform();
    }
    public static void scrollOperation(WebDriver driver,int x, int y){
        Actions a = new Actions(driver);
        a.scrollByAmount(x,y).perform();
    }
    public static void pressEnter(WebDriver driver){
        Actions a = new Actions(driver);
        a.keyDown(Keys.ENTER);
    }

    public static void scrollToElement(WebDriver driver,WebElement element){
        Actions a = new Actions(driver);
        a.scrollToElement(element);
    }
}
