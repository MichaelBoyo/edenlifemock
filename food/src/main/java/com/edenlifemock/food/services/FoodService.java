package com.edenlifemock.food.services;


import com.edenlifemock.clients.food.FoodResponse;
import com.edenlifemock.clients.food.MealObject;
import com.edenlifemock.clients.food.UpdateMealRequest;

import java.util.List;

public interface FoodService {

    FoodResponse addMeal(MealObject meal);

    MealObject getMeal(Long id);

    FoodResponse removeMeal(Long mealId);

    List<MealObject> getAllMeals();


    FoodResponse updateMeal(UpdateMealRequest updateLaundryOrderRequest);

}
