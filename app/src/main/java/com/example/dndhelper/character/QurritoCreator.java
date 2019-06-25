package com.example.dndhelper.character;

import com.example.dndhelper.enums.Class;
import com.example.dndhelper.enums.SpellSchool;

import java.util.Arrays;

public class QurritoCreator {
    public static void createQurrito() {
        // 25 hp
        Character.createNewCharacter("Qurrito", Class.Wizard, SpellSchool.Evocation, Arrays.asList(SpellSchool.Necromancy, SpellSchool.Conjuration), new Attributes(10, 18, 12, 18, 12, 7));
        Character.getInstance().getMoney().addCopper(9409);
        Character.getInstance().getHealth().dealDamage(5);
        Character.getInstance().getSpellbook().setSpellClassLevel(3);
    }
}
