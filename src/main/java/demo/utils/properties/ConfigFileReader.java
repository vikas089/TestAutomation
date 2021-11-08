package demo.utils.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir") + "/src/main/java/demo/properties/";

    public static Properties getInstance (String path) {
        Properties prop = new Properties();
        synchronized (lock) {
            try {
                prop.load(new FileInputStream(propertyFilePath +path));
            } catch (IOException e) {
                System.out.println("Configuration properties file cannot be found");
            }
        }
        return prop;
    }
}