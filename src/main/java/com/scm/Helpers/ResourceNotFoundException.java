package com.scm.Helpers;

public class ResourceNotFoundException extends RuntimeException  {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("Resource not found");
    }

}
