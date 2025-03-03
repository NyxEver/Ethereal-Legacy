package Game;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private static List<String> map = new ArrayList<>();

    public static void recordScene(String scene) {
        if (!map.contains(scene)) {
            map.add(scene);
            System.out.println(scene + " 已记录至地图");
        }
    }

    public static void showMap() {
        System.out.println("已探索地图：" + map);
    }
}
