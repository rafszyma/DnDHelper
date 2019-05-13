package com.example.dndhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.dndhelper.PrepareSpellActivity;
import com.example.dndhelper.R;
import com.example.dndhelper.SpellInfoActivity;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;
import java.util.List;


public class PrepareSpellListAdapter extends ArrayAdapter<Spell> {

    private Context sContext;
    private List<Spell> spellList;

    public PrepareSpellListAdapter(@NonNull Context context, ArrayList<Spell> list) {
        super(context, 0, list);
        sContext = context;
        spellList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(R.layout.learned_spell_record, parent, false);

        final Spell currentSpell = spellList.get(position);

        TextView textView = listItem.findViewById(R.id.spellNameView);
        textView.setText(currentSpell.getName());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sContext, SpellInfoActivity.class);
                intent.putExtra(SpellInfoActivity.SPELL_PARCEL_KEY, currentSpell);
                sContext.startActivity(intent);
            }
        });

        Button prepareSpellButton = listItem.findViewById(R.id.changeSpellButton);
        prepareSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Character.getInstance().getSpellbook().prepareSpell(currentSpell);
                ((PrepareSpellActivity) sContext).finish();
            }
        });

        return listItem;
    }
}
