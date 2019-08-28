package info.ivicel.augmented.core.twitch.exception;

public class TwitchApiException extends RuntimeException {
    private static final long serialVersionUID = -7069085230607195749L;

    public TwitchApiException() {
    }

    public TwitchApiException(String message) {
        super(message);
    }

    public TwitchApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public TwitchApiException(Throwable cause) {
        super(cause);
    }
}
