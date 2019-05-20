package com.example.dndhelper.character;

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

    public void increaseStrength() {
        this.Strength = this.Strength + 1;
    }

    public void increaseDexterity() {
        this.Dexterity = this.Dexterity + 1;
    }

    public void increaseConstitution() {
        this.Constitution = this.Constitution + 1;
    }

    public void increaseIntelligence() {
        this.Intelligence = this.Intelligence + 1;
    }

    public void increaseWisdom() {
        this.Wisdom = this.Wisdom + 1;
    }

    public void increaseCharisma() {
        this.Charisma = this.Charisma + 1;
    }

    public int getStrengthModifier() {

        return (Strength - 10) / 2;
    }

    public int getDexterityModifier() {
        return (Dexterity - 10) / 2;
    }

    public int getConstitutionModifier() {
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
}
