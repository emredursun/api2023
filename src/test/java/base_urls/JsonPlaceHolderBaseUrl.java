package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {
        protected RequestSpecification specJsonPlaceHolder;

    //If you use @Before annotation at the top of a method, it means the method will be executed before every test method
    @Before
    public void setUp(){
<<<<<<< HEAD
        specJsonPlaceHolder = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com").
                build();
=======
        specJsonPlaceHolder = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
>>>>>>> 6f47f5f5ece890ea4a81915ef4b41a91d88fc750
    }
}
