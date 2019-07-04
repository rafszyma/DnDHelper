package com.example.dndhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.dndhelper.adapters.CharacterListAdapter;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.Attribute;
import com.example.dndhelper.enums.Class;
import com.example.dndhelper.enums.SpellComponent;
import com.example.dndhelper.enums.SpellDefense;
import com.example.dndhelper.enums.SpellSchool;

import java.io.File;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    CharacterListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setEnumStrings();
        setEnumColors();

        adapter = new CharacterListAdapter(this, getAllCharacters());
        ListView characterListView = findViewById(R.id.characterList);
        characterListView.setAdapter(adapter);
    }

    public void createNewCharacter(View view) {
        Intent intent = new Intent(this, CreateCharacterActivity.class);
        startActivity(intent);
    }

    public void deleteCharacter(String characterName) {
        String filename = String.format("%s_character", characterName);
        File file = getFileStreamPath(filename);
        file.delete();

        adapter.clear();
        adapter.addAll(getAllCharacters());
    }

    public ArrayList<String> getAllCharacters() {
        ArrayList<String> characters = new ArrayList<>();
        File[] files = getFilesDir().listFiles();
        for (File file : files) {
            String[] charFile = file.getName().split("_");
            if (charFile.length > 1) {
                characters.add(charFile[0]);
            }
        }

        return characters;
    }

    public void openCharacter(String characterName) {
        String filename = String.format("%s_character", characterName);
        Character.initialize(getFileStreamPath(filename));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

        Class.Bard.setValue(getResources().getString(R.string.bard));
        Class.Cleric.setValue(getResources().getString(R.string.cleric));
        Class.Druid.setValue(getResources().getString(R.string.druid));
        Class.Paladin.setValue(getResources().getString(R.string.paladin));
        Class.Sorcerer.setValue(getResources().getString(R.string.sorcerer));
        Class.Wizard.setValue(getResources().getString(R.string.wizard));
        Class.Rogue.setValue(getResources().getString(R.string.rogue));

        Attribute.Strength.setValue(getResources().getString(R.string.strength));
        Attribute.Dexterity.setValue(getResources().getString(R.string.dexterity));
        Attribute.Constitution.setValue(getResources().getString(R.string.constitution));
        Attribute.Wisdom.setValue(getResources().getString(R.string.wisdom));
        Attribute.Charisma.setValue(getResources().getString(R.string.charisma));
        Attribute.Intelligence.setValue(getResources().getString(R.string.intelligence));
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
}
