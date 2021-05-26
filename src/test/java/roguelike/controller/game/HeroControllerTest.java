package roguelike.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.arena.ArenaBuilderLevel;

class HeroControllerTest {

    private Arena arena;

    @BeforeEach
    void createArena(){
        this.arena = new ArenaBuilderLevel().getLevel2();
    }

    @Test
    void moveLeftTest() {

    }

    @Test
    void moveRightTest() {

    }

    @Test
    public void moveUpTest() {

    }

    @Test
    public void moveDownTest() {


    }

}