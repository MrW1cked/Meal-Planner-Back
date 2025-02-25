package com.sousa.meal_planner.models.dto;

import com.sousa.meal_planner.models.enums.ItemTypeEnum;
import com.sousa.meal_planner.models.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealDTO {

    private Integer id;
    private LocalDate date;
    private DayOfWeek dayOfWeek;
    private MealType mealType;
    private ItemTypeEnum itemType;
    private String itemName;
    private String itemColour;
    private BigDecimal itemPrice;

    public void setItemType(ItemTypeEnum itemType) {
        this.itemType = itemType;
        this.itemColour = (itemType != null) ? itemType.getDefaultColor() : null;
    }
}
