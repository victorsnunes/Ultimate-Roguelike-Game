import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Chunk> chunks = new ArrayList<>();

    public List<Chunk> getChunks() { return chunks; }
    public void add (Chunk chunk) { chunks.add(chunk); }

    public void checkIsVisible(Position heroPosition) {

        boolean turnNextChunkVisible = false;

        for (Chunk chunk : chunks) {
            if (turnNextChunkVisible) {
                chunk.setIsVisible(true);
                break;
            }

            if (heroPosition.equals(chunk.getPosition()))
                turnNextChunkVisible = true;
        }
    }
}
