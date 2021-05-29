package roguelikeTests.model.game.structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roguelike.model.Position;
import roguelike.model.game.elements.*;
import roguelike.model.game.structures.Room;

class RoomTest {

    Room room;

    @BeforeEach
    public void createRoom() {
        room = new Room(5, 5, 30, 20);
    }

    @Test
    public void checkWalls() {
        Assertions.assertTrue( room.getWalls().contains(new HorizontalWall(5, 5)) );
        Assertions.assertTrue( room.getWalls().contains(new HorizontalWall(25, 5)) );

        Assertions.assertTrue( room.getWalls().contains(new VerticalWall(5, 15)) );
        Assertions.assertTrue( room.getWalls().contains(new VerticalWall(35, 15)) );

        Assertions.assertFalse( room.getWalls().contains(new VerticalWall(10, 15)) );
        Assertions.assertFalse( room.getWalls().contains(new HorizontalWall(29, 24)) );

        Assertions.assertTrue( room.getWalls().contains(new HorizontalWall(7, 25)) );
        Assertions.assertTrue( room.getWalls().contains(new HorizontalWall(27, 25)) );
    }

    @Test
    public void checkDots() {
        Assertions.assertTrue( room.getDots().contains(new Dot(6, 6)) );
        Assertions.assertTrue( room.getDots().contains(new Dot(27, 15)) );
        Assertions.assertTrue( room.getDots().contains(new Dot(34, 24)) );
    }

    @Test
    public void checkAddMonster() {

        Monster monster1 = new Monster(new Position(6, 6));
        Monster monster2 = new Monster(new Position(10, 15));
        Monster monster3 = new Monster(new Position(40, 15));

        room.addMonster(monster1);
        room.addMonster(monster2);
        room.addMonster(monster3);

        Assertions.assertTrue(room.getMonsters().contains(monster1));
        Assertions.assertTrue(room.getMonsters().contains(monster2));
        Assertions.assertFalse(room.getMonsters().contains(monster3));
    }

    @Test
    public void checkAddCoin() {
        Coin coin1 = new Coin(new Position(6, 6));
        Coin coin2 = new Coin(new Position(10, 15));
        Coin coin3 = new Coin(new Position(40, 15));

        room.addCoin(coin1);
        room.addCoin(coin2);
        room.addCoin(coin3);

        Assertions.assertTrue(room.getCoins().contains(coin1));
        Assertions.assertTrue(room.getCoins().contains(coin2));
        Assertions.assertFalse(room.getCoins().contains(coin3));
    }

    @Test
    public void checkAddStrengthPotion() {
        StrengthPotion sp1 = new StrengthPotion(new Position(6, 6));
        StrengthPotion sp2 = new StrengthPotion(new Position(10, 15));
        StrengthPotion sp3 = new StrengthPotion(new Position(40, 15));

        room.addStrengthPotion(sp1);
        room.addStrengthPotion(sp2);
        room.addStrengthPotion(sp3);

        Assertions.assertTrue(room.getStrengthPotions().contains(sp1));
        Assertions.assertTrue(room.getStrengthPotions().contains(sp2));
        Assertions.assertFalse(room.getStrengthPotions().contains(sp3));
    }

    @Test
    public void inRoomTest() {
        Assertions.assertTrue(room.inRoom(new Position(5, 5)));
        Assertions.assertTrue(room.inRoom(new Position(10, 5)));
        Assertions.assertTrue(room.inRoom(new Position(10, 23)));
        Assertions.assertTrue(room.inRoom(new Position(27, 25)));
        Assertions.assertTrue(room.inRoom(new Position(35, 13)));

        Assertions.assertFalse(room.inRoom(new Position(40, 14)));
        Assertions.assertFalse(room.inRoom(new Position(24, 36)));
    }

