import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {

    protected Position position;

    public Element(Position position) {
        this.position = position;
    }
    public Element(int x, int y) { this.position = new Position(x, y); }

    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public Position getPosition() { return position; }

    public void setX(int x) {
        position.setX(x);
    }
    public void setY(int y) {
        position.setY(y);
    }

    public void setPosition(Position position) { this.position = position; }

    public abstract void draw(TextGraphics graphics);
}
