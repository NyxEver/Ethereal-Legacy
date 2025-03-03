package Game;

public class Character {
    private String name;
    private int level;
    private int health;
    private int mana;
    private String lingGen; // 灵根类型

    public Character(String name, String lingGen) {
        this.name = name;
        this.lingGen = lingGen;
        this.level = 1;
        this.health = 100;
        this.mana = 50;
    }

    public void levelUp() {
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

    // Getter和Setter
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }
    public String getLingGen() { return lingGen; }
}