package model.game.arena;

import java.util.List;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        arena.setHero(createHero());
        arena.setMonsters(createMonsters());
        arena.setWalls(createWalls());

        return arena;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<com.aor.hero.model.game.elements.Wall> createWalls();

    protected abstract List<com.aor.hero.model.game.elements.Monster> createMonsters();

    protected abstract com.aor.hero.model.game.elements.Hero createHero();
}
