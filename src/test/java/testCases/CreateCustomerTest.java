package testCases;
import apis.CreateCustomerAPI;
import com.aventstack.extentreports.ExtentReports;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import listeners.ExtentListeners;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import setUp.BaseTest;
import utilities.DataUtil;

import java.util.Hashtable;

import static io.restassured.RestAssured.*;


public class CreateCustomerTest extends BaseTest {

    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
    public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {


        Response response = CreateCustomerAPI
                .sendPostRequestToCreateCustomerAPIWithValidAuthKey(
                        data.get("name"),
                        data.get("email"),
                        data.get("description"));

        ExtentListeners.testReport.get().info(data.toString());

        response.prettyPrint();

        System.out.println(response.statusCode());

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
    public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data) {


        Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuthKey(
                data.get("name"),
                data.get("email"),
                data.get("description"));

        ExtentListeners.testReport.get().info(data.toString());

        response.prettyPrint();

        System.out.println(response.statusCode());

        Assert.assertEquals(response.statusCode(), 401);
    }


}
