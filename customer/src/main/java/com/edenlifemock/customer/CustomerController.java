package com.edenlifemock.customer;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final iCustomerService iCustomerService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody CustomerRequest customerRequest){
        return new ResponseEntity<>(iCustomerService.saveCustomer(customerRequest),HttpStatus.OK);
    }
}
