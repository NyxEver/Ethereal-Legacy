package Game;

import Game.Character;
import com.google.gson.Gson;
import java.io.*;

public class SaveLoad {
    private static final String SAVE_DIR = "SAVE";
    private static final String SAVE_FILE = SAVE_DIR + File.separator + "save.json";
    private static final Gson gson = new Gson();

    public static void saveGame(Character player) {
        File dir = new File(SAVE_DIR);
        if (!dir.exists()) dir.mkdir();
        try (Writer writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(player, writer);
            System.out.println("游戏已保存至 " + SAVE_FILE);
        } catch (IOException e) {
            System.err.println("保存失败：" + e.getMessage());
        }
    }

    public static Character loadGame() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return null;
        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, Character.class);
        } catch (IOException e) {
            System.err.println("加载失败：" + e.getMessage());
            return null;
        }
    }
}
