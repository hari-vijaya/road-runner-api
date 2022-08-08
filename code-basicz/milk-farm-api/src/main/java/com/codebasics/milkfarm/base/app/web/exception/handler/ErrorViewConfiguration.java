package com.codebasics.milkfarm.base.app.web.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.Builder;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProviders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Configuration
public class ErrorViewConfiguration {
    public ErrorViewConfiguration() {
    }

    private static class ErrorTemplateMissingCondition extends SpringBootCondition {
        private ErrorTemplateMissingCondition() {
        }

        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Builder message = ConditionMessage.forCondition("ErrorTemplate Missing", new Object[0]);
            TemplateAvailabilityProviders providers = new TemplateAvailabilityProviders(context.getClassLoader());
            TemplateAvailabilityProvider provider = providers.getProvider("error", context.getEnvironment(), context.getClassLoader(), context.getResourceLoader());
            return provider != null ? ConditionOutcome.noMatch(message.foundExactly("template from " + provider)) : ConditionOutcome.match(message.didNotFind("error template view").atAll());
        }
    }

    @ConditionalOnProperty(
            prefix = "server.error.whitelabel",
            name = {"enabled"},
            matchIfMissing = true
    )
    @Conditional({ErrorTemplateMissingCondition.class})
    public static class CustomWhitelabelErrorViewConfiguration {
        private final String customErrorView = "<html><body><h1>Whitelabel Error Page</h1><p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p><hr/><div><pre>%s</pre></div></body></html>";
        ObjectWriter objectWriter = (new ObjectMapper()).writerWithDefaultPrettyPrinter();

        public CustomWhitelabelErrorViewConfiguration() {
        }

        @Bean(
                name = {"error"}
        )
        @ConditionalOnMissingBean(
                name = {"error"}
        )
        public View defaultErrorView() {
            return new View() {
                public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                    if (response.getContentType() == null) {
                        response.setContentType(this.getContentType());
                    }

                    response.getWriter().append(String.format("<html><body><h1>Whitelabel Error Page</h1><p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p><hr/><div><pre>%s</pre></div></body></html>", CustomWhitelabelErrorViewConfiguration.this.objectWriter.writeValueAsString(model.get("error"))));
                }

                public String getContentType() {
                    return "text/html";
                }
            };
        }
    }
}
