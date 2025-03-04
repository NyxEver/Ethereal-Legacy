package Game;

public enum Legacy {
    SWORD_CULTIVATOR("剑修", "擅长剑法，攻击力强", 1.5, 1.0, 0.8, 1.0),
    PILL_MASTER("丹修", "擅长炼丹，恢复能力强", 0.8, 1.2, 1.0, 1.3),
    FORMATION_MASTER("阵修", "擅长阵法，防御力强", 1.0, 1.0, 1.5, 1.0),
    BODY_CULTIVATOR("体修", "擅长炼体，生命力强", 1.2, 1.5, 1.2, 0.8);

    private final String name;
    private final String description;
    private final double attackModifier;
    private final double healthModifier;
    private final double defenseModifier;
    private final double recoveryModifier;

    Legacy(String name, String description, double attackModifier, double healthModifier, 
          double defenseModifier, double recoveryModifier) {
        this.name = name;
        this.description = description;
        this.attackModifier = attackModifier;
        this.healthModifier = healthModifier;
        this.defenseModifier = defenseModifier;
        this.recoveryModifier = recoveryModifier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAttackModifier() {
        return attackModifier;
    }

    public double getHealthModifier() {
        return healthModifier;
    }

    public double getDefenseModifier() {
        return defenseModifier;
    }

    public double getRecoveryModifier() {
        return recoveryModifier;
    }
} 