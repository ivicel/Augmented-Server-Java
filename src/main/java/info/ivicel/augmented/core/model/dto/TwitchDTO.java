package info.ivicel.augmented.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TwitchDTO {
    @JsonProperty("user_name")
    private String username;

    private String title;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    @JsonProperty("viewer_count")
    private String viewerCount;

    private String game;
}
