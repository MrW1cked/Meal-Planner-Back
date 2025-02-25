package com.sousa.meal_planner.models.database;

import com.sousa.meal_planner.models.enums.ItemTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shopping_list")
public class CartMO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_list_seq")
    @SequenceGenerator(name = "shopping_list_seq", sequenceName = "shopping_list_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "itemType")
    private ItemTypeEnum itemType;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemColour", length = 50)
    private String itemColour;
}