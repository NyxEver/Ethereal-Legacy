package com.example.EtherealLegacy.Object;

public enum MaterialGrade {
    LOW("低级", "品质一般的材料"),
    MEDIUM("中级", "品质不错的材料"),
    MEDIUM_HIGH("中上级", "品质优良的材料"),
    HIGH("高级", "品质极佳的材料"),
    PERFECT("完美级", "品质完美的材料");

    private final String name;
    private final String description;

    MaterialGrade(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getDifficulty() {
        return switch (this) {
            case LOW -> 1.0;
            case MEDIUM -> 2.0;
            case MEDIUM_HIGH -> 2.5;
            case HIGH -> 3.0;
            case PERFECT -> 4.0;
        };
    }
} 