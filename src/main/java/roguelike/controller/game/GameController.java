package roguelike.controller.game;

import roguelike.controller.Controller;
import roguelike.model.game.arena.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }
}
