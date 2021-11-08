package demo.tests;


import com.aventstack.extentreports.ExtentTest;
import demo.framework.driver.CreateDriver;
import demo.utils.properties.ConfigFileReader;
import demo.utils.reports.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class SetUp {

    public static String url;
    public static String env;
    public static ExtentTest test;
    String path = "config.properties";

    @Parameters({"env"})
    @BeforeSuite
    public void setUpProxy(String env) {

        this.env = env;
        if(env.equalsIgnoreCase("UAT")){
            url = ConfigFileReader.getInstance(path).getProperty("demo.uat");
        }else{
            Assert.assertTrue(false , "Provide valid environment");
        }
    }

    public WebDriver setDriver(){
        CreateDriver crdriver = new CreateDriver();
        WebDriver driver = crdriver.getDriverInstance();
        driver.get(ConfigFileReader.getInstance(path).getProperty("url"));
        return driver;
    }

    @BeforeMethod
    public void BeforeMethod(Method method, Object[] testData, ITestContext ctx) {
        ExtentTestManager.startTest(ctx.getCurrentXmlTest().getName());
    }

}