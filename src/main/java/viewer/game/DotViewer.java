package viewer.game;

import gui.GUI;
import model.game.elements.Dot;
import model.game.elements.Hero;

public class DotViewer implements ElementViewer<Dot> {
    @Override
    public void draw(Dot dot, GUI gui) { gui.drawDot(dot.getPosition()); }
}
