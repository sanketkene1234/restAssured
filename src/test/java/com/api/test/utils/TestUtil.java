
package com.api.test.utils;
import com.api.test.ConfigReader;

public class TestUtil {

    public static String getLoginPayload(String user, String passWord) {
        if (user == null || passWord == null) {
            passWord = ConfigReader.get("password");
            user = generateRandomUser(ConfigReader.get("username"));
        }

        return "{\"userName\": \"" + user + "\", \"password\": \"" + passWord + "\"}";
    }

    public static String generateRandomUser(String username) {
        return Math.random() + username;
    }

    public static String getBookUpdatePayload(String id) {
        // Assuming the payload needs to include userId and isbn
        // Adjust the payload structure as per your API requirements
        String firstIsbn = ConfigReader.get("firstIsbn");
        return String.format(
                "{ \"userId\": \"%s\", \"isbn\": \"%s\" }",
                id, firstIsbn);
    }

    public static String addBooksPayload(String id) {
        String firstIsbn = ConfigReader.get("firstIsbn");
        return String.format(
                "{ \"userId\": \"%s\", \"collectionOfIsbns\": [{ \"isbn\": \"%s\" }] }",
                id, firstIsbn);
    }
}