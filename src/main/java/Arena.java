import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private List<Room> rooms;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(new Position(10, 10));
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
        this.rooms = createRooms();
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        for (Coin coin : coins) {
            coin.draw(graphics);
        }

        hero.draw(graphics);

        for (Monster monster : monsters)
            monster.draw(graphics);

        for (Wall wall : walls)
            wall.draw(graphics);

        for (Room room : rooms) {
            for (Wall wall : room.getWalls())
                wall.draw(graphics);
        }
    }

    public void processKey(KeyStroke key) {

        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                moveMonsters(hero.getPosition());
                break;

            case ArrowDown:
                moveHero(hero.moveDown());
                moveMonsters(hero.getPosition());
                break;

            case ArrowLeft:
                moveHero(hero.moveLeft());
                moveMonsters(hero.getPosition());
                break;

            case ArrowRight:
                moveHero(hero.moveRight());
                moveMonsters(hero.getPosition());
                break;

            default:
                break;
        }
    }

    private void moveHero(Position position) {
        if (canMove(position))
            hero.setPosition(position);

        retrieveCoins(position);
    }

    private void moveMonsters(Position pos) {
        Random random = new Random();

        for(Monster monster : monsters) {
            int movement = random.nextInt(4);
            Position monsterPosition = new Position(monster.getX(), monster.getY());

            if (monsterPosition.getX() < pos.getX()){
                monsterPosition.setX(monster.getX() + 1);
            }
            if (monsterPosition.getX() > pos.getX()){
                monsterPosition.setX(monster.getX() - 1);
            }
            if (monsterPosition.getY() < pos.getY()){
                monsterPosition.setY(monster.getY() + 1);
            }
            if (monsterPosition.getY() > pos.getY()){
                monsterPosition.setY(monster.getY() - 1);

            }


            if (canMove(monsterPosition))
                monster.setPosition(monsterPosition);
        }
    }

    private void retrieveCoins(Position position) {
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)){
                coins.remove(coin);
                break;
            }
        }
    }

    public boolean verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (hero.getPosition().equals(monster.getPosition()))
                return true;
        }
        return false;
    }

    private boolean canMove(Position position) {

        for (Wall wall : walls) {
            if (wall.getPosition().equals(position))
                return false;
        }

        return (position.getX() >= 0) && (position.getX() < width) && (position.getY() >= 0) && (position.getY() < height);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new HorizontalWall(c, 0));
            walls.add(new HorizontalWall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new VerticalWall(0, r));
            walls.add(new VerticalWall(width - 1, r));
        }

        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        }
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        }
        return monsters;
    }

    private List<Room> createRooms() {
        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Room(30, 60, 20, 20));

        return rooms;
    }
}
