package roguelike.model.game.elements;

import roguelike.model.Position;

import java.util.Objects;

public class Chunk extends Element {

    private boolean isVisible = false;

    public Chunk(Position pos){
        super(pos);
    }
    public Chunk(int x, int y){
        super(x, y);
    }

    public boolean getIsVisible() { return isVisible; }
    public void setIsVisible(boolean visible) { isVisible = visible; }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Chunk chunk = (Chunk) obj;
        return getX() == chunk.getX() && getY() == chunk.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

}
