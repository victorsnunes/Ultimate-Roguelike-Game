package model.game.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomArenaBuilder extends ArenaBuilder {
    private final Random rng;

    private final int width;
    private final int height;
    private final int numberOfMonsters;

    public RandomArenaBuilder(int width, int height, int numberOfMonsters) {
        this.rng = new Random();

        this.width = width;
        this.height = height;
        this.numberOfMonsters = numberOfMonsters;
    }

    @Override
    protected int getWidth() {
        return width;
    }

    @Override
    protected int getHeight() {
        return height;
    }

    @Override
    protected List<com.aor.hero.model.game.elements.Wall> createWalls() {
        List<com.aor.hero.model.game.elements.Wall> walls = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            walls.add(new com.aor.hero.model.game.elements.Wall(x, 0));
            walls.add(new com.aor.hero.model.game.elements.Wall(x, height - 1));
        }

        for (int y = 1; y < height - 1; y++) {
            walls.add(new com.aor.hero.model.game.elements.Wall(0, y));
            walls.add(new com.aor.hero.model.game.elements.Wall(width - 1, y));
        }

        return walls;
    }

    @Override
    protected List<com.aor.hero.model.game.elements.Monster> createMonsters() {
        List<com.aor.hero.model.game.elements.Monster> monsters = new ArrayList<>();

        while (monsters.size() < numberOfMonsters)
            monsters.add(new com.aor.hero.model.game.elements.Monster(rng.nextInt(width - 2) + 1, rng.nextInt(height - 2) + 1));

        return monsters;
    }

    @Override
    protected com.aor.hero.model.game.elements.Hero createHero() {
        return new com.aor.hero.model.game.elements.Hero(width / 2, height / 2);
    }
}
