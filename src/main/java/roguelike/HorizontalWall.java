package roguelike;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import roguelike.model.Position;

public class HorizontalWall extends Wall {

    public HorizontalWall(Position position) { super(position); }
    public HorizontalWall(int x, int y) { super(x, y); }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#EF8433"));
        graphics.putString(new TerminalPosition(getX(), getY()), "-");
    }
}
