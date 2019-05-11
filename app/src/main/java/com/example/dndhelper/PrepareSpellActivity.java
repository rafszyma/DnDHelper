package com.example.dndhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;

public class PrepareSpellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_spell);

        Intent intent = getIntent();
        ArrayList<Spell> learnedSpells = intent.getParcelableArrayListExtra(MainActivity.SPELL_KEY);
        ListView spellList = findViewById(R.id.learnedSpellList);
        PrepareSpellListAdapter adapter = new PrepareSpellListAdapter(this, learnedSpells);
        spellList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
