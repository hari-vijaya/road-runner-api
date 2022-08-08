package com.codebasics.milkfarm.controller;

import com.codebasics.milkfarm.model.request.CustomerRequest;
import com.codebasics.milkfarm.model.response.CustomerResponse;
import com.codebasics.milkfarm.model.response.CustomersResponse;
import com.codebasics.milkfarm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/milk-farm/v1")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.addCustomer(customerRequest), HttpStatus.CREATED);
    }

    @PutMapping("/customer")
    public ResponseEntity<CustomerResponse> updateCustomer(@Valid @RequestBody CustomerRequest customerRequest, @RequestParam UUID id) {
        return new ResponseEntity<>(customerService.updateCustomer(customerRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/customer")
    public ResponseEntity<Void> deleteCustomer(@RequestParam UUID id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable UUID id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<CustomersResponse> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

}
