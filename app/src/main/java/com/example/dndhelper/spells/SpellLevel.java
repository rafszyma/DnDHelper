package com.example.dndhelper.spells;

import com.example.dndhelper.SpellException;

import java.util.ArrayList;
import java.util.HashSet;

public class SpellLevel {

    private int tierNumber;

    private int maxDailyCharges;
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

    public HashSet<Spell> getLearnedSpells() {
        return learnedSpells;
    }

    public ArrayList<Spell> getActiveSpells() {
        return activeSpells;
    }

    public boolean learnSpell(Spell spell) throws SpellException {
        if (spell.getLevel() != tierNumber) {
            throw new SpellException("Incorrect spell level");
        }

        return learnedSpells.add(spell);
    }

    public boolean prepareSpell(Spell spell) throws SpellException {
        if (spell.getLevel() != tierNumber) {
            throw new SpellException("Incorrect spell level");
        }

        if (activeSpells.size() < maxDailyCharges) {
            return activeSpells.add(spell);
        }

        throw new SpellException("Full on charges!");
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

    public boolean castSpell(Spell spell) throws SpellException {
        if (!activeSpells.remove(spell)) {
            throw new SpellException("Spell is not present");
        }

        currentCharges--;
        return true;
    }

    public int getCurrentCharges() {
        return currentCharges;
    }
}