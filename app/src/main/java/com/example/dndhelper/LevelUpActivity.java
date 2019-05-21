package com.example.dndhelper;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.Class;

public class LevelUpActivity extends AppCompatActivity {

    Spinner classSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);

        classSpinner = findViewById(R.id.classSpinner);
        SpinnerAdapter classSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Class.getClassNames());
        classSpinner.setAdapter(classSpinnerAdapter);

        TextView headerTextView = findViewById(R.id.headerView);
        headerTextView.setText(String.format("Gratulacje, awansowałeś na %s poziom!", Character.getInstance().getClasses().getOverallLevel() + 1));


    }

    public void finishLeveling(View view) {
        Class characterClass = Class.values()[classSpinner.getSelectedItemPosition()];
        TextInputEditText hitDiceView = findViewById(R.id.hitDiceInputEditText);
        int hitDiceValue = Integer.parseInt(hitDiceView.getText().toString());
        Character.getInstance().levelUp(hitDiceValue, characterClass);
    }


}
