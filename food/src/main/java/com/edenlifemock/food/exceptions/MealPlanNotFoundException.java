package com.edenlifemock.food.exceptions;

public class MealPlanNotFoundException extends RuntimeException{
    public MealPlanNotFoundException(String message) {
        super(message);
    }
}
