package demo.framework.pages;

import com.aventstack.extentreports.Status;
import demo.utils.common.AdvancePage;
import demo.utils.reports.ExtentTestManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

import static demo.framework.context.DemoContext.getcnSerchReult;
import static demo.framework.context.DemoContext.*;
import static demo.framework.helper.WaitHelper.*;

public class HomePage extends AdvancePage {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver=driver;
    }

    // For page object model defining English Text xpath
    By englishLink = By.xpath("//*[@title='English']");

    // For page object model defining searchBox objects xpath
    By searchBox = By.xpath("//*[@id='content']//input[@name='q'][@type='text']");

    // For page object model defining searchResult objects xpath
    By searchReslutChld = By.xpath("//*[@id='content']//div[@class='TPN-autocomplete__result']/ul/li");

    // Open URL
    public void openURL(){driver.get(loadProperties("urlABDPolice"));}

    // Click on English Link
    public void clickEnglishLink() {
        driver.findElement(englishLink).click();
    }

    //Enter Search String in Search box
    public void enterInSearchBox() {
        waitUntilVisible(driver,driver.findElement(searchBox));
        driver.findElement(searchBox).sendKeys(loadProperties("StringToSerach"));
    }

    // Get Search result list and store count and items in list
    public void getSerchReult(){
        List<String> ExpectedListValue = new ArrayList<String>();
          pause(3000);
          waitUntilVisible(driver,driver.findElement(searchReslutChld));
       List<WebElement> searchList =  driver.findElements(searchReslutChld);
        setListItemCount(searchList.size());
        for(int i=0 ;i<searchList.size(); i++){
           try{
               ExpectedListValue.add(searchList.get(i).getText());
            }catch(Exception e){
                e.printStackTrace();
                System.out.println(e.toString());
            }
        }
        setSerchReult(ExpectedListValue);
    }

    // Verify Search result list and stored in list
      public void verifyResults(){
        pause(3000);
        waitUntilVisible(driver,driver.findElement(searchReslutChld));
        List<WebElement> searchListNew =  driver.findElements(searchReslutChld);
        List<String> ActualValue = new ArrayList<String>();
        for( int i=0 ;i<searchListNew.size(); i++){
            try{
                ActualValue.add(searchListNew.get(i).getText());
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        if(getListItemCount()==searchListNew.size()){
            Assert.assertTrue(true);
            ExtentTestManager.getTest().log(Status.PASS, "List Items is : " + getListItemCount() );
        }else {
            ExtentTestManager.getTest().log(Status.FAIL, "List Items is not  : " + getListItemCount() );
            Assert.assertTrue(false);
        }
        if(ActualValue.containsAll(getcnSerchReult())){
            Assert.assertTrue(true);
            ExtentTestManager.getTest().log(Status.PASS, "Test Passed for  : " + getcnSerchReult().toString() );
        }else{
            ExtentTestManager.getTest().log(Status.FAIL, "Test failed for  : " + getcnSerchReult().toString() );
            Assert.assertTrue(false);
         }
    }




}
