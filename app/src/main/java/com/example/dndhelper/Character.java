package com.example.dndhelper;

import java.util.ArrayList;
import java.util.List;


public class Character {
    private Health _health;

    private int _gold;

    private List<Spell> _activeSpells;

    private List<Spell> _learnedSpells;

    private Spell _specialSpell;

    public Character(int maxHealth) {
        _health = new Health(maxHealth);
        _gold = 0;
        _activeSpells = new ArrayList<>();
        _learnedSpells = new ArrayList<>();
    }

    private class Health {

        public Health(int _maxHitPoints) {
            this._maxHitPoints = _maxHitPoints;
        }

        private int _currentHitPoints;

        private int _contusionDamage;

        private int _maxHitPoints;

        public int GetHitPoints() {
            return _currentHitPoints;
        }

        public int GetContusion() {
            return this._contusionDamage;
        }

        public CharacterState GetHit(int hit) {
            _currentHitPoints = _currentHitPoints - hit;
            return GetState(_currentHitPoints);
        }

        public CharacterState GetContusionDmg(int contusion) {
            int currentHitPoints = _currentHitPoints - _contusionDamage - contusion;

            // You lost hit damage from contusion
            if (currentHitPoints <= 0) {
                _currentHitPoints = currentHitPoints;
                return GetState(_currentHitPoints);
            }

            // You didn't get HitPoint loss means you have to be healthy
            _contusionDamage = _contusionDamage + contusion;
            return CharacterState.Healthy;

        }

        public CharacterState HealDamage(int heal) {
            _currentHitPoints = Math.min(_maxHitPoints, _currentHitPoints + heal);
            _contusionDamage = Math.max(0, _contusionDamage + heal * 2);

            return GetState(_currentHitPoints);
        }
    }


    public CharacterState GetState(int hp) {
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
}
