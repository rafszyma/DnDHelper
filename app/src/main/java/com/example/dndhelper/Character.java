package com.example.dndhelper;

import java.util.List;

public class Character {
    private int _punktyWytrzymalosci;
    private int _stluczenia;

    private int _gold;

    private List<Spell> activeSpells;

    private List<Spell> learnedSpells;

    private Spell czarWywolania;

    public Character(int _punktyWytrzymalosci) {
        this._punktyWytrzymalosci = _punktyWytrzymalosci;
    }
}
