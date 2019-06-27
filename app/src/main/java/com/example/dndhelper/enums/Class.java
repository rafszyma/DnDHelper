package com.example.dndhelper.enums;

import com.example.dndhelper.character.Attributes;
import com.example.dndhelper.character.Character;
import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Attr;

import java.util.ArrayList;

public enum Class {
    @SerializedName("bard")
    Bard {
        @Override
        public int[] generateSpellChargesList(int newLevel, Attributes attr) {
            return new int[0];
        }

        @Override
        public int getHitDice() {
            return 6;
        }

        @Override
        public int getAttributeModificator(Attributes attr) {
            return attr.getCharismaModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },

    @SerializedName("cleric")
    Cleric {
        @Override
        public int[] generateSpellChargesList(int newLevel, Attributes attr) {
            return new int[0];
        }

        @Override
        public int getHitDice() {
            return 8;
        }

        @Override
        public int getAttributeModificator(Attributes attr) {
            return attr.getWisdomModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },
    @SerializedName("druid")
    Druid {
        @Override
        public int[] generateSpellChargesList(int newLevel, Attributes attr) {
            return new int[0];
        }

        @Override
        public int getHitDice() {
            return 8;
        }

        @Override
        public int getAttributeModificator(Attributes attr) {
            return attr.getWisdomModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },
    @SerializedName("paladin")
    Paladin {
        @Override
        public int[] generateSpellChargesList(int newLevel, Attributes attr) {
            int[][]spellMatrix = new int[][] {
                    {0, 0, 0, 0, 0 },
                    {0, 0, 0, 0, 0 },
                    {0, 0, 0, 0, 0 },
                    {0, 0, 0, 0, 0 },
                    {0, 0, 0, 0, 0 },
                    {0, 1, 0, 0, 0 },
                    {0, 1, 0, 0, 0 },
                    {0, 1, 0, 0, 0 },
                    {0, 1, 0, 0, 0 },
                    {0, 1, 1, 0, 0 },
                    {0, 1, 1, 0, 0 },
                    {0, 1, 1, 1, 0 },
                    {0, 1, 1, 1, 0 },
                    {0, 2, 1, 1, 0 },
                    {0, 2, 1, 1, 1 },
                    {0, 2, 2, 1, 1 },
                    {0, 2, 2, 2, 1 },
                    {0, 3, 2, 2, 1 },
                    {0, 3, 3, 3, 2 },
                    {0, 3, 3, 3, 3 }
            };

            int[] spellCharges = spellMatrix[newLevel];
            for (int i = 1 ; i < SPELL_LEVELS; i++) {
                if (spellCharges[i] > 0) {
                    spellCharges[i] = spellCharges[i] + Attributes.getExtraChargesForSpellLevel(i, getAttributeModificator(attr));
                } else {
                    break;
                }
            }

            return spellCharges;
        }

        @Override
        public int getHitDice() {
            return 10;
        }

        @Override
        public int getAttributeModificator(Attributes attr) {
            return attr.getCharismaModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },
    @SerializedName("sorcerer")
    Sorcerer {
        @Override
        public int[] generateSpellChargesList(int newLevel, Attributes attr) {
            int[][]spellMatrix = new int[][] {
                    {5, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 4, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 5, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 6, 3, 0, 0, 0, 0, 0, 0, 0},
                    {6, 6, 4, 0, 0, 0, 0, 0, 0, 0},
                    {6, 6, 5, 3, 0, 0, 0, 0, 0, 0},
                    {6, 6, 6, 4, 0, 0, 0, 0, 0, 0},
                    {6, 6, 6, 5, 3, 0, 0, 0, 0, 0},
                    {6, 6, 6, 6, 4, 0, 0, 0, 0, 0},
                    {6, 6, 6, 6, 5, 3, 0, 0, 0, 0},
                    {6, 6, 6, 6, 6, 4, 0, 0, 0, 0},
                    {6, 6, 6, 6, 6, 5, 3, 0, 0, 0},
                    {6, 6, 6, 6, 6, 6, 4, 0, 0, 0},
                    {6, 6, 6, 6, 6, 6, 5, 3, 0, 0},
                    {6, 6, 6, 6, 6, 6, 6, 4, 0, 0},
                    {6, 6, 6, 6, 6, 6, 6, 5, 3, 0},
                    {6, 6, 6, 6, 6, 6, 6, 6, 4, 0},
                    {6, 6, 6, 6, 6, 6, 6, 6, 5, 3},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 4},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6}
            };

            int[] spellCharges = spellMatrix[newLevel];
            for (int i = 1 ; i < SPELL_LEVELS; i++) {
                if (spellCharges[i] > 0) {
                    spellCharges[i] = spellCharges[i] + Attributes.getExtraChargesForSpellLevel(i, getAttributeModificator(attr));
                } else {
                    break;
                }
            }

            return spellCharges;
        }

        @Override
        public int getHitDice() {
            return 4;
        }

        @Override
        public int getAttributeModificator(Attributes attr) {
            return attr.getCharismaModifier();
        }

        @Override
        public boolean isSpellingClass() {
            return true;
        }
    },
    @SerializedName("wizard")
    Wizard {
        @Override
        public int[] generateSpellChargesList(int newLevel, Attributes attr) {
            newLevel--;
            int[][]spellMatrix = new int[][] {
                    {3, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {4, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                    {4, 2, 1, 0, 0, 0, 0, 0, 0, 0},
                    {4, 3, 2, 0, 0, 0, 0, 0, 0, 0},
                    {4, 3, 2, 1, 0, 0, 0, 0, 0, 0},
                    {4, 3, 3, 2, 0, 0, 0, 0, 0, 0},
                    {4, 4, 3, 2, 1, 0, 0, 0, 0, 0},
                    {4, 4, 3, 3, 2, 0, 0, 0, 0, 0},
                    {4, 4, 4, 3, 2, 1, 0, 0, 0, 0},
                    {4, 4, 4, 3, 3, 2, 0, 0, 0, 0},
                    {4, 4, 4, 4, 3, 2, 1, 0, 0, 0},
                    {4, 4, 4, 4, 3, 3, 2, 0, 0, 0},
                    {4, 4, 4, 4, 4, 3, 2, 1, 0, 0},
                    {4, 4, 4, 4, 4, 3, 3, 2, 0, 0},
                    {4, 4, 4, 4, 4, 4, 3, 2, 1, 0},
                    {4, 4, 4, 4, 4, 4, 3, 3, 2, 0},
                    {4, 4, 4, 4, 4, 4, 4, 3, 2, 1},
                    {4, 4, 4, 4, 4, 4, 4, 3, 3, 2},
                    {4, 4, 4, 4, 4, 4, 4, 4, 3, 3},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4},

            };

            int[] spellCharges = spellMatrix[newLevel];
            for (int i = 1 ; i < SPELL_LEVELS; i++) {
                if (spellCharges[i] > 0) {
                    spellCharges[i] = spellCharges[i] + Attributes.getExtraChargesForSpellLevel(i, getAttributeModificator(attr));
                } else {
                    break;
                }
            }

            return spellCharges;
        }

        @Override
        public int getHitDice() {
            return 4;
        }

        @Override
        public int getAttributeModificator(Attributes attr) {
            return attr.getIntelligenceModifier();
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

    public abstract int[] generateSpellChargesList(int newLevel, Attributes attr);

    public abstract int getHitDice();

    public abstract int getAttributeModificator(Attributes attr);

    public abstract boolean isSpellingClass();

    public static ArrayList<String> getClassNames() {
        ArrayList<String> classNames = new ArrayList<>();
        for (Class myClass : Class.values()) {
            classNames.add(myClass.getValue());
        }

        return classNames;
    }
}
