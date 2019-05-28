package com.example.dndhelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dndhelper.adapters.ExtraSpellListAdapter;
import com.example.dndhelper.adapters.LearnSpellListAdapter;
import com.example.dndhelper.adapters.PrepareSpellListAdapter;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.SpellListIntent;
import com.example.dndhelper.spells.AllSpells;
import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;

public class SpellListActivity extends AppCompatActivity {

    private static String LEVEL_KEY = "spell_level";
    private static String INTENT_KEY = "list_intent";

    public static Intent getIntent(Context baseContext, int spellLevel, SpellListIntent intentType) {
        Intent intent = new Intent(baseContext, SpellListActivity.class);
        intent.putExtra(LEVEL_KEY, spellLevel);
        intent.putExtra(INTENT_KEY, intentType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_spell);

        Intent intent = getIntent();
        ListView spellList = findViewById(R.id.spellList);
        SpellListIntent listIntent = (SpellListIntent) intent.getSerializableExtra(INTENT_KEY);
        int spellLevel = intent.getIntExtra(LEVEL_KEY, 0);
        ArrayAdapter<Spell> adapter = null;
        if (listIntent == SpellListIntent.Prepare) {
            adapter = new PrepareSpellListAdapter(this, Character.getInstance().getSpellbook().getLearnedSpells(spellLevel));
        } else if (listIntent == SpellListIntent.Learn){
            ArrayList<Spell> spellsToLearn = AllSpells.getInstance().getSpells(spellLevel);
            ArrayList<Spell> alreadyKnownSpells = Character.getInstance().getSpellbook().getLearnedSpells(spellLevel);
            spellsToLearn.removeAll(alreadyKnownSpells);
            adapter = new LearnSpellListAdapter(this, spellsToLearn);
        } else if (listIntent == SpellListIntent.SetExtra){
            ArrayList<Spell> spellsToChoose = Character.getInstance().getSpellbook().getExtraSpells();
            adapter = new ExtraSpellListAdapter(this, spellsToChoose);
        }

        spellList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
