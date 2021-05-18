package roguelike.model.game.elements;

import roguelike.model.Position;

public class Coin extends Element {

    int bonus = 1;

    public Coin(Position position) { super(position); }
    public Coin(int x, int y) { super(x, y); }
    public Coin(Position position, int bonus) {
        super(position);
        this.bonus = bonus;
    }

    public int getBonus() { return bonus; }
    public void setBonus(int bonus) { this.bonus = bonus; }
}