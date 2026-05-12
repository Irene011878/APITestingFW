package setUp;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    public static Properties config= new Properties();
    private FileInputStream fis;

    public static ExcelReader excel =
            new ExcelReader(".\\src\\test\\resources\\excel\\testData.xlsx");
    //Pones EL PATH A DONDE ESTA TU FILE DE EXCEL EN LA CARPETA DE RESOURCES CUANDO LA COPIES


    @BeforeSuite
    public void setUp(){

       /*try {
           fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");

       }catch (FileNotFoundException e){
           e.printStackTrace();
       }

        try {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try{
            File file = new File(".\\src\\test\\resources\\properties\\config-local.properties");

            if(file.exists()){
                fis = new FileInputStream(file);
            }else {
                fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
            }
            config.load(fis);
        }catch (IOException e){
            e.printStackTrace();
        }

        baseURI=config.getProperty("baseURI");
        basePath=config.getProperty("basePath");

    }

    @AfterSuite
    public void tearDown(){

    }
}
