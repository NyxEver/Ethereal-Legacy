package Game;

import Game.Character;
import Game.SaveLoad;
import java.util.Scanner;

public class LoopMenu {
    private static Scanner scanner = new Scanner(System.in);

    public void showFunctionMenu(Character player) {
        while (true) {
            System.out.println("=== 功能菜单 ===");
            System.out.println("1. 继续游戏");
            System.out.println("2. 保存游戏");
            System.out.println("3. 离开游戏");
            System.out.print("请选择：");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": return; // 返回游戏循环
                case "2": SaveLoad.saveGame(player); break;
                case "3": System.exit(0); break;
                default: System.out.println("无效选项，请重试。");
            }
        }
    }
}
