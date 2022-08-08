package com.codebasics.milkfarm.base.app.web.exception.handler;

import com.codebasics.milkfarm.base.app.web.tracer.DefaultRequestTracerConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(
        prefix = "cn.app.exception-handler",
        name = {"enabled"},
        matchIfMissing = false
)
@ComponentScan(
        basePackageClasses = {ExceptionHandlerConfiguration.class}
)
@Import({DefaultRequestTracerConfiguration.class})
public class ExceptionHandlerConfiguration {
    public ExceptionHandlerConfiguration() {
    }
}
