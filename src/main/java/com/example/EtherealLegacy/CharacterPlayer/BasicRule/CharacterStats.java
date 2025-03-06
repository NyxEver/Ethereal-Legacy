package com.example.EtherealLegacy.CharacterPlayer.BasicRule;

import com.example.EtherealLegacy.CharacterPlayer.BasicRule.CultivationRealm;
import com.example.EtherealLegacy.CharacterPlayer.Legacy;
import com.example.EtherealLegacy.CharacterPlayer.Race;

/**
 * 角色属性管理类
 * 负责处理角色的所有属性计算和更新
 */
public class CharacterStats {
    private int health;
    private int maxHealth;
    private int currentMana;
    private int maxMana;
    private double damage;
    private double defense;
    private double speed;
    private Race race;
    private Legacy legacy;
    private int level;

    public CharacterStats(Race race, Legacy legacy) {
        this.race = race;
        this.legacy = legacy;
        this.level = 1;
        initializeBaseStats();
    }

    /**
     * 初始化基础属性
     */
    private void initializeBaseStats() {
        this.maxHealth = (int)(race.getBaseHealth() * legacy.getHealthModifier());
        this.health = maxHealth;
        this.maxMana = race.getBaseMana();
        this.currentMana = maxMana;
        this.damage = race.getBaseAttack() * legacy.getAttackModifier();
        this.defense = race.getBaseDefense() * legacy.getDefenseModifier();
        this.speed = race.getBaseSpeed();
    }

    /**
     * 根据等级更新属性
     */
    public void updateStats() {
        this.maxHealth = (int)(race.getBaseHealth() * (1 + 0.1 * (level - 1)) * legacy.getHealthModifier());
        this.maxMana = (int)(race.getBaseMana() * (1 + 0.1 * (level - 1)));
        this.damage = (race.getBaseAttack() + (level - 1)) * legacy.getAttackModifier();
        this.defense = (race.getBaseDefense() + 0.5 * (level - 1)) * legacy.getDefenseModifier();
        this.speed = race.getBaseSpeed() + 0.5 * (level - 1);
    }

    // Getters and Setters
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getCurrentMana() { return currentMana; }
    public int getMaxMana() { return maxMana; }
    public double getDamage() { return damage; }
    public double getDefense() { return defense; }
    public double getSpeed() { return speed; }
    public int getLevel() { return level; }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
        if (this.health < 0) this.health = 0;
    }

    public void setCurrentMana(int mana) {
        this.currentMana = Math.min(mana, maxMana);
        if (this.currentMana < 0) this.currentMana = 0;
    }

    public void setLevel(int level) {
        this.level = level;
        updateStats();
        this.health = maxHealth;
        this.currentMana = maxMana;
    }

    public boolean consumeMana(int amount) {
        if (currentMana >= amount) {
            setCurrentMana(currentMana - amount);
            return true;
        }
        return false;
    }

    public void recoverMana(int amount) {
        setCurrentMana(currentMana + amount);
    }
} 