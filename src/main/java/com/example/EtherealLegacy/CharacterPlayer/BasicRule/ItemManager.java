package com.example.EtherealLegacy.CharacterPlayer.BasicRule;

import com.example.EtherealLegacy.CharacterPlayer.Character;
import com.example.EtherealLegacy.CharacterPlayer.Inventory;
import com.example.EtherealLegacy.Object.ConsumableType;
import com.example.EtherealLegacy.Object.Consumable;
import com.example.EtherealLegacy.Object.Item;
import com.example.EtherealLegacy.Object.ItemGrade;
import com.example.EtherealLegacy.Object.Skill;
import com.example.EtherealLegacy.Object.Weapon;
import com.example.EtherealLegacy.CharacterPlayer.BasicRule.CultivationRealm;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * 物品管理类
 * 负责处理物品的生成、判定和管理
 */
public class ItemManager {
    private final Random random = new Random();
    private final Character character;
    private final Inventory inventory;

    public ItemManager(Character character, Inventory inventory) {
        this.character = character;
        this.inventory = inventory;
        generateInitialConsumables();
    }

    /**
     * 生成初始消耗品
     */
    private void generateInitialConsumables() {
        Consumable[] consumables = Consumable.getAllConsumables();
        // 添加10个小血瓶和10个小法瓶
        for (int i = 0; i < 10; i++) {
            inventory.addItem(consumables[0]); // 小血瓶
            inventory.addItem(consumables[1]); // 小法瓶
        }
    }

    /**
     * 检查并自动使用恢复道具
     * @return 是否使用了道具
     */
    public boolean checkAndUseConsumables() {
        boolean used = false;
        List<Item> items = new ArrayList<>(inventory.getItems());
        
        // 检查生命值是否低于50%
        if (character.getHealth() < character.getMaxHealth() * 0.5) {
            for (Item item : items) {
                if (item instanceof Consumable consumable && 
                    consumable.getType() == ConsumableType.HEALTH_POTION) {
                    if (consumable.use(character)) {
                        inventory.removeItem(item);
                        used = true;
                        break;
                    }
                }
            }
        }
        
        // 检查法力值是否低于50%
        if (character.getCurrentMana() < character.getMaxMana() * 0.5) {
            for (Item item : items) {
                if (item instanceof Consumable consumable && 
                    consumable.getType() == ConsumableType.MANA_POTION) {
                    if (consumable.use(character)) {
                        inventory.removeItem(item);
                        used = true;
                        break;
                    }
                }
            }
        }
        
        return used;
    }

    /**
     * 生成初始武器
     */
    public void generateInitialWeapon() {
        Weapon[] weapons = Weapon.getAllWeapons();
        List<Weapon> suitableWeapons = new ArrayList<>();
        
        for (Weapon weapon : weapons) {
            if (isItemSuitableForRealm(weapon)) {
                suitableWeapons.add(weapon);
            }
        }
        
        if (!suitableWeapons.isEmpty()) {
            Weapon initialWeapon = suitableWeapons.get(random.nextInt(suitableWeapons.size()));
            inventory.addItem(initialWeapon);
            System.out.println("获得初始武器：" + initialWeapon.getName());
        }
    }

    /**
     * 生成初始技能
     */
    public void generateInitialSkill() {
        Skill[] skills = Skill.getAllSkills();
        List<Skill> suitableSkills = new ArrayList<>();
        
        for (Skill skill : skills) {
            if (isSkillSuitableForRealm(skill)) {
                suitableSkills.add(skill);
            }
        }
        
        if (!suitableSkills.isEmpty()) {
            Skill initialSkill = suitableSkills.get(random.nextInt(suitableSkills.size()));
            inventory.addSkill(initialSkill);
            System.out.println("获得初始技能：" + initialSkill.getName());
        }
    }

    /**
     * 判断物品是否适合当前境界
     */
    public boolean isItemSuitableForRealm(Item item) {
        if (item.getGrade() == null) return true;
        
        return switch (character.getRealm()) {
            case MORTAL_BODY, MERIDIAN_OPENING -> item.getGrade() == ItemGrade.MORTAL_IRON;
            case QI_CONDENSATION, FOUNDATION_BUILDING -> item.getGrade() == ItemGrade.SPIRIT_MATERIAL;
            case GOLDEN_CORE, NASCENT_SOUL -> item.getGrade() == ItemGrade.MYSTIC_GOLD;
            case SPIRIT_SEVERING -> item.getGrade() == ItemGrade.EARTH_TREASURE;
            case TRIBULATION -> item.getGrade() == ItemGrade.HEAVEN_CRYSTAL;
            case IMMORTAL -> item.getGrade() == ItemGrade.IMMORTAL_JADE;
            default -> false;
        };
    }

    /**
     * 判断技能是否适合当前境界
     */
    public boolean isSkillSuitableForRealm(Skill skill) {
        if (skill.getGrade() == null) return true;
        
        return switch (character.getRealm()) {
            case MORTAL_BODY, MERIDIAN_OPENING -> skill.getGrade() == ItemGrade.MORTAL_IRON;
            case QI_CONDENSATION, FOUNDATION_BUILDING -> skill.getGrade() == ItemGrade.SPIRIT_MATERIAL;
            case GOLDEN_CORE, NASCENT_SOUL -> skill.getGrade() == ItemGrade.MYSTIC_GOLD;
            case SPIRIT_SEVERING -> skill.getGrade() == ItemGrade.EARTH_TREASURE;
            case TRIBULATION -> skill.getGrade() == ItemGrade.HEAVEN_CRYSTAL;
            case IMMORTAL -> skill.getGrade() == ItemGrade.IMMORTAL_JADE;
            default -> false;
        };
    }
} 