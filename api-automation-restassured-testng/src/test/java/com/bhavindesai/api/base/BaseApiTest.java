package com.bhavindesai.api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.InputStream;
import java.util.Properties;

public abstract class BaseApiTest {
    @BeforeClass(alwaysRun = true)
    public void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = loadFromConfig("baseUrl", "https://reqres.in");
        }
        RestAssured.baseURI = baseUrl;
    }

    private String loadFromConfig(String key, String defaultValue) {
        Properties props = new Properties();
        try (InputStream is = BaseApiTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) props.load(is);
        } catch (Exception ignored) {}
        return props.getProperty(key, defaultValue);
    }
}
