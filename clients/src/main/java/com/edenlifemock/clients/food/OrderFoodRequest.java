package com.edenlifemock.clients.food;

public record OrderFoodRequest(String email,
                               Long customerId,
                               String customerName,
                               String phone,
                               String address,
                               String mealName
                           ) {
}
