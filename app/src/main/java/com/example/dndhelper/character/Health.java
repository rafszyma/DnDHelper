package com.example.dndhelper.character;

import com.example.dndhelper.enums.CharacterState;

public class Health {

    private int currentHitPoints;
    private int contusionDamage;
    private int maxHitPoints;

    public Health(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
        this.currentHitPoints = maxHitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    void levelUp(int newMaxHitPoints) {
        this.maxHitPoints = this.maxHitPoints + newMaxHitPoints;
        this.currentHitPoints = this.currentHitPoints / this.maxHitPoints * newMaxHitPoints + this.currentHitPoints;
    }

    public int getHitPoints() {
        return currentHitPoints;
    }

    public int getContusion() {
        return this.contusionDamage;
    }

    public CharacterState dealDamage(int hit) {
        currentHitPoints = currentHitPoints - hit;
        return Character.getState(currentHitPoints);
    }

    public CharacterState dealContusionDamage(int contusion) {
        int currentHitPoints = this.currentHitPoints - contusionDamage - contusion;

        // You lost hit damage from contusion
        if (currentHitPoints <= 0) {
            this.currentHitPoints = currentHitPoints;
            return Character.getState(this.currentHitPoints);
        }

        // You didn't get HitPoint loss means you have to be healthy
        contusionDamage = contusionDamage + contusion;
        return CharacterState.Healthy;

    }

    public CharacterState healDamage(int heal) {
        currentHitPoints = Math.min(maxHitPoints, currentHitPoints + heal);
        contusionDamage = Math.max(0, contusionDamage - heal * 2);

        return Character.getState(currentHitPoints);
    }
}