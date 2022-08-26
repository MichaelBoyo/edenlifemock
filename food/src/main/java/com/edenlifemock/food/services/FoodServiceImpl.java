package com.edenlifemock.food.services;

import com.edenlifemock.food.MealRepository;
import com.edenlifemock.food.dtos.FoodResponse;
import com.edenlifemock.food.dtos.MealRequest;
import com.edenlifemock.food.dtos.UpdateMealRequest;
import com.edenlifemock.food.models.Meal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class FoodServiceImpl implements FoodService{
    private final MealRepository mealRepository;

    @Override
    public FoodResponse addMeal(Meal meal) {
        isExist(meal.getMealName());
        mealRepository.saveAndFlush(meal);
        return new FoodResponse("meal "+ meal.getMealName()+ "added successfully");
    }

    private void isExist(String mealName) {
      var anyMatch =  mealRepository.findAll().stream().anyMatch(meal -> meal.getMealName().equals(mealName));
      if(anyMatch)throw new MealExistException("meal with "+mealName+" already exist");

    }

    @Override
    public Meal getMeal(Long id) {
        return mealRepository.findById(id).orElseThrow(
                ()-> new MealNotFoundException("meal with id {"+id+"} not found")
        );
    }

    @Override
    public FoodResponse removeMeal(Long mealId) {
        mealRepository.deleteById(mealId);
        return new FoodResponse("meal with id "+ mealId+ " successfully deleted");
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
    private Meal getMealByName(String name){
        return mealRepository.findMealByMealName(name);
    }

    @Override
    public FoodResponse orderMeal(String mealName) {
        Meal meal = getMealByName(mealName);

        return null;
    }

    @Override
    public FoodResponse updateMeal(UpdateMealRequest updateLaundryOrderRequest) {
        return null;
    }
}
