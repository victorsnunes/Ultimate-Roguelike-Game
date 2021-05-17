package model.game.arena;

import java.util.List;

public class Arena {
    private final int width;
    private final int height;

    private com.aor.hero.model.game.elements.Hero hero;

    private List<com.aor.hero.model.game.elements.Monster> monsters;
    private List<com.aor.hero.model.game.elements.Wall> walls;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public com.aor.hero.model.game.elements.Hero getHero() {
        return hero;
    }

    public void setHero(com.aor.hero.model.game.elements.Hero hero) {
        this.hero = hero;
    }

    public List<com.aor.hero.model.game.elements.Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<com.aor.hero.model.game.elements.Monster> monsters) {
        this.monsters = monsters;
    }

    public List<com.aor.hero.model.game.elements.Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<com.aor.hero.model.game.elements.Wall> walls) {
        this.walls = walls;
    }

    public boolean isEmpty(Position position) {
        for (com.aor.hero.model.game.elements.Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    public boolean isMonster(Position position) {
        for (com.aor.hero.model.game.elements.Monster monster : monsters)
            if (monster.getPosition().equals(position))
                return true;
        return false;
    }
}
