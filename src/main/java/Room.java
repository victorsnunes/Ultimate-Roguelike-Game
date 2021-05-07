import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int x;
    private int y;
    private int widht;
    private int height;

    private boolean isVisible = false;
    private boolean isActive = false;

    private List<Wall> walls = new ArrayList<>();

    public Room(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.widht = width;
        this.height = height;

        for (int i = x; i <= x + width; i++) {
            walls.add(new HorizontalWall(i, y));
            walls.add(new HorizontalWall(i, y + height));
        }

        for (int j = y + 1; j < y + height; j++) {
            walls.add(new VerticalWall(x, j));
            walls.add(new VerticalWall(x + width, j));
        }
    }

    public List<Wall> getWalls() { return walls; }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getWidht() { return widht; }
    public int getHeight() { return height; }

    public void setActive(boolean active) { isActive = active; }

    public void draw(TextGraphics graphics) {

        if (isVisible) {
            for (Wall wall : walls)
                wall.draw(graphics);

            if (isActive) {
                for (int i = x + 1; i < x + widht; i++) {
                    for (int j = y + 1; j < y + height; j++){
                        graphics.setForegroundColor(TextColor.Factory.fromString("#EF8433"));
                        graphics.putString(new TerminalPosition(i, j), ".");
                    }
                }
            }
        }
    }

    public boolean inRoom(Position position) {
        boolean InX = (position.getX() >= x) && (position.getX() <= (x + widht));
        boolean InY = (position.getY() >= y) && (position.getY() <= (y + height));

        return InX && InY;
    }

    public void checkIsActive(Position heroPosition) {
        if (inRoom(heroPosition)) {
            isVisible = true;
            isActive = true;
        }
        else
            isActive = false;
    }
}
