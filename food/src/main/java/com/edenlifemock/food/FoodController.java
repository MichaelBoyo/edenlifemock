package com.edenlifemock.food;

import com.edenlifemock.clients.food.FoodResponse;
import com.edenlifemock.clients.food.MealObject;
import com.edenlifemock.clients.food.UpdateMealRequest;

import com.edenlifemock.food.models.Meal;
import com.edenlifemock.food.services.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/meal")
@AllArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @PostMapping
    public FoodResponse addMeal(@RequestBody MealObject mealObject) {
        return foodService.addMeal(mealObject);
    }

//    @GetMapping("/{id}")
//    public MealObject getMeal(@PathVariable Long id) {
//        return foodService.getMeal(id);
//    }
    @GetMapping("/{mealName}")
    FoodResponse getMealByName(@PathVariable("mealName") String mealName){
        return foodService.getMealByName(mealName);
    }

    @DeleteMapping("/{mealId}")
    public FoodResponse removeMeal(@PathVariable Long mealId) {
        return foodService.removeMeal(mealId);
    }

    @GetMapping("all-meals")
    List<MealObject> getAllMeals() {
        return foodService.getAllMeals();
    }

    @PatchMapping
    FoodResponse updateMeal(@RequestBody UpdateMealRequest updateMealRequest) {
        return foodService.updateMeal(updateMealRequest);
    }
}

