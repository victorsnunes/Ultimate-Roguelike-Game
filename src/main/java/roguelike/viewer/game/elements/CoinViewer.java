package roguelike.viewer.game.elements;

import roguelike.gui.GUI;
import roguelike.model.game.elements.Coin;

public class CoinViewer implements ElementViewer<Coin> {
    @Override
    public void draw(Coin coin, GUI gui) { gui.drawCoin(coin.getPosition()); }
}
