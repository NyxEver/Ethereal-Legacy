package Game;

import Game.Character;
import Game.SaveLoad;
import java.util.Scanner;

public class HomePage {
    private static Scanner scanner = new Scanner(System.in);

    public void displayMainMenu() {
        while (true) {
            System.out.println("=== 主界面 ===");
            System.out.println("1. 新游戏");
            System.out.println("2. 加载");
            System.out.println("3. 退出");
            System.out.print("请选择：");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": startNewGame(); break;
                case "2": loadGame(); break;
                case "3": System.exit(0); break;
                default: System.out.println("无效选项，请重试。");
            }
        }
    }

    private void startNewGame() {
        System.out.print("输入角色名称：");
        String name = scanner.nextLine();
        System.out.print("选择灵根（金/木/水/火/土/真灵根/天灵根）：");
        String lingGen = scanner.nextLine();
        Character player = new Character(name, lingGen);
        System.out.println("角色创建成功！进入游戏...");
        new GameLoop(player).run();
    }

    private void loadGame() {
        Character player = SaveLoad.loadGame();
        if (player != null) {
            System.out.println("加载成功！进入游戏...");
            new GameLoop(player).run();
        } else {
            System.out.println("无存档或加载失败。");
        }
    }
}
