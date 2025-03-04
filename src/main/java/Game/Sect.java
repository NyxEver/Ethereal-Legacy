package Game;

import java.util.HashMap;
import java.util.Map;

public class Sect {
    private String name;
    private LingShi lingShi;
    private Map<String, Integer> facilities;
    private long lastCollectTime;
    private static final int BASE_PRODUCTION = 100; // 基础灵石产出
    private static final long COLLECT_INTERVAL = 3600000; // 收取间隔（1小时）

    public Sect(String name) {
        this.name = name;
        this.lingShi = new LingShi(1000); // 初始1000灵石
        this.facilities = new HashMap<>();
        this.lastCollectTime = System.currentTimeMillis();
        
        // 初始化基础设施
        facilities.put("聚灵阵", 1);
        facilities.put("灵田", 1);
        facilities.put("藏经阁", 0);
        facilities.put("炼丹房", 0);
        facilities.put("修炼室", 0);
    }

    public void collectLingShi() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCollectTime >= COLLECT_INTERVAL) {
            int production = calculateProduction();
            lingShi.add(production);
            System.out.println("收取灵石：" + production);
            lastCollectTime = currentTime;
        } else {
            long remainingTime = (COLLECT_INTERVAL - (currentTime - lastCollectTime)) / 1000;
            System.out.println("还需等待 " + remainingTime + " 秒");
        }
    }

    private int calculateProduction() {
        int total = BASE_PRODUCTION;
        // 各设施增益
        total += facilities.get("聚灵阵") * 50;
        total += facilities.get("灵田") * 30;
        total += facilities.get("藏经阁") * 20;
        total += facilities.get("炼丹房") * 40;
        total += facilities.get("修炼室") * 25;
        return total;
    }

    public void upgradeFacility(String name) {
        if (!facilities.containsKey(name)) {
            System.out.println("设施不存在");
            return;
        }

        int currentLevel = facilities.get(name);
        int cost = calculateUpgradeCost(name, currentLevel);

        if (lingShi.spend(cost)) {
            facilities.put(name, currentLevel + 1);
            System.out.println(name + "升级成功！当前等级：" + (currentLevel + 1));
        } else {
            System.out.println("灵石不足！需要：" + cost);
        }
    }

    private int calculateUpgradeCost(String facility, int currentLevel) {
        int baseCost = switch (facility) {
            case "聚灵阵" -> 1000;
            case "灵田" -> 800;
            case "藏经阁" -> 1200;
            case "炼丹房" -> 1500;
            case "修炼室" -> 1000;
            default -> 1000;
        };
        return baseCost * (currentLevel + 1);
    }

    public void showStatus() {
        System.out.println("\n=== " + name + " ===");
        System.out.println("灵石储备：" + lingShi.getAmount());
        System.out.println("\n设施列表：");
        for (Map.Entry<String, Integer> facility : facilities.entrySet()) {
            System.out.println(facility.getKey() + " - 等级：" + facility.getValue());
            System.out.println("升级费用：" + calculateUpgradeCost(facility.getKey(), facility.getValue()));
        }
        System.out.println("\n当前灵石产出：" + calculateProduction() + "/小时");
    }

    public LingShi getLingShi() {
        return lingShi;
    }

    public String getName() {
        return name;
    }
} 