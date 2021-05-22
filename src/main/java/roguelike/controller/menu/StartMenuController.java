package roguelike.controller.menu;

import roguelike.Game;
import roguelike.controller.Controller;
import roguelike.gui.GUI;
import roguelike.model.game.arena.RandomArenaBuilder;
import roguelike.model.menu.Menu;
import roguelike.model.menu.Window;
import roguelike.states.GameState;
import roguelike.states.LevelMenuState;
import roguelike.states.WindowState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class StartMenuController extends Controller<Menu> {
    public StartMenuController(Menu menu) {
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
                //Start Game
                if (getModel().isSelected(0)) game.setState(new LevelMenuState(new Menu("Choose the Level", Arrays.asList("Level 1", "Level 2", "Level 3", "Random Map"))));
                //Instructions
                if (getModel().isSelected(1)) game.setState(new WindowState(new Window("Instructions", Arrays.asList(
                        "Welcome to the Ultimate Rogue Game!",
                        "Use the arrows to move the hero",
                        "Press X to attack the monsters near you",
                        "Don't let the monsters touch you",
                        "Collect coins to regenerate your health",
                        "And collect potions 'S' to increase your strength",
                        "Reach the goal 'G' to complete the level"
                ))));
                //Exit
                if (getModel().isSelected(2)) game.setState(null);
                break;
        }
    }
}
