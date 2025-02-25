package com.sousa.meal_planner.models.database;

import com.sousa.meal_planner.models.enums.ItemTypeEnum;
import com.sousa.meal_planner.models.enums.MealType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MEALS")
public class MealMO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meals_seq")
    @SequenceGenerator(name = "meals_seq", sequenceName = "meals_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "dayOfWeek")
    private DayOfWeek dayOfWeek;

    @Enumerated(EnumType.STRING)
    @Column(name = "mealType")
    private MealType mealType;

    @Enumerated(EnumType.STRING)
    @Column(name = "itemType")
    private ItemTypeEnum itemType;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemColour", length = 50)
    private String itemColour;

    @Column(name = "itemPrice", precision = 10, scale = 2)
    private BigDecimal itemPrice;
}
