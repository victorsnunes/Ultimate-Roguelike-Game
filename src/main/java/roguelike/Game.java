package roguelike;

import roguelike.gui.LanternaGUI;
import roguelike.states.StartMenuState;
import roguelike.states.State;
import roguelike.model.menu.Menu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Game {
    private final LanternaGUI gui;
    private State state;
    private State savedState;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(60, 30);
        this.state = new StartMenuState(new Menu("Ultimate Rogue Game", Arrays.asList("Start", "Instructions", "Exit")));
    }

    public void setState(State state) {
        this.state = state;
    }
    public void saveState() { this.savedState = state; }
    public void restoreSavedState() { this.state = savedState; }

    private void start() throws IOException {
        int FPS = 20;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) { }
        }

        gui.close();
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game().start();
    }
}
