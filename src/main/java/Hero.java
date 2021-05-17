import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;

public class Hero extends Element {

    private int health = 5;
    private int strength = 3;

    public Hero(Position position) { super(position); }
    public Hero(int x, int y) { super(x, y); }

    public int getHealth() { return health; }
    public int getStrength() { return strength; }

    public void decreaseHealth(int hit) { health -= hit; }
    public void increaseHealth(int bonus) { health += bonus; }

    public void attack(Monster monster) {
        monster.decreaseHealth(strength);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()), "@");
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

}
