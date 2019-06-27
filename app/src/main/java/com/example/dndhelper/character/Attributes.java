package com.example.dndhelper.character;

import com.example.dndhelper.enums.Attribute;

public class Attributes {
    private int Strength;

    private int Dexterity;

    private int Constitution;

    private int Intelligence;

    private int Wisdom;

    private int Charisma;

    public Attributes(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        Strength = strength;
        Dexterity = dexterity;
        Constitution = constitution;
        Intelligence = intelligence;
        Wisdom = wisdom;
        Charisma = charisma;
    }

    public void increaseAttribute(int i) {
        Attribute[] values = Attribute.values();

        if (i < values.length) {
            switch (values[i]){
                case Strength:
                    increaseStrength();
                    break;
                case Wisdom:
                    increaseWisdom();
                    break;
                case Charisma:
                    increaseCharisma();
                    break;
                case Dexterity:
                    increaseDexterity();
                    break;
                case Constitution:
                    increaseConstitution();
                    break;
                case Intelligence:
                    increaseIntelligence();
                    break;
            }
        }
    }

    private void increaseStrength() {
        this.Strength = this.Strength + 1;
    }

    private void increaseDexterity() {
        this.Dexterity = this.Dexterity + 1;
    }

    private void increaseConstitution() {
        this.Constitution = this.Constitution + 1;
    }

    private void increaseIntelligence() {
        this.Intelligence = this.Intelligence + 1;
    }

    private void increaseWisdom() {
        this.Wisdom = this.Wisdom + 1;
    }

    private void increaseCharisma() {
        this.Charisma = this.Charisma + 1;
    }

    int getConstitutionModifier() {
        return (Constitution - 10) / 2;
    }

    public int getIntelligenceModifier() {
        return (Intelligence - 10) / 2;
    }

    public int getWisdomModifier() {
        return (Wisdom - 10) / 2;
    }

    public int getCharismaModifier() {
        return (Charisma - 10) / 2;
    }

    public static int getExtraChargesForSpellLevel(int spellLevel, int modifier) {
        int[][] ChargesGrid = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 2, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 2, 2, 1, 1, 1, 1, 0, 0, 0},
                {0, 2, 2, 2, 1, 1, 1, 1, 0, 0},
                {0, 2, 2, 2, 2, 1, 1, 1, 1, 0},
                {0, 3, 2, 2, 2, 2, 1, 1, 1, 1}
        };

        return ChargesGrid[modifier][spellLevel];
    }
}
