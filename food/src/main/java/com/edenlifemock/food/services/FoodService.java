package com.edenlifemock.food.services;


import com.edenlifemock.food.MealRepository;
import com.edenlifemock.food.dtos.FoodResponse;
import com.edenlifemock.food.dtos.MealRequest;
import com.edenlifemock.food.dtos.UpdateMealRequest;
import com.edenlifemock.food.models.Meal;

import java.util.List;

public interface FoodService {

    FoodResponse addMeal(Meal meal);
    Meal getMeal(Long id);
    FoodResponse removeMeal(Long mealId);
    List<Meal> getAllMeals();


    FoodResponse orderMeal(String mealName);

    FoodResponse updateMeal(UpdateMealRequest updateLaundryOrderRequest);

}
