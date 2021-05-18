package roguelike.model.game.arena;

import roguelike.model.Position;
import roguelike.model.game.elements.*;
import roguelike.model.game.structures.Path;
import roguelike.model.game.structures.Room;

import java.util.*;

public class RandomArenaBuilder extends ArenaBuilder {
    private final Random random;

    private final int width;
    private final int height;
    private final int numberOfMonsters;
    private final int numberOfCoins;

    public RandomArenaBuilder(int width, int height, int numberOfMonsters, int numberOfCoins) {
        this.random = new Random();

        this.width = width;
        this.height = height;
        this.numberOfMonsters = numberOfMonsters;
        this.numberOfCoins = numberOfCoins;
    }

    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        createRooms(arena);
        createPaths(arena);
        createMonsters(arena);
        createCoins(arena);
        createHero(arena);

        return arena;
    }

    
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    
    private void createRooms(Arena arena) {
        //TODO: Make createRooms an actual random room generator
        ArrayList<Room> rooms = new ArrayList<>();

        arena.addRoom(new Room(2, 2, 8, 6));
        arena.addRoom(new Room(12, 10, 5, 5));
    }
    
    private void createCoins(Arena arena) {
        Random random = new Random();

        for (int i = 0; i < numberOfCoins; i++) {
            int randomRoomIndex = random.nextInt(arena.getRooms().size());
            Room room = arena.getRooms().get(randomRoomIndex);

            int posX = random.nextInt(room.getWidht() - 2) + room.getX() + 1;
            int posY = random.nextInt(room.getHeight() - 2) + room.getY() + 1;

            room.addCoin(new Coin(new Position(posX, posY)));
        }
    }

    private void createMonsters(Arena arena) {
        Random random = new Random();

        for (int i = 0; i < numberOfMonsters; i++) {
            int randomRoomIndex = random.nextInt(arena.getRooms().size());
            Room room = arena.getRooms().get(randomRoomIndex);

            int posX = random.nextInt(room.getWidht() - 2) + room.getX() + 1;
            int posY = random.nextInt(room.getHeight() - 2) + room.getY() + 1;

            room.addMonster(new Monster(new Position(posX, posY)));
        }
    }

    private void createHero(Arena arena) {

        Random random = new Random();

        Position position = new Position(0, 0);
        while (!arena.inInnerRoom(position)) {
            int randomX = random.nextInt(width);
            int randomY = random.nextInt(height);

            position.setX(randomX);
            position.setY(randomY);
        }

        arena.setHero(new Hero(position));
    }
}
