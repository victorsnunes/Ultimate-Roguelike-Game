package roguelike.model.game.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import roguelike.model.Position;

//No need to test again methods from the Mob class
class HeroTest {

    Hero hero;

    @BeforeEach
    public void createHero() { hero = new Hero(Mockito.mock(Position.class), 5, 9); }

    @Test
    public void writeStrengthTimeTest() {
        Assertions.assertEquals("0:00", hero.writeStrengthTime());
        hero.strengthBonusTime = 30;
        Assertions.assertEquals("0:30", hero.writeStrengthTime());
        hero.strengthBonusTime = 47;
        Assertions.assertEquals("0:47", hero.writeStrengthTime());
        hero.strengthBonusTime = 60;
        Assertions.assertEquals("1:00", hero.writeStrengthTime());
        hero.strengthBonusTime = 83;
        Assertions.assertEquals("1:23", hero.writeStrengthTime());
        hero.strengthBonusTime = 134;
        Assertions.assertEquals("2:14", hero.writeStrengthTime());
        hero.strengthBonusTime = 600;
        Assertions.assertEquals("10:00", hero.writeStrengthTime());
    }

}