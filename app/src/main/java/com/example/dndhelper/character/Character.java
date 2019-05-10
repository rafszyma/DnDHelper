package com.example.dndhelper.character;

import com.example.dndhelper.enums.CharacterState;
import com.example.dndhelper.enums.SpellSchool;
import com.example.dndhelper.spells.Spellbook;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private Health health;

    private Money money;

    private Spellbook spellbook;

    public Character(int maxHealth, SpellSchool extraSpellSchool, List<SpellSchool> forbiddenedSchools) {
        health = new Health(maxHealth);
        money = new Money(0);
        spellbook = new Spellbook(extraSpellSchool, forbiddenedSchools);
    }

    public Health getHealth() {
        return health;
    }

    public Money getMoney() {
        return money;
    }

    public Spellbook getSpellbook() {
        return spellbook;
    }

    public static CharacterState getState(int hp) {
        if (hp < -10) {
            return CharacterState.Dead;
        }

        if (hp < 0) {
            return CharacterState.Unconscious;
        }

        if (hp == 0) {
            return CharacterState.Semiconscious;
        }

        return CharacterState.Healthy;
    }
}
