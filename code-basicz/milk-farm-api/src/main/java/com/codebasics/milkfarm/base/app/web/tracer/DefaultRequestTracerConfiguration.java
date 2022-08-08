package com.codebasics.milkfarm.base.app.web.tracer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultRequestTracerConfiguration {
    public DefaultRequestTracerConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestTracer requestTracerBean() {
        return new RequestTracer() {
            public boolean isEnabled() {
                return false;
            }

            public String getTraceId() {
                return null;
            }
        };
    }
}
