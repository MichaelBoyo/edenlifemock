package com.edenlifemock.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements iCustomerService{
    private final CustomerRepository customerRepository;

//    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer saveCustomer(CustomerRequest customerRequest) {
       boolean anyMatch =  customerRepository.findAll().stream().
               anyMatch(customer -> customer.getEmail().equals(customerRequest.email()));
       if(anyMatch){
           throw new EmailAlreadyExistException("email already exist");
       }
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .password(customerRequest.password())
                .build();
        return customerRepository.saveAndFlush(customer);
    }
}
