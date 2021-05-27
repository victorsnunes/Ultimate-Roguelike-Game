package roguelike.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class WindowTest {

    Window window;

    @BeforeEach
    public void createWindow() { window = new Window("This is the title", Arrays.asList(
                                           "Line 1",
                                           "Line 2",
                                           "Line 3",
                                           "Line 4"
    )); }

    @Test
    public void titleTest() { Assertions.assertEquals("This is the title", window.getTitle()); }

    @Test
    public void contentTest() {
        Assertions.assertEquals(Arrays.asList("Line 1", "Line 2", "Line 3", "Line 4"), window.getContent());

        Assertions.assertEquals(4, window.getNumberOfLines());

        Assertions.assertEquals("Line 1", window.getLineContent(0));
        Assertions.assertEquals("Line 2", window.getLineContent(1));
        Assertions.assertEquals("Line 3", window.getLineContent(2));
        Assertions.assertEquals("Line 4", window.getLineContent(3));
    }
}