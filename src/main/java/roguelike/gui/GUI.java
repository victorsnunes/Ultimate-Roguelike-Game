package roguelike.gui;

import roguelike.model.Position;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawHero(Position position, String color);
    void drawMonster(Position position, String color);
    void drawVerticalWall(Position position);
    void drawHorizontalWall(Position position);
    void drawChunk(Position position);
    void drawCoin(Position position);
    void drawGoal(Position position);
    void drawDot(Position position);
    void drawStrengthPotion(Position position);

    int getWidth();
    int getHeight();

    void drawText(Position position, String text, String color);

    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, ATTACK, NONE, QUIT, SELECT}
}
