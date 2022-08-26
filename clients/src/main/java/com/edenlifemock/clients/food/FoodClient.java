package com.edenlifemock.clients.food;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("food")
public interface FoodClient {

    @PostMapping
    FoodResponse addMeal(MealObject mealObject);

    @GetMapping
    MealObject getMeal(Long id);

    @DeleteMapping
    FoodResponse removeMeal(Long mealId);

    @GetMapping("all-meals")
    List<MealObject> getAllMeals();


    @GetMapping("order-meal")
    FoodResponse orderMeal(String mealName);

    @PatchMapping
    FoodResponse updateMeal(UpdateMealRequest updateMealRequest);
}
