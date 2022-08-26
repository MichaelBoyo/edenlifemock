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
    public FoodResponse addMeal(MealObject mealObject) {
        return foodService.addMeal(mealObject);
    }

    @GetMapping
    public MealObject getMeal(Long id) {
        return foodService.getMeal(id);
    }

    @DeleteMapping
    public FoodResponse removeMeal(Long mealId) {
        return foodService.removeMeal(mealId);
    }

    @GetMapping("all-meals")
    List<MealObject> getAllMeals() {
        return foodService.getAllMeals();
    }

    @PatchMapping
    FoodResponse updateMeal(UpdateMealRequest updateMealRequest) {
        return foodService.updateMeal(updateMealRequest);
    }
}

