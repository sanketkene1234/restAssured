
package com.api.test.utils;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.api.test.ConfigReader;

public class TestUtil {

    public static String readJsonFromFile(String fileName) {
        String jsonString = "";
        try {
            String path = Paths.get("src", "test", "java", "com", "api", "test", "data", fileName).toString();
            jsonString = new String(Files.readAllBytes(Paths.get(path)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String getSignUpPayload(String userEmail, String passWord) {
        if (userEmail == null || passWord == null) {
            passWord = ConfigReader.get("password");
            userEmail = generateRandomUser(ConfigReader.get("userEmail"));
        }

        return "{\"email\": \"" + userEmail + "\", \"password\": \"" + passWord + "\"}";
    }

    public static String generateRandomUser(String username) {
        return Math.random() + username;
    }

}