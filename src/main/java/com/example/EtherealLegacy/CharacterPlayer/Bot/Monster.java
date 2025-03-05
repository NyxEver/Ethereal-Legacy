package com.example.EtherealLegacy.CharacterPlayer.Bot;

import com.example.EtherealLegacy.CharacterPlayer.Race;

public class Monster {
    private String name;
    private int level;
    private int health;
    private int maxHealth;
    private Race race;

    public Monster(int playerLevel) {
        this.name = "魔物";
        this.level = Math.max(1, playerLevel - 2 + (int)(Math.random() * 4)); // 等级在玩家等级-2到+1之间
        this.race = Math.random() < 0.5 ? Race.MONSTER : Race.DEMON; // 随机选择妖族或魔族
        calculateStats();
    }

    private void calculateStats() {
        double levelMultiplier = 1 + (level * 0.05); // 每级增加5%基础属性
        this.maxHealth = (int)(race.getBaseHealth() * levelMultiplier);
        this.health = maxHealth;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return "Lv." + level + " " + race.getName() + name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
        calculateStats(); // 重新计算属性
    }

    public void setLevel(int level) {
        this.level = level;
        // 每提升一级增加生命值上限
        this.maxHealth = 100 + (level - 1) * 20;
        this.health = maxHealth;
    }
} 