package fatiny.myTest.other.mavencon.remoteContect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class propertyUtil {

    private static Properties prop = new Properties();

    private static void load(String fileName) {
        try {
            prop.load(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String fileName, String key) {
        load(fileName);
        return prop.getProperty(key);
    }

    public static void setProper(String fileName, String key, String value) {
        try {
            load(fileName);
            prop.setProperty(key, value);
            FileOutputStream fos = new FileOutputStream(fileName);
            prop.store(fos, null);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(propertyUtil.getProperty("test.properties", "key"));
        propertyUtil.setProper("test.properties", "key", "xxxx");
        System.out.println(propertyUtil.getProperty("test.properties", "key"));
    }
}