package com.edenlifemock.clients.food;

import java.math.BigDecimal;

public record MealObject(
        Long mealId,
         String mealName,
         String type,
         String desc,
         Double price
) {
}
