package Game;

import java.util.*;

public class SceneManager {
    private static Map<String, Integer> sceneVisits = new HashMap<>();
    private static Map<String, Long> lastVisitTime = new HashMap<>();
    private static final long VISIT_COOLDOWN = 1800000; // 30分钟冷却时间
    
    private static List<Scene> forestScenes = new ArrayList<>();
    private static List<Scene> dungeonScenes = new ArrayList<>();
    private static List<Scene> relicScenes = new ArrayList<>();
    private static List<Scene> sectScenes = new ArrayList<>();

    public static void recordScene(String sceneName) {
        sceneVisits.put(sceneName, sceneVisits.getOrDefault(sceneName, 0) + 1);
        lastVisitTime.put(sceneName, System.currentTimeMillis());
    }

    public static boolean canVisitScene(String sceneName) {
        Long lastVisit = lastVisitTime.get(sceneName);
        if (lastVisit == null) return true;
        
        return System.currentTimeMillis() - lastVisit >= VISIT_COOLDOWN;
    }

    public static int getVisitCount(String sceneName) {
        return sceneVisits.getOrDefault(sceneName, 0);
    }

    public static Map<String, Integer> getSceneVisits() {
        return new HashMap<>(sceneVisits);
    }

    public static List<String> getMostVisitedScenes(int limit) {
        return sceneVisits.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(limit)
            .map(Map.Entry::getKey)
            .toList();
    }

    public static void resetCooldown(String sceneName) {
        lastVisitTime.remove(sceneName);
    }

    public static long getRemainingCooldown(String sceneName) {
        Long lastVisit = lastVisitTime.get(sceneName);
        if (lastVisit == null) return 0;
        
        long remaining = VISIT_COOLDOWN - (System.currentTimeMillis() - lastVisit);
        return Math.max(0, remaining);
    }

    public static void clearHistory() {
        sceneVisits.clear();
        lastVisitTime.clear();
    }

    public static void showMap() {
        System.out.println("\n=== 地图 ===");
        System.out.println("1. 森林区域");
        for (Scene scene : forestScenes) {
            System.out.println("   - " + scene.getName() + " (需求境界：" + scene.getRequiredRealm().getName() + ")");
        }
        
        System.out.println("\n2. 地下城区域");
        for (Scene scene : dungeonScenes) {
            System.out.println("   - " + scene.getName() + " (需求境界：" + scene.getRequiredRealm().getName() + ")");
        }
        
        System.out.println("\n3. 遗迹区域");
        for (Scene scene : relicScenes) {
            System.out.println("   - " + scene.getName() + " (需求境界：" + scene.getRequiredRealm().getName() + ")");
        }
        
        System.out.println("\n4. 宗门区域");
        for (Scene scene : sectScenes) {
            System.out.println("   - " + scene.getName() + " (需求境界：" + scene.getRequiredRealm().getName() + ")");
        }
    }
}
