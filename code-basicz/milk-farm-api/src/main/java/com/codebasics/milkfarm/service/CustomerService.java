package com.codebasics.milkfarm.service;

import com.codebasics.milkfarm.base.api.BaseBodyResponse;
import com.codebasics.milkfarm.entity.CustomerEntity;
import com.codebasics.milkfarm.expections.ConstraintViolationException;
import com.codebasics.milkfarm.expections.CustomerNotFound;
import com.codebasics.milkfarm.mapper.CustomerMapper;
import com.codebasics.milkfarm.model.request.CustomerRequest;
import com.codebasics.milkfarm.model.response.CustomerResponse;
import com.codebasics.milkfarm.model.response.CustomersResponse;
import com.codebasics.milkfarm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        CustomerResponse.CustomerResponseResult customerResponseResult;
        try {
            customerResponseResult = CustomerResponse.CustomerResponseResult.builder()
                    .customerEntity(customerRepository.save(customerMapper.toCustomerEntity(customerRequest)))
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ConstraintViolationException(e.getMostSpecificCause().getLocalizedMessage());
        }
        return BaseBodyResponse.result(customerResponseResult, CustomerResponse.class);
    }

    public CustomersResponse getAllCustomers() {
        CustomersResponse.CustomersResponseResult customersResponseResult = CustomersResponse.CustomersResponseResult.builder()
                .customerEntities(customerRepository.findAll())
                .build();
        return BaseBodyResponse.result(customersResponseResult, CustomersResponse.class);
    }

    public CustomerResponse getCustomer(UUID id) {
        CustomerResponse.CustomerResponseResult customerResponseResult = CustomerResponse.CustomerResponseResult.builder()
                .customerEntity(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFound("Customer not found.")))
                .build();
        return BaseBodyResponse.result(customerResponseResult, CustomerResponse.class);
    }

    public CustomerResponse updateCustomer(CustomerRequest customerRequest, UUID id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFound("Customer not found."));
        customerEntity.setCustomerName(customerRequest.getCustomerName());
        customerEntity.setMobileNo(customerRequest.getMobileNo());
        customerEntity.setRouteName(customerRequest.getRouteName());
        customerEntity.setVillage(customerRequest.getVillage());
        customerEntity.setSupervisor(customerRequest.getSupervisor());
        CustomerResponse.CustomerResponseResult customerResponseResult = CustomerResponse.CustomerResponseResult.builder()
                .customerEntity(customerRepository.save(customerEntity))
                .build();
        return BaseBodyResponse.result(customerResponseResult, CustomerResponse.class);
    }

    public void deleteCustomer(UUID id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomerNotFound("Customer not found.");
        }
    }
}
