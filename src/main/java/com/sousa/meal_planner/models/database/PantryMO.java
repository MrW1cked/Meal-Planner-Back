package com.sousa.meal_planner.models.database;

import com.sousa.meal_planner.models.enums.ItemTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pantry")
public class PantryMO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pantry_seq")
    @SequenceGenerator(name = "pantry_seq", sequenceName = "pantry_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "itemType")
    private ItemTypeEnum itemType;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemColour", length = 50)
    private String itemColour;

    @Column(name = "itemPricePerDosis", precision = 10, scale = 2)
    private BigDecimal itemPricePerDosis;

    @Column(name = "itemTotalDosis")
    private Integer itemTotalDosis;
}