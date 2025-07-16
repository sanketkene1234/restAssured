package com.api.test.base;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.config;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import com.api.test.ConfigReader;

public class BaseSetUp {

    @BeforeSuite
    public static void setUp() {
        RestAssured.baseURI = ConfigReader.get("base.url");
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }
}
