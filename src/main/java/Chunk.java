import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Chunk extends Element {

    private boolean isVisible = false;

    public Chunk(Position pos){
        super(pos);
    }
    public Chunk(int x, int y){
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {

        if (isVisible) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#EF8433"));
            graphics.putString(new TerminalPosition(getX(), getY()), "#");
        }
    }

    public boolean getIsVisible() { return isVisible; }
    public void setIsVisible(boolean visible) { isVisible = visible; }

}
