package com.example.EtherealLegacy.Gaming;

import com.example.EtherealLegacy.CharacterPlayer.BasicRule.CultivationRealm;
import com.example.EtherealLegacy.CharacterPlayer.Character;
import com.example.EtherealLegacy.Scene.*;
import com.example.EtherealLegacy.Scene.Business.SectScene;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
  探索系统类
  负责管理游戏中的场景探索功能
  包括场景初始化、场景选择、探索逻辑等 
  主要功能：
  1. 管理各类场景（森林、地牢、遗迹、宗门）
  2. 根据概率选择探索场景
  3. 处理场景进入和探索逻辑
  4. 管理场景冷却时间
 */
public class Explore {
    // 玩家角色对象
    private Character player;
    // 随机数生成器，用于场景选择和事件触发
    private Random random = new Random();
    // 场景映射表，存储所有可用场景
    private Map<String, Scene> scenes;

    /**
     * 探索系统构造函数
     * 初始化探索系统并加载所有场景
     * 
     * @param player 玩家角色对象
     */
    public Explore(Character player) {
        this.player = player;
        initializeScenes();
    }

    /**
     * 初始化所有场景
     * 创建并配置四类场景：
     * 1. 森林场景：适合采集和狩猎
     * 2. 地牢场景：适合战斗和寻宝
     * 3. 遗迹场景：适合探索和获得机缘
     * 4. 宗门场景：适合交易和任务
     * 
     * 每个场景都包含：
     * - 名称
     * - 描述
     * - 危险等级
     * - 所需境界
     */
    private void initializeScenes() {
        scenes = new HashMap<>();
        
        // 初始化森林场景
        scenes.put("蚀月古林", new ForestScene("蚀月古林",
            "每月月圆之夜树木渗出银血，妖兽集体化形", 1.2, CultivationRealm.MERIDIAN_OPENING));
        scenes.put("无回林", new ForestScene("无回林", 
            "雾气中暗藏空间裂隙，合体期以下入内必失方向", 1.5, CultivationRealm.GOLDEN_CORE));
        scenes.put("龙骸森", new ForestScene("龙骸森", 
            "上古青龙陨落之地，龙威催生变异噬灵藤", 1.8, CultivationRealm.NASCENT_SOUL));
        scenes.put("千劫雷桦林", new ForestScene("千劫雷桦林", 
            "被天雷反复淬炼的桦树皆成天然避雷木", 2.0, CultivationRealm.SPIRIT_SEVERING));
        scenes.put("醉梦花海", new ForestScene("醉梦花海", 
            "食人花释放致幻香气，沉眠者化为花肥", 1.7, CultivationRealm.FOUNDATION_BUILDING));

        // 初始化地牢场景
        scenes.put("九劫渊", new DungeonScene("九劫渊",
            "每深入百丈遭遇一种天劫残余能量", 2.2, CultivationRealm.SPIRIT_SEVERING));
        scenes.put("千骸井", new DungeonScene("千骸井", 
            "井壁镶嵌十万修士头骨，镇压上古尸仙", 1.9, CultivationRealm.GOLDEN_CORE));
        scenes.put("锁龙窟", new DungeonScene("锁龙窟", 
            "断裂的缚仙链困着半腐化的五爪金龙残魂", 2.5, CultivationRealm.NASCENT_SOUL));
        scenes.put("血髓洞", new DungeonScene("血髓洞", 
            "洞内血池孕育出血煞晶，魔修争夺之地", 1.8, CultivationRealm.FOUNDATION_BUILDING));
        scenes.put("无间鬼牢", new DungeonScene("无间鬼牢", 
            "时空错乱的刑狱，关押着触犯天条的仙人", 2.8, CultivationRealm.TRIBULATION));

        // 初始化遗迹场景
        scenes.put("堕星城", new RelicScene("堕星城",
            "天外陨石构建的巨城，重力是外界的三十倍", 2.3, CultivationRealm.NASCENT_SOUL));
        scenes.put("大罗天残碑", new RelicScene("大罗天残碑", 
            "刻有仙界残缺功法，参悟者必遭心魔劫", 2.6, CultivationRealm.SPIRIT_SEVERING));
        scenes.put("归墟海眼", new RelicScene("归墟海眼", 
            "海底漩涡下的破碎洞天，藏有蓬莱遗舟", 2.4, CultivationRealm.GOLDEN_CORE));
        scenes.put("兵冢荒原", new RelicScene("兵冢荒原", 
            "插满上古神魔兵器，每把武器都封印着战魂", 2.7, CultivationRealm.TRIBULATION));
        scenes.put("逆光阴殿", new RelicScene("逆光阴殿", 
            "时间流速错乱的废墟，可能遇见未来化灰的自己", 3.0, CultivationRealm.IMMORTAL));

        // 初始化宗门场景
        scenes.put("玄霜谷", new SectScene("玄霜谷",
            "以冰魄寒髓筑基，弟子皆白发蓝瞳", 1.0, CultivationRealm.FOUNDATION_BUILDING));
        scenes.put("焚天阙", new SectScene("焚天阙", 
            "建在活火山口的霸道剑宗，剑诀引动地火", 1.0, CultivationRealm.GOLDEN_CORE));
        scenes.put("冥河宗", new SectScene("冥河宗", 
            "驾驭阴尸修炼的诡道宗门，山门由奈何桥碎片建成", 1.0, CultivationRealm.NASCENT_SOUL));
        scenes.put("星移阁", new SectScene("星移阁", 
            "精通炼器，建筑悬浮于破碎秘境", 1.0, CultivationRealm.SPIRIT_SEVERING));
        scenes.put("无相禅林", new SectScene("无相禅林", 
            "拥有且培养了大量的妖族，只出售素材", 1.0, CultivationRealm.TRIBULATION));
    }

