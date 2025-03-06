package com.example.EtherealLegacy.Scene.Town;

/**
 * 帝都
 * 修仙界最繁华的城市，是修仙界的政治、经济和文化中心
 */
public class ImperialCity extends Town {
    public ImperialCity() {
        super("帝都", "修仙界最繁华的城市，皇室所在地。这里云集了各大宗门的产业，是修士们交易、切磋的重要场所。");
        setDiscovered(true); // 帝都作为初始城镇，默认已发现
    }

    @Override
    public void onEnter() {
        System.out.println("进入帝都，街道上人来人往，各色修士络绎不绝。");
        System.out.println("这里有：");
        System.out.println("1. 百宝阁 - 各类法器、丹药、符箓应有尽有");
        System.out.println("2. 修士集会 - 可以结识其他修士，获取任务");
        System.out.println("3. 皇家拍卖行 - 定期举办拍卖会，出售稀有物品");
    }
} 