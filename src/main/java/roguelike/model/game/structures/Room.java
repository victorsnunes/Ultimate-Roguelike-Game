package roguelike.model.game.structures;

import roguelike.model.Position;
import roguelike.model.game.elements.*;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int x;
    private int y;
    private int widht;
    private int height;

    private boolean isVisible = false;
    private boolean isActive = false;
    private boolean hasGoal = false;

    private List<Wall> walls = new ArrayList<>();

    private List<Monster> monsters = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();
    private List<Dot> dots = new ArrayList<>();
    private Goal goal;

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
    public boolean getHasGoal() { return hasGoal; }

    public List<Monster> getMonsters() { return monsters; }
    public List<Coin> getCoins() { return coins; }
    public List<Dot> getDots() { return dots; }
    public Goal getGoal() { return goal; }

    public void setGoal(Goal goal) {
        this.goal = goal;
        this.hasGoal = true;
    }

    public boolean inRoom(Position position) {
        boolean InX = (position.getX() >= x) && (position.getX() <= (x + widht));
        boolean InY = (position.getY() >= y) && (position.getY() <= (y + height));

        return InX && InY;
    }

    public boolean inInnerRoom(Position position) {
        boolean InX = (position.getX() > x) && (position.getX() < (x + widht));
        boolean InY = (position.getY() > y) && (position.getY() < (y + height));

        return InX && InY;
    }

    public void updateVisibility(Position heroPosition) {
        if (inRoom(heroPosition)) {
            isVisible = true;
            isActive = true;
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

    public Coin retrieveCoin(Position position) {
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) {
                coins.remove(coin);
                return coin;
            }
        }
        return new Coin(new Position(0, 0), 0);
    }

    public boolean isGoal(Position position) {
        if (hasGoal) {
            if (goal.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public void attackMonster(Position monsterPosition, int heroStrength) {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(monsterPosition)) {
                monster.decreaseHealth(heroStrength);
                if (monster.getHealth() <= 0)
                    monsters.remove(monster);
                break;
            }
        }
    }

    public void addMonster(Monster monster) { monsters.add(monster); }
    public void addCoin(Coin coin) { coins.add(coin); }
}
