package apis;

import io.restassured.response.Response;
import setUp.BaseTest;

import static io.restassured.RestAssured.given;

public class CreateCustomerAPI extends BaseTest {

    public static Response sendPostRequestToCreateCustomerAPIWithValidAuthKey(
            String name,
            String email,
            String description) {

        Response response = given()
                .auth()
                .basic(getSecret("STRIPE_SECRET_KEY", "validSecretKey"), "")
                .formParam("name", name)
                .formParam("email", email)
                .formParam("description", description)
                .post(config.getProperty("customerAPIEndPoint"));

        return response;
    }

    public static Response sendPostRequestToCreateCustomerAPIWithInValidAuthKey(
            String name,
            String email,
            String description) {

        Response response = given()
                .auth()
                .basic(getSecret("STRIPE_INVALID_SECRET_KEY", "invalidSecretKey"), "")
                .formParam("name", name)
                .formParam("email", email)
                .formParam("description", description)
                .post(config.getProperty("customerAPIEndPoint"));

        return response;
    }
}
