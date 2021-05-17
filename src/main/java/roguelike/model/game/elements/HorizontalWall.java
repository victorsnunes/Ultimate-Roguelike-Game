package roguelike.model.game.elements;

import roguelike.model.Position;

public class HorizontalWall extends Wall {
    public HorizontalWall(Position position) { super(position); }
    public HorizontalWall(int x, int y) { super(x, y); }
}
