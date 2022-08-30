package com.edenlifemock.food.services;

import com.edenlifemock.food.dtos.MealPLanRequest;
import com.edenlifemock.food.models.Day;
import com.edenlifemock.food.models.MealPlan;
import com.edenlifemock.food.models.Time;
import com.edenlifemock.food.repositories.MealPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.edenlifemock.food.models.Day.*;
import static com.edenlifemock.food.models.Time.*;

@Service
@AllArgsConstructor
public class MealPlanService implements iMealPlanService {
    private final FoodService foodService;
    private final MealPlanRepository mealPlanRepository;

    @Override
    public MealPlan orderMealPlan(MealPLanRequest mealPLanRequest) {
        var meal = foodService.findMealByMealName(mealPLanRequest.mealName());
        return mealPlanRepository.saveAndFlush(MealPlan.builder()
                .meal(meal)
                .day(getDay(mealPLanRequest.day()))
                .time(getTime(mealPLanRequest.time()))
                .build()
        );
    }

    private Time getTime(String time1) {
        Time time;
        switch (time1) {
            case "12" -> time = PM_12;
            case "4" -> time = PM_4;
            default -> time = AM_9;
        }
        return time;
    }

    private Day getDay(String day_) {
        Day day;
        switch (day_) {
            case "Tue" -> day = TUE;
            case "Wed" -> day = WED;
            case "Thur" -> day = THUR;
            case "Fri" -> day = FRI;
            case "Sat" -> day = SAT;
            case "Sun" -> day = SUN;
            default -> day = MON;
        }
        return day;
    }
}
