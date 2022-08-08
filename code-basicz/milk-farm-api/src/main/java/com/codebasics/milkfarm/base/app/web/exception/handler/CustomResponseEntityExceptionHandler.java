package com.codebasics.milkfarm.base.app.web.exception.handler;

import com.codebasics.milkfarm.base.api.BaseBodyError;
import com.codebasics.milkfarm.base.api.BaseBodyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ControllerAdvice
@Order(2147483636)
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);
    CommonBaseBodyErrorAttributes commonBaseBodyErrorAttributes;
    HttpStatus dataErrorsHttpStatus;

    @Autowired
    public CustomResponseEntityExceptionHandler(CommonBaseBodyErrorAttributes commonBaseBodyErrorAttributes, @Value("${cn.app.exception-handler.data-errors.statusCode:400}") int dataErrorsHttpStatusCode) {
        this.commonBaseBodyErrorAttributes = commonBaseBodyErrorAttributes;
        this.dataErrorsHttpStatus = HttpStatus.valueOf(dataErrorsHttpStatusCode);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.debug("MVC Exception", ex);
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }

        BaseBodyError fordError = this.commonBaseBodyErrorAttributes.buildWithAttributes(ex, request).message(ex.getMessage()).build();
        return new ResponseEntity(BaseBodyResponse.error(fordError), status);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseBodyError fordError = this.commonBaseBodyErrorAttributes.buildWithAttributes(ex, request).message(String.format("Validation failed for object='%s'. Error count: %s", ex.getBindingResult().getObjectName(), ex.getBindingResult().getErrorCount())).dataErrors(this.toDataErrors(ex.getBindingResult().getFieldErrors())).build();
        return new ResponseEntity(BaseBodyResponse.error(fordError), this.dataErrorsHttpStatus);
    }

    List<BaseBodyError.BaseBodyDataError> toDataErrors(List<FieldError> fieldErrors) {
        List<BaseBodyError.BaseBodyDataError> errors = new ArrayList();
        Iterator var3 = fieldErrors.iterator();

        while (var3.hasNext()) {
            FieldError fieldError = (FieldError) var3.next();
            errors.add(BaseBodyError.BaseBodyDataError.builder().code(fieldError.getCode()).name(fieldError.getField()).value(fieldError.getRejectedValue() == null ? null : String.valueOf(fieldError.getRejectedValue())).message(fieldError.getDefaultMessage()).build());
        }

        return errors;
    }
}
