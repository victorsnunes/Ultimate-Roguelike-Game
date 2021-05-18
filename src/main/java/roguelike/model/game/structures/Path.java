package roguelike.model.game.structures;

import roguelike.model.Position;
import roguelike.model.game.elements.Chunk;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Chunk> chunks = new ArrayList<>();

    public List<Chunk> getChunks() { return chunks; }
    public Chunk getFirstChunk() { return chunks.get(0); }
    public Chunk getLastChunk() { return chunks.get(chunks.size() - 1); }
    public void add(Chunk chunk) { chunks.add(chunk); }

    public void updateVisibility(Position heroPosition) {
        for (int i = 0; i < chunks.size(); i++) {
            if (chunks.get(i).getPosition().equals(heroPosition)) {
                if (i != 0) chunks.get(i - 1).setIsVisible(true);
                if (i != chunks.size() - 1) chunks.get(i + 1).setIsVisible(true);
            }
        }
    }
}