package com.example.dndhelper.spells;

import com.example.dndhelper.SpellException;
import com.example.dndhelper.character.Attributes;
import com.example.dndhelper.character.CharacterClasses;
import com.example.dndhelper.enums.Class;
import com.example.dndhelper.enums.SpellSchool;

import java.util.ArrayList;
import java.util.List;

public class Spellbook {
    private static final int SPELL_LEVELS = 10;
    public int spellClassLevel;
    private SpellLevel[] spellLevels;
    private Spell extraSpell;
    private CharacterClasses spellClass;

    public boolean isAvailableExtraSpell() {
        return availableExtraSpell;
    }

    private boolean availableExtraSpell;
    private SpellSchool extraSpellSchool;
    private ArrayList<SpellSchool> forbiddenSchools;

    public Spellbook(int classLevel, SpellSchool extraSpellSchool, List<SpellSchool> forbiddenSchools, Class spellClass, Attributes attr) {
        spellClassLevel = classLevel;
        if (extraSpellSchool != SpellSchool.None && forbiddenSchools != null & forbiddenSchools.size() > 0) {
            this.extraSpellSchool = extraSpellSchool;
            this.forbiddenSchools = new ArrayList<>(forbiddenSchools);
        }

        spellLevels = new SpellLevel[SPELL_LEVELS];

        for (int i = 0; i < SPELL_LEVELS; i++) {
            spellLevels[i] = new SpellLevel(i);
        }

        modifyClassCharges(spellClass.generateSpellChargesList(spellClassLevel, attr));
    }

    public SpellSchool getExtraSpellSchool() {
        return extraSpellSchool;
    }

    public int getMaxSpellLevelCharges(int level) {
        return spellLevels[level].getMaxDailyCharges();
    }

    public int getCurrentSpellLevelCharges(int level) {
        return spellLevels[level].getCurrentDailyCharges();
    }

    public boolean learnSpell(Spell spell) throws SpellException {

        if (this.forbiddenSchools.contains(spell.getSchool())) {
            throw new SpellException("Ta szkoła jest zakazana!");
        }

        spellLevels[spell.getLevel()].learnSpell(spell);
        return true;
    }

    public boolean prepareSpell(Spell spell) throws SpellException {
        if (this.forbiddenSchools.contains(spell.getSchool())) {
            throw new SpellException("Ta szkoła jest zakazana!");
        }

        spellLevels[spell.getLevel()].prepareSpell(spell);
        return true;
    }

    public boolean changeSpell(Spell spell) throws SpellException {
        spellLevels[spell.getLevel()].changeSpell(spell);
        return true;
    }

    public boolean castSpell(Spell spell) throws SpellException {
        if (this.forbiddenSchools.contains(spell.getSchool())) {
            throw new SpellException("Ta szkoła jest zakazana!");
        }

        spellLevels[spell.getLevel()].castSpell(spell);
        return true;
    }

    public boolean castExtraSpell(Spell spell) {
        if (this.extraSpell == spell) {
            this.extraSpell = null;
            return true;
        }

        return false;
    }

    public void modifyClassCharges(int[] spellCharges) {
        for(int i = 0; i < spellCharges.length; i++) {
            if (spellCharges[i] > 0){
                this.spellLevels[i].setDailyCharges(spellCharges[i]);
            }
            else {
                return;
            }
        }
    }

    public void resetDailyCharges() {
        for (SpellLevel spellLevel : spellLevels) {
            spellLevel.resetDailyCharges();
        }
        this.availableExtraSpell = true;
    }

    public boolean setExtraSpell(Spell spell) throws SpellException {
        if (!this.availableExtraSpell) {
            throw new SpellException("Don't have available spells");
        }
        if (spell.getSchool() != this.extraSpellSchool) {
            throw new SpellException("This spell don't have correct school");
        }

        this.availableExtraSpell = false;

        extraSpell = spell;
        return true;
    }

    public int getMaxSpellLevel() {
        for (int i = SPELL_LEVELS - 1; i >= 0; i--) {
            if (spellLevels[i].getMaxDailyCharges() > 0)
                return i;
        }

        return -1;
    }

    public ArrayList<Spell> getActiveSpells(int level) {
        if (level > SPELL_LEVELS - 1) {
            return new ArrayList<>();
        }

        return this.spellLevels[level].getActiveSpells();
    }

    public int getSpellClassLevel() {
        return spellClassLevel;
    }

    public boolean setSpellClassLevel(int i) {
        if (i > 0) {
            this.spellClassLevel = i;
            return true;
        }

        return false;
    }

    public ArrayList<Spell> getLearnedSpells(int level) {
        if (level > SPELL_LEVELS - 1) {
            return new ArrayList<>();
        }

        return new ArrayList<>(this.spellLevels[level].getLearnedSpells());
    }

    public ArrayList<Spell> getExtraSpells() {
        ArrayList<Spell> extraSpells = new ArrayList<>();

        for (int i = SPELL_LEVELS - 1; i >= 0; i--) {
            extraSpells.addAll(this.spellLevels[i].getExtraSpells(this.extraSpellSchool));
        }

        return extraSpells;
    }

    public Spell getExtraSpell() {
        return extraSpell;
    }
}
