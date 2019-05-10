package com.example.dndhelper;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.dndhelper.spells.Spell;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SpellbookFragment extends Fragment {

    private SpellBookViewModel mViewModel;

    private static String SPELL_KEY = "spell_key";

    private ArrayList<Spell> activeSpells;

    public static SpellbookFragment newInstance(ArrayList<Spell> activeSpells) {
        SpellbookFragment fragment = new SpellbookFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(SPELL_KEY, activeSpells);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View spellView = inflater.inflate(R.layout.spellbook_fragment, container, false);
         if( getArguments() != null) {
             activeSpells = getArguments().getParcelableArrayList(SPELL_KEY);


             ListView spellList = spellView.findViewById(R.id.spellList);
             SpellListAdapter adapter = new SpellListAdapter(Objects.requireNonNull(getActivity()), activeSpells);
             spellList.setAdapter(adapter);
             adapter.notifyDataSetChanged();
         }

        return spellView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SpellBookViewModel.class);
        // TODO: Use the ViewModel
    }

}
