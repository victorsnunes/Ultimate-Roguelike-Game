package roguelike.viewer.game.elements;

import roguelike.gui.GUI;
import roguelike.model.game.elements.Chunk;

public class ChunkViewer implements ElementViewer<Chunk> {
    @Override
    public void draw(Chunk chunk, GUI gui) { gui.drawChunk(chunk.getPosition()); }
}