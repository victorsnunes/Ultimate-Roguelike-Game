package roguelike.model.game.elements;

import roguelike.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int x;
    private int y;
    private int widht;
    private int height;

    private boolean isVisible = false;
    private boolean isActive = false;

    private List<Wall> walls = new ArrayList<>();

    private List<Path> paths = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();
    private List<Chunk> initialChunks = new ArrayList<>();
    private List<Dot> dots = new ArrayList<>();

    public Room(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.widht = width;
        this.height = height;

        for (int i = x; i <= x + width; i++) {
            walls.add(new HorizontalWall(i, y));
            walls.add(new HorizontalWall(i, y + height));
        }

        for (int j = y + 1; j < y + height; j++) {
            walls.add(new VerticalWall(x, j));
            walls.add(new VerticalWall(x + width, j));
        }

        for (int i = x + 1; i < x + widht; i++) {
            for (int j = y + 1; j < y + height; j++){
                dots.add(new Dot(i, j));
            }
        }
    }

    public List<Wall> getWalls() { return walls; }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getWidht() { return widht; }
    public int getHeight() { return height; }

    public void setIsActive(boolean active) { isActive = active; }
    public boolean getIsActive() { return isActive; }
    public void setIsVisible(boolean visible) { isVisible = visible; }
    public boolean getIsVisible() { return isVisible; }

    public List<Monster> getMonsters() { return monsters; }
    public List<Coin> getCoins() { return coins; }
    public List<Dot> getDots() { return dots; }

    public boolean inRoom(Position position) {
        boolean InX = (position.getX() > x) && (position.getX() < (x + widht));
        boolean InY = (position.getY() > y) && (position.getY() < (y + height));

        boolean InInitialChunks = false;
        for (Chunk chunk : initialChunks) {
            if (chunk.getPosition().equals(position))
                InInitialChunks = true;
        }

        return (InX && InY) || InInitialChunks;
    }

    public void checkIsActive(Position heroPosition) {
        if (inRoom(heroPosition)) {
            isVisible = true;
            isActive = true;

            for (Chunk chunk : initialChunks)
                chunk.setIsVisible(true);
        }
        else
            isActive = false;
    }

    public Monster getMonster(Position position) {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position))
                return monster;
        }
        return new Monster(new Position(0, 0), 0, 0);
    }

    public void addPath(Path path) {
        paths.add(path);

        Chunk startingChunk = path.getChunks().get(0);
        Chunk endingChunk = path.getChunks().get(path.getChunks().size() - 1);

        for (Wall wall : walls) {

            if (startingChunk.getPosition().equals(wall.position)) {
                initialChunks.add(startingChunk);
                break;
            }

            if (endingChunk.getPosition().equals(wall.position)) {
                initialChunks.add(endingChunk);
                break;
            }
        }
    }

    public void addMonster(Monster monster) { monsters.add(monster); }
    public void addCoin(Coin coin) { coins.add(coin); }

}