    /**
     * 执行探索操作
     * 根据概率选择场景类型并进入探索
     * 
     * 场景选择概率：
     * - 森林场景：40%
     * - 地牢场景：30%
     * - 宗门场景：20%
     * - 遗迹场景：10%
     * 
     * 探索流程：
     * 1. 随机选择场景类型
     * 2. 筛选可用场景
     * 3. 检查场景冷却时间
     * 4. 进入场景并执行探索
     */
    public void explore() {
        // 根据概率选择场景类型
        double roll = random.nextDouble();
        List<Scene> availableScenes = new ArrayList<>();
        
        if (roll < 0.4) { // 40%概率进入森林
            for (Scene scene : scenes.values()) {
                if (scene instanceof ForestScene && SceneManager.canVisitScene(scene.getName())) {
                    availableScenes.add(scene);
                }
            }
        } else if (roll < 0.7) { // 30%概率进入地牢
            for (Scene scene : scenes.values()) {
                if (scene instanceof DungeonScene && SceneManager.canVisitScene(scene.getName())) {
                    availableScenes.add(scene);
                }
            }
        } else if (roll < 0.9) { // 20%概率进入宗门
            for (Scene scene : scenes.values()) {
                if (scene instanceof SectScene && SceneManager.canVisitScene(scene.getName())) {
                    availableScenes.add(scene);
                }
            }
        } else { // 10%概率进入遗迹
            for (Scene scene : scenes.values()) {
                if (scene instanceof RelicScene && SceneManager.canVisitScene(scene.getName())) {
                    availableScenes.add(scene);
                }
            }
        }

        if (!availableScenes.isEmpty()) {
            // 随机选择一个可用场景
            Scene selectedScene = availableScenes.get(random.nextInt(availableScenes.size()));
            // 检查场景冷却时间
            long cooldown = SceneManager.getRemainingCooldown(selectedScene.getName());
            if (cooldown > 0) {
                System.out.printf("【%s】还在冷却中，剩余时间：%d分%d秒%n", 
                    selectedScene.getName(),
                    cooldown / 60000,
                    (cooldown % 60000) / 1000);
                return;
            }
            
            // 进入场景并开始探索
            System.out.printf("进入【%s】%n", selectedScene.getName());
            System.out.println(selectedScene.getDescription());
            SceneManager.recordScene(selectedScene.getName());
            selectedScene.onEnter(player);
            selectedScene.explore(player);
        } else {
            System.out.println("当前没有可探索的场景，请等待冷却时间结束。");
        }
    }
}
