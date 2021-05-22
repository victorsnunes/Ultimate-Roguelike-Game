package roguelike.model.game.elements;

import roguelike.model.Position;

public class Hero extends Element {

    private int health = 5;
    private int strength = 3;
    private int strengthBonusTime = 0;

    public Hero(Position position) { super(position); }
    public Hero(int x, int y) { super(x, y); }

    public int getHealth() { return health; }
    public int getStrength() { return strength; }
    public int getStrengthBonusTime() { return strengthBonusTime; }

    public void setStrength(int strength) { this.strength = strength; }

    public void decreaseHealth(int hit) {
        if (health - hit >= 0)
            health -= hit;
        else
            health = 0;
    }
    public void increaseHealth(int bonus) { health += bonus; }

    public void decreaseStrengthBonusTime() {
        if (strengthBonusTime > 0)
            strengthBonusTime--;
    }

    public void increaseStrength(int bonus) { strength += bonus; }
    public void increaseStrengthBonusTime(int time) { strengthBonusTime += time; }

    public String writeStrengthTime() { return String.format("%d:%02d", strengthBonusTime / 60, strengthBonusTime % 60); }
}
