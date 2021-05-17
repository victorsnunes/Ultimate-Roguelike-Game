package model.game.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;

public class Monster extends Element {
    private int health = 6;
    private int strength = 1;

    public Monster(Position position) { super(position); }
    public Monster(int x, int y){ super(x, y); }

    public void decreaseHealth(int hit) { health -= hit; }

    public int getHealth() { return health; }
    public int getStrength() { return strength; }
}