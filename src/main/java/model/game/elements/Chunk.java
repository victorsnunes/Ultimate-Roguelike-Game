package model.game.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;

public class Chunk extends Element {

    private boolean isVisible = false;

    public Chunk(Position pos){
        super(pos);
    }
    public Chunk(int x, int y){
        super(x, y);
    }

    public boolean getIsVisible() { return isVisible; }
    public void setIsVisible(boolean visible) { isVisible = visible; }

}
