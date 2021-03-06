package com.example.dndhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dndhelper.R;
import com.example.dndhelper.ReturnCodes;
import com.example.dndhelper.SpellException;
import com.example.dndhelper.SpellInfoActivity;
import com.example.dndhelper.SpellListActivity;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;

public class ExtraSpellListAdapter extends GenericSpellListAdapter {

    public ExtraSpellListAdapter(@NonNull Context context, ArrayList<Spell> list) {
        super(context, list);
        this.layoutId = R.layout.spell_action_record;
    }

    @Override
    protected View fillCurrentView(View listItem, final Spell currentSpell) {
        TextView spellNameView = listItem.findViewById(R.id.spellNameView);
        spellNameView.setText(currentSpell.getName());
        spellNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sContext, SpellInfoActivity.class);
                intent.putExtra(SpellInfoActivity.SPELL_PARCEL_KEY, currentSpell);
                sContext.startActivity(intent);
            }
        });

        TextView spellSchoolView = listItem.findViewById(R.id.spellSchoolView);
        spellSchoolView.setText(currentSpell.getSchool().getValue());
        spellSchoolView.setBackgroundColor(currentSpell.getSchool().getColor());

        Button prepareSpellButton = listItem.findViewById(R.id.spellActionButton);
        prepareSpellButton.setText(R.string.prepare_spell);
        prepareSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Character.getInstance().getSpellbook().setExtraSpell(currentSpell);
                } catch (SpellException e) {
                    makeToast(e.getMessage(), Toast.LENGTH_SHORT);
                }
                ((SpellListActivity) sContext).setResult(ReturnCodes.PREPARED_EXTRA);
                ((SpellListActivity) sContext).finish();
            }
        });

        return listItem;
    }
}
