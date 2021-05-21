package roguelike.controller.game;

import roguelike.Game;
import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Monster;
import roguelike.model.game.structures.Room;

import java.io.IOException;

public class MonsterController extends GameController {
    private long lastMovement;

    public MonsterController(Arena arena) {
        super(arena);

        this.lastMovement = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastMovement > 500) {
            for (Room room : getModel().getRooms()) {
                if (room.getIsActive()) {
                    for (Monster monster : room.getMonsters())
                        moveMonster(monster, monster.getPosition().follow(getModel().getHero().getPosition()));
                }
            }
            this.lastMovement = time;
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
