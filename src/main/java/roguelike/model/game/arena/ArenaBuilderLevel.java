package roguelike.model.game.arena;

import roguelike.model.Position;
import roguelike.model.game.elements.Coin;
import roguelike.model.game.elements.Goal;
import roguelike.model.game.elements.Hero;
import roguelike.model.game.elements.Monster;
import roguelike.model.game.structures.Room;

public class ArenaBuilderLevel extends ArenaBuilder {

    public Arena getLevel1() {
        Arena arena = new Arena(60, 20);

        Room room1 = new Room(3, 2, 18, 8);
        Room room2 = new Room(19, 13, 13, 6);
        Room room3 = new Room(39, 2, 19, 9);

        room1.addMonster(new Monster(new Position(5, 8)));
        room1.addMonster(new Monster(new Position(17, 5)));
        room2.addMonster(new Monster(new Position(25, 15)));
        room3.addMonster(new Monster(new Position(43, 4)));
        room3.addMonster(new Monster(new Position(54,9)));

        room2.addCoin(new Coin(new Position(27, 17)));
        room3.addCoin(new Coin(new Position(45, 9)));

        room3.setGoal(new Goal(new Position(56, 3)));

        arena.addRoom(room1);
        arena.addRoom(room2);
        arena.addRoom(room3);

        createPaths(arena);

        arena.setHero(new Hero(new Position(5, 3)));

        return arena;
    }

    public Arena getLevel2() {
        Arena arena = new Arena(60, 20);

        Room room1 = new Room(3, 2, 15, 6);
        Room room2 = new Room(8, 12, 12, 5);
        Room room3 = new Room(32, 16, 12, 7);
        Room room4 = new Room(43, 3, 11, 10);

        room1.addMonster(new Monster(new Position(5, 7)));
        room1.addMonster(new Monster(new Position(15, 5)));
        room2.addMonster(new Monster(new Position(10, 16)));
        room2.addMonster(new Monster(new Position(14, 14)));
        room3.addMonster(new Monster(new Position(40, 22)));
        room4.addMonster(new Monster(new Position(50,10)));
        room4.addMonster(new Monster(new Position(51,7)));
        room4.addMonster(new Monster(new Position(44,5)));

        room2.addCoin(new Coin(new Position(16, 16)));
        room3.addCoin(new Coin(new Position(35, 19)));
        room3.addCoin(new Coin(new Position(42, 19)));

        room4.setGoal(new Goal(new Position(53, 4)));

        arena.addRoom(room1);
        arena.addRoom(room2);
        arena.addRoom(room3);
        arena.addRoom(room4);

        createPaths(arena);

        arena.setHero(new Hero(new Position(5, 3)));

        return arena;
    }
}
