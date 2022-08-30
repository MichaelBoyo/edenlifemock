package com.edenlifemock.customer.dtos;

public record UpdateCustomerRequest(
        String email,
        String password,
        String firstName,
        String lastName) {
}
