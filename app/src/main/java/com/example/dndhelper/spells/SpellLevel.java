package com.example.dndhelper.spells;

import java.util.ArrayList;
import java.util.HashSet;

public class SpellLevel {

    private int tierNumber;

    private int maxDailyCharges;

    public HashSet<Spell> getLearnedSpells() {
        return learnedSpells;
    }

    public ArrayList<Spell> getActiveSpells() {
        return activeSpells;
    }

    private int currentCharges;

    private HashSet<Spell> learnedSpells;

    private ArrayList<Spell> activeSpells;



    public SpellLevel(int tierNumber) {
        this.tierNumber = tierNumber;
        this.maxDailyCharges = 0;
        this.currentCharges = this.maxDailyCharges;
        this.learnedSpells = new HashSet<>();
        this.activeSpells = new ArrayList<>();
    }

    public boolean learnSpell(Spell spell) {
        if (spell.getLevel() != tierNumber) {
            return false;
        }

        return learnedSpells.add(spell);
    }

    public int getTierNumber() {
        return tierNumber;
    }

    public int getMaxDailyCharges() {
        return maxDailyCharges;
    }

    public void modifyDailyCharges(int modificator) {
        maxDailyCharges = Math.max(0, maxDailyCharges + modificator);
    }

    public boolean castSpell(Spell spell) {
        if (!activeSpells.remove(spell)) {
            return false;
        }

        currentCharges--;
        return true;
    }

    public int getCurrentCharges() {
        return currentCharges;
    }
}