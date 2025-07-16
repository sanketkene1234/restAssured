package com.api.test.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.test.ConfigReader;
import com.api.test.base.BaseSetUp;
import com.api.test.constants.ApiEndPoints;
import com.api.test.utils.TestUtil;

import io.restassured.response.Response;

public class NegativeTests extends BaseSetUp {
  // This class is intended for negative test cases.
  // Currently, it does not contain any tests.
  // Future implementations can include tests that validate error handling,
  // such as invalid inputs, unauthorized access, etc.
  @Test
  public void createUserWeakPassword() {

    Response response = given()
        .body(TestUtil.getLoginPayload("weak", "123"))
        .log().all() // Log the request details
        .when()
        .post(ApiEndPoints.CREATE_USER)
        .then()
        .log().all() // Log the response details
        .statusCode(400)
        .extract()
        .response();
    String mesageString = response.jsonPath().getString("message");
    Assert.assertTrue(mesageString.contains("Passwords must have"),
        "User validation failed: password: Password must have at least one uppercase, one lowercase, one numeric and one special character");

  }

  @Test
  public void createTokenWithInvalidCreds() {
    Response response = given()
        .body(TestUtil.getLoginPayload("invalidUser", "invalidPass"))
        .log().all() // Log the request details
        .when()
        .post(ApiEndPoints.GENERATE_TOKEN)
        .then()
        .log().all() // Log the response details
        .statusCode(200)
        .extract()
        .response();
    String status = response.jsonPath().getString("status");
    Assert.assertEquals(status, "Failed", "Token generation should fail with invalid credentials");

  }

  @Test
  public void addBooksWithoutToken() {
    String bookPayload = TestUtil.addBooksPayload(Accounts.id);
    Response response = given()
        .log().all() // Log the request details
        .body(bookPayload)
        .when()
        .post(ApiEndPoints.ADD_BOOKS)
        .then().log().all() // Log the response details
        .statusCode(401).extract().response();

    String message = response.jsonPath().getString("message");
    Assert.assertTrue(message.contains("User not authorized!"),
        "Expected Unauthorized error when adding books without token");
  }

  @Test
  public void getUserWrongId() {
    Response response = given()
        .header("Authorization", "Bearer " + Accounts.token)
        .pathParam("id", "wrongId")
        .log().all()
        .when()
        .get(ApiEndPoints.GET_USER + "{id}")
        .then().log().all()
        .statusCode(401).extract().response();

    String message = response.jsonPath().getString("message");
    Assert.assertTrue(message.contains("User not found"), "Expected User not found error for wrong user ID");
  }

  @Test(priority = 1, dependsOnMethods = "com.api.test.tests.Accounts.createUserApi")
  public void updateBookWithoutIsbn() {

    Response response = given()
        .header("Authorization", "Bearer " + Accounts.token)
        .log().all()
        .body(String.format(
            "{ \"userId\": \"%s\", \"isbn\": \"%s\" }",
            Accounts.id, "")) // Intentionally leaving isbn empty
        .pathParam("isbn", ConfigReader.get("firstIsbn"))
        .when()
        .put(ApiEndPoints.UPDATE_BOOKS + "{isbn}")
        .then().log().all()
        .statusCode(400).extract().response();

    String message = response.jsonPath().getString("message");
    Assert.assertTrue(message.contains("Request Body is Invalid!"), "Expected error when updating book without ISBN");
  }

  @Test
  public void deleteBookWithWrongId() {
    String body = TestUtil.getBookUpdatePayload("");
    Response response = given()
        .header("Authorization", "Bearer " + Accounts.token)
        .body(body).log().all() // Log the request details
        .when()
        .delete(ApiEndPoints.DELETE_BOOK)
        .then().log().all() // Log the response details
        .statusCode(401).extract().response();
    String message = response.jsonPath().getString("message");
    Assert.assertTrue(message.contains("User Id not correct!"), "Expected error when deleting book with wrong user ID");

  }
}
