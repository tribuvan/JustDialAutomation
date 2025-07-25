package com.cts.justdial.seleniumutils;

import com.cts.justdial.fwutils.CommonUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class ScreenshotUtil {
    //method to take screenshot of a webelement and store it in the screenshots folder
    public static void takeScreenshot(WebElement element){
        TakesScreenshot tss = (TakesScreenshot) element;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File det = new File("screenshots/"+ CommonUtils.getCurrentDate()+".png");
        try{
            FileUtils.copyFile(src,det);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //method to take screenshot using a driver which will take the screenshot of the visible section of the page
    public static void takeScreenshot(WebDriver driver)
    {
        TakesScreenshot tss = (TakesScreenshot) driver;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File det = new File("screenshots/"+ CommonUtils.getCurrentDate()+".png");
        try{
            FileUtils.copyFile(src,det);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //this method is used to take screenshot if a test case is failed
    public static String takeScreenShot(WebDriver driver, String testName){
        TakesScreenshot tss = (TakesScreenshot) driver;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshots/" + testName + "_" + CommonUtils.getCurrentDate()+ ".png");
        src.renameTo(dest);
        return testName;
    }
}
