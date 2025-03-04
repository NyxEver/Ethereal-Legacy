package Game;

public enum SkillGrade {
    BASIC("下品", "凡人武者可修", 1.0),
    SPIRIT("中品", "需筑基期灵力", 2.0),
    MYSTIC("上品", "金丹修士方可驾驭", 3.0),
    EARTH("地品", "需领悟法则碎片", 4.0),
    HEAVEN("天品", "渡劫期方可驾驭", 5.0),
    IMMORTAL("仙品", "需融合仙界本源", 6.0);

    private final String name;
    private final String requirement;
    private final double damageMultiplier;

    SkillGrade(String name, String requirement, double damageMultiplier) {
        this.name = name;
        this.requirement = requirement;
        this.damageMultiplier = damageMultiplier;
    }

    public String getName() { return name; }
    public String getRequirement() { return requirement; }
    public double getDamageMultiplier() { return damageMultiplier; }
} 