package com.cts.justdial.pageobjects;

import com.cts.justdial.seleniumutils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GymsPage extends BasePage {

    private final By gymNameLocator = By.xpath("//h2");

    public GymsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getSubMenuItems(WebDriver driver) {
        List<WebElement> gymnames = Waits.getAllVisibleElements(driver, gymNameLocator, 10);
        List<String> gyms = new ArrayList<>();
        int n = gymnames.size();

        for (int i = 0; i < Math.min(10, n); i++) {
            try {
                WebElement elem = gymnames.get(i);
                gyms.add(elem.getText());
            } catch (Exception e) {
                gymnames = driver.findElements(gymNameLocator);
                WebElement elem = gymnames.get(i);
                gyms.add(elem.getText());
            }
        }

        System.out.println(Arrays.toString(gyms.toArray(new String[0])));
        return gymnames;
    }
}
