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
        while (player.getHealth() > 0) {
            System.out.println("\n当前状态：" + player.getName() + " | 等级：" + player.getLevel() +
                    " | 生命值：" + player.getHealth() + " | 法力值：" + player.getMana());
            explore.explore();
            System.out.print("输入行动（esc呼出菜单）：");
            String input = scanner.nextLine();
            if ("esc".equalsIgnoreCase(input)) {
                loopMenu.showFunctionMenu(player);
            }
        }
        System.out.println("生命值归零，游戏结束！");
    }
}
