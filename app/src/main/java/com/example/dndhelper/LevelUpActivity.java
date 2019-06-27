package com.example.dndhelper;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.Attribute;
import com.example.dndhelper.enums.Class;

public class LevelUpActivity extends AppCompatActivity {

    Spinner classSpinner;

    Spinner attributeSpinner;

    int newLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newLevel = Character.getInstance().getClasses().getOverallLevel() + 1;
        setContentView(R.layout.activity_level_up);

        classSpinner = findViewById(R.id.classSpinner);
        SpinnerAdapter classSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Class.getClassNames());
        classSpinner.setAdapter(classSpinnerAdapter);

        attributeSpinner = findViewById(R.id.attributeSpinner);
        if (newLevel % 4 == 0) {
            attributeSpinner.setEnabled(true);
            SpinnerAdapter attributeSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Attribute.getAttributeNames());
            attributeSpinner.setAdapter(attributeSpinnerAdapter);
        } else {
            attributeSpinner.setEnabled(false);
        }

        TextView headerTextView = findViewById(R.id.headerView);
        headerTextView.setText(String.format("Gratulacje, awansowałeś na %s poziom!", newLevel));
    }

    public void finishLeveling(View view) {
        Class characterClass = Class.values()[classSpinner.getSelectedItemPosition()];
        TextInputEditText hitDiceView = findViewById(R.id.hitDiceInputEditText);
        int hitDiceValue = Integer.parseInt(hitDiceView.getText().toString());
        Character.getInstance().levelUp(hitDiceValue, characterClass);
        if (newLevel % 4 == 0) {
            Character.getInstance().getAttributes().increaseAttribute(attributeSpinner.getSelectedItemPosition());
        }
        setResult(ReturnCodes.LEVEL_UP);
        finish();
    }
}
