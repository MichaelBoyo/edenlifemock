package com.edenlifemock.food.services;

import com.edenlifemock.food.dtos.MealPLanRequest;
import com.edenlifemock.food.models.DailyMealPlan;
import com.edenlifemock.food.models.MealPlan;

public interface iMealPlanService {
    MealPlan orderMealPlan(MealPLanRequest mealPLanRequest);
    DailyMealPlan orderDailyMealPlan(MealPLanRequest mealPLanRequest);
}
