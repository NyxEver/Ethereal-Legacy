package com.example.EtherealLegacy.Scene.Business;

import com.example.EtherealLegacy.CharacterPlayer.BasicRule.CultivationRealm;
import com.example.EtherealLegacy.CharacterPlayer.Bot.Monster;
import com.example.EtherealLegacy.CharacterPlayer.Character;
import com.example.EtherealLegacy.CharacterPlayer.Race;
import com.example.EtherealLegacy.Gaming.Battle;
import com.example.EtherealLegacy.Object.*;
import com.example.EtherealLegacy.Scene.Scene;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SectScene extends Scene {
    private boolean hasIceCore; // 是否有冰魄寒髓
    private boolean hasVolcano; // 是否有活火山
    private boolean hasGhostBridge; // 是否有奈何桥
    private boolean hasBrokenRealm; // 是否有破碎秘境
    private boolean hasBeastClan; // 是否有妖族聚集

    private List<Weapon> weaponShop;
    private List<Material> materialShop;

    public SectScene(String name, String description, double dangerLevel, CultivationRealm requiredRealm) {
        super(name, description, dangerLevel, requiredRealm);
        updateEnvironmentalEffects();
        initializeShops();
    }

    private void updateEnvironmentalEffects() {
        hasIceCore = name.equals("玄霜谷");
        hasVolcano = name.equals("焚天阙");
        hasGhostBridge = name.equals("冥河宗");
        hasBrokenRealm = name.equals("星移阁");
        hasBeastClan = name.equals("无相禅林");
    }

    private void initializeShops() {
        weaponShop = new ArrayList<>();
        materialShop = new ArrayList<>();
        
        if (name.equals("星移阁")) {
            // 星移阁专门出售武器
            Weapon[] allWeapons = Weapon.getAllWeapons();
            for (Weapon weapon : allWeapons) {
                if (weapon.getGrade().ordinal() >= ItemGrade.SPIRIT_MATERIAL.ordinal()) {
                    weaponShop.add(weapon);
                }
            }
        }
        
        if (name.equals("无相禅林")) {
            // 无相禅林专门出售材料
            Material[] allMaterials = Material.getAllMaterials();
            for (Material material : allMaterials) {
                if (material.getGrade().ordinal() >= ItemGrade.SPIRIT_MATERIAL.ordinal()) {
                    materialShop.add(material);
                }
            }
        }
    }

    @Override
    public void onEnter(Character player) {
        if (!checkRequirements(player)) {
            return;
        }

        System.out.println("进入" + name + "...");
        System.out.println(description);

        // 特殊环境效果
        if (hasIceCore) {
            System.out.println("寒气逼人，普通修士难以承受...");
            System.out.println("传闻地下冰封着已经入魔的创派祖师...");
        }

        if (hasVolcano) {
            System.out.println("火山口的地火为剑诀提供源源不断的力量...");
        }

        if (hasGhostBridge) {
            System.out.println("奈何桥的碎片散发着阴森气息...");
        }

        if (hasBrokenRealm) {
            System.out.println("整个宗门悬浮在破碎的秘境之中...");
        }

        if (hasBeastClan) {
            System.out.println("各种妖族在此和平共处...");
        }
    }

    @Override
    public void explore(Character player) {
        Random random = new Random();

        // 特殊环境效果
        if (hasIceCore && random.nextDouble() < 0.1) {
            System.out.println("地下传来一阵剧烈的震动，似乎是创派祖师在挣扎...");
            if (player.getRealm().ordinal() < CultivationRealm.NASCENT_SOUL.ordinal()) {
                int damage = 30 + random.nextInt(40);
                System.out.println("你受到" + damage + "点寒气伤害！");
                player.setHealth(player.getHealth() - damage);
            }
        }

        if (hasVolcano && random.nextDouble() < 0.2) {
            System.out.println("火山口喷发出一股地火！");
            if (random.nextDouble() < 0.5) {
                System.out.println("你借助地火之力淬炼剑气，修为大增！");
                player.addExp(200 * player.getLevel());
            } else {
                int damage = 40 + random.nextInt(30);
                System.out.println("你被地火灼伤，受到" + damage + "点伤害！");
                player.setHealth(player.getHealth() - damage);
            }
        }

        if (hasGhostBridge && random.nextDouble() < 0.15) {
            System.out.println("一个阴魂从奈何桥碎片中浮现！");
            if (random.nextDouble() < 0.4) {
                System.out.println("阴魂传授给你一些阴法要诀...");
                Skill[] allSkills = Skill.getAllSkills();
                Skill newSkill = getSkillByLevel(allSkills, player.getLevel());
                player.getInventory().addSkill(newSkill);
            } else {
                Monster ghost = new Monster(player.getLevel());
                ghost.setRace(Race.DEMON);
                Battle battle = new Battle(player, ghost);
                battle.start();
            }
        }

        // 商店系统
        if (hasBrokenRealm) {
            showWeaponShop(player);
        }

        if (hasBeastClan) {
            showMaterialShop(player);
        }
    }

    private void showWeaponShop(Character player) {
        System.out.println("\n=== 星移阁武器商店 ===");
        System.out.println("当前灵石：" + player.getLingShi().getAmount());
        
        List<Weapon> availableWeapons = new ArrayList<>();
        for (Weapon weapon : weaponShop) {
            if (isItemSuitableForRealm(weapon, player.getRealm())) {
                availableWeapons.add(weapon);
                int price = calculatePrice(weapon);
                System.out.println(availableWeapons.size() + ". " + weapon.getName() + 
                    " - " + weapon.getDescription() + " (价格：" + price + "灵石)");
            }
        }
        
        if (availableWeapons.isEmpty()) {
            System.out.println("当前境界无可购买武器");
            return;
        }

        System.out.println("请选择要购买的武器编号（输入0返回）：");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        if (choice > 0 && choice <= availableWeapons.size()) {
            Weapon selectedWeapon = availableWeapons.get(choice - 1);
            int price = calculatePrice(selectedWeapon);
            
            if (player.getLingShi().spend(price)) {
                player.getInventory().addItem(selectedWeapon);
                System.out.println("购买成功！");
            } else {
                System.out.println("灵石不足！");
            }
        }
    }

    private void showMaterialShop(Character player) {
        System.out.println("\n=== 无相禅林材料商店 ===");
        System.out.println("当前灵石：" + player.getLingShi().getAmount());
        
        List<Material> availableMaterials = new ArrayList<>();
        for (Material material : materialShop) {
            if (isItemSuitableForRealm(material, player.getRealm())) {
                availableMaterials.add(material);
                int price = calculatePrice(material);
                System.out.println(availableMaterials.size() + ". " + material.getName() + 
                    " - " + material.getDescription() + " (价格：" + price + "灵石)");
            }
        }
        
        if (availableMaterials.isEmpty()) {
            System.out.println("当前境界无可购买材料");
            return;
        }

        System.out.println("请选择要购买的材料编号（输入0返回）：");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        if (choice > 0 && choice <= availableMaterials.size()) {
            Material selectedMaterial = availableMaterials.get(choice - 1);
            int price = calculatePrice(selectedMaterial);
            
            if (player.getLingShi().spend(price)) {
                player.getInventory().addItem(selectedMaterial);
                System.out.println("购买成功！");
            } else {
                System.out.println("灵石不足！");
            }
        }
    }

    private int calculatePrice(Item item) {
        int basePrice = switch (item.getGrade()) {
            case MORTAL_IRON -> 100;
            case SPIRIT_MATERIAL -> 500;
            case MYSTIC_GOLD -> 2000;
            case EARTH_TREASURE -> 5000;
            case HEAVEN_CRYSTAL -> 10000;
            case IMMORTAL_JADE -> 50000;
            case DIVINE_SOURCE -> 100000;
            default -> 100;
        };

        if (item instanceof Weapon) {
            basePrice *= 2; // 武器价格翻倍
        }

        return basePrice;
    }

    @Override
    public void generateRewards(Character player) {
        Random random = new Random();
        
        // 基础奖励
        double rewardRoll = random.nextDouble();
        if (rewardRoll < 0.4) { // 40%概率获得材料
            Material[] materials = Material.getAllMaterials();
            Material material = getItemByLevel(materials, player.getLevel());
            player.getInventory().addItem(material);
        } else if (rewardRoll < 0.7) { // 30%概率获得武器
            Weapon[] weapons = Weapon.getAllWeapons();
            Weapon weapon = getItemByLevel(weapons, player.getLevel());
            player.getInventory().addItem(weapon);
        } else { // 30%概率获得技能
            Skill[] allSkills = Skill.getAllSkills();
            Skill newSkill = getSkillByLevel(allSkills, player.getLevel());
            player.getInventory().addSkill(newSkill);
        }
        
        // 额外获得灵石
        int lingShi = 200 + random.nextInt(300) * player.getLevel();
        player.getLingShi().add(lingShi);
        System.out.println("获得灵石：" + lingShi);
    }

    private <T extends Item> T getItemByLevel(T[] items, int playerLevel) {
        Random random = new Random();
        CultivationRealm playerRealm = CultivationRealm.getRealmByLevel(playerLevel);
        
        // 筛选出适合玩家境界的物品
        java.util.List<T> suitableItems = new java.util.ArrayList<>();
        for (T item : items) {
            if (isItemSuitableForRealm(item, playerRealm)) {
                suitableItems.add(item);
            }
        }
        
        if (suitableItems.isEmpty()) {
            return items[0]; // 返回最基础的物品
        }
        
        return suitableItems.get(random.nextInt(suitableItems.size()));
    }

    private Skill getSkillByLevel(Skill[] skills, int playerLevel) {
        Random random = new Random();
        CultivationRealm playerRealm = CultivationRealm.getRealmByLevel(playerLevel);
        
        // 筛选出适合玩家境界的技能
        java.util.List<Skill> suitableSkills = new java.util.ArrayList<>();
        for (Skill skill : skills) {
            if (isSkillSuitableForRealm(skill, playerRealm)) {
                suitableSkills.add(skill);
            }
        }
        
        if (suitableSkills.isEmpty()) {
            return skills[0]; // 返回最基础的技能
        }
        
        return suitableSkills.get(random.nextInt(suitableSkills.size()));
    }

    private boolean isItemSuitableForRealm(Item item, CultivationRealm realm) {
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

    private boolean isSkillSuitableForRealm(Skill skill, CultivationRealm realm) {
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
} 