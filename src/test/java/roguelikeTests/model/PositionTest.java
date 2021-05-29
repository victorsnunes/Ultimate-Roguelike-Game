package roguelikeTests.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roguelike.model.Position;

import java.util.Arrays;
import java.util.List;

class PositionTest {

    Position pos;

    @BeforeEach
    public void createPosition() { pos = new Position(6, 6); }

    @Test
    public void getLeftTest() { Assertions.assertEquals(new Position(5, 6), pos.getLeft()); }
    @Test
    public void getRightTest() { Assertions.assertEquals(new Position(7, 6), pos.getRight()); }
    @Test
    public void getUpTest() { Assertions.assertEquals(new Position(6, 5), pos.getUp()); }
    @Test
    public void getDownTest() { Assertions.assertEquals(new Position(6, 7), pos.getDown()); }

    @Test
    public void followTest() {

        Position pos1 = new Position (1, 3);
        List<Position> pos1PossibleFollows = Arrays.asList(new Position(6, 6), new Position(5, 6),
                                                           new Position(6, 5), new Position(5, 5));
        Assertions.assertTrue( pos1PossibleFollows.contains(pos.follow(pos1)) );


        Position pos2 = new Position (9, 7);
        List<Position> pos2PossibleFollows = Arrays.asList(new Position(6, 6), new Position(7, 6),
                                                           new Position(6, 7), new Position(7, 7));
        Assertions.assertTrue( pos2PossibleFollows.contains(pos.follow(pos2)) );


        Position pos3 = new Position (10, 2);
        List<Position> pos3PossibleFollows = Arrays.asList(new Position(6, 6), new Position(7, 6),
                                                           new Position(6, 5), new Position(7, 5));
        Assertions.assertTrue( pos3PossibleFollows.contains(pos.follow(pos3)) );


        Position pos4 = new Position (3, 11);
        List<Position> pos4PossibleFollows = Arrays.asList(new Position(6, 6), new Position(5, 6),
                                                           new Position(6, 7), new Position(5, 7));
        Assertions.assertTrue( pos4PossibleFollows.contains(pos.follow(pos4)) );


        Position pos5 = new Position (4, 6);
        List<Position> pos5PossibleFollows = Arrays.asList(new Position(6, 6), new Position(5, 6));
        Assertions.assertTrue( pos5PossibleFollows.contains(pos.follow(pos5)) );

        Position pos6 = new Position (9, 6);
        List<Position> pos6PossibleFollows = Arrays.asList(new Position(6, 6), new Position(7, 6));
        Assertions.assertTrue( pos6PossibleFollows.contains(pos.follow(pos6)) );

        Position pos7 = new Position (6, 1);
        List<Position> pos7PossibleFollows = Arrays.asList(new Position(6, 6), new Position(6, 5));
        Assertions.assertTrue( pos7PossibleFollows.contains(pos.follow(pos7)) );

        Position pos8 = new Position (6, 12);
        List<Position> pos8PossibleFollows = Arrays.asList(new Position(6, 6), new Position(6, 7));
        Assertions.assertTrue( pos8PossibleFollows.contains(pos.follow(pos8)) );

        Position pos9 = new Position (6, 6);
        List<Position> pos9PossibleFollows = Arrays.asList(new Position(6, 6));
        Assertions.assertTrue( pos9PossibleFollows.contains(pos.follow(pos9)) );
    }

}