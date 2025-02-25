package com.sousa.meal_planner.services;

import com.sousa.meal_planner.mappers.MealsMapper;
import com.sousa.meal_planner.models.database.MealMO;
import com.sousa.meal_planner.models.database.PantryMO;
import com.sousa.meal_planner.models.dto.MealDTO;
import com.sousa.meal_planner.models.dto.MonthCostDTO;
import com.sousa.meal_planner.models.dto.PantryDTO;
import com.sousa.meal_planner.models.enums.MealType;
import com.sousa.meal_planner.repositories.MealRepository;
import com.sousa.meal_planner.repositories.PantryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealsService {

    private final MealRepository mealRepository;
    private final PantryRepository pantryRepository;
    //private final CartRepository cartRepository;
    private final MealsMapper mealsMapper;

    public List<PantryDTO> getPantryItems() {
        return mealsMapper.toPantryDTOList(pantryRepository.findAll());
    }

    public void addPantryItem(PantryDTO pantryDTO) {
        pantryRepository.save(mealsMapper.toPantryMO(pantryDTO));
    }

    public void addPantryItems(List<PantryMO> pantryMOList) {
        pantryRepository.saveAll(pantryMOList);
    }

    public void deletePantryItem(Integer id) {
        pantryRepository.deleteById(id);
    }

    public void updatePantryItem(PantryMO pantryMO) {
        pantryRepository.save(pantryMO);
    }

    public void updateMeals(MealDTO mealDTO) {}

    private void deleteAllMealsForTheDay(LocalDate date) {
        mealRepository.deleteAllByDate(date);
    }

    public List<MealMO> getMeals() {
        return mealRepository.findAll();
    }

    public void addMeal(MealMO mealMO) {
        mealRepository.save(mealMO);
    }

    public void deleteMeal(List<Integer> ids) {
        mealRepository.deleteAllById(ids);
    }

    public List<MealDTO> getMealsByYear(int year) {
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        LocalDate lastDayOfYear = LocalDate.of(year, 12, 31);
        return mealsMapper.toMealDTOList(mealRepository.findAllByDateBetween(firstDayOfYear, lastDayOfYear));
    }

    public void updateMealToNewDate(int id, LocalDate newDate, String mealType) {
        MealMO mealMO = mealRepository.findById(id).orElseThrow();
        mealMO.setDate(newDate);
        mealMO.setMealType(MealType.valueOf(mealType));
        mealRepository.save(mealMO);
    }

    public void deleteMealById(int id) {
        MealMO mealMO = mealRepository.findById(id).orElseThrow();
        mealRepository.delete(mealMO);
        Optional<PantryMO> pantryMO = pantryRepository.findByItemName(mealMO.getItemName());

        if (pantryMO.isPresent()) {
            pantryMO.get().setItemTotalDosis(pantryMO.get().getItemTotalDosis() + 1);
            pantryRepository.save(pantryMO.get());
        } else {
            PantryMO newPantryItem = PantryMO.builder()
                    .itemType(mealMO.getItemType())
                    .itemName(mealMO.getItemName())
                    .itemColour(mealMO.getItemColour())
                    .itemPricePerDosis(mealMO.getItemPrice())
                    .itemTotalDosis(1)
                    .build();
            pantryRepository.save(newPantryItem);
        }

        pantryRepository.findAll();
    }

    public void addNewMeal(int id, LocalDate newDate, String mealType) throws Exception {
        PantryMO pantryMO = pantryRepository.findById(id).orElseThrow();

        MealMO newMeal = MealMO.builder()
                .date(newDate)
                .dayOfWeek(newDate.getDayOfWeek())
                .mealType(MealType.valueOf(mealType))
                .itemType(pantryMO.getItemType())
                .itemName(pantryMO.getItemName())
                .itemColour(pantryMO.getItemType().getDefaultColor())
                .itemPrice(pantryMO.getItemPricePerDosis())
                .build();
        mealRepository.save(newMeal);
        if (pantryMO.getItemTotalDosis() == 1) {
            pantryRepository.delete(pantryMO);
        } else {
            pantryMO.setItemTotalDosis(pantryMO.getItemTotalDosis() - 1);
            pantryRepository.save(pantryMO);
        }

    }

    public List<MonthCostDTO> getMonthTotalCost(int year) {
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        LocalDate lastDayOfYear = LocalDate.of(year, 12, 31);
        List<MealMO> meals = mealRepository.findAllByDateBetween(firstDayOfYear, lastDayOfYear);
        Map<Integer, Double> monthCost = new HashMap<>();

        for (MealMO meal : meals) {
            int month = meal.getDate().getMonthValue();
            BigDecimal cost = meal.getItemPrice();
            if (monthCost.containsKey(month)) {
                monthCost.put(month, monthCost.get(month) + cost.doubleValue());
            } else {
                monthCost.put(month, cost.doubleValue());
            }
        }
        return mealsMapper.toMonthCostDTOList(monthCost);
    }
}
