package com.edenlifemock.food.exceptions;

public class MealExistException extends RuntimeException {
    public MealExistException(String message) {
        super(message);
    }
}
