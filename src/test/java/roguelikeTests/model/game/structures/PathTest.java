package roguelikeTests.model.game.structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roguelike.model.Position;
import roguelike.model.game.elements.Chunk;
import roguelike.model.game.structures.Path;

class PathTest {

    Path path = new Path();

    @BeforeEach
    public void createPath() {
        for (int i = 5; i < 20; i++)
            path.add(new Chunk(new Position(i, 10)));
    }

    @Test
    public void updateVisibilityTest() {

        for (Chunk chunk : path.getChunks()) {
            Assertions.assertFalse(chunk.getIsVisible());
        }

        path.updateVisibility(new Position(8, 10));

        for (Chunk chunk : path.getChunks()) {
            if (chunk.getPosition().equals(new Position(7, 10)))
                Assertions.assertTrue(chunk.getIsVisible());
            else if (chunk.getPosition().equals(new Position(9, 10)))
                Assertions.assertTrue(chunk.getIsVisible());
            else
                Assertions.assertFalse(chunk.getIsVisible());
        }
    }

    @Test
    public void getFirstChunkTest() {
        Assertions.assertEquals(new Chunk(new Position(5, 10)), path.getFirstChunk());
    }

    @Test
    public void getLastChunkTest() {
        Assertions.assertEquals(new Chunk(new Position(19, 10)), path.getLastChunk());
    }
}