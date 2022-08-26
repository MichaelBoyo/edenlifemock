package com.edenlifemock.clients.food;

import java.math.BigDecimal;

public record UpdateMealRequest(Long mealId,
         String mealName,
         String type,
         String desc,
         BigDecimal price) {
}
