package com.edenlifemock.food.exceptions;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(String message) {
        super(message);
    }
}
