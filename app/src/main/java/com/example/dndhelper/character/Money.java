package com.example.dndhelper.character;

public class Money {
    private int copper;

    private int silver;

    private int gold;

    public Money(int cooper) {
        this.addCopper(cooper);
    }

    public int getCopper() {
        return copper;
    }

    public int getSilver() {
        return silver;
    }

    public int getGold() {
        return gold;
    }

    public void addCopper(int copper) {
        int money = this.copper + this.silver * 10 + this.gold * 100;
        money += copper;
        this.copper = money % 10;
        this.silver = money / 10 % 10;
        this.gold = money / 100;
    }

    public void addSilver(int silver) {
        int money = this.silver * 10 + this.gold * 100;
        money += silver;
        this.silver = money % 10;
        this.gold = money / 10;
    }

    public void addGold(int gold) {
        this.gold = gold;
    }

    public void subCopper(int copper) {
        int money = this.copper + this.silver * 10 + this.gold * 100;
        money -= copper;
        this.copper = money % 10;
        this.silver = money / 10 % 10;
        this.gold = money / 100;
    }
}
