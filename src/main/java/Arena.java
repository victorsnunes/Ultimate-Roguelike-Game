import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

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
        this.rooms = createRooms(hero.getPosition());
        this.paths = createPath();
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        for (Coin coin : coins) {
            coin.draw(graphics);
        }

        for (Room room : rooms) {
            room.draw(graphics);
        }

        for(Path path : paths){
            for (Chunk chunk : path.getChunks())
                chunk.draw(graphics);
        }

        hero.draw(graphics);

        for (Monster monster : monsters)
            monster.draw(graphics);

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

        for (Room room : rooms)
            room.checkIsActive(hero.position);

        for (Path path : paths)
            path.checkIsVisible(hero.position);
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

        for (Path path : paths)
            for (Chunk chunk : path.getChunks())
                if (position.equals(chunk.position))
                    return true;

        for (Room room : rooms)
            if (room.inRoom(position))
                return true;

        return false;
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

    private List<Room> createRooms(Position heroPosition) {
        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Room(5, 5, 9, 6));
        rooms.add(new Room(40, 5, 9, 6));
        rooms.add(new Room(60, 20, 10, 12));
        rooms.add(new Room(10, 20, 5, 5));

        for (Room room : rooms)
            room.checkIsActive(heroPosition);

        return rooms;
    }

    private List<Path> createPath(){
        ArrayList<Path> paths = new ArrayList<>();
        Random random = new Random();

        //TODO: what is the best queue to use in the situation?
        Queue<Room> queue = new LinkedList<>();
        Path path = new Path();
        for(Room room: rooms) {
            queue.add(room);
        }

        while(!queue.isEmpty() && queue.size() > 1) {
            boolean hDirection = false;
            boolean vDirection = false;

            Room room1 = queue.poll();
            Room room2 = queue.peek();
            Room mostLeft = room1;
            Room mostTop = room1;
            Room mostRight = room2;
            Room mostBottom = room2;


            if (room1.getX() + room1.getWidht() < room2.getX()) {
                hDirection = true;
                mostLeft = room1;
                mostRight = room2;
            }
            else if (room2.getX() + room2.getWidht() < room1.getX()) {
                hDirection = true;
                mostLeft = room2;
                mostRight = room1;
            }
            if (room1.getY() + room1.getHeight() < room2.getY()) {
                vDirection = true;
                mostTop = room1;
                mostBottom = room2;
            }
            if (room2.getY() + room2.getHeight() < room1.getY()) {
                vDirection = true;
                mostTop = room2;
                mostBottom = room1;
            }
            if (hDirection && vDirection) {
                hDirection = random.nextBoolean();
            }
            if (!(hDirection || vDirection)){
                //two room are overlapping
                continue;
            }


            int division;
            if (hDirection) {
                System.out.println("left x+widht: " + (mostLeft.getX() + mostLeft.getWidht()) + " right x: " + mostRight.getX());
                division = random.nextInt(mostRight.getX() - (mostLeft.getX() + mostLeft.getWidht())) + mostLeft.getX() + mostLeft.getWidht();
                int leftPointX = mostLeft.getX() + mostLeft.getWidht();
                int leftPointY = random.nextInt(mostLeft.getHeight()) + mostLeft.getY();

                int rightPointX = mostRight.getX();
                int rightPointY = random.nextInt(mostRight.getHeight()) + mostRight.getY();


                int x = leftPointX;
                int y = leftPointY;
                while (x < division) {
                    path.add(new Chunk(x, y));
                    x++;
                }
                while (y != rightPointY + 1) {
                    path.add(new Chunk(x, y));
                    if (y > rightPointY) y--;
                    else y++;
                }
                while (x != rightPointX + 1) {
                    path.add(new Chunk(x, y));
                    x++;
                }

            } else {
                System.out.println("Bottom (x, y): (" + mostBottom.getX() + ", " + mostBottom.getY() + ")");
                System.out.println("Top height (x, y): (" + mostTop.getX() + ", " + (mostTop.getY() + mostTop.getHeight()) + ")");
                division = random.nextInt(mostBottom.getY() - (mostTop.getY() + mostTop.getHeight())) + mostTop.getY() + mostTop.getHeight();
                int topPointX = random.nextInt(mostTop.getWidht()) + mostTop.getX();
                int topPointY = mostTop.getY() + mostTop.getHeight();

                int bottomPointx = random.nextInt(mostBottom.getWidht()) + mostBottom.getX();
                int bottomPointY = mostBottom.getY();


                int x = topPointX;
                int y = topPointY;
                while (y < division) {
                    path.add(new Chunk(x, y));
                    y++;
                }
                while (x != bottomPointx + 1) {
                    path.add(new Chunk(x, y));
                    if (x > bottomPointx) x--;
                    else x++;
                }
                while (y != bottomPointY + 1) {
                    path.add(new Chunk(x, y));
                    y++;
                }
            }
        }
        //Sets begining of the path always visible
        (path.getChunks().get(0)).setIsVisible(true);

        paths.add(path);

        return paths;
    }
}
