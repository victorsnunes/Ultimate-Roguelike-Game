package roguelike.model.game.elements;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Chunk> chunks = new ArrayList<>();

    public List<Chunk> getChunks() { return chunks; }
    public void add (Chunk chunk) { chunks.add(chunk); }

}