package info.ivicel.augmented.core.model.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SteamUserProfileDTO {
    private List<SupporterProjection> badges = new ArrayList<>();
    private List<String> steamrep = new ArrayList<>();
    private String style;
    private Background bg;

    @Data
    @Builder
    public static class Badge {
        private String link;
        private String title;
        private String img;
    }

    @Data
    @Builder
    public static class Background {
        private String img;
        private String appid;
    }
}
