package gui;

import model.Position;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawHero(Position position);
    void drawMonster(Position position);
    void drawVerticalWall(Position position);
    void drawHorizontalWall(Position position);
    void drawChunk(Position position);
    void drawCoin(Position position);
    void drawDot(Position position);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
