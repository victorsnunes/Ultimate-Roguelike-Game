package roguelikeTests.controller.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import roguelike.Game;
import roguelike.controller.game.MonsterController;
import roguelike.gui.GUI;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Coin;
import roguelike.model.game.elements.Hero;
import roguelike.model.game.elements.Monster;
import roguelike.model.game.elements.StrengthPotion;
import roguelike.model.game.structures.Room;

import java.io.IOException;
import java.util.Arrays;

class MonsterControllerTest {

    Game game;
    Arena arena;
    Monster monster;
    MonsterController monsterController;

    @BeforeEach
    public void createMonsterController() {
        arena = Mockito.mock(Arena.class);
        Room room = Mockito.mock(Room.class);
        game = Mockito.mock(Game.class);
        Coin coin = Mockito.mock(Coin.class);
        StrengthPotion sp = Mockito.mock(StrengthPotion.class);
        Position position = Mockito.mock(Position.class);

        monster = new Monster(position, 6, 7);

        Mockito.when(position.getX()).thenReturn(10);
        Mockito.when(position.getY()).thenReturn(10);

        Mockito.when(arena.getRooms()).thenReturn(Arrays.asList(room));
        Mockito.when(arena.inInnerRoom(Mockito.any())).thenReturn(true);

        Mockito.when(room.getMonsters()).thenReturn(Arrays.asList(monster));
        Mockito.when(room.getCoins()).thenReturn(Arrays.asList(coin));
        Mockito.when(room.getStrengthPotions()).thenReturn(Arrays.asList(sp));
        Mockito.when(room.getIsActive()).thenReturn(true);

        Mockito.when(coin.getPosition()).thenReturn(new Position(11, 10));
        Mockito.when(coin.getBonus()).thenReturn(2);

        Mockito.when(sp.getPosition()).thenReturn(new Position(10, 9));
        Mockito.when(sp.getStrengthBonus()).thenReturn(7);
        Mockito.when(sp.getTimeBonus()).thenReturn(20);

        Mockito.when(arena.retrieveStrengthPotion(new Position(10, 9))).thenReturn(sp);
        Mockito.when(arena.retrieveCoin(new Position(11, 10))).thenReturn(coin);

        Mockito.when(position.follow(new Position(15, 10))).thenReturn(new Position(11, 10));
        Mockito.when(position.follow(new Position(10, 7))).thenReturn(new Position(10, 9));

        monsterController = new MonsterController(arena);
    }

    @Test
    public void monsterRetrieveCoin() throws IOException {
        Hero hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getPosition()).thenReturn(new Position(15, 10));
        Mockito.when(arena.getHero()).thenReturn(hero);

        monsterController.step(game, GUI.ACTION.NONE, 600);

        Assertions.assertEquals(8, monster.getHealth());
    }

    @Test
    public void monsterRetrieveStrengthPotion() throws IOException {
        Hero hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getPosition()).thenReturn(new Position(10, 7));
        Mockito.when(arena.getHero()).thenReturn(hero);

        monsterController.step(game, GUI.ACTION.NONE, 600);

        Assertions.assertEquals(14, monster.getStrength());
        Assertions.assertEquals(20, monster.getStrengthBonusTime());
    }
}