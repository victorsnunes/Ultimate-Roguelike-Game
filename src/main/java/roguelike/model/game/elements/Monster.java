package roguelike.model.game.elements;

import roguelike.model.Position;

public class Monster extends Mob {

    private boolean tookDamage = false;
    private long tookDamageTime = 0;

    public Monster(Position position) {
        super(position);
        this.health = 9;
        this.initialStrength = 2;
        this.strength = this.initialStrength;
    }
    public Monster(int x, int y) {
        super(x, y);
        this.health = 9;
        this.initialStrength = 2;
        this.strength = 2;
    }
    public Monster(Position position, int health, int strength) { super(position, health, strength); }

    public boolean getTookDamage() { return tookDamage; }
    public long getTookDamageTime() { return tookDamageTime; }

    public void setTookDamage(boolean tookDamage) { this.tookDamage = tookDamage; }
    public void setTookDamageTime(long tookDamageTime) { this.tookDamageTime = tookDamageTime; }
}