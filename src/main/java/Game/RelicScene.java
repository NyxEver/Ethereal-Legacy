package Game;

import java.util.Random;

public class RelicScene extends Scene {
    private boolean hasHeavyGravity; // 是否有重力场
    private boolean hasHeartDemon; // 是否有心魔劫
    private boolean hasVortex; // 是否有漩涡
    private boolean hasWarSoul; // 是否有战魂
    private boolean hasTimeAnomaly; // 是否有时间异常

    public RelicScene(String name, String description, double dangerLevel, CultivationRealm requiredRealm) {
        super(name, description, dangerLevel, requiredRealm);
        updateEnvironmentalEffects();
    }

    private void updateEnvironmentalEffects() {
        hasHeavyGravity = name.equals("堕星城");
        hasHeartDemon = name.equals("大罗天残碑");
        hasVortex = name.equals("归墟海眼");
        hasWarSoul = name.equals("兵冢荒原");
        hasTimeAnomaly = name.equals("逆光阴殿");
    }

    @Override
    public void onEnter(Character player) {
        if (!checkRequirements(player)) {
            return;
        }

        System.out.println("进入" + name + "...");
        System.out.println(description);

        // 特殊环境效果
        if (hasHeavyGravity) {
            System.out.println("这里的重力是外界的三十倍，行动会受到极大影响...");
        }

        if (hasHeartDemon) {
            System.out.println("残碑上的功法引动心魔，需要极强的心境才能参悟...");
        }

        if (hasVortex) {
            System.out.println("海底漩涡中隐藏着蓬莱仙岛的遗迹...");
        }

        if (hasWarSoul) {
            System.out.println("无数上古神魔兵器的战魂在此游荡...");
        }

        if (hasTimeAnomaly) {
            System.out.println("时间在这里流速紊乱，当心遇到未来的自己...");
        }

        // 第一次进入遗迹的奖励
        if (!player.hasVisitedRelic()) {
            System.out.println("首次探索遗迹，获得灵根源泉！");
            player.setHasVisitedRelic(true);
            // 增加大量经验
            player.addExp(500 * player.getLevel());
        }
    }

    @Override
    public void explore(Character player) {
        Random random = new Random();

        // 特殊环境效果
        if (hasHeavyGravity) {
            System.out.println("重力场影响下，你的行动变得异常困难...");
            int damage = 10 + random.nextInt(20);
            System.out.println("受到" + damage + "点重力伤害！");
            player.setHealth(player.getHealth() - damage);
        }

        if (hasHeartDemon && random.nextDouble() < 0.3) {
            System.out.println("残碑上的功法引发心魔显现！");
            if (random.nextDouble() < 0.6) {
                System.out.println("你成功降伏心魔，获得功法感悟！");
                player.addExp(300 * player.getLevel());
            } else {
                System.out.println("心魔影响下，你陷入混乱！");
                int damage = 40 + random.nextInt(40);
                System.out.println("受到" + damage + "点心魔伤害！");
                player.setHealth(player.getHealth() - damage);
            }
        }

        if (hasVortex && random.nextDouble() < 0.2) {
            System.out.println("发现一件蓬莱遗宝！");
            Weapon[] weapons = Weapon.getAllWeapons();
            Weapon weapon = getHighGradeItem(weapons);
            player.getInventory().addItem(weapon);
        }

        if (hasWarSoul && random.nextDouble() < 0.4) {
            System.out.println("一柄上古神兵的战魂苏醒了！");
            Monster warSoul = new Monster(player.getLevel() + 2);
            warSoul.setRace(Race.DEMON);
            Battle battle = new Battle(player, warSoul);
            battle.start();
            
            if (player.getHealth() > 0 && random.nextDouble() < 0.3) {
                System.out.println("你成功降服了战魂，获得了这件神兵！");
                Weapon[] weapons = Weapon.getAllWeapons();
                Weapon weapon = getHighGradeItem(weapons);
                player.getInventory().addItem(weapon);
            }
        }

        if (hasTimeAnomaly && random.nextDouble() < 0.15) {
            System.out.println("你遇到了未来的自己！");
            if (random.nextDouble() < 0.5) {
                System.out.println("未来的你传授了一些修炼经验...");
                player.addExp(400 * player.getLevel());
            } else {
                System.out.println("看到自己未来化为灰烬的景象，你受到了巨大的心灵冲击！");
                int damage = 50 + random.nextInt(50);
                System.out.println("受到" + damage + "点心灵伤害！");
                player.setHealth(player.getHealth() - damage);
            }
        }

        // 常规探索
        double encounterRoll = random.nextDouble();
        if (encounterRoll < 0.7) { // 70%概率遇到敌人
            System.out.println("遭遇敌人！");
            Monster monster = new Monster(player.getLevel() + 1);
            // 遗迹中的敌人更强大
            monster.setLevel(monster.getLevel() + 2);
            monster.setRace(Race.values()[random.nextInt(Race.values().length)]);
            Battle battle = new Battle(player, monster);
            battle.start();
        } else {
            System.out.println("发现一处安全地带...");
            generateRewards(player);
        }
    }

    @Override
    public void generateRewards(Character player) {
        Random random = new Random();
        
        // 基础奖励
        double rewardRoll = random.nextDouble();
        if (rewardRoll < 0.3) { // 30%概率获得材料
            Material[] materials = Material.getAllMaterials();
            Material material = getItemByLevel(materials, player.getLevel());
            player.getInventory().addItem(material);
        } else if (rewardRoll < 0.7) { // 40%概率获得武器
            Weapon[] weapons = Weapon.getAllWeapons();
            Weapon weapon = getItemByLevel(weapons, player.getLevel());
            player.getInventory().addItem(weapon);
        } else { // 30%概率获得技能
            Skill[] allSkills = Skill.getAllSkills();
            Skill newSkill = getSkillByLevel(allSkills, player.getLevel());
            player.getInventory().addSkill(newSkill);
        }
        
        // 额外获得灵石
        int lingShi = 100 + random.nextInt(100) * player.getLevel();
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

    private Weapon getHighGradeItem(Weapon[] items) {
        Random random = new Random();
        java.util.List<Weapon> highGradeItems = new java.util.ArrayList<>();
        for (Weapon item : items) {
            if (item.getGrade().ordinal() >= ItemGrade.MYSTIC_GOLD.ordinal()) {
                highGradeItems.add(item);
            }
        }
        return highGradeItems.get(random.nextInt(highGradeItems.size()));
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