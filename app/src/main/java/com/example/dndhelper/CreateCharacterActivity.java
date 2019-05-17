package com.example.dndhelper;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.Class;
import com.example.dndhelper.enums.SpellSchool;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateCharacterActivity extends AppCompatActivity {

    Spinner classSpinner;
    Spinner extraSpellSchoolSpinner;
    Spinner forbiddenSpellSchool1Spinner;
    Spinner forbiddenSpellSchool2Spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        setValues();
    }

    private void setValues() {
        classSpinner = findViewById(R.id.classSpinner);
        SpinnerAdapter classSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getClassNames());
        classSpinner.setAdapter(classSpinnerAdapter);

        extraSpellSchoolSpinner = findViewById(R.id.extraSpellSchoolSpinner);
        SpinnerAdapter spellSchoolAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getSpellSchools());
        extraSpellSchoolSpinner.setAdapter(spellSchoolAdapter);

        forbiddenSpellSchool1Spinner = findViewById(R.id.forbiddenSpellSchool1Spinner);
        forbiddenSpellSchool1Spinner.setAdapter(spellSchoolAdapter);

        forbiddenSpellSchool2Spinner = findViewById(R.id.forbiddenSpellSchool2Spinner);
        forbiddenSpellSchool2Spinner.setAdapter(spellSchoolAdapter);


    }

    private ArrayList<String> getClassNames() {
        ArrayList<String> classNames = new ArrayList<>();
        for (Class myClass : Class.values()) {
            classNames.add(myClass.getValue());
        }

        return classNames;
    }

    private ArrayList<String> getSpellSchools() {
        ArrayList<String> spellSchools = new ArrayList<>();
        for (SpellSchool mySchool : SpellSchool.values()) {
            if (mySchool == SpellSchool.None || mySchool == SpellSchool.Universal){
                continue;
            }

            spellSchools.add(mySchool.getValue());
        }

        return spellSchools;
    }

    public void finishCreating(View view) {
        TextInputEditText nameView = findViewById(R.id.nameTextInputEditText);
        TextInputEditText attributeView = findViewById(R.id.attributeTextInputEditText);
        int attributeValue = Integer.parseInt(attributeView.getText().toString());
        Class characterClass = Class.values()[classSpinner.getSelectedItemPosition()];
        SpellSchool extraSpellSchool = SpellSchool.values()[extraSpellSchoolSpinner.getSelectedItemPosition()];
        SpellSchool forbiddenOneSchool = SpellSchool.values()[forbiddenSpellSchool1Spinner.getSelectedItemPosition()];
        SpellSchool forbiddenTwoSchool = SpellSchool.values()[forbiddenSpellSchool2Spinner.getSelectedItemPosition()];
        Character.createNewCharacter(nameView.getText().toString(), characterClass, characterClass.getHitDice(), extraSpellSchool, new ArrayList<>(Arrays.asList(forbiddenOneSchool, forbiddenTwoSchool)), attributeValue);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}