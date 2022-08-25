package com.edenlifemock.eurekaserver.customer;

public record CustomerRequest(
        String email,
        String password,
        String firstName,
        String lastName
) {
}
