package roguelike.viewer.menu;

import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.menu.Window;
import roguelike.viewer.Viewer;

public class WindowViewer extends Viewer<Window> {
    public WindowViewer(Window window) {
        super(window);
    }

    @Override
    public void drawElements(GUI gui) {

        //Calculates initial Y for a centralized menu
        int y = (gui.getHeight() - (getModel().getNumberOfLines() + 3)) / 2;

        gui.drawText(new Position(findCentralX(gui.getWidth(), getModel().getTitle()), y), getModel().getTitle(), "#FFFFFF");
        y += 3;

        for (int i = 0; i < getModel().getNumberOfLines(); i++)
            gui.drawText(
                    new Position(findCentralX(gui.getWidth(), getModel().getLineContent(i)), y + i),
                    getModel().getLineContent(i),
                    "#FFFFFF");
    }

    //Calculates initial X for a centralized string in the screen
    private int findCentralX(int totalWidth, String string) {
        return (totalWidth - string.length()) / 2;
    }
}
