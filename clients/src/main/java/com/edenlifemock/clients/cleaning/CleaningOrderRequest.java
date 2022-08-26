package com.edenlifemock.clients.cleaning;

public record CleaningOrderRequest(
        String email,
        Long customerId,
        String customerName,
        String contactNo,
        String address,
        Integer noOfRooms) {
}