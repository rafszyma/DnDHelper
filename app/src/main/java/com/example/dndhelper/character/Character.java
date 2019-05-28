package com.example.dndhelper.character;

import com.example.dndhelper.enums.CharacterState;
import com.example.dndhelper.enums.Class;
import com.example.dndhelper.enums.SpellSchool;
import com.example.dndhelper.spells.Spellbook;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

public class Character {
    private static Character ourInstance;
    private String characterName;
    private CharacterClasses classes;
    private Health health;
    private Money money;
    private Spellbook spellbook;
    private Attributes attributes;
    private Character(String name, Class characterClass, SpellSchool extraSpellSchool, List<SpellSchool> forbiddenedSchools, Attributes attr) {
        classes = new CharacterClasses(characterClass);
        characterName = name;
        health = new Health(characterClass.getHitDice() + attr.getConstitutionModifier());
        money = new Money(0);
        spellbook = new Spellbook(1, extraSpellSchool, forbiddenedSchools);
        attributes = attr;
    }

    public CharacterClasses getClasses() {
        return classes;
    }

    public static Character getInstance() {
        return ourInstance;
    }

    public static void initialize(File readFrom) {
        loadCharacter(readFrom);
    }

    public static void createNewCharacter(String name, Class characterClass, SpellSchool extraSpellSchool, List<SpellSchool> forbiddenedSchools, Attributes attr) {
        ourInstance = new Character(name, characterClass, extraSpellSchool, forbiddenedSchools, attr);
    }

    public static CharacterState getState(int hp) {
        if (hp < -10) {
            return CharacterState.Dead;
        }

        if (hp < 0) {
            return CharacterState.Unconscious;
        }

        if (hp == 0) {
            return CharacterState.Semiconscious;
        }

        return CharacterState.Healthy;
    }

    private static void loadCharacter(File characterFile) {
        try {
            if (characterFile != null && characterFile.exists()) {
                StringBuilder text = new StringBuilder();

                BufferedReader reader = new BufferedReader(new FileReader(characterFile));
                String line;

                while ((line = reader.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                reader.close();

                Character character = new Gson().fromJson(text.toString(), com.example.dndhelper.character.Character.class);
                if (character.getHealth() != null || character.getMoney() != null || character.getSpellbook() != null) {
                    ourInstance = character;
                    return;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        QurritoCreator.createQurrito();
    }

    public Class getSpellClass() {
        return classes.getSpellingClass();
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public String getCharacterFileName() {
        return String.format("%s_character", characterName);
    }

    public String getCharacterName() {
        return characterName;
    }

    public Health getHealth() {
        return health;
    }

    public Money getMoney() {
        return money;
    }

    public Spellbook getSpellbook() {
        return spellbook;
    }

    public void saveCharacter(FileOutputStream outputStream) {
        try {
            outputStream.write(new Gson().toJson(this).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void levelUp(int hitDice, Class classToLevel) {
        this.health.levelUp(hitDice + this.attributes.getConstitutionModifier());
        int newClassLevel = this.classes.levelUpClass(classToLevel);
        this.getSpellbook().modifyClassCharges(classToLevel.generateSpellChargesList(newClassLevel));
    }
}
