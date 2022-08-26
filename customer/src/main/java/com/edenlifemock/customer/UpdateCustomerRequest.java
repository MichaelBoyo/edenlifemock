package com.edenlifemock.customer;

public record UpdateCustomerRequest(
        String email,
        String password,
        String firstName,
        String lastName) {
}
