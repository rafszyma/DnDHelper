package com.example.dndhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.dndhelper.adapters.PrepareSpellListAdapter;
import com.example.dndhelper.character.Character;

public class PrepareSpellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_spell);

        Intent intent = getIntent();
        ListView spellList = findViewById(R.id.learnedSpellList);
        PrepareSpellListAdapter adapter = new PrepareSpellListAdapter(this, Character.getInstance().getSpellbook().getLearnedSpells(intent.getIntExtra(MainActivity.SPELL_KEY, 0)));
        spellList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
