package info.ivicel.augmented.core.twitch.endpoint;

import info.ivicel.augmented.core.twitch.response.GamesResponse;
import org.springframework.http.HttpMethod;

public class GamesEndpoint extends AbstractPaginableEndpoint<GamesResponse> {
    private static final String ENDPOINT = "games";

    public GamesEndpoint(String apiKey) {
        super(apiKey, ENDPOINT, HttpMethod.GET, GamesResponse.class);
    }

    public GamesEndpoint setGameId(String gameId) {
        addParam("id", gameId);
        return this;
    }

    @Override
    protected void checkNextPage(GamesResponse response) {
        setReachedEnd(true);
    }
}
