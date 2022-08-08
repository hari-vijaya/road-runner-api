package com.codebasics.milkfarm.model.response;

import com.codebasics.milkfarm.base.api.BaseBodyResponse;
import com.codebasics.milkfarm.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CustomerResponse extends BaseBodyResponse<CustomerResponse.CustomerResponseResult> {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CustomerResponseResult {
        CustomerEntity customerEntity;
    }

}
