package roguelike.controller.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roguelike.model.Position;
import roguelike.model.game.arena.Arena;
import roguelike.model.game.elements.Hero;
import roguelike.model.game.structures.Room;

class HeroControllerTest {

    private HeroController heroController;

    @BeforeEach
    public void createArena(){
        Arena arena = new Arena(60,30);
        arena.addRoom(new Room(5, 5, 20, 10));
        arena.setHero(new Hero(new Position(15, 10)));

        heroController = new HeroController(arena);
    }

    //The 4 following tests must success because the hero is doing valid moves
    @Test
    public void moveLeftTest() {

        Position positionExpected = new Position(heroController.getModel().getHero().getPosition().getX() - 1,
                                                 heroController.getModel().getHero().getPosition().getY());

        heroController.moveLeft();
        Assertions.assertEquals(positionExpected, heroController.getModel().getHero().getPosition());

        //Reaching the left wall
        for (int i = 0; i < 50; i++)
            heroController.moveLeft();

        Position previousPosition = heroController.getModel().getHero().getPosition();
        heroController.moveLeft();

        //Checking if hero stays in the same position, and that position is in front of the left wall of the room
        Assertions.assertEquals(previousPosition, heroController.getModel().getHero().getPosition());
        Assertions.assertEquals(new Position (6, 10), heroController.getModel().getHero().getPosition());
    }
    @Test
    public void moveRightTest() {

        Position positionExpected = new Position(heroController.getModel().getHero().getPosition().getX() + 1,
                                                    heroController.getModel().getHero().getPosition().getY());

        heroController.moveRight();
        Assertions.assertEquals(positionExpected, heroController.getModel().getHero().getPosition());

        //Reaching the right wall
        for (int i = 0; i < 50; i++)
            heroController.moveRight();

        Position previousPosition = heroController.getModel().getHero().getPosition();
        heroController.moveRight();

        //Checking if hero stays in the same position, and that position is in front of the right wall of the room
        Assertions.assertEquals(previousPosition, heroController.getModel().getHero().getPosition());
        Assertions.assertEquals(new Position (24, 10), heroController.getModel().getHero().getPosition());
    }
    @Test
    public void moveUpTest() {
        Position positionExpected = new Position(heroController.getModel().getHero().getPosition().getX(),
                                                    heroController.getModel().getHero().getPosition().getY() - 1);

        heroController.moveUp();
        Assertions.assertEquals(positionExpected, heroController.getModel().getHero().getPosition());

        //Reaching the top wall
        for (int i = 0; i < 50; i++)
            heroController.moveUp();

        Position previousPosition = heroController.getModel().getHero().getPosition();
        heroController.moveUp();

        //Checking if hero stays in the same position, and that position is in front of the top wall of the room
        Assertions.assertEquals(previousPosition, heroController.getModel().getHero().getPosition());
        Assertions.assertEquals(new Position (15, 6), heroController.getModel().getHero().getPosition());
    }
    @Test
    public void moveDownTest() {
        Position positionExpected = new Position(heroController.getModel().getHero().getPosition().getX(),
                                              heroController.getModel().getHero().getPosition().getY() + 1);

        heroController.moveDown();
        Assertions.assertEquals(positionExpected, heroController.getModel().getHero().getPosition());

        //Reaching the bottom wall
        for (int i = 0; i < 50; i++)
            heroController.moveDown();

        Position previousPosition = heroController.getModel().getHero().getPosition();
        heroController.moveDown();

        //Checking if hero stays in the same position, and that position is in front of the bottom wall of the room
        Assertions.assertEquals(previousPosition, heroController.getModel().getHero().getPosition());
        Assertions.assertEquals(new Position (15, 14), heroController.getModel().getHero().getPosition());
    }
}