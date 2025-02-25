package com.sousa.meal_planner.repositories;

import com.sousa.meal_planner.models.database.MealMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<MealMO, Integer> {

    @Query("SELECT m FROM MealMO m WHERE m.date BETWEEN :firstDayOfMonth AND :lastDayOfMonth")
    List<MealMO> findAllByDateBetween(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth);

    @Modifying
    @Query("DELETE FROM MealMO m WHERE m.date = :date")
    void deleteAllByDate(LocalDate date);

}