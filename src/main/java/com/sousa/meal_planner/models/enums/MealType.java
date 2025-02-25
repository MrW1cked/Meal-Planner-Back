package com.sousa.meal_planner.models.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MealType {
  BREAKFAST("BREAKFAST"),
    LUNCH("LUNCH"),
    DINNER("DINNER"),
    SNACK("SNACK");


  private static final Map<String, MealType> map;

  static {
    map =
        Arrays.stream(MealType.values())
            .collect(Collectors.toMap(MealType::getMealType, Function.identity()));
  }

  private final String mealType;

  MealType(String mealType) {
    this.mealType = mealType;
  }

  public String getMealType() {
    return mealType;
  }

}
