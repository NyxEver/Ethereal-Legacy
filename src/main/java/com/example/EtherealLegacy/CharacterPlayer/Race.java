package com.example.EtherealLegacy.CharacterPlayer;

/**
  种族枚举
  定义了游戏中可选择的种族类型
  每个种族都有其独特的基础属性
 */
public enum Race {
    // 人类：均衡型种族
    HUMAN("人类", 100, 50, 10, 5, 10),
    // 魔族：高法力、高速度、低防御
    DEMON("魔族", 60, 90, 8, 3, 12),
    // 妖族：高生命、高攻击、高防御、低速度、低法力
    MONSTER("妖族", 150, 20, 12, 8, 8);

    // 种族名称 
    private final String name;
    // 基础生命值 
    private final int baseHealth;
    // 基础法力值 
    private final int baseMana;
    // 基础攻击力
    private final int baseAttack;
    // 基础防御力
    private final int baseDefense;
    // 基础速度
    private final int baseSpeed;

    /**
     * 种族构造函数
     * @param name 种族名称
     * @param baseHealth 基础生命值
     * @param baseMana 基础法力值
     * @param baseAttack 基础攻击力
     * @param baseDefense 基础防御力
     * @param baseSpeed 基础速度
     */
    Race(String name, int baseHealth, int baseMana, int baseAttack, int baseDefense, int baseSpeed) {
        this.name = name;
        this.baseHealth = baseHealth;
        this.baseMana = baseMana;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpeed = baseSpeed;
    }

    /**
     * 获取种族名称
     * @return 种族名称
     */
    public String getName() { 
        return name; 
    }
    
    /**
     * 获取基础生命值
     * @return 基础生命值
     */
    public int getBaseHealth() { 
        return baseHealth; 
    }
    
    /**
     * 获取基础法力值
     * @return 基础法力值
     */
    public int getBaseMana() { 
        return baseMana; 
    }

    /**
     * 获取基础攻击力
     * @return 基础攻击力
     */
    public int getBaseAttack() {
        return baseAttack;
    }

    /**
     * 获取基础防御力
     * @return 基础防御力
     */
    public int getBaseDefense() {
        return baseDefense;
    }

    /**
     * 获取基础速度
     * @return 基础速度
     */
    public int getBaseSpeed() {
        return baseSpeed;
    }
} 