package roguelikeTests.controller.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import roguelike.Game;
import roguelike.controller.menu.StartMenuController;
import roguelike.gui.GUI;
import roguelike.model.menu.Menu;
import roguelike.states.LevelMenuState;
import roguelike.states.WindowState;

import java.io.IOException;
import java.util.Arrays;

class StartMenuControllerTest {

    private Menu menu;
    private StartMenuController controller;
    private Game gameStub;
    private long time = 0;

    @BeforeEach
    void setup(){
        String title = "Ultimate Roguelike Game";
        this.menu = new Menu(title, Arrays.asList("Start", "Instructions", "Exit"));
        controller = new StartMenuController(this.menu);

        this.gameStub = Mockito.mock(Game.class);
    }

    @Test
    void arrowsTest() throws IOException {

        GUI.ACTION action = GUI.ACTION.DOWN;
        controller.step(gameStub, action, time);
        Assertions.assertTrue(menu.isSelected(1));

        action = GUI.ACTION.UP;
        controller.step(gameStub, action, time);
        Assertions.assertTrue(menu.isSelected(0));

        action = GUI.ACTION.UP;
        controller.step(gameStub, action, time);
        Assertions.assertTrue(menu.isSelected(2));

        action = GUI.ACTION.DOWN;
        controller.step(gameStub, action, time);
        Assertions.assertTrue(menu.isSelected(0));
    }

    @Test
    void startTest() throws IOException {
        GUI.ACTION action = GUI.ACTION.SELECT;
        controller.step(gameStub, action, time);

        Mockito.verify(gameStub, Mockito.times(1)).setState(
                new LevelMenuState(Mockito.any())
        );
    }

    @Test
    void instructionsTest() throws IOException {
        GUI.ACTION action = GUI.ACTION.DOWN;
        controller.step(gameStub, action, time);

        action = GUI.ACTION.SELECT;
        controller.step(gameStub, action, time);

        Mockito.verify(gameStub, Mockito.times(1)).setState(
                new WindowState(Mockito.any())
        );
    }

    @Test
    void exitTest() throws IOException {
        GUI.ACTION action = GUI.ACTION.DOWN;
        controller.step(gameStub, action, time);
        controller.step(gameStub, action, time);

        action = GUI.ACTION.SELECT;
        controller.step(gameStub, action, time);

        Mockito.verify(gameStub, Mockito.times(1)).setState(null);
    }
}
