package com.edenlifemock.clients.food;

public record MealPlanRequest(
        Long mealPlanId,
        String email,
        Long weeklyMealPlanId,
        String mealName,
        String day,
        String time) {
}
