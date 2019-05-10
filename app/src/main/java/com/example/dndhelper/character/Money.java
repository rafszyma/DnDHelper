package com.example.dndhelper.character;

public class Money {
    private int cooper;

    private int silver;

    private int gold;

    public Money(int cooper) {
        this.setCooper(cooper);
    }

    public int getCooper() {
        return cooper;
    }

    public int getSilver() {
        return silver;
    }

    public int getGold() {
        return gold;
    }

    public void setCooper(int cooper) {
        if (cooper < 10) {
            this.cooper = cooper;
            return;
        }

        if (cooper < 100) {
            this.cooper = cooper%10;
            this.silver = cooper/10;
            return;
        }

        this.cooper = cooper%10;
        this.cooper = cooper/10;
        this.cooper = cooper/100;
    }

    public void setSilver(int silver) {
        if (silver < 10) {
            this.silver = silver;
            return;
        }

        this.silver = silver%10;
        this.gold = silver/10;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
