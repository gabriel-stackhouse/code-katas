package bowling.game;

import bowling.game.exception.BowlingGameAlreadyFinishedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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