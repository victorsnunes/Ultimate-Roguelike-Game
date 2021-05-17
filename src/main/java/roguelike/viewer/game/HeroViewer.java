package roguelike.viewer.game;

import roguelike.gui.GUI;
import roguelike.model.game.elements.Hero;

public class HeroViewer implements ElementViewer<Hero> {
    @Override
    public void draw(Hero hero, GUI gui) { gui.drawHero(hero.getPosition()); }
}