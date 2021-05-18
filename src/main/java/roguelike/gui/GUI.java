package roguelike.gui;

import roguelike.model.Position;

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

    int getWidth();
    int getHeight();

    void drawText(Position position, String text, String color);

    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, ATTACK, NONE, QUIT, SELECT}
}
