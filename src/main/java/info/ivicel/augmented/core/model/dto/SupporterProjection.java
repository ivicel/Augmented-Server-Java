package info.ivicel.augmented.core.model.dto;

import org.springframework.beans.factory.annotation.Value;

public interface SupporterProjection {
    @Value("#{target.link}")
    String getLink();

    @Value("#{target.supporterBadges.title}")
    String getTitle();

    @Value("#{target.supporterBadges.img}")
    String getImg();
}
