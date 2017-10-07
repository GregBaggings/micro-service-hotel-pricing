package featureTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class PriceControllerTests {
    private final static String ENDPOINT = "http://localhost:2224/v1";

    @Test
    public void getAllPrices() {
        Response resp = RestAssured.get(ENDPOINT + "/hotels/prices");
        Assert.assertFalse(resp.asString().isEmpty());
    }


    @Test
    public void getPricesByID() {
        Response resp = RestAssured.get(ENDPOINT + "/hotels/prices/price?id=1");
        Assert.assertFalse(resp.asString().isEmpty());
    }

}
