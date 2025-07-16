package com.api.test.tests;

import static io.restassured.RestAssured.given;
import com.api.test.base.BaseSetUp;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.api.test.constants.ApiEndPoints;
import com.api.test.utils.TestUtil;
import io.restassured.response.Response;

public class Accounts extends BaseSetUp {
    static String token;
    String loginPayload;
    static String  id;
    Response response;

    @Test(priority = 1)
    public void createUserApi() {
        // getting login payload for test user
        loginPayload=TestUtil.getLoginPayload(null, null);
        response = given()
                .body(loginPayload)
                .log().all() // Log the request details
                .when()
                .post(ApiEndPoints.CREATE_USER)
                .then()
                .log().all() // Log the response details
                .statusCode(201)
                .extract()
                .response();
        id = response.jsonPath().getString("userID");
        System.out.println("User ID: " + id);

        Assert.assertNotNull(id, "User ID should not be null");
    }

    @Test(priority = 2, dependsOnMethods = "createUserApi")
    public void generateTokenApi() {
        Response response = given()
                .body(loginPayload)
                .log().all() // Log the request details
                .when()
                .post(ApiEndPoints.GENERATE_TOKEN)
                .then()
                .log().all() // Log the response details
                .statusCode(200)
                .extract().response();

        token = response.jsonPath().getString("token");
        System.out.println("Generated Token: " + token);

        Assert.assertNotNull((token), "Token should not be null");

    }

    @Test(priority = 3, dependsOnMethods = "createUserApi")
    public void getUserApi() {

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("userId", id)
                .when()
                .get(ApiEndPoints.GET_USER + "{userId}")
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println("Get User Response: " + response.asString());

    }
}


