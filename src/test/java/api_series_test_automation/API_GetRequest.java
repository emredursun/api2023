package api_series_test_automation;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class API_GetRequest extends JsonPlaceHolderBaseUrl {

    @Test
    public void Get01(){
        /* {
        Status Code: 200
    "userId": 5,
    "id": 44,
    "title": "optio dolor molestias sit",
    "body": "temporibus est consectetur dolore\net libero debitis vel velit laboriosam quia\nipsum quibusdam qui itaque fuga rem aut\nea et iure quam sed maxime ut distinctio quae"
        * */

        //1.Step: Set the Url with endpoint: /posts/44
        specJsonPlaceHolder.pathParams("first","posts","second","44");

        //2.Set the Expected Data
        JSONObject expBody = new JSONObject();
        expBody.put("userId",5);
        expBody.put("id", 44);
        expBody.put("title","optio dolor molestias sit");
        expBody.put("body", "temporibus est consectetur dolore\\net libero debitis vel velit laboriosam quia\\nipsum quibusdam qui itaque fuga rem aut\\nea et iure quam sed maxime ut distinctio quae");

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(specJsonPlaceHolder).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertions
        //1.Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json");

        JsonPath actBody = response.jsonPath();

        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));
        Assert.assertEquals(expBody.get("id"),actBody.get("id"));
        Assert.assertEquals(expBody.get("title"),actBody.get("title"));
        Assert.assertEquals(expBody.get("body"),actBody.get("body"));

        //2.Way:
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),
                        "userId",equalTo(2));

        /*
            Note 1: When you execute assertions, Java stops execution just after the first failure, it means assertions after the failure
                    were not executed. If assertions were not executed, you cannot tell anything about their states. They may pass or they may fail.
                    But the assertions before failure were passed, because assertions before failure were executed.
            Note 2: If you type your code as execution will stop in the first failure, this is called "Hard Assertion"(Assertion)
            Note 3: If you type your code as execution will not stop in any failure, this is called "Soft Assertion"(Verification)
            Note 4: If you use multiple "body()" method it will work like "Hard Assertion", if you use just a single "body()" method with multiple
                    assertions in it, it will work like "Soft Assertion"
        */

    }

}
