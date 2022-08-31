package bowling.game;

import bowling.game.exception.FrameAlreadyFinishedException;

import java.util.Objects;

import static java.util.stream.Stream.of;

public class Frame {

    private int frameNumber;
    private Integer firstRoll;
    private Integer secondRoll;
    private Integer thirdRoll;
    private int frameBonus;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public Integer getFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(Integer firstRoll) {
        this.firstRoll = firstRoll;
    }

    public Integer getSecondRoll() {
        return secondRoll;
    }

    public void setSecondRoll(Integer secondRoll) {
        this.secondRoll = secondRoll;
    }

    public Integer getThirdRoll() {
        return thirdRoll;
    }

    public void setThirdRoll(Integer thirdRoll) {
        this.thirdRoll = thirdRoll;
    }

    public int getFrameBonus() {
        return frameBonus;
    }

    public void setFrameBonus(int frameBonus) {
        this.frameBonus = frameBonus;
    }

    public void addRoll(int pins) {
        if (isFrameFinished()) {
            throw new FrameAlreadyFinishedException();
        }
        if (firstRoll == null) {
            firstRoll = pins;
        } else if (secondRoll == null) {
            secondRoll = pins;
        } else {
            thirdRoll = pins;
        }
    }

    public boolean isFrameFinished() {
        if (frameNumber < 10) {
            return isStrike() || numberOfRollsFinished() == 2;
        }
        int tenthFrameRolls = isStrike() || isSpare() ? 3 : 2;
        return numberOfRollsFinished() == tenthFrameRolls;
    }

    public int calculateFrameScore() {
        return of(firstRoll, secondRoll, thirdRoll, frameBonus)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int numberOfRollsFinished() {
        if (firstRoll == null) {
            return 0;
        } else if (secondRoll == null) {
            return 1;
        } else if (thirdRoll == null) {
            return 2;
        }
        return 3;
    }

    public void addFrameBonus(int frameBonus) {
        this.frameBonus += frameBonus;
    }

    public boolean isStrike() {
        return firstRoll != null && firstRoll == 10;
    }

    public boolean isSpare() {
        return firstRoll != null && secondRoll != null && firstRoll + secondRoll == 10;
    }
}