package com.example.dndhelper.enums;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public enum Attribute {
    @SerializedName("strength")
    Strength,
    @SerializedName("dexterity")
    Dexterity,
    @SerializedName("constitution")
    Constitution,
    @SerializedName("intelligence")
    Intelligence,
    @SerializedName("wisdom")
    Wisdom,
    @SerializedName("charisma")
    Charisma;

    public static ArrayList<String> getAttributeNames() {
        ArrayList<String> attributeNames = new ArrayList<>();
        for (Attribute myAttr : Attribute.values()) {
            attributeNames.add(myAttr.getValue());
        }

        return attributeNames;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
