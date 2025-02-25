package com.sousa.meal_planner.models.dto;

import com.sousa.meal_planner.models.enums.ItemTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {

    private Integer id;

    private ItemTypeEnum itemType;

    private String itemName;

    private String itemColour;


    public void setItemType(ItemTypeEnum itemType) {
        this.itemType = itemType;
        this.itemColour = (itemType != null) ? itemType.getDefaultColor() : null;
    }
}