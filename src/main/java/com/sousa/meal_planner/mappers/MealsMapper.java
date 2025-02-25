package com.sousa.meal_planner.mappers;

import com.sousa.meal_planner.models.database.CartMO;
import com.sousa.meal_planner.models.database.MealMO;
import com.sousa.meal_planner.models.database.PantryMO;
import com.sousa.meal_planner.models.dto.CartDTO;
import com.sousa.meal_planner.models.dto.MealDTO;
import com.sousa.meal_planner.models.dto.MonthCostDTO;
import com.sousa.meal_planner.models.dto.PantryDTO;
import com.sousa.meal_planner.models.enums.ItemTypeEnum;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    default List<MonthCostDTO> toMonthCostDTOList(Map<Integer, Double> monthCost){
        return List.of(
                MonthCostDTO.builder()
                        .month(1)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(1, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(2)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(2, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(3)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(3, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(4)
                            .cost(BigDecimal.valueOf(monthCost.getOrDefault(4, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(5)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(5, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(6)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(6, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(7)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(7, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(8)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(8, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(9)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(9, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(10)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(10, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(11)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(11, 0.0)))
                        .build(),
                MonthCostDTO.builder()
                        .month(12)
                        .cost(BigDecimal.valueOf(monthCost.getOrDefault(12, 0.0)))
                        .build()
        );

    }
}
