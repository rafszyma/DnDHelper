package com.example.dndhelper.spells;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.dndhelper.enums.SpellComponent;
import com.example.dndhelper.enums.SpellDefense;
import com.example.dndhelper.enums.SpellRange;
import com.example.dndhelper.enums.SpellSchool;

import java.util.HashSet;

public class Spell implements Parcelable {
    private String name;
    private int level;
    private SpellSchool spellSchool;
    private HashSet<SpellComponent> spellComponents;
    private SpellRange spellRange;
    private String target;
    private String duration;
    private SpellDefense defense;
    private String shortDescription;

    public Spell(String name, int level, SpellSchool spellSchool, HashSet<SpellComponent> spellComponents, SpellRange spellRange, String target, String duration, SpellDefense defense, String shortDescription) {
        this.name = name;
        this.level = level;
        this.spellSchool = spellSchool;
        this.spellComponents = spellComponents;
        this.spellRange = spellRange;
        this.target = target;
        this.duration = duration;
        this.defense = defense;
        this.shortDescription = shortDescription;
    }

    protected Spell(Parcel in) {
        name = in.readString();
        level = in.readInt();
        target = in.readString();
        duration = in.readString();
        shortDescription = in.readString();
    }

    public static final Creator<Spell> CREATOR = new Creator<Spell>() {
        @Override
        public Spell createFromParcel(Parcel in) {
            return new Spell(in);
        }

        @Override
        public Spell[] newArray(int size) {
            return new Spell[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public SpellSchool getSpellSchool() {
        return spellSchool;
    }

    public HashSet<SpellComponent> getSpellComponents() {
        return spellComponents;
    }

    public SpellRange getSpellRange() {
        return spellRange;
    }

    public String getTarget() {
        return target;
    }

    public String getDuration() {
        return duration;
    }

    public SpellDefense getDefense() {
        return defense;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(level);
        dest.writeString(target);
        dest.writeString(duration);
        dest.writeString(shortDescription);
    }
}
