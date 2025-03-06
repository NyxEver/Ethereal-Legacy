package com.example.EtherealLegacy.Gaming;

import com.example.EtherealLegacy.CharacterPlayer.Character;
import com.example.EtherealLegacy.Object.Item;
import com.example.EtherealLegacy.Object.ItemGrade;
import com.example.EtherealLegacy.Object.Material;
import com.example.EtherealLegacy.Object.Skill;
import com.example.EtherealLegacy.Scene.SceneManager;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
  游戏主循环类
  负责控制游戏的整体流程
  包括主菜单显示、场景切换、战斗系统等核心功能
 */
public class GameLoop {
    // 玩家角色
    private Character player;
    // 输入扫描器
    private Scanner scanner;
    // 是否继续游戏
    private boolean isRunning;
    // 探索系统
    private Explore explore;
    // 循环菜单
    private LoopMenu loopMenu;

    /**
      游戏主循环构造函数
      @param player 玩家角色
     */
    public GameLoop(Character player) {
        this.player = player;
        this.explore = new Explore(player);
        this.loopMenu = new LoopMenu();
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }

    /**
      运行游戏主循环
      处理玩家输入并执行相应的游戏逻辑
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (player.getHealth() > 0) {
            // 显示玩家当前状态，包括名称、等级、生命值、灵力值和经验值
            System.out.println("\n当前状态：" + player.getName() + " | 等级：" + player.getLevel() +
                    " | 生命值：" + player.getHealth() + "/" + player.getMaxHealth() +
                    " | 灵力值：" + player.getCurrentMana() + "/" + player.getMaxMana() +
                    " | 经验值：" + player.getExp() + "/" + player.getExpToNextLevel());
            // 提供玩家可选择的行动选项
            System.out.print("选择行动（1:探索 2:背包 3:地图 4:角色属性 5:菜单）：");
            String input = scanner.nextLine();
            // 根据玩家输入执行相应操作
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
                    showCharacterStats();
                    break;
                case "5":
                    showFunctionMenu(scanner);
                    break;
                default:
                    System.out.println("无效选项，请重试。");
            }
        }
        System.out.println("生命值归零，游戏结束！");
    }

    private void showCharacterStats() {
        System.out.println("\n=== 角色属性 ===");
        System.out.println("名称: " + player.getName());
        System.out.println("种族: " + player.getRace().getName());
        System.out.println("灵根: " + player.getLingGen());
        System.out.println("境界: " + player.getRealm().getName() + " (" + player.getRealm().getDescription() + ")");
        System.out.println("等级: " + player.getLevel());
        System.out.println("经验值: " + player.getExp() + "/" + player.getExpToNextLevel());
        System.out.println("生命值: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("灵力值: " + player.getCurrentMana() + "/" + player.getMaxMana());
        System.out.println("\n已学习技能：");
        if (player.getInventory().getSkills() != null && !player.getInventory().getSkills().isEmpty()) {
            for (Skill skill : player.getInventory().getSkills()) {
                System.out.println("- " + skill.getName());
            }
        } else {
            System.out.println("暂无已学习的技能");
        }
        System.out.println("================");
    }

    /**
     * 显示背包内容
     */
    private void showBackpack() {
        player.showBackpack();
    }

    /**
     * 显示功能菜单
     * 提供游戏基本功能选项：
     * - 继续游戏
     * - 保存游戏
     * - 退出游戏
     * 
     * @param scanner 输入扫描器，用于接收玩家选择
     */
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
