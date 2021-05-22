package roguelike.viewer.game.elements;

import roguelike.gui.GUI;
import roguelike.model.game.elements.Hero;

public class HeroViewer implements ElementViewer<Hero> {
    @Override
    public void draw(Hero hero, GUI gui) {
        if (hero.getStrengthBonusTime() > 0)
            gui.drawHero(hero.getPosition(), "#E94B6A");
        else
            gui.drawHero(hero.getPosition(), "#4163EC");
    }
}