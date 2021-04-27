import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Wall extends Element {

    public Wall(Position position) { super(position); }
    public Wall(int x, int y) { super(x, y); }

}
