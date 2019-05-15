package com.example.dndhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dndhelper.character.Character;
import com.example.dndhelper.spells.AllSpells;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    public static String SPELL_KEY = "learnedSpells";

    public static String FILENAME = "character";
    // This have to be changed

    private SpellbookFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AllSpells.getInstance().readFromJSON(getResources().openRawResource(R.raw.spells0));
        AllSpells.getInstance().readFromJSON(getResources().openRawResource(R.raw.spells1));
        AllSpells.getInstance().readFromJSON(getResources().openRawResource(R.raw.spells2));
        Character.initialize(getFileStreamPath(FILENAME));
        setContentView(R.layout.activity_main);
        updateMoneyText();
        updateHealthText();
        createSpellbook();

    }

    private void updateHealthText() {
        TextView healthTextView = findViewById(R.id.healthStatusTextView);
        healthTextView.setText(String.format("%s / %s", Character.getInstance().getHealth().getHitPoints(), Character.getInstance().getHealth().getContusion()));
    }

    private void updateMoneyText() {
        TextView moneyTextView = findViewById(R.id.moneyStatusTextView);
        moneyTextView.setText(String.format("%sg %ss %sc", Character.getInstance().getMoney().getGold(), Character.getInstance().getMoney().getSilver(), Character.getInstance().getMoney().getCopper()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            Character.getInstance().saveCharacter(openFileOutput(FILENAME, MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        notifyFragment();
    }

    public void notifyFragment() {
        fragment.notifySpellChanged();
    }

    private void createSpellbook() {
        TabLayout spellbook = findViewById(R.id.spellLayout);
        spellbook.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int level = Integer.parseInt(tab.getText().toString());
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                fragment = SpellbookFragment.newInstance(level);
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
        for (int i = 0; i <= Character.getInstance().getSpellbook().getMaxSpellLevel(); i++) {
            CharSequence cs = String.format("%s", Integer.toString(i));
            spellbook.addTab(spellbook.newTab().setText(cs));
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment = SpellbookFragment.newInstance(0);
        ft.replace(R.id.fragment, fragment);
        ft.commit();
    }

    public void dealDamage(View view) {
        TextView hpView = findViewById(R.id.healthModificationsText);
        Character.getInstance().getHealth().dealDamage(Integer.parseInt(hpView.getText().toString()));
        updateHealthText();
    }

    public void dealContusion(View view) {
        TextView hpView = findViewById(R.id.healthModificationsText);
        Character.getInstance().getHealth().dealContusionDamage(Integer.parseInt(hpView.getText().toString()));
        updateHealthText();
    }

    public void healDamage(View view) {
        TextView hpView = findViewById(R.id.healthModificationsText);
        Character.getInstance().getHealth().healDamage(Integer.parseInt(hpView.getText().toString()));
        updateHealthText();
    }

    public void addMoney(View view) {
        TextView moneyView = findViewById(R.id.moneyModificationsText);
        Character.getInstance().getMoney().addCopper(Integer.parseInt(moneyView.getText().toString()));
        updateMoneyText();
    }

    public void subMoney(View view) {
        TextView moneyView = findViewById(R.id.moneyModificationsText);
        Character.getInstance().getMoney().subCopper(Integer.parseInt(moneyView.getText().toString()));
        updateMoneyText();
    }

    public void prepareSpells(View view) {
        Intent intent = new Intent(getBaseContext(), PrepareSpellActivity.class);
        TabLayout layout = findViewById(R.id.spellLayout);
        intent.putExtra(SPELL_KEY, layout.getSelectedTabPosition());
        startActivityForResult(intent, 0);
    }
}
