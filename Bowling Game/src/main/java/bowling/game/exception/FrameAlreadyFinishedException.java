package bowling.game.exception;

public class FrameAlreadyFinishedException extends RuntimeException {

    public FrameAlreadyFinishedException() {
        super("Frame is already finished");
    }
}
