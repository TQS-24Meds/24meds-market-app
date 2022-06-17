package com.meds.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ObjectErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectErrorException(String message){ super(message); }
}
