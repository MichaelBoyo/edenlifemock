package com.edenlifemock.food.services;

public class MealExistException extends RuntimeException {
    public MealExistException(String message) {
        super(message);
    }
}
