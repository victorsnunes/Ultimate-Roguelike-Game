package model.game.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;

public class Hero extends Element {

    private int health = 5;
    private int strength = 3;

    public Hero(Position position) { super(position); }
    public Hero(int x, int y) { super(x, y); }

    public int getHealth() { return health; }
    public int getStrength() { return strength; }

    public void decreaseHealth(int hit) { health -= hit; }
    public void increaseHealth(int bonus) { health += bonus; }

}
