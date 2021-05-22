package roguelike.viewer.game.elements;

import roguelike.gui.GUI;
import roguelike.model.game.elements.Monster;

public class MonsterViewer implements ElementViewer<Monster> {
    @Override
    public void draw(Monster monster, GUI gui) {
        if (monster.getTookDamage())
            gui.drawMonster(monster.getPosition(), "#C10F0F");
        else if (monster.getStrengthBonusTime() > 0) {
            gui.drawMonster(monster.getPosition(), "#E94B6A");
        } else {
            if (monster.getStrength() >= 6)
                gui.drawMonster(monster.getPosition(), "#F20000");
            else
                gui.drawMonster(monster.getPosition(), "#189D09");
        }
    }
}
