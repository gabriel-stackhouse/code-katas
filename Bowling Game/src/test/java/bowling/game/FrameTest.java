package bowling.game;

import bowling.game.exception.FrameAlreadyFinishedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FrameTest {

    private Frame frame;
    private Frame tenthFrame;   // todo - may delete this and just reuse the first one

    @Before
    public void setUp() {
        frame = new Frame(1);
        tenthFrame = new Frame(10);
    }

    @Test
    public void addRoll_AddsRollToFirstRollWhenThereIsNoFirstRoll() {
        frame.addRoll(5);
        assertFrameRolls(frame, 5, null, null);
    }

    @Test
    public void addRoll_AddsRollToFirstRollWhenThereIsNoFirstRoll_CorrectPinNumber() {
        frame.addRoll(8);
        assertFrameRolls(frame, 8, null, null);
    }

    @Test
    public void addRoll_AddsRollToSecondRollWhenThereIsNoSecondRoll() {
        frame.addRoll(5);
        frame.addRoll(3);
        assertFrameRolls(frame, 5, 3, null);
    }

    @Test
    public void addRoll_AddsRollToSecondRollWhenThereIsNoSecondRoll_CorrectPinNumber() {
        frame.addRoll(8);
        frame.addRoll(2);
        assertFrameRolls(frame, 8, 2, null);
    }

    @Test
    public void addRoll_AddsRollToThirdRollWhenFrame10AndFrameIsNotFinishedAndThereIsNoThirdRoll() {
        tenthFrame.addRoll(6);
        tenthFrame.addRoll(4);
        tenthFrame.addRoll(7);
        assertFrameRolls(tenthFrame, 6, 4, 7);
    }

    @Test
    public void addRoll_AddsRollToThirdRollWhenFrame10AndFrameIsNotFinishedAndThereIsNoThirdRoll_CorrectPinNumber() {
        tenthFrame.addRoll(10);
        tenthFrame.addRoll(4);
        tenthFrame.addRoll(3);
        assertFrameRolls(tenthFrame, 10, 4, 3);
    }

    @Test(expected = FrameAlreadyFinishedException.class)
    public void addRoll_ThrowsExceptionWhenFrameIsFinished() {
        Frame mockFrame = mock(Frame.class);
        when(mockFrame.isFrameFinished()).thenReturn(true);
        doCallRealMethod().when(mockFrame).addRoll(anyInt());
        mockFrame.addRoll(5);
    }

    @Test
    public void isFrameFinished_ReturnsTrueWhenStrikeIsRolled() {
        frame.setFirstRoll(10);
        assertTrue(frame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsFalseWhenFirstRollIsNotAStrike() {
        frame.setFirstRoll(9);
        assertFalse(frame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsFalseWhenFirstRollIsNotAStrike_OtherParameters() {
        frame.setFirstRoll(7);
        assertFalse(frame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsTrueWhenTwoRollsAreCompleted() {
        frame.setFirstRoll(9);
        frame.setSecondRoll(1);
        assertTrue(frame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsTrueWhenTwoRollsAreCompleted_OtherParameters() {
        frame.setFirstRoll(2);
        frame.setSecondRoll(3);
        assertTrue(frame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsFalseWhenTenthFrameAndStrikeIsRolledOnFirstRoll() {
        tenthFrame.setFirstRoll(10);
        assertFalse(tenthFrame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsFalseWhenTenthFrameAndTwoRollsAreCompletedWithStrikeOnFirstRoll() {
        tenthFrame.setFirstRoll(10);
        tenthFrame.setSecondRoll(2);
        assertFalse(tenthFrame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsFalseWhenTenthFrameAndTwoRollsAreCompletedWithStrikeOnFirstRoll_OtherParameters() {
        tenthFrame.setFirstRoll(10);
        tenthFrame.setSecondRoll(10);
        assertFalse(tenthFrame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsFalseWhenTenthFrameAndTwoRollsAreCompletedWithASpare() {
        tenthFrame.setFirstRoll(4);
        tenthFrame.setSecondRoll(6);
        assertFalse(tenthFrame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsFalseWhenTenthFrameAndTwoRollsAreCompletedWithASpare_OtherParameters() {
        tenthFrame.setFirstRoll(8);
        tenthFrame.setSecondRoll(2);
        assertFalse(tenthFrame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsTrueWhenTenthFrameAndThreeRollsAreCompleted() {
        tenthFrame.setFirstRoll(10);
        tenthFrame.setSecondRoll(2);
        tenthFrame.setThirdRoll(4);
        assertTrue(tenthFrame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsTrueWhenTenthFrameAndThreeRollsAreCompleted_OtherParameters() {
        tenthFrame.setFirstRoll(7);
        tenthFrame.setSecondRoll(3);
        tenthFrame.setThirdRoll(10);
        assertTrue(tenthFrame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsTrueWhenTenthFrameAndTwoRollsAreCompletedWithNoStrikeOrSpare() {
        tenthFrame.setFirstRoll(5);
        tenthFrame.setSecondRoll(2);
        assertTrue(tenthFrame.isFrameFinished());
    }

    @Test
    public void isFrameFinished_ReturnsTrueWhenTenthFrameAndTwoRollsAreCompletedWithNoStrikeOrSpare_OtherParameters() {
        tenthFrame.setFirstRoll(8);
        tenthFrame.setSecondRoll(1);
        assertTrue(tenthFrame.isFrameFinished());
    }

    @Test
    public void calculateFrameScore_ReturnsSumOfAllRolesAndFrameBonus() {
        Frame newFrame = buildFrame(6, 4, 8, 2);
        assertEquals(20, newFrame.calculateFrameScore());
    }

    @Test
    public void calculateFrameScore_ReturnsSumOfAllRolesAndFrameBonus_OtherParameters() {
        Frame newFrame = buildFrame(7, 3, 4, 1);
        assertEquals(15, newFrame.calculateFrameScore());
    }

    @Test
    public void calculateFrameScore_ReturnsSumOfAllRolesAndFrameBonusIgnoringNullValues() {
        Frame newFrame = buildFrame(6, 2, null, 3);
        assertEquals(11, newFrame.calculateFrameScore());
    }

    @Test
    public void calculateFrameScore_ReturnsSumOfAllRolesAndFrameBonusIgnoringNullValues_OtherParameters() {
        Frame newFrame = buildFrame(4, null, null, 0);
        assertEquals(4, newFrame.calculateFrameScore());
    }

    @Test
    public void numberOfRollsFinished_ReturnsZeroWhenNoRollsHaveBeenCompleted() {
        assertEquals(0, frame.numberOfRollsFinished());
    }

    @Test
    public void numberOfRollsFinished_ReturnsOneWhenOneRollHasBeenCompleted() {
        frame.setFirstRoll(3);
        assertEquals(1, frame.numberOfRollsFinished());
    }

    @Test
    public void numberOfRollsFinished_ReturnsTwoWhenTwoRollsHaveBeenCompleted() {
        frame.setFirstRoll(3);
        frame.setSecondRoll(3);
        assertEquals(2, frame.numberOfRollsFinished());
    }

    @Test
    public void numberOfRollsFinished_ReturnsThreeWhenThreeRollsHaveBeenCompleted() {
        frame.setFirstRoll(10);
        frame.setSecondRoll(3);
        frame.setThirdRoll(3);
        assertEquals(3, frame.numberOfRollsFinished());
    }

    @Test
    public void addFrameBonus_BonusIsAddedFrameBonus() {
        frame.addFrameBonus(8);
        assertEquals(8, frame.getFrameBonus());
    }

    @Test
    public void addFrameBonus_BonusIsAddedToFrameBonus_OtherParameters() {
        frame.setFrameBonus(10);
        frame.addFrameBonus(6);
        assertEquals(16, frame.getFrameBonus());
    }

    @Test
    public void isStrike_ReturnsTrueWhenFirstRollIsTen() {
        frame.setFirstRoll(10);
        assertTrue(frame.isStrike());
    }

    @Test
    public void isStrike_ReturnsFalseWhenFirstRollIsLessThanTen() {
        frame.setFirstRoll(9);
        assertFalse(frame.isStrike());
    }

    @Test
    public void isStrike_ReturnsFalseWhenFirstRollIsLessThanTen_OtherParameters() {
        frame.setFirstRoll(4);
        assertFalse(frame.isStrike());
    }

    @Test
    public void isSpare_ReturnsTrueWhenFirstAndSecondRollAddToTen() {
        frame.setFirstRoll(9);
        frame.setSecondRoll(1);
        assertTrue(frame.isSpare());
    }

    @Test
    public void isSpare_ReturnsTrueWhenFirstAndSecondRollAddToTen_OtherParameters() {
        frame.setFirstRoll(6);
        frame.setSecondRoll(4);
        assertTrue(frame.isSpare());
    }

    @Test
    public void isSpare_ReturnsFalseWhenFirstAndSecondRollAddToLessThanTen() {
        frame.setFirstRoll(8);
        frame.setSecondRoll(1);
        assertFalse(frame.isSpare());
    }

    @Test
    public void isSpare_ReturnsFalseWhenFirstAndSecondRollAddToLessThanTen_OtherParameters() {
        frame.setFirstRoll(4);
        frame.setSecondRoll(2);
        assertFalse(frame.isSpare());
    }

    @Test
    public void isSpare_ReturnsFalseWhenFirstRollHasNotBeenCompleted() {
        assertFalse(frame.isSpare());
    }

    @Test
    public void isSpare_ReturnsFalseWhenSecondRollHasNotBeenCompleted() {
        frame.setFirstRoll(4);
        assertFalse(frame.isSpare());
    }

    @Test
    public void isStrike_ReturnsFalseWhenFirstRollHasNotBeenCompleted() {
        assertFalse(frame.isStrike());
    }

    private Frame buildFrame(Integer firstRoll, Integer secondRoll, Integer thirdRoll, int frameBonus) {
        Frame frame = new Frame(1);
        frame.setFirstRoll(firstRoll);
        frame.setSecondRoll(secondRoll);
        frame.setThirdRoll(thirdRoll);
        frame.setFrameBonus(frameBonus);
        return frame;
    }

    public void assertFrameRolls(Frame frame, Integer firstRoll, Integer secondRoll, Integer thirdRoll) {
        assertEquals(firstRoll, frame.getFirstRoll());
        assertEquals(secondRoll, frame.getSecondRoll());
        assertEquals(thirdRoll, frame.getThirdRoll());
    }
}