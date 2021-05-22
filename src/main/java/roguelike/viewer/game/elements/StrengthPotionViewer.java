package roguelike.viewer.game.elements;

import roguelike.gui.GUI;
import roguelike.model.game.elements.StrengthPotion;

public class StrengthPotionViewer implements ElementViewer<StrengthPotion> {
    @Override
    public void draw(StrengthPotion strengthPotion, GUI gui) { gui.drawStrengthPotion(strengthPotion.getPosition()); }
}
