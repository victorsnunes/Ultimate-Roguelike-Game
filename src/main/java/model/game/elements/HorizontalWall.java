package model.game.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;

public class HorizontalWall extends Wall {
    public HorizontalWall(Position position) { super(position); }
    public HorizontalWall(int x, int y) { super(x, y); }
}
