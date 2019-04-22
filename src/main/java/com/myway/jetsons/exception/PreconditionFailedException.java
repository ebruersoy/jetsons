package com.myway.jetsons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PreconditionFailedException extends RuntimeException {

    private final String reason;


    public PreconditionFailedException(String reason) {
        super(reason);
        this.reason = reason;
    }


    public String getReason() {
        return reason;
    }
}
