package com.codebasics.milkfarm.base.app.web.exception.handler;

import com.codebasics.milkfarm.base.api.BaseBodyError;
import com.codebasics.milkfarm.base.api.BaseBodyResponse;
import com.codebasics.milkfarm.base.app.web.exception.BaseBodyResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    CommonBaseBodyErrorAttributes commonBaseBodyErrorAttributes;

    @Autowired
    public ControllerExceptionHandler(CommonBaseBodyErrorAttributes commonBaseBodyErrorAttributes) {
        this.commonBaseBodyErrorAttributes = commonBaseBodyErrorAttributes;
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    protected ResponseEntity<?> handleUncaughtException(Exception ex, WebRequest request) {
        ResponseEntity responseEntity;
        if (ex instanceof BaseBodyResponseException) {
            BaseBodyResponseException bodyResponseEx = (BaseBodyResponseException) ex;
            if (bodyResponseEx.getBodyResponse().getError() != null) {
                Map<String, Object> errorAttributes = bodyResponseEx.getBodyResponse().getError().getAttributes();
                this.commonBaseBodyErrorAttributes.populateAttributes(errorAttributes, ex, request);
            }

            responseEntity = new ResponseEntity(bodyResponseEx.getBodyResponse(), bodyResponseEx.getHttpStatus());
        } else {
            this.logger.error("Unexpected exception", ex);
            BaseBodyError fordError = this.commonBaseBodyErrorAttributes.buildWithAttributes(ex, request).message(ex.getMessage()).build();
            ResponseStatus responseStatus = (ResponseStatus) AnnotatedElementUtils.findMergedAnnotation(ex.getClass(), ResponseStatus.class);
            HttpStatus httpStatus = responseStatus != null ? responseStatus.code() : HttpStatus.INTERNAL_SERVER_ERROR;
            responseEntity = new ResponseEntity(BaseBodyResponse.error(fordError), httpStatus);
        }

        if (request != null && HttpStatus.INTERNAL_SERVER_ERROR.equals(responseEntity.getStatusCode())) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }

        return responseEntity;
    }
}
