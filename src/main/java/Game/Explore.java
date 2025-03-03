package Game;

import Game.Character;
import Game.SceneManager;
import java.util.Random;

public class Explore {
    private Character player;
    //private Random random;
    private Random random = new Random();

    public Explore(Character player) {
        this.player = player;
    }

    public void explore() {
        String[] scenes = {"地牢", "森林", "城镇", "遗迹"};
        String scene = scenes[random.nextInt(scenes.length)];
        System.out.println("你进入了 " + scene);
        SceneManager.recordScene(scene);

        switch (scene) {
            case "地牢":
                System.out.println("遭遇魔物！");
                player.setHealth(player.getHealth() - 10);
                System.out.println(random.nextBoolean() ? "获得素材！" : "获得装备！");
                player.gainExp(20); // 获得20经验值
                break;
            case "森林":
                if (random.nextBoolean()) {
                    System.out.println("遭遇魔物！");
                    player.setHealth(player.getHealth() - 5);
                    System.out.println("获得素材！");
                    player.gainExp(10); // 获得10经验值
                } else {
                    System.out.println("遇到友善NPC，学习新技能！");
                }
                break;
            case "城镇":
                System.out.println("进入城镇，恢复生命值。");
                player.setHealth(player.getHealth() + 20);
                break;
            case "遗迹":
                System.out.println("探索遗迹，发现灵根源泉！");
                break;
        }
    }
}
