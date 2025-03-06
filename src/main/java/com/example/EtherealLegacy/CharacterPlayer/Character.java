package com.example.EtherealLegacy.CharacterPlayer;

import com.example.EtherealLegacy.CharacterPlayer.BasicRule.*;
import com.example.EtherealLegacy.Object.*;
import com.example.EtherealLegacy.Scene.Business.Sect;

/**
 * 角色主类
 * 整合各个系统，提供角色的主要接口
 */
public class Character {
    private String name;
    private CharacterStats stats;
    private ExperienceSystem expSystem;
    private LingGenSystem lingGenSystem;
    private ItemManager itemManager;
    private DisplayManager displayManager;
    private Legacy legacy;
    private Race race;
    private Inventory inventory;
    private LingShi lingShi;
    private Sect sect;
    private boolean hasVisitedRelic = false;

    /**
     * 创建角色（默认人类种族）
     * @param name 角色名称
     * @param lingGen 灵根类型
     * @param legacy 传承类型
     */
    public Character(String name, String lingGen, Legacy legacy) {
        this(name, lingGen, legacy, Race.HUMAN);
    }

    /**
     * 创建指定种族的角色
     * @param name 角色名称
     * @param lingGen 灵根类型
     * @param legacy 传承类型
     * @param race 种族类型
     */
    public Character(String name, String lingGen, Legacy legacy, Race race) {
        this.name = name;
        this.legacy = legacy;
        this.race = race;
        
        // 初始化各个系统
        this.inventory = new Inventory();
        this.stats = new CharacterStats(race, legacy);
        this.expSystem = new ExperienceSystem(stats);
        this.lingGenSystem = new LingGenSystem(lingGen);
        this.itemManager = new ItemManager(this, inventory);
        this.displayManager = new DisplayManager(this, inventory);
        this.lingShi = new LingShi(0);

        // 生成初始装备
        itemManager.generateInitialWeapon();
        itemManager.generateInitialSkill();
        
        // 显示角色信息
        displayManager.displayCharacterInfo();
    }

    // 委托方法到各个系统
    public void gainExp(int amount) { expSystem.gainExp(amount); }
    public void addExp(int amount) { gainExp(amount); }
    public int getExp() { return expSystem.getExp(); }
    public int getExpToNextLevel() { return expSystem.getExpToNextLevel(); }
    public int getLevel() { return stats.getLevel(); }
    public int getHealth() { return stats.getHealth(); }
    public void setHealth(int health) { stats.setHealth(health); }
    public int getMaxHealth() { return stats.getMaxHealth(); }
    public int getCurrentMana() { return stats.getCurrentMana(); }
    public void setCurrentMana(int mana) { stats.setCurrentMana(mana); }
    public int getMaxMana() { return stats.getMaxMana(); }
    public double getDamage() { return stats.getDamage(); }
    public double getDefense() { return stats.getDefense(); }
    public double getSpeed() { return stats.getSpeed(); }
    public String getLingGen() { return lingGenSystem.getLingGen(); }
    public Legacy getLegacy() { return legacy; }
    public Race getRace() { return race; }
    public Inventory getInventory() { return inventory; }
    public LingShi getLingShi() { return lingShi; }
    public boolean hasVisitedRelic() { return hasVisitedRelic; }
    public void setHasVisitedRelic(boolean value) { this.hasVisitedRelic = value; }
    public String getName() { return name; }
    public CultivationRealm getRealm() { return expSystem.getRealm(); }

    public void recoverMana(int amount) { stats.recoverMana(amount); }
    public boolean consumeMana(int amount) { return stats.consumeMana(amount); }

    public void joinSect(String sectName) {
        if (sect == null) {
            sect = new Sect(sectName);
            System.out.println("加入宗门：" + sectName);
        } else {
            System.out.println("已经加入宗门：" + sect.getName());
        }
    }

    public Sect getSect() {
        return sect;
    }

    /**
     * 显示角色详细信息
     */
    public void displayCharacterInfo() {
        displayManager.displayCharacterInfo();
    }

    /**
     * 显示背包内容
     */
    public void showBackpack() {
        displayManager.displayBackpack();
    }

    public ItemManager getItemManager() {
        return itemManager;
    }
}