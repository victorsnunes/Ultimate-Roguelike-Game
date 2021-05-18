package roguelike.model.game.arena;

import roguelike.model.Position;
import roguelike.model.game.elements.*;

import java.util.*;

public class RandomArenaBuilder extends ArenaBuilder {
    private final Random rng;

    private final int width;
    private final int height;
    private final int numberOfMonsters;
    private final int numberOfCoins;

    public RandomArenaBuilder(int width, int height, int numberOfMonsters, int numberOfCoins) {
        this.rng = new Random();

        this.width = width;
        this.height = height;
        this.numberOfMonsters = numberOfMonsters;
        this.numberOfCoins = numberOfCoins;
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
    protected void createRooms(Arena arena) {
        //TODO: Make createRooms an actual random room generator
        ArrayList<Room> rooms = new ArrayList<>();

        arena.addRoom(new Room(5, 5, 9, 6));
        arena.addRoom(new Room(40, 5, 9, 6));
        arena.addRoom(new Room(60, 20, 10, 12));
        arena.addRoom(new Room(10, 20, 5, 5));
    }

    @Override
    protected void createPaths(Arena arena){
        ArrayList<Path> paths = new ArrayList<>();
        Random random = new Random();

        //TODO: what is the best queue to use in the situation?
        Queue<Room> queue = new LinkedList<>();
        for(Room room: arena.getRooms()) {
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

        arena.setPaths(paths);
    }

    @Override
    protected void createCoins(Arena arena) {
        Random random = new Random();

        for (int i = 0; i < numberOfCoins; i++) {
            int randomRoomIndex = random.nextInt(arena.getRooms().size());
            Room room = arena.getRooms().get(randomRoomIndex);

            int posX = random.nextInt(room.getWidht() - 2) + room.getX() + 1;
            int posY = random.nextInt(room.getHeight() - 2) + room.getY() + 1;

            room.addCoin(new Coin(new Position(posX, posY)));
        }
    }

    @Override
    protected void createMonsters(Arena arena) {
        Random random = new Random();

        for (int i = 0; i < numberOfMonsters; i++) {
            int randomRoomIndex = random.nextInt(arena.getRooms().size());
            Room room = arena.getRooms().get(randomRoomIndex);

            int posX = random.nextInt(room.getWidht() - 2) + room.getX() + 1;
            int posY = random.nextInt(room.getHeight() - 2) + room.getY() + 1;

            room.addMonster(new Monster(new Position(posX, posY)));
        }
    }

    @Override
    protected void createHero(Arena arena) {

        Random random = new Random();

        Position position = new Position(0, 0);
        while (!arena.inRoom(position)) {
            int randomX = random.nextInt(width);
            int randomY = random.nextInt(height);

            position.setX(randomX);
            position.setY(randomY);
        }

        arena.setHero(new Hero(position));
    }
}
