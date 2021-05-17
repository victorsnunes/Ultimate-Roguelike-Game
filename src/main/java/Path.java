import model.Position;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Chunk> chunks = new ArrayList<>();

    public List<Chunk> getChunks() { return chunks; }
    public void add (Chunk chunk) { chunks.add(chunk); }

    public void checkIsVisible(Position heroPosition) {

        for (int i = 0; i < chunks.size(); i++) {

            if (heroPosition.equals(chunks.get(i).getPosition())) {
                if (i != 0)
                    chunks.get(i - 1).setIsVisible(true);
                if (i != chunks.size() - 1)
                    chunks.get(i + 1).setIsVisible(true);
            }
        }
    }
}
