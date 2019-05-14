package com.example.dndhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.SpellComponent;
import com.example.dndhelper.enums.SpellDefense;
import com.example.dndhelper.enums.SpellRange;
import com.example.dndhelper.enums.SpellSchool;
import com.example.dndhelper.spells.Spell;

import java.util.HashSet;

public class SpellInfoActivity extends AppCompatActivity {

    public static String SPELL_PARCEL_KEY = "spell_parcel_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_info);
        Intent intent = getIntent();
        Spell spell = intent.getParcelableExtra(SPELL_PARCEL_KEY);
        fillUiWithSpell(spell);

    }

    private void fillUiWithSpell(Spell spell) {
        TextView nameView = findViewById(R.id.nameValueSpellView);
        nameView.setText(spell.getName());

        TextView levelView = findViewById(R.id.levelValueSpellView);
        levelView.setText(Integer.toString(spell.getLevel()));

        TextView schoolView = findViewById(R.id.schoolValueSpellView);
        schoolView.setText(getSpellSchoolString(spell.getSchool()));

        TextView componentsView = findViewById(R.id.componentsValueSpellView);
        componentsView.setText(getSpellComponentsString(spell.getComponents()));

        TextView rangeView = findViewById(R.id.rangeValueSpellView);
        rangeView.setText(getSpellRangeString(spell.getRange()));

        TextView targetView = findViewById(R.id.targetValueSpellView);
        targetView.setText(spell.getTarget());

        TextView durationView = findViewById(R.id.durationValueSpellView);
        durationView.setText(spell.getDuration());

        TextView defenseView = findViewById(R.id.defenseValueSpellView);
        defenseView.setText(getDefenseString(spell.getDefense()));

        TextView descriptionView = findViewById(R.id.descriptionValueSpellView);
        descriptionView.setText(spell.getShortDescription());
    }

    private String getSpellSchoolString(SpellSchool school) {
        switch (school) {

            case None:
                throw new IllegalArgumentException("Spell have to have school assigned");
            case Abjuration:
                return getResources().getString(R.string.abjuration_school);
            case Conjuration:
                return getResources().getString(R.string.conjuration_school);
            case Divination:
                return getResources().getString(R.string.divination_school);
            case Enchantment:
                return getResources().getString(R.string.enchantment_school);
            case Evocation:
                return getResources().getString(R.string.evocation_school);
            case Illusion:
                return getResources().getString(R.string.illusion_school);
            case Necromancy:
                return getResources().getString(R.string.necromancy_school);
            case Transmutation:
                return getResources().getString(R.string.transmutation_school);
            case Universal:
                return getResources().getString(R.string.universal_school);
        }

        throw new TypeNotPresentException(school.toString(), new Exception());
    }

    private String getSpellRangeString(SpellRange range) {
        double rangeValue;
        switch (range) {
            case Close:
                rangeValue = 7.5 + ((Character.getInstance().getSpellbook().getSpellClassLevel() >> 1) * 1.5);
                return Double.toString(rangeValue);
            case Medium:
                rangeValue = 30 + (Character.getInstance().getSpellbook().getSpellClassLevel() * 3);
                return Double.toString(rangeValue);
            case Far:
                rangeValue = 120 + (Character.getInstance().getSpellbook().getSpellClassLevel() * 12);
                return Double.toString(rangeValue);
            case Touch:
                return getResources().getString(R.string.touch);
            case Self:
                return getResources().getString(R.string.self);
            case m18:
                return "18m";
            case m3:
                return "3m";
            case m4point5:
                return "4.5m";
        }

        throw new TypeNotPresentException(range.toString(), new Exception());
    }

    private String getDefenseString(SpellDefense defense) {
        switch (defense) {
            case None:
                return "-";
            case Will:
                return getResources().getString(R.string.will);
            case Reflex:
                return getResources().getString(R.string.reflex);
            case Endurance:
                return getResources().getString(R.string.endurance);
            case TouchAttackMelee:
                return getResources().getString(R.string.touch_attack_melee);
            case TouchAttackRanged:
                return getResources().getString(R.string.touch_attack_range);
        }

        throw new TypeNotPresentException(defense.toString(), new Exception());
    }

    private String getSpellComponentsString(HashSet<SpellComponent> components) {
        String componentString = "";
        for (SpellComponent component : components) {
            switch (component) {

                case Verbal:
                    if (componentString.isEmpty()) {
                        componentString = getResources().getString(R.string.verbal);
                    } else {
                        componentString = componentString.concat(", " + getResources().getString(R.string.verbal));
                    }
                    break;
                case Somatic:
                    if (componentString.isEmpty()) {
                        componentString = getResources().getString(R.string.somatic);
                    } else {
                        componentString = componentString.concat(", " + getResources().getString(R.string.somatic));
                    }
                    break;
                case Material:
                    if (componentString.isEmpty()) {
                        componentString = getResources().getString(R.string.material);
                    } else {
                        componentString = componentString.concat(", " + getResources().getString(R.string.material));
                    }
                    break;
                case Focus:
                    if (componentString.isEmpty()) {
                        componentString = getResources().getString(R.string.focus);
                    } else {
                        componentString = componentString.concat(", " + getResources().getString(R.string.focus));
                    }
                    break;
                default:
                    throw new TypeNotPresentException(component.toString(), new Exception());
            }
        }

        return componentString;
    }
}
