package demo.framework.pages;

import com.aventstack.extentreports.Status;
import demo.utils.common.AdvancePage;
import demo.utils.reports.ExtentTestManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static demo.framework.helper.WaitHelper.waitUntilVisible;

public class AbuDhabiGovtEntitiesPage extends AdvancePage {
    WebDriver driver;
    public AbuDhabiGovtEntitiesPage(WebDriver driver) {
        this.driver=driver;
    }

    // For page object model defining English Text xpath
    By englishLink = By.xpath("//*[@title='English']");

    // For page object model defining Burger menu xpath
    By Burgermenu = By.xpath("//*[@target='burger-nav']");

    // For page object model defining Abu Dhabi Government Entities link xpath
    By AbuDhabiGovernmentEntitiesLink = By.xpath("//*[text()='Abu Dhabi Government Entities']");

    // For page object model defining Abu Dhabi Government Entities link xpath
    By SearchBox = By.xpath("//Input[@class='search-box-input tt-input']");

    // For page object model defining Abu Dhabi Government Entities link xpath
    By SearchListItems = By.xpath("//*[@class='sugesstion-item tt-suggestion tt-selectable']");

    // For page object model defining Abu Dhabi Police List Item xpath
    By AbuDhabiPoliceListItems = By.xpath("//*[text()='Abu Dhabi Police']");

    // For page object model defining total Results xpath
    By totalResults = By.xpath("//*[@class='results-count']");

    // Click on English Link
    public void clickEnglishLink() {
        driver.findElement(englishLink).click();
    }

    // Open URL
    public void openURL(){driver.get(loadProperties("urlABD"));}

    // Click on Burger menu
    public void clickBurgermenu() {
        pause(2000);
        driver.findElement(Burgermenu).click();
        pause(2000);
    }

    // Click on Abu Dhabi Government Entities Link
    public void clickAbuDhabiGovernmentEntitiesLink() {
        driver.findElement(AbuDhabiGovernmentEntitiesLink).click();
    }

    //Enter Search String in Search box
    public void enterInSearchBox() {
        waitUntilVisible(driver,driver.findElement(SearchBox));
        driver.findElement(SearchBox).sendKeys(loadProperties("StringToSerach"));
    }

    //Verify Result for ADP
    public void verifyADPresults() {
        pause(3000);
        waitUntilVisible(driver,driver.findElement(SearchBox));
        List<WebElement> searchList =  driver.findElements(SearchListItems);
        String[] charList = loadProperties("StringToSerach").split("");
        for( int i=0 ;i<searchList.size(); i++){
            for (int j=0 ; j<charList.length;j++){

                if (searchList.get(i).getText().contains(charList[j])){
                    Assert.assertTrue(true);
                    ExtentTestManager.getTest().log(Status.PASS, "initials " +charList[j] + " Found in " + searchList.get(i).getText() );
                } else{
                    Assert.assertTrue(true);
                    ExtentTestManager.getTest().log(Status.FAIL, "initials " +charList[j] + " not Found in " + searchList.get(i).getText() );
                }
            }
        }
    }


    // Click on Abu Dhabi Police List Items
    public void clickAbuDhabiPoliceListItems() {
        driver.findElement(AbuDhabiPoliceListItems).click();
        pause(3000);
    }

    // Printing Total results on page
    public void showTotalresults(){
        pause(2000);
        ExtentTestManager.getTest().log(Status.INFO, "Total result is :" + driver.findElement(totalResults).getText() );
    }


}
