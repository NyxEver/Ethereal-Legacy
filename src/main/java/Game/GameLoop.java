package Game;

import Game.Character;
import Game.Explore;
import java.util.Scanner;

public class GameLoop {
    private Character player;
    private Explore explore;
    private LoopMenu loopMenu;
    private Scanner scanner;

    public GameLoop(Character player) {
        this.player = player;
        this.explore = new Explore(player);
        this.loopMenu = new LoopMenu();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (player.getHealth() > 0) {
            System.out.println("\n当前状态：" + player.getName() + " | 等级：" + player.getLevel() +
                    " | 生命值：" + player.getHealth() + " | 法力值：" + player.getMana() +
                    " | 经验值：" + player.getExp() + "/" + player.getExpToNextLevel());
            System.out.print("选择行动（1:探索 2:背包 3:地图 4:菜单）：");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    new Explore(player).explore();
                    break;
                case "2":
                    showBackpack();
                    break;
                case "3":
                    SceneManager.showMap();
                    break;
                case "4":
                    showFunctionMenu(scanner);
                    break;
                default:
                    System.out.println("无效选项，请重试。");
            }
        }
        System.out.println("生命值归零，游戏结束！");
    }

    private void showBackpack() {
        System.out.println("背包物品：（道具列表待实现）");
    }

    private void showFunctionMenu(Scanner scanner) {
        System.out.print("菜单（1:继续 2:保存 3:离开）：");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                break; // 继续游戏
            case "2":
                System.out.println("游戏已保存（功能待实现）");
                break;
            case "3":
                System.exit(0);
                break;
            default:
                System.out.println("无效选项，返回游戏。");
        }
    }
}
