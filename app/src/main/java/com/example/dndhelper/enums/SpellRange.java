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
    @SerializedName("4.5m")
    m4point5,
    @SerializedName("6m")
    m6,
    @SerializedName("1.5kmlvl")
    km1point5
}
