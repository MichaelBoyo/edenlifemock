package com.edenlifemock.clients.food;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("food")
public interface FoodClient {

    @DeleteMapping("api/v1/meal/{mealId}")
    FoodResponse removeMeal(@PathVariable("mealId") Long mealId);

    @GetMapping("api/v1/meal/all-meals")
    List<MealObject> getAllMeals();

    @GetMapping("api/v1/meal-plan/order")
    MealPlanObject orderMealPlan(@RequestBody MealPlanRequest request);

    @PostMapping("api/v1/meal-plan/order-daily")
    WeeklyMealPlanObject orderWeeklyMealPlan(@RequestBody MealPlanRequest request);

//    @GetMapping("api/v1/meal-plan/all-weekly-plans")
//    List<WeeklyMealPlanObject> findAllWeeklyMealPlan();

    @PatchMapping("api/v1/meal")
    FoodResponse updateMeal(@RequestBody UpdateMealRequest updateMealRequest);
    @GetMapping("api/v1/meal/{mealName}")
    FoodResponse getMealByName(@PathVariable("mealName") String mealName);
}
