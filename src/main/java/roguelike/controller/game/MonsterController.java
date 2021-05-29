package roguelike.controller.game;

import roguelike.Game;
import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Coin;
import roguelike.model.game.elements.Monster;
import roguelike.model.game.elements.StrengthPotion;
import roguelike.model.game.structures.Room;

import java.io.IOException;

public class MonsterController extends GameController {
    private long lastMovement;
    private long lastTimeUpdate;

    public MonsterController(Arena arena) {
        super(arena);

        this.lastMovement = 0;
        this.lastTimeUpdate = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

        //Update monsters strength status
        if (time - lastTimeUpdate > 1000) {
            for (Room room : getModel().getRooms()) {
                for (Monster monster : room.getMonsters()) {
                    monster.decreaseStrengthBonusTime();
                    if (monster.getStrengthBonusTime() <= 0)
                        monster.setStrength(monster.getInitialStrength());
                }
            }
            this.lastTimeUpdate = time;
        }

        //Update monsters movement
        if (time - lastMovement > 500) {
            for (Room room : getModel().getRooms()) {
                if (room.getIsActive()) {
                    for (Monster monster : room.getMonsters()) {
                        moveMonster(monster, monster.getPosition().follow(getModel().getHero().getPosition()));

                        //Monsters also can get coins and strength potion
                        //Checks for possible strength potions to retrieve (sp = null if there's no strength potion in that position)
                        StrengthPotion sp = getModel().retrieveStrengthPotion(monster.getPosition());
                        if (sp != null) {
                            monster.increaseStrength(sp.getStrengthBonus());
                            monster.increaseStrengthBonusTime(sp.getTimeBonus());
                        }

                        //Checks for possible coins to retrieve (coin = null if there's no coin in that position)
                        Coin coin = getModel().retrieveCoin(monster.getPosition());
                        if (coin != null) {
                            monster.increaseHealth(coin.getBonus());
                        }

                    }
                }
            }
            this.lastMovement = time;
        }

        for (Room room : getModel().getRooms()) {
            if (room.getIsActive()) {
                for (Monster monster : room.getMonsters())
                     if (time - monster.getTookDamageTime() > 150)
                         monster.setTookDamage(false);
            }
        }
    }

    private void moveMonster(Monster monster, Position position) {
        if (getModel().inInnerRoom(position)) {
            monster.setPosition(position);
            if (getModel().getHero().getPosition().equals(position))
                getModel().getHero().decreaseHealth(monster.getStrength());
        }
    }
}
