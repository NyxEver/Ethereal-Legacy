package com.example.EtherealLegacy.Scene.Town;

import java.util.ArrayList;
import java.util.List;

/**
 * 城镇管理类
 * 负责管理游戏中的所有城镇
 */
public class TownManager {
    private List<Town> towns;
    private Town currentTown;

    public TownManager() {
        towns = new ArrayList<>();
        // 初始化所有城镇
        towns.add(new ImperialCity());    // 帝都
        towns.add(new MysticValley());    // 幽谷
        towns.add(new CloudPeak());       // 云顶城
        towns.add(new WaterCity());       // 水月城
        towns.add(new DesertOasis());     // 沙洲城
        
        // 设置初始城镇为帝都
        currentTown = towns.get(0);
    }

    /**
     * 获取所有已发现的城镇
     * @return 已发现的城镇列表
     */
    public List<Town> getDiscoveredTowns() {
        List<Town> discoveredTowns = new ArrayList<>();
        for (Town town : towns) {
            if (town.isDiscovered()) {
                discoveredTowns.add(town);
            }
        }
        return discoveredTowns;
    }

    /**
     * 获取当前所在城镇
     * @return 当前城镇
     */
    public Town getCurrentTown() {
        return currentTown;
    }

    /**
     * 前往指定城镇
     * @param townName 城镇名称
     * @return 是否成功到达
     */
    public boolean travelTo(String townName) {
        for (Town town : towns) {
            if (town.getName().equals(townName)) {
                if (town.isDiscovered()) {
                    currentTown = town;
                    town.onEnter();
                    return true;
                } else {
                    System.out.println("该城镇尚未发现。");
                    return false;
                }
            }
        }
        System.out.println("未找到该城镇。");
        return false;
    }

    /**
     * 发现新城镇
     * @param townName 城镇名称
     */
    public void discoverTown(String townName) {
        for (Town town : towns) {
            if (town.getName().equals(townName) && !town.isDiscovered()) {
                town.setDiscovered(true);
                System.out.println("发现新城镇：" + townName);
                return;
            }
        }
    }

    /**
     * 显示所有已发现的城镇
     */
    public void displayDiscoveredTowns() {
        System.out.println("\n=== 已发现的城镇 ===");
        for (Town town : getDiscoveredTowns()) {
            System.out.println(town.getName() + (town == currentTown ? " (当前位置)" : ""));
            System.out.println("  " + town.getDescription());
        }
    }
} 