package com.example.dndhelper;

import java.util.ArrayList;

public class Spellbook {
    private int[] Charges = new int[7];

    private ArrayList<ArrayList<Spell>> _learnedSpells;

    private ArrayList<ArrayList<Spell>> _activeSpells;

    private Spell _extraSpell;


    public Spellbook() {
        _learnedSpells = new ArrayList<>();
        _activeSpells = new ArrayList<>();
    }

    public void SetCharges(int[] charges) {
        for (int i = 0; i < 7; i++)
        {
            if (Charges[i] == 0 && charges[i] < 0) {
                _learnedSpells.set(i, new ArrayList<Spell>());
                _activeSpells.set(i, new ArrayList<Spell>());
            }
            Charges[i] = charges[i];
        }
    }

    public void LearnSpell(Spell spell) {
        // TODO cant learn if there is no charges
        _learnedSpells.get(spell.GetLevel()).add(spell);
    }

    public void AddActiveSpell(Spell spell) {
        // TODO cant learn if there is no charges
        _activeSpells.get(spell.GetLevel()).add(spell);
    }

    public void SetExtraSpell(Spell spell) {
        // TODO cant learn if there is no charges / check if correct school
        _extraSpell = spell;
    }
}
