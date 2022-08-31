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
public class WeeklyMealPlan {
    @Id
    @SequenceGenerator(
            name = "weekly_meal_plan_id_sequence",
            sequenceName = "weekly_meal_plan_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "weekly_meal_plan_id_sequence"
    )
    private Long weeklyMealPlanId;
    @ElementCollection
    private List<MealPlan> mealPLans;
}