package com.example.dndhelper.enums;

import com.example.dndhelper.character.Character;
import com.google.gson.annotations.SerializedName;

public enum Class {
    @SerializedName("bard")
    Bard {
        @Override
        public int getHitDice() {
            return 6;
        }

        @Override
        public int getAttributeModificator() {
            return Character.getInstance().getAttributes().getCharismaModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },

    @SerializedName("cleric")
    Cleric {
        @Override
        public int getHitDice() {
            return 8;
        }

        @Override
        public int getAttributeModificator() {
            return Character.getInstance().getAttributes().getWisdomModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },
    @SerializedName("druid")
    Druid {
        @Override
        public int getHitDice() {
            return 8;
        }

        @Override
        public int getAttributeModificator() {
            return Character.getInstance().getAttributes().getWisdomModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },
    @SerializedName("paladin")
    Paladin {
        @Override
        public int getHitDice() {
            return 10;
        }

        @Override
        public int getAttributeModificator() {
            return Character.getInstance().getAttributes().getCharismaModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },
    @SerializedName("sorcerer")
    Sorcerer {
        @Override
        public int getHitDice() {
            return 4;
        }

        @Override
        public int getAttributeModificator() {
            return Character.getInstance().getAttributes().getCharismaModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },
    @SerializedName("wizard")
    Wizard {
        @Override
        public int getHitDice() {
            return 4;
        }

        @Override
        public int getAttributeModificator() {
            return Character.getInstance().getAttributes().getIntelligenceModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
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

    public abstract int getAttributeModificator();

    public abstract boolean isSpellingClass();
}
