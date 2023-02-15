package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDataPojo;
import pojos.BookingDatesPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05Pojo extends HerOkuAppBaseUrl {

    /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */
    @Test
    public void PostPojo01(){

        //1.Step: Set the URL
        spec.pathParam("first", "booking");

        //2.Step: Set the Expected Data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingDataPojo bookingDataPojo = new BookingDataPojo("Ali", "Can", 999, true, bookingDates, "Breakfast with white tea, Dragon juice");

        //3.Step:Send POST Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(bookingDataPojo).when().post("/{first}");

        //4.Step: Do Assertions
        BookingResponseBodyPojo actualPojo = response.as(BookingResponseBodyPojo.class);

        assertEquals(200, response.getStatusCode());

        assertEquals(bookingDataPojo.getFirstname(), actualPojo.getBooking().getFirstname());
        assertEquals(bookingDataPojo.getLastname(), actualPojo.getBooking().getLastname());
        assertEquals(bookingDataPojo.getTotalprice(), actualPojo.getBooking().getTotalprice());
        assertEquals(bookingDataPojo.getDepositpaid(), actualPojo.getBooking().getDepositpaid());

        //1.Way: Can be used
        assertEquals(bookingDataPojo.getBookingdates().getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDataPojo.getBookingdates().getCheckout(), actualPojo.getBooking().getBookingdates().getCheckout());

        //2.Way: Recommended
        assertEquals(bookingDates.getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualPojo.getBooking().getBookingdates().getCheckout());
    }
}
