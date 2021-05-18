package roguelike.model.game.arena;

import roguelike.model.game.elements.Hero;
import roguelike.model.game.elements.Path;
import roguelike.model.game.elements.Room;

import java.util.List;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        createRooms(arena);
        createPaths(arena);
        createMonsters(arena);
        createCoins(arena);
        createHero(arena);

        return arena;
    }

    protected abstract int getWidth();
    protected abstract int getHeight();

    protected abstract void createRooms(Arena arena);
    protected abstract void createPaths(Arena arena);
    protected abstract void createCoins(Arena arena);
    protected abstract void createMonsters(Arena arena);
    protected abstract void createHero(Arena arena);
}
