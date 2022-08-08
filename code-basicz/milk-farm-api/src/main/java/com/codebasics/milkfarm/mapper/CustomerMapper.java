package com.codebasics.milkfarm.mapper;

import com.codebasics.milkfarm.entity.CustomerEntity;
import com.codebasics.milkfarm.model.request.CustomerRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity toCustomerEntity(CustomerRequest customerRequest) {
        return CustomerEntity.builder()
                .customerCode(customerRequest.getCustomerCode())
                .customerName(customerRequest.getCustomerName())
                .mobileNo(customerRequest.getMobileNo())
                .routeName(customerRequest.getRouteName())
                .village(customerRequest.getVillage())
                .supervisor(customerRequest.getSupervisor())
                .createdBy("SYSTEM")
                .updatedBy("SYSTEM")
                .build();
    }

}
