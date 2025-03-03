package Game;

public class Character {
    private String name;
    private int level;
    private int health;
    private int mana;
    private String lingGen; // 灵根类型
    private int exp;
    private int expToNextLevel;

    public Character(String name, String lingGen) {
        this.name = name;
        this.lingGen = lingGen;
        this.exp = 0;
        this.expToNextLevel = calculateExpToNextLevel();
        this.health = calculateHealth();
        this.mana = calculateMana();
    }

    public void gainExp(int amount) {
        exp += amount;
        while (exp >= expToNextLevel) {
            exp -= expToNextLevel;
            levelUp();
            expToNextLevel = calculateExpToNextLevel();
        }
    }

    private void levelUp() {
        level++;
        health = calculateHealth();
        mana = calculateMana();
        System.out.println(name + " 升级至 " + level + " 级！");
    }

    private int calculateExpToNextLevel() {
        return (int)(50 * (1 + level * 0.5));
    }

    private int calculateHealth() {
        int baseHealth = 100;
        return (int)(baseHealth * (1 + level * 0.008)) + getExtraHealth();
    }

    private int calculateMana() {
        int baseMana = 50;
        return (int)(baseMana * (1 + level * 0.008)) + getExtraMana();
    }

    private int getExtraHealth() { return 0; } // 待实现武器/道具加成
    private int getExtraMana() { return 0; } // 待实现武器/道具加成

    // Getter 方法
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMana() { return mana; }
    public int getExp() { return exp; }
    public int getExpToNextLevel() { return expToNextLevel; }
    public void setHealth(int health) { this.health = health; }
}
    /*public void levelUp() {
        if ("天灵根".equals(lingGen) || level < getMaxLevel()) {
            level++;
            health += 10;
            mana += 5;
            System.out.println(name + " 升级至 " + level + " 级！");
        } else {
            System.out.println("已达等级上限！");
        }
    }


    private int getMaxLevel() {
        switch (lingGen) {
            case "真灵根": return 50;
            case "天灵根": return Integer.MAX_VALUE;
            default: return 30; // 基础灵根
        }
    }
     */