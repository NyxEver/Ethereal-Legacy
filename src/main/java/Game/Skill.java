package Game;

public class Skill extends Item {
    private int damage;
    private int cost;

    public Skill(String name, String description, ItemGrade grade, int damage, int cost) {
        super(name, description, grade);
        this.damage = damage;
        this.cost = cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public void use(Character character) {
        if (character.getCurrentMana() >= cost) {
            System.out.println("使用技能：" + name);
            character.setCurrentMana(character.getCurrentMana() - cost);
            // TODO: 实现技能使用逻辑
        } else {
            System.out.println("灵力不足，无法使用技能");
        }
    }

    public static Skill[] getAllSkills() {
        return new Skill[] {
            // 凡铁级技能
            new Skill("基础剑法", "最基础的剑术", ItemGrade.MORTAL_IRON, 10, 5),
            new Skill("初级火球", "凝聚火元素的基础法术", ItemGrade.MORTAL_IRON, 12, 8),
            new Skill("风刃术", "操控风元素形成锋利气刃", ItemGrade.MORTAL_IRON, 11, 7),
            new Skill("土盾术", "凝聚土元素形成防护盾", ItemGrade.MORTAL_IRON, 8, 10),
            new Skill("水箭术", "凝聚水元素形成水箭", ItemGrade.MORTAL_IRON, 9, 6),
            new Skill("雷击术", "引导雷元素进行攻击", ItemGrade.MORTAL_IRON, 13, 9),
            new Skill("冰锥术", "凝聚寒冰形成冰锥", ItemGrade.MORTAL_IRON, 11, 7),
            new Skill("金刃术", "凝聚金元素形成利刃", ItemGrade.MORTAL_IRON, 12, 8),
            new Skill("木藤术", "操控植物进行缠绕", ItemGrade.MORTAL_IRON, 9, 6),
            new Skill("毒雾术", "释放有毒气体", ItemGrade.MORTAL_IRON, 10, 7),

            // 灵材级技能
            new Skill("灵剑术", "操控灵剑进行攻击", ItemGrade.SPIRIT_MATERIAL, 25, 15),
            new Skill("烈焰爆", "引发烈焰爆炸", ItemGrade.SPIRIT_MATERIAL, 28, 20),
            new Skill("狂风斩", "释放风刃旋风", ItemGrade.SPIRIT_MATERIAL, 26, 18),
            new Skill("大地护盾", "形成坚固的土元素护盾", ItemGrade.SPIRIT_MATERIAL, 22, 25),
            new Skill("水龙弹", "凝聚水龙进行攻击", ItemGrade.SPIRIT_MATERIAL, 24, 17),
            new Skill("雷霆万钧", "召唤雷霆打击", ItemGrade.SPIRIT_MATERIAL, 30, 22),
            new Skill("寒冰风暴", "释放寒冰风暴", ItemGrade.SPIRIT_MATERIAL, 27, 19),
            new Skill("金光剑阵", "布置金元素剑阵", ItemGrade.SPIRIT_MATERIAL, 29, 21),
            new Skill("藤蔓缠绕", "控制大量藤蔓", ItemGrade.SPIRIT_MATERIAL, 23, 16),
            new Skill("剧毒之云", "释放剧毒云雾", ItemGrade.SPIRIT_MATERIAL, 25, 18),

            // 玄金级技能
            new Skill("御剑术", "操控多把飞剑", ItemGrade.MYSTIC_GOLD, 40, 30),
            new Skill("火龙真诀", "召唤火龙", ItemGrade.MYSTIC_GOLD, 45, 35),
            new Skill("玄风斩", "释放玄风利刃", ItemGrade.MYSTIC_GOLD, 42, 32),
            new Skill("山岳护体", "形成山岳般的防护", ItemGrade.MYSTIC_GOLD, 38, 40),
            new Skill("碧海潮生", "掀起巨浪攻击", ItemGrade.MYSTIC_GOLD, 41, 31),
            new Skill("九天雷罚", "召唤天雷惩罚", ItemGrade.MYSTIC_GOLD, 47, 37),
            new Skill("冰封千里", "大范围冰冻", ItemGrade.MYSTIC_GOLD, 43, 33),
            new Skill("金光万剑", "凝聚万剑攻击", ItemGrade.MYSTIC_GOLD, 46, 36),
            new Skill("万木奇术", "控制周围植物", ItemGrade.MYSTIC_GOLD, 39, 29),
            new Skill("五毒秘术", "释放五种剧毒", ItemGrade.MYSTIC_GOLD, 44, 34),

            // 地宝级技能
            new Skill("天剑遁术", "剑光化虹", ItemGrade.EARTH_TREASURE, 60, 50),
            new Skill("三昧真火", "释放不灭真火", ItemGrade.EARTH_TREASURE, 65, 55),
            new Skill("大罗风袭", "掀起毁灭风暴", ItemGrade.EARTH_TREASURE, 62, 52),
            new Skill("不动金身", "形成金刚不坏之体", ItemGrade.EARTH_TREASURE, 58, 60),
            new Skill("沧海桑田", "改变地形地貌", ItemGrade.EARTH_TREASURE, 61, 51),
            new Skill("五雷正法", "掌控五种雷霆", ItemGrade.EARTH_TREASURE, 67, 57),
            new Skill("太阴玄冰", "释放太阴寒气", ItemGrade.EARTH_TREASURE, 63, 53),
            new Skill("纯阳剑气", "释放纯阳之力", ItemGrade.EARTH_TREASURE, 66, 56),
            new Skill("万木化龙", "植物化为神龙", ItemGrade.EARTH_TREASURE, 59, 49),
            new Skill("万毒噬心", "释放致命剧毒", ItemGrade.EARTH_TREASURE, 64, 54),

            // 天晶级技能
            new Skill("诛仙剑阵", "布置诛仙剑阵", ItemGrade.HEAVEN_CRYSTAL, 80, 70),
            new Skill("太阳真火", "释放太阳真火", ItemGrade.HEAVEN_CRYSTAL, 85, 75),
            new Skill("九天玄风", "掌控九天玄风", ItemGrade.HEAVEN_CRYSTAL, 82, 72),
            new Skill("混元金身", "达到金刚不坏", ItemGrade.HEAVEN_CRYSTAL, 78, 80),
            new Skill("四海龙吟", "召唤四海龙王", ItemGrade.HEAVEN_CRYSTAL, 81, 71),
            new Skill("九霄神雷", "召唤九霄神雷", ItemGrade.HEAVEN_CRYSTAL, 87, 77),
            new Skill("太阴神光", "释放太阴神光", ItemGrade.HEAVEN_CRYSTAL, 83, 73),
            new Skill("太极剑意", "领悟太极剑意", ItemGrade.HEAVEN_CRYSTAL, 86, 76),
            new Skill("扶桑神木", "召唤扶桑神木", ItemGrade.HEAVEN_CRYSTAL, 79, 69),
            new Skill("化龙毒经", "使用龙族剧毒", ItemGrade.HEAVEN_CRYSTAL, 84, 74),

            // 仙玉级技能
            new Skill("斩仙剑诀", "斩杀仙人的剑诀", ItemGrade.IMMORTAL_JADE, 100, 90),
            new Skill("混沌真火", "释放混沌真火", ItemGrade.IMMORTAL_JADE, 105, 95),
            new Skill("太清神风", "掌控太清神风", ItemGrade.IMMORTAL_JADE, 102, 92),
            new Skill("先天金身", "修成先天金身", ItemGrade.IMMORTAL_JADE, 98, 100),
            new Skill("应龙九变", "化身应龙", ItemGrade.IMMORTAL_JADE, 101, 91),
            new Skill("天罚神雷", "降下天罚神雷", ItemGrade.IMMORTAL_JADE, 107, 97),
            new Skill("太阴仙诀", "修炼太阴仙诀", ItemGrade.IMMORTAL_JADE, 103, 93),
            new Skill("天人剑道", "领悟天人剑道", ItemGrade.IMMORTAL_JADE, 106, 96),
            new Skill("建木神通", "掌控建木之力", ItemGrade.IMMORTAL_JADE, 99, 89),
            new Skill("九幽毒典", "修炼九幽毒功", ItemGrade.IMMORTAL_JADE, 104, 94),

            // 神源级技能
            new Skill("开天剑诀", "开天辟地的剑诀", ItemGrade.DIVINE_SOURCE, 120, 110),
            new Skill("混沌神火", "掌控混沌神火", ItemGrade.DIVINE_SOURCE, 125, 115),
            new Skill("鸿蒙神风", "掌控鸿蒙神风", ItemGrade.DIVINE_SOURCE, 122, 112),
            new Skill("造化金身", "修成造化金身", ItemGrade.DIVINE_SOURCE, 118, 120),
            new Skill("真龙九变", "化身真龙", ItemGrade.DIVINE_SOURCE, 121, 111),
            new Skill("混沌神雷", "掌控混沌神雷", ItemGrade.DIVINE_SOURCE, 127, 117),
            new Skill("太初玄冰", "掌控太初玄冰", ItemGrade.DIVINE_SOURCE, 123, 113),
            new Skill("道之剑意", "领悟道之剑意", ItemGrade.DIVINE_SOURCE, 126, 116),
            new Skill("生命神木", "掌控生命之树", ItemGrade.DIVINE_SOURCE, 119, 109),
            new Skill("毒之本源", "掌控毒之本源", ItemGrade.DIVINE_SOURCE, 124, 114)
        };
    }
} 