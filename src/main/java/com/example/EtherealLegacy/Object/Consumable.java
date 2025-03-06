package com.example.EtherealLegacy.Object;

import com.example.EtherealLegacy.CharacterPlayer.Character;

/**
 * 消耗品类
 * 表示可以使用的物品，如血瓶、法力瓶等
 */
public class Consumable extends Item {
    private final ConsumableType type;
    private final int value;

    public Consumable(String name, String description, ItemGrade grade, ConsumableType type, int value) {
        super(name, description, grade);
        this.type = type;
        this.value = value;
    }

    /**
     * 使用物品
     * @param character 使用物品的角色
     * @return 是否使用成功
     */
    @Override
    public boolean use(Character character) {
        return switch (type) {
            case HEALTH_POTION -> {
                if (character.getHealth() < character.getMaxHealth()) {
                    character.setHealth(Math.min(character.getHealth() + value, character.getMaxHealth()));
                    System.out.println("使用" + getName() + "，恢复" + value + "点生命值");
                    yield true;
                }
                yield false;
            }
            case MANA_POTION -> {
                if (character.getCurrentMana() < character.getMaxMana()) {
                    character.setCurrentMana(Math.min(character.getCurrentMana() + value, character.getMaxMana()));
                    System.out.println("使用" + getName() + "，恢复" + value + "点法力值");
                    yield true;
                }
                yield false;
            }
        };
    }

    public ConsumableType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public static Consumable[] getAllConsumables() {
        return new Consumable[] {
            // 小型恢复道具
            new Consumable("小血瓶", "恢复80点生命值", ItemGrade.MORTAL_IRON, ConsumableType.HEALTH_POTION, 80),
            new Consumable("小法瓶", "恢复80点法力值", ItemGrade.MORTAL_IRON, ConsumableType.MANA_POTION, 80),
            // 中型恢复道具
            new Consumable("中血瓶", "恢复200点生命值", ItemGrade.SPIRIT_MATERIAL, ConsumableType.HEALTH_POTION, 200),
            new Consumable("中法瓶", "恢复200点法力值", ItemGrade.SPIRIT_MATERIAL, ConsumableType.MANA_POTION, 200),
            // 大型恢复道具
            new Consumable("大血瓶", "恢复500点生命值", ItemGrade.MYSTIC_GOLD, ConsumableType.HEALTH_POTION, 500),
            new Consumable("大法瓶", "恢复500点法力值", ItemGrade.MYSTIC_GOLD, ConsumableType.MANA_POTION, 500)
        };
    }
} 