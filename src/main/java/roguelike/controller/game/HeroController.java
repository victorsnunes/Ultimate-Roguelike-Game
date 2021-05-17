package roguelike.controller.game;

import roguelike.Game;
import roguelike.Room;
import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Monster;

public class HeroController extends GameController {
    public HeroController(Arena arena) {
        super(arena);
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

        if (getModel().inRoom(position) || getModel().inPath(position)) {
            getModel().getHero().setPosition(position);

            //Checks for possible attacks from monsters (monster.getStrength() = 0 if there's no monster in that position)
            Monster monster = getModel().getMonster(position);
            getModel().getHero().decreaseHealth(monster.getStrength());
        }
    }

    public void attack() {

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

        m1.decreaseHealth(heroStrength);
        m2.decreaseHealth(heroStrength);
        m3.decreaseHealth(heroStrength);
        m4.decreaseHealth(heroStrength);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (action == GUI.ACTION.UP) moveUp();
        if (action == GUI.ACTION.RIGHT) moveRight();
        if (action == GUI.ACTION.DOWN) moveDown();
        if (action == GUI.ACTION.LEFT) moveLeft();
        if (action == GUI.ACTION.ATTACK) attack();
    }
}
