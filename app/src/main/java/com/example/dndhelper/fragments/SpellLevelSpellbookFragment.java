package com.example.dndhelper.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dndhelper.R;
import com.example.dndhelper.adapters.ActiveSpellListAdapter;
import com.example.dndhelper.character.Character;

import java.util.Objects;

public class SpellLevelSpellbookFragment extends SpellbookFragment {

    private static String LEVEL_KEY = "level_key";

    private int spellLevel;

    public static SpellbookFragment newInstance(int level) {
        SpellbookFragment spellbookFragment = new SpellLevelSpellbookFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LEVEL_KEY, level);
        spellbookFragment.setArguments(bundle);
        return spellbookFragment;
    }

    @Override
    public View InitializeList(View spellView) {
        if (getArguments() != null && getArguments().containsKey(LEVEL_KEY)) {
            spellLevel = getArguments().getInt(LEVEL_KEY);

            ListView spellList = spellView.findViewById(R.id.activeSpellList);
            adapter = new ActiveSpellListAdapter(Objects.requireNonNull(getActivity()), Character.getInstance().getSpellbook().getActiveSpells(spellLevel), false);
            spellList.setAdapter(adapter);
            notifyChargesChanged();
        }

        return spellView;
    }

    @Override
    public void notifyChargesChanged() {
        TextView spellCharges = spellView.findViewById(R.id.chargesTextView);
        spellCharges.setText(String.format("%s / %s", Character.getInstance().getSpellbook().getCurrentSpellLevelCharges(spellLevel), Character.getInstance().getSpellbook().getMaxSpellLevelCharges(spellLevel)));
    }
}
