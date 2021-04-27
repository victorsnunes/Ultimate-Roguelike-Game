import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{

    public Monster(Position position) { super(position); }
    public Monster(int x, int y){ super(x, y); }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFEE66"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()), "M");
    }

}
