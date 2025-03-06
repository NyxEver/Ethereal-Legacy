package com.example.EtherealLegacy.Scene.Town;

/**
 * 沙洲城
 * 建在沙漠绿洲中的城镇，是探索古迹的重要据点
 */
public class DesertOasis extends Town {
    public DesertOasis() {
        super("沙洲城", "矗立在无尽沙海中的绿洲城镇，这里是探索古迹的重要据点，经常有修士带来各种奇珍异宝。");
    }

    @Override
    public void onEnter() {
        System.out.println("踏入沙洲城，扑面而来的是绿洲特有的清新气息。");
        System.out.println("这里有：");
        System.out.println("1. 探宝会 - 可以组队探索古迹，或交易探索获得的宝物");
        System.out.println("2. 宝器阁 - 收购和出售各类古董法器");
        System.out.println("3. 佣兵工会 - 可以雇佣向导或护卫，也可以接取护送任务");
    }
} 