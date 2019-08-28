package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.SupporterUsersPending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupporterUsersPendingRepository extends JpaRepository<SupporterUsersPending, Long> {
}
