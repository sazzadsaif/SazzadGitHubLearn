package apitest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetTestWithPathVariable {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithPathVariable.class);

    @Test
    public void getSingleUser() {
        LOGGER.info("-----get single with path variable-----");

        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();
        String id = "2";

        Response response = httpRequest.request(Method.GET,id);
        LOGGER.debug(response.prettyPrint());

        Assert.assertEquals(response.getStatusCode(),200);

        JsonPath jsonPath = response.jsonPath();

        String actualEmailId =jsonPath.getString("data.email");

        String expectedEmailId = "janet.weaver@reqres.in";



        Assert.assertEquals(actualEmailId,expectedEmailId);

        LOGGER.info("-----END test: Get All users query--------");

    }
    @Test
    public void attemptToGetUserWithInvalidId(){

    }
}



