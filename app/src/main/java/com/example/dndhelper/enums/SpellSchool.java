package com.example.dndhelper.enums;

import com.google.gson.annotations.SerializedName;

public enum SpellSchool {
    @SerializedName("none")
    None,
    @SerializedName("abjuration")
    Abjuration,
    @SerializedName("conjuration")
    Conjuration,
    @SerializedName("divination")
    Divination,
    @SerializedName("enchantment")
    Enchantment,
    @SerializedName("evocation")
    Evocation,
    @SerializedName("illusion")
    Illusion,
    @SerializedName("necromancy")
    Necromancy,
    @SerializedName("transmutation")
    Transmutation,
    @SerializedName("universal")
    Universal;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(final int value) {
        this.color = value;
    }
}
