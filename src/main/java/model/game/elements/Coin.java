package model.game.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;

public class Coin extends Element {

    public Coin(Position position) { super(position); }
    public Coin(int x, int y) { super(x, y); }

}