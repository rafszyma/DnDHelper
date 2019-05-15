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

import com.example.dndhelper.R;
import com.example.dndhelper.SpellInfoActivity;
import com.example.dndhelper.SpellListActivity;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;

public class LearnSpellListAdapter extends GenericSpellListAdapter {

    public LearnSpellListAdapter(@NonNull Context context, ArrayList<Spell> list) {
        super(context, list);
        this.layoutId = R.layout.spell_action_record;
    }

    @Override
    protected View fillCurrentView(View listItem, final Spell currentSpell) {
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

        Button learnSpellButton = listItem.findViewById(R.id.spellActionButton);
        learnSpellButton.setText(R.string.learn);
        learnSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Character.getInstance().getSpellbook().learnSpell(currentSpell);
                ((SpellListActivity) sContext).finish();
            }
        });

        return listItem;
    }
}
