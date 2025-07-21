package com.cts.justdial.stepdefinition;

import java.util.Map;

public class TestDataContext {
    private static String tcId;
    private static Map<String, String> testData;

    public static String getTcId() {
        return tcId;
    }
    public static Map<String, String> getTestData() {
        return testData;
    }
    public static void setTestData(Map<String, String> testData) {
        TestDataContext.testData = testData;
    }
    public static void setTcId(String tcId) {
        TestDataContext.tcId = tcId;
    }
}
