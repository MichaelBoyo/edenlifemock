package com.edenlifemock.food.repositories;

import com.edenlifemock.food.models.WeeklyMealPlan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyMealPlanRepository extends JpaRepository<WeeklyMealPlan, Long> {

}

