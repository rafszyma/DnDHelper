package com.example.dndhelper.spells;

import com.example.dndhelper.SpellException;
import com.example.dndhelper.enums.SpellSchool;

import java.util.ArrayList;
import java.util.List;

public class Spellbook {
    private static final int SPELL_LEVELS = 10;
    public int spellClassLevel;
    private SpellLevel[] spellLevels;
    private Spell extraSpell;
    private SpellSchool extraSpellSchool;
    private ArrayList<SpellSchool> forbiddenSchools;
    public Spellbook(int classLevel, SpellSchool extraSpellSchool, List<SpellSchool> forbiddenSchools) {
        spellClassLevel = classLevel;
        if (extraSpellSchool != SpellSchool.None && forbiddenSchools != null & forbiddenSchools.size() > 0) {
            this.extraSpellSchool = extraSpellSchool;
            this.forbiddenSchools = new ArrayList<>(forbiddenSchools);
        }

        spellLevels = new SpellLevel[SPELL_LEVELS];

        for (int i = 0; i < SPELL_LEVELS; i++) {
            spellLevels[i] = new SpellLevel(i);
        }
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
            throw new SpellException("Spell school is forbidden");
        }

        spellLevels[spell.getLevel()].learnSpell(spell);
        return true;
    }

    public boolean prepareSpell(Spell spell) throws SpellException {
        if (this.forbiddenSchools.contains(spell.getSchool())) {
            throw new SpellException("Spell school is forbidden");
        }

        spellLevels[spell.getLevel()].prepareSpell(spell);
        return true;
    }

    public boolean castSpell(Spell spell) throws SpellException {
        if (this.forbiddenSchools.contains(spell.getSchool())) {
            throw new SpellException("Spell school is forbidden");
        }

        spellLevels[spell.getLevel()].castSpell(spell);
        return true;
    }

    public void increaseDailyCharges(int spellLevel, int charges) {
        spellLevels[spellLevel].modifyDailyCharges(charges);
    }

    public void resetDailyCharges() {
        for (SpellLevel spellLevel : spellLevels) {
            spellLevel.resetDailyCharges();
        }
    }

    public boolean setExtraSpell(Spell spell) {
        if (spell.getSchool() != this.extraSpellSchool) {
            return false;
        }

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
}
