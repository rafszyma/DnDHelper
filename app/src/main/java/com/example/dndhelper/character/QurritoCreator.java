package com.example.dndhelper.character;

import com.example.dndhelper.enums.SpellComponent;
import com.example.dndhelper.enums.SpellDefense;
import com.example.dndhelper.enums.SpellRange;
import com.example.dndhelper.enums.SpellSchool;
import com.example.dndhelper.spells.Spell;

import java.util.Arrays;
import java.util.HashSet;

public class QurritoCreator {
    public static Character createQurrito() {
        Character qurrito = new Character(20, SpellSchool.Evocation, Arrays.asList(SpellSchool.Necromancy, SpellSchool.Conjuration));
        qurrito.getMoney().addCopper(39409);
        qurrito.getHealth().dealDamage(12);
        qurrito.getSpellbook().increaseDailyCharges(0, 4);
        qurrito.getSpellbook().increaseDailyCharges(1, 3);

        for (Spell spell : GetLearnedSpells()) {
            qurrito.getSpellbook().learnSpell(spell);
        }
        return qurrito;
    }

    private static Spell[] GetLearnedSpells() {
        return new Spell[] {
                new Spell("Otumanienie", 0, SpellSchool.Enchantment, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Material)), SpellRange.Close, "1 cel <4KW", "1 runda", SpellDefense.Will, "Humanoid o maksymalnie 4 KW traci następną akcję."),
                new Spell("Odporność", 0, SpellSchool.Abjuration, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Material)), SpellRange.Touch, "Dotyk", "1 min", SpellDefense.Will, "Podmiot otrzymuje +1 do rzutów obronnych"),
                new Spell("Odczytanie Magii", 0, SpellSchool.Divination, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Focus)), SpellRange.Self, "Ja", "10min / lvl", SpellDefense.None, "Pozwala odczytywać zwoje i księgi zaklęć"),
                new Spell("Wykrycie Magii", 0, SpellSchool.Divination, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.m18, "Stożek Emanacja", "1 min / lvl, koncentracja", SpellDefense.None, "Wykrywa czary i magiczne przedmioty w zasięgu 18m"),
                new Spell("Wykrycie Trucizny", 0, SpellSchool.Divination, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Close, "Kratka przylegająca", "Instant", SpellDefense.None, "Wykrywa truciznę w jednej istocie lub przedmiocie"),
                new Spell("Dłoń Maga", 0, SpellSchool.Transmutation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Close, "Niemagiczny, nietrzymany przedmiot", "Koncentracja", SpellDefense.None, "Telekineza na 2,5 kg"),
                new Spell("Naprawa", 0, SpellSchool.Transmutation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.m3, "Przedmiot o wadze < 0.5kg", "Instant", SpellDefense.Will, "Dokonuje drobnych napraw przedmiotu"),
                new Spell("Otwarcie / Zamknięcie", 0, SpellSchool.Transmutation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Focus)), SpellRange.Close, "Przedmiot lub wejście o wadze <15kg", "Instant", SpellDefense.Will, "Otwiera lub zamyka małe i lekkie przedmioty"),
                new Spell("Wiadomość", 0, SpellSchool.Transmutation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Focus)), SpellRange.Medium, "1 cel / lvl", "10 min / lvl", SpellDefense.None, "Rozmowa szeptem na odległość"),
                new Spell("Kuglarstwo", 0, SpellSchool.Universal, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.m3, "Opis", "1h", SpellDefense.None, "Powołuje do istnienia drobne sztuczki"),
                new Spell("Mistyczny Znak", 0, SpellSchool.Universal, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Touch, "0.1m2", "Pernamentny", SpellDefense.None, "Umożliwia zapisanie prywatnej runy"),
                new Spell("Promień Mrozu", 0, SpellSchool.Evocation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Close, "1 cel", "Instant", SpellDefense.TouchAttackRanged, "Promień zadający 1k3 obrażeń od zimna"),
                new Spell("Raca", 0, SpellSchool.Evocation, new HashSet<>(Arrays.asList(SpellComponent.Verbal)), SpellRange.Close, "Rozprysk", "Instant", SpellDefense.Endurance, "Istota pada ofiarą olśnienia (-1 do testów ataków)"),
                new Spell("Światło", 0, SpellSchool.Evocation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Material)), SpellRange.Touch, "Dotyk", "10 min / lvl", SpellDefense.None, "Przedmiot rozświetla się niczym pochodnia"),
                new Spell("Tańczące Światło", 0, SpellSchool.Evocation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Medium, "Do 4 świateł", "1 min", SpellDefense.None, "Tworzy pochodnie i innego typu światła"),
                new Spell("Widmowy odgłos", 0, SpellSchool.Evocation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Material)), SpellRange.Close, "Dźwięki", "1 runda / lvl", SpellDefense.Will, "Tworzy złudne dźwięki"),
                new Spell("Wyciszenie drzwi ", 0, SpellSchool.Evocation, new HashSet<>(Arrays.asList(SpellComponent.Somatic)), SpellRange.Close, "Jedne odrzwia", "1h / lvl", SpellDefense.Will, "Odrzwia otwierają się cicho jak się otwiera je w magiczny sposób."),

                new Spell("Płonące dłonie", 1, SpellSchool.Evocation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.m4point5, "Stożek", "Instant", SpellDefense.Reflex, "1k4 na poziom. Podpala łatwopalne materiały"),
                new Spell("Magiczny pocisk", 1, SpellSchool.Evocation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Medium, "Cel na pocisk, nie dalej niż 4.5m", "Instant", SpellDefense.None, "1k4+1 na 2 levele, "),
                new Spell("Zauroczenie", 1, SpellSchool.Enchantment, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Close, "Jeden humanoid", "1h / lvl", SpellDefense.Will, "Istota traktuje Cię jak zaufanego przyjaciela. Jak mu zagrażamy to +5 do rzutu obronnego. Jak mu dam głupi rozkaz to test charyzmy. Jak sprawiamy zagrożenie to czar się kończy."),
                new Spell("Trzewia z żelaza", 1, SpellSchool.Abjuration, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Material)), SpellRange.Touch, "Dotyk", "10 min / lvl", SpellDefense.Will, "+4 na wytrwałość przeciw truciznom"),
                new Spell("Tarcza", 1, SpellSchool.Abjuration, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Self, "Ja", "10 min / lvl", SpellDefense.None, "Neguje magiczne pociski, +4 KP z tarczy, również przed bezcielesne ataki dotykowe"),
                new Spell("Alarm", 1, SpellSchool.Abjuration, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Focus)), SpellRange.Close, "Emanacja o promieniu 6m", "2 godziny / lvl", SpellDefense.None, "Alarm jak malutka lub większa wkroczy w krąg. Jak podasz hasło to nie triggruje alarmu. Decyduje czy będzie mentalny czy słyszalny."),
                new Spell("Deszcz Kolorów", 1, SpellSchool.Illusion, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic,  SpellComponent.Material)), SpellRange.m4point5, "Stożek", "Instant", SpellDefense.Will, "2KW i mniej: nieprzytomna, oślepiona i oszołomiona na 2k4. (3-4KW) Potem 1k4 oślepiona i oszołomiona i (5KW+) 1 runde oszołomiona"),
                new Spell("Identyfikacja", 1, SpellSchool.Divination, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic, SpellComponent.Material)), SpellRange.Touch, "1 przedmiot", "Instant", SpellDefense.None, "Rzuca się 1 godzinę. Potemmożna określić wszystkie właściwości przedmiotu, oraz liczbę ładunków. Komponent: perła o wartości co najmniej 100sz"),
                new Spell("Porażający uścisk", 1, SpellSchool.Evocation, new HashSet<>(Arrays.asList(SpellComponent.Verbal, SpellComponent.Somatic)), SpellRange.Touch, "1 cel", "Instant", SpellDefense.None, "Atak wręcz 1k6 na poziom. +3 do ataku jak przeciwnik ma dużo metalu"),
        };
    }
}
