package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.Features;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturesRepository extends JpaSpecificationExecutorWithProjection<Features, Long> {
}
