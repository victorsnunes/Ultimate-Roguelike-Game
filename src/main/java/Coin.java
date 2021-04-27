import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element {

    public Coin(Position position) { super(position); }
    public Coin(int x, int y) { super(x, y); }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#E94B6A"));
        graphics.putString(new TerminalPosition(getX(), getY()), "O");
    }

}
