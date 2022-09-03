package com.edenlifemock.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer,Long > {
    Customer findCustomerByEmail(String email);


    Optional<Customer> findCustomersByUsername(String username);
}
