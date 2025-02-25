package com.sousa.meal_planner.models.enums;

public enum ItemTypeEnum {
    MEAT("#ffd1d1"),
    FISH("#d1e0ff"),
    DAIRY("#fff3d1"),
    VEGETABLE("#d1ffd8"),
    FRUIT("#ffe3d1"),
    GRAIN("#f7ffd1"),
    OTHER("#f0f0f0");

    private final String defaultColor;

    ItemTypeEnum(String defaultColor) {
        this.defaultColor = defaultColor;
    }

    public String getDefaultColor() {
        return defaultColor;
    }
}