package com.api.test.tests;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.test.base.BaseSetUp;
import com.api.test.constants.ApiEndPoints;

import io.restassured.response.Response;

public class NegativeTests extends BaseSetUp {
    // This class is intended for negative test cases.
    // Currently, it does not contain any tests.
    // Future implementations can include tests that validate error handling,
    // such as invalid inputs, unauthorized access, etc.
 public static final String CONTENT_TYPE = "application/json";
    @Test(priority = 1)
    public void createUserWeakPassword() { 
      String user = "weak";
      String passWord = "123";
      
    Response response = given()
                .contentType(CONTENT_TYPE)
                .body("{\"userName\": \"" + user + "\", \"password\": \"" + passWord + "\"}")
                .log().all() // Log the request details
                .when()
                .post(ApiEndPoints.CREATE_USER)
                .then()
                .log().all() // Log the response details
                .statusCode(400)
                .extract()
                .response();
        String mesageString = response.jsonPath().getString("message");
        Assert.assertTrue(mesageString.contains("Passwords must have"), "User validation failed: password: Password must have at least one uppercase, one lowercase, one numeric and one special character");

    }
    
}
