package com.example.EtherealLegacy.CharacterPlayer;

import com.example.EtherealLegacy.Object.Item;
import com.example.EtherealLegacy.Object.ItemGrade;
import com.example.EtherealLegacy.Object.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * 背包系统类
 * 负责管理角色的物品和技能
 */
public class Inventory {
    private List<Item> items;
    private List<Skill> skills;

    public Inventory() {
        items = new ArrayList<>();
        skills = new ArrayList<>();
        // 添加基础攻击技能
        skills.add(new Skill("基础攻击", "最基本的攻击方式", ItemGrade.MORTAL_IRON, 5, 0));
    }

    /**
     * 添加物品到背包
     * @param item 要添加的物品
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * 从背包中移除物品
     * @param item 要移除的物品
     * @return 是否成功移除
     */
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    /**
     * 添加技能到背包
     * @param skill 要添加的技能
     */
    public void addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
            System.out.println("学习技能：" + skill.getName() + "（" + skill.getGrade().getName() + "）");
        }
    }

    /**
     * 获取所有物品
     * @return 物品列表
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 获取所有技能
     * @return 技能列表
     */
    public List<Skill> getSkills() {
        return skills;
    }
} 