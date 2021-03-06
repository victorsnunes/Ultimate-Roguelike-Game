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
    public void setStrengthBonusTime(int strengthBonusTime) {
        this.strengthBonusTime = strengthBonusTime;
    }

    public void increaseHealth(int bonus) { if (bonus > 0) health += bonus; }
    public void increaseStrength(int bonus) { if (bonus > 0) strength += bonus; }
    public void increaseStrengthBonusTime(int time) { if (time > 0) strengthBonusTime += time; }

    public void decreaseHealth(int hit) {
        if (hit < 0) return;
        if (health - hit >= 0)
            health -= hit;
        else
            health = 0;
    }
    public void decreaseStrengthBonusTime() {
        if (strengthBonusTime > 0)
            strengthBonusTime--;
        else
            strengthBonusTime = 0;
    }
}
