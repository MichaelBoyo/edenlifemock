package com.edenlifemock.customer.dtos;

public record CustomerRequest(
        String email,
        String password,
        String firstName,
        String lastName
) {
}
