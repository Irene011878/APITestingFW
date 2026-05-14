package testCases;
import apis.CreateCustomerAPI;
import apis.DeleteCustomerAPI;
import io.restassured.response.Response;
import listeners.ExtentListeners;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import setUp.BaseTest;
import tools.jackson.databind.util.JSONPObject;
import utilities.DataUtil;
import utilities.TestUtil;

import java.util.Hashtable;


public class DeleteCustomerTest extends BaseTest {

    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
    public void deleteCustomer(Hashtable<String, String> data) {


        Response response = DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidId(data);

        response.prettyPrint();

        ExtentListeners.testReport.get().info(data.toString());

        System.out.println(response.statusCode());

        System.out.println("Presence checked from Object key: " + TestUtil.jsonHasKey(response.asString(), "object"));
        System.out.println("Presence checked from Deleted key: " + TestUtil.jsonHasKey(response.asString(), "deleted"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"), "ID key is not present in the JSON response" );
        String actual_id = TestUtil.getJsonKeyValue(response.asString(), "id");
        System.out.println(actual_id);
        Assert.assertEquals(actual_id, data.get("id"), "ID is not matching");

        System.out.println("Object key value is :" + TestUtil.getJsonKeyValue(response.asString(), "object"));
        System.out.println("Deleted key value is :" + TestUtil.getJsonKeyValue(response.asString(), "deleted"));

        Assert.assertEquals(response.statusCode(), 200);
    }


}
