package roguelike.viewer.game;

import roguelike.gui.GUI;
import roguelike.model.game.elements.Element;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui);
}
