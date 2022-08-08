package com.codebasics.milkfarm.model.response;

import com.codebasics.milkfarm.base.api.BaseBodyResponse;
import com.codebasics.milkfarm.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class CustomersResponse extends BaseBodyResponse<CustomersResponse.CustomersResponseResult> {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CustomersResponseResult {
        List<CustomerEntity> customerEntities;
    }

}
