package roguelike.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public Position getLeft() { return new Position(x - 1, y); }
    public Position getRight() { return new Position(x + 1, y); }
    public Position getUp() { return new Position(x , y - 1); }
    public Position getDown() { return new Position(x, y + 1); }

    public Position follow(Position position) {
        List<Position> possiblePositions = new ArrayList<>();

        int deltaX = position.getX() - x;
        int deltaY = position.getY() - y;

        if (deltaX > 0) {
            possiblePositions.add(new Position(x + 1, y));
            if (deltaY > 0) {
                possiblePositions.add(new Position(x + 1, y + 1));
            } else if (deltaY < 0) {
                possiblePositions.add(new Position(x + 1, y - 1));
            }
        }
        else if (deltaX < 0) {
            possiblePositions.add(new Position(x - 1, y));
            if (deltaY > 0) {
                possiblePositions.add(new Position(x - 1, y + 1));
            } else if (deltaY < 0) {
                possiblePositions.add(new Position(x - 1, y - 1));
            }
        }
        if (deltaY > 0) {
            possiblePositions.add(new Position(x, y + 1));
        } else if (deltaY < 0) {
            possiblePositions.add(new Position(x, y - 1));
        }

        Random random = new Random();
        int randomIndex = random.nextInt(possiblePositions.size());

        return possiblePositions.get(randomIndex);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Position p = (Position) obj;
        return x == p.getX() && y == p.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
