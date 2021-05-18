package roguelike.states;

import roguelike.controller.Controller;
import roguelike.controller.game.ArenaController;
import roguelike.model.game.arena.Arena;
import roguelike.viewer.Viewer;
import roguelike.viewer.game.arena.GameViewer;

public class GameState extends State<Arena> {
    public GameState(Arena arena) {
        super(arena);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }
}
