package roguelike.viewer.game;

import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Hero;
import roguelike.model.game.elements.Path;
import roguelike.model.game.elements.Room;
import roguelike.viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {

    public GameViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) {

        drawRooms(gui, getModel().getRooms());
        drawPaths(gui, getModel().getPaths());
        drawHero(gui, getModel().getHero(), new HeroViewer());

        gui.drawText(new Position(0, 0), "Health: " + getModel().getHero().getHealth(), "#FFD700");
    }

    private void drawRooms(GUI gui, List<Room> rooms) {
        for (Room room : rooms)
            new RoomViewer(room).drawElements(gui);
    }

    private void drawPaths(GUI gui, List<Path> paths) {
        for (Path path : paths)
            new PathViewer(path).drawElements(gui);
    }

    private void drawHero(GUI gui, Hero hero, ElementViewer<Hero> viewer) {
        viewer.draw(hero, gui);
    }
}
