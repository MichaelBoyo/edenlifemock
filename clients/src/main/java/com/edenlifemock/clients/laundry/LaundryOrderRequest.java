package com.edenlifemock.clients.laundry;

public record LaundryOrderRequest(
        String email,
        String customerName,
        String address,
        Integer noOfClothing,
        Long laundryOrderId) {
}
