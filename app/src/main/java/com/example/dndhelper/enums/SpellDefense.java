package com.example.dndhelper.enums;

import com.google.gson.annotations.SerializedName;

public enum SpellDefense {
    @SerializedName("none")
    None,
    @SerializedName("will")
    Will,
    @SerializedName("reflex")
    Reflex,
    @SerializedName("endurance")
    Endurance,
    @SerializedName("touch_melee")
    TouchAttackMelee,
    @SerializedName("touch_ranged")
    TouchAttackRanged;

    private String value;

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue() { return value; }
}
