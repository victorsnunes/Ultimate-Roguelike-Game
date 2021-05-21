package roguelike.model.game.elements;

import roguelike.model.Position;

public class Hero extends Element {

    private int health = 5;
    private int strength = 3;

    public Hero(Position position) { super(position); }
    public Hero(int x, int y) { super(x, y); }

    public int getHealth() { return health; }
    public int getStrength() { return strength; }

    public void decreaseHealth(int hit) {
        if (health - hit >= 0)
            health -= hit;
        else
            health = 0;
    }
    public void increaseHealth(int bonus) { health += bonus; }

}
