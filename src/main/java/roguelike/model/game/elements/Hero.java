package roguelike.model.game.elements;

import roguelike.model.Position;

public class Hero extends Mob {

    public Hero(Position position) {
        super(position);
        this.health = 5;
        this.initialStrength = 3;
        this.strength = 3;
    }
    public Hero(int x, int y) {
        super(x, y);
        this.health = 5;
        this.initialStrength = 3;
        this.strength = 3;
    }
    public Hero(Position position, int health, int strength) {
        super(position, health, strength);
    }

    public String writeStrengthTime() {
        return String.format("%d:%02d", strengthBonusTime / 60, strengthBonusTime % 60);
    }
}
