package com.codebasics.milkfarm.base.app.web.exception;

import com.codebasics.milkfarm.base.api.BaseBodyError;
import com.codebasics.milkfarm.base.api.BaseBodyResponse;
import org.springframework.http.HttpStatus;

public class BaseBodyResponseException extends RuntimeException {
    BaseBodyError bodyError;
    HttpStatus httpStatus;

    public BaseBodyResponseException(BaseBodyError bodyError, HttpStatus httpStatus) {
        super(bodyError != null && bodyError.getMessages() != null && !bodyError.getMessages().isEmpty() ? (String) bodyError.getMessages().get(0) : null);
        this.bodyError = bodyError;
        this.httpStatus = httpStatus;
    }

    public BaseBodyResponseException(String message, HttpStatus httpStatus) {
        this(message, httpStatus, (String) null);
    }

    public BaseBodyResponseException(String message, HttpStatus httpStatus, String errorCode) {
        this(BaseBodyError.builder().errorCode(errorCode).message(message).build(), httpStatus);
    }

    public BaseBodyResponse<Object> getBodyResponse() {
        return BaseBodyResponse.error(this.bodyError);
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
