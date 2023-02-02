package api_series_test_automation;

import TestDataStore.JsonPlaceHolderTestData;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class API_BaseURL_UsageOfTestData extends JsonPlaceHolderBaseUrl {

    @Test
    public void GET_01(){
        /*{
    "userId": 3,
    "id": 22,
    "title": "dolor sint quo a velit explicabo quia nam",
    "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
}       */


        // 1 - URL ve Request Body olusturma
        // BaseURL: http://jsonplaceholder.typicode.com  and path params: /posts/101

        specJsonPlaceHolder.pathParams("first","posts", "second",22);

        // 2 - Expected Data Olustur
        JsonPlaceHolderTestData jsonPlaceHolder = new JsonPlaceHolderTestData();

        JSONObject expBody = jsonPlaceHolder.getExpData();
        int expStatusCode = jsonPlaceHolder.statusCode;

        // 3 - Response kaydet

        Response response = given().spec(specJsonPlaceHolder).when().get("{first}/{second}");

        // 4 - Assertion

        JsonPath resJsonPath = response.jsonPath();

        assertEquals(expStatusCode, response.statusCode());
        assertEquals(expBody.get("userId"),resJsonPath.getInt("userId"));
        assertEquals(expBody.get("id"),resJsonPath.getInt("id"));
        assertEquals(expBody.get("title"),resJsonPath.getString("title"));
        assertEquals(expBody.get("body"),resJsonPath.getString("body"));
    }

    @Test
    public void POST_01(){
        /*
            {
                "userId": 11,
                "title": "API Expert",
                "body": "Experience level is awesome!"
            }
        */

        //1.Step: Set the Url
        // Post url: "https://jsonplaceholder.typicode.com/posts";
        specJsonPlaceHolder.pathParams("first","posts");
        //2.Set the Expected Data
        JsonPlaceHolderTestData jsonPlaceHolder = new JsonPlaceHolderTestData();

        JSONObject reqBody = jsonPlaceHolder.setExpData();
        int expStatusCode = jsonPlaceHolder.statusCode;

        //3.Step: Send the Request and Get the Response
        Response response = given().
                spec(specJsonPlaceHolder).
                contentType(ContentType.JSON).
                body(reqBody).
                when().
                get("{first}");

        JsonPath actBody = response.jsonPath();
        actBody.prettyPrint();

        //4.Step: Do Assertions

        assertEquals(expStatusCode, response.statusCode());
        assertEquals(reqBody.get("userId"),actBody.get("userId"));
        assertEquals(reqBody.get("title"),actBody.get("title"));
        assertEquals(reqBody.get("body"),actBody.get("body"));
        assertEquals(101, actBody.getInt("id"));
    }
}
