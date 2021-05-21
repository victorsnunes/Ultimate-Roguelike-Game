package roguelike.controller.menu;

import roguelike.Game;
import roguelike.controller.Controller;
import roguelike.gui.GUI;
import roguelike.model.game.arena.ArenaBuilderLevel;
import roguelike.model.game.arena.RandomArenaBuilder;
import roguelike.model.menu.Menu;
import roguelike.states.GameState;
import roguelike.states.StartMenuState;

import java.io.IOException;
import java.util.Arrays;

public class LevelMenuController extends Controller<Menu> {
    public LevelMenuController(Menu menu) {
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
            case QUIT:
                game.setState(new StartMenuState(new Menu("Ultimate Rogue Game", Arrays.asList("Start", "Instructions", "Exit"))));
                break;
            case SELECT:
                //Level 1
                if (getModel().isSelected(0)) game.setState(new GameState(new ArenaBuilderLevel().getLevel1()));
                //Level 2
                if (getModel().isSelected(1)) game.setState(new GameState(new ArenaBuilderLevel().getLevel2()));
                //Level 3
                if (getModel().isSelected(2)) game.setState(new GameState(new ArenaBuilderLevel().getLevel3()));
                //Random Map
                if (getModel().isSelected(3)) game.setState(new GameState(new RandomArenaBuilder(3, 2).createArena()));
                break;
        }
    }
}
