package roguelike.model.game.elements;

import roguelike.model.Position;

import java.util.Objects;

public class HorizontalWall extends Wall {
    public HorizontalWall(Position position) { super(position); }
    public HorizontalWall(int x, int y) { super(x, y); }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        HorizontalWall hw = (HorizontalWall) obj;
        return getX() == hw.getX() && getY() == hw.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
