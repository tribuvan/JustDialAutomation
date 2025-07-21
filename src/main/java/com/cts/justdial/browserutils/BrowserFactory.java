package com.cts.justdial.browserutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static WebDriver driver;
    private static WebDriver runLocal(String bname,String url){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                + "(KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        //Set geolocation permission to "allow"
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2); // 1 = Allow, 2 = Block
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");

        EdgeOptions eoptions = new EdgeOptions();
        eoptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        eoptions.setExperimentalOption("useAutomationExtension", false);
        eoptions.addArguments("--disable-blink-features=AutomationControlled");
        eoptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                + "(KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        // Set geolocation permission to "allow"
        Map<String, Object> eprefs = new HashMap<>();
        eprefs.put("profile.default_content_setting_values.geolocation", 1); // 1 = Allow, 2 = Block
        eprefs.put("profile.default_content_setting_values.notifications", 2);
        eoptions.setExperimentalOption("prefs", eprefs);
        switch (bname.intern().toLowerCase()){
            case "chrome":
                driver = new ChromeDriver(options);
                driver.manage().deleteAllCookies();
                break;
            case "edge":
                driver = new EdgeDriver(eoptions);
                driver.manage().deleteAllCookies();
                break;
            default:
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        return driver;
    }
    private static WebDriver runRemote(String browsername,String url,String ip) throws Exception{
        DesiredCapabilities dc = new DesiredCapabilities();

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                + "(KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        //Set geolocation permission to "allow"
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2); // 1 = Allow, 2 = Block
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        switch(browsername.toLowerCase().intern()){
            case "chrome":
                dc.setBrowserName("chrome");
                driver = new RemoteWebDriver(new URL(ip+"/wd/hub"),dc);
                break;
            case "edge":
                dc.setBrowserName("edge");
                driver = new RemoteWebDriver(new URL(ip+"/wd/hub"),dc);
                break;
            default:
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        return driver;
    }

    public static WebDriver getBrowser(String bname, String url, String wr,String hip) throws Exception{
        switch(wr.toLowerCase().intern()){
            case "cloud":
                driver = runRemote(bname,url,hip);
                break;
            default:
                driver = runLocal(bname,url);
        }
        return driver;
    }
}
