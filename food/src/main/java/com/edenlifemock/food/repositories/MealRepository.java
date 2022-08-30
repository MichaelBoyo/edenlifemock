package com.edenlifemock.food.repositories;

import com.edenlifemock.food.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal,Long> {
    Meal findMealByMealName(String mealName);
}
