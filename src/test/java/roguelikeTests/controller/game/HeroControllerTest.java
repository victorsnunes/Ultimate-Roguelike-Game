package roguelikeTests.controller.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import roguelike.controller.game.HeroController;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Coin;
import roguelike.model.game.elements.Hero;
import roguelike.model.game.elements.Monster;
import roguelike.model.game.elements.StrengthPotion;

class HeroControllerTest {

    private HeroController heroController;
    private Arena arena;

    @BeforeEach
    public void createArena() {
        //Arena arena = new Arena(60,30);
        //arena.addRoom(new Room(5, 5, 20, 10));

        arena = Mockito.mock(Arena.class);

        Hero hero = new Hero(new Position(15, 10));

        //Going left
        Mockito.when(arena.inInnerRoom(new Position(14, 10))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(13, 10))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(12, 10))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(11, 10))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(10, 10))).thenReturn(false);

        //Going up
        Mockito.when(arena.inInnerRoom(new Position(15, 9))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(15, 8))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(15, 7))).thenReturn(false);

        //Going down
        Mockito.when(arena.inInnerRoom(new Position(15, 11))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(15, 12))).thenReturn(false);

        //Going right
        Mockito.when(arena.inInnerRoom(new Position(16, 10))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(17, 10))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(18, 10))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(19, 10))).thenReturn(true);
        Mockito.when(arena.inInnerRoom(new Position(20, 10))).thenReturn(false);

        Mockito.when(arena.getHero()).thenReturn(hero);

        heroController = new HeroController(arena);
    }

    //The 4 following tests must success because the hero is doing valid moves
    @Test
    public void moveLeftTest() {

        Position positionExpected = new Position(14, 10);

        heroController.moveLeft();
        Assertions.assertEquals(positionExpected, heroController.getModel().getHero().getPosition());

        //Reaching the left wall
        for (int i = 0; i < 50; i++)
            heroController.moveLeft();

        Position previousPosition = heroController.getModel().getHero().getPosition();
        heroController.moveLeft();

        //Checking if hero stays in the same position, and that position is in front of the left wall of the room
        Assertions.assertEquals(previousPosition, heroController.getModel().getHero().getPosition());
        Assertions.assertEquals(new Position (11, 10), heroController.getModel().getHero().getPosition());
    }
    @Test
    public void moveRightTest() {

        Position positionExpected = new Position(16, 10);

        heroController.moveRight();
        Assertions.assertEquals(positionExpected, heroController.getModel().getHero().getPosition());

        //Reaching the right wall
        for (int i = 0; i < 50; i++)
            heroController.moveRight();

        Position previousPosition = heroController.getModel().getHero().getPosition();
        heroController.moveRight();

        //Checking if hero stays in the same position, and that position is in front of the right wall of the room
        Assertions.assertEquals(previousPosition, heroController.getModel().getHero().getPosition());
        Assertions.assertEquals(new Position (19, 10), heroController.getModel().getHero().getPosition());
    }
    @Test
    public void moveUpTest() {
        Position positionExpected = new Position(15, 9);

        heroController.moveUp();
        Assertions.assertEquals(positionExpected, heroController.getModel().getHero().getPosition());

        //Reaching the top wall
        for (int i = 0; i < 50; i++)
            heroController.moveUp();

        Position previousPosition = heroController.getModel().getHero().getPosition();
        heroController.moveUp();

        //Checking if hero stays in the same position, and that position is in front of the top wall of the room
        Assertions.assertEquals(previousPosition, heroController.getModel().getHero().getPosition());
        Assertions.assertEquals(new Position (15, 8), heroController.getModel().getHero().getPosition());
    }
    @Test
    public void moveDownTest() {
        Position positionExpected = new Position(15, 11);

        heroController.moveDown();
        Assertions.assertEquals(positionExpected, heroController.getModel().getHero().getPosition());

        //Reaching the bottom wall
        for (int i = 0; i < 50; i++)
            heroController.moveDown();

        Position previousPosition = heroController.getModel().getHero().getPosition();
        heroController.moveDown();

        //Checking if hero stays in the same position, and that position is in front of the bottom wall of the room
        Assertions.assertEquals(previousPosition, heroController.getModel().getHero().getPosition());
        Assertions.assertEquals(new Position (15, 11), heroController.getModel().getHero().getPosition());
    }

    @Test
    public void runIntoAMonsterTest() {
        Monster monster = Mockito.mock(Monster.class);
        Mockito.when(monster.getStrength()).thenReturn(4);

        Mockito.when(arena.getMonster(new Position(16, 10))).thenReturn(monster);

        heroController.moveRight();
        Assertions.assertEquals(1, heroController.getModel().getHero().getHealth());
    }

    @Test
    public void retrieveCoinTest() {
        Coin coin = Mockito.mock(Coin.class);
        Mockito.when(coin.getBonus()).thenReturn(1);

        Mockito.when(arena.retrieveCoin(new Position(14, 10))).thenReturn(coin);

        heroController.moveLeft();
        Assertions.assertEquals(6, heroController.getModel().getHero().getHealth());
    }

    @Test
    public void retrieveStrengthPotionTest() {
        StrengthPotion sp = Mockito.mock(StrengthPotion.class);
        Mockito.when(sp.getStrengthBonus()).thenReturn(5);

        Mockito.when(arena.retrieveStrengthPotion(new Position(15, 11))).thenReturn(sp);

        heroController.moveDown();
        Assertions.assertEquals(8, heroController.getModel().getHero().getStrength());
    }
}