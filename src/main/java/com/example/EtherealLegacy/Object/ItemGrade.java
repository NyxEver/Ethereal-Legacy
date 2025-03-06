package com.example.EtherealLegacy.Object;

/**
  物品等级枚举
  定义了游戏中所有物品的等级体系
  从凡铁到神源，共七个等级
 */
public enum ItemGrade {
    MORTAL_IRON("凡铁", 1.0),
    SPIRIT_MATERIAL("灵材", 2.0),
    MYSTIC_GOLD("玄金", 3.0),
    EARTH_TREASURE("地宝", 4.0),
    HEAVEN_CRYSTAL("天晶", 5.0),
    IMMORTAL_JADE("仙玉", 6.0),
    DIVINE_SOURCE("神源", 7.0);

    // 等级名称 
    private final String name;
    // 等级倍率，用于计算物品效果 
    private final double multiplier;

    /**
      物品等级构造函数
      @param name 等级名称
      @param multiplier 等级倍率
     */
    ItemGrade(String name, double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    /**
      获取等级名称
      @return 等级名称
     */
    public String getName() { return name; }
    
    /**
      获取等级倍率
      @return 等级倍率
     */
    public double getMultiplier() { return multiplier; }
} 