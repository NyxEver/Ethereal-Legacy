package com.example.EtherealLegacy.Object;

import com.example.EtherealLegacy.CharacterPlayer.Character;

public class Weapon extends Item {
    private int damage;

    public Weapon(String name, String description, ItemGrade grade, int damage) {
        super(name, description, grade);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void use(Character character) {
        System.out.println("装备武器：" + name);
        // TODO: 实现武器装备逻辑
    }

    public static Weapon[] getAllWeapons() {
        return new Weapon[] {
            // 凡铁级武器
            new Weapon("铁剑", "普通的铁剑", ItemGrade.MORTAL_IRON, 10),
            new Weapon("青铜刀", "青铜铸造的刀", ItemGrade.MORTAL_IRON, 11),
            new Weapon("木杖", "普通的木杖", ItemGrade.MORTAL_IRON, 9),
            new Weapon("石斧", "石头打造的斧头", ItemGrade.MORTAL_IRON, 12),
            new Weapon("铁矛", "普通的铁矛", ItemGrade.MORTAL_IRON, 11),
            new Weapon("铜锤", "铜制的锤子", ItemGrade.MORTAL_IRON, 13),
            new Weapon("竹笛", "普通的竹笛", ItemGrade.MORTAL_IRON, 8),
            new Weapon("布鞭", "布条编织的鞭子", ItemGrade.MORTAL_IRON, 9),
            new Weapon("铁针", "锋利的铁针", ItemGrade.MORTAL_IRON, 7),
            new Weapon("石环", "石头打磨的环", ItemGrade.MORTAL_IRON, 8),
            new Weapon("木弓", "普通的木弓", ItemGrade.MORTAL_IRON, 10),
            new Weapon("铁镖", "铁制的飞镖", ItemGrade.MORTAL_IRON, 9),
            new Weapon("布索", "布条编织的绳索", ItemGrade.MORTAL_IRON, 8),
            new Weapon("铜剑", "铜制的剑", ItemGrade.MORTAL_IRON, 11),
            new Weapon("石锤", "石头打造的锤子", ItemGrade.MORTAL_IRON, 12),

            // 灵材级武器
            new Weapon("灵铁剑", "以灵铁打造的剑", ItemGrade.SPIRIT_MATERIAL, 35),
            new Weapon("青锋刀", "锋利的灵刀", ItemGrade.SPIRIT_MATERIAL, 36),
            new Weapon("火木杖", "蕴含火属性的木杖", ItemGrade.SPIRIT_MATERIAL, 34),
            new Weapon("玄铁斧", "玄铁打造的斧头", ItemGrade.SPIRIT_MATERIAL, 37),
            new Weapon("蛇牙矛", "妖蛇牙齿制成的矛", ItemGrade.SPIRIT_MATERIAL, 36),
            new Weapon("雷击锤", "雷击木制成的锤子", ItemGrade.SPIRIT_MATERIAL, 38),
            new Weapon("通灵笛", "可以引动灵气的笛子", ItemGrade.SPIRIT_MATERIAL, 33),
            new Weapon("龙筋鞭", "龙筋制成的鞭子", ItemGrade.SPIRIT_MATERIAL, 34),
            new Weapon("寒冰针", "寒冰凝结的长针", ItemGrade.SPIRIT_MATERIAL, 32),
            new Weapon("风灵环", "风属性的法器", ItemGrade.SPIRIT_MATERIAL, 33),
            new Weapon("灵木弓", "灵木制成的长弓", ItemGrade.SPIRIT_MATERIAL, 35),
            new Weapon("飞灵镖", "注入灵力的飞镖", ItemGrade.SPIRIT_MATERIAL, 34),
            new Weapon("缚妖索", "专门捆缚妖魔的绳索", ItemGrade.SPIRIT_MATERIAL, 33),
            new Weapon("青蛇剑", "青蛇毒牙制成的剑", ItemGrade.SPIRIT_MATERIAL, 36),
            new Weapon("震雷锤", "可以引动雷电的锤子", ItemGrade.SPIRIT_MATERIAL, 37),

            // 玄金级武器
            new Weapon("紫金剑", "紫金铸造的宝剑", ItemGrade.MYSTIC_GOLD, 50),
            new Weapon("青龙刀", "具有龙威的神刀", ItemGrade.MYSTIC_GOLD, 51),
            new Weapon("玄天杖", "引动天地之力的法杖", ItemGrade.MYSTIC_GOLD, 49),
            new Weapon("离火斧", "附带火焰之力的战斧", ItemGrade.MYSTIC_GOLD, 52),
            new Weapon("太阴矛", "蕴含太阴之力的长矛", ItemGrade.MYSTIC_GOLD, 51),
            new Weapon("玄金锤", "玄金打造的神锤", ItemGrade.MYSTIC_GOLD, 53),
            new Weapon("天音笛", "可以引动天地之音的宝笛", ItemGrade.MYSTIC_GOLD, 48),
            new Weapon("九节鞭", "九节玄金打造的长鞭", ItemGrade.MYSTIC_GOLD, 49),
            new Weapon("星光针", "凝聚星光之力的银针", ItemGrade.MYSTIC_GOLD, 47),
            new Weapon("太极环", "蕴含阴阳之力的法环", ItemGrade.MYSTIC_GOLD, 48),
            new Weapon("射日弓", "可以射日的神弓", ItemGrade.MYSTIC_GOLD, 50),
            new Weapon("追魂镖", "能够追踪敌人的飞镖", ItemGrade.MYSTIC_GOLD, 49),
            new Weapon("缚龙索", "可以捆缚真龙的绳索", ItemGrade.MYSTIC_GOLD, 48),
            new Weapon("青虹剑", "散发青色虹光的宝剑", ItemGrade.MYSTIC_GOLD, 51),
            new Weapon("镇魔锤", "专门镇压妖魔的神锤", ItemGrade.MYSTIC_GOLD, 52),

            // 地宝级武器
            new Weapon("元磁神光砂轮", "以元磁神光砂打造的飞轮", ItemGrade.EARTH_TREASURE, 52),
            new Weapon("太阴真水镜", "以太阴真水凝结的法镜", ItemGrade.EARTH_TREASURE, 51),
            new Weapon("太阳精火灯", "以太阳精火炼制的宝灯", ItemGrade.EARTH_TREASURE, 53),
            new Weapon("先天戊土印", "以先天戊土炼制的法印", ItemGrade.EARTH_TREASURE, 50),
            new Weapon("玄黄母气鼎", "以玄黄母气炼制的宝鼎", ItemGrade.EARTH_TREASURE, 54),
            new Weapon("九幽玄冥剑", "九幽玄冥石打造的神剑", ItemGrade.EARTH_TREASURE, 51),
            new Weapon("昆仑玉斧", "昆仑山玉石打造的玉斧", ItemGrade.EARTH_TREASURE, 52),
            new Weapon("朱雀羽扇", "朱雀真羽编织的羽扇", ItemGrade.EARTH_TREASURE, 50),
            new Weapon("白虎牙刃", "白虎獠牙打造的利刃", ItemGrade.EARTH_TREASURE, 53),
            new Weapon("玄武甲盾", "玄武甲片打造的神盾", ItemGrade.EARTH_TREASURE, 49),
            new Weapon("青龙鳞枪", "青龙鳞片打造的神枪", ItemGrade.EARTH_TREASURE, 52),
            new Weapon("混沌石钟", "混沌石胚打造的宝钟", ItemGrade.EARTH_TREASURE, 51),
            new Weapon("息壤珠", "息壤凝结而成的宝珠", ItemGrade.EARTH_TREASURE, 50),
            new Weapon("建木杖", "以建木残枝打造的法杖", ItemGrade.EARTH_TREASURE, 53),
            new Weapon("麒麟角弓", "麒麟角碎片打造的神弓", ItemGrade.EARTH_TREASURE, 54),

            // 天晶级武器
            new Weapon("周天星辰砂剑", "以周天星辰砂打造的神剑", ItemGrade.HEAVEN_CRYSTAL, 70),
            new Weapon("紫霄雷纹玉玺", "以紫霄雷纹玉打造的玉玺", ItemGrade.HEAVEN_CRYSTAL, 72),
            new Weapon("天河弱水绫", "以天河弱水凝结的丝绫", ItemGrade.HEAVEN_CRYSTAL, 71),
            new Weapon("大日金乌羽箭", "以金乌羽毛打造的神箭", ItemGrade.HEAVEN_CRYSTAL, 73),
            new Weapon("太虚玄冰棺", "以太虚玄冰打造的冰棺", ItemGrade.HEAVEN_CRYSTAL, 74),
            new Weapon("混元一气石斧", "以混元一气石打造的神斧", ItemGrade.HEAVEN_CRYSTAL, 72),
            new Weapon("弑神枪残片矛", "以弑神枪残片打造的神矛", ItemGrade.HEAVEN_CRYSTAL, 71),
            new Weapon("东皇钟碎片铃", "以东皇钟碎片打造的神铃", ItemGrade.HEAVEN_CRYSTAL, 73),
            new Weapon("诛仙剑煞气符", "以诛仙剑煞气凝结的符箓", ItemGrade.HEAVEN_CRYSTAL, 70),
            new Weapon("盘古斧印记凿", "以盘古斧印记打造的神凿", ItemGrade.HEAVEN_CRYSTAL, 74),
            new Weapon("九转造化玉瓶", "以九转造化玉打造的宝瓶", ItemGrade.HEAVEN_CRYSTAL, 72),
            new Weapon("鸿蒙紫气丝拂尘", "以鸿蒙紫气丝编织的拂尘", ItemGrade.HEAVEN_CRYSTAL, 71),
            new Weapon("天道劫雷结晶锤", "以天道劫雷结晶打造的神锤", ItemGrade.HEAVEN_CRYSTAL, 73),
            new Weapon("三生石屑笔", "以三生石屑打造的神笔", ItemGrade.HEAVEN_CRYSTAL, 70),
            new Weapon("轮回镜粉尘纱", "以轮回镜粉尘织就的薄纱", ItemGrade.HEAVEN_CRYSTAL, 74),

            // 仙玉级武器
            new Weapon("瑶池琼浆凝晶瓶", "以瑶池琼浆凝晶打造的宝瓶", ItemGrade.IMMORTAL_JADE, 90),
            new Weapon("蟠桃树心杖", "以蟠桃树心打造的神杖", ItemGrade.IMMORTAL_JADE, 92),
            new Weapon("兜率宫炉灰鼎", "以兜率宫炉灰打造的宝鼎", ItemGrade.IMMORTAL_JADE, 91),
            new Weapon("斩仙台煞血刀", "以斩仙台煞血打造的神刀", ItemGrade.IMMORTAL_JADE, 93),
            new Weapon("天河定底神珍铁棍", "以天河定底神珍铁打造的神棍", ItemGrade.IMMORTAL_JADE, 94),
            new Weapon("广寒月魄轮", "以广寒月魄打造的月轮", ItemGrade.IMMORTAL_JADE, 92),
            new Weapon("金乌精魄灯", "以金乌精魄点燃的神灯", ItemGrade.IMMORTAL_JADE, 91),
            new Weapon("十二品莲台残瓣", "十二品莲台的残瓣", ItemGrade.IMMORTAL_JADE, 93),
            new Weapon("封神榜残页卷轴", "封神榜的残页", ItemGrade.IMMORTAL_JADE, 90),
            new Weapon("打神鞭灵屑链", "以打神鞭灵屑串成的链子", ItemGrade.IMMORTAL_JADE, 94),
            new Weapon("山河社稷图经纬线", "山河社稷图的经纬线", ItemGrade.IMMORTAL_JADE, 92),
            new Weapon("诛仙阵图残纹旗", "以诛仙阵图残纹织就的战旗", ItemGrade.IMMORTAL_JADE, 91),
            new Weapon("混沌钟声凝晶铃", "以混沌钟声凝晶打造的铃铛", ItemGrade.IMMORTAL_JADE, 93),
            new Weapon("生死簿纸灰册", "以生死簿纸灰制成的书册", ItemGrade.IMMORTAL_JADE, 90),
            new Weapon("六道轮回盘碎片", "六道轮回盘的碎片", ItemGrade.IMMORTAL_JADE, 94),

            // 神源级武器
            new Weapon("开天斧刃残光", "开天斧的斧刃残留的光芒", ItemGrade.DIVINE_SOURCE, 120),
            new Weapon("造化玉碟道纹盘", "以造化玉碟道纹制成的玉盘", ItemGrade.DIVINE_SOURCE, 122),
            new Weapon("混沌青莲根须鞭", "以混沌青莲根须编织的长鞭", ItemGrade.DIVINE_SOURCE, 121),
            new Weapon("鸿蒙紫气本源剑", "以鸿蒙紫气本源凝结的神剑", ItemGrade.DIVINE_SOURCE, 123),
            new Weapon("天道法则锁链", "天道法则凝结的锁链", ItemGrade.DIVINE_SOURCE, 124),
            new Weapon("时间长河浪花杯", "以时间长河浪花凝结的宝杯", ItemGrade.DIVINE_SOURCE, 122),
            new Weapon("命运之弦竖琴", "以命运之弦编织的竖琴", ItemGrade.DIVINE_SOURCE, 121),
            new Weapon("虚无之核珠", "以虚无之核凝结的宝珠", ItemGrade.DIVINE_SOURCE, 123),
            new Weapon("永恒之火种灯", "以永恒之火种点燃的神灯", ItemGrade.DIVINE_SOURCE, 120),
            new Weapon("寂灭之瞳珠", "以寂灭之瞳凝结的宝珠", ItemGrade.DIVINE_SOURCE, 124),
            new Weapon("创世血瓶", "盛放创世之血的宝瓶", ItemGrade.DIVINE_SOURCE, 122),
            new Weapon("原初之息壶", "封存原初之息的宝壶", ItemGrade.DIVINE_SOURCE, 121)
        };
    }
} 