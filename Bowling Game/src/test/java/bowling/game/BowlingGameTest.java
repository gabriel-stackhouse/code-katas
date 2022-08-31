package bowling.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @Before
    public void setUp() {
        bowlingGame = new BowlingGame();
    }

    // todo - add more unit tests

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

    // todo - fix
//    @Test
//    public void score_ReturnsTheSumOfAllTheFrameScores() {
//        bowlingGame.getFrames()[0] = buildFrame(7);
//        bowlingGame.getFrames()[1] = buildFrame(6);
//        bowlingGame.getFrames()[2] = buildFrame(12);
//        bowlingGame.getFrames()[3] = buildFrame(2);
//        bowlingGame.getFrames()[4] = buildFrame(8);
//        bowlingGame.getFrames()[5] = buildFrame(10);
//        assertEquals(45, bowlingGame.score());
//    }
//
//    @Test
//    public void score_ReturnsTheSumOfAllTheFrameScores_OtherParameters() {
//        bowlingGame.getFrames()[0] = buildFrame(9);
//        bowlingGame.getFrames()[1] = buildFrame(8);
//        bowlingGame.getFrames()[2] = buildFrame(12);
//        bowlingGame.getFrames()[3] = buildFrame(15);
//        bowlingGame.getFrames()[4] = buildFrame(3);
//        bowlingGame.getFrames()[5] = buildFrame(7);
//        bowlingGame.getFrames()[6] = buildFrame(6);
//        bowlingGame.getFrames()[7] = buildFrame(9);
//        bowlingGame.getFrames()[8] = buildFrame(10);
//        bowlingGame.getFrames()[9] = buildFrame(10);
//        assertEquals(89, bowlingGame.score());
//    }

    @Test
    public void score_ReturnsZeroWhenNoRollsHaveBeenPerformed() {
        assertEquals(0, bowlingGame.score());
    }

    private Frame buildFrame(int frameNumber, Integer firstRoll, Integer secondRoll, Integer thirdRoll,
                             Integer frameBonus) {
        Frame frame = new Frame(frameNumber);
        frame.setFirstRoll(firstRoll);
        frame.setSecondRoll(secondRoll);
        frame.setThirdRoll(thirdRoll);
        frame.setFrameBonus(frameBonus);
        return frame;
    }
}