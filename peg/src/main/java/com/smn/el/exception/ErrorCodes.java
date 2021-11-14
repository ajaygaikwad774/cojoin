package com.smn.el.exception;


public enum ErrorCodes {

    INTERNAL_ERROR(10100, "Internal Server Error."),

    USER_UNAUTHORIZED(10101, "Invalid user name/password."),

    USER_NOT_FOUND(10104, "Such user has not been found."),

    FILE_NOT_ACCESSIBLE(10401, "Failed to export file."),

    ENTITY_ALREADY_EXISTS(10109,"Entity already exists"),

    ENTITY_VALIDATION_ERROR(10110,"Entity validation error");

    private final Integer code;
    private final String description;

    ErrorCodes(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
