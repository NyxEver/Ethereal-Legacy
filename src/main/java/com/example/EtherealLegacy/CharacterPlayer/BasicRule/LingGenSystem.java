package com.example.EtherealLegacy.CharacterPlayer.BasicRule;

import java.util.Random;

/**
 * 灵根系统管理类
 * 负责处理角色的灵根分配和特殊灵根判定
 */
public class LingGenSystem {
    private String lingGen;
    private static final Random random = new Random();

    public LingGenSystem(String baseLingGen) {
        this.lingGen = assignSpecialLingGen(baseLingGen);
    }

    /**
     * 分配特殊灵根
     * 5%概率获得天灵根
     * 15%概率获得真灵根
     * 80%概率保持基础灵根
     */
    private String assignSpecialLingGen(String base) {
        int chance = random.nextInt(100);
        if (chance < 5) return "天灵根";
        else if (chance < 20) return "真灵根";
        return base;
    }

    public String getLingGen() {
        return lingGen;
    }
} 