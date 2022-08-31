package bowling.game;

import bowling.game.exception.BowlingGameAlreadyFinishedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @Before
    public void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test(expected = BowlingGameAlreadyFinishedException.class)
    public void roll_ThrowsExceptionWhenGameIsAlreadyFinished() {
        bowlingGame.setGameComplete(true);
        bowlingGame.roll(4);
    }

    @Test
    public void roll_AddsRollToFrame() {
        bowlingGame.roll(4);
        assertEquals(4, bowlingGame.getFrames()[0].getFirstRoll().intValue());
    }

    @Test
    public void roll_AddsRollToFrame_OtherParameters() {
        bowlingGame.roll(2);
        bowlingGame.roll(3);
        assertEquals(2, bowlingGame.getFrames()[0].getFirstRoll().intValue());
        assertEquals(3, bowlingGame.getFrames()[0].getSecondRoll().intValue());
    }

    @Test
    public void roll_AddsRollToNextFrame() {
        bowlingGame.roll(4);
        bowlingGame.roll(3);
        bowlingGame.roll(7);
        assertEquals(4, bowlingGame.getFrames()[0].getFirstRoll().intValue());
        assertEquals(3, bowlingGame.getFrames()[0].getSecondRoll().intValue());
        assertEquals(7, bowlingGame.getFrames()[1].getFirstRoll().intValue());
    }

    @Test
    public void roll_AddsRollToNextFrameWhenThereWasAStrike() {
        bowlingGame.roll(4);
        bowlingGame.roll(3);
        bowlingGame.roll(10);
        bowlingGame.roll(6);
        assertEquals(4, bowlingGame.getFrames()[0].getFirstRoll().intValue());
        assertEquals(3, bowlingGame.getFrames()[0].getSecondRoll().intValue());
        assertEquals(10, bowlingGame.getFrames()[1].getFirstRoll().intValue());
        assertNull(bowlingGame.getFrames()[2].getSecondRoll());
        assertEquals(6, bowlingGame.getFrames()[2].getFirstRoll().intValue());
    }

    @Test
    public void roll_AppliesFrameBonusForNextTwoRollsWhenThereWasAStrike() {
        bowlingGame.roll(10);
        bowlingGame.roll(6);
        bowlingGame.roll(2);
        bowlingGame.roll(5);
        assertEquals(8, bowlingGame.getFrames()[0].getFrameBonus());
    }

    @Test
    public void roll_AppliesFrameBonusForNextTwoRollsWhenThereWasAStrike_OtherParameters() {
        bowlingGame.roll(2);
        bowlingGame.roll(3);
        bowlingGame.roll(1);
        bowlingGame.roll(2);
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        bowlingGame.roll(4);
        bowlingGame.roll(8);
        assertEquals(14, bowlingGame.getFrames()[2].getFrameBonus());
    }

    @Test
    public void roll_DoesNotApplyFrameBonusWhenThereWasAStrikeWhenLastFrame() {
        for (int i = 0; i < 18; i++) {
            bowlingGame.roll(4);
        }
        bowlingGame.roll(10);
        bowlingGame.roll(4);
        bowlingGame.roll(6);
        assertEquals(0, bowlingGame.getFrames()[9].getFrameBonus());
    }

    @Test
    public void roll_AppliesFrameBonusForNextRollWhenThereWasASpare() {
        bowlingGame.roll(8);
        bowlingGame.roll(2);
        bowlingGame.roll(3);
        bowlingGame.roll(4);
        assertEquals(3, bowlingGame.getFrames()[0].getFrameBonus());
    }

    @Test
    public void roll_AppliesFrameBonusForNextRollWhenThereWasASpare_OtherParameters() {
        bowlingGame.roll(2);
        bowlingGame.roll(3);
        bowlingGame.roll(1);
        bowlingGame.roll(2);
        bowlingGame.roll(7);
        bowlingGame.roll(3);
        bowlingGame.roll(9);
        bowlingGame.roll(8);
        assertEquals(9, bowlingGame.getFrames()[2].getFrameBonus());
    }

    @Test
    public void roll_DoesNotApplyFrameBonusWhenThereWasASpareWhenLastFrame() {
        for (int i = 0; i < 18; i++) {
            bowlingGame.roll(4);
        }
        bowlingGame.roll(4);
        bowlingGame.roll(6);
        bowlingGame.roll(3);
        assertEquals(0, bowlingGame.getFrames()[9].getFrameBonus());
    }

    @Test
    public void roll_MarksGameAsCompleteWhenTenthFrameIsComplete() {
        for (int i = 0; i < 19; i++) {
            bowlingGame.roll(4);
        }
        assertFalse(bowlingGame.isGameComplete());
        bowlingGame.roll(4);
        assertTrue(bowlingGame.isGameComplete());
    }

    @Test
    public void roll_MarksGameAsCompleteWhenTenthFrameIsComplete_WithThreeFinalRolls() {
        for (int i = 0; i < 19; i++) {
            bowlingGame.roll(4);
        }
        assertFalse(bowlingGame.isGameComplete());
        bowlingGame.roll(6);
        assertFalse(bowlingGame.isGameComplete());
        bowlingGame.roll(7);
        assertTrue(bowlingGame.isGameComplete());
    }

    @Test
    public void score_CallsFrameCalculateFrameScoreMethod() {
        bowlingGame.getFrames()[0] = buildMockFrame(7);
        bowlingGame.getFrames()[1] = buildMockFrame(6);
        bowlingGame.getFrames()[2] = buildMockFrame(12);
        bowlingGame.score();
        verify(bowlingGame.getFrames()[0]).calculateFrameScore();
        verify(bowlingGame.getFrames()[1]).calculateFrameScore();
        verify(bowlingGame.getFrames()[2]).calculateFrameScore();
    }

    @Test
    public void score_CallsFrameCalculateFrameScoreMethod_OtherParameters() {
        bowlingGame.getFrames()[0] = buildMockFrame(1);
        bowlingGame.getFrames()[1] = buildMockFrame(1);
        bowlingGame.getFrames()[2] = buildMockFrame(1);
        bowlingGame.getFrames()[3] = buildMockFrame(1);
        bowlingGame.getFrames()[4] = buildMockFrame(1);
        bowlingGame.getFrames()[5] = buildMockFrame(1);
        bowlingGame.getFrames()[6] = buildMockFrame(1);
        bowlingGame.getFrames()[7] = buildMockFrame(1);
        bowlingGame.getFrames()[8] = buildMockFrame(1);
        bowlingGame.getFrames()[9] = buildMockFrame(1);
        bowlingGame.score();
        verify(bowlingGame.getFrames()[0]).calculateFrameScore();
        verify(bowlingGame.getFrames()[1]).calculateFrameScore();
        verify(bowlingGame.getFrames()[2]).calculateFrameScore();
        verify(bowlingGame.getFrames()[3]).calculateFrameScore();
        verify(bowlingGame.getFrames()[4]).calculateFrameScore();
        verify(bowlingGame.getFrames()[5]).calculateFrameScore();
        verify(bowlingGame.getFrames()[6]).calculateFrameScore();
        verify(bowlingGame.getFrames()[7]).calculateFrameScore();
        verify(bowlingGame.getFrames()[8]).calculateFrameScore();
        verify(bowlingGame.getFrames()[9]).calculateFrameScore();
    }

    @Test
    public void score_ReturnsTheSumOfAllTheFrameScores() {
        bowlingGame.getFrames()[0] = buildMockFrame(7);
        bowlingGame.getFrames()[1] = buildMockFrame(6);
        bowlingGame.getFrames()[2] = buildMockFrame(12);
        bowlingGame.getFrames()[3] = buildMockFrame(2);
        bowlingGame.getFrames()[4] = buildMockFrame(8);
        bowlingGame.getFrames()[5] = buildMockFrame(10);
        assertEquals(45, bowlingGame.score());
    }

    @Test
    public void score_ReturnsTheSumOfAllTheFrameScores_OtherParameters() {
        bowlingGame.getFrames()[0] = buildMockFrame(9);
        bowlingGame.getFrames()[1] = buildMockFrame(8);
        bowlingGame.getFrames()[2] = buildMockFrame(12);
        bowlingGame.getFrames()[3] = buildMockFrame(15);
        bowlingGame.getFrames()[4] = buildMockFrame(3);
        bowlingGame.getFrames()[5] = buildMockFrame(7);
        bowlingGame.getFrames()[6] = buildMockFrame(6);
        bowlingGame.getFrames()[7] = buildMockFrame(9);
        bowlingGame.getFrames()[8] = buildMockFrame(10);
        bowlingGame.getFrames()[9] = buildMockFrame(10);
        assertEquals(89, bowlingGame.score());
    }

    @Test
    public void score_ReturnsZeroWhenNoRollsHaveBeenPerformed() {
        assertEquals(0, bowlingGame.score());
    }

    private Frame buildMockFrame(int frameScore) {
        Frame mockFrame = mock(Frame.class);
        when(mockFrame.calculateFrameScore()).thenReturn(frameScore);
        return mockFrame;
    }
}