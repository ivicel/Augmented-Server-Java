package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.SteamReviews;
import info.ivicel.augmented.repository.SteamReviewsRepository;
import info.ivicel.augmented.service.SteamReviewsService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service("steamReviewsService")
public class SteamReviewsServiceImpl implements SteamReviewsService {
    private SteamReviewsRepository steamReviewsRepository;

    public SteamReviewsServiceImpl(SteamReviewsRepository steamReviewsRepository) {
        this.steamReviewsRepository = steamReviewsRepository;
    }

    @Override
    public Optional<SteamReviews> findByAppid(Integer appid) {
        return steamReviewsRepository.findByAppid(appid);
    }

    @Override
    public void update(SteamReviews review) {
        add(review);
    }

    @Override
    public void add(SteamReviews review) {
        steamReviewsRepository.save(review);
    }
}
