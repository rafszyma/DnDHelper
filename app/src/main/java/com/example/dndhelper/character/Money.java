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
        if (copper < 10) {
            this.copper = copper;
            return;
        }

        if (copper < 100) {
            this.copper = copper%10;
            this.silver = copper/10;
            return;
        }

        this.copper = copper%10;
        this.silver = (copper/10)%10;
        this.gold = copper/100;
    }

    public void addSilver(int silver) {
        if (silver < 10) {
            this.silver = silver;
            return;
        }

        this.silver = silver%10;
        this.gold = silver/10;
    }

    public void addGold(int gold) {
        this.gold = gold;
    }

    public void subCopper(int copper) {
        int newGold = 0;
        int newSilver = 0;
        int newCopper;
        if (copper > 100) {
            newGold = this.gold - copper / 100;
            newSilver = this.silver - (copper / 10 % 10);
            newCopper = this.copper - copper %10;

            SetIfPositive(newGold, newSilver, newCopper);
            return;
        }

        newCopper = this.copper - copper%10;
        if (copper > 10) {
            newSilver = this.silver - (copper / 10 % 10);
        }

        if (newCopper <= 0) {
            newCopper = 10 - newCopper;
            newSilver = this.silver - 1;
            if (newSilver <= 0) {
                newSilver = 10 - newSilver;
                newGold = this.gold - 1;
            }
        }

        SetIfPositive(newGold, newSilver, newCopper);
    }

    private boolean SetIfPositive(int gold, int silver, int copper) {
        if(gold >= 0 && silver >= 0 && copper >= 0) {
            this.gold = gold;
            this.silver = silver;
            this.copper = copper;
            return true;
        }
        else{
            // Throw popup
            return false;
        }
    }
}
