package com.example.dndhelper.spells;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.dndhelper.enums.Class;
import com.example.dndhelper.enums.SpellComponent;
import com.example.dndhelper.enums.SpellDefense;
import com.example.dndhelper.enums.SpellRange;
import com.example.dndhelper.enums.SpellSchool;

import java.util.HashSet;

public class Spell implements Parcelable {
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

    private String name;
    private int level;
    private HashSet<Class> classes;
    private SpellSchool school;
    private HashSet<SpellComponent> components;
    private SpellRange range;
    private String target;
    private String duration;
    private SpellDefense defense;
    private String shortDescription;

    public Spell(String name, int level, SpellSchool spellSchool, HashSet<Class> classes, HashSet<SpellComponent> spellComponents, SpellRange spellRange, String target, String duration, SpellDefense defense, String shortDescription) {
        this.name = name;
        this.level = level;
        this.school = spellSchool;
        this.classes = classes;
        this.components = spellComponents;
        this.range = spellRange;
        this.target = target;
        this.duration = duration;
        this.defense = defense;
        this.shortDescription = shortDescription;
    }

    protected Spell(Parcel in) {
        name = in.readString();
        level = in.readInt();
        school = SpellSchool.valueOf(in.readString());
        components = (HashSet<SpellComponent>) in.readSerializable();
        classes = (HashSet<Class>) in.readSerializable();
        range = SpellRange.valueOf(in.readString());
        target = in.readString();
        duration = in.readString();
        defense = SpellDefense.valueOf(in.readString());
        shortDescription = in.readString();
    }

    public boolean checkIfValid() {
        return name != null && !name.isEmpty() && level >= 0 && !classes.contains(null) && school != null && !components.contains(null) && range != null && target != null && !target.isEmpty() && duration != null && !duration.isEmpty() && defense != null && shortDescription != null && !shortDescription.isEmpty();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public SpellSchool getSchool() {
        return school;
    }

    public HashSet<SpellComponent> getComponents() {
        return components;
    }

    public SpellRange getRange() {
        return range;
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
        dest.writeString(school.toString());
        dest.writeSerializable(components);
        dest.writeSerializable(classes);
        dest.writeString(range.toString());
        dest.writeString(target);
        dest.writeString(duration);
        dest.writeString(defense.toString());
        dest.writeString(shortDescription);
    }

    @Override
    public boolean equals(@NonNull Object obj) {
        return ((Spell) obj).name.equals(this.name);

    }
}
