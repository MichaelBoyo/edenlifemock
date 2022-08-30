package com.edenlifemock.food.controller;

import com.edenlifemock.clients.food.FoodResponse;
import com.edenlifemock.clients.food.MealObject;
import com.edenlifemock.clients.food.UpdateMealRequest;

import com.edenlifemock.food.models.Meal;
import com.edenlifemock.food.services.FoodService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/meal")
@AllArgsConstructor
@Slf4j
public class FoodController {
    private final FoodService foodService;

    @PostMapping
    public FoodResponse addMeal(@RequestBody MealObject mealObject) {
        log.info("adding meal {}",mealObject);
        return foodService.addMeal(mealObject);
    }

    @GetMapping("/{mealName}")
    FoodResponse getMealByName(@PathVariable("mealName") String mealName){
        log.info("fetching meal data for {}",mealName);
        return foodService.getMealByName(mealName);
    }

    @DeleteMapping("/{mealId}")
    public FoodResponse removeMeal(@PathVariable Long mealId) {
        log.info("removing meal {}",mealId);
        return foodService.removeMeal(mealId);
    }

    @GetMapping("all-meals")
    List<MealObject> getAllMeals() {
        log.info("fetching all meals ");
        return foodService.getAllMeals();
    }

    @PatchMapping
    FoodResponse updateMeal(@RequestBody UpdateMealRequest updateMealRequest) {
        log.info("updating meal {}",updateMealRequest);
        return foodService.updateMeal(updateMealRequest);
    }
}

