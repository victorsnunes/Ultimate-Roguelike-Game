package viewer.game;

import gui.GUI;
import model.game.elements.HorizontalWall;
import model.game.elements.VerticalWall;
import model.game.elements.Wall;

public class WallViewer implements ElementViewer<Wall> {
    @Override
    public void draw(Wall wall, GUI gui) {

        //TODO: Can we remove this smell code ?
        if (wall instanceof VerticalWall)
            gui.drawVerticalWall(wall.getPosition());
        else if (wall instanceof HorizontalWall)
            gui.drawVerticalWall(wall.getPosition());
    }
}