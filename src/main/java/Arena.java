import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.nio.file.Paths;
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
    private List<Path> paths;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(new Position(10, 10));
        this.coins = createCoins();
        this.monsters = createMonsters();
        this.rooms = createRooms();
        this.paths = createPath();
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

        for (Room room : rooms) {
            for (Wall wall : room.getWalls())
                wall.draw(graphics);
        }

        for(Path path : paths){
            for (Chunk chunk : path.getChunks())
                chunk.draw(graphics);
        }
    }

    public void processKey(KeyStroke key) {
        if(key == null){
            return;
        }

        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;

            case ArrowDown:
                moveHero(hero.moveDown());
                break;

            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;

            case ArrowRight:
                moveHero(hero.moveRight());
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

    public void moveMonsters() {
        Position pos = hero.getPosition();
        for(Monster monster : monsters) {
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

        for (Room room : rooms){
            for (Wall wall : room.getWalls()){
                if (wall.getPosition().equals(position))
                    return false;
            }
        }

        return (position.getX() >= 0) && (position.getX() < width) && (position.getY() >= 0) && (position.getY() < height);
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

        for (int i = 0; i < 2; i++) {
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        }
        return monsters;
    }

    private List<Room> createRooms() {
        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Room(5, 5, 9, 6));
        rooms.add(new Room(50, 20, 10, 12));
        return rooms;
    }

    private List<Path> createPath(){
        ArrayList<Path> paths = new ArrayList<>();
        Random random = new Random();
        Room room1 = rooms.get(0);
        Room room2 = rooms.get(1);
        boolean hDirection = false;
        boolean vDirection = false;
        Room mostLeftTop = room1;
        Room mostRightBottom = room2;

        Path path = new Path();

        if(room1.getX() + room1.getWidht() < room2.getX()){
            hDirection = true;
            mostLeftTop = room1;
            mostRightBottom = room2;
        }
        if(room2.getX() + room2.getWidht() < room1.getX()){
            hDirection = true;
            mostLeftTop = room2;
            mostRightBottom = room1;
        }
        if(room1.getY() + room1.getHeight() < room2.getY()){
            vDirection = true;
            mostLeftTop = room1;
            mostRightBottom = room2;
        }
        if (room2.getY() + room2.getHeight() < room1.getY()){
            vDirection = true;
            mostLeftTop = room2;
            mostRightBottom = room1;
        }
        if(hDirection && vDirection){
            hDirection = false;
        }


        int division;
        if(hDirection) {
            division = random.nextInt(mostRightBottom.getX() - (mostLeftTop.getX() + mostLeftTop.getWidht())) + mostLeftTop.getX() + mostLeftTop.getWidht();
            int leftPointX = mostLeftTop.getX() + mostLeftTop.getWidht();
            int leftPointY = random.nextInt(mostLeftTop.getHeight()) + mostLeftTop.getY();

            int rightPointX = mostRightBottom.getX();
            int rightPointY = random.nextInt(mostRightBottom.getHeight()) + mostRightBottom.getY();


            int x = leftPointX;
            int y = leftPointY;
            while(x < division){
                path.add(new Chunk(x, y));
                x++;
            }
            while(y != rightPointY){
                path.add(new Chunk(x, y));
                if(y > rightPointY) y--;
                else y++;
            }
            while(x != rightPointX){
                path.add(new Chunk(x, y));
                x++;
            }

        } else {
            division = random.nextInt(mostRightBottom.getY() - (mostLeftTop.getY() + mostLeftTop.getHeight())) + mostLeftTop.getY() + mostLeftTop.getHeight();
            int topPointX = random.nextInt(mostLeftTop.getWidht()) + mostLeftTop.getX();
            int topPointY = mostLeftTop.getY() + mostLeftTop.getHeight();

            int bottomPointx = random.nextInt(mostRightBottom.getWidht()) + mostRightBottom.getX();
            int bottomPointY = mostRightBottom.getY();


            int x = topPointX;
            int y = topPointY;
            while(y < division){
                path.add(new Chunk(x, y));
                y++;
            }
            while(x != bottomPointx){
                path.add(new Chunk(x, y));
                if(x > bottomPointx) x--;
                else x++;
            }
            while(y != bottomPointY){
                path.add(new Chunk(x, y));
                y++;
            }
        }

        paths.add(path);

        return paths;
    }
}
