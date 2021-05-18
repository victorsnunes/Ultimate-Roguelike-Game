package roguelike.model.game.arena;

import roguelike.model.Position;
import roguelike.model.game.elements.Coin;
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

        arena.addRoom(room1);
        arena.addRoom(room2);
        arena.addRoom(room3);

        createPaths(arena);

        arena.setHero(new Hero(new Position(5, 3)));

        return arena;
    }
}
