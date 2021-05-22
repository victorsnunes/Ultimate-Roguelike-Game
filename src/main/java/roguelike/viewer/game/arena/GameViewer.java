package roguelike.viewer.game.arena;

import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Hero;
import roguelike.model.game.structures.Path;
import roguelike.model.game.structures.Room;
import roguelike.viewer.Viewer;
import roguelike.viewer.game.elements.ElementViewer;
import roguelike.viewer.game.elements.HeroViewer;
import roguelike.viewer.game.structures.PathViewer;
import roguelike.viewer.game.structures.RoomViewer;

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

        gui.drawText(new Position(0, 0), "Health: " + getModel().getHero().getHealth(), "#FF4A4A");
        gui.drawText(new Position(48, 0), "Time: " + getModel().writeTime(), "#FFFFFF");

        if (getModel().getHero().getStrengthBonusTime() > 0) {
            gui.drawText(new Position(38, 29), "Strength Bonus: " + getModel().getHero().writeStrengthTime(), "#E94B6A");
            gui.drawText(new Position(13, 0), "Strength: " + getModel().getHero().getStrength(), "#E94B6A");
        } else {
            getModel().getHero().setStrength(3);
            gui.drawText(new Position(13, 0), "Strength: " + getModel().getHero().getStrength(), "#4163EC");
        }

    }

    private void drawRooms(GUI gui, List<Room> rooms) {
        for (Room room : rooms) {
            room.updateVisibility(getModel().getHero().getPosition());
            new RoomViewer(room).drawElements(gui);
        }
    }

    private void drawPaths(GUI gui, List<Path> paths) {
        for (Path path : paths) {

            //Updates the initial chunk visibility of the room, if the room is visible
            for (Room room : getModel().getRooms()) {
                if (room.getIsVisible()){
                    if (room.inRoom(path.getFirstChunk().getPosition()))
                        path.getFirstChunk().setIsVisible(true);
                    if (room.inRoom(path.getLastChunk().getPosition()))
                        path.getLastChunk().setIsVisible(true);
                }
            }
            //Updates the visibility of the rest of the path
            path.updateVisibility(getModel().getHero().getPosition());

            new PathViewer(path).drawElements(gui);
        }
    }

    private void drawHero(GUI gui, Hero hero, ElementViewer<Hero> viewer) {
        viewer.draw(hero, gui);
    }
}
