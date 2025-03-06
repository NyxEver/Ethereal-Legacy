package com.example.EtherealLegacy.Scene.Town;

/**
 * 云顶城
 * 建立在高山之巅的修仙圣地，是剑修的集中地
 */
public class CloudPeak extends Town {
    public CloudPeak() {
        super("云顶城", "位于万丈高山之巅的修仙圣地，这里灵气浓郁，是众多剑修的修炼圣地。");
    }

    @Override
    public void onEnter() {
        System.out.println("来到云顶城，云雾缭绕间时有剑光闪过。");
        System.out.println("这里有：");
        System.out.println("1. 剑阁 - 收藏着各种名剑，可以购买或借阅剑谱");
        System.out.println("2. 演武场 - 供修士切磋剑术的场所");
        System.out.println("3. 铸剑坊 - 可以打造或购买各类法剑");
    }
} 