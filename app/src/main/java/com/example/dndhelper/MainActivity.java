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
import com.example.dndhelper.enums.Class;
import com.example.dndhelper.enums.SpellListIntent;
import com.example.dndhelper.fragments.SpellLevelSpellbookFragment;
import com.example.dndhelper.fragments.SpellSchoolSpellbookFragment;
import com.example.dndhelper.fragments.SpellbookFragment;
import com.example.dndhelper.spells.AllSpells;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private SpellbookFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AllSpells.getInstance().readFromJSON(getResources().openRawResource(R.raw.spells0));
        AllSpells.getInstance().readFromJSON(getResources().openRawResource(R.raw.spells1));
        AllSpells.getInstance().readFromJSON(getResources().openRawResource(R.raw.spells2));

        setContentView(R.layout.activity_main);
        updateMoneyText();
        updateHealthText();
        createSpellbook();

    }

    private void updateHealthText() {
        TextView healthTextView = findViewById(R.id.healthStatusTextView);
        healthTextView.setText(String.format("%s / %s / (%s)", Character.getInstance().getHealth().getHitPoints(), Character.getInstance().getHealth().getContusion(), Character.getInstance().getHealth().getMaxHitPoints()));
    }

    private void updateMoneyText() {
        TextView moneyTextView = findViewById(R.id.moneyStatusTextView);
        moneyTextView.setText(String.format("%sg %ss %sc", Character.getInstance().getMoney().getGold(), Character.getInstance().getMoney().getSilver(), Character.getInstance().getMoney().getCopper()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            Character.getInstance().saveCharacter(openFileOutput(Character.getInstance().getCharacterFileName(), MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ReturnCodes.LEARNED_SPELL) {

        } else if (resultCode == ReturnCodes.PREPARED_SPELL) {
            fragment.notifySpellChanged();
            fragment.notifyChargesChanged();
        } else if (resultCode == ReturnCodes.PREPARED_EXTRA){
            fragment.notifySpellChanged();
            fragment.notifyChargesChanged();
        } else if (resultCode == ReturnCodes.LEVEL_UP) {
            createSpellbook();
            updateHealthText();
        }
    }

    public void notifySpellCasted() {
        fragment.notifySpellChanged();
    }

    private void createSpellbook() {
        TabLayout spellbook = findViewById(R.id.spellLayout);
        spellbook.removeAllTabs();
        spellbook.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("EXTRA")) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    fragment = SpellSchoolSpellbookFragment.newInstance();
                    ft.replace(R.id.fragment, fragment);
                    ft.commit();
                } else {
                    int level = Integer.parseInt(tab.getText().toString());
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    fragment = SpellLevelSpellbookFragment.newInstance(level);
                    ft.replace(R.id.fragment, fragment);
                    ft.commit();
                }
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

        // TODO this is very bad, should be moved to Class fragment
        if (Character.getInstance().getSpellClass() == Class.Wizard) {
            CharSequence cs = "EXTRA";
            spellbook.addTab(spellbook.newTab().setText(cs));
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment = SpellLevelSpellbookFragment.newInstance(0);
        ft.replace(R.id.fragment, fragment);
        ft.commit();
    }

    public void dealDamage(View view) {
        // https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
        TextView hpView = findViewById(R.id.healthModificationsText);
        if (hpView.getText().length() == 0) {
            return;
        }
        Character.getInstance().getHealth().dealDamage(Integer.parseInt(hpView.getText().toString()));
        hpView.setText("");
        updateHealthText();
        view.requestFocusFromTouch();
    }

    public void dealContusion(View view) {
        TextView hpView = findViewById(R.id.healthModificationsText);
        hpView.clearFocus();
        if (hpView.getText().length() == 0) {
            return;
        }
        Character.getInstance().getHealth().dealContusionDamage(Integer.parseInt(hpView.getText().toString()));
        hpView.setText("");
        updateHealthText();
        view.requestFocusFromTouch();
    }

    public void healDamage(View view) {
        TextView hpView = findViewById(R.id.healthModificationsText);
        hpView.clearFocus();
        if (hpView.getText().length() == 0) {
            return;
        }
        Character.getInstance().getHealth().healDamage(Integer.parseInt(hpView.getText().toString()));
        hpView.setText("");
        updateHealthText();
    }

    public void addMoney(View view) {
        TextView moneyView = findViewById(R.id.moneyModificationsText);
        moneyView.clearFocus();
        if (moneyView.getText().length() == 0) {
            return;
        }
        Character.getInstance().getMoney().addCopper(Integer.parseInt(moneyView.getText().toString()));
        moneyView.setText("");
        updateMoneyText();
    }

    public void subMoney(View view) {
        TextView moneyView = findViewById(R.id.moneyModificationsText);
        moneyView.clearFocus();
        if (moneyView.getText().length() == 0) {
            return;
        }
        Character.getInstance().getMoney().subCopper(Integer.parseInt(moneyView.getText().toString()));
        moneyView.setText("");
        updateMoneyText();
    }

    public void prepareSpells(View view) {
        TabLayout layout = findViewById(R.id.spellLayout);
        if (layout.getSelectedTabPosition() < 0) {
            return;
        }

        if (isExtraTab(layout)) {
            startActivityForResult(SpellListActivity.getIntent(getBaseContext(), layout.getSelectedTabPosition(), SpellListIntent.SetExtra), 0);
            return;
        }

        startActivityForResult(SpellListActivity.getIntent(getBaseContext(), layout.getSelectedTabPosition(), SpellListIntent.Prepare), 0);
    }

    public void learnSpell(View view) {
        TabLayout layout = findViewById(R.id.spellLayout);
        if (layout.getSelectedTabPosition() < 0 || isExtraTab(layout)) {
            return;
        }

        startActivityForResult(SpellListActivity.getIntent(getBaseContext(), layout.getSelectedTabPosition(), SpellListIntent.Learn), 0);
    }

    public void longRest(View view) {
        Character.getInstance().getHealth().healDamage(2);
        Character.getInstance().getSpellbook().resetDailyCharges();
        updateHealthText();
        fragment.notifyChargesChanged();
    }

    public void levelUp(View view) {
        Intent intent = new Intent(this, LevelUpActivity.class);
        startActivityForResult(intent, 0);
    }

    private boolean isExtraTab(TabLayout layout) {
        return layout.getTabAt(layout.getSelectedTabPosition()) != null && layout.getTabAt(layout.getSelectedTabPosition()).getText() != null && layout.getTabAt(layout.getSelectedTabPosition()).getText().equals("EXTRA");
    }
}
