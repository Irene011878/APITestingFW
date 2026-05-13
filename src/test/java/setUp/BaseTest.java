package setUp;

import org.testng.annotations.AfterSuite;
import utilities.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    public static Properties config = new Properties();

    public static String getSecret(String envKey, String propertyKey){

        String envValue = System.getenv(envKey);

        if(envValue != null && !envValue.isEmpty()){

            return envValue;
        }

        return config.getProperty(propertyKey);
    }

    public static ExcelReader excel =
            new ExcelReader("./src/test/resources/excel/testData.xlsx");

    static {

        try {

            File localFile = new File(
                    System.getProperty("user.dir")
                            + "/src/test/resources/properties/config-local.properties");

            FileInputStream fis;

            if (localFile.exists()) {

                fis = new FileInputStream(localFile);

                System.out.println("Loading LOCAL config file");

            } else {

                fis = new FileInputStream(
                        System.getProperty("user.dir")
                                + "/src/test/resources/properties/config.properties");

                System.out.println("Loading DEFAULT config file");
            }

            config.load(fis);

            baseURI = config.getProperty("baseURI");
            basePath = config.getProperty("basePath");

            System.out.println("BaseURI -> " + baseURI);
            System.out.println("BasePath -> " + basePath);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown() {

    }
}

