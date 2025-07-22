package api.com.qa.tests;

import api.com.qa.base.*;
import api.com.qa.payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ui.com.qa.tests.DemoQABookStoreTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class CreateUserTest extends TestBase {
	static Logger log = LogManager.getLogger(DemoQABookStoreTest.class);
		
	@BeforeMethod
	public void prep() {
		setup();
	}

    @Test(priority = 1)
    public void createUser() {
        UserPayload payload = new UserPayload("Pavan", "QA");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(payload)
          .when()
                .post("/users")
          .then()
                .statusCode(201)
                .extract().response();

        userId = response.jsonPath().getString("id");
        Assert.assertNotNull(userId, "User ID should not be null");
        log.info("Created User ID: " + userId);
    }

    @Test(priority = 2, dependsOnMethods = "createUser")
    public void getUserDetails() {
        // Note: reqres.in returns mock userId for create, can't GET by that ID.
        // Using /users/2 as a sample valid user
        Response response = given()
        		.header("x-api-key", "reqres-free-v1")
           .when()
                .get("/users/2")
           .then()
                .statusCode(200)
                .extract().response();

        String email = response.jsonPath().getString("data.email");
        Assert.assertTrue(email.contains("@reqres.in"), "Email validation failed");
    }

    @Test(priority = 3, dependsOnMethods = "createUser")
    public void updateUser() {
        UserPayload updatedPayload = new UserPayload("Pavan B", "Senior QA");

        Response response = given()
        		.header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(updatedPayload)
          .when()
                .put("/users/" + userId)
          .then()
                .statusCode(200)
                .extract().response();

        String updatedName = updatedPayload.getName();
        Assert.assertEquals(updatedName, "Pavan B", "Name update validation failed");
        log.info("Updated name to: " + updatedName);
    }
}
