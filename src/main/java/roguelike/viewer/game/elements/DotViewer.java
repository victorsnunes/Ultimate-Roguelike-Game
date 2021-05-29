package roguelike.viewer.game.elements;

import roguelike.gui.GUI;
import roguelike.model.game.elements.Dot;

public class DotViewer implements ElementViewer<Dot> {
    @Override
    public void draw(Dot dot, GUI gui) { gui.drawDot(dot.getPosition()); }
}
