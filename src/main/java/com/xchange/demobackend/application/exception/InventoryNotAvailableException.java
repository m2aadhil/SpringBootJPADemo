package com.xchange.demobackend.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InventoryNotAvailableException extends RuntimeException {

    public InventoryNotAvailableException(String productCode) {
        super(String.format("Inventory not available for item %s" , productCode));
    }
}
