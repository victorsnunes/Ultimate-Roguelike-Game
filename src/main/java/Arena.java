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
    private List<Room> rooms;
    private List<Path> paths;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(new Position(10, 10));
        this.rooms = createRooms();
        this.paths = createPath(hero.getPosition());
        createMonsters();
        createCoins();
    }

    public Hero getHero() { return hero; }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#EF8433"));
        graphics.putString(new TerminalPosition(0, 0), "Hero Health: " + hero.getHealth());

        for (Room room : rooms) {
            room.draw(graphics);
        }

        for(Path path : paths) {
            for (Chunk chunk : path.getChunks())
                chunk.draw(graphics);
        }

        hero.draw(graphics);

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
        if (canMove(position)) {
            hero.setPosition(position);
            if (verifyMonsterCollisions())
                hero.decreaseHealth();
        }

        retrieveCoins();
    }

    public void moveMonsters() {
        Position pos = hero.getPosition();
        for (Room room : rooms) {
            for(Monster monster : room.getMonsters()) {
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

                if (canMoveMonster(monsterPosition)) {
                    monster.setPosition(monsterPosition);
                    if (verifyMonsterCollisions())
                        hero.decreaseHealth();
                }
            }
        }
    }

    private void retrieveCoins() {
        for (Room room : rooms) {
            for (Coin coin : room.getCoins()) {
                if (coin.getPosition().equals(hero.position)) {
                    hero.increaseHealth();
                    room.getCoins().remove(coin);
                    break;
                }
            }
        }
    }

    public boolean verifyMonsterCollisions() {
        for (Room room : rooms) {
            for (Monster monster : room.getMonsters()) {
                if (hero.getPosition().equals(monster.getPosition()))
                    return true;
            }
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

    private boolean canMoveMonster(Position position) {
        for (Room room : rooms) {
            if (room.getActive()) {
                boolean InX = (position.getX() > room.getX()) && (position.getX() < (room.getX() + room.getWidht()));
                boolean InY = (position.getY() > room.getY()) && (position.getY() < (room.getY() + room.getHeight()));

                if (InX && InY)
                    return true;
            }
        }
        return false;
    }

    private void createCoins() {
        Random random = new Random();

        for (Room room : rooms) {

            int numberOfCoins = random.nextInt(4) - 1;
            for (int i = 0; i < numberOfCoins; i++) {
                int posX = random.nextInt(room.getWidht() - 2) + room.getX() + 1;
                int posY = random.nextInt(room.getHeight() - 2) + room.getY() + 1;

                room.addCoin(new Coin(new Position(posX, posY)));
            }

        }
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();

        for (Room room : rooms) {
            if (random.nextInt(2) == 0) {
                int posX = random.nextInt(room.getWidht() - 2) + room.getX() + 1;
                int posY = random.nextInt(room.getHeight() - 2) + room.getY() + 1;

                Monster monster = new Monster(new Position(posX, posY));
                monsters.add(monster);
                room.addMonster(monster);
            }
        }

        return monsters;
    }

    private List<Room> createRooms() {
        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Room(5, 5, 9, 6));
        rooms.add(new Room(40, 5, 9, 6));
        rooms.add(new Room(60, 20, 10, 12));
        rooms.add(new Room(10, 20, 5, 5));

        return rooms;
    }

    private List<Path> createPath(Position heroPosition){
        ArrayList<Path> paths = new ArrayList<>();
        Random random = new Random();

        //TODO: what is the best queue to use in the situation?
        Queue<Room> queue = new LinkedList<>();
        for(Room room: rooms) {
            queue.add(room);
        }

        while(queue.size() > 1) {

            Path path = new Path();

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
            if (room2.getX() + room2.getWidht() < room1.getX()) {
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
                division = random.nextInt(mostRight.getX() - (mostLeft.getX() + mostLeft.getWidht())) + mostLeft.getX() + mostLeft.getWidht();
                int leftPointX = mostLeft.getX() + mostLeft.getWidht();
                int leftPointY = random.nextInt(mostLeft.getHeight() - 2) + mostLeft.getY() + 1;

                int rightPointX = mostRight.getX();
                int rightPointY = random.nextInt(mostRight.getHeight() - 2) + mostRight.getY() + 1;


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
                int topPointX = random.nextInt(mostTop.getWidht() - 2) + mostTop.getX() + 1;
                int topPointY = mostTop.getY() + mostTop.getHeight();

                int bottomPointx = random.nextInt(mostBottom.getWidht() - 2) + mostBottom.getX() + 1;
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

            paths.add(path);
            room1.addPath(path);
            room2.addPath(path);
        }

        for (Room room : rooms)
            room.checkIsActive(heroPosition);

        return paths;
    }
}
