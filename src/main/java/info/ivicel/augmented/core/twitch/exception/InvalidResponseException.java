package info.ivicel.augmented.core.twitch.exception;

public class InvalidResponseException extends TwitchApiException {
    private static final long serialVersionUID = -817736926583772398L;

    public InvalidResponseException() {
    }

    public InvalidResponseException(String message) {
        super(message);
    }

    public InvalidResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidResponseException(Throwable cause) {
        super(cause);
    }
}
