package com.example.dndhelper.enums;

import java.util.ArrayList;

public enum Attribute {
    Strength,
    Dexterity,
    Constitution,
    Intelligence,
    Wisdom,
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
