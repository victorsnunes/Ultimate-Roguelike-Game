package roguelike.controller.game;

import roguelike.Game;
import roguelike.gui.GUI;
import roguelike.model.game.arena.Arena;
import roguelike.model.menu.Menu;
import roguelike.model.menu.Window;
import roguelike.states.PauseMenuState;
import roguelike.states.StartMenuState;
import roguelike.states.WindowState;

import java.io.IOException;
import java.util.Arrays;

public class ArenaController extends GameController {
    private final HeroController heroController;
    private final MonsterController monsterController;

    public ArenaController(Arena arena) {
        super(arena);

        this.heroController = new HeroController(arena);
        this.monsterController = new MonsterController(arena);
    }

    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT) {
            game.saveState(); //Saving previous state, in case of resuming the game after the pause
            game.setState(new PauseMenuState(new Menu("Game Paused", Arrays.asList("Resume", "Quit"))));
        }
        else if (getModel().getHero().getHealth() <= 0)
            game.setState(new WindowState(new Window("You Died", Arrays.asList(
                    "Oh no! Looks like a monster killed you",
                    "Better luck next time, young hero..."
            ))));
        else {
            heroController.step(game, action, time);
            monsterController.step(game, action, time);
        }
    }
}