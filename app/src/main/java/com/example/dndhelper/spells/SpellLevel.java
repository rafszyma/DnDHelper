package com.example.dndhelper.spells;

import com.example.dndhelper.SpellException;
import com.example.dndhelper.enums.SpellSchool;

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

        boolean result = activeSpells.remove(spell);
        result &= activeSpells.add(spell);
        return result;

    }

    int getMaxDailyCharges() {
        return maxDailyCharges;
    }

    void setDailyCharges(int dailyCharges) {
        maxDailyCharges = dailyCharges;
        currentDailyCharges = dailyCharges;
    }

    boolean castSpell(Spell spell) throws SpellException {
        if (!activeSpells.remove(spell)) {
            throw new SpellException("Nie ma tego czaru!");
        }

        return true;
    }

    ArrayList<Spell> getExtraSpells(SpellSchool extraSpellSchool) {
        ArrayList<Spell> extraSpells = new ArrayList<>();
        for (Spell learnedSpell:
             this.learnedSpells) {
            if (learnedSpell.getSchool() == extraSpellSchool){
                extraSpells.add(learnedSpell);
            }
        }

        return extraSpells;
    }
}