package com.codebasics.milkfarm.base.app.web.exception.handler;

import com.codebasics.milkfarm.base.api.BaseBodyError.BaseBodyErrorBuilder;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class FallbackErrorAttributesHandler extends DefaultErrorAttributes {
    CommonBaseBodyErrorAttributes baseBodyErrorAttributes;

    public FallbackErrorAttributesHandler(CommonBaseBodyErrorAttributes baseBodyErrorAttributes) {
        super();
        this.baseBodyErrorAttributes = baseBodyErrorAttributes;
    }

    String valueOf(Object o) {
        return o != null ? String.valueOf(o) : null;
    }

    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        ErrorAttributeOptions errorAttributeOptions = ErrorAttributeOptions
                .defaults();

        if (includeStackTrace) {
            errorAttributeOptions.including(ErrorAttributeOptions.Include.STACK_TRACE);
        }

        Map<String, Object> srcErrorAttributes = super.getErrorAttributes(webRequest, errorAttributeOptions
        );
        BaseBodyErrorBuilder bodyErrorBuilder = this.baseBodyErrorAttributes.buildWithAttributes((Exception) null, webRequest).attribute("fallback", "true");
        String value;
        if ((value = this.valueOf(srcErrorAttributes.get("exception"))) != null) {
            bodyErrorBuilder.attribute("exception", value);
        }

        if ((value = this.valueOf(srcErrorAttributes.get("error"))) != null) {
            bodyErrorBuilder.message(value);
        }

        if ((value = this.valueOf(srcErrorAttributes.get("message"))) != null) {
            bodyErrorBuilder.message(value);
        }

        Map<String, Object> errorAttributes = new HashMap();
        errorAttributes.put("value", (Object) null);
        errorAttributes.put("error", bodyErrorBuilder.build());
        return errorAttributes;
    }
}

