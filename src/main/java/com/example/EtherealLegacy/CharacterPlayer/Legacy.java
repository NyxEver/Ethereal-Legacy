package com.example.EtherealLegacy.CharacterPlayer;

/**
  修仙传承枚举
  定义了游戏中不同的修仙流派
  每个流派都有其独特的属性加成
 */
public enum Legacy {
    SWORD_CULTIVATOR("剑修", "擅长剑法，攻击力强", 1.5, 1.0, 0.8, 1.0),
    PILL_MASTER("丹修", "擅长炼丹，恢复能力强", 0.8, 1.2, 1.0, 1.3),
    FORMATION_MASTER("阵修", "擅长阵法，防御力强", 1.0, 1.0, 1.5, 1.0),
    BODY_CULTIVATOR("体修", "擅长炼体，生命力强", 1.2, 1.5, 1.2, 0.8);

    // 传承名称
    private final String name;
    // 传承描述
    private final String description;
    // 攻击力加成
    private final double attackModifier;
    // 生命值加成
    private final double healthModifier;
    // 防御力加成 
    private final double defenseModifier;
    // 恢复力加成 
    private final double recoveryModifier;

    /**
      传承构造函数
      @param name 传承名称
      @param description 传承描述
      @param attackModifier 攻击力加成
      @param healthModifier 生命值加成
      @param defenseModifier 防御力加成
      @param recoveryModifier 恢复力加成
     */
    Legacy(String name, String description, double attackModifier, double healthModifier, 
          double defenseModifier, double recoveryModifier) {
        this.name = name;
        this.description = description;
        this.attackModifier = attackModifier;
        this.healthModifier = healthModifier;
        this.defenseModifier = defenseModifier;
        this.recoveryModifier = recoveryModifier;
    }

    /**
     * 获取传承名称
     * @return 传承名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取传承描述
     * @return 传承描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 获取攻击力加成
     * @return 攻击力加成倍率
     */
    public double getAttackModifier() {
        return attackModifier;
    }

    /**
     * 获取生命值加成
     * @return 生命值加成倍率
     */
    public double getHealthModifier() {
        return healthModifier;
    }

    /**
     * 获取防御力加成
     * @return 防御力加成倍率
     */
    public double getDefenseModifier() {
        return defenseModifier;
    }

    /**
     * 获取恢复力加成
     * @return 恢复力加成倍率
     */
    public double getRecoveryModifier() {
        return recoveryModifier;
    }
} 