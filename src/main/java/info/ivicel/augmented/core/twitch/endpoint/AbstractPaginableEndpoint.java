package info.ivicel.augmented.core.twitch.endpoint;

import info.ivicel.augmented.core.twitch.exception.TwitchApiException;
import info.ivicel.augmented.core.twitch.response.StatusResponse;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Setter;
import org.springframework.http.HttpMethod;

public abstract class AbstractPaginableEndpoint<T extends StatusResponse> extends AbstractEndpoint {
    private String endpoint;
    private HttpMethod method;
    private Map<String, Object> params;
    private Class<T> clazz;

    @Setter
    private boolean reachedEnd = false;

    @Setter
    private String cursor;

    public AbstractPaginableEndpoint(String apiKey, String endpoint, HttpMethod method, Class<T> clazz) {
        super(apiKey);
        this.endpoint = endpoint;
        this.method = method;
        this.clazz = clazz;
        params = new LinkedHashMap<>();
    }

    public <R> List<R> getNextPage() {
        if (reachedEnd) {
            return Collections.emptyList();
        }

        try {
            StringBuilder query = new StringBuilder();
            for (String key : getParams().keySet()) {
                query.append(key).append("={").append(key).append("}").append("&");
            }

            T response = execute(endpoint + "?" + query.toString(), method, clazz);
            if (response.getError() != null) {
                return Collections.emptyList();
            }

            checkNextPage(response);

            return response.getData();

        } catch (TwitchApiException e) {
            return Collections.emptyList();
        }
    }

    abstract protected void checkNextPage(T response);

    @Override
    public Map<String, Object> getParams() {
        if (cursor != null) {
            params.put("after", cursor);
        } else {
            params.remove("after");
        }

        return params;
    }

    public void addParam(String name, Object value) {
        params.put(name, value);
    }
}
