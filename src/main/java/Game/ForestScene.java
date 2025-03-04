package Game;

import java.util.Random;

public class ForestScene extends Scene {
    private boolean isMoonNight; // 是否是月圆之夜
    private boolean hasSpacialRift; // 是否有空间裂隙
    private boolean hasDragonAura; // 是否有龙威影响
    private boolean hasThunderEnergy; // 是否有雷霆能量
    private boolean hasDreamFlower; // 是否有致幻花海

    public ForestScene(String name, String description, double dangerLevel, CultivationRealm requiredRealm) {
        super(name, description, dangerLevel, requiredRealm);
        updateEnvironmentalEffects();
    }

    private void updateEnvironmentalEffects() {
        Random random = new Random();
        // 月圆之夜有20%概率出现
        isMoonNight = random.nextDouble() < 0.2;
        hasSpacialRift = name.equals("无回林");
        hasDragonAura = name.equals("龙骸森");
        hasThunderEnergy = name.equals("千劫雷桦林");
        hasDreamFlower = name.equals("醉梦花海");
    }

    @Override
    public void onEnter(Character player) {
        if (!checkRequirements(player)) {
            return;
        }

        System.out.println("进入" + name + "...");
        System.out.println(description);

        // 特殊环境效果
        if (isMoonNight && name.equals("蚀月古林")) {
            System.out.println("今夜是月圆之夜，树木渗出银血，妖兽更容易化形！");
            dangerLevel *= 1.5;
        }

        if (hasSpacialRift) {
            System.out.println("浓雾中隐藏着空间裂隙，稍不注意就会迷失方向...");
            if (player.getRealm().ordinal() < CultivationRealm.NASCENT_SOUL.ordinal()) {
                System.out.println("你的修为不足以抵抗空间裂隙的影响，将受到额外伤害！");
            }
        }

        if (hasDragonAura) {
            System.out.println("上古青龙的威压仍在此地徘徊，影响着周围的一切生灵...");
        }

        if (hasThunderEnergy) {
            System.out.println("空气中充斥着雷霆之力，稍有不慎就会引来天雷轰击...");
        }

        if (hasDreamFlower) {
            System.out.println("空气中弥漫着致幻的花香，需要时刻保持清醒...");
        }
    }

    @Override
    public void explore(Character player) {
        Random random = new Random();
        
        // 特殊环境伤害判定
        if (hasSpacialRift && player.getRealm().ordinal() < CultivationRealm.NASCENT_SOUL.ordinal()) {
            int damage = 20 + random.nextInt(30);
            System.out.println("空间裂隙的力量撕扯着你的身体，造成" + damage + "点伤害！");
            player.setHealth(player.getHealth() - damage);
        }

        if (hasDragonAura && random.nextDouble() < 0.3) {
            System.out.println("龙威影响下，周围的噬灵藤变得极其狂暴！");
            Monster vine = new Monster(player.getLevel() + 2);
            vine.setRace(Race.MONSTER);
            Battle battle = new Battle(player, vine);
            battle.start();
        }

        if (hasThunderEnergy && random.nextDouble() < 0.2) {
            System.out.println("一道天雷劈向你所在的位置！");
            if (random.nextDouble() < 0.5) {
                System.out.println("你成功躲避了天雷的轰击！");
            } else {
                int damage = 30 + random.nextInt(40);
                System.out.println("天雷击中了你，造成" + damage + "点伤害！");
                player.setHealth(player.getHealth() - damage);
            }
        }

        if (hasDreamFlower && random.nextDouble() < 0.25) {
            System.out.println("你不小心吸入了过量的致幻花粉...");
            if (random.nextDouble() < 0.7) {
                System.out.println("你及时运转功法抵抗了花粉的影响！");
            } else {
                System.out.println("你陷入了沉睡，成为了花海的养分...");
                player.setHealth(0);
                return;
            }
        }

        // 常规探索
        double encounterRoll = random.nextDouble();
        if (encounterRoll < 0.45) { // 45%概率遇到人类
            if (random.nextDouble() < 0.25) { // 25%是友善单位
                System.out.println("遇到友善的修士！");
                generateRewards(player);
            } else {
                System.out.println("遇到敌对修士！");
                Monster enemy = new Monster(player.getLevel());
                enemy.setRace(Race.HUMAN);
                Battle battle = new Battle(player, enemy);
                battle.start();
            }
        } else { // 其他情况遇到妖魔
            System.out.println("遭遇敌人！");
            Monster monster = new Monster(player.getLevel());
            // 月圆之夜的妖兽更强大
            if (isMoonNight && name.equals("蚀月古林")) {
                monster.setLevel(monster.getLevel() + 2);
            }
            monster.setRace(random.nextBoolean() ? Race.MONSTER : Race.DEMON);
            Battle battle = new Battle(player, monster);
            battle.start();
        }
    }

