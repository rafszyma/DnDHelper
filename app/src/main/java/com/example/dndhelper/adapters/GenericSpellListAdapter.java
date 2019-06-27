package com.example.dndhelper.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericSpellListAdapter extends ArrayAdapter<Spell> {
    Context sContext;
    int layoutId;
    private List<Spell> spellList;

    GenericSpellListAdapter(@NonNull Context context, ArrayList<Spell> list) {
        super(context, 0, list);
        sContext = context;
        spellList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(layoutId, parent, false);

        final Spell currentSpell = spellList.get(position);

        listItem = fillCurrentView(listItem, currentSpell);

        return listItem;
    }

    protected View fillCurrentView(View listItem, Spell currentSpell) {
        return listItem;
    }

    void makeToast(String message, int duration) {
        Toast toast = Toast.makeText(sContext, message, duration);
        toast.show();
    }
}
