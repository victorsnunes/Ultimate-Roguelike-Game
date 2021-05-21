package roguelike.model.game.elements;

import roguelike.model.Position;

public class Monster extends Element {
    private int health = 6;
    private int strength = 2;

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
}