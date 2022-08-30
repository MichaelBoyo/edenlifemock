package com.edenlifemock.clients.cleaning;

public record CleaningOrderRequest(
        String email,
        String customerName,
        String contactNo,
        String address,
        Integer noOfRooms) {
}