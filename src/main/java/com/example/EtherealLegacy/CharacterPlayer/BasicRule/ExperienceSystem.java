package com.example.EtherealLegacy.CharacterPlayer.BasicRule;

import com.example.EtherealLegacy.CharacterPlayer.BasicRule.CharacterStats;
import com.example.EtherealLegacy.CharacterPlayer.BasicRule.CultivationRealm;

/**
 * 经验系统管理类
 * 负责处理角色的经验值获取、等级提升和境界突破
 */
public class ExperienceSystem {
    private int exp;
    private int expToNextLevel;
    private CultivationRealm realm;
    private CharacterStats stats;

    public ExperienceSystem(CharacterStats stats) {
        this.stats = stats;
        this.exp = 0;
        this.realm = CultivationRealm.MORTAL_BODY;
        this.expToNextLevel = calculateExpToNextLevel();
    }

    /**
     * 获得经验值并检查升级
     */
    public void gainExp(int amount) {
        this.exp += amount;
        checkLevelUp();
    }

    /**
     * 检查是否可以升级
     */
    private void checkLevelUp() {
        int expNeeded = stats.getLevel() * 100;
        while (exp >= expNeeded) {
            exp -= expNeeded;
            stats.setLevel(stats.getLevel() + 1);
            System.out.println("等级提升！当前等级：" + stats.getLevel());
            checkRealmAdvancement();
            expNeeded = stats.getLevel() * 100;
        }
        this.expToNextLevel = calculateExpToNextLevel();
    }

    /**
     * 检查是否可以突破境界
     */
    private void checkRealmAdvancement() {
        CultivationRealm newRealm = CultivationRealm.getRealmByLevel(stats.getLevel());
        if (newRealm != realm) {
            realm = newRealm;
            System.out.println("突破成功！当前境界：" + realm.getName());
        }
    }

    private int calculateExpToNextLevel() {
        return stats.getLevel() * 100;
    }

    // Getters
    public int getExp() { return exp; }
    public int getExpToNextLevel() { return expToNextLevel; }
    public CultivationRealm getRealm() { return realm; }
} 