package com.edenlifemock.customer.dtos;

import java.time.LocalDateTime;

public record CustomerResponse(
        Long customerId,
        String email,
        String firstName,
        String lastName,
        LocalDateTime dateRegistered
) {
}
