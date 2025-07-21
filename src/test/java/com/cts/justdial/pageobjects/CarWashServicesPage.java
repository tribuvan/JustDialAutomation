package com.cts.justdial.pageobjects;

import com.cts.justdial.seleniumutils.ActionsUtil;
import com.cts.justdial.seleniumutils.Waits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CarWashServicesPage extends BasePage {

    public CarWashServicesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@id='all_filters_btn']")
    private WebElement allFiltersButton;

    @FindBy(xpath = "//div[@class='jsx-d347624528bd8c18 filcn mr-5']/parent::span[text()='Top Rated']")
    private WebElement topRatedFilter;

    @FindBy(xpath = "//span[text()='4.0+']")
    private WebElement ratingFilter;

    @FindBy(xpath = "//button[text()='Apply Filters']")
    private WebElement applyFiltersButton;

    @FindBy(xpath = "//div[@class='jsx-7cbb814d75c86232 resultbox_info']")
    private List<WebElement> listings;

    @FindBy(xpath = "//*[@class='font20 fw600 colorFFF mt-15']")
    private WebElement phonePopup;

    @FindBy(xpath = "//*[@class='jsx-dcde576cdf171c2a jd_modal_close jdicon']")
    private WebElement closePopup;

    @FindBy(xpath = "//div[@class='jsx-d347624528bd8c18 sidemenu inside_content_jd']")
    private WebElement filterBox;

    @FindBy(xpath = "//li[contains(text(),'Ratings')]")
    private List<WebElement> voteList;

    @FindBy(xpath = "//*[@class='resultbox_totalrate mr-6 font17 fw600 colorFFF ']")
    private List<WebElement> ratingList;

    public void clickFilterBtn(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        allFiltersButton.click();
    }
    public boolean isFilterBoxDisplayed(WebDriver driver){
        return filterBox.isDisplayed();
    }

    public void filterTopRated(WebDriver driver) {
        ActionsUtil.moveToElement(driver, topRatedFilter);
        Waits.getClickableElements(driver,topRatedFilter,10).click();
    }

    public boolean isTopRatedSelected(WebDriver driver){
        return topRatedFilter.getAttribute("class").contains("jsx-d347624528bd8c18  resfilter_inner_item font14 gray_whitefill_animate active  itmdisp");
    }

    public void filterRating(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ActionsUtil.scrollOperation(driver, 0, 150);
        ActionsUtil.moveToElement(driver, ratingFilter);
        js.executeScript("arguments[0].click()", ratingFilter);
    }

    public boolean isRatingSelected(WebDriver driver){
        return ratingFilter.getAttribute("class").contains("jsx-d347624528bd8c18  resfilter_inner_item font14 gray_whitefill_animate active  itmdisp");
    }

    public boolean votesGreaterThan(WebDriver driver, int minVote){
        for(int i=0;i<Math.min(listings.size(), 5);i++) {
            // Extract votes and check if > 20
            String votesText = voteList.get(i).getText().split(" ")[0].replace(",", "");
            int votes = Integer.parseInt(votesText);
            if (votes <= minVote) {
                return false;
            }
        }
        return true;
    }

    public boolean ratingGreaterThan(WebDriver driver,double rating){
        for(int i=0;i<Math.min(listings.size(), 5);i++) {
            // Extract votes and check if > 20
            String votesText = ratingList.get(i).getText().split(" ")[0];
            double votes = Double.parseDouble(votesText);
            if (votes <= rating) {
                return false;
            }
        }
        return true;
    }
    public void applyFilters(WebDriver driver){
        applyFiltersButton.click();
    }

    public boolean isAllFilterEnabled(WebDriver driver){
        return allFiltersButton.isEnabled();
    }

    public int listingSize(WebDriver Driver){
        return listings.size();
    }



    public Map<String,String> getMatchingServices(WebDriver driver) {
        Map<String, String> carWashContacts = new LinkedHashMap<>();
        Pattern numberPattern = Pattern.compile("\\d+");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < Math.min(listings.size(), 5); i++) {
            WebElement service = listings.get(i);
            String name = "";
            String phone = "";

            try {
                js.executeScript("arguments[0].scrollIntoView()", service);
                name = service.findElement(By.xpath(".//h3[contains(@class,'resultbox_title_anchor')]")).getText();

                try {
                    WebElement callButton = service.findElement(By.xpath(".//*[contains(@class,'callbutton')]"));
                    callButton.click();
                    Thread.sleep(3000);
                    phone = Waits.getElementByVisibility(driver, phonePopup, 20).getText();
                    closePopup.click();
                } catch (Exception e) {
                    phone = service.findElement(By.xpath(".//*[contains(@class,'callcontent')]")).getText();
                }
            } catch (Exception e) {
                // fallback logic if needed
            }

            carWashContacts.put(name, phone);
        }

        carWashContacts.forEach((k, v) -> System.out.println(k + " : " + v));
        return carWashContacts;
    }
}
