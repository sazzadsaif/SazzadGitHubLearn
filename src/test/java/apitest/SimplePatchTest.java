package apitest;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePatchTest {
    private static final Logger LOGGER = LogManager.getLogger(SimplePatchTest.class);

    @Test
    public void updateUserSingleField() {
        LOGGER.info("------Update Single User");
        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        Faker faker = new Faker();

        String jobTitle = faker.job().title();
        LOGGER.debug("New Job Tilte: " + jobTitle);

        JSONObject reqBody = new JSONObject();

        reqBody.put("job",jobTitle);

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(reqBody.toJSONString());

        String id = "2";

        Response response  = httpRequest.request(Method.PUT,id);
        LOGGER.debug(response.prettyPrint());

        Assert.assertEquals(response.getStatusCode(),200);

        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("job");
        Assert.assertEquals(actualName,jobTitle);


        LOGGER.info("---End----");

    }
}
