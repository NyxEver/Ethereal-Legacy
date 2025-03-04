package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Inventory {
    private List<Item> items;
    private List<Skill> skills;

    public Inventory() {
        items = new ArrayList<>();
        skills = new ArrayList<>();
        // 添加基础攻击技能
        skills.add(new Skill("基础攻击", "最基本的攻击方式", ItemGrade.MORTAL_IRON, 5, 0));
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println("获得物品：" + item.getName());
    }

    public void addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
            System.out.println("学习技能：" + skill.getName() + "（" + skill.getGrade().getName() + "）");
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void showInventory() {
        System.out.println("\n=== 背包物品 ===");
        if (items.isEmpty()) {
            System.out.println("暂无物品");
        } else {
            Map<ItemGrade, List<Item>> itemsByGrade = new HashMap<>();
            for (Item item : items) {
                itemsByGrade.computeIfAbsent(item.getGrade(), k -> new ArrayList<>()).add(item);
            }

            for (ItemGrade grade : ItemGrade.values()) {
                List<Item> gradeItems = itemsByGrade.get(grade);
                if (gradeItems != null && !gradeItems.isEmpty()) {
                    System.out.println("\n" + grade.getName() + "：");
                    for (Item item : gradeItems) {
                        System.out.println("- " + item.getName() + "：" + item.getDescription());
                    }
                }
            }
        }

        System.out.println("\n=== 已学技能 ===");
        if (skills.isEmpty()) {
            System.out.println("暂无技能");
        } else {
            Map<ItemGrade, List<Skill>> skillsByGrade = new HashMap<>();
            for (Skill skill : skills) {
                skillsByGrade.computeIfAbsent(skill.getGrade(), k -> new ArrayList<>()).add(skill);
            }

            for (ItemGrade grade : ItemGrade.values()) {
                List<Skill> gradeSkills = skillsByGrade.get(grade);
                if (gradeSkills != null && !gradeSkills.isEmpty()) {
                    System.out.println("\n" + grade.getName() + "：");
                    for (Skill skill : gradeSkills) {
                        System.out.println("- " + skill.getName() + "：" + skill.getDescription());
                        System.out.println("  基础伤害：" + skill.getDamage());
                        System.out.println("  灵力消耗：" + skill.getCost());
                    }
                }
            }
        }
    }
} 