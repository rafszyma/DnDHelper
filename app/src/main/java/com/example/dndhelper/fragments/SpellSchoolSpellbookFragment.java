package com.example.dndhelper.fragments;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dndhelper.R;
import com.example.dndhelper.adapters.ActiveSpellListAdapter;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.spells.Spell;

import java.util.ArrayList;
import java.util.Objects;

public class SpellSchoolSpellbookFragment extends SpellbookFragment {

    public static SpellbookFragment newInstance() {
        return new SpellSchoolSpellbookFragment();
    }

    @Override
    public View InitializeList(View spellView) {
        ListView spellList = spellView.findViewById(R.id.activeSpellList);
        ArrayList<Spell> extraSpellList = new ArrayList<>();
        Spell extraSpell = Character.getInstance().getSpellbook().getExtraSpell();
        if (extraSpell != null)
        {
            extraSpellList.add(extraSpell);
        }

        adapter = new ActiveSpellListAdapter(Objects.requireNonNull(getActivity()), extraSpellList, true);
        spellList.setAdapter(adapter);
        notifyChargesChanged();

            return spellView;
    }

    @Override
    public void notifyChargesChanged() {
        TextView spellCharges = spellView.findViewById(R.id.chargesTextView);
        if (Character.getInstance().getSpellbook().isAvailableExtraSpell()) {
            spellCharges.setText("Available");
        } else {
            spellCharges.setText("Unavailable");
        }
    }
}
