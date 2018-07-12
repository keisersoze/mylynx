package com.lynx.oauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceType;
    private String resourceIdentifier;


    public ResourceNotFoundException(String resourceType, String resourceIdentifier) {
        this.resourceType = resourceType;
        this.resourceIdentifier = resourceIdentifier;
    }

    @Override
    public String getMessage() {
        return String.format("The resource \"%s\" of type %s don't exist",resourceIdentifier , resourceType);
    }
}
