package info.ivicel.augmented.core.twitch.exception;

public class RateLimitedException extends TwitchApiException {
    private static final long serialVersionUID = 4266862086061006754L;

    public RateLimitedException() {
    }

    public RateLimitedException(String message) {
        super(message);
    }

    public RateLimitedException(String message, Throwable cause) {
        super(message, cause);
    }
}
