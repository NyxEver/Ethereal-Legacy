package com.example.EtherealLegacy.Object;

/**
 * 武器等级枚举
 * 定义了游戏中武器的等级体系
 */
public enum WeaponGrade {
    MORTAL("凡器", 10, 1.0),      // 凡器级：基础伤害10，倍率1.0
    SPIRIT("灵器", 20, 1.2),      // 灵器级：基础伤害20，倍率1.2
    MYSTIC("法器", 35, 1.5),      // 法器级：基础伤害35，倍率1.5
    EARTH("地器", 55, 2.0),       // 地器级：基础伤害55，倍率2.0
    HEAVEN("天器", 80, 2.5),      // 天器级：基础伤害80，倍率2.5
    IMMORTAL("仙器", 120, 3.0),   // 仙器级：基础伤害120，倍率3.0
    DIVINE("神器", 200, 4.0);     // 神器级：基础伤害200，倍率4.0

    private final String name;
    private final int baseDamage;
    private final double damageMultiplier;

    WeaponGrade(String name, int baseDamage, double damageMultiplier) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.damageMultiplier = damageMultiplier;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    @Override
    public String toString() {
        return name;
    }
}