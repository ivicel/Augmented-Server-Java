package info.ivicel.augmented.core.twitch.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.ivicel.augmented.core.twitch.response.GamesResponse.Game;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class GamesResponse extends StatusResponse<Game> {

    @Data
    public static class Game {
        private String id;

        private String name;

        @JsonProperty("box_art_url")
        private String boxArtUrl;
    }
}
