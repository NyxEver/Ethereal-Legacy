package com.example.EtherealLegacy.Object;

import com.example.EtherealLegacy.CharacterPlayer.Character;

/**
  游戏物品的抽象基类
  定义了所有物品的基本属性和行为
 */
public abstract class Item {
    // 物品名称 
    protected String name;
    // 物品描述 
    protected String description;
    // 物品等级 
    protected ItemGrade grade;

    /**
      物品构造函数
      @param name 物品名称
      @param description 物品描述
      @param grade 物品等级
     */
    public Item(String name, String description, ItemGrade grade) {
        this.name = name;
        this.description = description;
        this.grade = grade;
    }

    /**
      获取物品名称
      @return 物品名称
     */
    public String getName() {
        return name;
    }

    /**
      获取物品描述
      @return 物品描述
     */
    public String getDescription() {
        return description;
    }

    /**
      获取物品等级
      @return 物品等级
     */
    public ItemGrade getGrade() {
        return grade;
    }

    /**
      使用物品的抽象方法
      由子类实现具体的物品使用逻辑
      @param character 使用物品的角色
      @return 是否使用成功
     */
    public abstract boolean use(Character character);
} 