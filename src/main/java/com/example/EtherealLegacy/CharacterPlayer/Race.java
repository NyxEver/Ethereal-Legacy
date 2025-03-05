package com.example.EtherealLegacy.CharacterPlayer;

public enum Race {
    HUMAN("人类", 100, 50),
    DEMON("魔族", 60, 90),
    MONSTER("妖族", 150, 20);

    private final String name;
    private final int baseHealth;
    private final int baseMana;

    Race(String name, int baseHealth, int baseMana) {
        this.name = name;
        this.baseHealth = baseHealth;
        this.baseMana = baseMana;
    }

    public String getName() { return name; }
    public int getBaseHealth() { return baseHealth; }
    public int getBaseMana() { return baseMana; }
} 