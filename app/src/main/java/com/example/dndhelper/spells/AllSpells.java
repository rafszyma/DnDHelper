package com.example.dndhelper.spells;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class AllSpells {
    private static final AllSpells ourInstance = new AllSpells();
    private static final int SPELL_LEVELS = 10;
    private ArrayList<HashSet<Spell>> spells;

    private AllSpells() {
        spells = new ArrayList<>();

        for (int i = 0; i < SPELL_LEVELS; i++) {
            spells.add(new HashSet<Spell>());
        }
    }

    public static AllSpells getInstance() {
        return ourInstance;
    }

    public ArrayList<Spell> getSpells(int level) {
        return new ArrayList<>(spells.get(level));
    }

    private boolean addSpell(Spell spell) {
        if (spell.getLevel() < SPELL_LEVELS) {
            return spells.get(spell.getLevel()).add(spell);
        }

        return false;
    }

    public boolean readFromJSON(InputStream inputStream) {
        JSONResourceReader(inputStream);

        return true;
    }

    private boolean JSONResourceReader(InputStream inputStream) {
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e(AllSpells.class.getName(), "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                Log.e(AllSpells.class.getName(), "Unhandled exception while using JSONResourceReader", e);
            }
        }

        Gson gson = new GsonBuilder().create();
        try {
            Type collectionType = new TypeToken<Collection<Spell>>() {
            }.getType();
            ArrayList<Spell> spellList = gson.fromJson(writer.toString(), collectionType);
            for (Spell spell : spellList) {
                this.addSpell(spell);
            }
        } catch (Exception ex) {
            Log.e(AllSpells.class.getName(), "Unhandled ex", ex);
            return false;
        }

        return true;
    }
}
