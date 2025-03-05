package com.example.EtherealLegacy.CharacterPlayer;

import com.example.EtherealLegacy.CharacterPlayer.BasicRule.CultivationRealm;
import com.example.EtherealLegacy.Object.*;
import com.example.EtherealLegacy.Scene.Business.Sect;

import java.util.Random;

public class Character {
    private String name;
    private int level;
    private int health;
    private int maxHealth;
    private int currentMana; // 当前灵力值
    private int maxMana; // 最大灵力值
    private String lingGen; // 灵根类型
    private int exp;
    private int expToNextLevel;
    private Legacy legacy; // 传承
    private double damage; // 基础伤害值
    private Inventory inventory; // 背包系统
    private Race race; // 种族
    private CultivationRealm realm; // 修炼境界
    private LingShi lingShi; // 灵石
    private Sect sect; // 宗门
    private boolean hasVisitedRelic = false; // 是否访问过遗迹

    public Character(String name, String lingGen, Legacy legacy) {
        this.name = name;
        this.lingGen = assignSpecialLingGen(lingGen);
        this.legacy = legacy;
        this.level = 1;
        this.exp = 0;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.maxMana = 100; // 初始最大灵力值
        this.currentMana = maxMana; // 初始当前灵力值
        this.realm = CultivationRealm.MORTAL_BODY;
        this.damage = 10;
        this.expToNextLevel = calculateExpToNextLevel();
        this.inventory = new Inventory();
        this.lingShi = new LingShi(0); // 初始灵石为0
        this.race = Race.HUMAN; // 玩家默认为人类

        // 生成初始武器
        generateInitialWeapon();
        // 生成初始技能
        generateInitialSkill();
        
        System.out.println("创建角色成功！");
        System.out.println("姓名: " + name);
        System.out.println("灵根: " + lingGen);
        System.out.println("种族: " + race.getName());
        System.out.println("境界: " + realm.getName() + "(" + realm.getDescription() + ")");
        System.out.println("传承: " + legacy.getName());
        System.out.println("特效: " + legacy.getDescription());
        System.out.println("灵石: " + lingShi.getAmount());
    }

    private void generateInitialWeapon() {
        Weapon[] weapons = Weapon.getAllWeapons();
        java.util.List<Weapon> suitableWeapons = new java.util.ArrayList<>();
        
        // 筛选出适合当前境界的武器
        for (Weapon weapon : weapons) {
            if (isItemSuitableForRealm(weapon)) {
                suitableWeapons.add(weapon);
            }
        }
        
        if (!suitableWeapons.isEmpty()) {
            Weapon initialWeapon = suitableWeapons.get(new java.util.Random().nextInt(suitableWeapons.size()));
            inventory.addItem(initialWeapon);
            System.out.println("获得初始武器：" + initialWeapon.getName());
        }
    }

    private void generateInitialSkill() {
        Skill[] skills = Skill.getAllSkills();
        java.util.List<Skill> suitableSkills = new java.util.ArrayList<>();
        
        // 筛选出适合当前境界的技能
        for (Skill skill : skills) {
            if (isSkillSuitableForRealm(skill)) {
                suitableSkills.add(skill);
            }
        }
        
        if (!suitableSkills.isEmpty()) {
            Skill initialSkill = suitableSkills.get(new java.util.Random().nextInt(suitableSkills.size()));
            inventory.addSkill(initialSkill);
            System.out.println("获得初始技能：" + initialSkill.getName());
        }
    }

    private boolean isItemSuitableForRealm(Item item) {
        if (item.getGrade() == null) return true;
        
        return switch (realm) {
            case MORTAL_BODY, MERIDIAN_OPENING -> item.getGrade() == ItemGrade.MORTAL_IRON;
            case QI_CONDENSATION, FOUNDATION_BUILDING -> item.getGrade() == ItemGrade.SPIRIT_MATERIAL;
            case GOLDEN_CORE, NASCENT_SOUL -> item.getGrade() == ItemGrade.MYSTIC_GOLD;
            case SPIRIT_SEVERING -> item.getGrade() == ItemGrade.EARTH_TREASURE;
            case TRIBULATION -> item.getGrade() == ItemGrade.HEAVEN_CRYSTAL;
            case IMMORTAL -> item.getGrade() == ItemGrade.IMMORTAL_JADE;
            default -> false;
        };
    }

