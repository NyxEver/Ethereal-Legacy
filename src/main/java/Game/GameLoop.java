package Game;

import Game.Character;
import Game.Explore;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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
                    " | 生命值：" + player.getHealth() + "/" + player.getMaxHealth() +
                    " | 灵力值：" + player.getCurrentMana() + "/" + player.getMaxMana() +
                    " | 经验值：" + player.getExp() + "/" + player.getExpToNextLevel());
            System.out.print("选择行动（1:探索 2:背包 3:地图 4:角色属性 5:菜单）：");
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

    private void showBackpack() {
        System.out.println("\n=== 背包物品 ===");
        List<Item> items = player.getInventory().getItems();
        if (items.isEmpty()) {
            System.out.println("背包为空");
        } else {
            // 按品级分类显示物品
            Map<ItemGrade, List<Item>> itemsByGrade = new HashMap<>();
            for (Item item : items) {
                if (item instanceof Material) {
                    Material material = (Material) item;
                    itemsByGrade.computeIfAbsent(material.getGrade(), k -> new ArrayList<>()).add(item);
                }
            }

            // 按品级顺序显示
            for (ItemGrade grade : ItemGrade.values()) {
                List<Item> gradeItems = itemsByGrade.get(grade);
                if (gradeItems != null && !gradeItems.isEmpty()) {
                    System.out.println("\n" + grade.getName() + "：");
                    for (Item item : gradeItems) {
                        Material material = (Material) item;
                        System.out.println("- " + item.getName() + "：" + item.getDescription());
                        System.out.println("  获取难度：" + material.getMaterialGrade().getDifficulty());
                        System.out.println("  获取方式：" + material.getMaterialGrade().getDescription());
                    }
                }
            }
        }
        System.out.println("\n=== 已学技能 ===");
        player.getInventory().showInventory();
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
