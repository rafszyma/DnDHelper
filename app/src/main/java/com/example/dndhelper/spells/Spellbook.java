package com.example.dndhelper.spells;

import com.example.dndhelper.enums.SpellSchool;

import java.util.ArrayList;
import java.util.List;

public class Spellbook {
    public int MAX_SPELL_LEVELS = 10; // Levels 0-9
    private SpellLevel[] spellLevels;

    private Spell extraSpell;
    private SpellSchool extraSpellSchool;
    private ArrayList<SpellSchool> forbiddenedSchools;


    public Spellbook(SpellSchool extraSpellSchool, List<SpellSchool> forbiddenSchools) {
        if (extraSpellSchool != SpellSchool.None && forbiddenSchools != null & forbiddenSchools.size() > 0) {
            this.extraSpellSchool = extraSpellSchool;
            this.forbiddenedSchools = new ArrayList<>(forbiddenSchools);
        }
        spellLevels = new SpellLevel[MAX_SPELL_LEVELS];

        for (int i = 0; i < MAX_SPELL_LEVELS; i++)
        {
            spellLevels[i] = new SpellLevel(i);
        }
    }

    public boolean learnSpell(Spell spell) {

        if (this.forbiddenedSchools.contains(spell.getSpellSchool())){
            return false;
        }

        spellLevels[spell.getLevel()].learnSpell(spell);
        return true;
    }

    public boolean prepareSpell(Spell spell) {
        if (this.forbiddenedSchools.contains(spell.getSpellSchool())){
            return false;
        }

        spellLevels[spell.getLevel()].prepareSpell(spell);
        return true;
    }

    public boolean castSpell(Spell spell) {
        if (this.forbiddenedSchools.contains(spell.getSpellSchool())){
            return false;
        }

        spellLevels[spell.getLevel()].castSpell(spell);
        return true;
    }

    public void increaseDailyCharges(int spellLevel, int charges) {
        spellLevels[spellLevel].modifyDailyCharges(charges);
    }

    public boolean setExtraSpell(Spell spell) {
        if (spell.getSpellSchool() != this.extraSpellSchool)
        {
            return false;
        }

        extraSpell = spell;
        return true;
    }

    public int getMaxSpellLevel() {
        for (int i = MAX_SPELL_LEVELS - 1; i >= 0; i--) {
            if (spellLevels[i].getMaxDailyCharges() > 0)
                return i;
        }

        return -1;
    }

    public ArrayList<Spell> getActiveSpells(int level) {
        if (level > MAX_SPELL_LEVELS -1 ) {
            return new ArrayList<>();
        }

        return this.spellLevels[level].getActiveSpells();
    }

    public ArrayList<Spell> getLearnedSpells(int level) {
        if (level > MAX_SPELL_LEVELS -1 ) {
            return new ArrayList<>();
        }

        return new ArrayList<>(this.spellLevels[level].getLearnedSpells());
    }
}
