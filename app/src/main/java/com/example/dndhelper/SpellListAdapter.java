package com.example.dndhelper;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;
import java.util.List;


public class SpellListAdapter extends ArrayAdapter<Spell> {

    private Context sContext;
    private List<Spell> spellList;

    public SpellListAdapter (@NonNull Context context, ArrayList<Spell> list) {
        super(context, 0, list);
        sContext = context;
        spellList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(R.layout.spell_record,parent,false);

        Spell currentSpell = spellList.get(position);

        TextView textView = listItem.findViewById(R.id.spellNameView);
        textView.setText(currentSpell.getName());

        return listItem;
    }
}
