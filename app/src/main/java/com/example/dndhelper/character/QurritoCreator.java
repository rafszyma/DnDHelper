package com.example.dndhelper.character;

import com.example.dndhelper.enums.Class;
import com.example.dndhelper.enums.SpellComponent;
import com.example.dndhelper.enums.SpellDefense;
import com.example.dndhelper.enums.SpellRange;
import com.example.dndhelper.enums.SpellSchool;
import com.example.dndhelper.spells.Spell;

import java.util.Arrays;
import java.util.HashSet;

public class QurritoCreator {
    public static void createQurrito() {
        Character.createNewCharacter(25, SpellSchool.Evocation, Arrays.asList(SpellSchool.Necromancy, SpellSchool.Conjuration));
        Character.getInstance().getMoney().addCopper(9409);
        Character.getInstance().getHealth().dealDamage(5);
        Character.getInstance().getSpellbook().increaseDailyCharges(0, 4);
        Character.getInstance().getSpellbook().increaseDailyCharges(1, 3);
        Character.getInstance().getSpellbook().increaseDailyCharges(2, 2);
        Character.getInstance().getSpellbook().setSpellClassLevel(3);

        for (Spell spell : GetLearnedSpells()) {
            Character.getInstance().getSpellbook().learnSpell(spell);
        }
    }

    private static Spell[] GetLearnedSpells() {
        return new Spell[]{
                new Spell("Wykrycie myśli", 2, SpellSchool.Divination, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Focus)), SpellRange.m18, "Emanacja w kształcie stożka", "Koncentracja, 1min / lvl", SpellDefense.Will, "Pozwala \"wsłuchiwać się\" w powierzchowne myśli"),
                new Spell("Podmuch wiatru", 2, SpellSchool.Evocation, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.m18, "Linia ode mnie do granicy zasięgu", "1 runda", SpellDefense.Endurance, "Odpycha lub przewraca małe istoty."),
        };
    }
}
