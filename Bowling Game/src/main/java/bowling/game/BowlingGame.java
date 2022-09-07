package bowling.game;

import bowling.game.exception.BowlingGameAlreadyFinishedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.stream;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;

public class BowlingGame {

    private int frameNumber;
    private Frame[] frames;
    private boolean isGameComplete;

    private Frame twoRollBonusFrame;
    private final List<Frame> oneRollBonusFrames;

    public BowlingGame() {
        frameNumber = 1;
        frames = new Frame[10];
        frames[0] = new Frame(frameNumber);
        oneRollBonusFrames = new ArrayList<>();
    }

    public void roll(int pins) {
        if (isGameComplete) {
            throw new BowlingGameAlreadyFinishedException();
        }
        Frame currentFrame = frames[frameNumber - 1];
        currentFrame.addRoll(pins);
        addFrameBonuses(pins);
        checkForFutureFrameBonuses(currentFrame);
        isGameComplete = frameNumber == 10 && currentFrame.isFrameFinished();
        if (currentFrame.isFrameFinished() && !isGameComplete) {
            frameNumber++;
            frames[frameNumber - 1] = new Frame(frameNumber);
        }
    }

    public int score() {
        return stream(frames)
                .filter(Objects::nonNull)
                .mapToInt(Frame::calculateFrameScore)
                .sum();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public Frame[] getFrames() {
        return frames;
    }

    public void setFrames(Frame[] frames) {
        this.frames = frames;
    }

    public boolean isGameComplete() {
        return isGameComplete;
    }

    public void setGameComplete(boolean gameComplete) {
        isGameComplete = gameComplete;
    }

    private void addFrameBonuses(int pins) {
        concat(oneRollBonusFrames.stream(), of(twoRollBonusFrame))
                .filter(Objects::nonNull)
                .forEach(frame -> frame.addFrameBonus(pins));
        oneRollBonusFrames.clear();
        oneRollBonusFrames.add(twoRollBonusFrame);
        twoRollBonusFrame = null;
    }

    private void checkForFutureFrameBonuses(Frame currentFrame) {
        if (!currentFrame.isFrameFinished() || frameNumber == 10) {
            return;
        }
        if (currentFrame.isStrike()) {
            twoRollBonusFrame = currentFrame;
        } else if (currentFrame.isSpare()) {
            oneRollBonusFrames.add(currentFrame);
        }
    }
}