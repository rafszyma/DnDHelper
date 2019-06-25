package com.example.dndhelper.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dndhelper.R;
import com.example.dndhelper.adapters.ActiveSpellListAdapter;

public class SpellbookFragment extends Fragment {

    // TODO change it to extra spell fragment
    protected View spellView;
    protected ActiveSpellListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        spellView = inflater.inflate(R.layout.spellbook_fragment, container, false);
        return InitializeList(spellView);
    }

    public View InitializeList(View spellView){
        return spellView;
    }

    public void notifySpellChanged() {
        adapter.notifyDataSetChanged();
        notifyChargesChanged();
    }

    public void notifyChargesChanged(){
    }
}
