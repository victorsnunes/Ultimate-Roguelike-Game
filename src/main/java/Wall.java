import model.Position;

public abstract class Wall extends Element {

    public Wall(Position position) { super(position); }
    public Wall(int x, int y) { super(x, y); }

}
