package com.example.dndhelper;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dndhelper.adapters.ActiveSpellListAdapter;
import com.example.dndhelper.character.Character;

import java.util.Objects;

public class SpellbookFragment extends Fragment {

    private static String LEVEL_KEY = "level_key";
    private SpellBookViewModel mViewModel;
    private ActiveSpellListAdapter adapter;

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
        View spellView = inflater.inflate(R.layout.spellbook_fragment, container, false);
        if (getArguments() != null) {
            int spellLevel = getArguments().getInt(LEVEL_KEY);

            ListView spellList = spellView.findViewById(R.id.activeSpellList);
            adapter = new ActiveSpellListAdapter(Objects.requireNonNull(getActivity()), Character.getInstance().getSpellbook().getActiveSpells(spellLevel));
            spellList.setAdapter(adapter);

        }

        return spellView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SpellBookViewModel.class);
        // TODO: Use the ViewModel
    }

    public void notifySpellChanged() {
        adapter.notifyDataSetChanged();
    }
}
