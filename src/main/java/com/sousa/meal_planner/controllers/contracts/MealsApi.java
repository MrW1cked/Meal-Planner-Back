package com.sousa.meal_planner.controllers.contracts;

import com.sousa.meal_planner.models.dto.MealDTO;
import com.sousa.meal_planner.models.dto.MonthCostDTO;
import com.sousa.meal_planner.models.dto.PantryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/v1/meals")
public interface MealsApi {

    @Operation(summary = "Get all meals by year", operationId = "getMealsByYear", tags = "meals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meals retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealDTO.class))),
            @ApiResponse(responseCode = "404", description = "No meals found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/year/{year}/all")
    ResponseEntity<List<MealDTO>> getMealsByYear(
            @PathVariable int year);

    //get all meals from Pantry:
   @Operation(summary = "Get all pantry items", operationId = "getPantryItems", tags = "meals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pantry items retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealDTO.class))),
            @ApiResponse(responseCode = "404", description = "No pantry items found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/pantry/all")
    ResponseEntity<List<PantryDTO>> getPantryItems();

   //add a new meal for Pantry:
    @Operation(summary = "Add a new meal to the pantry", operationId = "addPantryItem", tags = "meals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pantry item added successfully", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/pantry/add")
    ResponseEntity<String> addPantryItem(
            @RequestBody PantryDTO pantryDTO);

    @Operation(summary = "Move a meal to a new date", operationId = "moveMealToNewDate", tags = "meals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal updated successfully", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Meal not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/update/{id}")
    ResponseEntity<String> moveMealToNewDate(
            @PathVariable int id,
            @RequestParam LocalDate newDate,
            @RequestParam String mealType);

    @Operation(summary = "Delete a meal from a day", operationId = "deleteMealFromDay", tags = "meals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal deleted successfully", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Meal not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteMealFromDay(@PathVariable int id);

    @Operation(summary = "Add a new meal based on a pantry item", operationId = "addMeal", tags = "meals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal added successfully", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/add/{id}")
    ResponseEntity<String> addMeal(
            @PathVariable int id,
            @RequestParam LocalDate newDate,
            @RequestParam String mealType) throws Exception;

    @Operation(summary = "Get month total cost", operationId = "getMonthTotalCost", tags = "meals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Month total cost retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealDTO.class))),
            @ApiResponse(responseCode = "404", description = "No month total cost found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/year/{year}")
    ResponseEntity<List<MonthCostDTO>> getMonthTotalCost(
            @PathVariable int year);
}