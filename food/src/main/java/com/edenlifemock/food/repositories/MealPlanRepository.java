package com.edenlifemock.food.repositories;

import com.edenlifemock.food.models.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlan,Long> {
}
