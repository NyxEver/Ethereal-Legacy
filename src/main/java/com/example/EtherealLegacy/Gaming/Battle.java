package com.example.EtherealLegacy.Gaming;

import com.example.EtherealLegacy.CharacterPlayer.BasicRule.CultivationRealm;
import com.example.EtherealLegacy.CharacterPlayer.Bot.Monster;
import com.example.EtherealLegacy.CharacterPlayer.Character;
import com.example.EtherealLegacy.CharacterPlayer.Race;
import com.example.EtherealLegacy.Object.*;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
  战斗系统类
  处理游戏中的战斗逻辑
  包括回合制战斗、技能使用、伤害计算等
 */
public class Battle {
    private Character player;
    // 敌人角色
    private Monster monster;
    private Random random = new Random();

    /**
     * 战斗系统构造函数
     * @param player 玩家角色
     * @param monster 敌人角色
     */
    public Battle(Character player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    /**
      开始战斗
      处理战斗流程直到一方死亡
     */
    public void start() {
        System.out.println("\n=== 战斗开始 ===");
        System.out.println("遭遇 " + monster.getName() + "！");
        System.out.println("魔物生命值：" + monster.getHealth());

        while (monster.isAlive() && player.getHealth() > 0) {
            // 玩家回合
            playerTurn();
            if (!monster.isAlive()) {
                break;
            }

            // 魔物回合
            monsterTurn();
        }

        if (monster.isAlive()) {
            System.out.println("战斗失败！");
        } else {
            System.out.println("战斗胜利！");
            dropRewards();
        }
    }

    /**
      处理玩家回合
      显示选项并执行玩家选择的行动
     */
    private void playerTurn() {
        // 检查并使用恢复道具
        player.getItemManager().checkAndUseConsumables();

        List<Skill> skills = player.getInventory().getSkills();
        Skill skill = skills.get(random.nextInt(skills.size()));
        int damage = skill.getDamage();
        
        if (player.getCurrentMana() >= skill.getCost()) {
            monster.setHealth(monster.getHealth() - damage);
            player.setCurrentMana(player.getCurrentMana() - skill.getCost());
            System.out.println("使用" + skill.getName() + "，造成" + damage + "点伤害！消耗" + skill.getCost() + "点灵力");
            System.out.println("魔物剩余生命值：" + monster.getHealth());
        } else {
            System.out.println("灵力不足，无法使用" + skill.getName() + "！进行普通攻击");
            int basicDamage = 5 + player.getLevel();
            monster.setHealth(monster.getHealth() - basicDamage);
            System.out.println("造成" + basicDamage + "点伤害！");
            System.out.println("魔物剩余生命值：" + monster.getHealth());
        }
    }

    /**
      处理敌人回合
      执行敌人的行动
     */
    private void monsterTurn() {
        int damage = 5 + monster.getLevel() * 2;
        
        // 魔族和妖族伤害降低20%
        if (monster.getRace() == Race.MONSTER || monster.getRace() == Race.DEMON) {
            damage = (int)(damage * 0.8);
        }
        
        // 玩家1-10级时减免50%伤害
        if (player.getLevel() <= 10) {
            damage = (int)(damage * 0.5);
        }
        
        player.setHealth(player.getHealth() - damage);
        System.out.println("魔物攻击，造成" + damage + "点伤害！");
        System.out.println("玩家剩余生命值：" + player.getHealth());

        // 受到伤害后检查并使用恢复道具
        player.getItemManager().checkAndUseConsumables();
    }

    private void dropRewards() {
        // 100%获得经验值
        int expGain = 10 * monster.getLevel() * 5; // 5倍经验值
        player.gainExp(expGain);
        System.out.println("获得经验值：" + expGain);

        // 获得灵石
        int lingShi = calculateLingShiReward();
        player.getLingShi().add(lingShi);
        System.out.println("获得灵石：" + lingShi);

        double roll = random.nextDouble();
        
        // 50%概率获得素材
        if (roll < 0.5) {
            Material[] materials = Material.getAllMaterials();
            Material material = getItemByLevel(materials, player.getLevel());
            player.getInventory().addItem(material);
            System.out.println("获得物品：" + material.getName());
        }
        
        // 35%概率获得武器
        if (roll < 0.35) {
            Weapon[] weapons = Weapon.getAllWeapons();
            Weapon weapon = getItemByLevel(weapons, player.getLevel());
            player.getInventory().addItem(weapon);
            System.out.println("获得物品：" + weapon.getName());
        }
        
        // 15%概率获得技能
        if (roll < 0.15) {
            Skill[] allSkills = Skill.getAllSkills();
            Skill newSkill = getItemByLevel(allSkills, player.getLevel());
            player.getInventory().addSkill(newSkill);
            System.out.println("获得技能：" + newSkill.getName());
        }
    }

    private int calculateLingShiReward() {
        int baseReward = 20 + monster.getLevel() * 5;
        
        // 根据怪物种族调整奖励
        switch (monster.getRace()) {
            case HUMAN:
                baseReward *= 1.2; // 人类敌人给予20%额外灵石
                break;
            case MONSTER:
                baseReward *= 1.5; // 妖族给予50%额外灵石
                break;
            case DEMON:
                baseReward *= 2.0; // 魔族给予100%额外灵石
                break;
        }

        // 根据玩家境界额外增加
        CultivationRealm realm = player.getRealm();
        int realmIndex = getRealmIndex(realm);
        baseReward *= (1 + realmIndex * 0.1); // 每个境界增加10%

        return (int)baseReward;
    }

    private <T extends Item> T getItemByLevel(T[] items, int playerLevel) {
        List<T> availableItems = new ArrayList<>();
        for (T item : items) {
            if (isItemAvailableForLevel(item, playerLevel)) {
                availableItems.add(item);
            }
        }
        if (availableItems.isEmpty()) {
            return items[0]; // 返回最低级的物品
        }
        return availableItems.get(random.nextInt(availableItems.size()));
    }

    private boolean isItemAvailableForLevel(Item item, int playerLevel) {
        CultivationRealm realm = CultivationRealm.getRealmByLevel(playerLevel);
        return getItemGradeIndex(item.getGrade()) <= getRealmIndex(realm);
    }

    private int getItemGradeIndex(ItemGrade grade) {
        ItemGrade[] grades = ItemGrade.values();
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == grade) return i;
        }
        return 0;
    }

    private int getRealmIndex(CultivationRealm realm) {
        CultivationRealm[] realms = CultivationRealm.values();
        for (int i = 0; i < realms.length; i++) {
            if (realms[i] == realm) return i;
        }
        return 0;
    }
} 