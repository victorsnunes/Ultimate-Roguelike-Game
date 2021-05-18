package roguelike.controller.menu;

import roguelike.Game;
import roguelike.controller.Controller;
import roguelike.gui.GUI;
import roguelike.model.game.arena.Arena;
import roguelike.model.menu.Menu;
import roguelike.states.GameState;
import roguelike.states.StartMenuState;

import java.io.IOException;
import java.util.Arrays;

public class PauseMenuController extends Controller<Menu> {

    public PauseMenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousOption();
                break;
            case DOWN:
                getModel().nextOption();
                break;
            case SELECT:
                //if (getModel().isSelectedStart()) game.setState(new GameState(new LoaderArenaBuilder(1).createArena()));
                if (getModel().isSelected(0)) game.restoreSavedState();
                if (getModel().isSelected(1)) game.setState(new StartMenuState(new Menu("Ultimate Rogue Game", Arrays.asList("Start", "Instructions", "Exit"))));
                break;
            case QUIT:
                game.restoreSavedState();
                break;
        }
    }
}
