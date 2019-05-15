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
import android.widget.Toast;

import com.example.dndhelper.MainActivity;
import com.example.dndhelper.R;
import com.example.dndhelper.SpellException;
import com.example.dndhelper.SpellInfoActivity;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.SpellDefense;
import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;
import java.util.List;

public class ActiveSpellListAdapter extends GenericSpellListAdapter {

    public ActiveSpellListAdapter(@NonNull Context context, ArrayList<Spell> list) {
        super(context, list);
        this.layoutId = R.layout.active_spell_record;
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

        Button castSpellButton = listItem.findViewById(R.id.castSpellButton);
        castSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Character.getInstance().getSpellbook().castSpell(currentSpell);
                    SpellDefense currentDefense = currentSpell.getDefense();
                    if (currentDefense != SpellDefense.None) {
                        if (currentDefense == SpellDefense.Reflex || currentDefense == SpellDefense.Endurance || currentDefense == SpellDefense.Will) {
                            int dc = 10 + currentSpell.getLevel() + Character.getInstance().getAttributeModificator();
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
                ((MainActivity) sContext).notifyFragment();
            }
        });

        Button changeSpellButton = listItem.findViewById(R.id.changeSpellButton);
        changeSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Character.getInstance().getSpellbook().castSpell(currentSpell);
                } catch (SpellException e) {
                    makeToast(e.getMessage(), Toast.LENGTH_SHORT);
                }
                ((MainActivity) sContext).prepareSpells(null);
            }
        });

        return listItem;
    }
}
