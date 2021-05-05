import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Path extends Element{

    public Path(Position pos){
        super(pos);
    }
    public Path(int x, int y){
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#EF8433"));
        graphics.putString(new TerminalPosition(getX(), getY()), "#");
    }
}
