package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.SupporterBadges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupporterBadgesRepository extends JpaRepository<SupporterBadges, Integer> {
}