    private boolean isSkillSuitableForRealm(Skill skill) {
        if (skill.getGrade() == null) return true;
        
        return switch (realm) {
            case MORTAL_BODY, MERIDIAN_OPENING -> skill.getGrade() == ItemGrade.MORTAL_IRON;
            case QI_CONDENSATION, FOUNDATION_BUILDING -> skill.getGrade() == ItemGrade.SPIRIT_MATERIAL;
            case GOLDEN_CORE, NASCENT_SOUL -> skill.getGrade() == ItemGrade.MYSTIC_GOLD;
            case SPIRIT_SEVERING -> skill.getGrade() == ItemGrade.EARTH_TREASURE;
            case TRIBULATION -> skill.getGrade() == ItemGrade.HEAVEN_CRYSTAL;
            case IMMORTAL -> skill.getGrade() == ItemGrade.IMMORTAL_JADE;
            default -> false;
        };
    }

    public void gainExp(int amount) {
        this.exp += amount;
        checkLevelUp();
    }

    public void addExp(int amount) {
        this.exp += amount;
        this.expToNextLevel = calculateExpToNextLevel();
        checkLevelUp();
    }

    private void checkLevelUp() {
        int expNeeded = level * 100;
        while (exp >= expNeeded) {
            exp -= expNeeded;
            setLevel(level + 1);
            System.out.println("等级提升！当前等级：" + level);
            checkRealmAdvancement();
            expNeeded = level * 100;
        }
    }

    private void checkRealmAdvancement() {
        CultivationRealm newRealm = CultivationRealm.getRealmByLevel(level);
        if (newRealm != realm) {
            realm = newRealm;
            System.out.println("突破成功！当前境界：" + realm.getName());
            // 突破时完全恢复生命值和灵力值
            health = maxHealth;
            currentMana = maxMana;
        }
    }

    public void setLevel(int level) {
        this.level = level;
        // 每提升一级，增加生命值上限和灵力值上限
        this.maxHealth = 100 + (level - 1) * 20;
        this.maxMana = 100 + (level - 1) * 15;
        this.health = maxHealth;
        this.currentMana = maxMana;
    }

    public int getExp() {
        return exp;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
        if (this.currentMana > this.maxMana) {
            this.currentMana = this.maxMana;
        }
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int mana) {
        this.currentMana = Math.min(mana, maxMana); // 不能超过最大灵力值
        if (this.currentMana < 0) {
            this.currentMana = 0;
        }
    }

    public void recoverMana(int amount) {
        setCurrentMana(currentMana + amount);
    }

    public boolean consumeMana(int amount) {
        if (currentMana >= amount) {
            setCurrentMana(currentMana - amount);
            return true;
        }
        return false;
    }

    public double getDamage() {
        return damage * legacy.getAttackModifier();
    }

    public String getLingGen() {
        return lingGen;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public CultivationRealm getRealm() {
        return realm;
    }

    public Race getRace() {
        return race;
    }

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

    public LingShi getLingShi() {
        return lingShi;
    }

    public boolean hasVisitedRelic() {
        return hasVisitedRelic;
    }

    public void setHasVisitedRelic(boolean value) {
        this.hasVisitedRelic = value;
    }

    private String assignSpecialLingGen(String base) {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance < 5) return "天灵根"; // 5% 概率
        else if (chance < 20) return "真灵根"; // 15% 概率
        return base; // 80% 概率保持基础灵根
    }

    private int calculateExpToNextLevel() {
        return level * 100; // 每级所需经验值为当前等级 * 100
    }
}