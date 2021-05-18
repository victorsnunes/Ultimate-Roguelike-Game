package roguelike.viewer.game;

import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Chunk;
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
        for (Room room : rooms) {
            if (room.inRoom(getModel().getHero().getPosition())) {
                room.setIsVisible(true);
                room.setIsActive(true);
            } else {
                room.setIsActive(false);
            }
            new RoomViewer(room).drawElements(gui);
        }
    }

    private void drawPaths(GUI gui, List<Path> paths) {
        for (Path path : paths) {
            for (int i = 0; i < path.getChunks().size(); i++) {
                if (path.getChunks().get(i).getPosition()
                        .equals(getModel().getHero().getPosition())) {

                    if (i != 0) path.getChunks().get(i - 1).setIsVisible(true);
                    if (i != path.getChunks().size() - 1) path.getChunks().get(i + 1).setIsVisible(true);
                }
            }
            new PathViewer(path).drawElements(gui);
        }
    }

    private void drawHero(GUI gui, Hero hero, ElementViewer<Hero> viewer) {
        viewer.draw(hero, gui);
    }
}
