package com.smn.el.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;

public class DBExceptionManipulator {

	public static ValidationException getValidationException(DataIntegrityViolationException ex) {
        return new ValidationException(getViolationMessage(ex.getMostSpecificCause().getMessage()), ErrorCodes.ENTITY_ALREADY_EXISTS);
    }

    private static String getViolationMessage(String message) {
        message = message.substring(message.lastIndexOf("Key") + 4);
        message = message.replaceAll("\\(|\\)|", "").replaceAll("_|=", " ");
        return message.substring(0, 1).toUpperCase() + message.substring(1);
    }

    public static ValidationException getValidationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream().map(ConstraintViolation:: getMessage).collect(Collectors.joining("; "));
        return new ValidationException(message, ErrorCodes.ENTITY_VALIDATION_ERROR);
    }
    
}

