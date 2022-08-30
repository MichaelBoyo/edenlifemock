package com.edenlifemock.food.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyMealPlan {
    @Id
    @SequenceGenerator(
            name = "daily_meal_plan_id_sequence",
            sequenceName = "meal_plan_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "daily_meal_plan_id_sequence"
    )
    private Long dailyMealPlanId;
    @ElementCollection
    private List<MealPlan> dailyMealPLan;
}