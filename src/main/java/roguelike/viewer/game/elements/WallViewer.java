package roguelike.viewer.game.elements;

import roguelike.gui.GUI;
import roguelike.model.game.elements.HorizontalWall;
import roguelike.model.game.elements.VerticalWall;
import roguelike.model.game.elements.Wall;

public class WallViewer implements ElementViewer<Wall> {
    @Override
    public void draw(Wall wall, GUI gui) {

        //TODO: Can we remove this smell code ?
        if (wall instanceof VerticalWall)
            gui.drawVerticalWall(wall.getPosition());
        else if (wall instanceof HorizontalWall)
            gui.drawHorizontalWall(wall.getPosition());
    }
}