package bowling.game.exception;

public class BowlingGameAlreadyFinishedException extends RuntimeException {

    public BowlingGameAlreadyFinishedException() {
        super("Bowling game is already finished");
    }
}
