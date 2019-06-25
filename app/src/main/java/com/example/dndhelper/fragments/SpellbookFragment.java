package com.example.dndhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dndhelper.adapters.ActiveSpellListAdapter;
import com.example.dndhelper.character.Character;
import com.example.dndhelper.enums.SpellSchool;

import java.util.Objects;

public class SpellbookFragment extends Fragment {

    // TODO change it to extra spell fragment
    private static String LEVEL_KEY = "level_key";
    private static String SPELL_SCHOOL = "spell_school";
    private View spellView;
    private ActiveSpellListAdapter adapter;
    private int spellLevel;
    private SpellSchool extraSpellSchool;

    public static SpellbookFragment newInstance(int level) {
        SpellbookFragment spellbookFragment = new SpellbookFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LEVEL_KEY, level);
        spellbookFragment.setArguments(bundle);
        return spellbookFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        spellView = inflater.inflate(R.layout.spellbook_fragment, container, false);
        if (getArguments() != null) {
            if (getArguments().containsKey(LEVEL_KEY)) {
                spellLevel = getArguments().getInt(LEVEL_KEY);

                ListView spellList = spellView.findViewById(R.id.activeSpellList);
                adapter = new ActiveSpellListAdapter(Objects.requireNonNull(getActivity()), Character.getInstance().getSpellbook().getActiveSpells(spellLevel));
                spellList.setAdapter(adapter);
                notifyChargesChanged();
            } else {
                ListView spellList = spellView.findViewById(R.id.activeSpellList);
                adapter = new ActiveSpellListAdapter(Objects.requireNonNull(getActivity()), Character.getInstance().getSpellbook().getExtraSpells());
                spellList.setAdapter(adapter);
                notifyChargesChanged();
            }
        }

        return spellView;
    }

    public void notifySpellChanged() {
        adapter.notifyDataSetChanged();
        notifyChargesChanged();
    }

    public void notifyChargesChanged() {
        TextView spellCharges = spellView.findViewById(R.id.chargesTextView);
        spellCharges.setText(String.format("%s / %s", Character.getInstance().getSpellbook().getCurrentSpellLevelCharges(spellLevel), Character.getInstance().getSpellbook().getMaxSpellLevelCharges(spellLevel)));
    }
}
