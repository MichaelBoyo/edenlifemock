package com.edenlifemock.food.services;

import com.edenlifemock.clients.food.MealPlanObject;
import com.edenlifemock.clients.food.MealPlanRequest;
import com.edenlifemock.clients.food.WeeklyMealPlanObject;


import com.edenlifemock.food.exceptions.MealPlanNotFoundException;
import com.edenlifemock.food.exceptions.WeeklyMEalPlanNotFoundException;
import com.edenlifemock.food.models.*;
import com.edenlifemock.food.repositories.MealPlanRepository;
import com.edenlifemock.food.repositories.WeeklyMealPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import static com.edenlifemock.food.models.Day.*;
import static com.edenlifemock.food.models.Time.*;


@Service
@AllArgsConstructor
public class MealPlanService implements iMealPlanService {
    private final FoodService foodService;
    private final MealPlanRepository mealPlanRepository;
    private final WeeklyMealPlanRepository weeklyMEalPlanRepository;

    @Override
    public MealPlanObject orderMealPlan(MealPlanRequest mealPLanRequest) {
        mealPlanRepository.saveAndFlush(MealPlan.builder()
                .meal(foodService.findMealByMealName(mealPLanRequest.mealName()))
                .day(getDay(mealPLanRequest.day()))
                .time(getTime(mealPLanRequest.time()))
                .build()
        );
        return new MealPlanObject(mealPLanRequest.mealName(), mealPLanRequest.day(), mealPLanRequest.time());
    }



    @Override
    public WeeklyMealPlanObject orderWeeklyMealPlan(MealPlanRequest mealPLanRequest) {
        WeeklyMealPlan weeklyMEalPlan;
        if (mealPLanRequest.weeklyMealPlanId() == 0) {
            weeklyMEalPlan = WeeklyMealPlan.builder()
                    .mealPLans(new ArrayList<>())
                    .build();
        } else {
            weeklyMEalPlan = weeklyMEalPlanRepository.
                    findById(mealPLanRequest.weeklyMealPlanId()).orElseThrow(
                            () -> new WeeklyMEalPlanNotFoundException("no weekly meal plan with that id")
                    );
        }
        MealPlan mealPlan_ = MealPlan.builder()
                .meal(foodService.findMealByMealName(mealPLanRequest.mealName()))
                .day(getDay(mealPLanRequest.day()))
                .time(getTime(mealPLanRequest.time()))
                .build();
        mealPlanRepository.saveAndFlush(mealPlan_);

        weeklyMEalPlan.getMealPLans().add(mealPlan_);
        weeklyMEalPlanRepository.saveAndFlush(weeklyMEalPlan);
        return new WeeklyMealPlanObject(
                weeklyMEalPlan.getMealPLans().stream().map(mealPlan ->
                        new MealPlanObject(mealPlan.getMeal().getMealName(),
                                mealPlan.getDay().name(), mealPlan.getTime().name())).toList()
        );
    }

    @Override
    public List<WeeklyMealPlanObject> findAllWeeklyMealPlan() {
        return weeklyMEalPlanRepository.findAll().stream().map(plan -> new WeeklyMealPlanObject(
                plan.getMealPLans().stream().map(mealPlan -> new MealPlanObject(mealPlan.getMeal().getMealName(),
                        mealPlan.getDay().name(), mealPlan.getTime().name())).toList()
        )).toList();
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
