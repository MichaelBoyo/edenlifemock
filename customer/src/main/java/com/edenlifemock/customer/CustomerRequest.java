package com.edenlifemock.customer;

public record CustomerRequest(
        String email,
        String password,
        String firstName,
        String lastName
) {
}
