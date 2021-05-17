package viewer.game;

import gui.GUI;
import model.game.elements.Chunk;
import model.game.elements.Path;
import viewer.Viewer;

public class PathViewer extends Viewer<Path> {

    private ChunkViewer chunkViewer;

    public PathViewer(Path path) { super(path); }

    public void drawElements(GUI gui) {
        for (Chunk chunk : getModel().getChunks()) {
            if (chunk.getIsVisible())
                chunkViewer.draw(chunk, gui);
        }
    }
}