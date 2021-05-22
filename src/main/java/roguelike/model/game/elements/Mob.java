package roguelike.model.game.elements;

import roguelike.model.Position;

public abstract class Mob extends Element {

    protected int health;
    protected int strength;
    protected int initialStrength;
    protected int strengthBonusTime = 0;

    public Mob(Position position) { super(position); }
    public Mob(int x, int y) { super(x, y); }
    public Mob(Position position, int health, int strength) {
        super(position);
        this.health = health;
        this.initialStrength = strength;
        this.strength = strength;
    }

    public int getHealth() { return health; }
    public int getStrength() { return strength; }
    public int getInitialStrength() { return initialStrength; }
    public int getStrengthBonusTime() { return strengthBonusTime; }

    public void setStrength(int strength) { this.strength = strength; }

    public void increaseHealth(int bonus) { health += bonus; }
    public void increaseStrength(int bonus) { strength += bonus; }
    public void increaseStrengthBonusTime(int time) { strengthBonusTime += time; }

    public void decreaseHealth(int hit) {
        if (health - hit >= 0)
            health -= hit;
        else
            health = 0;
    }
    public void decreaseStrengthBonusTime() {
        if (strengthBonusTime > 0)
            strengthBonusTime--;
    }
}
