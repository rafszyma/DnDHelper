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
                new Spell("Wyciszenie drzwi ", 0, SpellSchool.Evocation, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Somatic)), SpellRange.Close, "Jedne odrzwia", "1h / lvl", SpellDefense.Will, "Odrzwia otwierają się cicho jak się otwiera je w magiczny sposób."),

                new Spell("Płonące dłonie", 1, SpellSchool.Evocation, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.m4point5, "Stożek", "Instant", SpellDefense.Reflex, "1k4 na poziom. Podpala łatwopalne materiały"),
                new Spell("Magiczny pocisk", 1, SpellSchool.Evocation, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Medium, "Cel na pocisk, nie dalej niż 4.5m", "Instant", SpellDefense.None, "1k4+1 na 2 levele, "),
                new Spell("Zauroczenie", 1, SpellSchool.Enchantment, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Close, "Jeden humanoid", "1h / lvl", SpellDefense.Will, "Istota traktuje Cię jak zaufanego przyjaciela. Jak mu zagrażamy to +5 do rzutu obronnego. Jak mu dam głupi rozkaz to test charyzmy. Jak sprawiamy zagrożenie to czar się kończy."),
                new Spell("Trzewia z żelaza", 1, SpellSchool.Abjuration, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Material)), SpellRange.Touch, "Dotyk", "10 min / lvl", SpellDefense.Will, "+4 na wytrwałość przeciw truciznom"),
                new Spell("Tarcza", 1, SpellSchool.Abjuration, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Self, "Ja", "10 min / lvl", SpellDefense.None, "Neguje magiczne pociski, +4 KP z tarczy, również przed bezcielesne ataki dotykowe"),
                new Spell("Alarm", 1, SpellSchool.Abjuration, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Focus)), SpellRange.Close, "Emanacja o promieniu 6m", "2 godziny / lvl", SpellDefense.None, "Alarm jak malutka lub większa wkroczy w krąg. Jak podasz hasło to nie triggruje alarmu. Decyduje czy będzie mentalny czy słyszalny."),
                new Spell("Deszcz Kolorów", 1, SpellSchool.Illusion, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Material)), SpellRange.m4point5, "Stożek", "Instant", SpellDefense.Will, "2KW i mniej: nieprzytomna, oślepiona i oszołomiona na 2k4. (3-4KW) Potem 1k4 oślepiona i oszołomiona i (5KW+) 1 runde oszołomiona"),
                new Spell("Identyfikacja", 1, SpellSchool.Divination, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Material)), SpellRange.Touch, "1 przedmiot", "Instant", SpellDefense.None, "Rzuca się 1 godzinę. Potemmożna określić wszystkie właściwości przedmiotu, oraz liczbę ładunków. Komponent: perła o wartości co najmniej 100sz"),
                new Spell("Porażający uścisk", 1, SpellSchool.Evocation, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Touch, "1 cel", "Instant", SpellDefense.None, "Atak wręcz 1k6 na poziom. +3 do ataku jak przeciwnik ma dużo metalu"),

                new Spell("Wykrycie myśli", 2, SpellSchool.Divination, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Focus)), SpellRange.m18, "Emanacja w kształcie stożka", "Koncentracja, 1min / lvl", SpellDefense.Will, "Pozwala \"wsłuchiwać się\" w powierzchowne myśli"),
                new Spell("Podmuch wiatru", 2, SpellSchool.Evocation, new HashSet<>(Arrays.asList(Class.Sorcerer, Class.Wizard)), new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.m18, "Linia ode mnie do granicy zasięgu", "1 runda", SpellDefense.Endurance, "Odpycha lub przewraca małe istoty."),
        };
    }
}
