package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/296673
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
{
    "meta": null,
    "data": {
        "id": 296673,
        "name": "Jahnu Khatri",
        "email": "khatri_jahnu@pfeffer.name",
        "gender": "female",
        "status": "inactive"
    }
}
    */

    @Test
    public void get01Pojo(){

        //1.Step: Set the URL
        spec.pathParams("first","users","second",296673);

        //2.Step: Set the Expected Data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(296673,"Jahnu Khatri","khatri_jahnu@pfeffer.name","female","inactive");
        GoRestResponseBodyPojo goRestResponseBodyPojo = new GoRestResponseBodyPojo(null,goRestDataPojo);

        //3.Step: Send the GET Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4.Step: Do Assertions
        GoRestResponseBodyPojo actualBodyPojo = response.as(GoRestResponseBodyPojo.class);

        assertEquals(200, response.getStatusCode());

        assertEquals(goRestResponseBodyPojo.getMeta(),actualBodyPojo.getMeta());
        assertEquals(goRestDataPojo.getId(),actualBodyPojo.getData().getId());
        assertEquals(goRestDataPojo.getName(),actualBodyPojo.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualBodyPojo.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualBodyPojo.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualBodyPojo.getData().getStatus());

    }
}
