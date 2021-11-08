package demo.utils.common;

import demo.tests.demoTest.BaseTest;
import demo.utils.properties.ConfigFileReader;
import io.restassured.http.ContentType;
import org.apache.commons.io.output.WriterOutputStream;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.*;



public class AdvancePage {

    /**
     * Set the path of property file whether to pick data from SIT or STG
     * @param fileName : file name as per that particular page
     * @return : Properties object
     */
    private Properties setPropertyPath(String fileName) {
        String env = BaseTest.env;
        Properties prop = null;
        String location = getClass().getCanonicalName();

        if (env.equalsIgnoreCase("uat") && location.contains("demo")) {
            prop = ConfigFileReader.getInstance("demo/uat/" + fileName + ".properties");
        }
        return prop;
    }

    /**
     * This method is to read the data from Page properties file
     * @param name : name of element whose value need to be fetch
     * @return : String value from page properties
     */
    public String loadProperties(String name) {
        String value = setPropertyPath(getClass().getSimpleName()).getProperty(name);
        return value;
    }

    /**
     * This method wait for given time
     * @param ms : wait in milliseconds
     */

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    /**
     * This method refresh the Browser
     * @param driver : deriver object required as param
     */
    public static void RefreshBrowser(WebDriver driver){
        driver.navigate().refresh();
    }


    /**
     * This method Browser Zoom In and Zoom Out
     * @param PrmaINOut : IN / OUt for browser
     */
    public void driverZoom(String PrmaINOut){
        Robot robot = null;
        try {
            robot = new Robot();
            if (PrmaINOut.equalsIgnoreCase("IN")){
                for (int i = 0; i < 7; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ADD);
                    robot.keyRelease(KeyEvent.VK_ADD);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
            } else if (PrmaINOut.equalsIgnoreCase("OUT")) {
                for (int i = 0; i < 7; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }



}

