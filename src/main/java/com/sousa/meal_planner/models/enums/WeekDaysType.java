package com.sousa.meal_planner.models.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WeekDaysType {
  MONDAY(1),
  TUESDAY(2),
  WEDNESDAY(3),
  THURSDAY(4),
  FRIDAY(5),
  SATURDAY(6),
  SUNDAY(7);

  private static final Map<Integer, WeekDaysType> map;

  static {
    map =
        Arrays.stream(WeekDaysType.values())
            .collect(Collectors.toMap(WeekDaysType::getGetWeekDay, Function.identity()));
  }

  private final Integer weekDay;

  WeekDaysType(Integer weekDay) {
    this.weekDay = weekDay;
  }

  public static String getWeekDayById(Integer weekDay) throws Exception {
    if (weekDay == null) {
      return null;
    }

    WeekDaysType weekDayName = map.get(weekDay);
    if (weekDayName == null) {
      throw new Exception("Week day not found");
    }

    return weekDayName.toString().toLowerCase();
  }

  public static Integer getWeekDayByName(String weekDay) throws Exception {
    if (weekDay == null) {
      return null;
    }

    for (WeekDaysType weekDayName : WeekDaysType.values()) {
      if (weekDayName.toString().equalsIgnoreCase(weekDay)) {
        return weekDayName.getGetWeekDay();
      }
    }

    throw new Exception("Week day not found");
  }

  public Integer getGetWeekDay() {
    return weekDay;
  }

  public boolean is(int weekDay) {
    return this.weekDay == weekDay;
  }
}
