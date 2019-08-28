package info.ivicel.augmented.core.twitch.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.ivicel.augmented.core.twitch.response.StreamsResponse.User;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class StreamsResponse extends StatusResponse<User> {

    // private List<User> data;

    private Map<String, String> pagination;

    @JsonAnySetter
    private Map<String, Object> others;

    @Data
    public static class User {
        private String id;

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("user_name")
        private String username;

        @JsonProperty("game_id")
        private String gameId;

        private String type;

        private String title;

        @JsonProperty("viewer_count")
        private String viewerCount;

        @JsonProperty("started_at")
        private String startedAt;

        private String language;

        @JsonProperty("thumbnail_url")
        private String thumbnailUrl;

        @JsonProperty("tag_ids")
        private List<String> tagIds;
    }
}
