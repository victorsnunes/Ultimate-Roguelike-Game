package roguelike.model.game.arena;

import roguelike.model.Position;
import roguelike.model.game.elements.*;
import roguelike.model.game.structures.Room;

import java.util.Random;

public class RandomArenaBuilder extends ArenaBuilder {
    private final Random random;

    private final int width = 60;
    private final int height = 30;
    private final int numberOfMonsters;
    private final int numberOfStrongMonsters;
    private final int numberOfCoins;
    private final int numberOfStrengthPotions;

    public RandomArenaBuilder(int numberOfMonsters, int numberOfStrongMonsters, int numberOfCoins, int numberOfStrengthPotions) {
        this.random = new Random();

        this.numberOfStrongMonsters = numberOfStrongMonsters;
        this.numberOfMonsters = numberOfMonsters;
        this.numberOfCoins = numberOfCoins;
        this.numberOfStrengthPotions = numberOfStrengthPotions;
    }

    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        createRooms(arena);
        createPaths(arena);
        createMonsters(arena);
        createCoins(arena);
        createStrongPotions(arena);
        createHero(arena);
        createGoal(arena);

        return arena;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    
    private void createRooms(Arena arena) {

        //There will be 3 rooms, with width and height varying between 5 and 15
        int numberOfRooms = 3;
        int offset = 0;
        int randomX;
        int randomY;
        int randomWidth;
        int randomHeight;

        for (int i = 0; i < 3; i++) {
            do {
                randomX = random.nextInt(15) + offset;
                randomY = random.nextInt(25);

                randomWidth = random.nextInt(10) + 5;
                randomHeight = random.nextInt(15) + 5;
            } while ((randomX + randomWidth > 20 + offset) || (randomY + randomHeight > 29));

            offset += 20;
            arena.addRoom(new Room(randomX, randomY, randomWidth, randomHeight));
        }
    }
    
    private void createCoins(Arena arena) {
        for (int i = 0; i < numberOfCoins; i++) {
            int randomRoomIndex = random.nextInt(arena.getRooms().size());
            Room room = arena.getRooms().get(randomRoomIndex);

            int posX = random.nextInt(room.getWidth() - 1) + room.getX() + 1;
            int posY = random.nextInt(room.getHeight() - 1) + room.getY() + 1;

            room.addCoin(new Coin(new Position(posX, posY)));
        }
    }

    private void createStrongPotions(Arena arena) {
        for (int i = 0; i < numberOfStrengthPotions; i++) {
            int randomRoomIndex = random.nextInt(arena.getRooms().size());
            Room room = arena.getRooms().get(randomRoomIndex);

            int posX = random.nextInt(room.getWidth() - 1) + room.getX() + 1;
            int posY = random.nextInt(room.getHeight() - 1) + room.getY() + 1;

            room.addStrengthPotion(new StrengthPotion(new Position(posX, posY), 7, 20));
        }
    }

    private void createMonsters(Arena arena) {

        for (int i = 0; i < numberOfMonsters; i++) {
            int randomRoomIndex = random.nextInt(arena.getRooms().size());
            Room room = arena.getRooms().get(randomRoomIndex);

            int posX = random.nextInt(room.getWidth() - 1) + room.getX() + 1;
            int posY = random.nextInt(room.getHeight() - 1) + room.getY() + 1;

            room.addMonster(new Monster(new Position(posX, posY)));
        }

        for (int i = 0; i < numberOfStrongMonsters; i++) {
            //No strong monsters in the first room
            int randomRoomIndex = random.nextInt(arena.getRooms().size() - 1) + 1;
            Room room = arena.getRooms().get(randomRoomIndex);

            int posX = random.nextInt(room.getWidth() - 1) + room.getX() + 1;
            int posY = random.nextInt(room.getHeight() - 1) + room.getY() + 1;

            room.addMonster(new Monster(new Position(posX, posY), 10, 6));
        }
    }

    private void createHero(Arena arena) {

        Position position = new Position(0, 0);
        Room room = arena.getRooms().get(0); //Initial room
        do {
            int randomX = random.nextInt(room.getWidth() - 1) + room.getX() + 1;
            int randomY = random.nextInt(room.getHeight() - 1) + room.getY() + 1;

            position.setX(randomX);
            position.setY(randomY);
        } while (arena.getMonster(position) != null); //Checking if hero doesn't spawn in the same position of a monster

        arena.setHero(new Hero(position));
    }

    private void createGoal(Arena arena) {

        Room room = arena.getRooms().get(arena.getRooms().size() - 1); //Last room

        int randomX = random.nextInt(room.getWidth() - 1) + room.getX() + 1;
        int randomY = random.nextInt(room.getHeight() - 1) + room.getY() + 1;

        room.setGoal(new Goal(new Position(randomX, randomY)));
    }
}
