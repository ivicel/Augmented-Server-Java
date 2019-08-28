package info.ivicel.augmented.core.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class OCDataDTO {
    @JsonProperty("openCriticScore")
    private String score;

    @JsonProperty("openCriticUrl")
    private String url;

    @JsonProperty("reviewCount")
    private String count;

    @JsonProperty("topReviews")
    private List<TopReviews> reviews;

    @Data
    public static class TopReviews {
        @JsonProperty("publishedDate")
        private String date;

        @JsonProperty("externalUrl")
        private String rURL;

        private String snippet;

        private Integer score;

        private String author;

        @JsonProperty("outletName")
        private String name;

        @JsonProperty("displayScore")
        private String dScore;
    }
}
