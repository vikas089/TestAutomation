package demo.tests.demoTest;

import com.aventstack.extentreports.Status;
import demo.framework.driver.CreateDriver;
import demo.framework.pages.HomePage;
import demo.utils.common.AdvancePage;
import demo.utils.reports.ExtentTestManager;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static demo.framework.driver.CreateDriver.getDriverInstance;

public class AbudhabiPoliceRefresh extends BaseTest {
    WebDriver dr = setDriver();

   // Test case Execution
    @Test
    public void abuDhabiPolishRefresh()  {
        HomePage hpage = new HomePage(dr);
        hpage.clickEnglishLink();
        hpage.enterInSearchBox();
        hpage.getSerchReult();
        for(int i=1 ;i < 6  ; i++ ) {
            ExtentTestManager.getTest().log(Status.INFO, "Test for Refresh : " + i);
            hpage.RefreshBrowser(dr);
            hpage.enterInSearchBox();
            hpage.verifyResults();
        }
    }
    // This method is to clear memory and closing driver
    @AfterTest
    public void ClearMemory(){
        dr.close();
        System.gc();
    }



}
