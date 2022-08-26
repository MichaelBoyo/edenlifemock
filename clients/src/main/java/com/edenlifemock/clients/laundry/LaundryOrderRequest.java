package com.edenlifemock.clients.laundry;

public record LaundryOrderRequest(
        String email,
        String customerName,
        String address,
        String phone,
        Integer noOfClothing,
        Long laundryOrderId) {
}
