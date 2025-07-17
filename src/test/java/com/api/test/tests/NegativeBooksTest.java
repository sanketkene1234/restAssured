package com.api.test.tests;

import com.api.test.base.BaseSetUp;
import com.api.test.constants.ApiEndPoints;
import com.api.test.utils.TestUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeBooksTest extends BaseSetUp {

    @Test(priority = 1, dependsOnMethods = { "com.api.test.tests.AccountsTests.signUpUser",
            "com.api.test.tests.AccountsTests.verifySignUpUserAndGenerateToken" })
    public void addBookWithInvalidTokenTest() {
        String jsonPayload = TestUtil.readJsonFromFile("createBook.json");
        Response response = given()
                .header("Authorization", "Bearer " + "invalid_token")
                .log().all() // Log the request details
                .body(jsonPayload)
                .when()
                .post(ApiEndPoints.ADD_NEW_BOOK)
                .then().log().all() // Log the response details
                .statusCode(403).extract().response();
        String message = response.jsonPath().getString("detail");
        Assert.assertEquals(message, "Invalid token or expired token", "Error message mismatch");
    }

    @Test(priority = 2, dependsOnMethods = "com.api.test.tests.BooksTest.addBooksTest")
    public void getAllBookWithInvalidTokenTest() {
        Response response = given()
                .header("Authorization", "Bearer " + "invalid_token")
                .log().all() // Log the request details
                .when()
                .get(ApiEndPoints.GET_ALL_BOOKS)
                .then()
                .log().all() // Log the response details
                .statusCode(403)
                .extract().response();

        String message = response.jsonPath().getString("detail");
        Assert.assertEquals(message, "Invalid token or expired token", "Error message mismatch");
    }

    @Test(priority = 2, dependsOnMethods = "com.api.test.tests.BooksTest.addBooksTest")
    public void getBookWithNonExistantIdTest() {
        Response response = given()
                .header("Authorization", "Bearer " + AccountsTests.token)
                .log().all() // Log the request details
                .when()
                .get(String.format(ApiEndPoints.GET_BOOK_ID, "89999"))
                .then()
                .log().all() // Log the response details
                .statusCode(404)
                .extract().response();
        String message = response.jsonPath().getString("detail");
        Assert.assertEquals(message, "Book not found", "Error message mismatch");
    }

    @Test(priority = 2, dependsOnMethods = "com.api.test.tests.BooksTest.addBooksTest")
    public void getBookWithInvalidIdTest() {
        Response response = given()
                .header("Authorization", "Bearer " + AccountsTests.token)
                .log().all() // Log the request details
                .when()
                .get(String.format(ApiEndPoints.GET_BOOK_ID, "inalid_id"))
                .then()
                .log().all() // Log the response details
                .statusCode(422)
                .extract().response();
        String message = response.jsonPath().getString("detail[0].msg");
        Assert.assertEquals(message, "Input should be a valid integer, unable to parse string as an integer",
                "Error message mismatch");
    }

}
