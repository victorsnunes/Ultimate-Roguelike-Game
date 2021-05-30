package roguelikeTests.model.game.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import roguelike.model.Position;
import roguelike.model.game.elements.Mob;

class MobTest {

    class GenericMob extends Mob {
        public GenericMob(Position position, int health, int strength) { super(position, health, strength); }
    }

    GenericMob mob;

    @BeforeEach
    public void createMob() {

        Position pos = Mockito.mock(Position.class);

        mob = new GenericMob(pos, 10, 7);
    }

    @Test
    public void initialStrengthTest() {
        Assertions.assertEquals(mob.getStrength(), mob.getInitialStrength());
        Assertions.assertEquals(7, mob.getInitialStrength());
    }

    @Test
    public void increaseHealthTest() {
        Assertions.assertEquals(10, mob.getHealth());

        mob.increaseHealth(15);
        Assertions.assertEquals(25, mob.getHealth());
        mob.increaseHealth(7);
        Assertions.assertEquals(32, mob.getHealth());
        mob.increaseHealth(0);
        Assertions.assertEquals(32, mob.getHealth());
        mob.increaseHealth(-8);
        Assertions.assertEquals(32, mob.getHealth());
    }

    @Test
    public void increaseStrengthTest() {
        Assertions.assertEquals(7, mob.getStrength());

        mob.increaseStrength(10);
        Assertions.assertEquals(17, mob.getStrength());
        mob.increaseStrength(0);
        Assertions.assertEquals(17, mob.getStrength());
        mob.increaseStrength(-7);
        Assertions.assertEquals(17, mob.getStrength());
    }

    @Test
    public void increaseStrengthBonusTimeTest() {
        Assertions.assertEquals(0, mob.getStrengthBonusTime());

        mob.increaseStrengthBonusTime(12);
        Assertions.assertEquals(12, mob.getStrengthBonusTime());
        mob.increaseStrengthBonusTime(0);
        Assertions.assertEquals(12, mob.getStrengthBonusTime());
        mob.increaseStrengthBonusTime(-8);
        Assertions.assertEquals(12, mob.getStrengthBonusTime());
    }

    @Test
    public void decreaseHealthTest() {
        Assertions.assertEquals(10, mob.getHealth());

        mob.decreaseHealth(7);
        Assertions.assertEquals(3, mob.getHealth());
        mob.decreaseHealth(0);
        Assertions.assertEquals(3, mob.getHealth());
        mob.decreaseHealth(-8);
        Assertions.assertEquals(3, mob.getHealth());
        mob.decreaseHealth(8);
        Assertions.assertEquals(0, mob.getHealth());
        mob.decreaseHealth(5);
        Assertions.assertEquals(0, mob.getHealth());
    }

    @Test
    public void decreaseStrengthBonusTimeTest() {
        Assertions.assertEquals(0, mob.getStrengthBonusTime());

        mob.setStrengthBonusTime(5);

        mob.decreaseStrengthBonusTime();
        Assertions.assertEquals(4, mob.getStrengthBonusTime());
        mob.decreaseStrengthBonusTime();
        Assertions.assertEquals(3, mob.getStrengthBonusTime());
        mob.decreaseStrengthBonusTime();
        Assertions.assertEquals(2, mob.getStrengthBonusTime());
        mob.decreaseStrengthBonusTime();
        Assertions.assertEquals(1, mob.getStrengthBonusTime());
        mob.decreaseStrengthBonusTime();
        Assertions.assertEquals(0, mob.getStrengthBonusTime());
        mob.decreaseStrengthBonusTime();
        Assertions.assertEquals(0, mob.getStrengthBonusTime());
    }

}