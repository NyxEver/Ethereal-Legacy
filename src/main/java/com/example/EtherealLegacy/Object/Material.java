package com.example.EtherealLegacy.Object;

import com.example.EtherealLegacy.CharacterPlayer.Character;

public class Material extends Item {
    private MaterialGrade materialGrade;

    public Material(String name, String description, ItemGrade grade, MaterialGrade materialGrade) {
        super(name, description, grade);
        this.materialGrade = materialGrade;
    }

    public MaterialGrade getMaterialGrade() {
        return materialGrade;
    }

    @Override
    public void use(Character character) {
        System.out.println("使用材料：" + name);
        // 根据材料等级和物品等级提供不同效果
        int effect = calculateEffect();
        System.out.println("获得" + effect + "点修为");
        character.gainExp(effect);
    }

    private int calculateEffect() {
        int baseEffect = switch (materialGrade) {
            case LOW -> 10;
            case MEDIUM -> 30;
            case MEDIUM_HIGH -> 60;
            case HIGH -> 100;
            case PERFECT -> 200;
        };

        return baseEffect * (grade.ordinal() + 1);
    }

    public static Material[] getAllMaterials() {
        return new Material[] {
            // 凡铁级材料
            new Material("铁精", "普通的精铁", ItemGrade.MORTAL_IRON, MaterialGrade.LOW),
            new Material("赤铜", "略有灵气的铜矿", ItemGrade.MORTAL_IRON, MaterialGrade.MEDIUM),
            new Material("玄铁", "蕴含微弱灵气的精铁", ItemGrade.MORTAL_IRON, MaterialGrade.MEDIUM_HIGH),
            new Material("精钢", "高纯度精钢", ItemGrade.MORTAL_IRON, MaterialGrade.HIGH),
            new Material("寒铁", "吸收天地寒气的精铁", ItemGrade.MORTAL_IRON, MaterialGrade.PERFECT),

            // 灵材级材料
            new Material("灵石精粹", "提纯的灵石精华", ItemGrade.SPIRIT_MATERIAL, MaterialGrade.LOW),
            new Material("聚灵木", "自然吸收灵气的树木", ItemGrade.SPIRIT_MATERIAL, MaterialGrade.MEDIUM),
            new Material("灵玉", "天然形成的灵气结晶", ItemGrade.SPIRIT_MATERIAL, MaterialGrade.MEDIUM_HIGH),
            new Material("五行精粹", "五行灵气的结晶", ItemGrade.SPIRIT_MATERIAL, MaterialGrade.HIGH),
            new Material("九天玄铁", "落自九天的神秘金属", ItemGrade.SPIRIT_MATERIAL, MaterialGrade.PERFECT),

            // 玄金级材料
            new Material("紫金原矿", "蕴含强大灵气的金属", ItemGrade.MYSTIC_GOLD, MaterialGrade.LOW),
            new Material("青龙木", "吸收龙气的神木", ItemGrade.MYSTIC_GOLD, MaterialGrade.MEDIUM),
            new Material("玄天石", "吸收天地精华的奇石", ItemGrade.MYSTIC_GOLD, MaterialGrade.MEDIUM_HIGH),
            new Material("离火精金", "被天火淬炼的神金", ItemGrade.MYSTIC_GOLD, MaterialGrade.HIGH),
            new Material("太阴玉髓", "凝聚太阴之力的玉髓", ItemGrade.MYSTIC_GOLD, MaterialGrade.PERFECT),

            // 地宝级材料
            new Material("地脉精华", "大地灵气的精纯结晶", ItemGrade.EARTH_TREASURE, MaterialGrade.LOW),
            new Material("万年灵乳", "万年钟乳石的精华", ItemGrade.EARTH_TREASURE, MaterialGrade.MEDIUM),
            new Material("九幽寒铁", "九幽地底的寒铁精华", ItemGrade.EARTH_TREASURE, MaterialGrade.MEDIUM_HIGH),
            new Material("地心火晶", "地心深处的火焰结晶", ItemGrade.EARTH_TREASURE, MaterialGrade.HIGH),
            new Material("太阳真金", "吸收太阳精华的神金", ItemGrade.EARTH_TREASURE, MaterialGrade.PERFECT),

            // 天晶级材料
            new Material("天外陨铁", "陨落九天的神秘金属", ItemGrade.HEAVEN_CRYSTAL, MaterialGrade.LOW),
            new Material("星辰精魄", "坠落星辰的精华", ItemGrade.HEAVEN_CRYSTAL, MaterialGrade.MEDIUM),
            new Material("雷劫真金", "经历雷劫的神金", ItemGrade.HEAVEN_CRYSTAL, MaterialGrade.MEDIUM_HIGH),
            new Material("混沌石", "混沌中诞生的奇石", ItemGrade.HEAVEN_CRYSTAL, MaterialGrade.HIGH),
            new Material("先天五行精粹", "先天五行之力的结晶", ItemGrade.HEAVEN_CRYSTAL, MaterialGrade.PERFECT),

            // 仙玉级材料
            new Material("仙界灵石", "仙界流落的灵石", ItemGrade.IMMORTAL_JADE, MaterialGrade.LOW),
            new Material("九天神玉", "九天之上的神玉", ItemGrade.IMMORTAL_JADE, MaterialGrade.MEDIUM),
            new Material("混元仙铁", "混元之力凝结的仙铁", ItemGrade.IMMORTAL_JADE, MaterialGrade.MEDIUM_HIGH),
            new Material("太初神石", "太初之时形成的神石", ItemGrade.IMMORTAL_JADE, MaterialGrade.HIGH),
            new Material("鸿蒙紫气", "鸿蒙中诞生的神秘物质", ItemGrade.IMMORTAL_JADE, MaterialGrade.PERFECT),

            // 神源级材料
            new Material("创世神铁", "创世之初诞生的神铁", ItemGrade.DIVINE_SOURCE, MaterialGrade.LOW),
            new Material("混沌神玉", "混沌孕育的神玉", ItemGrade.DIVINE_SOURCE, MaterialGrade.MEDIUM),
            new Material("太易神金", "太易时代的神金", ItemGrade.DIVINE_SOURCE, MaterialGrade.MEDIUM_HIGH),
            new Material("永恒仙金", "永恒不灭的仙金", ItemGrade.DIVINE_SOURCE, MaterialGrade.HIGH),
            new Material("道源之晶", "天道本源的结晶", ItemGrade.DIVINE_SOURCE, MaterialGrade.PERFECT)
        };
    }
} 