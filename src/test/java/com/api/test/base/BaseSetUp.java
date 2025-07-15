package com.api.test.base;

import static io.restassured.RestAssured.config;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import com.api.test.ConfigReader;

public class BaseSetUp {

    

    
    @BeforeSuite
    public static void setUp() {
        RestAssured.baseURI = ConfigReader.get("base.url");
        // RestAssured.requestSpecification = RestAssured.given().contentType(CONTENT_TYPE);

    }
}
