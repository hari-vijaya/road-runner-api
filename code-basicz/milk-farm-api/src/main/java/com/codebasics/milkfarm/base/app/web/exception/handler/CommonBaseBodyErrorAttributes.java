package com.codebasics.milkfarm.base.app.web.exception.handler;

import com.codebasics.milkfarm.base.api.BaseBodyError;
import com.codebasics.milkfarm.base.api.BaseBodyError.BaseBodyErrorBuilder;
import com.codebasics.milkfarm.base.app.web.tracer.RequestTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.HashMap;
import java.util.Map;


@Component
public class CommonBaseBodyErrorAttributes {
    RequestTracer requestTracer;

    @Autowired
    public CommonBaseBodyErrorAttributes(RequestTracer requestTracer) {
        this.requestTracer = requestTracer;
    }

    public BaseBodyErrorBuilder buildWithAttributes(Exception ex, RequestAttributes requestAttributes) {
        return this.buildWithAttributes(ex, this.getStartTime(requestAttributes));
    }

    public void populateAttributes(Map<String, Object> attributes, Exception ex, RequestAttributes requestAttributes) {
        this.populateAttributes(attributes, ex, this.getStartTime(requestAttributes));
    }

    Long getStartTime(RequestAttributes requestAttributes) {
        return (Long) requestAttributes.getAttribute("cn.app.startTime", 0);
    }

    BaseBodyErrorBuilder buildWithAttributes(Exception ex, Long startTime) {
        BaseBodyErrorBuilder builder = BaseBodyError.builder();
        Map<String, Object> attributes = new HashMap();
        this.populateAttributes(attributes, ex, (Long) startTime);
        builder.attributes(attributes);
        return builder;
    }

    void populateAttributes(Map<String, Object> attributes, Exception ex, Long startTime) {
        if (attributes != null) {
            if (ex != null && !attributes.containsKey("exception")) {
                attributes.put("exception", ex.getClass().getName());
            }

            if (startTime != null && !attributes.containsKey("timestamp")) {
                attributes.put("timestamp", startTime / 1000L);
            }

            String traceId = this.requestTracer.getTraceId();
            if (traceId != null && !attributes.containsKey("referenceId")) {
                attributes.put("referenceId", traceId);
            }

        }
    }
}
