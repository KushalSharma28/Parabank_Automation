package com.parabank.utils;

import java.util.Random;

/**
 * Utility class for test data generation
 */
public class TestDataUtils {
    private static final Random random = new Random();
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUMERIC = "0123456789";

    /**
     * Generate random email
     *
     * @return Random email address
     */
    public static String generateRandomEmail() {
        String randomString = generateRandomString(10);
        return randomString + "@test.com";
    }

    /**
     * Generate random username
     *
     * @return Random username
     */
    public static String generateRandomUsername() {
        return "user_" + generateRandomString(8);
    }

    /**
     * Generate random password
     *
     * @return Random password
     */
    public static String generateRandomPassword() {
        return "Pass@" + generateRandomNumeric(6);
    }

    /**
     * Generate random string
     *
     * @param length Length of string
     * @return Random string
     */
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }

    /**
     * Generate random numeric string
     *
     * @param length Length of string
     * @return Random numeric string
     */
    public static String generateRandomNumeric(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(NUMERIC.charAt(random.nextInt(NUMERIC.length())));
        }
        return sb.toString();
    }

    /**
     * Generate random phone number
     *
     * @return Random phone number
     */
    public static String generateRandomPhoneNumber() {
        return generateRandomNumeric(10);
    }

    /**
     * Generate random SSN
     *
     * @return Random SSN
     */
    public static String generateRandomSSN() {
        return generateRandomNumeric(9);
    }
}
