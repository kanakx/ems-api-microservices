package com.ems.eventmanagementspring.exceptions;

public class ExceptionMessage {

    private ExceptionMessage() {}

    public static String entityNotFound(String entityName) {
        return entityName + " not found";
    }

    public static String entityAlreadyExists(String entityName) {
        return entityName + " with such details already exists";
    }

}
