package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.SteamReviews;
import java.util.Optional;

public interface SteamReviewsService {
    Optional<SteamReviews> findByAppid(Integer appid);

    void update(SteamReviews review);

    void add(SteamReviews review);
}
