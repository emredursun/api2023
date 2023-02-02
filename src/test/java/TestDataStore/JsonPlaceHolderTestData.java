package TestDataStore;

import org.json.JSONObject;

public class JsonPlaceHolderTestData {

    public int statusCode = 200;

    public JSONObject getExpData(){

        JSONObject expBody = new JSONObject();

        expBody.put("userId", 3);
        expBody.put("id", 22);
        expBody.put("title","dolor sint quo a velit explicabo quia nam");
        expBody.put("body", "eos qui et ipsum ipsam suscipit aut\n" +
                "sed omnis non odio\n" +
                "expedita earum mollitia molestiae aut atque rem suscipit\n" +
                "nam impedit esse");

        return expBody;
    }

    public JSONObject setExpData(){
        /*
            {
                "userId": 11,
                "title": "API Expert",
                "body": "Experience level is awesome!"
            }
        */

        JSONObject setExpBody = new JSONObject();

        setExpBody.put("userId", 11);
        setExpBody.put("title","API Expert");
        setExpBody.put("body", "Experience level is awesome!");

        return setExpBody;
    }

}
