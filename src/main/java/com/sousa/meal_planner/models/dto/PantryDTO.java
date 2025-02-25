package com.sousa.meal_planner.models.dto;

import com.sousa.meal_planner.models.enums.ItemTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PantryDTO {

    private Integer id;
    private ItemTypeEnum itemType;
    private String itemName;
    private String itemColour;
    private BigDecimal itemPricePerDosis;
    private Integer itemTotalDosis;

    public void setItemType(ItemTypeEnum itemType) {
        this.itemType = itemType;
        this.itemColour = (itemType != null) ? itemType.getDefaultColor() : null;
    }
}