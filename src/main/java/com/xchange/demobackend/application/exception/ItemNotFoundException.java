package com.xchange.demobackend.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String itemName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", itemName, fieldName, fieldValue));
    }

}
