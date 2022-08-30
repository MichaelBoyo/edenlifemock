package com.edenlifemock.food.models;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MealPlan {
    @Id
    @SequenceGenerator(
            name = "meal_plan_id_sequence",
            sequenceName = "meal_plan_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meal_plan_id_sequence"
    )
    private Long mealPlanId;
    @ManyToOne
    private Meal meal;
    private Day day;
    private Time time;
}
