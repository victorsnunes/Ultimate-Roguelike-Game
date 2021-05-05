import java.util.ArrayList;
import java.util.List;

public class Room {
    private int x;
    private int y;
    private int widht;
    private int height;

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

    public List<Wall> getWalls() {
        return walls;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidht() {
        return widht;
    }

    public int getHeight() {
        return height;
    }
}
