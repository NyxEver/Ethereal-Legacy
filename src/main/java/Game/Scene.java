package Game;

public abstract class Scene {
    protected String name;
    protected String description;
    protected double dangerLevel; // 危险等级，影响掉落和敌人强度
    protected CultivationRealm requiredRealm; // 进入所需最低境界

    public Scene(String name, String description, double dangerLevel, CultivationRealm requiredRealm) {
        this.name = name;
        this.description = description;
        this.dangerLevel = dangerLevel;
        this.requiredRealm = requiredRealm;
    }

    public abstract void onEnter(Character player);
    public abstract void explore(Character player);
    public abstract void generateRewards(Character player);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getDangerLevel() {
        return dangerLevel;
    }

    public CultivationRealm getRequiredRealm() {
        return requiredRealm;
    }

    protected boolean checkRequirements(Character player) {
        if (player.getRealm().ordinal() < requiredRealm.ordinal()) {
            System.out.println("警告：你的境界不足以进入" + name + "，至少需要" + requiredRealm.getName() + "境界。");
            return false;
        }
        return true;
    }
} 