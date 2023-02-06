package get_requests;

import test_data.HerOkuAppTestData;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Get09 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/1
        When
	 		I send GET Request to the url
	 	Then
	 		Response body should be like that;
{
    "firstname": "Jim",
    "lastname": "Jackson",
    "totalprice": 725,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2021-07-08",
        "checkout": "2021-09-10"
    }
}
     */
    @Test
    public void Get01(){

        //1.Step: Set the URL
        spec.pathParams("first","booking","second",1);

        //2.Step: Set the Expected Data
        HerOkuAppTestData dataKey = new HerOkuAppTestData();
        Map<String, String> bookingdatesMap = dataKey.bookingDateSetUp("2015-05-15", "2020-02-20");
//        bookingdatesMap.put("checkin", "2020-10-28");
//        bookingdatesMap.put("checkout", "2021-04-30");

        Map<String, Object> expectedData = dataKey.expectedDataSetUp("Sally","Smith", 404, false, bookingdatesMap);
//        expectedData.put("firstname","Jim");
//        expectedData.put("lastname","Jackson");
//        expectedData.put("totalprice", 901);
//        expectedData.put("depositpaid", false);
//        expectedData.put("bookingdates", bookingdatesMap);

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //4.Step: Do Assertions
        assertEquals(expectedData.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualDataMap.get("depositpaid"));


        assertEquals(bookingdatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));

    }
}
