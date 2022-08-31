package com.edenlifemock.food.controller;

import com.edenlifemock.clients.food.MealPlanObject;
import com.edenlifemock.clients.food.MealPlanRequest;
import com.edenlifemock.clients.food.WeeklyMealPlanObject;

import com.edenlifemock.food.services.iMealPlanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/meal-plan")
@Slf4j
@AllArgsConstructor
public class MealPLanController {
    private final iMealPlanService mealPlanService;

    @GetMapping("/order")
    public MealPlanObject orderMealPlan(@RequestBody MealPlanRequest request) {
        log.info("ordering meal plan {}",request);
        return mealPlanService.orderMealPlan(request);
    }

    @PostMapping("/order-daily")
    public WeeklyMealPlanObject orderWeeklyMealPlan(@RequestBody MealPlanRequest request) {
        log.info("ordering weekly meal plan {}",request);
        return mealPlanService.orderWeeklyMealPlan(request);
    }
    @GetMapping("/all-weekly-plans")
    public List<WeeklyMealPlanObject> findAllWeeklyMealPlan() {
        log.info("finding all weekly meal plan");
        return mealPlanService.findAllWeeklyMealPlan();
    }

}
