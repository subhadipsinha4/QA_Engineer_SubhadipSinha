package com.qa.base;

import com.aventstack.extentreports.ExtentTest;
import com.qa.utils.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import org.testng.ITestResult;

import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
    import java.util.Set;


public class BaseClass {

    public static ExtentTest test;
    public static WebDriver driver;
    public static Properties prop;

    public BaseClass(){
        prop=new Properties();

    }

    public void getPropertie(){
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpBase(Method method) {
        getPropertie();
        test = ExtentReportManager.createTest(method.getName());
        String browser=prop.getProperty("browser");

        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    @AfterMethod
    public void teardown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped");
        }

        ExtentReportManager.flushReport();
        if (driver != null) {
            driver.quit();
        }
    }
}
