package com.ems.emsdataservicespring.exceptions;

public class ExceptionMessage {

    private ExceptionMessage() {}

    public static String entityNotFound(String entityName) {
        return entityName + " not found";
    }

    public static String entityAlreadyExists(String entityName) {
        return entityName + " with such details already exists";
    }

    public static String invalidToken() {
        return "Invalid JWT token";
    }

    public static String expiredToken() {
        return "Expired JWT token";
    }

    public static String tokenParsingError() {
        return "Error parsing JWT token";
    }

    public static String missingTokenClaims(String claim) {
        return "Missing " + claim + " claims in JWT token";
    }

}
