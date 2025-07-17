package com.api.test.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.api.test.base.BaseSetUp;
import com.api.test.constants.ApiEndPoints;
import com.api.test.utils.TestUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BooksTest extends BaseSetUp {
    int bookId;

    String jsonPayload = TestUtil.readJsonFromFile("createBook.json");

    @Test(priority = 1, dependsOnMethods = { "com.api.test.tests.AccountsTests.signUpUser",
            "com.api.test.tests.AccountsTests.verifySignUpUserAndGenerateToken" })
    public void addBooksTest() {
        Response response = given()
                .header("Authorization", "Bearer " + AccountsTests.token)
                .log().all() // Log the request details
                .body(jsonPayload)
                .when()
                .post(ApiEndPoints.ADD_NEW_BOOK)
                .then().log().all() // Log the response details
                .statusCode(200).extract().response();
        bookId = response.jsonPath().getInt("id");
        Assert.assertNotNull(bookId, "Book ID should not be null");

    }

    @Test(priority = 2, dependsOnMethods = "addBooksTest")
    public void getBookByIdTest() {
        Response response = given()
                .header("Authorization", "Bearer " + AccountsTests.token)
                // .pathParam("book_id", bookId)
                .log().all() // Log the request details
                .when()
                .get(String.format(ApiEndPoints.GET_BOOK_ID, bookId))
                .then()
                .log().all() // Log the response details
                .statusCode(200)
                .extract().response();
        Assert.assertTrue(response.jsonPath().getString("name").contains("The Silent Patient"),
                "Book name should match");

    }

    @Test(priority = 3, dependsOnMethods = "addBooksTest")
    public void updateBookByIdTest() {
        String updatePayload = TestUtil.readJsonFromFile("updateBook.json");
        Response response = given()
                .header("Authorization", "Bearer " + AccountsTests.token)
                .body(updatePayload)
                .log().all() // Log the request details
                .when()
                .put(String.format(ApiEndPoints.GET_BOOK_ID, bookId))
                .then()
                .log().all() // Log the response details
                .statusCode(200)
                .extract().response();

        JsonPath expectedJson = new JsonPath(updatePayload);
        JsonPath actualJson = new JsonPath(response.asString());
        Assert.assertEquals(actualJson.getString("name"), expectedJson.getString("name"),
                "Book name should match after update");

    }

    @Test(priority = 4, dependsOnMethods = "addBooksTest")
    public void getAllBooks() {
        Response response = given()
                .header("Authorization", "Bearer " + AccountsTests.token)
                .log().all() // Log the request details
                .when()
                .get(ApiEndPoints.GET_ALL_BOOKS)
                .then()
                .log().all() // Log the response details
                .statusCode(200)
                .extract().response();

        Assert.assertTrue(response.jsonPath().getList("books").size() > 0, "Books list should not be empty");

    }

    @Test(priority = 5, dependsOnMethods = "addBooksTest")
    public void deleteBookByIdTest() {

        Response response = given()
                .header("Authorization", "Bearer " + AccountsTests.token)
                .log().all() // Log the request details
                .when()
                .delete(String.format(ApiEndPoints.GET_BOOK_ID, bookId))
                .then()
                .log().all() // Log the response details
                .statusCode(200)
                .extract().response();
        Assert.assertTrue(response.jsonPath().getString("message").contains("Book deleted successfully"),
                "book deleted message should match");

    }

}
