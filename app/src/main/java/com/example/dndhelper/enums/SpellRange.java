package com.example.dndhelper.enums;

import com.google.gson.annotations.SerializedName;

public enum SpellRange {
    @SerializedName("close")
    Close,
    @SerializedName("medium")
    Medium,
    @SerializedName("far")
    Far,
    @SerializedName("touch")
    Touch,
    @SerializedName("self")
    Self,
    @SerializedName("18m")
    m18,
    @SerializedName("3m")
    m3,
    @SerializedName("m4.5")
    m4point5
}
