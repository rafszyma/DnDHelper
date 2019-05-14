package com.example.dndhelper.enums;

import com.google.gson.annotations.SerializedName;

public enum SpellComponent {
    @SerializedName("verbal")
    Verbal,
    @SerializedName("somatic")
    Somatic,
    @SerializedName("material")
    Material,
    @SerializedName("focus")
    Focus
}
