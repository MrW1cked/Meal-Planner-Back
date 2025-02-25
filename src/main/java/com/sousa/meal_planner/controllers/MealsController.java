package com.sousa.meal_planner.controllers;

import com.sousa.meal_planner.controllers.contracts.MealsApi;
import com.sousa.meal_planner.models.dto.MealDTO;
import com.sousa.meal_planner.models.dto.PantryDTO;
import com.sousa.meal_planner.services.MealsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MealsController implements MealsApi {

    private final MealsService mealsService;

    @Override
    public ResponseEntity<List<MealDTO>> getMealsByYear(int year) {
        return ResponseEntity.ok(mealsService.getMealsByYear(year));
    }

    @Override
    public ResponseEntity<List<PantryDTO>> getPantryItems() {
        return ResponseEntity.ok(mealsService.getPantryItems());
    }

    @Override
    public ResponseEntity<String> addPantryItem(PantryDTO pantryDTO) {
        mealsService.addPantryItem(pantryDTO);
        return ResponseEntity.ok("Pantry item added successfully");
    }

    @Override
    public ResponseEntity<String> moveMealToNewDate(int id, LocalDate newDate, String mealType) {
        mealsService.updateMealToNewDate(id, newDate, mealType);
        return ResponseEntity.ok("Meals updated successfully");
    }

    @Override
    public ResponseEntity<String> deleteMealFromDay(int id) {
        mealsService.deleteMealById(id);
        return ResponseEntity.ok("Meal deleted successfully");
    }

    @Override
    public ResponseEntity<String> addMeal(int id, LocalDate newDate, String mealType) throws Exception {
        mealsService.addNewMeal(id, newDate, mealType);
        return ResponseEntity.ok("Meal added successfully");
    }
}