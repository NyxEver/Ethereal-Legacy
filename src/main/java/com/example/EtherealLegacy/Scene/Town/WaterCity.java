package com.example.EtherealLegacy.Scene.Town;

/**
 * 水月城
 * 建在碧波之上的水城，以符箓闻名
 */
public class WaterCity extends Town {
    public WaterCity() {
        super("水月城", "建在碧波之上的水城，城中楼阁倒映在湖面上，如同一轮明月。这里是符箓之道的发源地。");
    }

    @Override
    public void onEnter() {
        System.out.println("进入水月城，清凉的水汽扑面而来，远处不时有符箓的光芒闪过。");
        System.out.println("这里有：");
        System.out.println("1. 符箓阁 - 可以购买各类符箓，也可以学习制符之术");
        System.out.println("2. 水月楼 - 城中最高的楼阁，是修士交易的场所");
        System.out.println("3. 渡仙桥 - 横跨湖面的巨大桥梁，两旁有众多商铺");
    }
} 