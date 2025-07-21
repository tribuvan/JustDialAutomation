package com.cts.justdial.pageobjects;

import com.cts.justdial.fwutils.CommonUtils;
import com.cts.justdial.seleniumutils.ActionsUtil;
import com.cts.justdial.seleniumutils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    @FindBy(xpath = "//input[@id='city-auto-sug']")
    private WebElement locationInput;

    @FindBy(linkText = "Gyms")
    private WebElement gymOption;

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void closePopup(WebDriver driver){
        WebElement popup = Waits.getElementByVisibility(driver,driver.findElement(By.xpath("//a[text()='Maybe Later']")),120);
        ActionsUtil.moveToElement(driver,popup);
        popup.click();
    }

    public void setLocation(WebDriver driver,String location){
        locationInput.click();
        locationInput.sendKeys(location);
        driver.findElement(By.xpath("//*[@id=\"react-autowhatever-city-auto-suggest--item-1\"]/a/div/b")).click();
    }

    public String getSelectedLocation(WebDriver driver){
        return locationInput.getAttribute("value");
    }

    public void searchService(WebDriver driver,String searchText){
        driver.findElement(By.xpath("//input[@id='main-auto']")).sendKeys(searchText);
        CommonUtils.sureWait(2);
        WebElement searchbtn = driver.findElement(By.xpath("//div[@id='srchbtn']"));

        //JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", searchbtn);
    }

    public void clickFitnessOption(WebDriver driver){
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WebElement fitnessElem=Waits.getElementByVisibility(driver,driver.findElement(By.xpath("//*[@aria-controls='panel4' and @id='tab4']")),10);
        js.executeScript("arguments[0].scrollIntoView()",fitnessElem);
        js.executeScript("window.scrollTo(0, -50);");
        fitnessElem.click();
    }

    public void selectGym(WebDriver driver){
        Waits.getElementByVisibility(driver,gymOption,10).click();
    }

    public boolean isGymsEnabled(WebDriver driver){
        return gymOption.isEnabled();
    }



}
