package com.example.EtherealLegacy.CharacterPlayer.BasicRule;

import com.example.EtherealLegacy.CharacterPlayer.Character;
import com.example.EtherealLegacy.CharacterPlayer.Inventory;
import com.example.EtherealLegacy.Object.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * 显示管理类
 * 负责处理角色信息和背包内容的显示
 */
public class DisplayManager {
    private final Character character;
    private final Inventory inventory;

    public DisplayManager(Character character, Inventory inventory) {
        this.character = character;
        this.inventory = inventory;
    }

    /**
     * 显示角色详细信息
     */
    public void displayCharacterInfo() {
        System.out.println("\n=== 角色信息 ===");
        System.out.println("姓名: " + character.getName());
        System.out.println("种族: " + character.getRace().getName());
        System.out.println("灵根: " + character.getLingGen());
        System.out.println("传承: " + character.getLegacy().getName());
        System.out.println("等级: " + character.getLevel());
        System.out.println("生命值: " + character.getHealth() + "/" + character.getMaxHealth());
        System.out.println("法力值: " + character.getCurrentMana() + "/" + character.getMaxMana());
        System.out.println("攻击力: " + String.format("%.1f", character.getDamage()));
        System.out.println("防御力: " + String.format("%.1f", character.getDefense()));
        System.out.println("速度: " + String.format("%.1f", character.getSpeed()));
        System.out.println("境界: " + character.getRealm().getName());
        System.out.println("灵石: " + character.getLingShi().getAmount());
        System.out.println("===============");
    }

    /**
     * 显示背包内容
     */
    public void displayBackpack() {
        List<Item> items = inventory.getItems();
        Map<ItemGrade, List<Item>> materialsByGrade = new HashMap<>();
        Map<ItemGrade, List<Item>> weaponsByGrade = new HashMap<>();

        // 分类物品
        for (Item item : items) {
            if (item instanceof Material) {
                materialsByGrade.computeIfAbsent(item.getGrade(), k -> new ArrayList<>()).add(item);
            } else if (item instanceof Weapon) {
                Weapon weapon = (Weapon) item;
                weaponsByGrade.computeIfAbsent(ItemGrade.MORTAL_IRON, k -> new ArrayList<>()).add(weapon);
            }
        }

        // 显示材料
        System.out.println("\n=== 材料物品 ===");
        displayItemsByGrade(materialsByGrade, "暂无材料");

        // 显示武器
        System.out.println("\n=== 武器物品 ===");
        displayItemsByGrade(weaponsByGrade, "暂无武器");

        // 显示技能
        System.out.println("\n=== 已学技能 ===");
        displaySkills();
    }

    /**
     * 显示指定品级的物品
     */
    private void displayItemsByGrade(Map<ItemGrade, List<Item>> itemsByGrade, String emptyMessage) {
        if (itemsByGrade.isEmpty()) {
            System.out.println("    " + emptyMessage);
        } else {
            for (ItemGrade grade : ItemGrade.values()) {
                List<Item> gradeItems = itemsByGrade.get(grade);
                if (gradeItems != null && !gradeItems.isEmpty()) {
                    System.out.println("    " + grade.getName() + " 等级：");
                    for (Item item : gradeItems) {
                        System.out.println("   - " + item.getName() + "：" + item.getDescription());
                    }
                }
            }
        }
    }

    /**
     * 显示技能列表
     */
    private void displaySkills() {
        List<Skill> skills = inventory.getSkills();
        if (skills == null || skills.isEmpty()) {
            System.out.println("    暂无技能");
            return;
        }

        Map<ItemGrade, List<Skill>> skillsByGrade = new HashMap<>();
        for (Skill skill : skills) {
            skillsByGrade.computeIfAbsent(skill.getGrade(), k -> new ArrayList<>()).add(skill);
        }

        for (ItemGrade grade : ItemGrade.values()) {
            List<Skill> gradeSkills = skillsByGrade.get(grade);
            if (gradeSkills != null && !gradeSkills.isEmpty()) {
                System.out.println("    " + grade.getName() + " 等级：");
                for (Skill skill : gradeSkills) {
                    System.out.println("   - " + skill.getName() + "：" + skill.getDescription());
                    System.out.println("     基础伤害：" + skill.getDamage());
                    System.out.println("     灵力消耗：" + skill.getCost());
                }
            }
        }
    }
} 