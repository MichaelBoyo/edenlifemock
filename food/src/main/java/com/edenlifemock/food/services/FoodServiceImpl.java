package com.edenlifemock.food.services;

import com.edenlifemock.clients.food.FoodResponse;
import com.edenlifemock.clients.food.MealObject;
import com.edenlifemock.clients.food.UpdateMealRequest;
import com.edenlifemock.food.exceptions.MealExistException;
import com.edenlifemock.food.exceptions.MealNotFoundException;
import com.edenlifemock.food.repositories.MealRepository;

import com.edenlifemock.food.models.Meal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final MealRepository mealRepository;

    @Override
    public FoodResponse addMeal(MealObject mealObject) {
        isExist(mealObject.mealName());
        log.info("adding meal {}",mealObject);
        mealRepository.saveAndFlush(Meal.builder()
                .mealName(mealObject.mealName())
                .description(mealObject.desc())
                .type(mealObject.type())
                .price(mealObject.price())
                .build());
        return new FoodResponse("meal " + mealObject.mealName() + "added successfully");
    }

    private void isExist(String mealName) {
        if (mealRepository.findAll().stream().anyMatch(meal -> meal.getMealName().equals(mealName)))
            throw new MealExistException("meal with " + mealName + " already exist");
    }

    @Override
    public MealObject getMeal(Long id) {
        return mealRepository.findById(id).map(meal -> new MealObject(meal.getMealId(), meal.getMealName(),
                meal.getType(), meal.getDescription(), meal.getPrice())).orElseThrow(
                () -> new MealNotFoundException("meal with id {" + id + "} not found")
        );
    }

    @Override
    public FoodResponse removeMeal(Long mealId) {
        mealRepository.deleteById(mealId);
        return new FoodResponse("meal with id " + mealId + " successfully deleted");
    }

    @Override
    public List<MealObject> getAllMeals() {
        return mealRepository.findAll().stream().map(meal -> new MealObject(meal.getMealId(), meal.getMealName(),
                meal.getType(), meal.getDescription(), meal.getPrice())).toList();
    }


    @Override
    public FoodResponse updateMeal(UpdateMealRequest updateMealRequest) {
        Meal meal = mealRepository.getById(updateMealRequest.mealId());
        if (isNotNullOrEmpty(updateMealRequest.mealName())) meal.setMealName(updateMealRequest.mealName());
        if (isNotNullOrEmpty(updateMealRequest.desc())) meal.setDescription(updateMealRequest.desc());
        if (isNotNullOrEmpty(updateMealRequest.type())) meal.setType(updateMealRequest.type());
        if (isNotNullOrEmpty(String.valueOf(updateMealRequest.price()))) meal.setPrice(updateMealRequest.price());
        return new FoodResponse("updated successfully");
    }

    @Override
    public FoodResponse getMealByName(String mealName) {
        var meal = mealRepository.findMealByMealName(mealName);
        MealObject mealObject = new MealObject(meal.getMealId(),meal.getMealName(),
                meal.getType(),meal.getDescription(), meal.getPrice());
        return new FoodResponse(mealObject+ " ordered successfully");
    }

    @Override
    public Meal findMealByMealName(String mealName) {
        return mealRepository.findMealByMealName(mealName);
    }

    private boolean isNotNullOrEmpty(String value) {
        return value != null && !value.equals("");
    }
}
