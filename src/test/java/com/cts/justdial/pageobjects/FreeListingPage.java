package com.cts.justdial.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FreeListingPage extends BasePage {

    public FreeListingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='1']")
    private WebElement mobileInput;

    @FindBy(xpath = "//button[text()='Start Now ']")
    private WebElement startNowButton;

    @FindBy(xpath = "//span[@class='undefined entermobilenumber_error__text__uPM09']")
    private WebElement errorMessage;

    @FindBy(xpath = "//*[@class='modal_modal__zB_6A otp_otp__cNujS']")
    private WebElement otpPopup;

    public String checkErrorMsgWithAlphabeticInput(WebDriver driver,String str) {
        mobileInput.sendKeys(str);
        startNowButton.click();
        return errorMessage.getText();
    }

    public String checkErrorMsgWithEmptyInput(WebDriver driver) {
        mobileInput.clear(); // Ensures the field is empty
        startNowButton.click();
        return errorMessage.getText();
    }

    public String checkErrorMsgWithSpecialCharInput(WebDriver driver, String str) {
        mobileInput.sendKeys(str);
        startNowButton.click();
        return errorMessage.getText();
    }

    public String checkErrorMsgWithShortNumberInput(WebDriver driver,String num) {
        mobileInput.sendKeys(num);
        startNowButton.click();
        return errorMessage.getText();
    }
    public boolean checkErrorMsgWithLongNumInput(WebDriver driver, String num) {
        mobileInput.sendKeys(num);
        startNowButton.click();
        return otpPopup.isDisplayed();
    }



}
