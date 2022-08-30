package com.edenlifemock.food.dtos;


public record MealPLanRequest(
        String mealName,
        String day,
        String time) {
}
