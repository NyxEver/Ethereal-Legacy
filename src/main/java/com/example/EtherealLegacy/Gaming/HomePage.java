package com.example.EtherealLegacy.Gaming;

import com.example.EtherealLegacy.CharacterPlayer.Character;
import com.example.EtherealLegacy.CharacterPlayer.Legacy;

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
        String lingGen = validateBaseLingGen(scanner);
        Legacy legacy = selectLegacy(scanner);
        Character player = new Character(name, lingGen, legacy);
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

    private void loadGame() {
        Character player = SaveLoad.loadGame();
        if (player != null) {
            System.out.println("加载成功！进入游戏...");
            new GameLoop(player).run();
        } else {
            System.out.println("无存档或加载失败。");
        }
    }

    private static Legacy selectLegacy(Scanner scanner) {
        while (true) {
            System.out.println("\n选择传承：");
            System.out.println("1. 剑修传承 - 擅长剑法，攻击力强");
            System.out.println("2. 丹修传承 - 擅长炼丹，恢复能力强");
            System.out.println("3. 阵修传承 - 擅长阵法，防御力强");
            System.out.println("4. 体修传承 - 擅长炼体，生命力强");
            
            System.out.print("请选择（1-4）：");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": return Legacy.SWORD_CULTIVATOR;
                case "2": return Legacy.PILL_MASTER;
                case "3": return Legacy.FORMATION_MASTER;
                case "4": return Legacy.BODY_CULTIVATOR;
                default:
                    System.out.println("无效选择，请重试。");
            }
        }
    }
}
