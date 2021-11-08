package demo.framework.helper;


import demo.framework.driver.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    public static final int WEBELEMENT_DEFAULT_TIMEOUT = 20;

    public static void waitFor(Integer timeout) throws InterruptedException {
        //TODO
        try{
            Thread.sleep(timeout);
        }catch( Exception e ){
            e.printStackTrace();
        }

    }

    public static void waitUntilVisible(WebDriver driver,WebElement element) {
        //WebDriver driver = CreateDriver.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilClickable(WebElement element) {
        WebDriver driver = CreateDriver.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementIsAvailable(By element) {
        try{
            WebDriver driver = CreateDriver.getDriverInstance();
            WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch(Exception e){
            System.out.println("Element is not available or not clickable");
        }
    }

    public static void waitUntilStalenessOf(WebElement element) {
        WebDriver driver = CreateDriver.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public static void waitUntilElementDisappears(WebElement element) throws Exception {
        boolean isVisible = true;
        int counter = 0;
        while (isVisible) {
            waitFor(100);
            counter = counter + 1;
            isVisible = element.isDisplayed();
            if (counter == WEBELEMENT_DEFAULT_TIMEOUT) {
                isVisible = false;
            }
        }
    }

    public static boolean waitForJStoLoad() {

        WebDriver driver = CreateDriver.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}
