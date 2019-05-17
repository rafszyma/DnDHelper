package com.example.dndhelper.enums;

import com.google.gson.annotations.SerializedName;

public enum Class {
    @SerializedName("bard")
    Bard {
        @Override
        public int getHitDice() {
            return 6;
        }
    },

    @SerializedName("cleric")
    Cleric {
        @Override
        public int getHitDice() {
            return 8;
        }
    },
    @SerializedName("druid")
    Druid {
        @Override
        public int getHitDice() {
            return 8;
        }
    },
    @SerializedName("paladin")
    Paladin {
        @Override
        public int getHitDice() {
            return 10;
        }
    },
    @SerializedName("sorcerer")
    Sorcerer {
        @Override
        public int getHitDice() {
            return 4;
        }
    },
    @SerializedName("wizard")
    Wizard {
        @Override
        public int getHitDice() {
            return 4;
        }
    };

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public abstract int getHitDice();
}
