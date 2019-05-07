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
}
