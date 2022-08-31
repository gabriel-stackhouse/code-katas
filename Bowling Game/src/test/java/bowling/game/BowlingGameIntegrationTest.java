package bowling.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BowlingGameIntegrationTest {

    private BowlingGame bowlingGame;

    @Before
    public void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    public void roll_ReturnsCorrectScoreForEntireGame() {
        bowlingGame.roll(5);
        bowlingGame.roll(5);
        bowlingGame.roll(4);
        bowlingGame.roll(0);
        bowlingGame.roll(8);
        bowlingGame.roll(1);
        bowlingGame.roll(10);
        bowlingGame.roll(0);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(4);
        bowlingGame.roll(6);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(5);
        assertTrue(bowlingGame.isGameComplete());
        assertEquals(186, bowlingGame.score());
    }

    @Test
    public void roll_ReturnsCorrectScoreForEntireGame_OtherParameters() {
        bowlingGame.roll(6);
        bowlingGame.roll(2);
        bowlingGame.roll(7);
        bowlingGame.roll(2);
        bowlingGame.roll(3);
        bowlingGame.roll(4);
        bowlingGame.roll(8);
        bowlingGame.roll(2);
        bowlingGame.roll(9);
        bowlingGame.roll(0);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(6);
        bowlingGame.roll(3);
        bowlingGame.roll(8);
        bowlingGame.roll(2);
        bowlingGame.roll(7);
        assertTrue(bowlingGame.isGameComplete());
        assertEquals(153, bowlingGame.score());
    }

    @Test
    public void roll_ReturnsCorrectScoreForEntireGame_OtherParameters2() {
        bowlingGame.roll(10);
        bowlingGame.roll(9);
        bowlingGame.roll(1);
        bowlingGame.roll(5);
        bowlingGame.roll(5);
        bowlingGame.roll(7);
        bowlingGame.roll(2);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(9);
        bowlingGame.roll(0);
        bowlingGame.roll(8);
        bowlingGame.roll(2);
        bowlingGame.roll(9);
        bowlingGame.roll(1);
        bowlingGame.roll(10);
        assertTrue(bowlingGame.isGameComplete());
        assertEquals(187, bowlingGame.score());
    }

    @Test
    public void roll_ReturnsCorrectScoreForEntireGameWhenTenthFrameIsOnlyTwoRolls() {
        bowlingGame.roll(5);
        bowlingGame.roll(5);
        bowlingGame.roll(4);
        bowlingGame.roll(0);
        bowlingGame.roll(8);
        bowlingGame.roll(1);
        bowlingGame.roll(10);
        bowlingGame.roll(0);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(4);
        bowlingGame.roll(6);
        bowlingGame.roll(8);
        bowlingGame.roll(1);
        assertTrue(bowlingGame.isGameComplete());
        assertEquals(168, bowlingGame.score());
    }

    @Test
    public void roll_ReturnsScoreOf300WhenAPerfectGameIsRolled() {
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        assertTrue(bowlingGame.isGameComplete());
        assertEquals(300, bowlingGame.score());
    }
}