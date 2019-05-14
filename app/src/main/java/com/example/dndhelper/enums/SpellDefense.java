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
    TouchAttackRanged

}
