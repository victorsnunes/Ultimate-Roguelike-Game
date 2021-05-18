package roguelike.viewer.game;

import roguelike.gui.GUI;
import roguelike.model.game.elements.Chunk;
import roguelike.model.game.elements.Path;
import roguelike.viewer.Viewer;

public class PathViewer extends Viewer<Path> {

    private ChunkViewer chunkViewer = new ChunkViewer();

    public PathViewer(Path path) { super(path); }

    public void drawElements(GUI gui) {
        for (Chunk chunk : getModel().getChunks()) {
            if (chunk.getIsVisible())
                chunkViewer.draw(chunk, gui);
        }
    }
}