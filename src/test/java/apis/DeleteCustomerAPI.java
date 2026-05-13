package apis;

import io.restassured.response.Response;
import setUp.BaseTest;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

/*public class DeleteCustomerAPI extends BaseTest {

    public static Response sendDeleteRequestToDeleteCustomerAPIWithValidId(Hashtable<String, String> data) {

        Response response = given().auth().basic(config.getProperty("validSecretKey"), "")
                .delete(config.getProperty("customerAPIEndPoint")+"/"+data.get("id"));

        return response;

    }
}*/

public class DeleteCustomerAPI extends BaseTest {

    public static Response sendDeleteRequestToDeleteCustomerAPIWithValidId(
            Hashtable<String, String> data) {

        Response response = given()
                .auth()
                .basic(getSecret("STRIPE_SECRET_KEY", "validSecretKey"), "")
                .delete(config.getProperty("customerAPIEndPoint") + "/" + data.get("id"));

        return response;
    }
}
