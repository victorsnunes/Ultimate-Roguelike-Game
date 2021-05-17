package model.game.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;

public class VerticalWall extends Wall {
    public VerticalWall(Position position) { super(position); }
    public VerticalWall(int x, int y) { super(x, y); }
}
