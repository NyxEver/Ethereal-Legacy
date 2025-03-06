package com.example.EtherealLegacy.CharacterPlayer;

/**
  种族枚举
  定义了游戏中可选择的种族类型
  每个种族都有其独特的基础属性
 */
public enum Race {
    HUMAN("人类", 100, 50),
    DEMON("魔族", 60, 90),
    MONSTER("妖族", 150, 20);

    // 种族名称 
    private final String name;
    // 基础生命值 
    private final int baseHealth;
    // 基础灵力值 
    private final int baseMana;

    /**
     * 种族构造函数
     * @param name 种族名称
     * @param baseHealth 基础生命值
     * @param baseMana 基础灵力值
     */
    Race(String name, int baseHealth, int baseMana) {
        this.name = name;
        this.baseHealth = baseHealth;
        this.baseMana = baseMana;
    }

    /**
     * 获取种族名称
     * @return 种族名称
     */
    public String getName() { return name; }
    
    /**
     * 获取基础生命值
     * @return 基础生命值
     */
    public int getBaseHealth() { return baseHealth; }
    
    /**
     * 获取基础灵力值
     * @return 基础灵力值
     */
    public int getBaseMana() { return baseMana; }
} 