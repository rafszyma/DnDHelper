package com.example.dndhelper.enums;

import com.example.dndhelper.character.Character;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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
        public void levelUpClass(int newLevel) throws Exception {
            if (newLevel >= 19) {
                throw new Exception("does not support those levels yet");
            }

            int initialCharges = 1;
            // TODO check this magic
            int spellLevels = (newLevel + 1) / 2 + 1;
            for (int i = spellLevels; i > 0 ; i--) {
                Character.getInstance().getSpellbook().modifyClassCharges(i, initialCharges);
                if (initialCharges <= 4) {
                    initialCharges++;
                }
            }
        }

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

    private static final int SPELL_LEVELS = 10;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public abstract void levelUpClass(int newLevel) throws Exception;

    public abstract int getHitDice();

    public abstract int getAttributeModificator();

    public abstract boolean isSpellingClass();

    public static ArrayList<String> getClassNames() {
        ArrayList<String> classNames = new ArrayList<>();
        for (Class myClass : Class.values()) {
            classNames.add(myClass.getValue());
        }

        return classNames;
    }
}
