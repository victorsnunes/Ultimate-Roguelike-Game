package roguelikeTests.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import roguelike.Game;
import roguelike.controller.game.ArenaController;
import roguelike.gui.GUI;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Hero;
import roguelike.model.menu.Window;
import roguelike.states.PauseMenuState;
import roguelike.states.WindowState;

import java.io.IOException;

class ArenaControllerTest {

    private ArenaController arenaController;
    private Arena arena;
    private Game game;
    private Hero hero;
    GUI.ACTION action;
    long time = 0;

    @BeforeEach
    void setup(){

        arena = Mockito.mock(Arena.class);
        game = Mockito.mock(Game.class);
        hero = Mockito.mock(Hero.class);

        Mockito.when(arena.getHero()).thenReturn(hero);

        arenaController = new ArenaController(arena);

    }

    @Test
    void pauseGameTest() throws IOException {

        Mockito.when(arena.getTime()).thenReturn(10);
        Mockito.when(hero.getHealth()).thenReturn(10);
        Mockito.when(arena.isGoal(Mockito.any())).thenReturn(false);

        action = GUI.ACTION.QUIT;
        arenaController.step(game, action, time);

        Mockito.verify(game, Mockito.times(1)).saveState();
        Mockito.verify(game, Mockito.times(1)).setState(new PauseMenuState(Mockito.any()));
    }

    @Test
    void timeOutTest() throws IOException {

        Mockito.when(arena.getTime()).thenReturn(0);
        Mockito.when(hero.getHealth()).thenReturn(10);
        Mockito.when(arena.isGoal(Mockito.any())).thenReturn(false);

        action = GUI.ACTION.NONE;
        arenaController.step(game, action, time);

        Mockito.verify(game, Mockito.times(1)).setState(
                new WindowState(new Window("Time's Up", Mockito.any()))
        );
    }

    @Test
    void heroDiedTest() throws IOException {

        Mockito.when(arena.getTime()).thenReturn(10);
        Mockito.when(hero.getHealth()).thenReturn(0);
        Mockito.when(arena.isGoal(Mockito.any())).thenReturn(false);

        action = GUI.ACTION.NONE;
        arenaController.step(game, action, time);

        Mockito.verify(game, Mockito.times(1)).setState(
                new WindowState(new Window("You Died", Mockito.any()))
        );
    }

    @Test
    void heroWonTest() throws IOException {

        Mockito.when(arena.getTime()).thenReturn(10);
        Mockito.when(hero.getHealth()).thenReturn(10);
        Mockito.when(arena.isGoal(Mockito.any())).thenReturn(true);

        action = GUI.ACTION.NONE;
        arenaController.step(game, action, time);

        Mockito.verify(game, Mockito.times(1)).setState(
                new WindowState(new Window("You Won", Mockito.any()))
        );
    }

}