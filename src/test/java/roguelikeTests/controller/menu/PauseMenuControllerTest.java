package roguelikeTests.controller.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import roguelike.Game;
import roguelike.controller.menu.PauseMenuController;
import roguelike.gui.GUI;
import roguelike.model.menu.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PauseMenuControllerTest {
    private Menu menu;
    private PauseMenuController controller;
    private Game gameStub;
    @BeforeEach
    void setup(){
        String title = "title";
        this.menu = new Menu(title, Arrays.asList("option1", "option2"));
        controller = new PauseMenuController(this.menu);

        this.gameStub = Mockito.mock(Game.class);
    }

    @Test
    void step() throws IOException {
        long time = 0;

        int i = 0;
        System.out.println("Testing Down");
        GUI.ACTION action = GUI.ACTION.DOWN;
        controller.step(gameStub, action, time);
        i = 1;
        Assertions.assertTrue(menu.isSelected(i));

        System.out.println("Testing UP");
        action = GUI.ACTION.UP;
        controller.step(gameStub, action, time);
        i = 0;
        Assertions.assertTrue(menu.isSelected(i));

        System.out.println("Testing UP");
        action = GUI.ACTION.UP;
        controller.step(gameStub, action, time);
        i = 1;
        Assertions.assertTrue(menu.isSelected(i));

        System.out.println("Testing UP");
        action = GUI.ACTION.UP;
        controller.step(gameStub, action, time);
        i = 0;
        Assertions.assertTrue(menu.isSelected(i));

        System.out.println("Testing Select");
        action = GUI.ACTION.SELECT;
        controller.step(gameStub, action, time);

        Mockito.verify(gameStub, Mockito.times(1)).restoreSavedState();

    }
}