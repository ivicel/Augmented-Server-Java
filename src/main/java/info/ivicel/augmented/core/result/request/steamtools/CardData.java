package info.ivicel.augmented.core.result.request.steamtools;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class CardData {
    @JsonProperty("game_count")
    private Integer gameCount;

    private List<Game> sets;

    private Integer time;

    private Map<String, Double> totals;

    @JsonProperty("bg_count")
    private Integer bgCount;

    @JsonProperty("emote_count")
    private Integer emoteCount;

    @JsonProperty("card_count")
    private Integer cardCount;

    @Data
    public static class Game {
        private int added;

        private Map<String, String> normal;

        private int appid;

        private double discount;

        private String game;

        @JsonProperty("bgs_avg")
        private double bgsAvg;

        @JsonProperty("true_count")
        private int trueCount;

        private Map<String, String> foil;

        private Boolean f2p;

        @JsonProperty("emotes_count")
        private int emotesCount;

        @JsonProperty("emotes_avg")
        private double emotesAvg;

        private double bprice;

        @JsonProperty("bgs_count")
        private int bgsCount;
    }
}
