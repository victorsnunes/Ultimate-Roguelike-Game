package roguelikeTests.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roguelike.model.menu.Menu;

import java.util.Arrays;

class MenuTest {

    Menu menu;

    @BeforeEach
    public void createMenu() {
        menu = new Menu("Just a regular title", Arrays.asList(
                            "Option 1",
                            "Option 2",
                            "Option 3",
                            "Option 4",
                            "Option 5"
        ));
    }

    @Test
    public void titleTest() {
        Assertions.assertEquals("Just a regular title", menu.getTitle());
    }

    @Test
    public void nextOptionTest() {
        Assertions.assertTrue(menu.isSelected(0));

        menu.nextOption();
        Assertions.assertTrue(menu.isSelected(1));
        menu.nextOption();
        Assertions.assertTrue(menu.isSelected(2));
        menu.nextOption();
        Assertions.assertTrue(menu.isSelected(3));
        menu.nextOption();
        Assertions.assertTrue(menu.isSelected(4));
        menu.nextOption();
        Assertions.assertTrue(menu.isSelected(0));
    }

    @Test
    public void previousOptionTest() {
        Assertions.assertTrue(menu.isSelected(0));

        menu.previousOption();
        Assertions.assertTrue(menu.isSelected(4));
        menu.previousOption();
        Assertions.assertTrue(menu.isSelected(3));
        menu.previousOption();
        Assertions.assertTrue(menu.isSelected(2));
        menu.previousOption();
        Assertions.assertTrue(menu.isSelected(1));
        menu.previousOption();
        Assertions.assertTrue(menu.isSelected(0));
    }
}