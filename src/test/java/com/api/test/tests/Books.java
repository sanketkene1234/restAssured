package com.api.test.tests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import com.api.test.ConfigReader;
import com.api.test.base.BaseSetUp;
import com.api.test.constants.ApiEndPoints;
import com.api.test.utils.TestUtil;
import org.testng.Assert;
import io.restassured.response.Response;

public class Books extends BaseSetUp {
    String isbnNumber;

    @Test(priority = 1, dependsOnMethods = { "com.api.test.tests.Accounts.createUserApi",
            "com.api.test.tests.Accounts.generateTokenApi" })
    public void addBooks() {

        String bookPayload = TestUtil.addBooksPayload(Accounts.id);
        Response response = given()
                .header("Authorization", "Bearer " + Accounts.token)
                .log().all() // Log the request details
                .body(bookPayload)
                .when()
                .post(ApiEndPoints.ADD_BOOKS)
                .then().log().all() // Log the response details
                .statusCode(201).extract().response();

        isbnNumber = response.jsonPath().getString("books[0].isbn");
        Assert.assertNotNull(isbnNumber, "ISBN number should not be null");
        System.out.println("ISBN Number: " + isbnNumber);
    }

    @Test(priority = 2, dependsOnMethods = "addBooks")
    public void getAllBooks() {
        Response response = given()
                .log().all() // Log the request details
                .when()
                .get(ApiEndPoints.GET_BOOKS)
                .then().log().all() // Log the response details
                .statusCode(200).extract().response();

        String title = response.jsonPath().getString("books[0].title");
        Assert.assertNotNull(title, "Book title should not be null");
        System.out.println("Retrieved Book Title: " + title);
    }

    @Test(priority = 3, dependsOnMethods = "addBooks")
    public void updateBook() {
        String updatePayload = TestUtil.getBookUpdatePayload(Accounts.id);

        given()
                .header("Authorization", "Bearer " + Accounts.token)
                .pathParam("isbn", ConfigReader.get("firstIsbn"))
                .log().all()
                .body(updatePayload)
                .when()
                .put(ApiEndPoints.UPDATE_BOOKS + "{isbn}")
                .then().log().all()
                .statusCode(400).extract().response();

    }

    @Test(priority = 4, dependsOnMethods = "addBooks")
    public void deleteBook() {
        String body = TestUtil.getBookUpdatePayload(Accounts.id);
        Response response = given()
                .header("Authorization", "Bearer " + Accounts.token)
                .body(body)
                .log().all() // Log the request details
                .when()
                .delete(ApiEndPoints.DELETE_BOOK)
                .then().log().all() // Log the response details
                .statusCode(204).extract().response();

        Assert.assertTrue(response.getBody().asString().isEmpty(), "Response body should be empty after deletion");
        System.out.println("Book deleted successfully.");
    }

    @Test(priority = 5, dependsOnMethods = "deleteBook")
    public void getBooksAfterDeletion() {
        Response response = given()
                .header("Authorization", "Bearer " + Accounts.token)
                .log().all() // Log the request details
                .when()
                .get(ApiEndPoints.GET_BOOKS + Accounts.id)
                .then().log().all() // Log the response details
                .statusCode(200).extract().response();

        String resBody = response.getBody().asString();
        Assert.assertFalse(resBody.contains(isbnNumber), "Deleted book should not be present in the response");
    }
}