package demo.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


public class CreateDriver {

    public static String path = System.getProperty("user.dir");
    private static WebDriver driver = null;

    public static WebDriver getDriverInstance() {
        System.setProperty("webdriver.chrome.driver", path + "/src/main/resources/chromedriver/chromedriver.exe");
        ChromeOptions cOptions = new ChromeOptions();
        cOptions.setBinary("C:/Program Files/Google/Chrome/Application/chrome.exe");
        cOptions.addArguments("disable-extensions");
        cOptions.addArguments("disable-infobars");
        cOptions.addArguments("start-maximized");
        cOptions.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(cOptions);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;
    }


}
