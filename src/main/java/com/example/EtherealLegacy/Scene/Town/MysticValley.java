package com.example.EtherealLegacy.Scene.Town;

/**
 * 幽谷
 * 位于深山中的隐秘城镇，以炼丹闻名
 */
public class MysticValley extends Town {
    public MysticValley() {
        super("幽谷", "隐藏在群山之中的神秘城镇，这里聚集了众多炼丹师，空气中弥漫着药香。");
    }

    @Override
    public void onEnter() {
        System.out.println("踏入幽谷，浓郁的药香扑面而来。");
        System.out.println("这里有：");
        System.out.println("1. 丹药坊 - 可以购买各种丹药，也可以委托炼丹");
        System.out.println("2. 药圃 - 种植着各种灵药，可以采集或购买");
        System.out.println("3. 丹道学院 - 可以学习炼丹之术");
    }
} 