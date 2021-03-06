package roguelikeTests.controller.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import roguelike.Game;
import roguelike.controller.menu.PauseMenuController;
import roguelike.gui.GUI;
import roguelike.model.menu.Menu;
import roguelike.states.StartMenuState;

import java.io.IOException;
import java.util.Arrays;

class PauseMenuControllerTest {
    private Menu menu;
    private PauseMenuController controller;
    private Game gameStub;
    long time = 0;

    @BeforeEach
    void setup(){
        String title = "Paused";
        this.menu = new Menu(title, Arrays.asList("Resume", "Quit"));
        controller = new PauseMenuController(this.menu);

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
        Assertions.assertTrue(menu.isSelected(1));

        action = GUI.ACTION.UP;
        controller.step(gameStub, action, time);
        Assertions.assertTrue(menu.isSelected(0));
    }

    @Test
    void resumeTest() throws IOException {
        GUI.ACTION action = GUI.ACTION.SELECT;
        controller.step(gameStub, action, time);

        Mockito.verify(gameStub, Mockito.times(1)).restoreSavedState();
    }

    @Test
    void quitTest() throws IOException {

        GUI.ACTION action = GUI.ACTION.DOWN;
        controller.step(gameStub, action, time);
        action = GUI.ACTION.SELECT;
        controller.step(gameStub, action, time);

        Mockito.verify(gameStub, Mockito.times(1)).setState(new StartMenuState(Mockito.any()));
    }
}