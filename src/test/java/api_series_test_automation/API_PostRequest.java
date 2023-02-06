package api_series_test_automation;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class API_PostRequest extends JsonPlaceHolderBaseUrl {

    @Test
    public void Get01(){
        /*{
    "userId": 15,
    "title": "API",
    "body": "Learning API is great",
    "id": 101
        */

        //1.Step: Set the Url
        String url = "https://jsonplaceholder.typicode.com/posts";
        spec.pathParams("first","posts");

        JSONObject reqBody = new JSONObject();

        reqBody.put("userId", 15);
        reqBody.put("title","API");
        reqBody.put("body", "Learning API is great");

        //2.Set the Expected Data

        JSONObject expBody = new JSONObject();

        expBody.put("userId", 15);
        expBody.put("title","API");
        expBody.put("body", "Learning API is great");

        //3.Step: Send the Request and Get the Response
        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                post(url);

        JsonPath actBody = response.jsonPath();
        response.prettyPrint();

        //4.Step: Do Assertions

        response.
                then().
                contentType(ContentType.JSON).
                statusCode(201);

        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));
        Assert.assertEquals(expBody.get("title"),actBody.get("title"));
        Assert.assertEquals(expBody.get("body"),actBody.get("body"));
    }

}
