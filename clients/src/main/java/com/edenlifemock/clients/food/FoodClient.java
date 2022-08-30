package com.edenlifemock.clients.food;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("food")
public interface FoodClient {


//    @GetMapping("api/v1/meal/{id}")
//    MealObject getMeal(@PathVariable("id") Long id);

    @DeleteMapping("api/v1/meal/{mealId}")
    FoodResponse removeMeal(@PathVariable("mealId") Long mealId);

    @GetMapping("api/v1/meal/all-meals")
    List<MealObject> getAllMeals();




    @PatchMapping("api/v1/meal")
    FoodResponse updateMeal(@RequestBody UpdateMealRequest updateMealRequest);
    @GetMapping("api/v1/meal/{mealName}")
    FoodResponse getMealByName(@PathVariable("mealName") String mealName);
}
