package roguelike.model.game.arena;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roguelike.model.game.elements.Chunk;
import roguelike.model.game.elements.Coin;
import roguelike.model.game.elements.Wall;
import roguelike.model.game.structures.Path;
import roguelike.model.game.structures.Room;

import java.util.List;

class RandomArenaBuilderTest {

    private Arena arena;

    @BeforeEach
    void createArena(){
        this.arena = new RandomArenaBuilder(18, 0, 18, 0).createArena();
    }

    @Test
    void createPaths() {
        //The path can only go over one wall once or twice
        for(Room room: arena.getRooms()){
            int pathsOnRoom = intersections(room.getWalls(), arena.getPaths());
            System.out.println(pathsOnRoom);
            Assertions.assertTrue(pathsOnRoom > 0 && pathsOnRoom <= 2);
        }

        //if a room is visible it should have a visible initial path
        for(Room room: arena.getRooms()){
            room.setIsVisible(true);
            //Assertions.assertTrue(findInitialChunk(room).getIsVisible());
        }

    }

    Chunk findInitialChunk(Room room){
        for (Wall wall : room.getWalls()) {
            for (Path path : arena.getPaths()) {
                for (Chunk chunk : path.getChunks()) {
                    if (wall.getPosition().equals(chunk.getPosition()))
                        return chunk;
                }
            }
        }
        return null;
    }

    @Test
    void createCoins() {
        allCoinsInsideARoom();
    }

    @Test
    void createMonsters() {
    }

    private void allCoinsInsideARoom(){
        for(Room room: arena.getRooms()){
            for(Coin coin:room.getCoins()){
                Assertions.assertTrue(
                      coin.getX() < room.getX() + room.getWidht() &&
                              coin.getY() < room.getY() + room.getHeight() &&
                              coin.getX() > room.getX() &&
                              coin.getY() > room.getY()

                );
            }
        }
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