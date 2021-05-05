import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Terminal terminal;
    private Screen screen;

    private int width = 80;
    private int height = 40;

    private Arena arena = new Arena(width, height);

    public Game() {
        try {

            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);     //we don't need a cursor
            screen.startScreen();               //screens must be started
            screen.doResizeIfNecessary();       //resize screen if necessary

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long FPS = 30;
        long frameCounter = 0;
        long loopTime = 1000/FPS;
        long elapsedTime;

        try {
            arena.draw(screen.newTextGraphics());
            KeyStroke key = screen.pollInput();
            processKey(key);
            while (key == null || ( !(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') && !(key.getKeyType() == KeyType.EOF))) {
                long start = System.currentTimeMillis();
                processKey(key);
                screen.clear();
                arena.draw(screen.newTextGraphics());
                screen.refresh();
                if(frameCounter%30 == 0){
                    arena.moveMonsters();
                }
                if (arena.verifyMonsterCollisions()) {
                    System.out.println("Oh no, a monster got you!");
                    break;
                }
                elapsedTime = System.currentTimeMillis() - start;

                if(elapsedTime < loopTime){
                    Thread.sleep(loopTime - elapsedTime);
                }
                frameCounter = (frameCounter + 1)%FPS;
                key = screen.pollInput();
            }
        }
        catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }
}
