package roguelike.model.game.elements;

import roguelike.model.Position;

public class Monster extends Element {
    private int health = 9;
    private int strength = 2;

    private boolean tookDamage = false;
    private long tookDamageTime = 0;

    public Monster(Position position) { super(position); }
    public Monster(int x, int y){ super(x, y); }
    public Monster(Position position, int health, int strength) {
        super(position);
        this.health = health;
        this.strength = strength;
    }

    public void decreaseHealth(int hit) { health -= hit; }

    public int getHealth() { return health; }
    public int getStrength() { return strength; }

    public boolean getTookDamage() { return tookDamage; }
    public long getTookDamageTime() { return tookDamageTime; }

    public void setHealth(int health) { this.health = health; }
    public void setStrength(int strength) { this.strength = strength; }

    public void setTookDamage(boolean tookDamage) { this.tookDamage = tookDamage; }
    public void setTookDamageTime(long tookDamageTime) { this.tookDamageTime = tookDamageTime; }
}