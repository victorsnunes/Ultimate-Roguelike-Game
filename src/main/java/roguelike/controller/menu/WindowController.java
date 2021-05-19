package roguelike.controller.menu;

import roguelike.Game;
import roguelike.controller.Controller;
import roguelike.gui.GUI;
import roguelike.model.menu.Menu;
import roguelike.model.menu.Window;
import roguelike.states.StartMenuState;

import java.io.IOException;
import java.util.Arrays;

public class WindowController extends Controller<Window> {
    public WindowController(Window window) { super(window); }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case SELECT:
            case QUIT:
                game.setState(new StartMenuState(new Menu("Ultimate Rogue Game", Arrays.asList("Start", "Instructions", "Exit"))));
                break;
        }
    }
}
