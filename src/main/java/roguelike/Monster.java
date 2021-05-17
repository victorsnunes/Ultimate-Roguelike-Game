package roguelike;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import roguelike.model.Position;

public class Monster extends Element {

    private int health = 6;
    private int strength = 1;

    public Monster(Position position) { super(position); }
    public Monster(int x, int y){ super(x, y); }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFEE66"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()), "M");
    }

    public void decreaseHealth(int hit) { health -= hit; }

    public int getHealth() { return health; }
    public int getStrength() { return strength; }
}
