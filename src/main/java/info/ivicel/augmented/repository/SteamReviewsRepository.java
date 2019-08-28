package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.SteamReviews;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SteamReviewsRepository extends JpaRepository<SteamReviews, Long> {
    Optional<SteamReviews> findByAppid(Integer appid);
}
