package com.example.EtherealLegacy.Object;

public enum ItemGrade {
    MORTAL_IRON("凡铁", 1.0),
    SPIRIT_MATERIAL("灵材", 2.0),
    MYSTIC_GOLD("玄金", 3.0),
    EARTH_TREASURE("地宝", 4.0),
    HEAVEN_CRYSTAL("天晶", 5.0),
    IMMORTAL_JADE("仙玉", 6.0),
    DIVINE_SOURCE("神源", 7.0);

    private final String name;
    private final double multiplier;

    ItemGrade(String name, double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    public String getName() { return name; }
    public double getMultiplier() { return multiplier; }
} 