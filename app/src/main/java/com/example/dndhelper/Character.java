package com.example.dndhelper;

public class Character {
    private Health _health;

    private int _gold;

    private Spellbook _spellbook;

    public Character(int maxHealth) {
        _health = new Health(maxHealth);
        _gold = 0;
        _spellbook = new Spellbook();
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

    private class Health {

        private int _currentHitPoints;
        private int _contusionDamage;
        private int _maxHitPoints;

        public Health(int _maxHitPoints) {
            this._maxHitPoints = _maxHitPoints;
        }

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

    public static Character CreateQurrito() {
        Character qurrito = new Character(20);
        qurrito._gold = 39409;
        qurrito._health._currentHitPoints = 8;
        qurrito._spellbook.SetCharges(new int[] {4,3});

        return qurrito;
    }
}
