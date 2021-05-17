package viewer.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Position;
import model.game.elements.*;
import viewer.Viewer;

import java.util.List;

public class RoomViewer extends Viewer<Room> {

    public RoomViewer(Room room) { super(room); }

    @Override
    public void drawElements(GUI gui) {

        if (getModel().getIsVisible()) {
            drawElements(gui, getModel().getWalls(), new WallViewer());
            if (getModel().getIsActive()) {
                drawElements(gui, getModel().getCoins(), new CoinViewer());
                drawElements(gui, getModel().getMonsters(), new MonsterViewer());
                drawElements(gui, getModel().getDots(), new DotViewer());
            }
        }
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            viewer.draw(element, gui);
    }
}
