package com.codebasics.milkfarm.base.api;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseBodyError {
    String errorCode;
    @Singular
    List<String> messages;
    @Singular
    List<BaseBodyDataError> dataErrors;
    @Singular
    Map<String, Object> attributes;

    public Map<String, Object> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap<String, Object>();
        } else if (!(this.attributes instanceof HashMap<?, ?>)) {
            this.attributes = new HashMap<>(this.attributes);
        }
        return this.attributes;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BaseBodyDataError {
        String code;
        String name;
        String value;
        String message;
    }
}
