package com.example.EtherealLegacy.Object;

public enum WeaponGrade {
    MORTAL("凡器", "凡人可用的普通武器", 1.0, 10),
    SPIRIT("灵器", "需要灵力方可驾驭", 2.0, 35),
    MYSTIC("法器", "需要法力支持的神兵", 3.0, 50),
    EARTH("地器", "蕴含地脉之力", 4.0, 70),
    HEAVEN("天器", "承载天道之力", 5.0, 90),
    IMMORTAL("仙器", "开天辟地之时之第一序列武器", 6.0, 120);

    private final String name;
    private final String description;
    private final double damageMultiplier;
    private final int baseDamage;

    WeaponGrade(String name, String description, double damageMultiplier, int baseDamage) {
        this.name = name;
        this.description = description;
        this.damageMultiplier = damageMultiplier;
        this.baseDamage = baseDamage;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getDamageMultiplier() { return damageMultiplier; }
    public int getBaseDamage() { return baseDamage; }
} 