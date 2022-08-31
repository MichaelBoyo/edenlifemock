package com.edenlifemock.clients.food;



public record MealObject(
        Long mealId,
        String mealName,
        String type,
        String desc,
        Double price,
        Status status
) {
}
