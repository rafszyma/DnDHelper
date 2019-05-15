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
    Focus;

    private String value;

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue() { return value; }
}
