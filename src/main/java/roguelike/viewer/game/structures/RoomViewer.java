package roguelike.viewer.game.structures;

import roguelike.gui.GUI;
import roguelike.model.game.elements.*;
import roguelike.model.game.structures.Room;
import roguelike.viewer.Viewer;
import roguelike.viewer.game.elements.*;

import java.util.List;

public class RoomViewer extends Viewer<Room> {

    public RoomViewer(Room room) { super(room); }

    @Override
    public void drawElements(GUI gui) {

        if (getModel().getIsVisible()) {
            drawElements(gui, getModel().getWalls(), new WallViewer());
            if (getModel().getIsActive()) {
                drawElements(gui, getModel().getDots(), new DotViewer());
                drawElements(gui, getModel().getCoins(), new CoinViewer());
                drawElements(gui, getModel().getStrengthPotions(), new StrengthPotionViewer());
                if (getModel().getHasGoal()) drawElement(gui, getModel().getGoal(), new GoalViewer());
                drawElements(gui, getModel().getMonsters(), new MonsterViewer());
            }
        }
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            viewer.draw(element, gui);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}
