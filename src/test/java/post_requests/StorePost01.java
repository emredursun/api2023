package post_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.StoreResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StorePost01 extends PetStoreBaseUrl {
    /*
            Given
            https://petstore.swagger.io/v2/store/order
            {
                  "petId": 0,
                  "quantity": 0,
                  "shipDate": "2023-02-16T10:36:51.958Z",
                  "status": "placed",
                  "complete": true
             }
         When
            User send the POST Request
         Then
            Status code is 200
         And
            Response body should be like the following
            {
              "id": 9223372036854776000,
              "petId": 0,
              "quantity": 0,
              "shipDate": "2023-02-16T10:36:51.958+0000",
              "status": "placed",
              "complete": true
            }
    */

    @Test
    public void post01() {
        //1.Step: Set the URL
        spec.pathParams("first","store","second","order");

        //2.Step: Set the Expected Data
        StoreResponseBodyPojo storeResponseBody = new StoreResponseBodyPojo(2, 5, "2022-02-16T00:00:00.000+0000", "placed", true);

        //3.Step:Send POST Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(storeResponseBody).when().post("/{first}/{second}");
        response.prettyPrint();
        StoreResponseBodyPojo actualStoreResponseBody = JsonUtil.convertJsonToJavaObject(response.asString(),StoreResponseBodyPojo.class);

        //4.Step: Do Assertion
        assertEquals(200, response.statusCode());
        assertEquals(storeResponseBody.getPetId(),actualStoreResponseBody.getPetId());
        assertEquals(storeResponseBody.getQuantity(),actualStoreResponseBody.getQuantity());
        assertEquals(storeResponseBody.getShipDate(),actualStoreResponseBody.getShipDate());
        assertEquals(storeResponseBody.getStatus(),actualStoreResponseBody.getStatus());
        assertEquals(storeResponseBody.getComplete(),actualStoreResponseBody.getComplete());

        //Soft Assertion
        //1)Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();
        //2)Do Assertions
        softAssert.assertEquals(actualStoreResponseBody.getPetId(), storeResponseBody.getPetId(), "petId did not match");
        softAssert.assertEquals(actualStoreResponseBody.getQuantity(), storeResponseBody.getQuantity(), "quantity did not match");
        softAssert.assertEquals(actualStoreResponseBody.getShipDate(), storeResponseBody.getShipDate(), "shipDate did not match");
        softAssert.assertEquals(actualStoreResponseBody.getStatus(), storeResponseBody.getStatus(), "status did not match");
        softAssert.assertEquals(actualStoreResponseBody.getComplete(), storeResponseBody.getComplete(), "complete did not match");
        //3)Use assertAll()
        softAssert.assertAll();
    }
}
