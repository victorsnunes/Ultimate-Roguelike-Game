package roguelikeTests.model.game.arena;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.arena.RandomArenaBuilder;
import roguelike.model.game.elements.*;
import roguelike.model.game.structures.Path;
import roguelike.model.game.structures.Room;

import java.util.List;

class RandomArenaBuilderTest {

    private Arena arena;

    @BeforeEach
    void createArena(){
        this.arena = new RandomArenaBuilder(5, 2, 3, 4).createArena();
    }

    @Test
    void pathTest() {
        //The path can only go over one wall once or twice
        for(Room room: arena.getRooms()){
            int pathsOnRoom = intersections(room.getWalls(), arena.getPaths());
            Assertions.assertTrue(pathsOnRoom > 0 && pathsOnRoom <= 2);
        }
    }

    @Test
    void allCoinsInsideARoom() {
        int numCoins = 0;
        for(Room room: arena.getRooms()){
            for(Coin coin:room.getCoins()){
                Assertions.assertTrue(
                        coin.getX() < room.getX() + room.getWidth() &&
                                coin.getY() < room.getY() + room.getHeight() &&
                                coin.getX() > room.getX() &&
                                coin.getY() > room.getY()

                );
                numCoins++;
            }
        }
        Assertions.assertEquals(3, numCoins);
    }

    @Test
    void allMonstersInsideARoom() {
        int numMonsters = 0;
        int numNormalMonsters = 0;
        int numStrongMonsters = 0;
        for(Room room : arena.getRooms()){
            for(Monster monster : room.getMonsters()){
                Assertions.assertTrue(
                        monster.getX() < room.getX() + room.getWidth() &&
                                monster.getY() < room.getY() + room.getHeight() &&
                                monster.getX() > room.getX() &&
                                monster.getY() > room.getY()
                );
                numMonsters++;

                if (monster.getStrength() >= 6) numStrongMonsters++;
                else numNormalMonsters++;
            }
        }

        Assertions.assertEquals(7, numMonsters);
        Assertions.assertEquals(5, numNormalMonsters);
        Assertions.assertEquals(2, numStrongMonsters);
    }

    @Test
    void allStrengthPotionsInsideARoom() {
        int numStrengthPotions = 0;
        for(Room room : arena.getRooms()){
            for(StrengthPotion sp : room.getStrengthPotions()){
                Assertions.assertTrue(
                        sp.getX() < room.getX() + room.getWidth() &&
                                sp.getY() < room.getY() + room.getHeight() &&
                                sp.getX() > room.getX() &&
                                sp.getY() > room.getY()

                );
                numStrengthPotions++;
            }
        }
        Assertions.assertEquals(4, numStrengthPotions);
    }

    private int intersections(List<Wall> walls, List<Path> paths){
        int counter = 0;
        for(Wall wall: walls){
            for(Path path: paths) {
                for (Chunk chunk : path.getChunks()) {
                    if (wall.getPosition().equals(chunk.getPosition()))
                        counter++;
                }
            }
        }
        return counter;
    }
}