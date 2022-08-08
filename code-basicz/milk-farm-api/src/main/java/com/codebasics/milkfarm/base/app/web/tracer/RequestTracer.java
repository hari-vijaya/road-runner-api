package com.codebasics.milkfarm.base.app.web.tracer;

public interface RequestTracer {
    String getTraceId();

    boolean isEnabled();
}
