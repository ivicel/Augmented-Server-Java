package info.ivicel.augmented.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.ivicel.augmented.core.model.entity.SteamSpy;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class StorePageDataDTO {
    private Map<String, Chart> charts;

    private OCDataDTO oc;

    @JsonProperty("steamspy")
    private SteamSpy steamSpy;

    private Survey survey;

    private Exfgls exfgls;

    private Hltb hltb;

    @JsonProperty("metacritic")
    private MetacriticScore metacriticScore;

    @Data
    @Builder
    public static class Chart {
        private String current;

        private String peaktoday;

        private String peakall;
    }

    @Data
    @JsonInclude(Include.NON_NULL)
    public static class Survey {
        private Boolean success;

        private Integer response;

        private String fr;

        private Integer frp;

        private String mr;

        private Boolean gs;

        private Integer nvidia;

        private Integer amd;

        private Integer intel;

        private Integer other;
    }

    @Builder
    @Data
    public static class Exfgls {
        private Integer appid;

        private Boolean excluded;
    }

    @Data
    public static class Hltb {
        private Boolean success;

        @JsonProperty("main_story")
        private String mainStory;

        @JsonProperty("main_extra")
        private String mainExtra;

        private String comp;

        private String url;

        @JsonProperty("submit_url")
        private String submitUrl;
    }

    @Data
    @Builder
    public static class MetacriticScore {
        @JsonProperty("userscore")
        private Double userScore;
    }
}
