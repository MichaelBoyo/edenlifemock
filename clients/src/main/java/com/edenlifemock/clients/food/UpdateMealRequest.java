package com.edenlifemock.clients.food;

public record UpdateMealRequest(Long mealId,
                                String mealName,
                                String type,
                                String desc,
                                Double price) {
}
