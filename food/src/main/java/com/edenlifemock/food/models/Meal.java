package com.edenlifemock.food.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    @Id
    @SequenceGenerator(
            name = "meal_id_sequence",
            sequenceName = "meal_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meal_id_sequence"
    )
    private Long mealId;
    private String mealName;
    private String type;
    private String description;
    private Double price;
}