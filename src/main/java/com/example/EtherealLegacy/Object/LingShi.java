package com.example.EtherealLegacy.Object;

/**
  灵石系统类
  游戏中用于交易的货币单位
  支持增加、消费和转移操作
 */
public class LingShi {
    // 当前灵石数量 
    private int amount;

    /**
      灵石系统构造函数
      @param amount 初始灵石数量
     */
    public LingShi(int amount) {
        this.amount = amount;
    }

    /**
      获取当前灵石数量
      @return 灵石数量
     */
    public int getAmount() {
        return amount;
    }

    /**
      增加灵石数量
      @param value 增加的灵石数量
     */
    public void add(int value) {
        this.amount += value;
    }

    /**
      消费灵石
      @param value 消费的灵石数量
      @return 是否消费成功
     */
    public boolean spend(int value) {
        if (amount >= value) {
            amount -= value;
            return true;
        }
        return false;
    }

    /**
      转移灵石到目标对象
      @param target 目标灵石对象
      @param value 转移的灵石数量
     */
    public void transfer(LingShi target, int value) {
        if (spend(value)) {
            target.add(value);
        }
    }
} 