package roguelike.controller.game;

import roguelike.Game;
import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Coin;
import roguelike.model.game.elements.Monster;
import roguelike.model.game.elements.StrengthPotion;
import roguelike.model.game.structures.Room;

public class HeroController extends GameController {

    private long lastTimeUpdate;

    public HeroController(Arena arena) {
        super(arena);
        this.lastTimeUpdate = 0;
    }

    public void moveLeft() { moveHero(getModel().getHero().getPosition().getLeft()); }
    public void moveRight() {
        moveHero(getModel().getHero().getPosition().getRight());
    }
    public void moveUp() { moveHero(getModel().getHero().getPosition().getUp()); }
    public void moveDown() {
        moveHero(getModel().getHero().getPosition().getDown());
    }

    private void moveHero(Position position) {

        if (getModel().inInnerRoom(position) || getModel().inPath(position)) {
            getModel().getHero().setPosition(position);

            //Checks for possible attacks from monsters (monster.getStrength() = 0 if there's no monster in that position)
            Monster monster = getModel().getMonster(position);
            if (monster.getStrength() != 0) {
                getModel().getHero().decreaseHealth(monster.getStrength());
            }

            //Checks for possible coins to retrieve (coin.getBonus() = 0 if there's no coin in that position)
            Coin coin = getModel().retrieveCoin(position);
            if (coin.getBonus() != 0) {
                getModel().getHero().increaseHealth(coin.getBonus());
            }

            //Checks for possible strength potions to retrieve (sp.getStrengthBonus() = 0 if there's no strength potion in that position)
            StrengthPotion sp = getModel().retrieveStrengthPotion(position);
            if (sp.getStrengthBonus() != 0) {
                getModel().getHero().increaseStrength(sp.getStrengthBonus());
                getModel().getHero().increaseStrengthBonusTime(sp.getTimeBonus());
            }
        }
    }

    private void attack(long time) {

        Position attackPos1 = new Position(getModel().getHero().getX() + 1, getModel().getHero().getY());
        Position attackPos2 = new Position(getModel().getHero().getX() - 1, getModel().getHero().getY());
        Position attackPos3 = new Position(getModel().getHero().getX(), getModel().getHero().getY() + 1);
        Position attackPos4 = new Position(getModel().getHero().getX(), getModel().getHero().getY() - 1);

        //Searches for all 4 possible monsters to attack
        Monster m1 = getModel().getMonster(attackPos1);
        Monster m2 = getModel().getMonster(attackPos2);
        Monster m3 = getModel().getMonster(attackPos3);
        Monster m4 = getModel().getMonster(attackPos4);

        int heroStrength = getModel().getHero().getStrength();

        if (m1.getStrength() != 0) {
            getModel().attackMonster(m1.getPosition(), heroStrength, time);
        }
        if (m2.getStrength() != 0) {
            getModel().attackMonster(m2.getPosition(), heroStrength, time);
        }
        if (m3.getStrength() != 0) {
            getModel().attackMonster(m3.getPosition(), heroStrength, time);
        }
        if (m4.getStrength() != 0) {
            getModel().attackMonster(m4.getPosition(), heroStrength, time);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {

        //Update hero strength status
        if (time - lastTimeUpdate > 1000) {
            getModel().getHero().decreaseStrengthBonusTime();
            this.lastTimeUpdate = time;
        }

        if (getModel().getHero().getStrengthBonusTime() <= 0) {
            getModel().getHero().setStrength(getModel().getHero().getInitialStrength());
        }

        if (action == GUI.ACTION.UP) moveUp();
        if (action == GUI.ACTION.RIGHT) moveRight();
        if (action == GUI.ACTION.DOWN) moveDown();
        if (action == GUI.ACTION.LEFT) moveLeft();
        if (action == GUI.ACTION.ATTACK) attack(time);
    }
}
