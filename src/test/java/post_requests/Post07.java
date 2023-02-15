package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDataPojo;
import pojos.BookingDatesPojo;
import pojos.BookingResponseBodyPojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Post07 extends HerOkuAppBaseUrl {
        /*
       URL: https://restful-booker.herokuapp.com/booking
       HTTP Request Method: POST Request
       Request body: {
                            "bookingid": 11770,
                            "booking": {
                                "firstname": "Emre",
                                "lastname": "Dursun",
                                "totalprice": 114,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2023-02-15",
                                    "checkout": "2024-02-15"
                                },
                                "additionalneeds": "Lunch"
                            }
                        }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    /*
            Given
            https://restful-booker.herokuapp.com/booking
                    {
                        "firstname": "Emre",
                        "lastname": "Dursun",
                        "totalprice": 114,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2023-02-15",
                            "checkout": "2024-02-15"
                        },
                        "additionalneeds": "Lunch"
                    }
         When
            User send the POST Request
         Then
            Status code is 200
         And
            Response body should be like the following
                {
                    "bookingid": 8334,
                    "booking": {
                        "firstname": "Emre",
                        "lastname": "Dursun",
                        "totalprice": 114,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2023-02-15",
                            "checkout": "2024-02-15"
                        },
                        "additionalneeds": "Lunch"
                    }
                }
    */

    @Test
    public void post01() {
        //1.Step: Set the URL
        spec.pathParam("first", "booking");

        //2.Step: Set the Expected Data
        BookingDatesPojo expectedBookingDates = new BookingDatesPojo("2023-02-15", "2024-02-15");
        BookingDataPojo expectedBookingData = new BookingDataPojo("Emre", "Dursun", 114, true, expectedBookingDates, "Lunch");

        //3.Step:Send POST Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedBookingData).when().post("/{first}");
        BookingResponseBodyPojo actualBookingData = JsonUtil.convertJsonToJavaObject(response.asString(), BookingResponseBodyPojo.class);

        //4.Step: Do Assertion
        assertEquals(200, response.statusCode());
        assertEquals(expectedBookingDates.getCheckin(), actualBookingData.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedBookingDates.getCheckout(), actualBookingData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedBookingData.getAdditionalneeds(), actualBookingData.getBooking().getAdditionalneeds());
        assertEquals(expectedBookingData.getDepositpaid(), actualBookingData.getBooking().getDepositpaid());
        assertEquals(expectedBookingData.getTotalprice(), actualBookingData.getBooking().getTotalprice());
        assertEquals(expectedBookingData.getLastname(), actualBookingData.getBooking().getLastname());
        assertEquals(expectedBookingData.getFirstname(), actualBookingData.getBooking().getFirstname());
    }

    @Test
    public void post02() {
        //1.Step: Set the URL
        spec.pathParam("first", "booking");

        //2.Step: Set the Expected Data
        HerOkuAppTestData herOkuApp = new HerOkuAppTestData();
        Map<String, String> bookingDatesMap = herOkuApp.bookingDateSetUp("2023-02-15", "2024-02-15");
        Map<String, Object> expectedDataMap = herOkuApp.expectedDataSetUp("Emre", "Dursun", 114, true, bookingDatesMap, "Lunch");

        //3.Step: Send POST Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        //4.Step: Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("firstname"), ((Map) actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map) actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map) actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map) actualDataMap.get("booking")).get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));
        assertEquals(expectedDataMap.get("additionalneeds"), ((Map) actualDataMap.get("booking")).get("additionalneeds"));
    }

    @Test
    public void post03() {

        //1.Step: Set the Url
        spec.pathParam("first", "booking");

        //2.Step: Set the Expected Data
        HerOkuAppTestData herOkuApp = new HerOkuAppTestData();
        Map<String, String> bookingDatesMap = herOkuApp.bookingDateSetUp("2023-02-15", "2024-02-15");
        Map<String, Object> expectedDataMap = herOkuApp.expectedDataSetUp("Emre", "Dursun", 114, true, bookingDatesMap, "Lunch");

        //3.Step: Send the POST Request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        //4.Step: Do Assertions
        //1.Way:
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("firstname"), ((Map) actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map) actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map) actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("lastname"), ((Map) actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map) actualDataMap.get("booking")).get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));
        assertEquals(expectedDataMap.get("additionalneeds"), ((Map) actualDataMap.get("booking")).get("additionalneeds"));

        //2.Way:
        JsonPath json = response.jsonPath();

        assertTrue(json.getString("booking.firstname").equals(expectedDataMap.get("firstname")));
        assertTrue(json.getString("booking.lastname").equals(expectedDataMap.get("lastname")));
        assertTrue(json.getInt("booking.totalprice") == (Integer) expectedDataMap.get("totalprice"));
        assertEquals(json.getBoolean("booking.depositpaid"), expectedDataMap.get("depositpaid"));
        assertTrue(json.getString("booking.bookingdates.checkin").equals(bookingDatesMap.get("checkin")));
        assertTrue(json.getString("booking.bookingdates.checkout").equals(bookingDatesMap.get("checkout")));
        assertTrue(json.getString("booking.additionalneeds").equals(expectedDataMap.get("additionalneeds")));
    }
}
