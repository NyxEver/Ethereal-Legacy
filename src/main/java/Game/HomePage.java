package Game;

import Game.Character;
import Game.SaveLoad;

import java.util.Random;
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入角色名称：");
        String name = scanner.nextLine();
        System.out.print("选择基础灵根（金/木/水/火/土）：");
        String baseLingGen = validateBaseLingGen(scanner);
        String lingGen = assignSpecialLingGen(baseLingGen);
        Character player = new Character(name, lingGen);
        System.out.println("角色创建成功！灵根：" + lingGen);
        new GameLoop(player).run();
    }
    private String validateBaseLingGen(Scanner scanner) {
        String input;
        do {
            input = scanner.nextLine().toLowerCase();
            if (!input.matches("金|木|水|火|土")) {
                System.out.print("无效灵根，请重新选择（金/木/水/火/土）：");
            }
        } while (!input.matches("金|木|水|火|土"));
        return input;
    }
    private String assignSpecialLingGen(String base) {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance < 5) return "天灵根"; // 5% 概率
        else if (chance < 20) return "真灵根"; // 15% 概率
        return base; // 80% 概率保持基础灵根
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
