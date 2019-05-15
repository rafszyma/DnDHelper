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
import com.example.dndhelper.enums.SpellComponent;
import com.example.dndhelper.enums.SpellDefense;
import com.example.dndhelper.enums.SpellListIntent;
import com.example.dndhelper.enums.SpellSchool;
import com.example.dndhelper.spells.AllSpells;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    public static String FILENAME = "character";
    // This have to be changed

    private SpellbookFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnumStrings();
        setEnumColors();
        AllSpells.getInstance().readFromJSON(getResources().openRawResource(R.raw.spells0));
        AllSpells.getInstance().readFromJSON(getResources().openRawResource(R.raw.spells1));
        AllSpells.getInstance().readFromJSON(getResources().openRawResource(R.raw.spells2));
        Character.initialize(getFileStreamPath(FILENAME));
        setContentView(R.layout.activity_main);
        updateMoneyText();
        updateHealthText();
        createSpellbook();

    }

    private void setEnumStrings() {
        SpellDefense.Endurance.setValue(getResources().getString(R.string.endurance));
        SpellDefense.Reflex.setValue(getResources().getString(R.string.reflex));
        SpellDefense.Will.setValue(getResources().getString(R.string.will));
        SpellDefense.None.setValue("-");
        SpellDefense.TouchAttackMelee.setValue(getResources().getString(R.string.touch_attack_melee));
        SpellDefense.TouchAttackRanged.setValue(getResources().getString(R.string.touch_attack_range));

        SpellComponent.Focus.setValue(getResources().getString(R.string.focus));
        SpellComponent.Material.setValue(getResources().getString(R.string.material));
        SpellComponent.Verbal.setValue(getResources().getString(R.string.verbal));
        SpellComponent.Somatic.setValue(getResources().getString(R.string.somatic));

        SpellSchool.Abjuration.setValue(getResources().getString(R.string.abjuration_school));
        SpellSchool.Conjuration.setValue(getResources().getString(R.string.conjuration_school));
        SpellSchool.Divination.setValue(getResources().getString(R.string.divination_school));
        SpellSchool.Enchantment.setValue(getResources().getString(R.string.enchantment_school));
        SpellSchool.Evocation.setValue(getResources().getString(R.string.evocation_school));
        SpellSchool.Illusion.setValue(getResources().getString(R.string.illusion_school));
        SpellSchool.Necromancy.setValue(getResources().getString(R.string.necromancy_school));
        SpellSchool.Transmutation.setValue(getResources().getString(R.string.transmutation_school));
        SpellSchool.Universal.setValue(getResources().getString(R.string.universal_school));
    }

    private void setEnumColors() {
        SpellSchool.Abjuration.setColor(getResources().getColor(R.color.abjuration, null));
        SpellSchool.Conjuration.setColor(getResources().getColor(R.color.conjuration, null));
        SpellSchool.Divination.setColor(getResources().getColor(R.color.divination, null));
        SpellSchool.Enchantment.setColor(getResources().getColor(R.color.enchantment, null));
        SpellSchool.Evocation.setColor(getResources().getColor(R.color.evocation, null));
        SpellSchool.Illusion.setColor(getResources().getColor(R.color.illusion, null));
        SpellSchool.Necromancy.setColor(getResources().getColor(R.color.necromancy, null));
        SpellSchool.Transmutation.setColor(getResources().getColor(R.color.transmutation, null));
        SpellSchool.Universal.setColor(getResources().getColor(R.color.universal, null));
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
            Character.getInstance().saveCharacter(openFileOutput(FILENAME, MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SpellInfoActivity.LEARNED_SPELL) {

        } else if (resultCode == SpellInfoActivity.PREPARED_SPELL) {
            fragment.notifySpellChanged();
            fragment.notifyChargesChanged();
        }
    }

    public void notifySpellCasted() {
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
        TabLayout layout = findViewById(R.id.spellLayout);
        startActivityForResult(SpellListActivity.getIntent(getBaseContext(), layout.getSelectedTabPosition(), SpellListIntent.Prepare), 0);
    }

    public void learnSpell(View view) {
        TabLayout layout = findViewById(R.id.spellLayout);
        startActivityForResult(SpellListActivity.getIntent(getBaseContext(), layout.getSelectedTabPosition(), SpellListIntent.Learn), 0);
    }

    public void longRest(View view) {
        Character.getInstance().getHealth().healDamage(2);
        Character.getInstance().getSpellbook().resetDailyCharges();
        updateHealthText();
        fragment.notifyChargesChanged();

    }
}
