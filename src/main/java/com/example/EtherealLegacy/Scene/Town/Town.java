package com.example.EtherealLegacy.Scene.Town;

/**
 * 城镇基础类
 * 定义了游戏中城镇的基本属性和功能
 */
public abstract class Town {
    private final String name;        // 城镇名称
    private final String description; // 城镇描述
    private boolean isDiscovered;    // 是否已发现
    
    /**
     * 城镇构造函数
     * @param name 城镇名称
     * @param description 城镇描述
     */
    public Town(String name, String description) {
        this.name = name;
        this.description = description;
        this.isDiscovered = false;
    }
    
    /**
     * 获取城镇名称
     * @return 城镇名称
     */
    public String getName() {
        return name;
    }
    
    /**
     * 获取城镇描述
     * @return 城镇描述
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 检查城镇是否已发现
     * @return 是否已发现
     */
    public boolean isDiscovered() {
        return isDiscovered;
    }
    
    /**
     * 设置城镇发现状态
     * @param discovered 是否发现
     */
    public void setDiscovered(boolean discovered) {
        this.isDiscovered = discovered;
    }
    
    /**
     * 显示城镇信息
     */
    public void displayInfo() {
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);
    }
    
    /**
     * 进入城镇时的特殊事件
     * 由具体城镇类实现
     */
    public abstract void onEnter();
} 