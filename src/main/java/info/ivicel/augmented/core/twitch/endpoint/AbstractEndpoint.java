package info.ivicel.augmented.core.twitch.endpoint;

import info.ivicel.augmented.core.twitch.exception.InvalidResponseException;
import info.ivicel.augmented.core.twitch.exception.RateLimitedException;
import info.ivicel.augmented.utils.Http;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public abstract class AbstractEndpoint {
    private static final String HOST = "https://api.twitch.tv/helix/";

    private String token;
    private String rateLimit;
    private String apiKey;
    private String rateLimitRemaining;
    private HttpHeaders headers;

    public AbstractEndpoint(String apiKey) {
        headers = new HttpHeaders();
        this.apiKey = apiKey;

        headers.add("Client-ID", apiKey);
    }

    @SuppressWarnings("ConstantConditions")
    public <R> R execute(String endpoint, HttpMethod method, Class<R> respClass) {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<R> response = Http
                .exchange(HOST + endpoint, method, entity, respClass, getParams());

        HttpHeaders headers = response.getHeaders();
        if (headers.get("Ratelimit-Limit") != null) {
            this.rateLimit = headers.get("Ratelimit-Limit").get(0);
        }

        if (headers.get("Ratelimit-Remaining") != null) {
            this.rateLimitRemaining = headers.get("Ratelimit-Remaining").get(0);
        }

        if (response.getStatusCodeValue() == 429) {
            throw new RateLimitedException();
        }

        R json = response.getBody();
        if (json == null) {
            throw new InvalidResponseException("Can not parse response to json object");
        }

        return json;
    }

    public abstract Map<String, Object> getParams();
}