    @Override
    public void generateRewards(Character player) {
        Random random = new Random();
        double rewardRoll = random.nextDouble();

        // 月圆之夜额外奖励
        if (isMoonNight && name.equals("蚀月古林")) {
            System.out.println("获得月华精华，修为大增！");
            player.addExp(100 * player.getLevel());
        }

        // 常规奖励
        if (rewardRoll < 0.4) { // 40%概率获得材料
            Material[] materials = Material.getAllMaterials();
            Material material = getItemByLevel(materials, player.getLevel());
            player.getInventory().addItem(material);
        } else if (rewardRoll < 0.7) { // 30%概率获得武器
            Weapon[] weapons = Weapon.getAllWeapons();
            Weapon weapon = getItemByLevel(weapons, player.getLevel());
            player.getInventory().addItem(weapon);
        } else { // 30%概率获得技能
            Skill[] allSkills = Skill.getAllSkills();
            Skill newSkill = getSkillByLevel(allSkills, player.getLevel());
            player.getInventory().addSkill(newSkill);
        }
        
        // 额外获得灵石
        int lingShi = 50 + random.nextInt(50) * player.getLevel();
        player.getLingShi().add(lingShi);
        System.out.println("获得灵石：" + lingShi);
    }

    private <T extends Item> T getItemByLevel(T[] items, int playerLevel) {
        Random random = new Random();
        CultivationRealm playerRealm = CultivationRealm.getRealmByLevel(playerLevel);
        
        // 筛选出适合玩家境界的物品
        java.util.List<T> suitableItems = new java.util.ArrayList<>();
        for (T item : items) {
            if (isItemSuitableForRealm(item, playerRealm)) {
                suitableItems.add(item);
            }
        }
        
        if (suitableItems.isEmpty()) {
            return items[0]; // 返回最基础的物品
        }
        
        return suitableItems.get(random.nextInt(suitableItems.size()));
    }

    private Skill getSkillByLevel(Skill[] skills, int playerLevel) {
        Random random = new Random();
        CultivationRealm playerRealm = CultivationRealm.getRealmByLevel(playerLevel);
        
        // 筛选出适合玩家境界的技能
        java.util.List<Skill> suitableSkills = new java.util.ArrayList<>();
        for (Skill skill : skills) {
            if (isSkillSuitableForRealm(skill, playerRealm)) {
                suitableSkills.add(skill);
            }
        }
        
        if (suitableSkills.isEmpty()) {
            return skills[0]; // 返回最基础的技能
        }
        
        return suitableSkills.get(random.nextInt(suitableSkills.size()));
    }

    private boolean isItemSuitableForRealm(Item item, CultivationRealm realm) {
        if (item.getGrade() == null) return true;
        
        return switch (realm) {
            case MORTAL_BODY, MERIDIAN_OPENING -> item.getGrade() == ItemGrade.MORTAL_IRON;
            case QI_CONDENSATION, FOUNDATION_BUILDING -> item.getGrade() == ItemGrade.SPIRIT_MATERIAL;
            case GOLDEN_CORE, NASCENT_SOUL -> item.getGrade() == ItemGrade.MYSTIC_GOLD;
            case SPIRIT_SEVERING -> item.getGrade() == ItemGrade.EARTH_TREASURE;
            case TRIBULATION -> item.getGrade() == ItemGrade.HEAVEN_CRYSTAL;
            case IMMORTAL -> item.getGrade() == ItemGrade.IMMORTAL_JADE;
            default -> false;
        };
    }

    private boolean isSkillSuitableForRealm(Skill skill, CultivationRealm realm) {
        return switch (realm) {
            case MORTAL_BODY, MERIDIAN_OPENING -> skill.getGrade() == ItemGrade.MORTAL_IRON;
            case QI_CONDENSATION, FOUNDATION_BUILDING -> skill.getGrade() == ItemGrade.SPIRIT_MATERIAL;
            case GOLDEN_CORE, NASCENT_SOUL -> skill.getGrade() == ItemGrade.MYSTIC_GOLD;
            case SPIRIT_SEVERING -> skill.getGrade() == ItemGrade.EARTH_TREASURE;
            case TRIBULATION -> skill.getGrade() == ItemGrade.HEAVEN_CRYSTAL;
            case IMMORTAL -> skill.getGrade() == ItemGrade.IMMORTAL_JADE;
            default -> false;
        };
    }
} 