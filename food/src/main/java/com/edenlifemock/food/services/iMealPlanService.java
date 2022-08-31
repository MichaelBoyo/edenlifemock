package com.edenlifemock.food.services;

import com.edenlifemock.clients.food.MealPlanObject;
import com.edenlifemock.clients.food.MealPlanRequest;
import com.edenlifemock.clients.food.WeeklyMealPlanObject;

import java.util.List;


public interface iMealPlanService {
    MealPlanObject orderMealPlan(MealPlanRequest mealPLanRequest);
    WeeklyMealPlanObject orderWeeklyMealPlan(MealPlanRequest mealPLanRequest);

    List<WeeklyMealPlanObject> findAllWeeklyMealPlan();

}
