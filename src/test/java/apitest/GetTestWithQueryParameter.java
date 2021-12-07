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

public class GetTestWithQueryParameter {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithQueryParameter.class);

    @Test
    public void getAllUserWithQueryParameter() {
        LOGGER.info("-----get all users query parameter-----");

        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.queryParam("page","2").request(Method.GET);
        LOGGER.debug(response.prettyPrint());

        Assert.assertEquals(response.getStatusCode(),200);

        JsonPath jsonPath = response.jsonPath();

        List<String> listEmails = jsonPath.get("data.email");

        String expectedEmailId = "michael.lawson@reqres.in";

        boolean emailExists = listEmails.contains(expectedEmailId);

        Assert.assertTrue(emailExists,expectedEmailId + "do not exists ");

        LOGGER.info("-----END test: Get All users query--------");

    }
}



