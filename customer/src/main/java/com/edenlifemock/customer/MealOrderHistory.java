package com.edenlifemock.customer;

import com.edenlifemock.clients.food.MealObject;
import com.edenlifemock.clients.food.WeeklyMealPlanObject;
import lombok.Data;


import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@Data
public class MealOrderHistory {
    private MealObject mealOrderHistory;

    private WeeklyMealPlanObject weeklyMealPlanOrderHistory;
}
