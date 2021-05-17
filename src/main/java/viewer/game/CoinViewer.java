package viewer.game;

import gui.GUI;
import model.Position;
import model.game.elements.Coin;

public class CoinViewer implements ElementViewer<Coin> {
    @Override
    public void draw(Coin coin, GUI gui) { gui.drawCoin(coin.getPosition()); }
}
