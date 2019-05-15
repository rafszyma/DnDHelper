package com.example.dndhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dndhelper.R;
import com.example.dndhelper.SpellException;
import com.example.dndhelper.SpellInfoActivity;
import com.example.dndhelper.SpellListActivity;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;


public class PrepareSpellListAdapter extends GenericSpellListAdapter {

    public PrepareSpellListAdapter(@NonNull Context context, ArrayList<Spell> list) {
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

        Button prepareSpellButton = listItem.findViewById(R.id.spellActionButton);
        prepareSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Character.getInstance().getSpellbook().prepareSpell(currentSpell);
                } catch (SpellException e) {
                    makeToast(e.getMessage(), Toast.LENGTH_SHORT);
                }
                ((SpellListActivity) sContext).setResult(SpellInfoActivity.PREPARED_SPELL);
                ((SpellListActivity) sContext).finish();
            }
        });

        return listItem;
    }
}
