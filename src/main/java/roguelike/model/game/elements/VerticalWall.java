package roguelike.model.game.elements;

import roguelike.model.Position;

public class VerticalWall extends Wall {
    public VerticalWall(Position position) { super(position); }
    public VerticalWall(int x, int y) { super(x, y); }
}
