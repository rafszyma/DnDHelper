package com.example.dndhelper.spells;

import com.example.dndhelper.SpellException;

import java.util.ArrayList;
import java.util.HashSet;

public class SpellLevel {

    private int tierNumber;

    private int maxDailyCharges;
    private int currentDailyCharges;

    private HashSet<Spell> learnedSpells;
    private ArrayList<Spell> activeSpells;

    SpellLevel(int tierNumber) {
        this.tierNumber = tierNumber;
        this.maxDailyCharges = 0;
        this.currentDailyCharges = this.maxDailyCharges;
        this.learnedSpells = new HashSet<>();
        this.activeSpells = new ArrayList<>();
    }

    void resetDailyCharges() {
        this.currentDailyCharges = maxDailyCharges;
    }

    int getCurrentDailyCharges() {
        return currentDailyCharges;
    }

    HashSet<Spell> getLearnedSpells() {
        return learnedSpells;
    }

    ArrayList<Spell> getActiveSpells() {
        return activeSpells;
    }

    boolean learnSpell(Spell spell) throws SpellException {
        if (spell.getLevel() != tierNumber) {
            throw new SpellException("Nie tem poziom czaru!");
        }

        return learnedSpells.add(spell);
    }

    boolean prepareSpell(Spell spell) throws SpellException {
        if (spell.getLevel() != tierNumber) {
            throw new SpellException("Nie tem poziom czaru!");
        }

        if (currentDailyCharges > 0) {
            currentDailyCharges--;
            return activeSpells.add(spell);
        }

        throw new SpellException("Nie ma już ładunków!");
    }

    boolean changeSpell(Spell spell) throws SpellException {
        if (spell.getLevel() != tierNumber) {
            throw new SpellException("Nie tem poziom czaru!");
        }


        if (currentDailyCharges > 0) {
            currentDailyCharges--;
            boolean result = activeSpells.remove(spell);
            result &= activeSpells.add(spell);
            return result;
        }

        throw new SpellException("Nie ma już ładunków!");
    }

    int getMaxDailyCharges() {
        return maxDailyCharges;
    }

    void modifyDailyCharges(int modificator) {
        maxDailyCharges = Math.max(0, maxDailyCharges + modificator);
        currentDailyCharges = maxDailyCharges;
    }

    boolean castSpell(Spell spell) throws SpellException {
        if (!activeSpells.remove(spell)) {
            throw new SpellException("Nie ma tego czaru!");
        }

        return true;
    }
}