package com.example.dndhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.SpellComponent;
import com.example.dndhelper.enums.SpellRange;
import com.example.dndhelper.spells.Spell;

import java.util.HashSet;

public class SpellInfoActivity extends AppCompatActivity {

    public static int PREPARED_SPELL = 1;
    public static int LEARNED_SPELL = 2;
    public static int PREPARED_EXTRA = 3;
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
        schoolView.setText(spell.getSchool().getValue());

        TextView componentsView = findViewById(R.id.componentsValueSpellView);
        componentsView.setText(getSpellComponentsString(spell.getComponents()));

        TextView rangeView = findViewById(R.id.rangeValueSpellView);
        rangeView.setText(getSpellRangeString(spell.getRange()));

        TextView targetView = findViewById(R.id.targetValueSpellView);
        targetView.setText(spell.getTarget());

        TextView durationView = findViewById(R.id.durationValueSpellView);
        durationView.setText(spell.getDuration());

        TextView defenseView = findViewById(R.id.defenseValueSpellView);
        defenseView.setText(spell.getDefense().getValue());

        TextView descriptionView = findViewById(R.id.descriptionValueSpellView);
        descriptionView.setText(spell.getShortDescription());
    }

    private String getSpellRangeString(SpellRange range) {
        double rangeValue;
        switch (range) {
            case Close:
                rangeValue = 7.5 + ((Character.getInstance().getSpellbook().getSpellClassLevel() >> 1) * 1.5);
                return String.format("%sm", (int) rangeValue);
            case Medium:
                rangeValue = 30 + (Character.getInstance().getSpellbook().getSpellClassLevel() * 3);
                return String.format("%sm", (int) rangeValue);
            case Far:
                rangeValue = 120 + (Character.getInstance().getSpellbook().getSpellClassLevel() * 12);
                return String.format("%sm", (int) rangeValue);
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
            case m6:
                return "6m";
            case km1point5:
                rangeValue = (Character.getInstance().getSpellbook().getSpellClassLevel() * 1.5);
                return String.format("%skm", rangeValue);
        }

        throw new TypeNotPresentException(range.toString(), new Exception());
    }

    private String getSpellComponentsString(HashSet<SpellComponent> components) {
        String componentString = "";
        for (SpellComponent component : components) {
            if (componentString.isEmpty()) {
                componentString = component.getValue();
            } else {
                componentString = componentString.concat(", " + component.getValue());
            }
        }

        return componentString;
    }
}
