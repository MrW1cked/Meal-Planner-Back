package com.sousa.meal_planner.repositories;

import com.sousa.meal_planner.models.database.CartMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartMO, Integer> {
}
