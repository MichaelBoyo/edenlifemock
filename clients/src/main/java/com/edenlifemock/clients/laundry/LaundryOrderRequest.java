package com.edenlifemock.clients.laundry;

public record LaundryOrderRequest(
        String email,
        Long customerId,
        String customerName,
        String address,
        Integer noOfClothing,
        Long laundryOrderId) {
}
