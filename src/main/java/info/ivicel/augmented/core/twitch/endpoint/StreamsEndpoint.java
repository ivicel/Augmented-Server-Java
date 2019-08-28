package info.ivicel.augmented.core.twitch.endpoint;

import info.ivicel.augmented.core.twitch.response.StreamsResponse;
import org.springframework.http.HttpMethod;

public class StreamsEndpoint extends AbstractPaginableEndpoint<StreamsResponse> {
    private static final String ENDPOINT = "streams";


    public StreamsEndpoint(String apiKey) {
        super(apiKey, ENDPOINT, HttpMethod.GET, StreamsResponse.class);
    }

    public StreamsEndpoint setGameId(Integer gameId) {
        addParam("game_id", gameId);
        return this;
    }

    public StreamsEndpoint setLanguage(String lang) {
        addParam("language", lang);
        return this;
    }

    public StreamsEndpoint setUserLogin(String userLogin) {
        addParam("user_login", userLogin);
        return this;
    }

    public StreamsEndpoint setFirst(String first) {
        addParam("first", first);
        return this;
    }

    @Override
    protected void checkNextPage(StreamsResponse response) {
        if (response.getPagination().containsKey("cursor")) {
            setCursor(response.getPagination().get("cursor"));
        } else {
            setReachedEnd(true);
        }
    }
}
