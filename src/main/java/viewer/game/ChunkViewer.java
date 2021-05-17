package viewer.game;

import gui.GUI;
import model.game.elements.Chunk;
import model.game.elements.Hero;

public class ChunkViewer implements ElementViewer<Chunk> {
    @Override
    public void draw(Chunk chunk, GUI gui) { gui.drawChunk(chunk.getPosition()); }
}