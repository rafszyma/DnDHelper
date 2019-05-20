package com.example.dndhelper.character;

import com.example.dndhelper.enums.Class;

import java.util.HashMap;

public class CharacterClasses {
    private HashMap<Class, Integer> classMap;

    private Class spellingClass;

    public CharacterClasses(Class characterClass) {
        this.classMap = new HashMap<>();
        classMap.put(characterClass, 1);
        if (characterClass.isSpellingClass()) {
            spellingClass = characterClass;
        }
    }

    public void levelUpClass(Class classToLevelUp) {
        if (classToLevelUp.isSpellingClass()) {
            spellingClass = classToLevelUp;
        }

        if (classMap.containsKey(classToLevelUp)) {
            Integer currentLevel = classMap.get(classToLevelUp);
            currentLevel++;
            classMap.put(classToLevelUp, currentLevel);
            return;
        }

        classMap.put(classToLevelUp, 1);
    }

    public int getLevelForClass(Class characterClass) {
        if (classMap.containsKey(characterClass)) {
            return classMap.get(characterClass);
        }

        return -1;
    }

    public Class getSpellingClass() {
        return spellingClass;
    }
}
