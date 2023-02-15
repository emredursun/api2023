package post_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.*;
import utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PetStorePost extends PetStoreBaseUrl {
     /*
            Given
            https://petstore.swagger.io/v2/pet
                        {
                          "category": {
                            "id": 3,
                            "name": "Köpek Cinsleri"
                          },
                          "name": "Yorkshire Terrier",
                          "photoUrls": [
                            "https://www.petihtiyac.com/yorkshire-terrier-931-blog.jpg"
                          ],
                          "tags": [
                            {
                              "id": 5,
                              "name": "Küçük köpek ırkı"
                            }
                          ],
                          "status": "available"
                        }
         When
            User send the POST Request
         Then
            Status code is 200
         And
            Response body should be like the following
                {
                  "id": 9223372036854762000,
                  "category": {
                    "id": 3,
                    "name": "Köpek Cinsleri"
                  },
                  "name": "Yorkshire Terrier",
                  "photoUrls": [
                    "https://www.petihtiyac.com/yorkshire-terrier-931-blog.jpg"
                  ],
                  "tags": [
                    {
                      "id": 5,
                      "name": "Küçük köpek ırkı"
                    }
                  ],
                  "status": "available"
                }
    */

    @Test
    public void post01() {
        //1.Step: Set the URL
        spec.pathParam("first","pet");

        //2.Step: Set the Expected Data
        PetStoreCategoryPojo petStoreCategory = new PetStoreCategoryPojo(3, "Köpek Cinsleri");
        //PetStoreTagPojo petStoreTag = new PetStoreTagPojo(5, "Küçük köpek ırkı");
        PetStoreResponseBodyPojo petStoreResponseBody = new PetStoreResponseBodyPojo(petStoreCategory, "Yorkshire Terrier", "available");

        //3.Step:Send POST Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(petStoreResponseBody).when().post("/{first}");
        PetStoreResponseBodyPojo actualPetStoreResponseBody = JsonUtil.convertJsonToJavaObject(response.asString(), PetStoreResponseBodyPojo.class);
        response.prettyPrint();

        //4.Step: Do Assertion
        assertEquals(200, response.statusCode());
        assertEquals(petStoreCategory.getId(), actualPetStoreResponseBody.getCategory().getId());
        assertEquals(petStoreCategory.getName(), actualPetStoreResponseBody.getCategory().getName());
        assertEquals(petStoreResponseBody.getName(), actualPetStoreResponseBody.getName());
        assertEquals(petStoreResponseBody.getStatus(), actualPetStoreResponseBody.getStatus());
    }
}
