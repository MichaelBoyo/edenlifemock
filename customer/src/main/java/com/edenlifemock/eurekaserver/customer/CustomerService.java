package com.edenlifemock.eurekaserver.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService implements iCustomerService{
    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;
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
        customerRepository.saveAndFlush(customer);

        NotificationResponse resp = restTemplate.getForObject(
                "http://localhost:8081/api/v1/notification/{id}",
                NotificationResponse.class,
                customer.getFirstName() + " " + customer.getLastName()
        );
        return customer;
    }
}
