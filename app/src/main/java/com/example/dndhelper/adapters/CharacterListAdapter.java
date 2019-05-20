package com.example.dndhelper.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dndhelper.R;
import com.example.dndhelper.WelcomeActivity;

import java.util.ArrayList;

public class CharacterListAdapter extends ArrayAdapter<String> {
    Context sContext;
    ArrayList<String> characterNames;

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
            listItem = LayoutInflater.from(sContext).inflate(R.layout.character_rectord, parent, false);

        final String currentCharacter = characterNames.get(position);

        TextView characterTextView = listItem.findViewById(R.id.characterNameTextView);
        characterTextView.setText(currentCharacter);
        characterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WelcomeActivity) sContext).openCharacter(currentCharacter);
            }
        });

        return listItem;
    }
}
