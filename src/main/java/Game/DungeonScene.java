package Game;

import java.util.Random;

public class DungeonScene extends Scene {
    private int currentDepth; // 当前深度
    private boolean hasTrialEnergy; // 是否有天劫残余能量
    private boolean hasGhostSkull; // 是否有头骨封印
    private boolean hasDragonSoul; // 是否有龙魂
    private boolean hasBloodPool; // 是否有血池
    private boolean hasTimeDistortion; // 是否有时空错乱

    public DungeonScene(String name, String description, double dangerLevel, CultivationRealm requiredRealm) {
        super(name, description, dangerLevel, requiredRealm);
        this.currentDepth = 0;
        updateEnvironmentalEffects();
    }

    private void updateEnvironmentalEffects() {
        hasTrialEnergy = name.equals("九劫渊");
        hasGhostSkull = name.equals("千骸井");
        hasDragonSoul = name.equals("锁龙窟");
        hasBloodPool = name.equals("血髓洞");
        hasTimeDistortion = name.equals("无间鬼牢");
    }

    @Override
    public void onEnter(Character player) {
        if (!checkRequirements(player)) {
            return;
        }

        System.out.println("进入" + name + "...");
        System.out.println(description);
        currentDepth = 0;

        // 特殊环境效果
        if (hasTrialEnergy) {
            System.out.println("每深入百丈都会遭遇一种天劫残余能量的考验...");
        }

        if (hasGhostSkull) {
            System.out.println("无数修士头骨中散发出不详的气息...");
        }

        if (hasDragonSoul) {
            System.out.println("一股腐朽的龙威若隐若现...");
        }

        if (hasBloodPool) {
            System.out.println("血池中不断涌出血煞之气...");
        }

        if (hasTimeDistortion) {
            System.out.println("时空在这里变得混乱，小心迷失在永恒中...");
        }
    }

    @Override
    public void explore(Character player) {
        Random random = new Random();
        currentDepth += 100; // 每次探索深入100丈

        // 特殊环境效果
        if (hasTrialEnergy && currentDepth % 100 == 0) {
            handleTrialEnergy(player);
        }

        if (hasGhostSkull && random.nextDouble() < 0.3) {
            System.out.println("头骨中的怨魂开始躁动！");
            Monster ghost = new Monster(player.getLevel());
            ghost.setRace(Race.DEMON);
            Battle battle = new Battle(player, ghost);
            battle.start();
        }

        if (hasDragonSoul && random.nextDouble() < 0.2) {
            System.out.println("金龙残魂显现！");
            if (random.nextDouble() < 0.5) {
                System.out.println("龙魂被你的气息吸引，传授了你一些感悟！");
                player.addExp(200 * player.getLevel());
            } else {
                System.out.println("龙魂陷入狂暴！");
                Monster dragonSoul = new Monster(player.getLevel() + 3);
                dragonSoul.setRace(Race.MONSTER);
                Battle battle = new Battle(player, dragonSoul);
                battle.start();
            }
        }

        if (hasBloodPool) {
            if (random.nextDouble() < 0.4) {
                System.out.println("发现一块血煞晶！");
                Material bloodCrystal = new Material("血煞晶", "蕴含强大血煞之力的晶石", ItemGrade.MYSTIC_GOLD, MaterialGrade.MEDIUM_HIGH);
                player.getInventory().addItem(bloodCrystal);
            }
        }

        if (hasTimeDistortion) {
            if (random.nextDouble() < 0.1) {
                System.out.println("你遇到了千年前进入此地的一位修士！");
                Monster ancientCultivator = new Monster(player.getLevel() + 2);
                ancientCultivator.setRace(Race.HUMAN);
                Battle battle = new Battle(player, ancientCultivator);
                battle.start();
                // 击败后获得特殊奖励
                if (player.getHealth() > 0) {
                    System.out.println("你获得了这位修士的全部遗产！");
                    generateAncientLegacy(player);
                }
            }
        }

        // 常规探索
        double encounterRoll = random.nextDouble();
        if (encounterRoll < 0.8) { // 80%概率遇到敌人
            System.out.println("遭遇敌人！");
            Monster monster = new Monster(player.getLevel());
            // 深度越深，敌人越强
            monster.setLevel(monster.getLevel() + (currentDepth / 200));
            monster.setRace(random.nextBoolean() ? Race.MONSTER : Race.DEMON);
            Battle battle = new Battle(player, monster);
            battle.start();
        } else {
            System.out.println("发现一处安全地带...");
            generateRewards(player);
        }
    }

    private void handleTrialEnergy(Character player) {
        Random random = new Random();
        String[] trialTypes = {"雷劫", "火劫", "风劫", "水劫", "土劫", "心魔劫", "空间劫", "时间劫", "因果劫"};
        String trialType = trialTypes[random.nextInt(trialTypes.length)];
        
        System.out.println("触发" + trialType + "残余！");
        int damage = 20 + random.nextInt(30) + (currentDepth / 100) * 10;
        
        if (random.nextDouble() < 0.6) { // 60%概率成功渡劫
            System.out.println("你成功抵御了" + trialType + "的考验！");
            System.out.println("获得感悟，修为大增！");
            player.addExp(150 * player.getLevel());
        } else {
            System.out.println("你未能完全抵御" + trialType + "，受到" + damage + "点伤害！");
            player.setHealth(player.getHealth() - damage);
        }
    }

    private void generateAncientLegacy(Character player) {
        Random random = new Random();
        // 获得大量灵石
        int lingShi = 1000 + random.nextInt(2000);
        player.getLingShi().add(lingShi);
        System.out.println("获得灵石：" + lingShi);

        // 获得随机高级物品
        if (random.nextDouble() < 0.7) {
            Weapon[] weapons = Weapon.getAllWeapons();
            Weapon weapon = getHighGradeItem(weapons);
            player.getInventory().addItem(weapon);
        }

        // 获得随机高级技能
        if (random.nextDouble() < 0.5) {
            Skill[] skills = Skill.getAllSkills();
            Skill skill = getHighGradeSkill(skills);
            player.getInventory().addSkill(skill);
        }
    }

    @Override
    public void generateRewards(Character player) {
        Random random = new Random();
        
        // 基础奖励
        double rewardRoll = random.nextDouble();
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
        
        // 额外获得灵石，深度越深越多
        int lingShi = 50 + random.nextInt(50) * player.getLevel() + (currentDepth / 100) * 20;
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

    private Skill getHighGradeSkill(Skill[] skills) {
        Random random = new Random();
        java.util.List<Skill> highGradeSkills = new java.util.ArrayList<>();
        for (Skill skill : skills) {
            if (skill.getGrade().ordinal() >= SkillGrade.MYSTIC.ordinal()) {
                highGradeSkills.add(skill);
            }
        }
        return highGradeSkills.get(random.nextInt(highGradeSkills.size()));
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