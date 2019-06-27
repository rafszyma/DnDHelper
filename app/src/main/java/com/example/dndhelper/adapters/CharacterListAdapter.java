package com.example.dndhelper.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.dndhelper.R;
import com.example.dndhelper.WelcomeActivity;

import java.util.ArrayList;

public class CharacterListAdapter extends ArrayAdapter<String> {
    private Context sContext;

    private ArrayList<String> characterNames;

    public CharacterListAdapter(@NonNull Context context, ArrayList<String> list) {
        super(context, 0, list);
        sContext = context;
        characterNames = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(R.layout.character_record, parent, false);

        final String currentCharacter = characterNames.get(position);

        TextView characterTextView = listItem.findViewById(R.id.characterNameTextView);
        characterTextView.setText(currentCharacter);
        characterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WelcomeActivity) sContext).openCharacter(currentCharacter);
            }
        });

        Button deleteCharacter = listItem.findViewById(R.id.deleteButton);
        deleteCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WelcomeActivity) sContext).deleteCharacter(currentCharacter);
            }
        });

        return listItem;
    }
}
