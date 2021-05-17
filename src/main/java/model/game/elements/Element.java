package model.game.elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;

public class Element {

    protected Position position;

    public Element(Position position) { this.position = position; }
    public Element(int x, int y) { this.position = new Position(x, y); }

    public int getX() { return position.getX(); }
    public int getY() { return position.getY(); }

    public void setX(int x) { position.setX(x); }
    public void setY(int y) { position.setY(y); }

    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }

}
