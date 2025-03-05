package com.example.EtherealLegacy.Object;

public class LingShi {
    private int amount;

    public LingShi(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void add(int value) {
        this.amount += value;
    }

    public boolean spend(int value) {
        if (amount >= value) {
            amount -= value;
            return true;
        }
        return false;
    }

    public void transfer(LingShi target, int value) {
        if (spend(value)) {
            target.add(value);
        }
    }
} 