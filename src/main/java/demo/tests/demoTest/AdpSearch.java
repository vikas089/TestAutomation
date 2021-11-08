package demo.tests.demoTest;

import demo.framework.driver.CreateDriver;
import demo.framework.pages.AbuDhabiGovtEntitiesPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdpSearch extends BaseTest {
   WebDriver dr = setDriver();

    // Test case Execution
    @Test
    public void serchADP(){
        AbuDhabiGovtEntitiesPage adgePage= new AbuDhabiGovtEntitiesPage(dr);
        adgePage.clickEnglishLink();
        adgePage.clickBurgermenu();
        adgePage.clickAbuDhabiGovernmentEntitiesLink();
        adgePage.enterInSearchBox();
        adgePage.verifyADPresults();
        adgePage.clickAbuDhabiPoliceListItems();
        adgePage.showTotalresults();
    }
    // This method is to clear memory and closing driver
    @AfterTest
    public void ClearMemory(){
        dr.close();
        System.gc();
    }


}
