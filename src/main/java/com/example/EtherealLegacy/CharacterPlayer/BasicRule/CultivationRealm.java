package com.example.EtherealLegacy.CharacterPlayer.BasicRule;

public enum CultivationRealm {
    MORTAL_BODY("凡胎境", "淬体炼骨", 1, 10),
    MERIDIAN_OPENING("通脉境", "贯通灵窍", 10, 25),
    QI_CONDENSATION("凝气境", "化虚为实", 25, 50),
    FOUNDATION_BUILDING("筑基境", "筑道基台", 50, 80),
    GOLDEN_CORE("金丹境", "结丹化形", 80, 120),
    NASCENT_SOUL("元婴境", "元神显化", 120, 160),
    SPIRIT_SEVERING("化神境", "法则初窥", 160, 200),
    TRIBULATION("渡劫境", "九死涅槃", 200, 300),
    IMMORTAL("不朽境", "与天同寿", 300, Integer.MAX_VALUE);

    private final String name;
    private final String description;
    private final int minLevel;
    private final int maxLevel;

    CultivationRealm(String name, String description, int minLevel, int maxLevel) {
        this.name = name;
        this.description = description;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getMinLevel() { return minLevel; }
    public int getMaxLevel() { return maxLevel; }

    public static CultivationRealm getRealmByLevel(int level) {
        for (CultivationRealm realm : values()) {
            if (level >= realm.minLevel && level < realm.maxLevel) {
                return realm;
            }
        }
        return IMMORTAL;
    }
} 