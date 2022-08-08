package com.codebasics.milkfarm.base.app.web.tracer;/*
package com.codebasicz.cloudnative.base.app.web.tracer;


import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean({SpanNamer.class})
public class SleuthRequestTracerConfiguration {
    public SleuthRequestTracerConfiguration() {
    }

    @Bean
    public RequestTracer requestTracerBean(final Tracer tracer) {
        return new RequestTracer() {
            public boolean isEnabled() {
                return true;
            }

            public String getTraceId() {
                Span currentSpan = tracer.currentSpan();
                TraceContext context = currentSpan != null ? currentSpan.context() : null;
                return context != null ? context.traceIdString() : null;
            }
        };
    }
}
*/
