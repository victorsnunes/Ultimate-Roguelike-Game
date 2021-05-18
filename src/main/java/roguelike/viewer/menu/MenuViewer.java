package roguelike.viewer.menu;

import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.menu.Menu;
import roguelike.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {

        //Calculates initial Y for a centralized menu
        int y = (gui.getHeight() - (getModel().getNumberOptions() + 2)) / 2;

        gui.drawText(new Position(findCentralX(gui.getWidth(), getModel().getTitle()), y), getModel().getTitle(), "#FFFFFF");
        y += 3;

        for (int i = 0; i < getModel().getNumberOptions(); i++)
            gui.drawText(
                    new Position(findCentralX(gui.getWidth(), getModel().getOption(i)), y + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }

    //Calculates initial X for a centralized string in the screen
    private int findCentralX(int totalWidth, String string) {
        return (totalWidth - string.length()) / 2;
    }
}