package com.sousa.meal_planner.mappers;

import com.sousa.meal_planner.models.database.CartMO;
import com.sousa.meal_planner.models.database.MealMO;
import com.sousa.meal_planner.models.database.PantryMO;
import com.sousa.meal_planner.models.dto.CartDTO;
import com.sousa.meal_planner.models.dto.MealDTO;
import com.sousa.meal_planner.models.dto.PantryDTO;
import com.sousa.meal_planner.models.enums.ItemTypeEnum;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MealsMapper {

    CartMO toCartMO(CartDTO cartDTO);
    CartDTO toCartDTO(CartMO cartMO);

    MealMO toMealMO(MealDTO mealDTO);
    MealDTO toMealDTO(MealMO mealMO);

    default PantryMO toPantryMO(PantryDTO pantryDTO){
        return PantryMO.builder()
                .id(pantryDTO.getId())
                .itemType(pantryDTO.getItemType())
                .itemName(pantryDTO.getItemName())
                .itemColour(ItemTypeEnum.valueOf(pantryDTO.getItemType().name()).getDefaultColor())
                .itemTotalDosis(pantryDTO.getItemTotalDosis())
                .itemPricePerDosis(pantryDTO.getItemPricePerDosis())
                .build();
    }
    PantryDTO toPantryDTO(PantryMO pantryMO);

    //***********

    List<CartMO> toCartMOList(List<CartDTO> cartDTO);
    List<CartDTO> toCartDTOList(List<CartMO> cartMO);

    List<MealMO> toMealMOList(List<MealDTO> mealDTO);
    List<MealDTO> toMealDTOList(List<MealMO> mealMO);

    List<PantryMO> toPantryMOList(List<PantryDTO> pantryDTO);

    List<PantryDTO> toPantryDTOList(List<PantryMO> pantryMO);
}
