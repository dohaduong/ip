package duke.exception;

public class MissingContentException extends DukeException {
    public MissingContentException() {
        super("OOPS! The content/detail cannot be empty!");
    }
}