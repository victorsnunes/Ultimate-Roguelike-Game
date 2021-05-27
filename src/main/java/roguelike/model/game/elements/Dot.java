package roguelike.model.game.elements;

import roguelike.model.Position;

import java.util.Objects;

public class Dot extends Element {
    public Dot(Position position) { super(position); }
    public Dot(int x, int y) { super(x, y); }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Dot dot = (Dot) obj;
        return getX() == dot.getX() && getY() == dot.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
