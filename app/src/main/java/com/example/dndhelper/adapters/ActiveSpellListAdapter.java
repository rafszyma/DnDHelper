package com.example.dndhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dndhelper.MainActivity;
import com.example.dndhelper.R;
import com.example.dndhelper.SpellException;
import com.example.dndhelper.SpellInfoActivity;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.SpellDefense;
import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;

public class ActiveSpellListAdapter extends GenericSpellListAdapter {

    private boolean isExtraSpellTab;

    public ActiveSpellListAdapter(@NonNull Context context, ArrayList<Spell> list, boolean isExtraSpellTab) {
        super(context, list);
        this.isExtraSpellTab = isExtraSpellTab;
        this.layoutId = R.layout.active_spell_record;
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

        Button castSpellButton = listItem.findViewById(R.id.castSpellButton);
        castSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isExtraSpellTab) {
                        Character.getInstance().getSpellbook().castExtraSpell(currentSpell);
                    } else {
                        Character.getInstance().getSpellbook().castSpell(currentSpell);
                    }
                    SpellDefense currentDefense = currentSpell.getDefense();
                    if (currentDefense != SpellDefense.None) {
                        if (currentDefense == SpellDefense.Reflex || currentDefense == SpellDefense.Endurance || currentDefense == SpellDefense.Will) {
                            int dc = 10 + currentSpell.getLevel() + Character.getInstance().getSpellClass().getAttributeModificator(Character.getInstance().getAttributes());
                            if (currentSpell.getSchool() == Character.getInstance().getSpellbook().getExtraSpellSchool()) {
                                dc += 2;
                            }

                            makeToast(String.format("Rzuciłeś czar z ST %s na %s", dc, currentDefense.getValue()), Toast.LENGTH_LONG);
                        } else {
                            makeToast(String.format("Rzuciłeś czar dotykowy, bez KP ze zbroi / tarczy / naturalnego"), Toast.LENGTH_LONG);
                        }
                    } else {
                        makeToast(String.format("Rzuciłeś czar bez obrony"), Toast.LENGTH_LONG);
                    }
                } catch (SpellException e) {
                    makeToast(e.getMessage(), Toast.LENGTH_SHORT);
                }
                ((MainActivity) sContext).notifySpellCasted();
            }
        });

        Button changeSpellButton = listItem.findViewById(R.id.changeSpellButton);
        changeSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Character.getInstance().getSpellbook().changeSpell(currentSpell);
                    ((MainActivity) sContext).prepareSpells(null);
                } catch (SpellException e) {
                    makeToast(e.getMessage(), Toast.LENGTH_SHORT);
                }

            }
        });

        return listItem;
    }
}
