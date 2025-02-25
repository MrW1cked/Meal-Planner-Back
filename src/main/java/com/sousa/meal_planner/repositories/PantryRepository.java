package com.sousa.meal_planner.repositories;

import com.sousa.meal_planner.models.database.PantryMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PantryRepository extends JpaRepository<PantryMO, Integer> {

    Optional<PantryMO> findByItemName(String itemName);
}
