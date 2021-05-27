package roguelike.model.game.elements;

import roguelike.model.Position;

import java.util.Objects;

public class VerticalWall extends Wall {
    public VerticalWall(Position position) { super(position); }
    public VerticalWall(int x, int y) { super(x, y); }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        VerticalWall hv = (VerticalWall) obj;
        return getX() == hv.getX() && getY() == hv.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