    @Test
    public void inInnerRoomTest() {
        Assertions.assertTrue(room.inInnerRoom(new Position(10, 23)));
        Assertions.assertTrue(room.inInnerRoom(new Position(15, 8)));
        Assertions.assertTrue(room.inInnerRoom(new Position(26, 11)));

        Assertions.assertFalse(room.inInnerRoom(new Position(5, 5)));
        Assertions.assertFalse(room.inInnerRoom(new Position(35, 13)));
        Assertions.assertFalse(room.inInnerRoom(new Position(27, 25)));
        Assertions.assertFalse(room.inInnerRoom(new Position(40, 14)));
        Assertions.assertFalse(room.inInnerRoom(new Position(24, 36)));
    }

    @Test
    public void updateVisibilityTest() {
        Assertions.assertFalse(room.getIsVisible());
        Assertions.assertFalse(room.getIsActive());

        room.updateVisibility(new Position(10, 10));

        Assertions.assertTrue(room.getIsVisible());
        Assertions.assertTrue(room.getIsActive());

        room.updateVisibility(new Position(45, 20));

        Assertions.assertTrue(room.getIsVisible());
        Assertions.assertFalse(room.getIsActive());
    }

    @Test
    public void getMonsterTest() {
        Monster monster1 = new Monster(new Position(6, 6));
        Monster monster2 = new Monster(new Position(10, 15));

        room.addMonster(monster1);
        room.addMonster(monster2);

        Assertions.assertEquals(monster1, room.getMonster(new Position(6, 6)));
        Assertions.assertEquals(monster2, room.getMonster(new Position(10, 15)));
        Assertions.assertEquals(null, room.getMonster(new Position(8, 24)));
        Assertions.assertEquals(null, room.getMonster(new Position(23, 20)));
    }

    @Test
    public void retrieveCoinTest() {
        Coin coin1 = new Coin(new Position(6, 6));
        Coin coin2 = new Coin(new Position(10, 15));

        room.addCoin(coin1);
        room.addCoin(coin2);

        Assertions.assertEquals(coin1, room.retrieveCoin(new Position(6, 6)));
        //After retrieved, the coin must be removed from the room coins list
        Assertions.assertEquals(null, room.retrieveCoin(new Position(6, 6)));

        Assertions.assertEquals(coin2, room.retrieveCoin(new Position(10, 15)));
        Assertions.assertEquals(null, room.retrieveCoin(new Position(10, 15)));

        Assertions.assertEquals(null, room.retrieveCoin(new Position(8, 24)));
        Assertions.assertEquals(null, room.retrieveCoin(new Position(23, 20)));
    }

    @Test
    public void retrieveStrengthPotionTest() {
        StrengthPotion sp1 = new StrengthPotion(new Position(6, 6));
        StrengthPotion sp2 = new StrengthPotion(new Position(10, 15));

        room.addStrengthPotion(sp1);
        room.addStrengthPotion(sp2);

        Assertions.assertEquals(sp1, room.retrieveStrengthPotion(new Position(6, 6)));
        //After retrieved, the strength must be removed from the room coins list
        Assertions.assertEquals(null, room.retrieveStrengthPotion(new Position(6, 6)));

        Assertions.assertEquals(sp2, room.retrieveStrengthPotion(new Position(10, 15)));
        Assertions.assertEquals(null, room.retrieveStrengthPotion(new Position(10, 15)));

        Assertions.assertEquals(null, room.retrieveStrengthPotion(new Position(8, 24)));
        Assertions.assertEquals(null, room.retrieveStrengthPotion(new Position(23, 20)));
    }

    @Test
    public void attackMonsterTest() {
        Monster monster1 = new Monster(new Position(10, 10), 10, 5);

        room.addMonster(monster1);

        Assertions.assertEquals(10, monster1.getHealth());
        room.attackMonster(new Position(11, 10), 7, 100); //Not a monster position
        Assertions.assertEquals(10, monster1.getHealth());

        room.attackMonster(new Position(10, 10), 7, 100);
        Assertions.assertEquals(3, monster1.getHealth());
        Assertions.assertTrue(room.getMonsters().contains(monster1));

        room.attackMonster(new Position(10, 10), 7, 100);
        Assertions.assertEquals(0, monster1.getHealth());
        Assertions.assertFalse(room.getMonsters().contains(monster1));
    }
}