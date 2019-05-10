package com.example.dndhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.dndhelper.character.Character;
import com.example.dndhelper.character.QurritoCreator;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    private Character character;
    private static String FILENAME = "character";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadCharacter();
        fillUi();
    }

    private void fillUi() {
        TextView healthTextView = findViewById(R.id.healthStatusTextView);
        healthTextView.setText(String.format("%s / %s", character.getHealth().getHitPoints(), character.getHealth().getContusion()));

        TextView moneyTextView = findViewById(R.id.moneyStatusTextView);
        moneyTextView.setText(String.format("%sg %ss %sc", character.getMoney().getGold(), character.getMoney().getSilver(), character.getMoney().getCooper()));

        createSpellbook();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveCharacter();
    }

    public boolean fileExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        return file != null && file.exists();
    }

    private void loadCharacter() {
        try {
            if (fileExists(this, FILENAME)) {
                StringBuilder text = new StringBuilder();

                BufferedReader reader = new BufferedReader(new FileReader(this.getFileStreamPath(FILENAME)));
                String line;

                while ((line = reader.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                reader.close();

                character = new Gson().fromJson(text.toString(), Character.class);
                if (character.getHealth() != null || character.getMoney() != null || character.getSpellbook() != null) {
                    return;
                }
            }

            character = QurritoCreator.createQurrito();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveCharacter() {
        FileOutputStream outputStream;
        try {
            if (character != null) {
                outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                outputStream.write(new Gson().toJson(character).getBytes());
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createSpellbook() {
        TabLayout spellbook = findViewById(R.id.spellLayout);
        spellbook.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int level = Integer.parseInt(tab.getText().toString());
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment fragment = SpellbookFragment.newInstance(character.getSpellbook().getLearnedSpells(level));
                ft.replace(R.id.fragment, fragment);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i <= this.character.getSpellbook().getMaxSpellLevel(); i++) {
            CharSequence cs = String.format("%s", Integer.toString(i));
            spellbook.addTab(spellbook.newTab().setText(cs));
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = SpellbookFragment.newInstance(this.character.getSpellbook().getLearnedSpells(0));
        ft.replace(R.id.fragment, fragment);
        ft.commit();
    }
}
